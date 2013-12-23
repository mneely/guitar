package trackplayer;

import java.awt.Color;
import java.io.Serializable;

public class Note implements Serializable{

    volatile String noteName;
    volatile int length;
    volatile int startBlockNo;
    volatile Color color = null;

    public Note(String n, int l, int s) {
        noteName = n;
        length = l;
        startBlockNo = s;
    }

    public synchronized boolean equals(Note other) {
        if (other.noteName.equals(this.noteName)
            && (other.length == this.length)
            && (other.startBlockNo == this.startBlockNo)) {
            return true;
        }
        return false;
    }

}
