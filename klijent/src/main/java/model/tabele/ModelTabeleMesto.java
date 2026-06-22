/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.tabele;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Mesto;

/**
 *
 * @author Ljilja
 */
public class ModelTabeleMesto extends AbstractTableModel {
    
    List<Mesto> mesta;
    String[] kolone = {"id","naziv mesta"};

    public ModelTabeleMesto(List<Mesto> mesta) {
        this.mesta = mesta;
    }

    public void setMesta(List<Mesto> mesta) {
        this.mesta = mesta;
    }
    
    

    @Override
    public int getRowCount() {
        return mesta.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Mesto m = mesta.get(rowIndex);
        switch(columnIndex){
            case 0:
                return m.getIdMesto();
            case 1:
                return m.getNazivMesta();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public List<Mesto> getMesta() {
        return mesta;
    }

    public void refresujPodatke() {
        fireTableDataChanged();
    }
    
    
    
    
    
    
}
