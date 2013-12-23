package chordscale;

/*
 * Written by Travis Nutting
 * CSC 420
 * Homework 2
 */
import javax.swing.*;
import trackplayer.Total;
import csc420project.fretpanel;
import java.awt.Dimension;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//import csc420project.dontfret;

public class ChordTab extends JPanel {

    //Color cr = Color.BLUE;
    //JSlider v = new JSlider();
    //JSlider h = new JSlider();
    //int draw = 1;
    JList csName = new JList();
    JList cName = new JList();
    JList cType = new JList();
    JList cVar = new JList();
    //JButton jb = new JButton();
    JScrollPane chordCSPane = new JScrollPane();//new chord/scale pane
    JScrollPane chordNamePane = new JScrollPane();
    JScrollPane chordTypePane = new JScrollPane();
    JScrollPane chordVarPane = new JScrollPane();
    //JScrollPane infoPane = new JScrollPane();
    //JTextArea info = new JTextArea();
    public Chord[] ch = new Chord[15];
    public Scale[] sc = new Scale[15];
    boolean isChord = true;
    DefaultListModel csModel = new DefaultListModel();//new chord/scale model
    DefaultListModel nameModel = new DefaultListModel();
    DefaultListModel typeModel = new DefaultListModel();
    DefaultListModel varModel = new DefaultListModel();
    int firstlist = 1;
    Total player;
    fretpanel fre;
    int HEIGHT = 120;
    //ChordFindThread cft = new ChordFindThread();
    ExecutorService ex = Executors.newCachedThreadPool();

