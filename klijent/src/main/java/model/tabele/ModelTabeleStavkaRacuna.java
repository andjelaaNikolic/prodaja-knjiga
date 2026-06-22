/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tabele;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StavkaRacuna;

/**
 *
 * @author Ljilja
 */
public class ModelTabeleStavkaRacuna extends AbstractTableModel {

    List<StavkaRacuna> stavkeRacuna;
    String[] kolone = {"redni broj","knjiga","kolicina","jedinicna cena","iznos"};

    public ModelTabeleStavkaRacuna(List<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }

    public List<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(List<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = stavkeRacuna;
    }
    
    
    @Override
    public int getRowCount() {
        return stavkeRacuna.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       StavkaRacuna sr = stavkeRacuna.get(rowIndex);
       switch(columnIndex){
           case 0:
               return sr.getRb();
           case 1:
               return sr.getKnjiga().getNaslov();
           case 2:
               return sr.getKolicina();
           case 3:
               return sr.getJedinicnaCena();
           case 4:
               return sr.getIznos();
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

    public void dodaj(StavkaRacuna sr) {
        stavkeRacuna.add(sr);
        fireTableDataChanged();
    }
    
    
    
}
