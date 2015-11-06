package org.GameEngine.Objects;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import org.GameEngine.Math.Vector3f;
import org.GameEngine.RenderEngine.Color;
import org.GameEngine.RenderEngine.Material;
import org.GameEngine.RenderEngine.Mesh;

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
		
	float[] vertices;
	float[] colors;
	
	float[] gameObjectPosition = new float[3];
	
	public Renderer(){ 
		setName("Renderer");
		
	}
	
	
	public void render(GLAutoDrawable drawable, int program, Transform gameObjectTransform){
		
		GL3 gl = drawable.getGL().getGL3();
		
		updateBuffers(drawable);
		
	
		
		gameObjectPosition[0] = gameObjectTransform.getPosition().x;
		gameObjectPosition[1] = gameObjectTransform.getPosition().y;
		gameObjectPosition[2] = gameObjectTransform.getPosition().z;
		
		gameObjectPositionFB = FloatBuffer.wrap(gameObjectPosition);		
		

		//add transform position
		gl.glUniform3fv(gl.glGetUniformLocation(program, "gameObjectPosition"), 1,	gameObjectPositionFB );
		
		//add transform rotation as a matrix
		
		//add transform scale
		
		//use camera transform to get the modelViewMatrix and projectionMatrix
		
		//add modelViewMatrix;
		
	    //add projectionMatrix;
		

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
       	
       	//TODO add uniforms for gameobject transform
       	//position vec3
       	/*
       	// VertexAttribArray 2 corresponds with location 2 in the vertex shader.
       	gl.glEnableVertexAttribArray(2);
       	gl.glBindBuffer(GL.GL_ARRAY_BUFFER, buffers.get(2));
       	gl.glVertexAttribPointer(2, 3, GL.GL_FLOAT, false, 0, 0);
       	*/
       	
       	
       	
       	//rotation mat3
       	
       	//scale vec3
       	
       	
       	
       	
       	//TODO add attribs for textures
       	
       	
       	//TODO add attribs for normals
       	
       	
       	//TODO add attribs for camera location
       	
       	
       	//TODO add attribs for Lighting effects (ambient, diffuse, ... )
       	
       	
       	
        
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
