package chordscale;



/*
 * Written by Travis Nutting
 * CSC 420
 * Homework 2
 */

import javax.swing.*;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import csc420project.fretpanel;

public class ScaleTab extends JPanel {

    //Color cr = Color.BLUE;
    //JSlider v = new JSlider();
    //JSlider h = new JSlider();
    //int draw = 1;
    JList cName = new JList();
    JList cType = new JList();
    //JList cVar = new JList();
    //JButton jb = new JButton();
    JScrollPane chordNamePane = new JScrollPane();
    JScrollPane chordTypePane = new JScrollPane();
    //JScrollPane chordVarPane = new JScrollPane();
    JScrollPane infoPane = new JScrollPane();
    JTextArea info = new JTextArea();
    public Scale[] sc = new Scale[10];
    DefaultListModel nameModel = new DefaultListModel();
    DefaultListModel typeModel = new DefaultListModel();
    //DefaultListModel varModel = new DefaultListModel();
    int firstlist = 1;
    fretpanel fre;

    public ScaleTab(fretpanel f) {
        //add a bunch of chords
        fre = f;
        //add C maj chord
        int[] var = {0, 0, 3, 2, 0, 1, 0};
        int[] var2 = {0, 0, 3, 5, 5, 5, 3};
        //ch[0] = new Chord("C", "maj", var, var2);
        sc[0] = new Scale("C", "Maj", new int[]{0, 1, 3, 5, 0, 2, 3, 5, 0, 2, 3, 5, 0, 2, 4, 5, 0, 1, 3, 5, 0, 1, 3, 5},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});
        sc[1] = new Scale("C", "min", new int[]{1, 3, 4, 7, 2, 3, 5, 6, 0, 1, 3, 5, 0, 1, 4, 5, 0, 1, 3, 4, 1, 3, 4, 7},
                new int[]{6, 6, 6, 6, 5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3, 2, 2, 2, 2, 1, 1, 1, 1});
        //add C min chord
        //ch[1] = new Chord("C", "min", new int[]{0, 0, 3, 1, 0, 1, 3}, new int[]{0, 0, 3, 5, 5, 4, 3});

        //add A maj chord
        //ch[2] = new Chord("A", "maj", new int[]{0, 0, 0, 2, 2, 2, 0}, new int[]{0, 5, 7, 7, 6, 5, 5});

        //add A min chord
        //ch[3] = new Chord("A", "min", new int[]{0, 0, 0, 2, 2, 1, 0}, new int[]{0, 5, 7, 7, 5, 5, 5});


        //setBorder(BorderFactory.createLineBorder(Color.black));
        //v = ver;
        //h = Hor;
        //cName.setSize(20, 50);
        //this.setLayout(new FlowLayout().);
        //cName.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));//little border on the JList
        //DefaultListModel nameModel = new DefaultListModel();
        //nameModel.addElement("Jane Doe");
        //nameModel.addElement("John Smith");
        //nameModel.addElement("Kathy Green");
        for (int i = 0; sc[i] != null; i++) {
            //nameModel.addElement(ch[i].name);
            //Check to see if it already is on there. if not, proceed.
            if (nameModel.contains(sc[i].name)) {
            } else {
                nameModel.addElement(sc[i].name);
            }
        }

        cName.setModel(nameModel);


        /*cName.setModel(new javax.swing.AbstractListModel() {//setting up the JList: is going to need to find all the chords

        String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6"};//all the chords added, eliminate matches

        public int getSize() {
        return strings.length;
        }

        public Object getElementAt(int i) {
        return strings[i];
        }
        });*/
        chordNamePane.setViewportView(cName);//set the JList on the JPane
        //chordNamePane.setPreferredSize(new Dimension (60, 10));
        //Box.Filler outside = new Box.Filler(new Dimension(0,0), new Dimension(10,10), new Dimension(200,200));
        chordNamePane.setPreferredSize(new Dimension(70, 100));//set size
        this.add(chordNamePane);//add it to the panel
        cName.setSelectedIndex(0);




        /*
         *
         *
         *
         *
         * *****************************************************************
         *
         *
         */





        cName.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                //System.out.println("sup");
                //System.out.println("1");
                firstlist = 2;
//                typeModel.removeAllElements();
//                for (int t = 0; ch[t] != null; t++) {
//                    //nameModel.addElement(ch[i].name);
//                    //Check to see if it already is on there. if not, proceed.
//                    //System.out.println("we got here");
//                    //typeMo
//                    if (cName.getSelectedIndex() >= 0) {
//                        if (ch[t].name.equals(cName.getModel().getElementAt(cName.getSelectedIndex()).toString())) {
//                            typeModel.addElement(ch[t].type);
//                            System.out.println("here");
//                        } else {
//                        }
//                    }
//                }

