package org.GameEngine.Math;
import java.lang.Math;

public class Vector2i {
	
	public int x;
	public int y;

	
	public Vector2i(){
		this.x = 0;
		this.y = 0;
	}
	
	public Vector2i(int X, int Y){
		this.x = X;
		this.y = Y;
	}
	
	public int Dot(Vector2i v1, Vector2i v2){
		return v1.x * v2.x + v1.y * v2.y;
	}
	
	public int Cross(Vector2i v1, Vector2i v2){
		return v1.x*v2.y - v2.x*v1.y;
	}
	
	public Vector2i Cross(Vector2i v, int z){
		return new Vector2i(z * v.y, -z * v.x);
	}
	
	public Vector2i Cross(int z, Vector2i  v){
		return new Vector2i(-z * v.y, z * v.x);
	}
	
	public boolean isEqual(Vector2i  v) {
		return x == v.x && y == v.y;
	}
	
	public void setToEqual(Vector2i  v){
		x = v.x;
		y = v.y;		
	}
	
	public Vector2i Add(Vector2i  v){
		x += v.x;
		y += v.y;
		return this;
	}
		
	public static Vector2i Add(Vector2i v1, Vector2i v2){
		Vector2i v3 = new Vector2i(0,0);
		v3.x = v1.x + v2.x;
		v3.y = v1.y + v2.y;
		return v3;
	}
	
	public Vector2i Subtract(Vector2i  v){
		x -= v.x;
		y -= v.y;
		return this;
	}	
	public static Vector2i Subtract(Vector2i v1, Vector2i v2){
		Vector2i v3 = new Vector2i(0,0);
		v3.x = v1.x + v2.x;
		v3.y = v1.y + v2.y;
		return v3;
	}
	public Vector2i Multiply(int scalar){
		x *= scalar;
		y *= scalar;
		return this;
	}
	
	public static Vector2i Multiply(Vector2i v, int scalar){
		Vector2i v2 = new Vector2i(0,0);
		v2.x = v.x*scalar;
		v2.y = v.y*scalar;
		return v2;
	}
	
	public Vector2i Divide(int scalar)
	{
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
	
		x /= scalar;
		y /= scalar;
		
		return this;
	}
	
	public static Vector2i Divide(Vector2i v, int scalar){
		
		if (scalar == 0)
			throw new RuntimeException("Divide by 0 prohibited");
		
		Vector2i v2 = new Vector2i(0,0);
		v2.x = v.x/scalar;
		v2.y = v.y/scalar;
		return v2;
	}

		
	public int  Magnitude()
	{
		return (int)Math.sqrt(x*x + y*y);
	}
	
	public int  MagnitudeSquared()
	{
		return x*x + y*y;
	}
	
	public int  Length(Vector2i v1, Vector2i v2)
	{
		return (Subtract(v1,v2)).Magnitude();
	}
	
	public int  lengthSquared(Vector2i v1, Vector2i v2)
	{
		return (Subtract(v1,v2)).MagnitudeSquared();
	}
	
	//Rotates the Vector by angle radians
	public Vector2i Rotate(int angle) 
	{
		int x0 = x;
		int y0 = y;
	
		x = (int) (x0 * Math.cos(angle) - y0 * Math.sin(angle));
		y = (int) (x0 * Math.sin(angle) + y0 * Math.cos(angle));
	
		return this;
	}
	
	public Vector2i  Normalize()
	{
		int currentMagnitude =  Magnitude();
		
		if (currentMagnitude == 0)
		{
			currentMagnitude = 1;
			//throw new RuntimeException("Divide by 0 prohibited")
		}
		
		this.Multiply(1).Divide(currentMagnitude);
	
		return this;
	}
	
	public void  zero()
	{
		x = y = 0;
	}
	
	//TO DO: add ability to print the vector

}