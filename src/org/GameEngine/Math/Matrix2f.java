package org.GameEngine.Math;

public class Matrix2f {

	
	private float[] m = new float[4];/** Read the matrix as four floats array*/
	

	public Matrix2f(float radians){
		float c = (float) Math.cos(radians);
		float s = (float) Math.sin(radians);

		this.m[0] = c; this.m[1] = -s;
		this.m[2] = s; this.m[3] = c;
	}

	public Matrix2f(float a, float b, float c, float d){
		this.m[0] = a; this.m[1] = b;
		this.m[2] = c; this.m[3] = d;
	}

	public Matrix2f(Matrix2f matrix){
		this.m[0] = matrix.m[0]; this.m[1] = matrix.m[1];
		this.m[2] = matrix.m[2]; this.m[3] = matrix.m[3];
	}

	public void set(float radians){
		float c = (float) Math.cos(radians);
		float s = (float) Math.sin(radians);

		m[0] = c; m[1] = -s;
		m[2] = s; m[3] = c;
	}

	public Vector2f axisX(){
		return new Vector2f(m[0], m[2]);
	}

	public Vector2f axisY(){
		return new Vector2f(m[1], m[3]);
	}

	public float Determinant(){
		return m[0]*m[3] - m[1]*m[2];
	}

	public Matrix2f Transpose() {
		return new Matrix2f(m[0], m[2], m[1], m[3]);
	}

	public Matrix2f Inverse() {
		float det = Determinant();
		if (det == 0){
			throw new RuntimeException("Divide by 0 prohibited");
		}

		Matrix2f inv = new Matrix2f(m[3], -m[1], -m[2], m[0]);
		return inv.Multiply(1 / det);
	}

	public Matrix2f Multiply(float s) {
		m[0] = m[0]*s; m[1] = m[1]*s;
		m[2] = m[2]*s; m[3] = m[3]*s;
		return this;
	}
	
	public static Matrix2f Multiply(Matrix2f matrix, float s) {
		
		return new Matrix2f(matrix.m[0]*s,matrix.m[1]*s,matrix.m[2]*s,matrix.m[3]*s);
	}

	public Vector2f Multiply(Vector2f rhs){
		return new Vector2f(m[0] * rhs.x + m[1] * rhs.y, m[2] * rhs.x + m[3] * rhs.y);
	}

	public Matrix2f Multiply(Matrix2f rhs){
		// [00 01]  [00 01]
		// [10 11]  [10 11]

		this.setToEqual(new Matrix2f(
			m[0] * rhs.m[0] + m[1] * rhs.m[2],
			m[0] * rhs.m[1] + m[1] * rhs.m[3],
			m[2] * rhs.m[0] + m[3] * rhs.m[3],
			m[2] * rhs.m[1] + m[3] * rhs.m[3]
			));
		return this;
	}

	
	public static Matrix2f Multiply(Matrix2f lhs, Matrix2f rhs){
		// [00 01]  [00 01]
		// [10 11]  [10 11]

		
		return new Matrix2f(
				lhs.m[0] * rhs.m[0] + lhs.m[1] * rhs.m[2],
				lhs.m[0] * rhs.m[1] + lhs.m[1] * rhs.m[3],
				lhs.m[2] * rhs.m[0] + lhs.m[3] * rhs.m[3],
				lhs.m[2] * rhs.m[1] + lhs.m[3] * rhs.m[3]
			);
	}

	
	
	public boolean isEqual(Matrix2f rhs){
		if(m[0] == rhs.m[0] &&m[1] == rhs.m[1] &&m[2] == rhs.m[2] && m[3] == rhs.m[3])	{
			return true;
		}else{
			return false;
		}
	}
	
	public Matrix2f setToEqual(Matrix2f rhs){
		m[0] = rhs.m[0];
		m[1] = rhs.m[1];
		m[2] = rhs.m[2];
		m[3] = rhs.m[3];
		return this;
	}

	
	public float[] getMatrix() {
		return m.clone();
	}

	public void setToEqual(float[] mat2f) {
		if(mat2f.length == 4){
			m[0] = mat2f[0];
			m[1] = mat2f[1];
			m[2] = mat2f[2];
			m[3] = mat2f[3];
		}else{
			throw new RuntimeException("Wrong Size for Matrix");
		}
	}
	
}