                //cType.setModel(typeModel);
                cType.setSelectedIndex(0);
                //cVar.setSelectedIndex(0);
                firstlist = 1;
                changeChord();
//                if (jB.getText().equals("Show")) {
//                    String tmp = col.getSelectedItem().toString();
//                    gPanel.cr = Color.getColor(tmp);
//                    if (tmp.equals("RED")) {
//                        gPanel.cr = Color.RED;
//                    } else if (tmp.equals("BLUE")) {
//                        gPanel.cr = Color.BLUE;
//                    } else if (tmp.equals("GREEN")) {
//                        gPanel.cr = Color.GREEN;
//                    } else if (tmp.equals("YELLOW")) {
//                        gPanel.cr = Color.YELLOW;
//                    } else {
//                        gPanel.cr = Color.ORANGE;
//                    }
//                    gPanel.draw = 0;
//                    jB.setText("Hide");
//                } else {
//                    gPanel.draw = 1;
//                    jB.setText("Show");
//                }
//                gPanel.repaint();
            }
        });





        /*
         *
         *
         *
         *
         * *****************************************************************
         *
         *
         */





        //cType.setListData(ch);

        //cType.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        //cName.setSelectedIndex(0);
        //DefaultListModel typeModel = new DefaultListModel();
        //typeModel.addElement("Jane Doe");
        //typeModel.addElement("John Smith");
        //typeModel.addElement("Kathy Green");
        //String tmp = nameModel.getElementAt(cName.getSelectedIndex()).toString();
        //System.out.println("here");
//        int j;
//        String r;
        cType.setModel(typeModel);

        cType.setSelectedIndex(0);
        for (int t = 0; sc[t] != null; t++) {
            //nameModel.addElement(ch[i].name);
            //Check to see if it already is on there. if not, proceed.
            //System.out.println("we got here");

            if (cName.getSelectedIndex() >= 0) {
                if (sc[t].name.equals(cName.getModel().getElementAt(cName.getSelectedIndex()).toString())) {
                    //System.out.println("help");
                    typeModel.addElement(sc[t].type);
                } else {
                }
            }
        }


        cType.setModel(typeModel);

        cType.setSelectedIndex(0);


//        cType.setModel(typeModel);
//
//        cType.setSelectedIndex(0);
        //cType.setSelectedIndex(0);
        //cType.getSelectedIndex();



        cType.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                //System.out.println("2");
                if (firstlist != 2) {
                    changeChord();
                }
                //firstlist = 1;
                //System.out.println("sup");
//                for (int t = 0; ch[t] != null; t++) {
                //nameModel.addElement(ch[i].name);
                //Check to see if it already is on there. if not, proceed.
                //System.out.println("we got here");
                //typeMo
//                    if (cName.getSelectedIndex() >= 0) {
//                        if (ch[t].name.equals(cName.getModel().getElementAt(cName.getSelectedIndex()).toString())) {
//                            typeModel.addElement(ch[t].type);
//                        } else {
//                        }
//                    }
//                }

//                cType.setModel(typeModel);
//                cType.setSelectedIndex(0);
//                cVar.setSelectedIndex(0);
//                if (jB.getText().equals("Show")) {
//                    String tmp = col.getSelectedItem().toString();
//                    gPanel.cr = Color.getColor(tmp);
//                    if (tmp.equals("RED")) {
//                        gPanel.cr = Color.RED;
//                    } else if (tmp.equals("BLUE")) {
//                        gPanel.cr = Color.BLUE;
//                    } else if (tmp.equals("GREEN")) {
//                        gPanel.cr = Color.GREEN;
//                    } else if (tmp.equals("YELLOW")) {
//                        gPanel.cr = Color.YELLOW;
//                    } else {
//                        gPanel.cr = Color.ORANGE;
//                    }
//                    gPanel.draw = 0;
//                    jB.setText("Hide");
//                } else {
//                    gPanel.draw = 1;
//                    jB.setText("Show");
//                }
//                gPanel.repaint();
            }
        });










        /*cType.setModel(new javax.swing.AbstractListModel() {

        String[] strings = {"Items 1", "Items 2", "Item 3", "Item 4", "Item 5", "Item 6"};//chord types. if cName = C, display all C chord types

        public int getSize() {
        return strings.length;
        }

        public void setStrings(String[] str) {
        strings = str;
        }

        public Object getElementAt(int i) {
        return strings[i];
        }
        });*/
        //setStrings(new String[]{)
        //cType.setSize(20, 50);
        chordTypePane.setViewportView(cType);
        //chordNamePane.setPreferredSize(new Dimension (60, 10));
        //Box.Filler outside = new Box.Filler(new Dimension(0,0), new Dimension(10,10), new Dimension(200,200));
        chordTypePane.setPreferredSize(new Dimension(70, 100));//size of chord type pane
        this.add(chordTypePane);//adding it




        /*
         *
         *
         *
         *
         * *****************************************************************
         *
         *
         */



        //varModel.addElement("Variation 1");
        // varModel.addElement("Variation 2");

        //cVar.setModel(varModel);
        //cVar.setSelectedIndex(0);
        //cType.getSelectedIndex();


        /*cType.setModel(new javax.swing.AbstractListModel() {

        String[] strings = {"Items 1", "Items 2", "Item 3", "Item 4", "Item 5", "Item 6"};//chord types. if cName = C, display all C chord types

        public int getSize() {
        return strings.length;
        }

        public void setStrings(String[] str) {
        strings = str;
        }

        public Object getElementAt(int i) {
        return strings[i];
        }
        });*/
        //setStrings(new String[]{)
        //cType.setSize(20, 50);




