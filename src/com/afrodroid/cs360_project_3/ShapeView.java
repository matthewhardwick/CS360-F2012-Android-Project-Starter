package com.afrodroid.cs360_project_3;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

public class ShapeView extends View {

	@SuppressWarnings("unused")
	private ShapeControl shapecontrol;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	private Shape selected = null;

	public ShapeView(Context context) {
		super(context);
		this.shapecontrol = (ShapeControl) context;
		setFocusable(true);
		setFocusableInTouchMode(true);

	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < shapes.size(); ++i)
			shapes.get(i).paintComponent(canvas);
	}
	
	public void addShape(Shape s) {
		shapes.add(s);
		invalidate();
	}
	
	public Shape findShape(int x, int y) {
		for (int i = 0; i < shapes.size(); ++i) {
			if (shapes.get(i).contains(x, y)) {
				selected = shapes.get(i);
				return selected;
			}
		}
		return null;
	}
	
	public void deselectShape() {
		selected = null;
	}
	
	public void drag(int x, int y) {
		if (selected != null) {
			selected.setCenterX(x);
			selected.setCenterY(y);
			invalidate();
		}
	}
	
	public void rotate(int y) {
		if (selected != null) {
			selected.setAngle(y);
			selected.setVertices();
			invalidate();
		}
	}
	
	public void resize(int y) {
		if (selected != null) {
			selected.resize(y);
			selected.setVertices();
			invalidate();
		}
	}

}
