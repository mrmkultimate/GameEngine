package org.GameEngine.Game;

import java.util.*;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.swing.JFrame;

import org.GameEngine.LevelManager.Level;
import org.GameEngine.Math.*;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GL2ES2;
import com.jogamp.opengl.GL3;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.*;

import org.GameEngine.Objects.*;
import org.GameEngine.RenderEngine.Camera;
import org.GameEngine.RenderEngine.Color;
import org.GameEngine.RenderEngine.Light;
import org.GameEngine.RenderEngine.Material;
import org.GameEngine.RenderEngine.Mesh;
import org.GameEngine.RenderEngine.Renderer;
import org.GameEngine.RenderEngine.Texture;
import org.GameEngine.System.*;

public class Game {
	private float t = 0;
	private float t2 = 0;
	

	public Game() {
        
	}
   

	public void play(){
	

		GameObject gameObject = new GameObject();
		Mesh mesh = new Mesh();
		List<Vector3f> vertices= new ArrayList<Vector3f>();
		List<Vector3f> normals = new ArrayList<Vector3f>();
		List<Color> colors= new ArrayList<Color>();
		List<Vector2f> textCoords = new ArrayList<Vector2f>();
		
		Vector3f a = new Vector3f(-0.5f,-0.5f,0.5f);
		Vector3f b = new Vector3f(0.5f,-0.5f,0.5f);
		Vector3f c = new Vector3f(0.5f,0.5f,0.5f);
		Vector3f d = new Vector3f(-0.5f,0.5f,0.5f);
		Vector3f e = new Vector3f(-0.5f,-0.5f,-0.5f);
		Vector3f f = new Vector3f(0.5f,-0.5f,-0.5f);
		Vector3f g = new Vector3f(0.5f,0.5f,-0.5f);
		Vector3f h = new Vector3f(-0.5f,0.5f,-0.5f);
		
		Vector3f normA = new Vector3f(-1,-1,1).normalize();
		Vector3f normB = new Vector3f(1,-1,1).normalize();
		Vector3f normC = new Vector3f(1,1,1).normalize();
		Vector3f normD = new Vector3f(-1,1,1).normalize();
		Vector3f normE = new Vector3f(-1,-1,-1).normalize();
		Vector3f normF = new Vector3f(1,-1,-1).normalize();
		Vector3f normG = new Vector3f(1,1,-1).normalize();
		Vector3f normH = new Vector3f(-1,1,-1).normalize();
				
		this.AddCube(vertices,a,b,c,d,e,f,g,h);
		//this.AddCubeNormals(normals,normA,normB,normC,normD,normE,normF,normG,normH);
		
		normals = Mesh.CalculateFaceNormals(vertices);
		
		
		
		for(int i = 0; i<vertices.size();i++){
			colors.add(new Color(1, 1, 1, 1));
			textCoords.add(new Vector2f(0,0));
		}
		
	
		mesh.setVertices(vertices);
		mesh.setNormals(normals);
		mesh.setColors(colors);
		mesh.setTextureVertices(textCoords);
		
		//System.out.println(normals.get(0).x);
		//System.out.println(normals.get(0).y);
		//System.out.println(normals.get(0).z);
		
		Renderer renderer = new Renderer();
		
		Material material = new Material();
		material.setAmbient(new Color(0,0,0,1f));
		material.setDiffuse(new Color(0,0.5f,0,1f));
		material.setSpecular(new Color(0.4f,0.4f,0.4f,1));
		material.setShininess(4);
		material.setTexture(new Texture());
		
		renderer.setMesh(mesh);
		renderer.setMaterial(material);
		
		gameObject.setRenderer(renderer);
		
		TestScript testScript = new TestScript();
		gameObject.addScript(testScript);
		
		Level.AddGameObject(gameObject);
		
		
		GameObject cameraObject = new GameObject();
		cameraObject.setCamera(new Camera());
		cameraObject.getCamera().setFarPlane(100);
		cameraObject.getCamera().setNearPlane(0.01f);
		cameraObject.getCamera().setFOV((float)Math.PI/4);
		cameraObject.getCamera().setAspectRatio(1);
		
		cameraObject.getTransform().setPosition(new Vector3f(0,0,-3.0f));

		Level.setMainCameraObject(cameraObject);
		
		Level.AddGameObject(cameraObject);
		
		
		GameObject lightObject = new GameObject();
		lightObject.getTransform().setPosition(new Vector3f(0,0,-5));
		lightObject.setLight(new Light());
		lightObject.getLight().setDiffuse(new Color(1,1,1,1));
		lightObject.getLight().setSpecular(new Color(1,1,1,1));
		
		
//		Renderer lightRenderer = new Renderer();
//		Mesh lightMesh = new Mesh();
//		lightMesh.setColors(colors);
//		lightMesh.setVertices(vertices);
//		lightMesh.setNormals(normals);
//		Material lightMaterial = new Material();
//		lightMaterial.setAmbient(new Color(1,1,1,1));
//		lightMaterial.setDiffuse(new Color(1,1,1,1));
//		lightMaterial.setSpecular(new Color(1,1,1,1));
//		lightMaterial.setShininess(4);
//		
//		lightRenderer.setMesh(lightMesh);
//		lightRenderer.setMaterial(lightMaterial);
//		lightObject.setRenderer(lightRenderer);
	
		Level.AddGameObject(lightObject);
		
		Level.setLightAmbient(new Color(0.7f,0.7f,0.7f,1));
		
	}
	
	


	private void AddQuad(List<Vector3f> list, Vector3f a, Vector3f b, Vector3f c, Vector3f d){
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(c);
		list.add(d);
		list.add(a);
	}
	
	private void AddCube(List<Vector3f> list, Vector3f a,Vector3f b,Vector3f c,Vector3f d,Vector3f e,Vector3f f,Vector3f g,Vector3f h){
		AddQuad(list,a,b,c,d);
		AddQuad(list,a,d,h,e);
		AddQuad(list,a,e,f,b);
		AddQuad(list,d,c,g,h);
		AddQuad(list,b,f,g,c);
		AddQuad(list,e,h,g,f);
	}
	
	private void AddCubeNormals(List<Vector3f> list, Vector3f a, Vector3f b, Vector3f c, Vector3f d,Vector3f e, Vector3f f, Vector3f g, Vector3f h) {
		AddQuadNormals(list,a,b,c,d);
		AddQuadNormals(list,a,d,h,e);
		AddQuadNormals(list,a,e,f,b);
		AddQuadNormals(list,d,c,g,h);
		AddQuadNormals(list,b,f,g,c);
		AddQuadNormals(list,e,h,g,f);
		
	}
	private void AddQuadNormals(List<Vector3f> list, Vector3f a, Vector3f b, Vector3f c, Vector3f d){
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(c);
		list.add(d);
		list.add(a);
	}
	
}