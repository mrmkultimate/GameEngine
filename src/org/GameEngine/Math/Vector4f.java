package org.GameEngine.Math;

public class Vector4f {

	public float x;
	public float y;
	public float z;
	public float w;
	
	
	public Vector4f(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
		this.w = 0;
	}
	
	public Vector4f(float X, float Y, float Z, float W){
		this.x = X;
		this.y = Y;
		this.z = Z;
		this.w = W;
	}
	
	public float Dot(Vector4f v1, Vector4f v2){
		return v1.x * v2.x + v1.y * v2.y + v1.z*v2.z + v1.w*v2.w;
	}
	
	//TO DO: cross product

	
	
	public boolean isEqual(Vector4f  v) {
		return x == v.x && y == v.y && z == v.z && w == v.w;
	}
	
	public void setToEqual(Vector4f  v){
		x = v.x;
		y = v.y;		
		z = v.z;
		w = v.w;
	}
	
	public Vector4f Add(Vector4f  v){
		x += v.x;
		y += v.y;
		z += v.z;
		w += v.w;
		return this;
	}
		
	public static Vector4f Add(Vector4f v1, Vector4f v2){
		Vector4f v3 = new Vector4f(0,0,0,0);
		v3.x = v1.x + v2.x;
		v3.y = v1.y + v2.y;
		v3.z = v1.z + v2.z;
		v3.w = v1.w + v2.w;
		return v3;
	}
	
	public Vector4f Subtract(Vector4f  v){
		x -= v.x;
		y -= v.y;
		z -= v.z;
		w -= v.w;
		return this;
	}	
	public static Vector4f Subtract(Vector4f v1, Vector4f v2){
		Vector4f v3 = new Vector4f(0,0,0,0);
		v3.x = v1.x + v2.x;
		v3.y = v1.y + v2.y;
		v3.z = v1.z + v2.z;
		v3.w = v1.w + v2.w;
		return v3;
	}
	public Vector4f Multiply(float scalar){
		x *= scalar;
		y *= scalar;
		z *= scalar;
		w *= scalar;
		return this;
	}
	
	public static Vector4f Multiply(Vector4f v, float scalar){
		Vector4f v2 = new Vector4f(0,0,0,0);
		v2.x = v.x*scalar;
		v2.y = v.y*scalar;
		v2.z = v.z*scalar;
		v2.w = v.w*scalar;
		return v2;
	}
	
	public Vector4f Divide(float scalar)
	{
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
	
		x /= scalar;
		y /= scalar;
		z /= scalar;
		z /= scalar;
		
		return this;
	}
	
	public static Vector4f Divide(Vector4f v, float scalar){
		
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
		
		Vector4f v2 = new Vector4f(0,0,0,0);
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
	
	public float length(Vector4f v1, Vector4f v2)
	{
		return (Subtract(v1,v2)).Magnitude();
	}
	
	public float lengthSquared(Vector4f v1, Vector4f v2)
	{
		return (Subtract(v1,v2)).MagnitudeSquared();
	}
	
	
	public Vector4f normalize()
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
	
	public void  zero()
	{
		x = y = z = w = 0;
	}
	
	
}
