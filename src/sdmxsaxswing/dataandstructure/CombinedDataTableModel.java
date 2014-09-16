/**
 *  This file is part of SdmxSax.
 *
 *   SdmxSax is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 
 *  SdmxSax is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with SdmxSax.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Copyright James Gardner 2014
 */
package sdmxsaxswing.dataandstructure;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import sdmx.structureddata.StructuredDataMessage;
import sdmx.structureddata.StructuredDataSet;
import sdmx.message.DataMessage;

/**
 *
 * @author James
 */
public class CombinedDataTableModel implements TableModel {

    StructuredDataMessage doc = null;
    
    @Override
    public int getRowCount() {
        if( doc == null ) return 0;
        return doc.getStructuredDataSet(0).size();
    }

    @Override
    public int getColumnCount() {
        if( doc == null ) return 0;
        return doc.getStructuredDataSet(0).getColumnCount();
    }

    @Override
    public String getColumnName(int columnIndex) {
        return doc.getStructuredDataSet(0).getColumnName(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StructuredDataSet ds = doc.getStructuredDataSet(0);
        return ds.getDecoratedValue(rowIndex, columnIndex).toString();
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

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
    public StructuredDataMessage getCombinedDataMessage() {
        return doc;
    }
    /**
     * @param msg the msg to set
     */
    public void setCombinedDataMessage(StructuredDataMessage cds) {
        this.doc=cds;
        TableModelEvent event = new TableModelEvent(this,0,this.getRowCount(),0,this.getColumnCount());
        for(int i=0;i<listeners.size();i++) {
            listeners.get(i).tableChanged(event);
        }
    }
    
}
