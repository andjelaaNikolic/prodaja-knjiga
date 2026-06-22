/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tabele;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Kupac;

/**
 *
 * @author Ljilja
 */
public class ModelTabeleKupac extends AbstractTableModel {

    List<Kupac> kupci;
    String[] kolone = {"id","ime","prezime","broj telefona","mesto"};

    public ModelTabeleKupac(List<Kupac> kupci) {
        this.kupci = kupci;
    }

    public List<Kupac> getKupci() {
        return kupci;
    }

    public void setKupci(List<Kupac> kupci) {
        this.kupci = kupci;
    }
   
    
    @Override
    public int getRowCount() {
        return kupci.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Kupac kupac = kupci.get(rowIndex);
        switch(columnIndex){
            case 0:
                return kupac.getIdKupac();
            case 1:
                return kupac.getIme();
            case 2:
                return kupac.getPrezime();
            case 3:
                return kupac.getBrojTelefona();
            case 4:
                return kupac.getMesto();
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
