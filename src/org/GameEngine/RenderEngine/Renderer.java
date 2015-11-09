package org.GameEngine.RenderEngine;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import org.GameEngine.LevelManager.Level;
import org.GameEngine.Math.Matrix4f;
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
	
	private IntBuffer buffers = IntBuffer.allocate(2);
	

	FloatBuffer vertexFB;
	FloatBuffer colorFB;
	FloatBuffer gameObjectPositionFB;
	FloatBuffer gameObjectTransformationMatrixFB;
	FloatBuffer modelViewMatrixFB;
	FloatBuffer projectionMatrixFB;	
	
	float[] vertices;
	float[] colors;
	
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
		
		
	
	
		//TODO: calculate gameobject transformation matrix (for the position, rotation, and scale of the gameobject)
		//scale, then rotate, then translate
		Transform transform = this.gameObject.getTransform();
		
		
		//scale
		Matrix4f scaleMatrix = new Matrix4f().initIdentity();
		scaleMatrix.set(0, 0, transform.getScale().x);
		scaleMatrix.set(1, 1, transform.getScale().y);
		scaleMatrix.set(2, 2, transform.getScale().z);
		
		//rotation
		transform.getRotation().normalize();
		Matrix4f rotationMatrix = new Matrix4f(transform.getRotation().ToRotationMatrix());

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
		
		
		//TODO: add uniforms for light 
		//	vec4 ambientProduct, diffuseProduct, specularProduct, lightPosition
		//	float shininess;
		
		
		
		//TODO: calculate the model view matrix using the camera transform(used for position, rotation, scale of the camera)
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
		transform.getRotation().normalize();
		Matrix4f inverseCameraRotationMatrix = new Matrix4f(cameraTransform.getRotation().ToRotationMatrix()).Transpose();
		
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
		
		gl.glGenBuffers(2, buffers);
       	
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
       	
       	
       	//TODO add attribs for textures
       	
       	
       	//TODO add attribs for normals
       	
     
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
			
			
			mesh.setNormalsUpdated(false);
		}
		
		if(mesh.isTextureVerticesUpdated()){
			
			
			
			
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
