/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tabele;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.PrSS;

/**
 *
 * @author Ljilja
 */
public class ModelTabeleProdavacStrSprema extends AbstractTableModel {
    
    private List<PrSS> prss;
    String[] kolone={"strucna sprema","datum sticanja","institucija","grad"};

    public ModelTabeleProdavacStrSprema(List<PrSS> prss) {
        this.prss = prss;
    }

    public List<PrSS> getPrss() {
        return prss;
    }

    public void setPrss(List<PrSS> prss) {
        this.prss = prss;
    }
    

    @Override
    public int getRowCount() {
       return prss.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PrSS ps = prss.get(rowIndex);
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy.");
        String datumNovi = format.format(ps.getDatumSticanja());
        switch(columnIndex){
            case 0:
                return ps.getStrSprema().getStepen();
            case 1:
                return datumNovi;
            case 2:
                return ps.getInstitucija(); 
            case 3:
                return ps.getGrad();
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

    public void dodaj(PrSS ps) {
        prss.add(ps);
        fireTableDataChanged();
    }
    
}
