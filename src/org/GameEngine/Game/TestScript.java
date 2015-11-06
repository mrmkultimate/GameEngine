package org.GameEngine.Game;

import java.util.ArrayList;
import java.util.List;

import org.GameEngine.LevelManager.Level;
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
		List<GameObject> gameObjects = Level.GetGameObjects();
		GameObject g = gameObjects.get(0);
		
		if(Keyboard.keyHit(Key.A)){
			g.getTransform().Translate(new Vector3f(-0.1f,0,0));
			System.out.println(g.getTransform().getPosition().y);
		}	
		if(Keyboard.keyHit(Key.S)){
			g.getTransform().Translate(new Vector3f(0,-0.1f,0));
			System.out.println(g.getTransform().getPosition().y);
		}	
		if(Keyboard.keyHit(Key.D)){
			g.getTransform().Translate(new Vector3f(0.1f,0,0));
			System.out.println(g.getTransform().getPosition().y);
		}	
		if(Keyboard.keyHit(Key.W)){
			g.getTransform().Translate(new Vector3f(0,0.1f,0));
			System.out.println(g.getTransform().getPosition().y);
		}	
		
		
		if(Keyboard.keyHit(Key.Space)){
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
			Level.Exit();
			
		}
		
	}
	
}
