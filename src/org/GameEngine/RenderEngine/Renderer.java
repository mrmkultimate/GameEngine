package org.GameEngine.RenderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import org.GameEngine.LevelManager.Level;
import org.GameEngine.Math.Matrix4f;
import org.GameEngine.Math.Quaternion;
import org.GameEngine.Math.Vector2f;
import org.GameEngine.Math.Vector3f;
import org.GameEngine.Objects.Component;
import org.GameEngine.Objects.GameObject;
import org.GameEngine.Objects.Transform;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.GLAutoDrawable;

public class Renderer extends Component {
	
	private Material material = new Material();
	private Mesh mesh = new Mesh();
	
	boolean initialized = false;
	
	//OpenGL stuff
	IntBuffer vertexArray = IntBuffer.allocate(1);
	//private int program;
	
	private IntBuffer buffers = IntBuffer.allocate(4);
	

	//vertex shader in
	FloatBuffer vertexFB;
	FloatBuffer colorFB;
	FloatBuffer normalFB;
	FloatBuffer textureFB;
	
	float[] vertices;
	float[] colors;
	float[] normals;
	float[] textureCoordinates;
	
	
	//uniforms
	FloatBuffer gameObjectPositionFB;
	FloatBuffer gameObjectTransformationMatrixFB;
	FloatBuffer modelViewMatrixFB;
	FloatBuffer projectionMatrixFB;	
	
	float[] gameObjectPosition = new float[4];
	float[] gameObjectTransformationMatrix = new float[16];
	float[] modelViewMatrix = new float[16];
	float[] projectionMatrix = new float[16];
	
