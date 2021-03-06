package org.GameEngine.LevelManager;
import java.util.*;

import org.GameEngine.Objects.*;
import org.GameEngine.RenderEngine.Camera;
import org.GameEngine.RenderEngine.Color;
import org.GameEngine.RenderEngine.Renderer;
import org.GameEngine.System.*;

import com.jogamp.opengl.GLAutoDrawable;

public final class Level {
	private static List<GameObject> gameObjects = new ArrayList<GameObject>();
	private static GameObject mainCameraObject;
	private static List<GameObject> lightObjects = new ArrayList<GameObject>();
	private static List<GameObject> gameObjectsToAdd = new ArrayList<GameObject>();
	
	
	private static boolean exit = false;
	
	private static Color lightAmbient = new Color(0.5f,0.5f,0.5f,1);
	
	private Level(){
		gameObjects.clear();
		gameObjectsToAdd.clear();
	}
	
	public static void testPrint(){
		System.out.println("test");
	}
	
	public static List<GameObject> GetGameObjects(){
		return gameObjects;
	}
	
	public static void AddGameObject(GameObject gameObject){
		gameObjectsToAdd.add(gameObject);
		
		List<Script> scripts = gameObject.getScripts();
		if(!scripts.isEmpty()){
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
		
		AddNewObjectsToLevel();
		
		//System.out.println("GameObjects = ");
		//System.out.println(gameObjects.size());
		
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
			if(!scripts.isEmpty()){
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
	
	
	public static void AddNewObjectsToLevel(){
		for(GameObject gameObject:gameObjectsToAdd){
			gameObjects.add(gameObject);
			if(gameObject.getLight() != null){
				lightObjects.add(gameObject);
			}
		}
		gameObjectsToAdd.clear();
	}
	
	
	public static void Render(GLAutoDrawable drawable, int program){
		
		//render if there is a renderer on the object
		for(GameObject gameObject : gameObjects){
			Renderer r = gameObject.getRenderer();
			if(r != null){
				if(r.isActive()){
					r.render(drawable,program);
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

	public static Color getLightAmbient() {
		return lightAmbient;
	}

	public static void setLightAmbient(Color lightAmbient) {
		Level.lightAmbient = lightAmbient;
	}

	public static List<GameObject> getLightObjects() {
		return lightObjects;
	}

	public static void setLightObjects(List<GameObject> lights) {
		Level.lightObjects = lights;
	}

}
