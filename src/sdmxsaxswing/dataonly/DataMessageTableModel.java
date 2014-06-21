/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdmxsaxswing.dataonly;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import sdmx.data.DataSet;
import sdmx.data.flat.FlatDataSet;
import sdmx.data.structured.StructuredDataSet;
import sdmx.message.DataMessage;

/**
 *
 * @author James
 */
public class DataMessageTableModel implements TableModel{

    private DataMessage msg = null;
    private int dataSet =0;
    //private StructuredDataSet ds = null;
    private DataSet ds = null;
    
    @Override
    public int getRowCount() {
        if( ds==null ) return 0;
        return ds.size();
    }

    @Override
    public int getColumnCount() {
        if( ds == null ) {
            return 0;
        }
        return ds.getColumnSize();
    }

    @Override
    public String getColumnName(int columnIndex) {
        if( ds == null ) return "";
        return ds.getColumnMapper().getColumnName(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if( ds == null ) return null;
        return ds.getValue(rowIndex,columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ds.setValue(rowIndex, columnIndex, aValue.toString());
        TableModelEvent event = new TableModelEvent(this,0,this.getRowCount(),0,this.getColumnCount());
        for(int i=0;i<listeners.size();i++) {
            listeners.get(i).tableChanged(event);
        }
    }

    List<TableModelListener> listeners = new ArrayList<TableModelListener>();
    
    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
        
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    /**
     * @return the msg
     */
    public DataMessage getDataMessage() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setDataMessage(DataMessage msg) {
        this.msg = msg;
        //this.ds = (StructuredDataSet)msg.getDataSets().get(0);
        this.ds = msg.getDataSets().get(0);
        TableModelEvent event = new TableModelEvent(this,0,this.getRowCount(),0,this.getColumnCount());
        for(int i=0;i<listeners.size();i++) {
            listeners.get(i).tableChanged(event);
        }
    }
}

