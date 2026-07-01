/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.racun;

import java.util.ArrayList;
import java.util.List;
import model.Knjiga;
import model.Prodavac;
import model.Racun;
import model.StavkaRacuna;
import sistemske.operacije.OpsteSistemskeOperacije;

/**
 * Sistemska operacija za pretragu liste racuna u kojima se pojavljuje
 * odredjena knjiga.
 *
 * @author Andjela
 * @see Racun
 * @see Knjiga
 */
public class SOVratiListuRacunKnjiga extends OpsteSistemskeOperacije {
    
    /** Lista racuna koji sadrze prosledjenu knjigu. */
    private List<Racun> racuni;

    /**
     * Vraca listu racuna pronadjenih pretragom.
     *
     * @return lista racuna koji sadrze prosledjenu knjigu
     */
    public List<Racun> getRacuni() {
        return racuni;
    }

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa.
     *
     * @param param objekat tipa {@link Racun}
     * @throws Exception ako parametar nije odgovarajuceg tipa
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Racun)) {
            throw new Exception("Nije prosleđen parametar odgovarajućeg tipa.");
        }
    }

    /**
     * Izvrsava pretragu liste racuna u kojima se pojavljuje knjiga
     * prosledjena kroz kljuc. Prvo se pronalaze sve stavke koje sadrze tu
     * knjigu, zatim se za svaku od njih ucitava odgovarajuci racun, i na
     * kraju se svakom racunu postavlja ista lista stavki.
     *
     * @param param objekat tipa {@link Racun}
     * @param kljuc objekat tipa {@link Knjiga} po kojoj se racuni pretrazuju
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        Knjiga k = (Knjiga) kljuc;
        String upit = " JOIN knjiga ON knjiga.idKnjiga=stavka_racuna.knjiga JOIN magacin ON knjiga.magacin=magacin.idMagacin JOIN racun ON racun.idRacun=stavka_racuna.racun WHERE knjiga.idKnjiga="+k.getIdKnjiga();
        List<StavkaRacuna> stavke = broker.vratiSve(new StavkaRacuna(), upit);
        racuni = new ArrayList<>();
        
  
       for (StavkaRacuna ss : stavke) {
        int idRacun = ss.getRacun().getIdRacun();
        String upit1 = " JOIN prodavac ON prodavac.idProdavac = racun.prodavac JOIN kupac ON kupac.idKupac=racun.kupac JOIN mesto ON mesto.idMesto=kupac.mesto WHERE racun.idRacun="+idRacun;
        Racun racun = (Racun) broker.vratiObjekat(new Racun(), upit1);
        racuni.add(racun);
        }
       
       for (Racun r : racuni) {
            String uslov1 = " JOIN knjiga ON knjiga.idKnjiga=stavka_racuna.knjiga JOIN magacin ON knjiga.magacin=magacin.idMagacin WHERE racun="+r.getIdRacun();
            r.setStavke(stavke);
        }
       
       
       
        this.racuni=racuni;
       
       

   
       
       
    }
    
}

