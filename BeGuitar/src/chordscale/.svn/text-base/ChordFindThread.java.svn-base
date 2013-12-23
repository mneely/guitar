package chordscale;

import javax.swing.JLabel;

public class ChordFindThread implements Runnable {

    volatile boolean isSearching = true;
    volatile int t = 0;
    int[] cSearch;
    public Chord[] ch;
    volatile int firstScore;
    volatile int tmpScore;
    String[] cName = new String[3];
    int[] cVar;
    int[] cScore;
    String[] cType;
    volatile int i = 0;
    JLabel[] names = new JLabel[3];
    JLabel[] scores = new JLabel[3];
    //this is going to need the labels for live updates.

    //should take in the Chordtab
    public ChordFindThread(Chord[] c, JLabel[] l1, JLabel[] b1) {
        ch = c;
        cSearch = new int[]{0, 0, 0, 0, 0, 0};
        cVar=new int[]{0,0,0};
        cScore = new int[]{0,0,0};
        cType = new String[]{"","",""};
        cName = new String[]{"","",""};
        names = l1;
        scores = b1;
    }

    public void setChordSearch(int[] cs) {
        cSearch = cs;
    }

    @Override
    public void run() {
        firstScore = 0;
        tmpScore = 0;
        i = 0;
        String tmp;
        int var;
        //System.out.println("counting");
        while (isSearching) {
            
            tmpScore = 0;

            if (ch[i].notes[1] == cSearch[0]) {
                tmpScore = tmpScore + 8;
            }
            if (ch[i].notes[2] == cSearch[1]) {
                tmpScore = tmpScore + 8;
            }
            if (ch[i].notes[3] == cSearch[2]) {
                tmpScore = tmpScore + 7;
            }
            if (ch[i].notes[4] == cSearch[3]) {
                tmpScore = tmpScore + 6;
            }
            if (ch[i].notes[5] == cSearch[4]) {
                tmpScore = tmpScore + 6;
            }
            if (ch[i].notes[6] == cSearch[5]) {
                tmpScore = tmpScore + 5;
            }
            firstScore = tmpScore;
            tmpScore = 0;
            if (ch[i].notes2[1] == cSearch[0]) {
                tmpScore = tmpScore + 8;
            }
            if (ch[i].notes2[2] == cSearch[1]) {
                tmpScore = tmpScore + 8;
            }
            if (ch[i].notes2[3] == cSearch[2]) {
                tmpScore = tmpScore + 7;
            }
            if (ch[i].notes2[4] == cSearch[3]) {
                tmpScore = tmpScore + 6;
            }
            if (ch[i].notes2[5] == cSearch[4]) {
                tmpScore = tmpScore + 6;
            }
            if (ch[i].notes2[6] == cSearch[5]) {
                tmpScore = tmpScore + 5;
            }
            if (firstScore > tmpScore) {
                tmpScore = firstScore;
                var = 1;
            } else {
                var = 2;
            }
            //System.out.println(tmpScore + " " + ch[i].name + " " + var);

            //score if this one is the greatest.
            if (tmpScore > cScore[0]) {
                cVar[2] = cVar[1];
                cVar[1] = cVar[0];
                cVar[0] = var;
                cScore[2] = cScore[1];
                cScore[1] = cScore[0];
                cScore[0] = tmpScore;
                cName[2] = cName[1];
                cName[1] = cName[0];
                cName[0] = ch[i].name;
                cType[2] = cType[1];
                cType[1] = cType[0];
                cType[0] = ch[i].type;
            } else if (tmpScore > cScore[1]) {//second highest score
                cVar[2] = cVar[1];
                cVar[1] = var;
                cScore[2] = cScore[1];
                cScore[1] = tmpScore;
                cName[2] = cName[1];
                cName[1] = ch[i].name;
                cType[2] = cType[1];
                cType[1] = ch[i].type;
            } else if (tmpScore > cScore[2]) {//third highest score
                cVar[2] = var;
                cScore[2] = tmpScore;
                cName[2] = ch[i].name;
                cType[2] = ch[i].type;
            } else {
                //FAIL
            }

            i++;
            if (i == 14) {
                //finish everything
//                    System.out.println("1: " + cName[0]  + " " + cType[0] + " Var " + cVar[0] + " at " + ((cScore[0]*100)/40) + "% and " + cScore[0]);
//                    System.out.println("2: " + cName[1]  + " " + cType[1]  + " Var " + cVar[1] + " at " + ((cScore[1]*100)/40) + "% and " + cScore[1]);
//                    System.out.println("3: " + cName[2]  + " " + cType[2]  + " Var " + cVar[2] + " at " + ((cScore[2]*100)/40) + "% and " + cScore[2]);

                    for (int i = 0; i < 3; i++) {
                        names[i].setText(cName[i]  + " " + cType[i] + " Variation " + cVar[i]);
                        scores[i].setText(((cScore[i]*100)/40) + "%");
                    }

                    break;
            }
        }
    }
}
