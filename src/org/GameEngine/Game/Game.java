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
	
		GameObject g = new GameObject();
		Mesh mesh = new Mesh();
		List<Vector3f> vertices= new ArrayList<Vector3f>();
		List<Color> colors= new ArrayList<Color>();
		
		vertices.add(new Vector3f(-0.5f, -0.5f, 0));
		vertices.add(new Vector3f(0.5f, -0.5f, 0));
		vertices.add(new Vector3f(0.5f, 0.5f, 0));
		vertices.add(new Vector3f(0.5f, 0.5f, 0));
		vertices.add(new Vector3f(-0.5f, 0.5f, 0));
		vertices.add(new Vector3f(-0.5f, -0.5f, 0));
		
		vertices.add(new Vector3f(-0.5f, -0.5f, 0));
		vertices.add(new Vector3f(-0.5f, -0.5f, 1));
		vertices.add(new Vector3f(-0.5f, 0.5f, 1));
		vertices.add(new Vector3f(-0.5f, -0.5f, 0));
		vertices.add(new Vector3f(-0.5f, 0.5f, 1));
		vertices.add(new Vector3f(-0.5f, 0.5f, 0));
		
		vertices.add(new Vector3f(-0.5f, -0.5f, 0));
		vertices.add(new Vector3f(-0.5f, -0.5f, 1));
		vertices.add(new Vector3f(0.5f, -0.5f, 1));
		vertices.add(new Vector3f(-0.5f, -0.5f, 0));
		vertices.add(new Vector3f(0.5f, -0.5f, 1));
		vertices.add(new Vector3f(0.5f, -0.5f, 0));
		
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
		
		g.setRenderer(renderer);
		
		TestScript testScript = new TestScript();
		g.addScript(testScript);;
		
		Level.AddGameObject(g);
		
		
		GameObject cameraObject = new GameObject();
		cameraObject.setCamera(new Camera());
		cameraObject.getCamera().setFarPlane(100);
		cameraObject.getCamera().setNearPlane(1);
		cameraObject.getCamera().setFOV((float)Math.PI/4);
		cameraObject.getCamera().setAspectRatio(1);
		
		cameraObject.getTransform().setPosition(new Vector3f(0,0,-3.0f));

		Level.setMainCameraObject(cameraObject);
		
		Level.AddGameObject(cameraObject);
		

	}
}