/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tabele;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Prodavac;

/**
 *
 * @author Ljilja
 */
public class ModelTabeleProdavac extends AbstractTableModel {
    
    List<Prodavac> prodavci;
    String[] kolone = {"Ime","Prezime","Korisnicko ime","Sifra","Email"};
    public ModelTabeleProdavac(List<Prodavac> prodavci) {
        this.prodavci = prodavci;
    }

    public List<Prodavac> getProdavci() {
        return prodavci;
    }

    public void setProdavci(List<Prodavac> prodavci) {
        this.prodavci = prodavci;
    }
    

    @Override
    public int getRowCount() {
        return prodavci.size();
    }

    @Override
    public int getColumnCount() {
       return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Prodavac prodavac = prodavci.get(rowIndex);
        switch(columnIndex){
            case 0:
                return prodavac.getIme();
            case 1:
                return prodavac.getPrezime();
            case 2:
                return prodavac.getKorisnickoIme();
            case 3:
                return prodavac.getSifra();
            case 4:
                return prodavac.getEmail();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public void refresujPodatke() {
        fireTableDataChanged();
    }
    
    
    
    
}
