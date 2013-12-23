/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chordscale;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JLabel;

/**
 *
 * @author Travis
 */
public class ChordFind {

    //ChordFindThread cft;
    ExecutorService ex = Executors.newCachedThreadPool();
    //ChordTab st = new ChordTab();
    Chord[] ch = new Chord[15];
    JLabel[] names = new JLabel[3];
    JLabel[] scores = new JLabel[3];


    //should take this array in in the constructor instead

    public ChordFind(Chord[] chtmp, JLabel l1, JLabel l2, JLabel l3, JLabel b1, JLabel b2, JLabel b3) {
        ch = chtmp;
//        cft.setChordSearch(new int[]{5,4,7,6,5,7});
//        ex.execute(cft);
        names[0] = l1;
        names[1] = l2;
        names[2] = l3;
        scores[0] = b1;
        scores[1] = b2;
        scores[2] = b3;
//        cft = new ChordFindThread(ch, names, scores);
    }

    public void findChord(int[] tmpCho) {
        ChordFindThread cft = new ChordFindThread(ch, names, scores);
        cft.setChordSearch(tmpCho);
        ex.execute(cft);
    }

//    public void endCFT() {
//        cft.isSearching = false;
//    }

    public static void main(String[] args) {
        //System.out.println("hello world");
    }
}
