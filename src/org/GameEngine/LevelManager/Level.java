package org.GameEngine.LevelManager;
import java.util.*;

import org.GameEngine.Objects.*;
import org.GameEngine.System.*;

import com.jogamp.opengl.GLAutoDrawable;

public final class Level {
	static List<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private Level(){
		gameObjects.clear();
	}
	
	public static void testPrint(){
		System.out.println("test");
	}
	
	public static List<GameObject> GetGameObjects(){
		return gameObjects;
	}
	
	public static void AddGameObject(GameObject gameObject){
		gameObjects.add(gameObject);
		List<Script> scripts = gameObject.getScripts();
		if(scripts != null){
			if(scripts.size()>0){
				for(Script script:scripts){
					script.Start();
				}
			}
		}
	}
	
	
	
	public static void Update(GLAutoDrawable drawable){
		
		

		UpdateTransforms();	
		
		CollisionCheck();
		
		UpdateScripts();
		
		
	}
	
	public static void UpdateTransforms(){
		
		for (GameObject gameObject : gameObjects) {
		    //System.out.println(gameObject.getName());
		    //System.out.println(gameObject.getTransform().getName());
			//System.out.println(gameObject.getTransform().getPosition().x);
		    gameObject.getTransform();
		}
		
	}
	
	public static void CollisionCheck(){
		for (GameObject gameObject : gameObjects) {
		    Collider col = gameObject.getCollider();
		    if(col != null){
		    	if(col.isActive()){
		    		//TO DO: add collision detection with other active Colliders
		    		
		    		
		    	}
		    }
		    
		}
	}
	
	public static void UpdateScripts(){
		//EarlyUpdate
		for(GameObject gameObject : gameObjects){
			List<Script> scripts = gameObject.getScripts();
			if(scripts != null){
				if(scripts.size()>0){
					for(Script script : scripts){
						if(script.isActive()){
							script.EarlyUpdate();
						}
					}
				}
			}
		}
		//Update
		for(GameObject gameObject : gameObjects){
			List<Script> scripts = gameObject.getScripts();
			if(scripts != null){
				if(scripts.size()>0){
					for(Script script : scripts){
						if(script.isActive()){
							script.Update();
						}
					}
				}
			}
		}
		
		//LateUpdate
		for(GameObject gameObject : gameObjects){
			List<Script> scripts = gameObject.getScripts();
			if(scripts != null){
				if(scripts.size()>0){
					for(Script script : scripts){
						if(script.isActive()){
							script.LateUpdate();
						}
					}
				}
			}
		}
	}
	
	public static void Render(GLAutoDrawable drawable, int program){
		for(GameObject gameObject : gameObjects){
			Renderer r = gameObject.getRenderer();
			if(r != null){
				if(r.isActive()){
					r.render(drawable,program, gameObject.getTransform());
				}
			}
		}
		
		
		
	}
}
