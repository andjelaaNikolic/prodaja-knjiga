/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tabele;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Knjiga;

/**
 *
 * @author Ljilja
 */
public class ModelTabeleKnjige extends AbstractTableModel {
    List<Knjiga> knjige;
    String[] kolone = {"id","Naslov","Zanr","Godina izdanja","Cena","Kolicina","Magacin"};
    
    public ModelTabeleKnjige(List<Knjiga> knjige){
        this.knjige = knjige;
    }

    @Override
    public int getRowCount() {
        return knjige.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Knjiga k = knjige.get(rowIndex);
        switch(columnIndex){
            case 0: return k.getIdKnjiga();
            case 1: return k.getNaslov();
            case 2: return k.getZanr();
            case 3: return k.getGodinaIzdanja();
            case 4: return k.getCena();
            case 5: return k.getKolicina();
            case 6: return k.getMagacin();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }

    public void setKnjige(List<Knjiga> knjige) {
        this.knjige = knjige;
    }
    
    
    
    public void refresujPodatke() {
        fireTableDataChanged();
    }
    
    
    
}
