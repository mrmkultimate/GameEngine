package org.GameEngine.Objects;

import org.GameEngine.Math.Matrix4f;
import org.GameEngine.Math.Quaternion;
import org.GameEngine.Math.Vector3f;

public class Rotation {
	private Matrix4f rotationMatrix = new Matrix4f();
	private Quaternion quaternion = new Quaternion(1,0,0,0);
	private Vector3f eulerAngles = new Vector3f();
	
	
	
	
	public Matrix4f getRotationMatrix() {
		rotationMatrix = quaternion.ToRotationMatrix();
		return rotationMatrix;
	}
	public void setRotationMatrix(Matrix4f rotationMatrix) {
		this.rotationMatrix = rotationMatrix;
		this.quaternion = rotationMatrix.ToQuaternion();
	}
	public Quaternion getQuaternion() {
		return quaternion;
	}
	public void setQuaternion(Quaternion quaternion) {
		this.quaternion = quaternion;
	}
	public Vector3f getEulerAngles() {
		eulerAngles = quaternion.ToEulerAngles();
		return eulerAngles;
	}
	public void setEulerAngles(Vector3f eulerAngles) {
		this.eulerAngles = eulerAngles;
		this.quaternion = EulerToQuaternion(eulerAngles);
	}
	
	public static Quaternion EulerToQuaternion(Vector3f euler){
		Quaternion result = new Quaternion();
		result.w = (float) (Math.cos(euler.x/2)*Math.cos(euler.y/2)*Math.cos(euler.z/2) + Math.sin(euler.x/2)*Math.sin(euler.y/2)*Math.sin(euler.z/2));
		result.x = (float) (Math.sin(euler.x/2)*Math.cos(euler.y/2)*Math.cos(euler.z/2) - Math.cos(euler.x/2)*Math.sin(euler.y/2)*Math.sin(euler.z/2));
		result.y = (float) (Math.cos(euler.x/2)*Math.sin(euler.y/2)*Math.cos(euler.z/2) + Math.sin(euler.x/2)*Math.cos(euler.y/2)*Math.sin(euler.z/2));
		result.z = (float) (Math.cos(euler.x/2)*Math.cos(euler.y/2)*Math.sin(euler.z/2) - Math.sin(euler.x/2)*Math.sin(euler.y/2)*Math.cos(euler.z/2));
		
		return result;
	}
	
	
}
