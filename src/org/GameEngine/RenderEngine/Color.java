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
	
	
}
