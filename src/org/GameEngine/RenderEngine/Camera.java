package org.GameEngine.RenderEngine;

import org.GameEngine.Objects.Component;

public class Camera extends Component {

	private float FOV = 1;
	private float aspectRatio = 1;
	private float nearPlane = 0.01f;
	private float farPlane = 1000;
	
	public Camera(){ 
		setName("Camera");
	}

	public float getNearPlane() {
		return nearPlane;
	}

	public void setNearPlane(float nearPlane) {
		this.nearPlane = nearPlane;
	}

	public float getFarPlane() {
		return farPlane;
	}

	public void setFarPlane(float farPlane) {
		this.farPlane = farPlane;
	}

	public float getAspectRatio() {
		return aspectRatio;
	}

	public void setAspectRatio(float aspectRatio) {
		this.aspectRatio = aspectRatio;
	}

	public float getFOV() {
		return FOV;
	}

	public void setFOV(float fOV) {
		FOV = fOV;
	}
	
}
