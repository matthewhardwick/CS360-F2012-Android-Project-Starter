// File: jShape.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains the description and implementation
of a virtual class called jShape. 
*/

package com.afrodroid.cs360_project_3;

import android.graphics.Canvas;

public class Shape implements Comparable<Shape>
{
	public enum ShapeType {CIRCLE, SQUARE, RECTANGLE, EQUILATERAL, RIGHT, SCALENE, HOURGLASS, STOPSIGN, PLUSSIGN};
	protected int centerX;
	protected int centerY;
	protected double side;
	protected int color;
	protected int selectedColor;
	protected boolean delete;

	public Shape ()
	{
		side = 20;
		delete = false;
	}

	public void markDelete()
	{
		delete = true;
	}

	public boolean markedToDelete()
	{
		if(delete==true)
		{
			return true;
		}
		else
		{
			return false;
		}
	}


	public void setCenterX (int X)
	{
		centerX = X;
	}

	public void setCenterY (int Y)
	{
		centerY = Y;
	}

	public int getCenterX ()
	{
		return centerX;
	}

	public int getCenterY ()
	{
		return centerY;
	}

	public void setColor (int C)
	{
		color = C;
	}
	public int getColor ()
	{
		return color;
	}
	public void modifyShape (Canvas frame, int x, int y)
	{
	}

	public double area ()
	{
		return 0;
	}

	public void resize(double delta)
	{
	}

	public void downsize(double delta)
	{
	}

	public double perimeter ()
	{
		return 0;
	}

	public void setAngle(double a)
	{
	}

	public double getAngle ()
	{
		return 0.0;
	}

	protected void setVertices()
	{
	}
	
    public boolean contains( int x, int y )
    {
    	return false;
    }
	
	public void paintComponent(Canvas canvas)
	{
	}


	public int compareTo (Shape S)
	{
		double a1 = area (), a2 = S.area ();
		double p1 = perimeter (), p2 = S.perimeter ();
		if (a1 < a2) return -1;
		if (a1 > a2) return 1;
		if (p1 < p2) return -2;
		if (p1 > p2) return 2;
		return 0;
	}

	public String getName ()
	{
		return "Shape";
	}

	public boolean isIn (int X, int Y)
	{
		return false;
	}

	public void move (int deltaX, int deltaY)
	{
		centerX += deltaX;
		centerY += deltaY;
	}
}
