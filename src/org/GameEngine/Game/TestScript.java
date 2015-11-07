package org.GameEngine.Game;

import java.util.ArrayList;
import java.util.List;

import org.GameEngine.LevelManager.Level;
import org.GameEngine.Math.Quaternion;
import org.GameEngine.Math.Vector3f;
import org.GameEngine.Objects.GameObject;
import org.GameEngine.Objects.Script;
import org.GameEngine.RenderEngine.Color;
import org.GameEngine.System.Key;
import org.GameEngine.System.Keyboard;

public class TestScript extends Script {
	
	private float t = 0;
	private float t2 = 0;
	
	@Override
	public void Update(){
		
		if(Keyboard.keyHit(Key.A)){
			this.gameObject.getTransform().Translate(new Vector3f(-0.1f,0,0));
			System.out.println(this.gameObject.getTransform().getPosition().x);
		}	
		if(Keyboard.keyHit(Key.S)){
			this.gameObject.getTransform().Translate(new Vector3f(0,-0.1f,0));
			System.out.println(this.gameObject.getTransform().getPosition().y);
		}	
		if(Keyboard.keyHit(Key.D)){
			this.gameObject.getTransform().Translate(new Vector3f(0.1f,0,0));
			System.out.println(this.gameObject.getTransform().getPosition().x);
		}	
		if(Keyboard.keyHit(Key.W)){
			this.gameObject.getTransform().Translate(new Vector3f(0,0.1f,0));
			System.out.println(this.gameObject.getTransform().getPosition().y);
		}	
		
		
		if(Keyboard.keyHeld(Key.Space)){
		
			t2 = t2 + 0.01f;

			List<Color> previousColors = this.gameObject.getRenderer().getMesh().getColors();
			
			List<Color> newColors = new ArrayList<Color>();
			
			for(int i = 0; i<previousColors.size(); i++){
				newColors.add(new Color((float)(Math.sin(t2)*Math.sin(t2)),(float)(Math.sin(t2 + i)*Math.sin(t2 + i)),(float)(Math.sin(t2 -i)*Math.sin(t2 -i)),1.0f));		
			}
			
			Quaternion quat = this.gameObject.getTransform().getRotation();
			
	
			this.gameObject.getTransform().setRotation(new Quaternion((float)Math.sin(t2),(float)-Math.sin(t2),(float)Math.sin(t2),0.5f).normalize());
			
			
			
			this.gameObject.getRenderer().getMesh().setColors(newColors);
			
		}
		
		System.out.println(this.gameObject.getTransform().getRotation().w);
		
		/*
		GameObject g2 = gameObjects.get(1);
		
		List<Color> previousColors = g2.getRenderer().getMesh().getColors();
		
		List<Color> newColors = new ArrayList<Color>();
		
		for(int i = 0; i<previousColors.size(); i++){
			newColors.add(new Color((float) (Math.sin(t + i)*Math.sin(t + i)),(float)(Math.sin(t + 3.14/4 + i)*Math.sin(t + 3.14/4 + i)),(float)(Math.sin(t + 3.14/2 + i)*Math.sin(t + 3.14/2 + i)),1.0f));		
		}
		t = t + 0.02f;
		
		g2.getRenderer().getMesh().setColors(newColors);
		
		*/
		
		
		if(Keyboard.keyHeld(Key.Escape)){
			//Exit program
			Level.Exit();
			
		}
		
	}
	
}
