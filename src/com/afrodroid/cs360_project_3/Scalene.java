// File: Scalene.java
// CS 360 - Fall 2012 - Watts
// Project 2
// October 2012 
// Originally created by Dr. Watts
// http://watts.cs.sonoma.edu
/*
This file contains the description and implementation
of a class called Scalene. 
*/

package com.afrodroid.cs360_project_3;

import static java.lang.Math.*;

public final class Scalene extends Triangle
{
	private double side2;
	private double side3;

	public Scalene ()
	{
		side2 = 30;
		side3 = 40;
	}

	public Scalene (Scalene R)
	{
		side = R.side;
		side2 = R.side2;
		side3 = R.side3;
		centerX = R.centerX;
		centerY = R.centerY;
		color = R.color;
		angle = R.angle;
		for (int i = 0; i < 3; i++)
		{
			vertexX[i] = R.vertexX[i];
			vertexY[i] = R.vertexY[i];
		}
	}

	public Scalene (double S1, double S2, double S3, int X, int Y, int C, double A)
	{
		side = S1;
		side2 = S2;
		side3 = S3;
		centerX = X;
		centerY = Y;
		color = C;
		angle = A;
		setVertices ();
	}

	public void setVertices ()
	{
		double cosAngle = (side * side + side2 * side2 - side3 * side3) / (2.0 * side * side2);
		double angle = acos (cosAngle);
		int height = (int) (sin(angle) * side2);
		int offX = (int) (cos(angle) * side2);
		vertexX[0] = vertexY[0] = 0;
		vertexX[1] = offX; vertexY[1] = -height;
		vertexX[2] = (int) side; vertexY[2] = 0;
		int inX = (int) ((vertexX[0]* side3 + vertexX[1] * side + vertexX[2] * side2) / perimeter());
		int inY = (int) ((vertexY[0]* side3 + vertexY[1] * side + vertexY[2] * side2) / perimeter());
		for (int i = 0; i < 3; i++)
		{
			vertexX[i] += (centerX - inX);
			vertexY[i] += (centerY - inY);
		}
		rotate ();
	}

	public void resize (double delta) {
		double incr = delta / perimeter();
		side = getSide1() + ( incr * getSide1() );
		side2 = getSide2() + ( incr * getSide2() );
		side3 = getSide3() + ( incr * getSide3() ) ;
		setVertices();
	}

	public void downsize (double delta) {
		double incr = delta / perimeter();
		side = getSide1() - ( incr * getSide1() );
		side2 = getSide2() - ( incr * getSide2() );
		side3 = getSide3() - ( incr * getSide3() ) ;
		setVertices();
	}

	public void setSide1 (double S1)
	{
		side = S1;
	}

	public double getSide1 ()
	{
		return side;
	}

	public void setSide2 (double S2)
	{
		side2 = S2;
	}

	public double getSide2 ()
	{
		return side2;
	}

	public void setSide3 (double S3)
	{
		side3 = S3;
		setVertices ();
	}

	public double getSide3 ()
	{
		return side3;
	}

	public double perimeter ()
	{
		return side + side2 + side3;
	}

	public double area ()
	{
		double h = (side + side2 + side3) / 2.0;
		return sqrt (h * (h-side) * (h-side2) * (h-side3));
	}
	
    public boolean contains( int x, int y )
    {
        boolean c = false;
        int i, j = 0;
        for (i = 0, j = vertexX.length - 1; i < vertexX.length; j = i++) {
            if (((vertexY[i] > y) != (vertexY[j] > y))
                && (x < (vertexX[j] - vertexX[i]) * (y - vertexY[i]) / (vertexY[j] - vertexY[i]) + vertexX[i]))
            c = !c;
        }
        return c;
    } 

	public String getName ()
	{
		return "Scalene";
	}


}	
