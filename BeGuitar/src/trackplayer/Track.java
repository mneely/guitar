package trackplayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Track implements Serializable{

    volatile List array;
    final int length;
    Track(int l){
        length = l;
        array = Collections.synchronizedList(new ArrayList<TrackBlock>(length));
        for(int i = 0; i < length; i++){
            array.add(i,null);
        }
    }

    public synchronized void add(int location, boolean o, String noteName){
        //if(array[location] != null){
        if(location < array.size()){
            if(array.get(location) != null){
            }else if (array.get(location) == null) {
                array.set(location, new TrackBlock(new Note(noteName, 1, location), o));
            }
        }
    }
}

class TrackBlock implements Serializable{
    volatile boolean isOccupied = false;
    volatile Note note;
    TrackBlock(Note n, boolean o){
        note = n;
        isOccupied = o;
    }

    synchronized void setNote(String n, int l, int s){
        note = new Note(n,l,s);
    }
}