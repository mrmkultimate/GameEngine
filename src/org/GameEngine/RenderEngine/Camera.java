package org.GameEngine.RenderEngine;

import org.GameEngine.Objects.Component;

public class Camera extends Component {

	private float FOV;
	private float nearPlane;
	private float farPlane;
	
	public Camera(){ 
		setName("Camera");
	}

	public float getFOV() {
		return FOV;
	}

	public void setFOV(float fOV) {
		FOV = fOV;
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
	
}
