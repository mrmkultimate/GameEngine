package org.GameEngine.RenderEngine;

import java.util.List;

public class Material {

	private Texture texture;
	private Color ambient = new Color(0.4f,0.4f,0.4f,0);
	private Color diffuse = new Color(0.7f,0.7f,0.7f,0);
	private Color specular = new Color(0,0,0,0);
	private float shininess = 0;
	
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

	public boolean isColorUpdated() {
		return colorUpdated;
	}

	public void setColorUpdated(boolean colorUpdated) {
		this.colorUpdated = colorUpdated;
	}

	public Color getAmbient() {
		return ambient;
	}

	public void setAmbient(Color ambient) {
		colorUpdated = true;
		this.ambient = ambient;
	}

	public Color getDiffuse() {
		return diffuse;
	}

	public void setDiffuse(Color diffuse) {
		colorUpdated = true;
		this.diffuse = diffuse;
	}

	public Color getSpecular() {
		return specular;
	}

	public void setSpecular(Color specular) {
		colorUpdated = true;
		this.specular = specular;
	}

	public float getShininess() {
		return shininess;
	}

	public void setShininess(float shininess) {
		this.shininess = shininess;
	}

}
