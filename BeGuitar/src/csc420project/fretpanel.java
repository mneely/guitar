/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package csc420project;

/**
 *
 * @author Travis
 */

import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;

public class fretpanel extends JPanel {

//	File f = new File("c:\images\myimage.gif");
//	BufferedImage bufferedImage = ImageIO.read(f);

    private int circleX = 30;
    private int circleY = 30;
    private int circleH = 0;
    private int circleW = 0;
    int paint = 0;
    //int placements= getLength(fretPlace);
    private BufferedImage fretBoard;


    //Don't Touch these!!
    final int[] fret = { 0, 57, 140, 225, 310, 395, 480, 565, 650, 735};
    final int[] string = {0, 4, 25, 49, 72,95, 117};

    //Finger Placements
    int[] fretPlace;
    int[] stringNum;
    //int[] fretPlace = { 0, 0, 3, 2, 0, 1, 0};
    //int[] stringNum = { 0, 6, 5, 4, 3, 2, 1};
    //int placements = 2; //**** Not Positive that this returns length of the array****




    public fretpanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));

        try {
        	fretBoard = ImageIO.read(new File("guitarneck.jpg"));
         } catch (IOException ex) {
              // handle exception...
         }



        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
             repaint();

            }
        });



        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {

            }
        });

    }


    private void addCircle( int x, int y){

           // g.drawOval(x, y, 15,15);



      //  }
    }





    private int getXPos(){
    	return circleX;
    }
    private int getYPos(){
    	return circleY;
    }


    public Dimension getPreferredSize() {
        return new Dimension(880, 137);
    }

    public void paintComponent(Graphics g) {


    	super.paintComponent(g);
        int i;
      	g.drawImage(fretBoard, 0, 0 ,null);

        g.setColor(Color.BLUE);
        //g.fillOval(895, 5, 20, 20);


        for(i = 1; i <= fretPlace.length-1; i++){

    	if(fretPlace[i] == 0)
    	{
    		g.drawOval( fret[fretPlace[i]] , string[stringNum[i]] ,15,15);

    	}else
    	{
        g.fillOval( fret[fretPlace[i]] , string[stringNum[i]] ,15,15);
    	}


    	}
       }

       public void setChord(int[] x, int[] y){

           //you dont need to iterate through an array to make a matching array
           //helpful tip. this was causing bugs for me

//    	   int i=x.length-1;
//    	   int j;

           fretPlace = x;
           stringNum = y;
//    	   for(j = 1; j<=i; j++)
//    	   {
//    		   fretPlace[j] = x[j];
//    		   stringNum[j] = y[j];
//    	   }
    	   repaint();

       }

       public void setScale(int[] x, int[] y){

    	   int i=x.length-1;
    	   int j;


    	   for(j = 0; j<=i; j++)
    	   {
    		   fretPlace[j] = x[j];
    		   stringNum[j] = y[j];
    	   }
    	   repaint();

       }



    }