	public Renderer(){ 
		setName("Renderer");
		
	}
	
	
	public void render(GLAutoDrawable drawable, int program){
		
		GL3 gl = drawable.getGL().getGL3();
		
		updateBuffers(drawable);
		
		
	
	
		//Calculate gameobject transformation matrix (for the position, rotation, and scale of the gameobject)
		//scale, then rotate, then translate
		Transform transform = this.gameObject.getTransform();
		
		
		//scale
		Matrix4f scaleMatrix = new Matrix4f().initIdentity();
		scaleMatrix.set(0, 0, transform.getScale().x);
		scaleMatrix.set(1, 1, transform.getScale().y);
		scaleMatrix.set(2, 2, transform.getScale().z);
		
		//rotation
		transform.getRotation().getQuaternion().normalize();
		Matrix4f rotationMatrix = new Matrix4f(transform.getRotation().getQuaternion().ToRotationMatrix());

		//position
		Matrix4f positionMatrix = new Matrix4f().initIdentity();
		positionMatrix.set(0, 3, transform.getPosition().x);
		positionMatrix.set(1, 3, transform.getPosition().y);
		positionMatrix.set(2, 3, transform.getPosition().z);
		
		
		//transformationMatrix
		Matrix4f transformationMatrix = new Matrix4f().initIdentity();
		transformationMatrix.Multiply(positionMatrix).Multiply(rotationMatrix).Multiply(scaleMatrix);
		
		//put the matrix into a buffer 
		gameObjectTransformationMatrix = transformationMatrix.getMatrix1DArray();
		gameObjectTransformationMatrixFB = FloatBuffer.wrap(gameObjectTransformationMatrix);		
		
		//add transformation matrix to the shader
		gl.glUniformMatrix4fv(gl.glGetUniformLocation(program, "gameObjectTransformationMatrix"), 1,true,gameObjectTransformationMatrixFB);
		
		/*void glUniformMatrix4fv( 	GLint location,
  		GLsizei count,
  		GLboolean transpose,
  		const GLfloat *value);
		 */
		
		
		
		Color materialAmbient = material.getAmbient();
		Color materialDiffuse = material.getDiffuse();
		Color materialSpecular = material.getSpecular();
		
		List<GameObject> lightObjects = Level.getLightObjects();
		Color lightAmbient = Level.getLightAmbient();
		
		
		//for now assume there is only one light
		Light light;
		Color lightDiffuse = new Color(0,0,0,1);
		Color lightSpecular = new Color(0,0,0,1);
		if(!lightObjects.isEmpty()){
			light = lightObjects.get(0).getLight();
			lightDiffuse = light.getDiffuse();
			lightSpecular = light.getSpecular();
			
			//add light position to the shader
			Transform lightObjectTransform = lightObjects.get(0).getTransform();
			FloatBuffer lightPositionFB;
			float[] lightPosition = {lightObjectTransform.getPosition().x,lightObjectTransform.getPosition().y,lightObjectTransform.getPosition().z,1};
			lightPositionFB = FloatBuffer.wrap(lightPosition);	
			gl.glUniform4fv(gl.glGetUniformLocation(program, "lightPosition"), 1,lightPositionFB);
			
		}
		else{
			
		}
		
		
		
		Color ambientProduct = Color.Multiply(lightAmbient, materialAmbient);
	    Color diffuseProduct = Color.Multiply(lightDiffuse, materialDiffuse);
	    Color specularProduct = Color.Multiply(lightSpecular, materialSpecular); 
		
	    //put the colors into a buffer 
	    FloatBuffer ambientProductFB;
	    FloatBuffer diffuseProductFB;
	    FloatBuffer specularProductFB;
	    float[] ambientProductFA = {ambientProduct.red,ambientProduct.green,ambientProduct.blue,ambientProduct.alpha};
	    float[] diffuseProductFA = {diffuseProduct.red,diffuseProduct.green,diffuseProduct.blue,diffuseProduct.alpha};
	    float[] specularProductFA = {specularProduct.red,specularProduct.green,specularProduct.blue,specularProduct.alpha};
	    
	    ambientProductFB = FloatBuffer.wrap(ambientProductFA);	
	    diffuseProductFB = FloatBuffer.wrap(diffuseProductFA);	
	    specularProductFB = FloatBuffer.wrap(specularProductFA);	
		
		//add colors to the shader
		gl.glUniform4fv(gl.glGetUniformLocation(program, "ambientProduct"), 1,ambientProductFB);
		gl.glUniform4fv(gl.glGetUniformLocation(program, "diffuseProduct"), 1,diffuseProductFB);
		gl.glUniform4fv(gl.glGetUniformLocation(program, "specularProduct"), 1,specularProductFB);
		gl.glUniform1f(gl.glGetUniformLocation(program, "shininess"), material.getShininess());
		//Calculate the model view matrix using the camera transform(used for position, rotation, scale of the camera)
		GameObject camera = Level.getMainCameraObject();
		Transform cameraTransform = camera.getTransform();
		
		/*
		//scale
		Matrix4f inverseCameraScaleMatrix = new Matrix4f().initIdentity();
		inverseCameraScaleMatrix.set(0, 0, cameraTransform.getScale().x);
		inverseCameraScaleMatrix.set(1, 1, cameraTransform.getScale().y);
		inverseCameraScaleMatrix.set(2, 2, cameraTransform.getScale().z);
		*/
		
		//rotation
		Quaternion cameraQuaternion = cameraTransform.getRotation().getQuaternion();
		cameraQuaternion.normalize();
		Matrix4f cameraRotationMatrix = cameraQuaternion.ToRotationMatrix();
		
		Matrix4f inverseCameraRotationMatrix = cameraRotationMatrix.Transpose();
		
		//position
		Matrix4f inverseCameraPositionMatrix = new Matrix4f().initIdentity();
		inverseCameraPositionMatrix.set(0, 3, -cameraTransform.getPosition().x);
		inverseCameraPositionMatrix.set(1, 3, -cameraTransform.getPosition().y);
		inverseCameraPositionMatrix.set(2, 3, -cameraTransform.getPosition().z);
		
		
		//calculate model view matrix
		Matrix4f ViewMatrix = new Matrix4f().initIdentity();
		ViewMatrix.Multiply(inverseCameraRotationMatrix).Multiply(inverseCameraPositionMatrix);
		
		//put the matrix into a buffer 
		modelViewMatrix = ViewMatrix.getMatrix1DArray();
		modelViewMatrixFB = FloatBuffer.wrap(modelViewMatrix);		
		
		//add model view matrix to the shader
		gl.glUniformMatrix4fv(gl.glGetUniformLocation(program, "modelViewMatrix"), 1,true,modelViewMatrixFB);

	
		
		//TODO: get the projection matrix
		
		//			2D/W		0			0			0
		//			0			2D/H		0			0
		//	P = [	0			0			F/(F-D)		-FD/(F-D)
		//			0			0			1			0
		//
		// D = near plane
		// F = far plane
		// W = view width at near plane
		// H = view height at near plane
		// 2D/H = 1.0/tan(D2R * fov / 2)
		// 2D/W = 2D/(H*aspect)
		
		
	    
		Matrix4f projMatrix = new Matrix4f().initIdentity();
		float D = camera.getCamera().getNearPlane();
		float F = camera.getCamera().getFarPlane();
		float fov = camera.getCamera().getFOV();
		float aspect = camera.getCamera().getAspectRatio();
		float yScale = (float) (1.0 / Math.tan(fov / 2));
	    float xScale = yScale / aspect;
		
		projMatrix.set(0, 0, xScale);
		projMatrix.set(1, 1, yScale);
		projMatrix.set(2, 2, F/(F-D));
		projMatrix.set(2, 3, -F*D/(F-D));
		projMatrix.set(3, 2, 1);
		projMatrix.set(3, 3, 0);
		
		//put the matrix into a buffer 
		projectionMatrix = projMatrix.getMatrix1DArray();
		projectionMatrixFB = FloatBuffer.wrap(projectionMatrix);		
		
		//add projection matrix to the shader
		gl.glUniformMatrix4fv(gl.glGetUniformLocation(program, "projectionMatrix"), 1,true,projectionMatrixFB);

		
		
		/*
		    attribute  vec4 vPosition;
			attribute  vec3 vNormal;
			attribute  vec2 vTexCoord;
			
			uniform vec4 ambientProduct, diffuseProduct, specularProduct;
			uniform vec4 lightPosition;
			uniform float shininess;
			
			uniform mat4 modelViewMatrix;
			uniform mat4 projectionMatrix;
			
			varying vec4 color;
			varying vec2 fTexCoord;
		  
		*/

		//draw vertices
		gl.glBindVertexArray(vertexArray.get(0));
	    gl.glDrawArrays(GL.GL_TRIANGLES, 0, mesh.getVertices().size());
		

	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
		material.setTextureUpdated(true);
		material.setColorUpdated(true);
	}

