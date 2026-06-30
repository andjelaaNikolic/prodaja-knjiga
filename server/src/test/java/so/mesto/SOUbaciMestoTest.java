package so.mesto;

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
import model.Mesto;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;

class SOUbaciMestoTest {

    private SOUbaciMesto so;

    @BeforeEach
    void setUp() {
        so = new SOUbaciMesto();
    }

    @AfterEach
    void tearDown() {
        so = null;
    }

    private Mesto napraviMesto() {
        return new Mesto(1, "Beograd");
    }

    private SOUbaciMesto napraviSOSaMockom(
            Repozitorijum mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        SOUbaciMesto soSaMockom = new SOUbaciMesto();
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
    void testUbaciMestoKadaNePostoji() throws Exception {
        Mesto m = napraviMesto();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false);

        doNothing().when(mockBroker).dodaj(m);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            SOUbaciMesto soTest = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soTest.preduslovi(m);
            soTest.izvrsiOperaciju(m, null);

            assertTrue(soTest.isUspesno());
            verify(mockBroker, times(1)).dodaj(m);
        }
    }

    @Test
    void testUbaciMestoKadaPostoji() throws Exception {
        Mesto m = napraviMesto();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            SOUbaciMesto soTest = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soTest.preduslovi(m);
            soTest.izvrsiOperaciju(m, null);

            assertFalse(soTest.isUspesno());
            verify(mockBroker, never()).dodaj(any());
        }
    }
}