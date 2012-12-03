package com.afrodroid.cs360_project_3;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class ShapeControl extends Activity implements View.OnLongClickListener,
		View.OnTouchListener {

	private ShapeView shapeView;
	private float endx = 0;
	private float endy = 0;
	private float starty = 0;
	private Scalene shapeSelected = null;
	private MenuItem addShapeMenuItem;
	private MenuItem dragShapeMenuItem;
	private MenuItem rotateShapeMenuItem;
	private MenuItem scaleShapeMenuItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		shapeView = new ShapeView(this);
		setContentView(shapeView);
		shapeView.setOnLongClickListener(this);
		shapeView.setOnTouchListener(this);
		shapeView.requestFocus();

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Add/Modify Shape");
		addShapeMenuItem = menu.getItem(0);
		addShapeMenuItem.setCheckable(true);
		addShapeMenuItem.setChecked(true);
		menu.add("Drag Shape");
		dragShapeMenuItem = menu.getItem(1);
		dragShapeMenuItem.setCheckable(true);
		menu.add("Rotate Shape");
		rotateShapeMenuItem = menu.getItem(2);
		rotateShapeMenuItem.setCheckable(true);
		menu.add("Scale Shape");
		scaleShapeMenuItem = menu.getItem(3);
		scaleShapeMenuItem.setCheckable(true);
		
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	 public boolean onMenuItemSelected(int featureId, MenuItem item) {
		String itemTitle = item.getTitle().toString();
		if (itemTitle == "Add/Modify Shape" && !addShapeMenuItem.isChecked()){
			  addShapeMenuItem.setChecked(true);
			  dragShapeMenuItem.setChecked(false);
			  rotateShapeMenuItem.setChecked(false);
			  scaleShapeMenuItem.setChecked(false);
			  return true;
		} else if (itemTitle == "Drag Shape" && !dragShapeMenuItem.isChecked()) {
			  addShapeMenuItem.setChecked(false);
			  dragShapeMenuItem.setChecked(true);
			  rotateShapeMenuItem.setChecked(false);
			  scaleShapeMenuItem.setChecked(false);
			  return true;
		} else if (itemTitle == "Rotate Shape" && !rotateShapeMenuItem.isChecked()) {
			  addShapeMenuItem.setChecked(false);
			  dragShapeMenuItem.setChecked(false);
			  rotateShapeMenuItem.setChecked(true);
			  scaleShapeMenuItem.setChecked(false);
			  return true;
		} else if (itemTitle == "Scale Shape" && !scaleShapeMenuItem.isChecked()) {
			  addShapeMenuItem.setChecked(false);
			  dragShapeMenuItem.setChecked(false);
			  rotateShapeMenuItem.setChecked(false);
			  scaleShapeMenuItem.setChecked(true);
			  return true;
		}
	  return super.onMenuItemSelected(featureId, item);
	 }

	@Override
	public boolean onLongClick(View v) {
		if (shapeSelected == null) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(R.string.pick_shape);
			builder.setItems(R.array.pick_shape_array,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {

							Dialog sd = new ScaleneDialog(ShapeControl.this,
									shapeView, (int) endx, (int) endy);
							sd.show();

						}
					});
			builder.create().show();
			return true;
		} 
		return false;

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		endx = event.getX();
		endy = event.getY();
		boolean retVal = false;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			
			shapeSelected = (Scalene) shapeView.findShape((int) endx, (int) endy);
			starty = endy;

			if (shapeSelected != null && addShapeMenuItem.isChecked())
			{ 
				Dialog sd = new ScaleneDialog(ShapeControl.this, shapeView, (Scalene) shapeSelected);
				sd.show();
				retVal = true;
			}
			return retVal;
		case MotionEvent.ACTION_MOVE:
			if (shapeSelected != null && dragShapeMenuItem.isChecked())
				shapeView.drag((int) endx, (int) endy);
			else if (shapeSelected != null && rotateShapeMenuItem.isChecked())
				shapeView.rotate((int)  (endy - starty));
			else if (shapeSelected != null && scaleShapeMenuItem.isChecked())
				shapeView.resize((int) (endy - starty));
			return retVal;
		case MotionEvent.ACTION_UP:
			if (shapeSelected != null) {
				shapeSelected = null;
			}
			return retVal;
		default:
			return retVal;

		}

	}

}
