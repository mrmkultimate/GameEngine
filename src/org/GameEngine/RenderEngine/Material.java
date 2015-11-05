package org.GameEngine.RenderEngine;

import java.util.List;

public class Material {

	private Texture texture;
	private Color color = new Color(1.0f,1.0f,1.0f,1.0f);
	
	
	private boolean textureUpdated = false;
	private boolean colorUpdated = false;
	
	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
		setTextureUpdated(true);
	}

	public boolean isTextureUpdated() {
		return textureUpdated;
	}

	public void setTextureUpdated(boolean textureUpdated) {
		this.textureUpdated = textureUpdated;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		colorUpdated = true;
	}

	public boolean isColorUpdated() {
		return colorUpdated;
	}

	public void setColorUpdated(boolean colorUpdated) {
		this.colorUpdated = colorUpdated;
	}




	
}
