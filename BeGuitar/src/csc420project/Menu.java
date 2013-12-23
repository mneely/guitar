package csc420project;



import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import trackplayer.Total;

/* MenuLookDemo.java requires images/middle.gif. */

/*
 * This class exists solely to show you what menus look like.
 * It has no menu-related event handling.
 */
public class Menu extends JMenuBar {
    JTextArea output;
    JScrollPane scrollPane;

    public Menu(final Total a){
        
        JMenu menu;
        JMenuItem menuItem;


    


        //---------------------------
        //  File Menu w/ Items
        // -New
        // -Save
        // -Load
        // -Exit
        //---------------------------
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
      
        this.add(menu);

        //----------
        //NEW
        //----------
        menuItem = new JMenuItem("New", KeyEvent.VK_N);
               menuItem.setAccelerator(KeyStroke.getKeyStroke(
               KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                a.newTrack();
            }
        });
        menu.add(menuItem);


        //-------------
        //SAVE
        //-------------
        menuItem = new JMenuItem("Save",
                                 KeyEvent.VK_S);
        
       menuItem.setAccelerator(KeyStroke.getKeyStroke(
               KeyEvent.VK_S, ActionEvent.ALT_MASK));

        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                a.save("butter");
            }
        });

       menu.add(menuItem);



       //Load

       menuItem = new JMenuItem("Load",
                                 KeyEvent.VK_L);

       menuItem.setAccelerator(KeyStroke.getKeyStroke(
               KeyEvent.VK_L, ActionEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                a.load("butter");
            }
        });

               menu.add(menuItem);


       //Exit

       menuItem = new JMenuItem("Exit",
                                 KeyEvent.VK_X);
 
       menuItem.setAccelerator(KeyStroke.getKeyStroke(
               KeyEvent.VK_X, ActionEvent.ALT_MASK));

        menu.add(menuItem);



 

        //Help Menu
        
        menu = new JMenu("Help");
        menu.setMnemonic(KeyEvent.VK_H);

        this.add(menu);

        menuItem = new JMenuItem("Help", KeyEvent.VK_H);
        menu.add(menuItem);


    }


}