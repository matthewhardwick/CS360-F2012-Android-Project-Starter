package com.afrodroid.cs360_project_3;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ScaleneDialog extends Dialog {

	private Button createButton;
	private EditText side1_editText;
	private EditText side2_editText;
	private EditText side3_editText;
	private EditText rotation_editText;
	private RadioGroup color_radioGroup;
	private RadioButton red_radioButton;
	private RadioButton green_radioButton;
	private RadioButton blue_radioButton;
	private int centerX;
	private int centerY;
	private int color;
	private double side1;
	private double side2;
	private double side3;
	private double rotation;
	private Scalene scalene = null;
	@SuppressWarnings("unused")
	private ShapeControl shapeControl;
	private ShapeView shapeView;

	public ScaleneDialog(Context context, View view, int x, int y) {
		super(context);
		shapeControl = (ShapeControl) context;
		shapeView = (ShapeView) view;
		centerX = x;
		centerY = y;
		color = Color.BLUE;
	}

	public ScaleneDialog(Context context, View view, Shape s) {
		super(context);
		shapeControl = (ShapeControl) context;
		shapeView = (ShapeView) view;
		scalene = (Scalene) s;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle("Scalene");
		setContentView(R.layout.scalenedialog);
		createButton = (Button) findViewById(R.id.create_shape);
		side1_editText = (EditText) findViewById(R.id.side1_editText);
		side2_editText = (EditText) findViewById(R.id.side2_editText);
		side3_editText = (EditText) findViewById(R.id.side3_editText);
		rotation_editText = (EditText) findViewById(R.id.rotation_editText);
		red_radioButton = (RadioButton) findViewById(R.id.red);
		green_radioButton = (RadioButton) findViewById(R.id.green);
		blue_radioButton = (RadioButton) findViewById(R.id.blue);
		
		if (scalene != null) {
			side1_editText.setText(Double.toString(scalene.getSide1()));
			side2_editText.setText(Double.toString(scalene.getSide2()));
			side3_editText.setText(Double.toString(scalene.getSide3()));
			rotation_editText.setText(Double.toString(scalene.getAngle()));
			color = scalene.getColor();
			
			switch (color)
			{
			case Color.RED:
				red_radioButton.setChecked(true);
				break;
			case Color.GREEN:
				green_radioButton.setChecked(true);
				break;
			case Color.BLUE:
				blue_radioButton.setChecked(true);
				break;
			}
		}

		createButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				populateValues();
				dismiss();
			}
		});

	}

	private void populateValues() {
		side1 = Double.valueOf(side1_editText.getText().toString());
		side2 = Double.valueOf(side2_editText.getText().toString());
		side3 = Double.valueOf(side3_editText.getText().toString());
		rotation = Double.valueOf(rotation_editText.getText().toString());
		
		color_radioGroup = (RadioGroup) findViewById(R.id.color_radiogroup);
		
		switch (color_radioGroup.getCheckedRadioButtonId())
		{
		case R.id.red:
			color = Color.RED;
			break;
		case R.id.green:
			color = Color.GREEN;
			break;
		case R.id.blue:
			color = Color.BLUE;
			break;
		}

		if (scalene == null) {
			shapeView.addShape(new Scalene(side1, side2, side3, centerX,
					centerY, color, rotation));
		} else {
			scalene.setSide1(side1);
			scalene.setSide2(side2);
			scalene.setSide3(side3);
			scalene.setAngle(rotation);
			scalene.setColor(color);
			scalene.setVertices();
			shapeView.invalidate();
		}
	}

}
