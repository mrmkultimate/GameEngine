package org.GameEngine.Math;
import java.lang.Math;

public class Vector3f {
	
	public float x;
	public float y;
	public float z;

	
	public Vector3f(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	public Vector3f(float X, float Y, float Z){
		this.x = X;
		this.y = Y;
		this.z = Z;
	}
	
	public float dot(Vector3f v1, Vector3f v2){
		return v1.x * v2.x + v1.y * v2.y + v1.z*v2.z;
	}
	
	public Vector3f cross(Vector3f v1, Vector3f v2){
		return new Vector3f(v1.y*v2.z - v1.z*v2.y, v1.z*v2.x - v1.x*v2.z, v1.x*v2.y - v1.y*v2.x);
	}
	
	
	public boolean isEqual(Vector3f  v) {
		return x == v.x && y == v.y && z == v.z;
	}
	
	public void setToEqual(Vector3f  v){
		x = v.x;
		y = v.y;		
		z = v.z;
	}
	
	public Vector3f add(Vector3f  v){
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}
		
	public static Vector3f add(Vector3f v1, Vector3f v2){
		Vector3f v3 = new Vector3f(0,0,0);
		v3.x = v1.x + v2.x;
		v3.y = v1.y + v2.y;
		v3.z = v1.z + v2.z;
		return v3;
	}
	
	public Vector3f subtract(Vector3f  v){
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}	
	public static Vector3f subtract(Vector3f v1, Vector3f v2){
		Vector3f v3 = new Vector3f(0,0,0);
		v3.x = v1.x + v2.x;
		v3.y = v1.y + v2.y;
		v3.z = v1.z + v2.z;
		return v3;
	}
	public Vector3f multiply(float scalar){
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}
	
	public static Vector3f multiply(Vector3f v, float scalar){
		Vector3f v2 = new Vector3f(0,0,0);
		v2.x = v.x*scalar;
		v2.y = v.y*scalar;
		v2.z = v.z*scalar;
		return v2;
	}
	
	public Vector3f divide(float scalar)
	{
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
	
		x /= scalar;
		y /= scalar;
		z /= scalar;
		
		return this;
	}
	
	public static Vector3f divide(Vector3f v, float scalar){
		
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
		
		Vector3f v2 = new Vector3f(0,0,0);
		v2.x = v.x/scalar;
		v2.y = v.y/scalar;
		v2.z = v.z/scalar;
		return v2;
	}

		
	public float  magnitude()
	{
		return (float)Math.sqrt(x*x + y*y + z*z);
	}
	
	public float  magnitudeSquared()
	{
		return x*x + y*y + z*z;
	}
	
	public float  length(Vector3f v1, Vector3f v2)
	{
		return (subtract(v1,v2)).magnitude();
	}
	
	public float  lengthSquared(Vector3f v1, Vector3f v2)
	{
		return (subtract(v1,v2)).magnitudeSquared();
	}
	
	
	//TO DO: add rotation method for vector3f
	
	

	
	
	
	
	public Vector3f  normalize()
	{
		float currentMagnitude =  magnitude();
		
		if (currentMagnitude == 0)
		{
			currentMagnitude = 0.000001f;
			//throw new RuntimeException("Divide by 0 prohibited")
		}
		
		this.multiply(1.0f).divide(currentMagnitude);
	
		return this;
	}
	
	public void  zero()
	{
		x = y = z = 0;
	}
	
	//TO DO: add ability to print the vector

}