	public Mesh getMesh() {
		return mesh;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
		mesh.setNormalsUpdated(true);
		mesh.setVerticesUpdated(true);
		mesh.setTextureVerticesUpdated(true);
		mesh.setColorsUpdated(true);
		
	}
	
	public void init(GLAutoDrawable drawable){
		// Create Vertex Array.
		GL3 gl = drawable.getGL().getGL3();
		
		gl.glGenBuffers(4, buffers);
       	
        gl.glGenVertexArrays(1, vertexArray);
       	gl.glBindVertexArray(vertexArray.get(0));
       	
       	
    	// Specify how data should be sent to the Program.

       	// VertexAttribArray 0 corresponds with location 0 in the vertex shader.
       	gl.glEnableVertexAttribArray(0);    
       	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers.get(0));
       	gl.glVertexAttribPointer(0, 3, GL.GL_FLOAT, false, 0, 0);

       	// VertexAttribArray 1 corresponds with location 1 in the vertex shader.
       	gl.glEnableVertexAttribArray(1);
       	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers.get(1));
       	gl.glVertexAttribPointer(1, 4, GL.GL_FLOAT, false, 0, 0);
       	
       	//VertexAttribArray 2 corresponds with location 2 in the vertex shader
       	gl.glEnableVertexAttribArray(2);
       	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers.get(2));
       	gl.glVertexAttribPointer(2,3,GL.GL_FLOAT,false,0,0);

       	//TODO add attribs for textures
       	//VertexAttribArray 3 corresponds with location 3 in the vertex shader
       	gl.glEnableVertexAttribArray(3);
       	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers.get(3));
       	gl.glVertexAttribPointer(3,2,GL.GL_FLOAT,false,0,0);
       	
       	
       	initialized = true;
       	
	}
	
	public void updateBuffers(GLAutoDrawable drawable){
		
		GL3 gl = drawable.getGL().getGL3();
		
		if(!initialized){
	       	init(drawable);
		}
		
		
		if(mesh.isVerticesUpdated()){
			
			List<Vector3f> vector3fVertices = mesh.getVertices();
			vertices = new float[vector3fVertices.size()*3];
			int i = 0;
			for(Vector3f vector3fVertice:vector3fVertices){
				vertices[i] = vector3fVertice.x;
				vertices[i+1] = vector3fVertice.y;
				vertices[i+2] = vector3fVertice.z;
				
				i = i + 3;
			} 
					
			vertexFB = FloatBuffer.wrap(vertices);		
			
			gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, buffers.get(0));
	        gl.glBufferData(GL2.GL_ARRAY_BUFFER, 4 * vector3fVertices.size() * 3, vertexFB, GL3.GL_STATIC_DRAW);
			
			mesh.setVerticesUpdated(false);
		}
		
		if(mesh.isColorsUpdated()){
			
			List<Color> meshColors = mesh.getColors();
			colors = new float[meshColors.size()*4];
			int i = 0;
			for(Color meshColor:meshColors){
				colors[i] = meshColor.red;
				colors[i+1] = meshColor.green;
				colors[i+2] = meshColor.blue;
				colors[i+3] = meshColor.alpha;
				
				i = i + 4;
			} 
					
			colorFB = FloatBuffer.wrap(colors);		
			
			gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, buffers.get(1));
	        gl.glBufferData(GL2.GL_ARRAY_BUFFER, 4 * mesh.getColors().size() * 4, colorFB, GL2.GL_STREAM_DRAW);
			
			
			mesh.setColorsUpdated(false);
		}
		
		
		if(mesh.isNormalsUpdated()){
			List<Vector3f> vector3fNormals = mesh.getNormals();
			if(vector3fNormals.size() == mesh.getVertices().size()){
				normals = new float[vector3fNormals.size()*3];
				int i = 0;
				for(Vector3f vector3fNormal:vector3fNormals){
					normals[i] = vector3fNormal.x;
					normals[i+1] = vector3fNormal.y;
					normals[i+2] = vector3fNormal.z;
					
					i = i + 3;
				} 
						
				normalFB = FloatBuffer.wrap(normals);		
				
				gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, buffers.get(2));
		        gl.glBufferData(GL2.GL_ARRAY_BUFFER, 4 * vector3fNormals.size() * 3, normalFB, GL3.GL_STATIC_DRAW);
			}
			else{
				System.out.println("Wrong Normals Size");
			}
			
			mesh.setNormalsUpdated(false);
		}
		
		if(mesh.isTextureVerticesUpdated()){
			List<Vector2f> TextureCoords = mesh.getTextureVertices();
			if(TextureCoords.size() == mesh.getVertices().size()){
				textureCoordinates = new float[TextureCoords.size()*2];
				int i = 0;
				for(Vector2f TextureCoord:TextureCoords){
					textureCoordinates[i] = TextureCoord.x;
					textureCoordinates[i+1] = TextureCoord.y;
					i = i + 2;
				} 
						
				textureFB = FloatBuffer.wrap(textureCoordinates);		
				
				gl.glBindBuffer(GL2.GL_ARRAY_BUFFER, buffers.get(3));
		        gl.glBufferData(GL2.GL_ARRAY_BUFFER, 4 * TextureCoords.size() * 2, textureFB, GL3.GL_STATIC_DRAW);
			}
			else{
				System.out.println("Wrong Texture Coordinate Size");
			}
			
			mesh.setTextureVerticesUpdated(false);
		}
		
		
		
		if(material.isTextureUpdated()){
			
			
			material.setTextureUpdated(false);
		}
		if(material.isColorUpdated()){
			
			
			material.setColorUpdated(false);
		}
		
		
	}
	
	
}
