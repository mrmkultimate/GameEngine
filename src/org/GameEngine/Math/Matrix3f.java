package org.GameEngine.Math;

public class Matrix3f {
	
	
	private float[][] m = new float[3][3];
	private float[] matrix1DArray = new float[9];/** Read the matrix as a float array*/

	public Matrix3f(float[] matrix){
		this.setToEqual(matrix);
	}

	public Matrix3f(Matrix3f matrix){
		this.setToEqual(matrix);
	}


	public Vector3f axisX(){
		return new Vector3f(m[0][0], m[1][0], m[2][0]);
	}
	public Vector3f axisY(){
		return new Vector3f(m[0][1], m[1][1], m[2][1]);
	}
	public Vector3f axisZ(){
		return new Vector3f(m[0][2], m[1][2], m[2][2]);
	}

	public float Determinant(){
		return m[0][0]*m[1][1]*m[2][2] + m[0][1]*m[1][2]*m[2][0] + m[0][2]*m[1][0]*m[2][1] - m[0][0]*m[1][2]*m[2][1] - m[0][1]*m[1][0]*m[2][2] - m[0][2]*m[1][1]*m[2][0];
	}

	public static float Determinant(Matrix3f mat){
		return mat.Determinant();
	}
	
	public Matrix3f Transpose() {	
		float[] mat = new float[9];
		mat[0] = m[0][0];
		mat[1] = m[1][0];
		mat[2] = m[2][0];
		mat[3] = m[0][1];
		mat[4] = m[1][1];
		mat[5] = m[2][1];
		mat[6] = m[0][2];
		mat[7] = m[1][2];
		mat[8] = m[2][2];
		
		return new Matrix3f(mat);
	}

	public Matrix3f Inverse() {
		
		float det = Determinant();
		
		if (det == 0){
			throw new RuntimeException("Divide by 0 prohibited");
		}

		float[] mat = new float[9];
		mat[0] = m[1][1]*m[2][2] - m[1][2]*m[2][1];
		mat[1] = m[0][2]*m[2][1] - m[0][1]*m[2][2];
		mat[2] = m[0][1]*m[1][2] - m[0][2]*m[1][1];
		mat[3] = m[1][2]*m[2][0] - m[1][0]*m[2][2];
		mat[4] = m[0][0]*m[2][2] - m[0][2]*m[2][0];
		mat[5] = m[0][2]*m[1][0] - m[0][0]*m[1][2];
		mat[6] = m[1][0]*m[2][1] - m[1][1]*m[2][0];
		mat[7] = m[0][1]*m[2][0] - m[0][0]*m[2][1];
		mat[8] = m[0][0]*m[1][1] - m[0][1]*m[1][0];
		
		Matrix3f inv = new Matrix3f(mat);
		
		return inv.Multiply(1 / det);
	}

