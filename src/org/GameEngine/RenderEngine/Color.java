package org.GameEngine.RenderEngine;

public class Color {
	
	public float red;
	public float green;
	public float blue;
	public float alpha;
	
	public Color(){
		red = 1;
		green = 1;
		blue = 1;
		alpha = 1;
	}
	public Color(float r, float g, float b, float a){
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}
	
	public void setColor(float r, float g, float b, float a){
		red = r;
		green = g;
		blue = b;
		alpha = a;
	}
	public void setColor(Color color){
		red = color.red;
		green = color.green;
		blue = color.blue;
		alpha = color.alpha;
	}
	public static Color Multiply(Color lhc, Color rhc){
		Color result = new Color(1,1,1,1);
		result.red = lhc.red*rhc.red;
		result.green = lhc.green*rhc.green;
		result.blue = lhc.blue*rhc.blue;
		result.alpha = lhc.alpha*rhc.alpha;
		return result;
	}
	
}
