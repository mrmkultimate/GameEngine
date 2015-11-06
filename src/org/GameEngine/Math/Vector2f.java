package org.GameEngine.Math;
import java.lang.Math;

public class Vector2f {
	
	public float x;
	public float y;

	
	public Vector2f(){
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2f(float X, float Y){
		this.x = X;
		this.y = Y;
	}
	
	public float Dot(Vector2f v1, Vector2f v2){
		return v1.x * v2.x + v1.y * v2.y;
	}
	
	public float Cross(Vector2f v1, Vector2f v2){
		return v1.x*v2.y - v2.x*v1.y;
	}
	
	public Vector2f Cross(Vector2f v, float z){
		return new Vector2f(z * v.y, -z * v.x);
	}
	
	public Vector2f Cross(float z, Vector2f  v){
		return new Vector2f(-z * v.y, z * v.x);
	}
	
	public boolean isEqual(Vector2f  v) {
		return x == v.x && y == v.y;
	}
	
	public void setToEqual(Vector2f  v){
		x = v.x;
		y = v.y;		
	}
	
	public Vector2f Add(Vector2f  v){
		x += v.x;
		y += v.y;
		return this;
	}
		
	public static Vector2f Add(Vector2f v1, Vector2f v2){
		Vector2f v3 = new Vector2f(0,0);
		v3.x = v1.x + v2.x;
		v3.y = v1.y + v2.y;
		return v3;
	}
	
	public Vector2f Subtract(Vector2f  v){
		x -= v.x;
		y -= v.y;
		return this;
	}	
	public static Vector2f Subtract(Vector2f v1, Vector2f v2){
		Vector2f v3 = new Vector2f(0,0);
		v3.x = v1.x + v2.x;
		v3.y = v1.y + v2.y;
		return v3;
	}
	public Vector2f Multiply(float scalar){
		x *= scalar;
		y *= scalar;
		return this;
	}
	
	public static Vector2f Multiply(Vector2f v, float scalar){
		Vector2f v2 = new Vector2f(0,0);
		v2.x = v.x*scalar;
		v2.y = v.y*scalar;
		return v2;
	}
	
	public Vector2f Divide(float scalar)
	{
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
	
		x /= scalar;
		y /= scalar;
		
		return this;
	}
	
	public static Vector2f Divide(Vector2f v, float scalar){
		
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
		
		Vector2f v2 = new Vector2f(0,0);
		v2.x = v.x/scalar;
		v2.y = v.y/scalar;
		return v2;
	}

		
	public float  Magnitude()
	{
		return (float)Math.sqrt(x*x + y*y);
	}
	
	public float  MagnitudeSquared()
	{
		return x*x + y*y;
	}
	
	public float  Length(Vector2f v1, Vector2f v2)
	{
		return (Subtract(v1,v2)).Magnitude();
	}
	
	public float  LengthSquared(Vector2f v1, Vector2f v2)
	{
		return (Subtract(v1,v2)).MagnitudeSquared();
	}
	
	//Rotates the Vector by angle radians
	public Vector2f Rotate(float angle) 
	{
		float x0 = x;
		float y0 = y;
	
		x = (float) (x0 * Math.cos(angle) - y0 * Math.sin(angle));
		y = (float) (x0 * Math.sin(angle) + y0 * Math.cos(angle));
	
		return this;
	}
	
	public Vector2f  Normalize()
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
	
	public void  Zero()
	{
		x = y = 0;
	}
	
	//TO DO: add ability to print the vector

}