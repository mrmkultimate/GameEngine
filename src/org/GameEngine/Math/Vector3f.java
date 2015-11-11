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
	
	public Vector3f(Vector3f v){
		this.x = v.x;
		this.y = v.y;
		this.z = v.z;
	}
	
	
	public static float Dot(Vector3f v1, Vector3f v2){
		return v1.x * v2.x + v1.y * v2.y + v1.z*v2.z;
	}
	
	public float Dot(Vector3f rhv){
		return Dot(this,rhv);
	}
	
	public Vector3f Cross(Vector3f rhv){
		Vector3f lhv = new Vector3f(this);
		
		this.x = lhv.y*rhv.z - lhv.z*rhv.y;
		this.y = lhv.z*rhv.x - lhv.x*rhv.z;
		this.z = lhv.x*rhv.y - lhv.y*rhv.x;
		return this;
	}
	
	public static Vector3f Cross(Vector3f v1, Vector3f v2){
		return new Vector3f(v1).Cross(v2);
	}
	
	
	public boolean isEqual(Vector3f v) {
		return x == v.x && y == v.y && z == v.z;
	}
	
	public void setToEqual(Vector3f v){
		x = v.x;
		y = v.y;		
		z = v.z;
	}
	
	public Vector3f Add(Vector3f v){
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}
		
	public static Vector3f Add(Vector3f v1, Vector3f v2){
		Vector3f v3 = new Vector3f(0,0,0);
		v3.x = v1.x + v2.x;
		v3.y = v1.y + v2.y;
		v3.z = v1.z + v2.z;
		return v3;
	}
	
	public Vector3f Subtract(Vector3f v){
		x -= v.x;
		y -= v.y;
		z -= v.z;
		return this;
	}	
	public static Vector3f Subtract(Vector3f v1, Vector3f v2){
		Vector3f v3 = new Vector3f(0,0,0);
		v3.x = v1.x - v2.x;
		v3.y = v1.y - v2.y;
		v3.z = v1.z - v2.z;
		return v3;
	}
	public Vector3f Multiply(float scalar){
		x *= scalar;
		y *= scalar;
		z *= scalar;
		return this;
	}
	
	public static Vector3f Multiply(Vector3f v, float scalar){
		Vector3f v2 = new Vector3f(0,0,0);
		v2.x = v.x*scalar;
		v2.y = v.y*scalar;
		v2.z = v.z*scalar;
		return v2;
	}
	
	public Vector3f Divide(float scalar)
	{
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
	
		x /= scalar;
		y /= scalar;
		z /= scalar;
		
		return this;
	}
	
	public static Vector3f Divide(Vector3f v, float scalar){
		
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
		
		Vector3f v2 = new Vector3f(0,0,0);
		v2.x = v.x/scalar;
		v2.y = v.y/scalar;
		v2.z = v.z/scalar;
		return v2;
	}

		
	public float Magnitude()
	{
		return (float)Math.sqrt(x*x + y*y + z*z);
	}
	
	public float MagnitudeSquared()
	{
		return x*x + y*y + z*z;
	}
	
	public float length(Vector3f v1, Vector3f v2)
	{
		return (Subtract(v1,v2)).Magnitude();
	}
	
	public float lengthSquared(Vector3f v1, Vector3f v2)
	{
		return (Subtract(v1,v2)).MagnitudeSquared();
	}
	
	
	//TO DO: add rotation method for vector3f
	
	

	
	
	
	
	public Vector3f  normalize()
	{
		float currentMagnitude =  Magnitude();
		
		if (currentMagnitude == 0)
		{
			currentMagnitude = 0.000001f;
			//throw new RuntimeException("Divide by 0 prohibited")
		}
		
		this.Multiply(1.0f).Divide(currentMagnitude);
	
		return this;
	}
	
	public void  zero()
	{
		x = y = z = 0;
	}
	
	//TO DO: add ability to print the vector

}