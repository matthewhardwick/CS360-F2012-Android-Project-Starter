// File: Triangle.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains the description and implementation
of a parent class called Triangle. 
*/

package com.afrodroid.cs360_project_3;

import static java.lang.Math.*;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

public class Triangle extends Shape
{
	protected int [] vertexX = new int [3];
	protected int [] vertexY = new int [3];
	protected double angle = 0.0;

	Triangle ()
	{
	}

	public void setCenterX (int X)
	{
		centerX = X;
		setVertices ();
	}

	public void setCenterY (int Y)
	{
		centerY = Y;
		setVertices ();
	}

	public String getName ()
	{
		return "Triangle";
	}

	public void resize(double delta)
	{
	}

	public void downsize(double delta)
	{
	}
	
    public boolean contains( int x, int y )
    {
    	return false;
    }

	public void paintComponent (Canvas canvas)
	{
		
		Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2);
        paint.setColor(color);
        
        Path path = new Path();
        path.moveTo(vertexX[0], vertexY[0]);
        path.lineTo(vertexX[1], vertexY[1]);
        path.lineTo(vertexX[2], vertexY[2]);
        path.close();
        
        canvas.drawPath(path, paint);
        
	}

	protected void setVertices ()
	{
	}

	public void setAngle(double a)
	{
		angle = a;
	}

	public double getAngle ()
	{
		return angle;
	}

	public void move (int deltaX, int deltaY)
	{
		centerX += deltaX;
		centerY += deltaY;
		setVertices ();
	}

	protected void rotate ()
	{
		double A = Math.toRadians (angle);
		for (int i = 0; i < 3; i++)
		{
			double dx = vertexX[i] - centerX;
			double dy = centerY - vertexY[i];
			double r = sqrt (dx*dx + dy*dy);
			double t = atan2 (dy, dx);
			t += A;
			vertexX[i] = centerX + (int) (r * cos(t));
			vertexY[i] = centerY - (int) (r * sin(t));
		}
	}
}
