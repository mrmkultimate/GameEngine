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
		w /= scalar;
		
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

	//assume the quaternion isn't normalized
	public Matrix4f ToRotationMatrix() {
		// TODO: create Quat to Rot matrix
		
		float sqx = this.x*this.x;
		float sqy = this.y*this.y;
		float sqz = this.z*this.z;
		float sqw = this.w*this.w;
		

		Matrix4f matrix = new Matrix4f();
		
	    // invs (inverse square length) is only required if quaternion is not already normalised
		float invs = 1 / (sqx + sqy + sqz + sqw);
				
				
		matrix.set(0,0,( sqx - sqy - sqz + sqw)*invs); // since sqw + sqx + sqy + sqz =1/invs*invs
		matrix.set(1,1,(-sqx + sqy - sqz + sqw)*invs);
		matrix.set(2,2,(-sqx - sqy + sqz + sqw)*invs);
	    
	    float tmp1 = this.x*this.y;
	    float tmp2 = this.z*this.w;
	    matrix.set(1,0,(2.0f*(tmp1 + tmp2)*invs));
	    matrix.set(0,1,(2.0f*(tmp1 - tmp2)*invs));
	    
	    tmp1 = this.x*this.z;
	    tmp2 = this.y*this.w;
	    matrix.set(2,0,(2.0f*(tmp1 - tmp2)*invs));
	    matrix.set(0,2,(2.0f*(tmp1 + tmp2)*invs));
	    tmp1 = this.y*this.z;
	    tmp2 = this.x*this.w;
	    matrix.set(2,1,(2.0f*(tmp1 + tmp2)*invs));
	    matrix.set(1,2,(2.0f*(tmp1 - tmp2)*invs)); 
		
	    matrix.set(0, 3, 0);
	    matrix.set(1,3,0);
	    matrix.set(2,3,0);
	    matrix.set(3,3,1);
	    matrix.set(3,2,0);
	    matrix.set(3,1,0);
	    matrix.set(3,0,0);
	    
		return matrix;
	}
	
	//TODO: QuatToRot for both matrix and yaw pitch roll
	
	//TODO: RotToQuat for both matrix and yaw pitch roll
	
	
	
}
