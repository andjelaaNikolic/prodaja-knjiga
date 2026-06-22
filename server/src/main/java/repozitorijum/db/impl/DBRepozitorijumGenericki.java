/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repozitorijum.db.impl;

import db.repozitorijum.DBKonekcija;
import db.repozitorijum.DBRepozitorijum;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.OpstiDomenskiObjekat;

/**
 *
 * @author Ljilja
 */
public class DBRepozitorijumGenericki implements DBRepozitorijum<OpstiDomenskiObjekat> {

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat param, String uslov) throws Exception {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        String upit = "SELECT * FROM "+param.vratiNazivTabele();
        if(uslov!=null){
            upit+=uslov;
        }
        System.out.println(upit);
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);
        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void dodaj(OpstiDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO "+param.vratiNazivTabele()+" ( "+param.vratiKoloneZaUbacivanje()+" ) VALUES ( "+param.vratiVrednostiZaUbacivanje()+" )";
        System.out.println(upit);
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
        
    }

    @Override
    public void promeni(OpstiDomenskiObjekat param) throws Exception {
        String upit = "UPDATE "+param.vratiNazivTabele()+" SET "+param.vratiVrednostiZaIzmenu()+ " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void obrisi(OpstiDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
        
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiSve(OpstiDomenskiObjekat param) throws Exception {
        if (DBKonekcija.getInstance().getConnection() == null) {
            System.err.println("Upozorenje: Konekcija nije uspostavljena.");
        } else {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();

            String upit = "SELECT * FROM " + param.vratiNazivTabele();
            Statement st = DBKonekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            lista = param.vratiListu(rs);
            rs.close();
            st.close();
            return lista;
        }
        return null;
    }
    
        @Override
    public OpstiDomenskiObjekat vratiObjekat(OpstiDomenskiObjekat param) throws Exception {

        OpstiDomenskiObjekat objekat = null;
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();

        ResultSet rs = st.executeQuery(upit);
        objekat = param.vratiObjekatIzRS(rs);
        rs.close();
        st.close();
        return objekat;
    }

    @Override
    public OpstiDomenskiObjekat vratiObjekat(OpstiDomenskiObjekat param, String uslov) throws Exception {

        OpstiDomenskiObjekat objekat = null;
        String upit = "SELECT * FROM " + param.vratiNazivTabele();
        if (uslov != null) {
            upit += uslov;
        }
        Statement st = DBKonekcija.getInstance().getConnection().createStatement();

        ResultSet rs = st.executeQuery(upit);
        objekat = param.vratiObjekatIzRS(rs);
        rs.close();
        st.close();
        return objekat;

    }
    
}
