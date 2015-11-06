package org.GameEngine.Math;

public class Quaternion {

	public float x;
	public float y;
	public float z;
	public float w;
	
	
	public Quaternion(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
	}
	
	public Quaternion(float X, float Y, float Z, float W){
		this.x = X;
		this.y = Y;
		this.z = Z;
		this.w = W;
	}

	
	public boolean isEqual(Quaternion  v) {
		return x == v.x && y == v.y && z == v.z && w == v.w;
	}
	
	public void setToEqual(Quaternion  v){
		x = v.x;
		y = v.y;		
		z = v.z;
		w = v.w;
	}
	
	
	public Quaternion Multiply(Quaternion r){
		float w_ = w*r.w - x*r.x - y*r.y - z*r.z;
		float x_ = x*r.w + w*r.x + y*r.z - z*r.y;
		float y_ = y*r.w + w*r.y + z*r.x - x*r.z;
		float z_ = z*r.w + w*r.z + x*r.y - y*r.x;
		
		return new Quaternion(x_,y_,z_,w_);
	}
	
	public Quaternion Multiply(Vector3f r){
		float w_ = -x*r.x - y*r.y - z*r.z;
		float x_ = w*r.x + y*r.z - z*r.y;
		float y_ = w*r.y + z*r.x - x*r.z;
		float z_ = w*r.z + x*r.y - y*r.x;
		
		return new Quaternion(x_,y_,z_,w_);
	}
	
	
	public Quaternion Divide(float scalar)
	{
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
	
		x /= scalar;
		y /= scalar;
		z /= scalar;
		z /= scalar;
		
		return this;
	}
	
	public static Quaternion Divide(Quaternion v, float scalar){
		
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
		
		Quaternion v2 = new Quaternion(0,0,0,0);
		v2.x = v.x/scalar;
		v2.y = v.y/scalar;
		v2.z = v.z/scalar;
		v2.w = v.w/scalar;
		
		return v2;
	}

		
	public float Magnitude()
	{
		return (float)Math.sqrt(x*x + y*y + z*z + w*w);
	}
	
	public float MagnitudeSquared()
	{
		return x*x + y*y + z*z + w*w;
	}
	
	
	public Quaternion Conjugate(){
		return new Quaternion(-x,-y,-z,-w);
	}
	
	public static Quaternion Conjugate(Quaternion quat){
		return quat.Conjugate();
	}
	
	
	
	public Quaternion normalize()
	{
		float currentMagnitude =  Magnitude();
		
		if (currentMagnitude == 0)
		{
			currentMagnitude = 0.000001f;
			//throw new RuntimeException("Divide by 0 prohibited")
		}
		
		this.Divide(currentMagnitude);
	
		return this;
	}
	
	//TODO: QuatToRot for both matrix and yaw pitch roll
	
	//TODO: RotToQuat for both matrix and yaw pitch roll
	
	
	
}
