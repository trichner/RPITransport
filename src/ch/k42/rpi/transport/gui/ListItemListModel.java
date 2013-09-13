package ch.k42.rpi.transport.gui;

import ch.k42.rpi.transport.api.ListItem;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/13/13
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListItemListModel extends AbstractListModel<ListItem> {

    private List<ListItem> list;

    public ListItemListModel() {
        this.list = new ArrayList<ListItem>();
    }

    public void setList(List<ListItem> nlist){
        if(!nlist.equals(list)){ // has list even changed?
            int oldsize = list.size();
            list=nlist;
            fireIntervalRemoved(this,0,oldsize);
            fireIntervalAdded(this,0,list.size());
        }
    }

    public void add(ListItem item){
        list.add(item);
        fireIntervalAdded(this,list.size()-1,list.size()-1);
    }

    @Override
    public int getSize() {
        return list.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListItem getElementAt(int i) {
        return list.get(i);  //To change body of implemented methods use File | Settings | File Templates.
    }
}
