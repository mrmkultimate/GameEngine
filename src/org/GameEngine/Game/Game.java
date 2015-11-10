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
import org.GameEngine.RenderEngine.Mesh;
import org.GameEngine.RenderEngine.Renderer;
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
		List<Color> colors= new ArrayList<Color>();
		
		
		Vector3f a = new Vector3f(-0.5f,-0.5f,0.5f);
		Vector3f b = new Vector3f(0.5f,-0.5f,0.5f);
		Vector3f c = new Vector3f(0.5f,0.5f,0.5f);
		Vector3f d = new Vector3f(-0.5f,0.5f,0.5f);
		Vector3f e = new Vector3f(-0.5f,-0.5f,-0.5f);
		Vector3f f = new Vector3f(0.5f,-0.5f,-0.5f);
		Vector3f g = new Vector3f(0.5f,0.5f,-0.5f);
		Vector3f h = new Vector3f(-0.5f,0.5f,-0.5f);
		
		
		this.AddCube(vertices,a,b,c,d,e,f,g,h);
		
		colors.add(new Color(1, 1, 0, 1));
		colors.add(new Color(1, 1, 1, 1));
		colors.add(new Color(0, 1, 1, 1));
		colors.add(new Color(1, 0, 0, 1));
		colors.add(new Color(0, 0, 1, 1));
		colors.add(new Color(0, 1, 0, 1));
		
		colors.add(new Color(1, 1, 0, 1));
		colors.add(new Color(1, 1, 1, 1));
		colors.add(new Color(0, 1, 1, 1));
		colors.add(new Color(1, 0, 0, 1));
		colors.add(new Color(0, 0, 1, 1));
		colors.add(new Color(0, 1, 0, 1));
		
		colors.add(new Color(1, 1, 0, 1));
		colors.add(new Color(1, 1, 1, 1));
		colors.add(new Color(0, 1, 1, 1));
		colors.add(new Color(1, 0, 0, 1));
		colors.add(new Color(0, 0, 1, 1));
		colors.add(new Color(0, 1, 0, 1));
		
		colors.add(new Color(1, 1, 0, 1));
		colors.add(new Color(1, 1, 1, 1));
		colors.add(new Color(0, 1, 1, 1));
		colors.add(new Color(1, 0, 0, 1));
		colors.add(new Color(0, 0, 1, 1));
		colors.add(new Color(0, 1, 0, 1));
		
		colors.add(new Color(1, 1, 0, 1));
		colors.add(new Color(1, 1, 1, 1));
		colors.add(new Color(0, 1, 1, 1));
		colors.add(new Color(1, 0, 0, 1));
		colors.add(new Color(0, 0, 1, 1));
		colors.add(new Color(0, 1, 0, 1));
		
		colors.add(new Color(1, 1, 0, 1));
		colors.add(new Color(1, 1, 1, 1));
		colors.add(new Color(0, 1, 1, 1));
		colors.add(new Color(1, 0, 0, 1));
		colors.add(new Color(0, 0, 1, 1));
		colors.add(new Color(0, 1, 0, 1));
		
		mesh.setVertices(vertices);
		mesh.setColors(colors);
		
		Renderer renderer = new Renderer();
		
		renderer.setMesh(mesh);
		
		gameObject.setRenderer(renderer);
		
		TestScript testScript = new TestScript();
		gameObject.addScript(testScript);;
		
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
		AddQuad(list,a,b,f,e);
		AddQuad(list,d,c,g,h);
		AddQuad(list,b,f,g,c);
		AddQuad(list,e,f,g,h);
	}
	
}