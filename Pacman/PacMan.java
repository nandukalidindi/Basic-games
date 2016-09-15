/*
 * File: PacMan.java
 * -------------------

 * Author: NanduKalidindi
 * Date: 9th October 2012
 */

import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import acm.graphics.GArc;
import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.util.RandomGenerator;


public class PacMan extends GraphicsProgram{

	// CONSTANTS
	/* Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 800;
	public static final int APPLICATION_HEIGHT = 600;

/* Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;


     public void run(){
    	 createBoxes();
         setBackground(Color.black);
    	 addKeyListeners();
    	 Setup();
         createPellets();
    	/* GLabel label1 = new GLabel(Lines.get(0).toString(), 100, 100);
    	 label1.setColor(Color.white);
    	 add(label1);*/
         i = 0;
    	 while(true){
    	     move(xVel, yVel, Direction);
    		 checkForCollision();
    	 }
     }

     private void Setup(){

    	 //   PACMAN

    	 Pacman = new GArc(30, 30, 45, -270);
    	 Pacman.setFilled(true);
    	 Pacman.setFillColor(Color.white);
    	 add(Pacman, 100, 500);
    	 Monster = new ReadKeys(355, 280);
    	 Monster.setColor(Color.blue);
    	 Monsters.add(Monster);
    	 add(Monster);
    	 Monster = new ReadKeys(600, 280);
    	 Monster.setColor(Color.blue);
    	 Monsters.add(Monster);
    	 add(Monster);

    	 createT(340, 160);
    	 createT(340, 485);
    	 createT(340, 380);

    	 Box(270, 215, 70, 10);
    	 Box(460, 215, 70, 10);

    	 Box(270, 435, 70, 10);
    	 Box(460, 435, 70, 10);

    	 Box(100, 80, 50, 40);
    	 Box(240, 80, 100, 40);
    	 Box(460, 80, 100, 40);
    	 Box(640, 80, 50, 40);



     }

     //MONSTERS

 	/*private void createMonster(double X, double Y){
 		Head = new GArc(30, 30, 0, 180);
 		Head.setColor(Color.blue);
 		add(Head, X, Y);
 		createLine(X, Y + 15, X, Y + 30);
 		createLine(X, Y + 30, X + 7.5, Y + 19);
 		createLine(X + 7.5, Y + 19, X + 15, Y + 30);
 		createLine(X + 15, Y + 30, X + 22.5, Y + 19);
 		createLine(X + 22.5, Y + 19, X + 30, Y + 30);
 		createLine(X + 30, Y + 30, X + 30, Y + 15);

 		Eye = new GOval(X + 3, Y + 5, 6, 10);
 		Eye.setFilled(true);
 		Eye.setFillColor(Color.blue);
 		add(Eye);
 		Eye = new GOval(X + 18, Y + 5, 6, 10);
 		Eye.setFilled(true);
 		Eye.setFillColor(Color.blue);
 		add(Eye);
 	}
 	*/



	 //PACMAN DONE

     //PACMAN Movement

    	private void move(double xVel, double yVel, String Direction){
        	 Pacman.move(xVel, yVel);
        	 Monster.move(xVel1, yVel1);
        	 pause(9);
        	 if(Direction.equals("UP")){
        	 if(i != 45){
        	Pacman.setStartAngle(45 + i);
        	Pacman.setSweepAngle(-270 - 2*i);
        	i++;
        	 }
        	 else i = 0;
        	 }
        	 else if(Direction.equals("DOWN")){
        		 if(i != 45){
        		 Pacman.setStartAngle(-45 - i);
        		 Pacman.setSweepAngle(270 + 2*i);
        		 i++;
        		 }
        		 else i = 0;
        	 }
        	 else if(Direction.equals("RIGHT")){
        		 if(i != 45){
        			 Pacman.setStartAngle(45 - i);
        			 Pacman.setSweepAngle(270 + 2*i);
        			 i++;
        		 }
        		 else i = 0;
        	 }
        	 else if(Direction.equals("LEFT")){
        		 if(i != 45){
        			 Pacman.setStartAngle(135 + i);
        			 Pacman.setSweepAngle(-270 - 2*i);
        			 i++;
        		 }
        		 else i = 0;
        	 }
     }


    // PACMAN Movement DONE


     //COLLISION _-------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

     private void checkForCollision(){
    	 if(getElementAt(Pacman.getX() + 15, Pacman.getY() - 1) != null){
    		 Coll = getElementAt(Pacman.getX() + 15, Pacman.getY() - 1);
    		 Coll1 = getElementAt(Pacman.getX() + 31, Pacman.getY() + 15);
    		 Coll2 = getElementAt(Pacman.getX() - 1, Pacman.getY() + 15);
    		 Coll3 = getElementAt(Pacman.getX() + 15, Pacman.getY() + 31);
    		 CollLine = getElementAt(Pacman.getX() + 15, Pacman.getY());
    		 if(Lines.contains(Coll) == false && Monsters.contains(Coll) == false)
        		 remove(Coll);
    		 else if(Lines.contains(Coll) || Monsters.contains(Coll)){
    			 xVel = 0;
    			 yVel = 1;
    			 Direction = "DOWN";

    		 }
    		 if(Monsters.contains(Coll)){
    			 xVel = 0;
    			 yVel = 0;
    			 remove(Pacman);
    		 }
    	 }
    	 if(getElementAt(Pacman.getX() + 31, Pacman.getY() + 15) != null){
    		 Coll1 = getElementAt(Pacman.getX() + 31, Pacman.getY() + 15);
    		 if(Lines.contains(Coll1) == false)
        		 remove(Coll1);
    		 else{
    			 xVel = -1;
    			 yVel = 0;
    			 Direction = "LEFT";
    		 }
    	 }
    	 if(getElementAt(Pacman.getX() - 1, Pacman.getY() + 15) != null){
    		 Coll2 = getElementAt(Pacman.getX() - 1, Pacman.getY() + 15);
    		 if(Lines.contains(Coll2) == false)
        		 remove(Coll2);
    		 else{
    			 xVel = 1;
    			 yVel = 0;
    			 Direction = "RIGHT";
    		 }
    	 }
    	 if(getElementAt(Pacman.getX() + 15, Pacman.getY() + 31) != null || getElementAt(Monster.getX() + 15, Monster.getY() + 15) != null){
    		 Coll3 = getElementAt(Pacman.getX() + 15, Pacman.getY() + 31);
    		 Coll31 = getElementAt(Monster.getX() + 15, Monster.getY() + 31);
    		 if(Lines.contains(Coll3) == false || Lines.contains(Coll31) == false)
        		 remove(Coll3);
    		 else{
    			 xVel = 0;
    			 yVel = -1;
    			 xVel1 = rgen.nextDouble(-1, 1);
    			 yVel1 = rgen.nextDouble(-1, 1);
    			 Direction = "UP";
    		 }
    	 }
     }


     // COLLISION DONE!!------------------------------------------>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

     private GObject CollidingObject(){
    	 if(getElementAt(Pacman.getX() + 15, Pacman.getY() - 1) != null){
    		 Coll = getElementAt(Pacman.getX() + 15, Pacman.getY() - 1);
    		 return Coll;
    	 }
    	 if(getElementAt(Pacman.getX() + 31, Pacman.getY() + 15) != null){
    		 Coll1 = getElementAt(Pacman.getX() + 31, Pacman.getY() + 15);
    		 return Coll1;
    	 }
    	 if(getElementAt(Pacman.getX() - 1, Pacman.getY() + 15) != null){
    		 Coll2 = getElementAt(Pacman.getX() - 1, Pacman.getY() + 15);
    		 return Coll2;
    	 }
    	 if(getElementAt(Pacman.getX() + 15, Pacman.getY() + 31) != null){
    		 Coll3 = getElementAt(Pacman.getX() + 15, Pacman.getY() + 31);
    		 return Coll3;
    	 }
    	 return null;
     }

     private void createPellets(){
 		for(int i=0; i<10; i++){
 			Pellets = new GOval(110, 50 + (30*i), 10, 10);
 			Pellets.setFilled(true);
 			Pellets.setFillColor(Color.yellow);
 			add(Pellets);
 		}
 	}

     public void keyPressed(KeyEvent e){
    	 if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W){
    		 xVel = 0;
    		 yVel = -1;
    		 Direction = "UP";
    	 }
    	 else if(e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S){
    		 xVel = 0;
    		 yVel = 1;
    		 Direction = "DOWN";
    	 }
    	 else if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D){
    		 xVel = 1;
    		 yVel = 0;
    		 Direction = "RIGHT";
    	 }
    	 else if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A){
    		 xVel = -1;
    		 yVel = 0;
    		 Direction = "LEFT";
    	 }
     }

     private void createT(double X, double Y){

    	 Box(X, Y, 120, 15);
    	 Box(X+95/2, Y+15, 25, 45);
 	}


 	private void createLine(double Xi, double Yi, double Xf, double Yf){
 		lines = new GLine(Xi, Yi, Xf, Yf);
 		Lines.add(lines);
 		lines.setColor(Color.blue);
 		add(lines);
 	}

 	private void Box(double X, double Y, double Height, double Width){
 		Box = new GRect(X, Y, Height, Width);
 		Box.setFilled(true);
 		Box.setColor(Color.blue);
 		Box.setFillColor(Color.blue);
 		Lines.add(Box);
 		add(Box);
 	}

 	private void createBoxes(){
 		 //OUTER BOX
 		createLine(50, 280, 50, 30);
 		createLine(50, 30, 750, 30);
 		createLine(750, 30, 750, 280);
 		createLine(750, 320, 750, 590);
 		createLine(750, 590, 50, 590);
 		createLine(50, 590, 50, 320);
 		//OUTER BOX DONE

 		//INNER BOX
 		createLine(55, 280, 55, 35);
 		createLine(55, 35, 745, 35);
 		createLine(745, 35, 745, 280);
 		createLine(745, 320, 745, 585);
 		createLine(745, 585, 55, 585);
 		createLine(55, 585, 55, 320);
 		//INNER BOX DONE

 		//MIDDLE DABBA ---------------->>>>>>>>>>>>>>>>>>>>>>>>>
 		createLine(350, 275, 350, 325);
 		createLine(350, 325, 450, 325);
 		createLine(450, 325, 450, 275);
 		createLine(450, 275, 350, 275);

 		//OUTER DABBA

 		createLine(345, 270, 345, 330);
 		createLine(345, 330, 455, 330);
 		createLine(455, 330, 455, 270);
 		createLine(455, 270, 345, 270);
 		//MIDDLE DABBA DONE ----------->>>>>>>>>>>>>>>>>>>>>>>>>
 	}


 	private RandomGenerator rgen = new RandomGenerator();
 	 private GLine lines;
 	 private ReadKeys Monster;
 	 private GRect Box;
   	private GArc Head;
 	private GOval Eye;
     private GArc arcs;
     private ArrayList<GObject> Lines = new ArrayList<GObject>();
     private ArrayList<GObject> Monsters = new ArrayList<GObject>();
     private GArc Pacman;
     private GObject Coll, CollLine, Coll1, Coll2, Coll3, Coll31;
     private int i = 0;
     private String Direction = "UP";
     private GOval Pellets;
     private double xVel = 0, yVel = -1, xVel1 = 0, yVel1 = 1;
}
