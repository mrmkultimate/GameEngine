package org.GameEngine.RenderEngine;

import java.util.*;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;

import java.io.BufferedReader;
import java.io.FileReader;
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
import org.GameEngine.System.*;

public class RenderEngine extends JFrame implements GLEventListener {
    private static final long serialVersionUID = 1L;
    final private int width = 800;
	final private int height = 600;
	
	private float t = 0;
	private float t2 = 0;
	
	//OpenGL program
	private int program;
	

	public RenderEngine() {
		super("Minimal OpenGL");
        
	}
   

	public void start(){
	
		
	    GLProfile profile = GLProfile.get(GLProfile.GL3);
	    GLCapabilities capabilities = new GLCapabilities(profile);
	
        GLCanvas canvas = new GLCanvas(capabilities);
        
        canvas.addGLEventListener(this);
        
    	Input input = new Input();

    	this.addKeyListener(input);
    	canvas.addKeyListener(input);

	    
	    this.setName("Minimal OpenGL");
        this.getContentPane().add(canvas);

        
	    this.setSize(width, height);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
	    this.setResizable(true);
	    this.setAutoRequestFocus(true);
	    
	    
        canvas.requestFocusInWindow();
        

        Animator animator = new Animator(canvas);
        //animator.add(canvas);
        animator.start();
		
		
		
		

	}
	
	public void update(GLAutoDrawable drawable){
		
		//TO DO: add time to deltaTime
		
		List<GameObject> gameObjects = Level.GetGameObjects();
		GameObject g = gameObjects.get(0);
		
		Keyboard.Update();
		Level.Update(drawable);
		if(Keyboard.keyHit(Key.S)){
			g.getTransform().Translate(new Vector3f(0,-0.1f,0));
			//System.out.println(g.getTransform().getPosition().x);
			System.out.println(g.getTransform().getPosition().y);
		}	
		
		
		if(Keyboard.keyHit(Key.W)){
			/*
			square[0] = square[0]+0.1f;	 
			System.out.println(square[0]);
			*/
			
			t2 = t2 + 0.1f;

			List<Color> previousColors = g.getRenderer().getMesh().getColors();
			
			List<Color> newColors = new ArrayList<Color>();
			
			for(int i = 0; i<previousColors.size(); i++){
				newColors.add(new Color((float)(Math.sin(t2)*Math.sin(t2)),(float)(Math.sin(t2 + i)*Math.sin(t2 + i)),(float)(Math.sin(t2 -i)*Math.sin(t2 -i)),1.0f));		
			}
			
			
			g.getRenderer().getMesh().setColors(newColors);
			
		}
		
		
		GameObject g2 = gameObjects.get(1);
		
		List<Color> previousColors = g2.getRenderer().getMesh().getColors();
		
		List<Color> newColors = new ArrayList<Color>();
		
		for(int i = 0; i<previousColors.size(); i++){
			newColors.add(new Color((float) (Math.sin(t + i)*Math.sin(t + i)),(float)(Math.sin(t + 3.14/4 + i)*Math.sin(t + 3.14/4 + i)),(float)(Math.sin(t + 3.14/2 + i)*Math.sin(t + 3.14/2 + i)),1.0f));		
		}
		t = t + 0.02f;
		
		g2.getRenderer().getMesh().setColors(newColors);
		
		
		
		
		if(Keyboard.keyHeld(Key.Escape)){
			//Exit program
			drawable.getAnimator().stop();
			this.dispose(drawable);
		    System.exit(0);
			
		}
		
	}
	
   
	@Override
	public void display(GLAutoDrawable drawable) {
		
		this.update(drawable);
		this.render(drawable);
		
	}
	
	public void render(GLAutoDrawable drawable){
		
		GL3 gl = drawable.getGL().getGL3();
		gl.glClear(GL3.GL_COLOR_BUFFER_BIT | GL3.GL_DEPTH_BUFFER_BIT);
		
		
		// call your draw code here
		gl.glUseProgram(program);	
		Level.Render(drawable,program);    
		gl.glFlush();
	}
 
	@Override
	public void dispose(GLAutoDrawable drawable) {
		
	}

	@Override
   	public void init(GLAutoDrawable drawable) {
       	GL3 gl = drawable.getGL().getGL3();
       	drawable.getGL().setSwapInterval(1);
 	
        gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);


        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glClearDepthf(10.0f);
        gl.glClearColor(0.8f, 0.6f, 0.8f, 1.0f);
        gl.glDepthFunc(GL2.GL_LEQUAL);
	
       	
       	// Create program.
       	program = gl.glCreateProgram();

       	
       	
       	//TODO add attribs for gameobject transform
       	//position vec3
       	
       	//rotation mat3
       	
       	//scale vec3
       	
       	
       	
       	
       	//TODO add attribs for textures
       	
       	
       	//TODO add attribs for normals
       	
       	
       	//TODO add attribs for camera location
       	
       	
       	//TODO add attribs for Lighting effects (ambient, diffuse, ... )
       	
       	
       	// Create vertexShader.
       	int vertexShader = gl.glCreateShader(GL2ES2.GL_VERTEX_SHADER);
       	String[] vertexShaderSource = new String[1];
       	vertexShaderSource[0] = loadFile("src/org/GameEngine/RenderEngine/defaultVertexShader.vsh");

       	gl.glShaderSource(vertexShader, 1, vertexShaderSource, null);
       	gl.glCompileShader(vertexShader);

       	// Create and fragment shader.
       	int fragmentShader = gl.glCreateShader(GL2ES2.GL_FRAGMENT_SHADER);
       	String[] fragmentShaderSource = new String[1];
       	fragmentShaderSource[0] = loadFile("src/org/GameEngine/RenderEngine/defaultFragmentShader.fsh");
       	/*
       	fragmentShaderSource[0] = "#version 330\n" +
       	    "in vec4 vColor;\n" +
       	    "out vec4 fColor;\n" +
       	    "void main(void)\n" +
       	    "{\n" +
       	    "fColor = vColor;\n" +
       	    "}\n";
       	*/
       	gl.glShaderSource(fragmentShader, 1, fragmentShaderSource, null);
       	gl.glCompileShader(fragmentShader);

       	// Attach shaders to program.
       	gl.glAttachShader(program, vertexShader);
       	gl.glAttachShader(program, fragmentShader);
       	gl.glLinkProgram(program);
       	
       	
   	}
 
   	@Override
   	public void reshape(GLAutoDrawable drawable, int x, int y, int width,
		   int height) {
   		
   	}
   	
   	
   	/**
	 * Load a text file and return its contents as a String.
	 */
	private String loadFile(String filename)
	{
		StringBuilder vertexCode = new StringBuilder();
		String line = null ;
		try
		{
		    BufferedReader reader = new BufferedReader(new FileReader(filename));
		    while( (line = reader.readLine()) !=null )
		    {
		    	vertexCode.append(line);
		    	vertexCode.append('\n');
		    }
		}
		catch(Exception e)
		{
			throw new IllegalArgumentException("unable to load shader from file ["+filename+"]", e);
		}
 
		return vertexCode.toString();
	}
   	
   	
}