package org.GameEngine.Math;

public class Matrix4f {
	
	private float[][] m = new float[4][4];
	private float[] matrix1DArray = new float[16];
	
	public Matrix4f(){
		
		
	}
	
	
	public Matrix4f(Matrix4f mat){
		this.setToEqual(mat.getMatrix2DArray());
	}
	
	
	public Matrix4f initIdentity(){
		m[0][0] = 1; m[0][1] = 0; m[0][2] = 0; m[0][3] = 0;
		m[1][0] = 0; m[1][1] = 1; m[1][2] = 0; m[1][3] = 0;
		m[2][0] = 0; m[2][1] = 0; m[2][2] = 1; m[2][3] = 0;
		m[3][0] = 0; m[3][1] = 0; m[3][2] = 0; m[3][3] = 1;
		
		for(int i = 0; i<4; i++){
			for(int j = 0; j<4; j++){
				matrix1DArray[i*4 + j] = m[i][j];
			}
		}
		
		
		return this;
	}
	
	public Matrix4f Multiply(Matrix4f rhm){

		for(int i = 0; i<4; i++){
			for(int j = 0; j<4; j++){
				this.set(i,j,m[i][0] * rhm.m[0][j] + 
							m[i][1] * rhm.m[1][j] + 
							m[i][2] * rhm.m[2][j] + 
							m[i][3] * rhm.m[3][j]);
			}
		}
		return this;	
	}
	
	public static Matrix4f Multiply(Matrix4f lhm,Matrix4f rhm){
		Matrix4f result = new Matrix4f();
		for(int i = 0; i<4; i++){
			for(int j = 0; j<4; j++){
				result.set(i,j,	lhm.m[i][0] * rhm.m[0][j] + 
								lhm.m[i][1] * rhm.m[1][j] + 
								lhm.m[i][2] * rhm.m[2][j] + 
								lhm.m[i][3] * rhm.m[3][j]);
			}
		}
		return result;
	}
	
	
	public float[] getMatrix1DArray() {
		
		return matrix1DArray.clone();
	}
	
	public float[][] getMatrix2DArray(){
		return m.clone();
	}
	
	public void setToEqual(Matrix4f matrix){
		this.setToEqual(matrix.m);
	}
	
	public void setToEqual(float[][] mat){
		if(mat.length != 4){
			return;
		}
		
		for(int k = 0; k<4; k++){
			if(mat[k].length != 4){
				return;
			}
		}
		
		for(int i = 0; i<4; i++){
			for(int j = 0; j<4; j++){
				m[i][j] = mat[i][j];
			}
		}
		
		
	}
	
	public void set(int x, int y, float value){
		
		m[x][y] = value;
		matrix1DArray[4*x+y] = value;
	}
	
	public float get(int x, int y){
		return m[x][y];
	}
	
	
}
