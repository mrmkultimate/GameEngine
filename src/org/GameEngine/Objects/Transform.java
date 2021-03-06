package org.GameEngine.Objects;

import org.GameEngine.Math.*;

public class Transform extends Component{
	
	private Vector3f position = new Vector3f(0,0,0);
	private Vector3f scale = new Vector3f(1,1,1);
	private Rotation rotation = new Rotation();
	
	public Transform(){ 
		setName("Transform");
	}
	
	
	public void Translate(Vector3f direction){
		position.Add(direction);
	}
	

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	

	public Rotation getRotation() {
		return rotation;
	}

	public void setRotation(Rotation rotation) {
		this.rotation = rotation;
	}

	
	
}
