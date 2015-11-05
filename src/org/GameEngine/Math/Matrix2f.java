package org.GameEngine.Math;

public class Matrix2f {


	public float m00, m01; /** Read the matrix as four floats*/
	public float m10, m11;
	
	private float[][] m = new float[2][2];
	

	public Matrix2f(float radians){
		float c = (float) Math.cos(radians);
		float s = (float) Math.sin(radians);

		this.m00 = c; this.m01 = -s;
		this.m10 = s; this.m11 = c;
	}

	public Matrix2f(float a, float b, float c, float d){
		this.m00 = a; this.m01 = b;
		this.m10 = c; this.m11 = d;
	}

	public Matrix2f(Matrix2f matrix){
		this.m00 = matrix.m00; this.m01 = matrix.m01;
		this.m10 = matrix.m10; this.m11 = matrix.m11;
	}

	public void set(float radians){
		float c = (float) Math.cos(radians);
		float s = (float) Math.sin(radians);

		m00 = c; m01 = -s;
		m10 = s; m11 = c;
	}

	public Vector2f axisX(){
		return new Vector2f(m00, m10);
	}

	public Vector2f axisY(){
		return new Vector2f(m01, m11);
	}

	public float determinant(){
		return m00*m11 - m01*m10;
	}

	public Matrix2f transpose() {
		return new Matrix2f(m00, m10, m01, m11);
	}

	public Matrix2f inverse() {
		float det = determinant();
		if (det == 0){
			throw new RuntimeException("Divide by 0 prohibited");
		}

		Matrix2f inv = new Matrix2f(m11, -m01, -m10, m00);
		return inv.multiply(1 / det);
	}

	public Matrix2f multiply(float s) {
		m00 = m00*s; m01 = m01*s;
		m10 = m10*s; m11 = m11*s;
		return this;
	}
	
	public static Matrix2f multiply(Matrix2f matrix, float s) {
		
		return new Matrix2f(matrix.m00*s,matrix.m01*s,matrix.m10*s,matrix.m11*s);
	}

	public Vector2f multiply(Vector2f rhs){
		return new Vector2f(m00 * rhs.x + m01 * rhs.y, m10 * rhs.x + m11 * rhs.y);
	}

	public Matrix2f multiply(Matrix2f rhs){
		// [00 01]  [00 01]
		// [10 11]  [10 11]

		this.setToEqual(new Matrix2f(
			m00 * rhs.m00 + m01 * rhs.m10,
			m00 * rhs.m01 + m01 * rhs.m11,
			m10 * rhs.m00 + m11 * rhs.m11,
			m10 * rhs.m01 + m11 * rhs.m11
			));
		return this;
	}

	
	public static Matrix2f multiply(Matrix2f lhs, Matrix2f rhs){
		// [00 01]  [00 01]
		// [10 11]  [10 11]

		
		return new Matrix2f(
				lhs.m00 * rhs.m00 + lhs.m01 * rhs.m10,
				lhs.m00 * rhs.m01 + lhs.m01 * rhs.m11,
				lhs.m10 * rhs.m00 + lhs.m11 * rhs.m11,
				lhs.m10 * rhs.m01 + lhs.m11 * rhs.m11
			);
	}

	
	
	public boolean isEqual(Matrix2f rhs){
		if(m00 == rhs.m00 &&m01 == rhs.m01 &&m10 == rhs.m10 && m11 == rhs.m11)	{
			return true;
		}else{
			return false;
		}
	}
	
	public Matrix2f setToEqual(Matrix2f rhs){
		m00 = rhs.m00;
		m01 = rhs.m01;
		m10 = rhs.m10;
		m11 = rhs.m11;
		return this;
	}

	
	public float[][] getMatrix2f() {
		m[0][0] = m00;
		m[0][1] = m01;
		m[1][0] = m10;
		m[1][1] = m11;
		return m;
	}

	public void setToEqual(float[][] m) {
		if(m.length == 4){
			m00 = m[0][0];
			m01 = m[0][1];
			m10 = m[1][0];
			m11 = m[1][1];
			this.m = m;
		}else{
			throw new RuntimeException("Wrong Size for Matrix");
		}
	}
	
}


