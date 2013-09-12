package ch.k42.rpi.transport.gui;

import ch.k42.rpi.transport.api.ListItem;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created with IntelliJ IDEA.
 * User: thomas
 * Date: 9/12/13
 * Time: 3:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class JPanelModel implements ListModel<JPanel> {

    private List<ListItem> queueItems;
    private Set<ListDataListener> listeners;

    public JPanelModel() {
        this.queueItems = new LinkedList<ListItem>();
        this.listeners = new HashSet<ListDataListener>();
    }

    public void push(ListItem item){
        queueItems.add(item);
        notifyAllListenersAdded(queueItems.size()-1,queueItems.size()-1); //TODO does this work?
    }

    public void clearList(){
        int oldsize=Math.max(queueItems.size(),1);
        queueItems.clear();
        notifyAllListenersRemoved(0,oldsize-1);
    }

    public void update(Date now){
        int removed=0;
        boolean invalid;
        do{
            invalid = false;
            if(queueItems.get(0).isOutOfDate()){
                invalid=true;
                removed++;
                queueItems.remove(0);
            }
        }while (invalid);
        notifyAllListenersRemoved(0,removed);
    }

    public void update(){
        update(new Date());
    }

    @Override
    public int getSize() {
        return queueItems.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public JPanel getElementAt(int i) {
        return queueItems.get(i).getJPanel();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addListDataListener(ListDataListener listDataListener) {
        listeners.add(listDataListener);
    }

    @Override
    public void removeListDataListener(ListDataListener listDataListener) {
        listeners.remove(listDataListener);
    }

    private void notifyAllListenersChanged(int from, int to){
        for(ListDataListener l : listeners){
            l.contentsChanged(new ListDataEvent(this,ListDataEvent.CONTENTS_CHANGED,from,to));
        }
    }

    private void notifyAllListenersAdded(int from, int to){
        for(ListDataListener l : listeners){
            l.contentsChanged(new ListDataEvent(this,ListDataEvent.INTERVAL_ADDED,from,to));
        }
    }

    private void notifyAllListenersRemoved(int from, int to){
        for(ListDataListener l : listeners){
            l.contentsChanged(new ListDataEvent(this,ListDataEvent.INTERVAL_REMOVED,from,to));
        }
    }

}