	public Matrix3f Multiply(float s) {
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				m[i][j] = m[i][j]*s;
			}
		}
		return this;
	}
	
	public static Matrix3f Multiply(Matrix3f matrix, float s) {
		
		return new Matrix3f(matrix).Multiply(s);
	}

	public Vector3f Multiply(Vector3f rhs){
		return new Vector3f(m[0][0] * rhs.x + m[0][1] * rhs.y + m[0][2]*rhs.z, m[1][0] * rhs.x + m[1][1] * rhs.y + m[1][2]*rhs.z,m[2][0]*rhs.x + m[2][1]*rhs.y + m[2][2]*rhs.z);
	}

	public Matrix3f Multiply(Matrix3f rhs){

		float[] mat = new float[9];
		
		mat[0] = m[0][0]*rhs.m [0][0] + m[0][1]*rhs.m[1][0] + m[0][2]*rhs.m[2][0];
		mat[1] = m[0][0]*rhs.m [0][1] + m[0][1]*rhs.m[1][1] + m[0][2]*rhs.m[2][1];
		mat[2] = m[0][0]*rhs.m [0][2] + m[0][1]*rhs.m[1][2] + m[0][2]*rhs.m[2][2];
		mat[3] = m[1][0]*rhs.m [0][0] + m[1][1]*rhs.m[1][0] + m[1][2]*rhs.m[2][0];
		mat[4] = m[1][0]*rhs.m [0][1] + m[1][1]*rhs.m[1][1] + m[1][2]*rhs.m[2][1];
		mat[5] = m[1][0]*rhs.m [0][2] + m[1][1]*rhs.m[1][2] + m[1][2]*rhs.m[2][2];
		mat[6] = m[2][0]*rhs.m [0][0] + m[2][1]*rhs.m[1][0] + m[2][2]*rhs.m[2][0];
		mat[7] = m[2][0]*rhs.m [0][1] + m[2][1]*rhs.m[1][1] + m[2][2]*rhs.m[2][1];
		mat[8] = m[2][0]*rhs.m [0][2] + m[2][1]*rhs.m[1][2] + m[2][2]*rhs.m[2][2];
		
		this.setToEqual(mat);
		
		return this;
	}

	
	public static Matrix3f Multiply(Matrix3f lhs, Matrix3f rhs){
	
		return new Matrix3f(lhs).Multiply(rhs);
	}

	
	
	public boolean isEqual(Matrix3f rhs){
		
		for(int i = 0; i<3; i++){
			for(int j = 0; j<3; j++){
				if(m[i][j]!=rhs.m[i][j]){
					return false;
				}
			}
		}
		
		return true;
		
	}
	
	public Matrix3f setToEqual(Matrix3f rhs){
		m = Matrix4f.deepCopyFloatMatrix(rhs.m);
		matrix1DArray = rhs.matrix1DArray.clone();
		return this;
	}

	
	

	public void setToEqual(float[] matrix) {
		if(matrix.length == 9){
			m[0][0] = matrix[0];
			m[0][1] = matrix[1];
			m[0][2] = matrix[2];
			m[1][0] = matrix[3];
			m[1][1] = matrix[4];
			m[1][2] = matrix[5];
			m[2][0] = matrix[6];
			m[2][1] = matrix[7];
			m[2][2] = matrix[8];
			matrix1DArray = matrix.clone();
		}else{
			throw new RuntimeException("Wrong Size for Matrix");
		}
	}

	public float[] getMatrix1DArray() {
		
		matrix1DArray[0] = m[0][0];
		matrix1DArray[1] = m[0][1];
		matrix1DArray[2] = m[0][2];
		matrix1DArray[3] = m[1][0];
		matrix1DArray[4] = m[1][1];
		matrix1DArray[5] = m[1][2];
		matrix1DArray[6] = m[2][0];
		matrix1DArray[7] = m[2][1];
		matrix1DArray[8] = m[2][2];
		
		return matrix1DArray.clone();
	}
	
	public float[][] getMatrix() {
		
		return Matrix4f.deepCopyFloatMatrix(m);
	}

	public void setToEqual(float[][] matrix2DArray) {
		
		m = Matrix4f.deepCopyFloatMatrix(matrix2DArray);
		
		matrix1DArray[0] = matrix2DArray[0][0];
		matrix1DArray[1] = matrix2DArray[0][1];
		matrix1DArray[2] = matrix2DArray[0][2];
		matrix1DArray[3] = matrix2DArray[1][0];
		matrix1DArray[4] = matrix2DArray[1][1];
		matrix1DArray[5] = matrix2DArray[1][2];
		matrix1DArray[6] = matrix2DArray[2][0];
		matrix1DArray[7] = matrix2DArray[2][1];
		matrix1DArray[8] = matrix2DArray[2][2];
	}

	public void set(int x, int y, float value){
		
		m[x][y] = value;
		matrix1DArray[3*x+y] = value;
	}
	
	public float get(int x, int y){
		return m[x][y];
	}
	
	
}
