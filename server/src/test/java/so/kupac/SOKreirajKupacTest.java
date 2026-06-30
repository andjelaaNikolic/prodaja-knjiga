package so.kupac;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import db.repozitorijum.DBKonekcija;
import model.Kupac;
import model.Mesto;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;

class SOKreirajKupacTest {

    private SOKreirajKupac so;

    @BeforeEach
    void setUp() {
        so = new SOKreirajKupac();
    }

    @AfterEach
    void tearDown() {
        so = null;
    }

    private Kupac napraviIspravnogKupca() {
        Mesto mesto = new Mesto(1, "Beograd");
        return new Kupac("Petar", "Petrović", "061123456", mesto);
    }

    @Test
    void testIsUspesnoPocetnaVrednost() {
        assertFalse(so.isUspesno());
    }

    @Test
    void testPredusloviNull() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi(null));

        assertTrue(ex.getMessage().contains("odgovarajuceg tipa"));
    }

    @Test
    void testPredusloviPogresanTip() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi("test"));

        assertTrue(ex.getMessage().contains("odgovarajuceg tipa"));
    }

    private SOKreirajKupac napraviSOSaMockom(
            Repozitorijum mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {

        DBKonekcija mockDB = mock(DBKonekcija.class);

        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDB);
        when(mockDB.getConnection()).thenReturn(mockConnection);

        SOKreirajKupac soMock = new SOKreirajKupac();

        Field brokerField = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soMock, mockBroker);

        return soMock;
    }

    @Test
    void testPredusloviKupacNePostoji() throws Exception {

        Kupac k = napraviIspravnogKupca();

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {

            SOKreirajKupac soMock = napraviSOSaMockom(
                    mock(Repozitorijum.class),
                    mockConnection,
                    mockedStatic);

            assertDoesNotThrow(() -> soMock.preduslovi(k));
        }
    }

    @Test
    void testIzvrsiKupacNePostojiUspesno() throws Exception {

        Kupac k = napraviIspravnogKupca();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {

            SOKreirajKupac soMock = napraviSOSaMockom(
                    mockBroker,
                    mockConnection,
                    mockedStatic);

            soMock.preduslovi(k);
            soMock.izvrsiOperaciju(k, null);

            assertTrue(soMock.isUspesno());
            verify(mockBroker, times(1)).dodaj(k);
        }
    }

    @Test
    void testIzvrsiKupacVecPostoji() throws Exception {

        Kupac k = napraviIspravnogKupca();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {

            SOKreirajKupac soMock = napraviSOSaMockom(
                    mockBroker,
                    mockConnection,
                    mockedStatic);

            soMock.preduslovi(k);
            soMock.izvrsiOperaciju(k, null);

            assertFalse(soMock.isUspesno());
            verify(mockBroker, never()).dodaj(any());
        }
    }
}