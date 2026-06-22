/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tabele;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.StrSprema;

/**
 *
 * @author Ljilja
 */
public class ModelTabeleStrSprema extends AbstractTableModel {

    List<StrSprema> strSpreme;
    String[] kolone = {"id","stepen"};

    public ModelTabeleStrSprema(List<StrSprema> strSpreme) {
        this.strSpreme = strSpreme;
    }

    public void setStrSpreme(List<StrSprema> strSpreme) {
        this.strSpreme = strSpreme;
    }
    
    
    @Override
    public int getRowCount() {
       return strSpreme.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StrSprema ss = strSpreme.get(rowIndex);
        switch(columnIndex){
            case 0:
                return ss.getIdStrucnaSprema();
            case 1:
                return ss.getStepen();
                
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    

    public List<StrSprema> getStrSpreme() {
        return strSpreme;
    }
    
    public void refresujPodatke() {
        fireTableDataChanged();
    }
    
    
}
