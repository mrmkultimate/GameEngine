package org.GameEngine.LevelManager;
import java.util.*;

import org.GameEngine.Objects.*;
import org.GameEngine.System.*;

import com.jogamp.opengl.GLAutoDrawable;

public final class Level {
	private static List<GameObject> gameObjects = new ArrayList<GameObject>();
	private static GameObject mainCameraObject;
	
	private static boolean exit = false;
	
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
	
	public static GameObject FindObjectWithCamera(){
		for(GameObject gameObject:gameObjects){
			if(gameObject.getCamera() != null){
				return gameObject;
			}
		}
		return null;
	}
	
	public static List<GameObject> FindObjectsWithCamera(){
		List<GameObject> cameraObjects = new ArrayList<GameObject>();
		for(GameObject gameObject:gameObjects){
			if(gameObject.getCamera() != null){
				cameraObjects.add(gameObject);
			}
		}
		return cameraObjects;
	}
	
	
	public static void Init(){
		if(mainCameraObject == null){
			mainCameraObject = FindObjectWithCamera();
			if(mainCameraObject == null){
				mainCameraObject = new GameObject();
				mainCameraObject.setCamera(new Camera());
				Level.AddGameObject(mainCameraObject);
			}
		}
	}
	
	public static void Update(GLAutoDrawable drawable){
		
		EarlyUpdateScripts();
		
		CollisionCheck();
		
		UpdateScripts();
		
		UpdateTransforms();
		
		LateUpdateScripts();
		
	}
	
	public static void UpdateTransforms(){
		
		//do rigidbody stuff
		for (GameObject gameObject : gameObjects) {
		    //System.out.println(gameObject.getName());
		    //System.out.println(gameObject.getTransform().getName());
			//System.out.println(gameObject.getTransform().getPosition().x);
		    gameObject.getTransform();
		}
		
	}
	
	public static void CollisionCheck(){
		
		//check collision with all objects that have a collider
		for (GameObject gameObject : gameObjects) {
		    Collider col = gameObject.getCollider();
		    if(col != null){
		    	if(col.isActive()){
		    		//TO DO: add collision detection with other active Colliders
		    		
		    		
		    	}
		    }
		    
		}
	}
	
	
	public static void EarlyUpdateScripts(){
		
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
	}
	
	public static void UpdateScripts(){
		
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
		
		
	}
	
	public static void LateUpdateScripts(){
		
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
		
		//render if there is a renderer on the object
		for(GameObject gameObject : gameObjects){
			Renderer r = gameObject.getRenderer();
			if(r != null){
				if(r.isActive()){
					r.render(drawable,program, gameObject.getTransform());
				}
			}
		}
	}

	public static GameObject getMainCameraObject() {
		return mainCameraObject;
	}

	public static void setMainCameraObject(GameObject mainCameraObject) {
		Level.mainCameraObject = mainCameraObject;
	}
	
	
	public static void Exit(){	
		exit = true;
		
	}

	public static boolean isExit() {
		return exit;
	}
}
