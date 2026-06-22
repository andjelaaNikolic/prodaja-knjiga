/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tabele;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Racun;

/**
 *
 * @author Ljilja
 */
public class ModelTabeleRacun extends AbstractTableModel {
    
    private List<Racun> racuni;
    String[] kolone = {"Id","Datum","Ukupan iznos","Prodavac","Kupac"};

    public ModelTabeleRacun(List<Racun> racuni) {
        this.racuni = racuni;
    }

    public List<Racun> getRacuni() {
        return racuni;
    }

    public void setRacuni(List<Racun> racuni) {
        this.racuni = racuni;
    }
    
    

    @Override
    public int getRowCount() {
        return racuni.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Racun r = racuni.get(rowIndex);
        String datumNovi = "";
        try{
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        datumNovi = format.format(r.getDatum());
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Greška prilikom obrade datuma", "Greška", JOptionPane.ERROR_MESSAGE);
        }
        switch(columnIndex){
            case 0:
                return r.getIdRacun();
            case 1:
                return datumNovi;
            case 2:
                return r.getUkupanIznos();
            case 3:
                return r.getProdavac().getKorisnickoIme();
            case 4:
                return r.getKupac().getIme()+" "+r.getKupac().getPrezime();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
    public void refresujTabelu(){
        fireTableDataChanged();
    }
    
    public void dodaj(Racun r) {
        racuni.add(r);
        fireTableDataChanged();
    }
    
}
