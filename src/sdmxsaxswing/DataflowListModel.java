/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdmxsaxswing;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import sdmx.common.Description;
import sdmx.common.Name;
import sdmx.commonreferences.DataStructureReferenceType;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.NestedIDType;
import sdmx.commonreferences.NestedNCNameIDType;
import sdmx.commonreferences.VersionType;
import sdmx.structure.dataflow.DataflowType;

/**
 *
 * @author James
 */
public class DataflowListModel implements ListModel {

    public DataflowListModel(){}
    
    List<DataflowType> list = null;
    
    @Override
    public int getSize() {
        if(list==null)return 0;
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        DataflowType flow = list.get(index);
        Locale loc = Locale.getDefault();
        Name name = flow.findName(loc.getLanguage());
        if( name != null ) return name.getText();
        Description description = flow.findDescription(loc.getLanguage());
        if( description!=null ) return description.getText();
        return flow.getId().toString();
    }

    
    List<ListDataListener> listeners = new ArrayList<ListDataListener>();
    
    @Override
    public void addListDataListener(ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public void removeListDataListener(ListDataListener l) {
        listeners.remove(l);
    }
    public void setList(List<DataflowType> list) {
        this.list=list;
        ListDataEvent event = new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,0,list.size());
        for(int i=0;i<listeners.size();i++) {
            listeners.get(i).contentsChanged(event);
        }
    }
    public DataflowType getDataflow(int i) {
        return list.get(i);
    }
}
