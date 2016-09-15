/*
 * File: PacManGrid.java
 * -------------------
 * Author: NanduKalidindi
 * Date: 8th October 2012
 */
import acm.graphics.GCanvas;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class PacManGrid extends GCompound{

	public PacManGrid(){

       createBoxes();

	}

	private void createT(double X, double Y){
		lines = new GLine(X, Y, X + 100, Y);
		lines.setColor(Color.blue);
		add(lines);

		lines = new GLine(X, Y + 15, X + 85/2 - 5, Y + 15);
		lines.setColor(Color.blue);
		add(lines);

		lines = new GLine(X + (85/2) + 20, Y + 15, X + 100, Y + 15);
		lines.setColor(Color.blue);
		add(lines);

		lines = new GLine(X + 85/2 - 5, Y + 15, X + 85/2 - 5, Y + 50);
		lines.setColor(Color.blue);
		add(lines);

		lines = new GLine(X + 85/2 + 20, Y + 15, X + 85/2 + 20, Y + 50);
		lines.setColor(Color.blue);
		add(lines);

		arcs = new GArc(15, 15, 90, 180);
		arcs.setColor(Color.blue);
		add(arcs, X - 7.5, Y);

		arcs = new GArc(15, 15, 90, -180);
		arcs.setColor(Color.blue);
		add(arcs, X - 7.5 + 100, Y);

		arcs = new GArc(25, 25, 180, 180);
		arcs.setColor(Color.blue);
		add(arcs, X + 85/2 -5, Y + 50 - 25/2);
	}


	private void createLine(double Xi, double Yi, double Xf, double Yf){
		lines = new GLine(Xi, Yi, Xf, Yf);
		lines.setColor(Color.blue);
		add(lines);
	}

	private void createBoxes(){
		 //OUTER BOX
		createLine(50, 280, 50, 30);
		createLine(50, 30, 750, 30);
		createLine(750, 30, 750, 280);
		createLine(750, 320, 750, 570);
		createLine(750, 570, 50, 570);
		createLine(50, 570, 50, 320);
		//OUTER BOX DONE

		//INNER BOX
		createLine(55, 280, 55, 35);
		createLine(55, 35, 745, 35);
		createLine(745, 35, 745, 280);
		createLine(745, 320, 745, 565);
		createLine(745, 565, 55, 565);
		createLine(55, 565, 55, 320);
		//INNER BOX DONE
	}



	private GLine lines;
    private GArc arcs;

}
