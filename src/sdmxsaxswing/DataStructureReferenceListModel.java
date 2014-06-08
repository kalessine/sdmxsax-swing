/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sdmxsaxswing;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import sdmx.commonreferences.DataStructureReferenceType;
import sdmx.commonreferences.IDType;
import sdmx.commonreferences.NestedIDType;
import sdmx.commonreferences.NestedNCNameIDType;
import sdmx.commonreferences.VersionType;

/**
 *
 * @author James
 */
public class DataStructureReferenceListModel implements ListModel {

    public DataStructureReferenceListModel(){}
    
    List<DataStructureReferenceType> list = null;
    
    @Override
    public int getSize() {
        if(list==null)return 0;
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        DataStructureReferenceType ref = list.get(index);
        NestedNCNameIDType agency = ref.getRef().getAgencyId();
        NestedIDType id = ref.getRef().getId();
        VersionType vers = ref.getRef().getVersion();
        return "Agency:"+agency.toString()+", ID:"+id.toString()+": Version:"+(vers==null?"":vers.toString());
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
    public void setList(List<DataStructureReferenceType> list) {
        this.list=list;
        ListDataEvent event = new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,0,list.size());
        for(int i=0;i<listeners.size();i++) {
            listeners.get(i).contentsChanged(event);
        }
    }
    public DataStructureReferenceType getDataStructureReference(int i) {
        return list.get(i);
    }
}
