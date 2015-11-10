package org.GameEngine.Math;

public class Matrix4f {
	
	private float[][] m = new float[4][4];
	private float[] matrix1DArray = new float[16];
	
	public Matrix4f(){
		
		
	}
	
	
	public Matrix4f(Matrix4f mat){
		this.setToEqual(mat.getMatrix2DArray());
	}
	
	public Matrix4f(float[][] mat) {
		// TODO Auto-generated constructor stub
		this.setToEqual(mat);
	}


	public Matrix4f Transpose(){
		float[][] tmp = new float[4][4];
		for(int i = 0; i<4; i++){
			for(int j = 0; j<4; j++){
				tmp[i][j] = m[j][i];
			}
		}
		return new Matrix4f().setToEqual(tmp);
	}
	
	public static Matrix4f Transpose(Matrix4f matrix){
		return matrix.Transpose();
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
		Matrix4f result = new Matrix4f();
		for(int i = 0; i<4; i++){
			for(int j = 0; j<4; j++){
				result.set(i,j,m[i][0] * rhm.m[0][j] + 
							m[i][1] * rhm.m[1][j] + 
							m[i][2] * rhm.m[2][j] + 
							m[i][3] * rhm.m[3][j]);
			}
		}
		this.setToEqual(result);
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
	
	private void update1DArray(){
		for(int i = 0; i<4; i++){
			for(int j = 0; j<4; j++){
				matrix1DArray[i*4 + j] = m[i][j];
			}
		}
	}
	public float[] getMatrix1DArray() {
		this.update1DArray();
		return matrix1DArray.clone();
	}
	
	public float[][] getMatrix2DArray(){
		
		return deepCopyFloatMatrix(m);
	}
	
	public Matrix4f setToEqual(Matrix4f matrix){
		
		this.setToEqual(matrix.m);
		return this;
	}
	
	public Matrix4f setToEqual(float[][] mat){
		if(mat.length != 4){
			System.out.println("Matrix4f error in setToEqual");
			return null;
		}
		
		for(int k = 0; k<4; k++){
			if(mat[k].length != 4){
				System.out.println("Matrix4f error in setToEqual");
				return null;
			}
		}
		
		for(int i = 0; i<4; i++){
			for(int j = 0; j<4; j++){
				m[i][j] = mat[i][j];
			}
		}
		
		return this;
		
	}
	
	public void set(int x, int y, float value){
		
		m[x][y] = value;
	}
	
	public float get(int x, int y){
		return m[x][y];
	}
	
	
	public static float[][] deepCopyFloatMatrix(float[][] input) {
	    if (input == null)
	        return null;
	    float[][] result = new float[input.length][];
	    for (int r = 0; r < input.length; r++) {
	        result[r] = input[r].clone();
	    }
	    return result;
	}
	
	public Quaternion ToQuaternion(){
		Quaternion result = new Quaternion();
		
		result.w = ( m[0][0] + m[1][1] + m[2][2] + 1.0f) / 4.0f;
		result.x = ( m[0][0] - m[1][1] - m[2][2] + 1.0f) / 4.0f;
		result.y = (-m[0][0] + m[1][1] - m[2][2] + 1.0f) / 4.0f;
		result.z = (-m[0][0] - m[1][1] + m[2][2] + 1.0f) / 4.0f;
		if(result.w < 0.0f) result.w = 0.0f;
		if(result.x < 0.0f) result.x = 0.0f;
		if(result.y < 0.0f) result.y = 0.0f;
		if(result.z < 0.0f) result.z = 0.0f;
		result.w = (float) Math.sqrt(result.w);
		result.x = (float) Math.sqrt(result.x);
		result.y = (float) Math.sqrt(result.y);
		result.z = (float) Math.sqrt(result.z);
		if(result.w >= result.x && result.w >= result.y && result.w >= result.z) {
		    result.w *= +1.0f;
		    result.x *= Math.signum(m[2][1] - m[1][2]);
		    result.y *= Math.signum(m[0][2] - m[2][0]);
		    result.z *= Math.signum(m[1][0] - m[0][1]);
		} else if(result.x >= result.w && result.x >= result.y && result.x >= result.z) {
		    result.w *= Math.signum(m[2][1] - m[1][2]);
		    result.x *= +1.0f;
		    result.y *= Math.signum(m[1][0] + m[0][1]);
		    result.z *= Math.signum(m[0][2] + m[2][0]);
		} else if(result.y >= result.w && result.y >= result.x && result.y >= result.z) {
		    result.w *= Math.signum(m[0][2] - m[2][0]);
		    result.x *= Math.signum(m[1][0] + m[0][1]);
		    result.y *= +1.0f;
		    result.z *= Math.signum(m[2][1] + m[1][2]);
		} else if(result.z >= result.w && result.z >= result.x && result.z >= result.y) {
		    result.w *= Math.signum(m[1][0] - m[0][1]);
		    result.x *= Math.signum(m[2][0] + m[0][2]);
		    result.y *= Math.signum(m[2][1] + m[1][2]);
		    result.z *= +1.0f;
		} else {
			System.out.println("coding error");
		}
		
		/*
		r = NORM(result.w, result.x, result.y, result.z);
		result.w /= r;
		result.x /= r;
		result.y /= r;
		result.z /= r		
		*/
		
		result.normalize();
		
		return result;
	}
	
	public Vector3f ToEulerAngles(){
		Vector3f eulerAngles = new Vector3f();
		float a1;
		float a2;
		float b1;
		float b2;
		float c1;
		float c2;
		if((m[2][0] != 1) && (m[2][0] != -1)){
			a1 = (float) -Math.asin(m[2][0]);
			a2 = (float) (Math.PI - a1);
			if((a1 == (float) (Math.PI/2)) || (a1 == (float) (-Math.PI/2)) ){
				//gimble lock
				eulerAngles.z = 0;
				if(m[2][0] == -1){
					eulerAngles.y = (float) (Math.PI/2);
					eulerAngles.x = (float) Math.atan2(m[0][1], m[0][2]);
				}
				else{
					eulerAngles.y = (float) -(Math.PI/2);
					eulerAngles.x = (float) Math.atan2(-m[0][1], -m[0][2]);
				}
			}
			else{
				b1 = (float) Math.atan2(m[2][1]/Math.cos(a1), m[2][2]/Math.cos(a1));
				b2 = (float) Math.atan2(m[2][1]/Math.cos(a2), m[2][2]/Math.cos(a2));
				c1 = (float) Math.atan2(m[1][0]/Math.cos(a1), m[0][0]/Math.cos(a1));
				c2 = (float) Math.atan2(m[1][0]/Math.cos(a2), m[0][0]/Math.cos(a2));
				
				eulerAngles.y = a1;
				eulerAngles.x = b1;
				eulerAngles.z = c1;

			}
		}
		else{
			eulerAngles.z = 0;
			if(m[2][0] == -1){
				eulerAngles.y = (float) (Math.PI/2);
				eulerAngles.x = (float) Math.atan2(m[0][1], m[0][2]);
			}
			else{
				eulerAngles.y = (float) -(Math.PI/2);
				eulerAngles.x = (float) Math.atan2(-m[0][1], -m[0][2]);
			}
		}	
		
		return eulerAngles;
	}
	
}