    public ChordTab(Total tot, fretpanel fp) {
        //add a bunch of chords
        //add C maj chord
        fre = fp;
        player = tot;
        player.setChord("A1");
        int[] var = {0, 0, 3, 2, 0, 1, 0};
        int[] var2 = {0, 0, 3, 5, 5, 5, 3};
        //add A maj chord
        ch[0] = new Chord("A", "Major", new int[]{0, 0, 0, 2, 2, 2, 0}, new int[]{0, 5, 7, 7, 6, 5, 5});

        //add A min chord
        ch[1] = new Chord("A", "minor", new int[]{0, 0, 0, 2, 2, 1, 0}, new int[]{0, 5, 7, 7, 5, 5, 5});

        ch[4] = new Chord("C", "Major", var, var2);

        //add C min chord
        ch[5] = new Chord("C", "minor", new int[]{0, 0, 3, 1, 0, 1, 3}, new int[]{0, 0, 3, 5, 5, 4, 3});

        //add B maj chord
        ch[2] = new Chord("B", "Major", new int[]{0, 0, 2, 4, 4, 4, 2}, new int[]{0, 7, 9, 9, 8, 7, 7});

        //add B min chord
        ch[3] = new Chord("B", "minor", new int[]{0, 0, 2, 4, 4, 3, 2}, new int[]{0, 7, 9, 9, 7, 7, 7});

        //add D Chords
        ch[6] = new Chord("D", "Major", new int[]{0, 0, 0, 0, 2, 3, 2}, new int[]{0, 0, 5, 7, 7, 7, 5});

        ch[7] = new Chord("D", "minor", new int[]{0, 0, 0, 0, 2, 3, 1}, new int[]{0, 0, 5, 7, 7, 6, 5});

        //add E Chords
        ch[8] = new Chord("E", "Major", new int[]{0, 0, 2, 2, 1, 0, 0}, new int[]{0, 0, 7, 9, 9, 9, 7});

        ch[9] = new Chord("E", "minor", new int[]{0, 0, 2, 2, 0, 0, 0}, new int[]{0, 0, 7, 9, 9, 8, 7});

        //add F Chords
        ch[10] = new Chord("F", "Major", new int[]{0, 1, 3, 3, 2, 1, 1}, new int[]{0, 0, 0, 3, 5, 6, 5});

        ch[11] = new Chord("F", "minor", new int[]{0, 1, 3, 3, 1, 1, 1}, new int[]{0, 0, 0, 3, 5, 6, 4});

        //add G maj Chord
        ch[12] = new Chord("G", "Major", new int[]{0, 3, 2, 0, 0, 3, 3}, new int[]{0, 3, 5, 5, 4, 3, 3});

        //add G min chord
        ch[13] = new Chord("G", "minor", new int[]{0, 3, 5, 5, 3, 3, 3}, new int[]{0, 0, 0, 5, 7, 8, 6});

        //add A scales
        sc[0] = new Scale("A", "Major", new int[]{0, 2, 4, 5, 0, 2, 4, 5, 0, 2, 4, 6, 0, 2, 4, 6, 1, 2, 4, 6, 0, 2, 3, 5},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});
        sc[1] = new Scale("A", "minor", new int[]{0, 1, 4, 5, 0, 2, 3, 5, 0, 2, 3, 6, 1, 2, 4, 5, 0, 1, 3, 5, 0, 1, 4, 5},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});

        //add B scaled
        sc[2] = new Scale("B", "Major", new int[]{0, 2, 4, 6, 1, 2, 4, 6, 1, 2, 4, 6, 1, 3, 4, 6, 1, 2, 4, 5, 0, 2, 4, 6},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});
        sc[3] = new Scale("B", "minor", new int[]{0, 2, 3, 6, 1, 2, 4, 5, 0, 2, 4, 5, 0, 3, 4, 6, 0, 2, 3, 5, 0, 2, 3, 6},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});

        //add C scales
        sc[4] = new Scale("C", "Major", new int[]{0, 1, 3, 5, 0, 2, 3, 5, 0, 2, 3, 5, 0, 2, 4, 5, 0, 1, 3, 5, 0, 1, 3, 5},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});
        sc[5] = new Scale("C", "minor", new int[]{1, 3, 4, 7, 2, 3, 5, 6, 0, 1, 3, 5, 0, 1, 4, 5, 0, 1, 3, 4, 1, 3, 4, 7},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});

        //add D scales
        sc[6] = new Scale("D", "Major", new int[]{0, 2, 3, 5, 0, 2, 4, 5, 0, 2, 4, 5, 0, 2, 4, 6, 0, 2, 3, 5, 0, 2, 3, 5},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});
        sc[7] = new Scale("D", "minor", new int[]{0, 1, 3, 5, 0, 1, 4, 5, 0, 2, 3, 5, 0, 2, 3, 6, 2, 3, 5, 6, 0, 1, 3, 5},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});

        //add E scaled
        sc[8] = new Scale("E", "Major", new int[]{0, 2, 4, 5, 0, 2, 4, 6, 1, 3, 4, 6, 1, 3, 4, 6, 0, 2, 4, 5, 0, 2, 4, 5},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});
        sc[9] = new Scale("E", "minor", new int[]{0, 2, 3, 5, 0, 2, 3, 6, 1, 2, 4, 5, 0, 2, 4, 5, 0, 1, 4, 5, 0, 2, 3, 5},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});

        //add F scales
        sc[10] = new Scale("F", "Major", new int[]{0, 1, 3, 5, 0, 1, 3, 5, 0, 2, 3, 5, 0, 2, 3, 5, 1, 3, 5, 6, 0, 1, 3, 5},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});
        sc[11] = new Scale("F", "minor", new int[]{0, 1, 3, 4, 1, 3, 4, 7, 2, 3, 5, 6, 0, 1, 3, 5, 1, 2, 5, 6, 0, 1, 3, 4},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});

        //add G scaled
        sc[12] = new Scale("G", "Major", new int[]{0, 2, 3, 5, 0, 2, 3, 5, 0, 2, 4, 5, 0, 2, 4, 5, 0, 1, 3, 5, 0, 2, 3, 5},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});
        sc[13] = new Scale("G", "minor", new int[]{2, 3, 5, 6, 0, 1, 3, 5, 0, 1, 4, 5, 0, 2, 3, 5, 1, 3, 4, 7, 2, 3, 5, 6},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});

        changeDisplay("A1");
        //player.disableImage();
        /**************************************
         * Chord/Scale scroll pane
         **************************************/
        csModel.addElement("Chord");
        csModel.addElement("Scale");
        csName.setModel(csModel);
        csName.setToolTipText("Select to display chord or scale");
        chordCSPane.setViewportView(csName);
        chordCSPane.setPreferredSize(new Dimension(70, HEIGHT));
        this.add(chordCSPane);
        csName.setSelectedIndex(0);

        csName.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    cType.setSelectedIndex(0);
                    cVar.setSelectedIndex(0);
                    cName.setSelectedIndex(0);
                    if (csName.getModel().getElementAt(csName.getSelectedIndex()).toString().equals("Chord")) {
                        isChord = true;
                        cName.setToolTipText("Select Chord to Display");
                        cType.setToolTipText("Select Chord Note");
                        cVar.setToolTipText("Select Chord Variation");
                        player.enableImage();
                        cVar.setEnabled(true);
                        changeChord();
                    } else {
                        isChord = false;
                        cName.setToolTipText("Select Scale to Display");
                        cType.setToolTipText("Select Scale Note");
                        cVar.setToolTipText("This is not an option for Scales");
                        player.disableImage();
                        cVar.setEnabled(false);
                        changeScale();
                    }

                }
            }
        });


        /**************************************
         * Letter that the chord is scroll pane
         **************************************/
        //populate the jlist
        for (int i = 0; ch[i] != null; i++) {
            if (nameModel.contains(ch[i].name)) {
            } else {
                nameModel.addElement(ch[i].name);
            }
        }

        cName.setModel(nameModel);
        cName.setToolTipText("Select Chord");
        chordNamePane.setViewportView(cName);//set the JList on the JPane
        chordNamePane.setPreferredSize(new Dimension(70, HEIGHT));//set size
        this.add(chordNamePane);//add it to the panel
        cName.setSelectedIndex(0);

        cName.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    firstlist = 2;
                    cType.setSelectedIndex(0);
                    cVar.setSelectedIndex(0);
                    firstlist = 1;
                    if (isChord) {
                        changeChord();
                    } else {
                        changeScale();
                    }
                }
            }
        });

        /**************************************
         * Major or Minor notes list
         **************************************/
        cType.setModel(typeModel);
        cType.setSelectedIndex(0);

        //add the types here
        typeModel.addElement("Major");
        typeModel.addElement("minor");

        cType.setModel(typeModel);
        cType.setToolTipText("Select Chord Note");
        cType.setSelectedIndex(0);

        cType.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    if (isChord) {
                        changeChord();
                    } else {
                        changeScale();
                    }
                }
            }
        });


        chordTypePane.setViewportView(cType);
        chordTypePane.setPreferredSize(new Dimension(70, HEIGHT));//size of chord type pane
        this.add(chordTypePane);//adding it

        /**************************************
         * Variation of chords list
         **************************************/
        varModel.addElement("Variation 1");
        varModel.addElement("Variation 2");

        cVar.setModel(varModel);
        cVar.setToolTipText("Select Chord Variation");
        cVar.setSelectedIndex(0);

        cVar.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    if (isChord) {
                        changeChord();
                    } else {
                        changeScale();
                    }
                }
            }
        });

        chordVarPane.setViewportView(cVar);
        chordVarPane.setPreferredSize(new Dimension(77, HEIGHT));//size of chord type pane
        this.add(chordVarPane);//adding it


    }

    public void changeScale() {
        // fre.setChord(sc[1].notes, sc[1].string);
        String tmpName = cName.getModel().getElementAt(cName.getSelectedIndex()).toString();
        String tmpType = cType.getModel().getElementAt(cType.getSelectedIndex()).toString();
        //String tmpVar = cVar.getModel().getElementAt(cVar.getSelectedIndex()).toString();
        //int tmpr = cVar.getSelectedIndex();
        for (int t = 0; ch[t] != null; t++) {
            if (cName.getSelectedIndex() >= 0) {
                if (sc[t].name.equals(tmpName)) {
                    if (sc[t].type.equals(tmpType)) {
                        fre.setChord(sc[t].notes, sc[t].string);
                        //player.disableImage();
                    }
                } else {
                }
            }
        }
    }

    public void changeChord() {
        String tmpName = cName.getModel().getElementAt(cName.getSelectedIndex()).toString();
        String tmpType = cType.getModel().getElementAt(cType.getSelectedIndex()).toString();
        String tmpVar = cVar.getModel().getElementAt(cVar.getSelectedIndex()).toString();
        int tmpr = cVar.getSelectedIndex();
        for (int t = 0; ch[t] != null; t++) {
            if (cName.getSelectedIndex() >= 0) {
                if (ch[t].name.equals(tmpName)) {
                    if (ch[t].type.equals(tmpType)) {
                        if (tmpr == 0) {
                            fre.setChord(ch[t].notes, new int[]{0, 6, 5, 4, 3, 2, 1});
                        } else {
                            fre.setChord(ch[t].notes2, new int[]{0, 6, 5, 4, 3, 2, 1});
                        }
                        if (tmpType.equalsIgnoreCase("minor")) {
                            tmpName = tmpName + "m";
                        }
                        if (tmpVar.contains("1")) {
                            tmpName = tmpName + "1";
                        } else {
                            tmpName = tmpName + "2";
                        }
                        player.setChord(tmpName);
                    }
                } else {
                }
            }
        }
    }

    public void changeDisplay(String s) {
        String str = s;
        String tmpName = "";
        String tmpType = "";
        String tmpVar = "";
        int var;
        int tmpr;
        if (str.length() == 3) {
            tmpName = Character.toString(str.charAt(0));
            tmpType = "minor";
            tmpVar = Character.toString(str.charAt(2));
            var = Integer.parseInt(tmpVar);
            tmpr = var - 1;
        } else {
            tmpName = Character.toString(str.charAt(0));
            tmpType = "Major";
            tmpVar = Character.toString(str.charAt(1));
            var = Integer.parseInt(tmpVar);
            tmpr = var - 1;
        }
        for (int t = 0; ch[t] != null; t++) {
            if (ch[t].name.equals(tmpName)) {
                if (ch[t].type.equals(tmpType)) {
                    if (tmpr == 0) {
                        fre.setChord(ch[t].notes, new int[]{0, 6, 5, 4, 3, 2, 1});
                    } else {
                        fre.setChord(ch[t].notes2, new int[]{0, 6, 5, 4, 3, 2, 1});
                    }
                    if (tmpType.equalsIgnoreCase("minor")) {
                        tmpName = tmpName + "m";
                    }
                    if (tmpVar.contains("1")) {
                        tmpName = tmpName + "1";
                    } else {
                        tmpName = tmpName + "2";
                    }
                }
            } else {
            }
        }
        //csName
        csName.setSelectedIndex(0);
        //cName
        if (str.contains("A")) {
            cName.setSelectedIndex(0);
        } else if (str.contains("B")) {
            cName.setSelectedIndex(1);
        } else if (str.contains("C")) {
            cName.setSelectedIndex(2);
        } else if (str.contains("D")) {
            cName.setSelectedIndex(3);
        } else if (str.contains("E")) {
            cName.setSelectedIndex(4);
        } else if (str.contains("F")) {
            cName.setSelectedIndex(5);
        } else if (str.contains("G")) {
            cName.setSelectedIndex(6);
        }
        //cType
        if (str.contains("m")) {
            cType.setSelectedIndex(1);
        } else {
            cType.setSelectedIndex(0);
        }
        //cVar
        if (str.contains("1")) {
            cVar.setSelectedIndex(0);
        } else {
            cVar.setSelectedIndex(1);
        }

    }

    public void changeChord(int[] tmp) {
        fre.setChord(tmp, new int[]{0, 6, 5, 4, 3, 2, 1});
    }

    public void changeToCurrent() {
        if (isChord) {
            changeChord();
        } else {
            changeScale();
        }
    }

    //Call this to shade everything out
    //call it again to unshade
    public void toggleShade() {
        if (cName.isEnabled()) {
            csName.setEnabled(false);
            cName.setEnabled(false);
            cType.setEnabled(false);
            cVar.setEnabled(false);
        } else {
            csName.setEnabled(true);
            cName.setEnabled(true);
            cType.setEnabled(true);
            if (isChord) {
                cVar.setEnabled(true);
            }
        }
    }
}
