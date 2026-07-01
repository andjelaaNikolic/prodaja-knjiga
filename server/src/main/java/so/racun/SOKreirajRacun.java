/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import db.repozitorijum.DBKonekcija;
import model.Racun;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.StavkaRacuna;
import java.sql.PreparedStatement;
/**
 * Sistemska operacija za kreiranje novog racuna, zajedno sa svim njegovim
 * stavkama ({@link StavkaRacuna}).
 * Pre kreiranja proverava da li racun sa identicnim stavkama (isti datum,
 * ukupan iznos, prodavac i kupac, kao i podudarajuce stavke) vec postoji u
 * bazi podataka.
 *
 * @author Andjela
 * @see Racun
 * @see StavkaRacuna
 */
public class SOKreirajRacun extends OpsteSistemskeOperacije {
    
    /** Indikator da li racun sa identicnim stavkama vec postoji u bazi podataka. */
    private boolean postoji=false;
    
    /** Indikator uspesnosti kreiranja racuna. */
    private boolean uspesno=false;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, i da li
     * racun sa istim datumom, ukupnim iznosom, prodavcem i kupcem, cije se
     * sve stavke poklapaju sa prosledjenim stavkama, vec postoji u bazi
     * podataka.
     *
     * @param param objekat tipa {@link Racun} koji se kreira
     * @throws Exception ako parametar nije odgovarajuceg tipa
     * @throws SQLException ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
        int brStavki = ((Racun) param).getStavke().size();
        int brIstih = 0;
        

        try {
            Date datum = new java.sql.Date(((Racun) param).getDatum().getTime());

            String upit = "SELECT * FROM knjiga k JOIN stavka_racuna sr ON k.idKnjiga=sr.knjiga JOIN racun r ON r.idRacun=sr.racun JOIN prodavac p ON r.prodavac=p.idProdavac JOIN kupac ku ON ku.idKupac=r.kupac WHERE r.datum='" + datum + "' AND r.ukupanIznos=" + ((Racun) param).getUkupanIznos() + " AND p.idProdavac=" + ((Racun) param).getProdavac().getIdProdavac() + " AND ku.idKupac=" + ((Racun) param).getKupac().getIdKupac();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {

                String naslov = rs.getString("k.naslov");
                //int dostupnaKolicina = rs.getInt("knjiga.kolicina");
                int kolicina = rs.getInt("sr.kolicina");
                double jedinicnaCena = Double.parseDouble(rs.getString("sr.jedinicnaCena"));
                double iznos = Double.parseDouble(rs.getString("sr.iznos"));

                for (StavkaRacuna stavka : ((Racun) param).getStavke()) {
                    if (naslov.equals(stavka.getKnjiga().getNaslov())
                            && jedinicnaCena == stavka.getJedinicnaCena()
                            && kolicina == stavka.getKolicina()
                            && iznos == stavka.getIznos()) {

                        brIstih++;
                    }
                }
            }
            if (brIstih == brStavki) {
                postoji = true;
            }

        } catch (SQLException ex) {
            throw ex;
        }
    }

    /**
     * Izvrsava kreiranje racuna u bazi podataka. Racun se upisuje samo
     * ukoliko istovetan racun jos ne postoji. Nakon upisa racuna, ucitava
     * se njegov generisani ID iz baze i svaka stavka racuna se povezuje sa
     * tim racunom i upisuje u bazu.
     *
     * @param param objekat tipa {@link Racun} koji se kreira
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        
         if (!postoji) {
            broker.dodaj((Racun) param);
            int Id = -1;
            
                Date datum = new java.sql.Date(((Racun) param).getDatum().getTime());
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                String datumString = sdf.format(((Racun) param).getDatum());

                String upit = "SELECT * FROM racun WHERE prodavac ="+((Racun) param).getProdavac().getIdProdavac()+" AND kupac ="+((Racun) param).getKupac().getIdKupac()+" AND ukupanIznos ="+ ((Racun) param).getUkupanIznos()+" AND datum ='"+datumString+"'";
                Statement st = DBKonekcija.getInstance().getConnection().createStatement();

                ResultSet rs = st.executeQuery(upit);
                
                while(rs.next()){
                    Id = rs.getInt("racun.idRacun");
                }

            ((Racun) param).setIdRacun(Id);

            for (StavkaRacuna stavka : ((Racun) param).getStavke()) {
                stavka.setRacun((Racun) param);
                broker.dodaj(stavka);

            }

            uspesno = true;
        }
        
        
        
    }

    /**
     * Vraca indikator uspesnosti kreiranja racuna.
     *
     * @return true ako je racun uspesno kreiran, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }
    
    
    
}

