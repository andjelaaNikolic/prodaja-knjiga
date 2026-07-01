/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.prodavac;
import db.repozitorijum.DBKonekcija;
import model.Prodavac;
import sistemske.operacije.OpsteSistemskeOperacije;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import model.PrSS;
import model.StrSprema;

/**
 * Sistemska operacija za promenu podataka postojeceg prodavca, ukljucujuci
 * i uskladjivanje njegove liste strucnih sprema ({@link PrSS}) sa stanjem u
 * bazi podataka (dodavanje novih, izmena postojecih i brisanje onih koje
 * vise nisu prisutne u prosledjenoj listi).
 *
 * @author Andjela
 * @see Prodavac
 * @see PrSS
 */
public class SOPromeniProdavac extends OpsteSistemskeOperacije {

    /** Indikator da li lista strucnih sprema prodavca sadrzi duplirane unose (ista strucna sprema i institucija). */
    private boolean postoji = false;
    /** Indikator uspesnosti izmene prodavca. */
    private boolean uspesno = false;

    /**
     * Proverava da li je prosledjen parametar odgovarajuceg tipa, da li je
     * lista strucnih sprema prodavca postavljena, i da li ta lista sadrzi
     * dva unosa sa istom strucnom spremom i institucijom (sto nije
     * dozvoljeno).
     *
     * @param param objekat tipa {@link Prodavac} koji se menja
     * @throws Exception ako parametar nije odgovarajuceg tipa, ili ako
     *         lista strucnih sprema nije postavljena
     */
    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Prodavac)) {
            throw new Exception("Nije prosledjen parametar odgovarajuceg tipa.");
        }
        Prodavac prodavac = (Prodavac) param;
        List<PrSS> prssZaIzmenu = prodavac.getPrss();
        if (prssZaIzmenu == null) {
            throw new Exception("Lista strucnih sprema prodavca nije postavljena.");
        }

        for (int i = 0; i < prssZaIzmenu.size(); i++) {
            for (int j = i + 1; j < prssZaIzmenu.size(); j++) {
                if ((prssZaIzmenu.get(i).getStrSprema().getIdStrucnaSprema() ==
                        prssZaIzmenu.get(j).getStrSprema().getIdStrucnaSprema()) && prssZaIzmenu.get(i).getInstitucija().equals(prssZaIzmenu.get(j).getInstitucija())) {
                    postoji = true;
                    return;
                }
            }
        }

    }

    /**
     * Izvrsava izmenu podataka prodavca u bazi podataka. Uporedjuje
     * prosledjenu listu strucnih sprema sa onom iz baze: postojece unose
     * azurira, nove dodaje, a one koji vise nisu prisutni u prosledjenoj
     * listi brise. Na kraju azurira i osnovne podatke prodavca.
     *
     * @param param objekat tipa {@link Prodavac} koji se menja
     * @param kljuc nije koriscen u ovoj operaciji
     * @throws Exception ako dodje do greske pri radu sa bazom podataka
     */
    @Override
    protected void izvrsiOperaciju(Object param, Object kljuc) throws Exception {
        Prodavac prodavac = (Prodavac) param;
        if (postoji != true) {

            List<PrSS> prssZaIzmenu = prodavac.getPrss();
            String upit = " JOIN prodavac ON prodavac.idProdavac=prss.prodavac JOIN str_sprema ON str_sprema.idStrucnaSprema=prss.strSprema WHERE prodavac.idProdavac=" + prodavac.getIdProdavac();
            List<PrSS> prssIzBaze = broker.vratiSve(new PrSS(), upit);

            for (PrSS sr : prssZaIzmenu) {
                boolean postojiUBazi = false;
                for (PrSS srBaza : prssIzBaze) {
                    if ((sr.getStrSprema().getIdStrucnaSprema() == srBaza.getStrSprema().getIdStrucnaSprema()) && (sr.getInstitucija().equals(srBaza.getInstitucija()))) {
                        postojiUBazi = true;
                        break;
                    }
                }

                if (postojiUBazi) {
                    broker.promeni(sr);
                } else {
                    sr.setProdavac(prodavac);
                    broker.dodaj(sr);
                }
            }

            for (PrSS srBaza : prssIzBaze) {
                boolean josUvekPostojiUListi = false;
                for (PrSS sr : prssZaIzmenu) {
                    if ((sr.getStrSprema().getIdStrucnaSprema() == srBaza.getStrSprema().getIdStrucnaSprema()) && (sr.getInstitucija().equals(srBaza.getInstitucija()))) {
                        josUvekPostojiUListi = true;
                        break;
                    }
                }
                if (!josUvekPostojiUListi) {
                    broker.obrisi(srBaza);
                }
            }

            broker.promeni(prodavac);
            uspesno = true;

        }

    }

    /**
     * Vraca indikator uspesnosti izmene prodavca.
     *
     * @return true ako je prodavac uspesno izmenjen, false inace
     */
    public boolean isUspesno() {
        return uspesno;
    }


}

