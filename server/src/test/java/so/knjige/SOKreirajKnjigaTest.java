package so.knjige;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import db.repozitorijum.DBKonekcija;
import model.Knjiga;
import model.Magacin;
import repozitorijum.Repozitorijum;
import sistemske.operacije.OpsteSistemskeOperacije;


class SOKreirajKnjigaTest {

    SOKreirajKnjiga so;

    @BeforeEach
    void setUp() throws Exception {
        so = new SOKreirajKnjiga();
    }

    @AfterEach
    void tearDown() throws Exception {
        so = null;
    }

    private Knjiga napraviIspravnuKnjigu() {
        Magacin magacin = new Magacin(1, "Centralni", "Bulevar 1");
        return new Knjiga("Na Drini ćuprija", "Roman", 2020, 990, 10, magacin);
    }

    @Test
    void testIsUspesnoPocetnaVrednost() {
        assertFalse(so.isUspesno(), "Početna vrednost atributa uspesno mora biti false");
    }

    @Test
    void testPredusloviNull() {
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi(null));
        assertEquals("Nije prosleđen parametar odgovarajućeg tipa.", ex.getMessage());    }

    @Test
    void testPredusloviPogresanTip() {
        Exception ex = assertThrows(Exception.class, () -> so.preduslovi("pogrešan tip"));
        assertEquals("Nije prosleđen parametar odgovarajućeg tipa.", ex.getMessage());    }



    private SOKreirajKnjiga napraviSOSaMockom(
            Repozitorijum mockBroker,
            Connection mockConnection,
            MockedStatic<DBKonekcija> mockedStatic) throws Exception {
        DBKonekcija mockDBKonekcija = mock(DBKonekcija.class);
        mockedStatic.when(DBKonekcija::getInstance).thenReturn(mockDBKonekcija);
        when(mockDBKonekcija.getConnection()).thenReturn(mockConnection);

        SOKreirajKnjiga soSaMockom = new SOKreirajKnjiga();
        Field brokerField = OpsteSistemskeOperacije.class.getDeclaredField("broker");
        brokerField.setAccessible(true);
        brokerField.set(soSaMockom, mockBroker);
        return soSaMockom;
    }

    @Test
    void testPredusloviKnjigaNePostojiNeBacaIzuzetak() throws Exception {
        Knjiga k = napraviIspravnuKnjigu();

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); 

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            SOKreirajKnjiga soSaMockom = napraviSOSaMockom(mock(Repozitorijum.class), mockConnection, mockedStatic);

            assertDoesNotThrow(() -> soSaMockom.preduslovi(k));
        }
    }

    @Test
    void testIzvrsiOperacijuKnjigaNePostojiUspesnoKreirana() throws Exception {
        Knjiga k = napraviIspravnuKnjigu();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); 

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            SOKreirajKnjiga soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.preduslovi(k);
            soSaMockom.izvrsiOperaciju(k, null);

            assertTrue(soSaMockom.isUspesno(), "Kreiranje knjige mora biti uspešno kada knjiga ne postoji");
            verify(mockBroker, times(1)).dodaj(k);
        }
    }

    @Test
    void testIzvrsiOperacijuKnjigaVecPostojiNeUspesno() throws Exception {
        Knjiga k = napraviIspravnuKnjigu();

        Repozitorijum mockBroker = mock(Repozitorijum.class);

        Connection mockConnection = mock(Connection.class);
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); 

        try (MockedStatic<DBKonekcija> mockedStatic = mockStatic(DBKonekcija.class)) {
            SOKreirajKnjiga soSaMockom = napraviSOSaMockom(mockBroker, mockConnection, mockedStatic);

            soSaMockom.preduslovi(k);
            soSaMockom.izvrsiOperaciju(k, null);

            assertFalse(soSaMockom.isUspesno(), "Kreiranje knjige ne treba da bude uspešno kada knjiga već postoji");
            verify(mockBroker, never()).dodaj(any());
        }
    }
}