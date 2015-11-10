package org.GameEngine.Game;

import java.util.ArrayList;
import java.util.List;

import org.GameEngine.LevelManager.Level;
import org.GameEngine.Math.Quaternion;
import org.GameEngine.Math.Vector3f;
import org.GameEngine.Objects.GameObject;
import org.GameEngine.Objects.Script;
import org.GameEngine.RenderEngine.Color;
import org.GameEngine.RenderEngine.Mesh;
import org.GameEngine.RenderEngine.Renderer;
import org.GameEngine.System.Key;
import org.GameEngine.System.Keyboard;
import org.GameEngine.System.Time;

public class TestScript extends Script {
	
	private float t2 = 0;
	
	private float tx = 0;
	private float ty = 0;
	private float tz = 0;
	
	private float movementSpeed = 1.0f;
	
	@Override
	public void Update(){
		
		if(Keyboard.keyHeld(Key.A)){
			this.gameObject.getTransform().Translate(new Vector3f(-movementSpeed*Time.DeltaTime(),0,0));
			System.out.println(this.gameObject.getTransform().getPosition().x);
		}	
		if(Keyboard.keyHeld(Key.S)){
			this.gameObject.getTransform().Translate(new Vector3f(0,-movementSpeed*Time.DeltaTime(),0));
			System.out.println(this.gameObject.getTransform().getPosition().y);
		}	
		if(Keyboard.keyHeld(Key.D)){
			this.gameObject.getTransform().Translate(new Vector3f(movementSpeed*Time.DeltaTime(),0,0));
			System.out.println(this.gameObject.getTransform().getPosition().x);
		}	
		if(Keyboard.keyHeld(Key.W)){
			this.gameObject.getTransform().Translate(new Vector3f(0,movementSpeed*Time.DeltaTime(),0));
			System.out.println(this.gameObject.getTransform().getPosition().y);
		}	
		if(Keyboard.keyHeld(Key.X)){
			this.gameObject.getTransform().Translate(new Vector3f(0,0,-movementSpeed*Time.DeltaTime()));
			System.out.println(this.gameObject.getTransform().getPosition().z);
		}	
		if(Keyboard.keyHeld(Key.C)){
			this.gameObject.getTransform().Translate(new Vector3f(0,0,movementSpeed*Time.DeltaTime()));
			System.out.println(this.gameObject.getTransform().getPosition().z);
		}	
		
		GameObject camera = Level.getMainCameraObject();
		
		if(Keyboard.keyHeld(Key.J)){
			camera.getTransform().Translate(new Vector3f(-movementSpeed*Time.DeltaTime(),0,0));
			System.out.println(camera.getTransform().getPosition().x);
		}	
		if(Keyboard.keyHeld(Key.K)){
			camera.getTransform().Translate(new Vector3f(0,-movementSpeed*Time.DeltaTime(),0));
			System.out.println(camera.getTransform().getPosition().y);
		}	
		if(Keyboard.keyHeld(Key.L)){
			camera.getTransform().Translate(new Vector3f(movementSpeed*Time.DeltaTime(),0,0));
			System.out.println(camera.getTransform().getPosition().x);
		}	
		if(Keyboard.keyHeld(Key.I)){
			camera.getTransform().Translate(new Vector3f(0,movementSpeed*Time.DeltaTime(),0));
			System.out.println(camera.getTransform().getPosition().y);
		}	
		if(Keyboard.keyHeld(Key.M)){
			camera.getTransform().Translate(new Vector3f(0,0,-movementSpeed*Time.DeltaTime()));
			System.out.println(camera.getTransform().getPosition().z);
		}	
		if(Keyboard.keyHeld(Key.N)){
			camera.getTransform().Translate(new Vector3f(0,0,movementSpeed*Time.DeltaTime()));
			System.out.println(camera.getTransform().getPosition().z);
		}	
		
		
		if(Keyboard.keyHeld(Key.Space)){
		
			tx +=  1f*Time.DeltaTime();
			ty += 2f*Time.DeltaTime();
			tz += 0.5f*Time.DeltaTime();

			List<Color> previousColors = this.gameObject.getRenderer().getMesh().getColors();
			
			List<Color> newColors = new ArrayList<Color>();
			
			for(int i = 0; i<previousColors.size(); i++){
				newColors.add(new Color((float)(Math.sin(tx)*Math.sin(tx)),(float)(Math.sin(tx + i)*Math.sin(tx + i)),(float)(Math.sin(tx -i)*Math.sin(tx -i)),1.0f));		
			}
			
			this.gameObject.getTransform().getRotation().setEulerAngles(new Vector3f(tx,ty,tz));

			this.gameObject.getRenderer().getMesh().setColors(newColors);
			
			System.out.println("object quat");
			System.out.println(this.gameObject.getTransform().getRotation().getQuaternion().w);	
			System.out.println(this.gameObject.getTransform().getRotation().getQuaternion().x);
			System.out.println(this.gameObject.getTransform().getRotation().getQuaternion().y);
			System.out.println(this.gameObject.getTransform().getRotation().getQuaternion().z);
			
		}
		
		if(Keyboard.keyHeld(Key.H)){
			
			t2 += 0.1f*Time.DeltaTime();

			
			camera.getTransform().getRotation().setEulerAngles(new Vector3f(0,t2,0));
			
			System.out.println("camera quat");
			System.out.println(camera.getTransform().getRotation().getQuaternion().w);
			System.out.println(camera.getTransform().getRotation().getQuaternion().x);
			System.out.println(camera.getTransform().getRotation().getQuaternion().y);
			System.out.println(camera.getTransform().getRotation().getQuaternion().z);
			
		}
		
		
		if(Keyboard.keyHeld(Key.Escape)){
			//Exit program
			Level.Exit();
			
		}
		
		if(Keyboard.keyHeld(Key.G)){
		
			GameObject newGameObject = new GameObject();
			Mesh mesh = new Mesh();
			List<Vector3f> vertices= new ArrayList<Vector3f>();
			List<Color> colors= new ArrayList<Color>();
			
			vertices = this.gameObject.getRenderer().getMesh().getVertices();
			colors = this.gameObject.getRenderer().getMesh().getColors();
	
			mesh.setVertices(vertices);
			mesh.setColors(colors);		
	
			Renderer renderer = new Renderer();
			
			renderer.setMesh(mesh);
			
			newGameObject.setRenderer(renderer);
	
			newGameObject.getTransform().Translate(new Vector3f(gameObject.getTransform().getPosition().x,gameObject.getTransform().getPosition().y,gameObject.getTransform().getPosition().z));;
			
			
			//TestScript testScript = new TestScript();
			//newGameObject.addScript(testScript);
			
			Level.AddGameObject(newGameObject);
			
		}
	}
	
}