//        cVar.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
//
//            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
//                //System.out.println("3");
//                if (firstlist != 2) {
//                    secondValueChanged(evt);
//                }
//                //firstlist = 1;
//                //changeChord();
//                //System.out.println("sup");
////                for (int t = 0; ch[t] != null; t++) {
//                //nameModel.addElement(ch[i].name);
//                //Check to see if it already is on there. if not, proceed.
//                //System.out.println("we got here");
//                //typeMo
////                    if (cName.getSelectedIndex() >= 0) {
////                        if (ch[t].name.equals(cName.getModel().getElementAt(cName.getSelectedIndex()).toString())) {
////                            typeModel.addElement(ch[t].type);
////                        } else {
////                        }
////                    }
////                }
//
////                cType.setModel(typeModel);
////                cType.setSelectedIndex(0);
////                cVar.setSelectedIndex(0);
////                if (jB.getText().equals("Show")) {
////                    String tmp = col.getSelectedItem().toString();
////                    gPanel.cr = Color.getColor(tmp);
////                    if (tmp.equals("RED")) {
////                        gPanel.cr = Color.RED;
////                    } else if (tmp.equals("BLUE")) {
////                        gPanel.cr = Color.BLUE;
////                    } else if (tmp.equals("GREEN")) {
////                        gPanel.cr = Color.GREEN;
////                    } else if (tmp.equals("YELLOW")) {
////                        gPanel.cr = Color.YELLOW;
////                    } else {
////                        gPanel.cr = Color.ORANGE;
////                    }
////                    gPanel.draw = 0;
////                    jB.setText("Hide");
////                } else {
////                    gPanel.draw = 1;
////                    jB.setText("Show");
////                }
////                gPanel.repaint();
//            }
//        });






        //chordVarPane.setViewportView(cVar);
        //chordNamePane.setPreferredSize(new Dimension (60, 10));
        //Box.Filler outside = new Box.Filler(new Dimension(0,0), new Dimension(10,10), new Dimension(200,200));
        //chordVarPane.setPreferredSize(new Dimension(70, 100));//size of chord type pane
        //this.add(chordVarPane);//adding it













        info.setPreferredSize(new Dimension(200, 100));
        info.setText("Scale: C Major\n\nInfo\n");
        infoPane.setViewportView(info);
        info.setEditable(false);
        this.add(infoPane);




        //cName.setVisible(true);
        //this.add(jb);
    }

    private void secondValueChanged(javax.swing.event.ListSelectionEvent evt) {
        // TODO add your handling code here:
        changeChord();
    }

    public void setInfo(String s) {
        //would set the info
    }

    public void changeChord() {
        //System.out.println("CHANGING CHORD");
        String tmpName = cName.getModel().getElementAt(cName.getSelectedIndex()).toString();
        String tmpType = cType.getModel().getElementAt(cType.getSelectedIndex()).toString();
        //String tmpVar = cVar.getModel().getElementAt(cVar.getSelectedIndex()).toString();
        for (int t = 0; sc[t] != null; t++) {
            //nameModel.addElement(ch[i].name);
            //Check to see if it already is on there. if not, proceed.
            //System.out.println("we got here");
            //typeMo
            if (cName.getSelectedIndex() >= 0) {
                if (sc[t].name.equals(tmpName)) {
                    if (sc[t].type.equals(tmpType)) {
                        //System.out.println("SCALE FOUND");
                        //call marcus' method, send array
                        //marcus.setScale(sc[t].string, sc[t].notes);
                          //  System.out.println("changed chord");
                        //fre.setScale(sc[t].notes, sc[t].string);

                        //call Richard's method, send note name
                        if (tmpType.equalsIgnoreCase("min")) {
                            tmpName = tmpName + "m";
                            //richard.setChord(tmpName);//string of the chord type C if major Cm if minor
                        }
                    }
                    //typeModel.addElement(ch[t].type);
                } else {
                }
            }
        }


    }

    public Dimension getPreferredSize() {
        return new Dimension(620, 250);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
//        if (draw == 0) {
//            g.setColor(Color.WHITE);
//            g.fillRect(0,0,250,200);
//            g.setColor(cr);
//            g.fillOval((240 * h.getValue()) / 100, 190 - ((190 * v.getValue()) / 100), 15, 15);
//        } else {
//            g.setColor(Color.WHITE);
//            g.fillRect(0,0,250,200);
//        }
    }
}
