/*
 * File: Breakout.java
 * -------------------
 * This file will eventually implement the game of Breakout.
 * Author: NanduKalidindi
 * Date: 29th May 2012
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/* Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/* Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/* Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/* Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/* Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/* Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/* Separation between bricks */
	private static final int BRICK_SEP = 4;

/* Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/* Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/* Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/* Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/* Number of turns */
	private static final int NTURNS = 3;

	private static final double MIN_X_VELOCITY = 1.0;
	private static final double MAX_X_VELOCITY = 3.0;



/* Runs the Breakout program. */
	public void run() {
		setup();
		addMouseListeners();
		GLabel start = new GLabel("Click to Start!!");
		start.setFont("Times New Roman-36");
		add(start, WIDTH/2 - start.getWidth()/2, HEIGHT/2 - start.getHeight()/2);
		waitForClick();
		remove(start);
		vx = rgen.nextDouble(MIN_X_VELOCITY, MAX_X_VELOCITY);
		if(rgen.nextBoolean()) vx = -vx;
		while(true){
    		checkForCollision();
    		move();
		}
	}

	// Setting up the BRICKS, PADDLE and SPHERE

	//Setting BRICKS
	public void setup(){
		int BRICK_X_OFFSET = (APPLICATION_WIDTH/2) - 18 - (BRICK_WIDTH*5);
		for(int j=0; j<10; j++){
			for(int i=0; i<10; i++){
				g1 = new GRect(BRICK_X_OFFSET + (i*BRICK_WIDTH) +(i*4), BRICK_Y_OFFSET + (j*BRICK_HEIGHT) + (j*4), BRICK_WIDTH, BRICK_HEIGHT);
				g1.setFilled(true);
				if(j==0 || j==1){
					g1.setFillColor(Color.RED);
					add(g1);
				}
				if(j==2 || j==3){
					g1.setFillColor(Color.ORANGE);
					add(g1);
				}
				if(j==4 || j==5){
					g1.setFillColor(Color.YELLOW);
					add(g1);
				}
				if(j==6 || j==7){
					g1.setFillColor(Color.GREEN);
					add(g1);
				}
				if(j==8 || j==9){
					g1.setFillColor(Color.CYAN);
					add(g1);
				}
			}
		}
		//PADDLE
		p1 = new GRect(APPLICATION_WIDTH/2, APPLICATION_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
		p1.setFilled(true);
		p1.setFillColor(Color.BLACK);
		add(p1);
		//SPHERE
    	o1 = new GOval(APPLICATION_WIDTH/2 - BALL_RADIUS, APPLICATION_HEIGHT/2 - BALL_RADIUS ,2*BALL_RADIUS,2*BALL_RADIUS);
    	o1.setFilled(true);
    	add(o1);
	}

	//Collision Check Against Application EDGES, Against PADDLE and Against BRICKS
    public void checkForCollision(){
    /*	gObj1 = getElementAt(o1.getX(), o1.getY() + (2*BALL_RADIUS));
    	gObj2 = getElementAt(o1.getX() + (2*BALL_RADIUS), o1.getY());
    	gObj3 = getElementAt(o1.getX() + (2*BALL_RADIUS), o1.getY() + (2*BALL_RADIUS));*/
    	gObj1 = getCollidingObject();
    	if(gObj1 == p1 || o1.getY() == 0){
    		vy = -vy;
    	}
    	if(o1.getX() > WIDTH - 2*BALL_RADIUS|| o1.getX() < 0) vx = -vx;
    	//gObj = getElementAt(o1.getX(), o1.getY());
    	if(gObj1 != null && gObj1 != p1){
    		remove(gObj1);
    		count++;
    		vy = -vy;
    	}

    	if(o1.getY() > HEIGHT){
    		GLabel label= new GLabel("SCORE : " + count );
    		label.setFont("Times New Roman-36");
    		add(label, WIDTH/2 - label.getWidth()/2, HEIGHT/2 - label.getHeight()/2);
    	}
    }

    public GObject getCollidingObject(){
    	GObject gObj = null;
    	if(getElementAt(o1.getX(), o1.getY()) != null){
    		gObj = getElementAt(o1.getX(), o1.getY());
    	}
    	if(getElementAt(o1.getX(), o1.getY() + (2*BALL_RADIUS)) != null){
    		gObj = getElementAt(o1.getX(), o1.getY() + (2*BALL_RADIUS));
    	}
    	if(getElementAt(o1.getX() + (2*BALL_RADIUS), o1.getY()) != null){
    		gObj = getElementAt(o1.getX() + (2*BALL_RADIUS), o1.getY());
    	}
    	if(getElementAt(o1.getX() + (2*BALL_RADIUS), o1.getY() + (2*BALL_RADIUS)) != null){
    		gObj = getElementAt(o1.getX() + (2*BALL_RADIUS), o1.getY() + (2*BALL_RADIUS));
    	}
    	return gObj;
    }
    //SPHERE Movement
    public void move(){
    		o1.move(vx, vy);
    		pause(15);
    }

    //For PADDLE
    public void mouseEntered(MouseEvent e){
    	Last = new GPoint(e.getPoint());
    	temp = Last.getX();
    	    p1.setLocation(Last.getX() - PADDLE_WIDTH/2,APPLICATION_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
    }

    //Controlling PADDLE
    public void mouseMoved(MouseEvent e){
    	p1.move(e.getX() - Last.getX(),0);
    	Last = new GPoint(e.getPoint());

    }

    public void mouseExited(MouseEvent e){
    	if(e.getX() >= WIDTH){
    		p1.setLocation(WIDTH - PADDLE_WIDTH,APPLICATION_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
    	}
    	if(e.getX() <=0){
    		p1.setLocation(0,APPLICATION_HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
    	}
    }


    //Private Variables
    private GOval o1;
    private GRect p1, g1, g2;
    private GPoint Last;
    private RandomGenerator rgen = RandomGenerator.getInstance();
    private double vx, vy = 5;
    private double temp;
    private GObject gObj1, gObj2, gObj3;
    private int count = 0;
}
