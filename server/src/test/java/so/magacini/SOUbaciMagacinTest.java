package so.magacini;

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
import model.Magacin;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;

class SOUbaciMagacinTest {

    private SOUbaciMagacin so;

    @BeforeEach
    void setUp() {
        so = new SOUbaciMagacin();
    }

    @AfterEach
    void tearDown() {
        so = null;
    }

    private Magacin napraviMagacin() {
        return new Magacin(1, "Centralni", "Bulevar 1");
    }

    private SOUbaciMagacin napraviSOSaMockom(
            Repozitorijum mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {

        DBKonekcija mockDB = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDB);
        when(mockDB.getConnection()).thenReturn(mockConnection);

        SOUbaciMagacin soSaMockom = new SOUbaciMagacin();
        Field brokerField = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);

        return soSaMockom;
    }

    @Test
    void testIsUspesnoPocetnaVrednost() {
        assertFalse(so.isUspesno());
    }

    @Test
    void testPredusloviNull() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi(null));

        assertEquals("Nije prosledjen parametar odgovarajuceg tipa.", ex.getMessage());
    }

    @Test
    void testPredusloviPogresanTip() {
        Exception ex = assertThrows(Exception.class,
                () -> so.preduslovi("test"));

        assertEquals("Nije prosledjen parametar odgovarajuceg tipa.", ex.getMessage());
    }


    @Test
    void testUbaciMagacinKadaNePostoji() throws Exception {
        Magacin m = napraviMagacin();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        doNothing().when(mockBroker).dodaj(m);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            SOUbaciMagacin soTest = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soTest.preduslovi(m);
            soTest.izvrsiOperaciju(m, null);

            assertTrue(soTest.isUspesno());
            verify(mockBroker, times(1)).dodaj(m);
        }
    }

    @Test
    void testUbaciMagacinKadaPostoji() throws Exception {
        Magacin m = napraviMagacin();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            SOUbaciMagacin soTest = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soTest.preduslovi(m);
            soTest.izvrsiOperaciju(m, null);

            assertFalse(soTest.isUspesno());
            verify(mockBroker, never()).dodaj(any());
        }
    }
}
 