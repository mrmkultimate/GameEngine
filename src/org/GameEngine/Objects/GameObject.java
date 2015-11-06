package org.GameEngine.Objects;
import java.util.*;

public class GameObject {

	private String name = "Empty";
	private Transform transform = new Transform();
	private Renderer renderer;
	private Collider collider;
	private List<Script> scripts = new ArrayList<Script>();
	private List<GameObject> children = new ArrayList<GameObject>();
	private Camera camera = null;
	private GameObject parent;
	
	public GameObject(){
		
		transform.setGameObject(this);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Transform getTransform() {
		return transform;
	}

	public void setTransform(Transform transform) {
		this.transform = transform;
		transform.setGameObject(this);
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
		renderer.setGameObject(this);
	}

	public List<Script> getScripts() {
		return scripts;
	}

	public void setScripts(List<Script> scripts) {
		this.scripts = scripts;
		for(Script script:scripts){
			script.setGameObject(this);
		}
	}
	
	public void addScript(Script script){
		this.scripts.add(script);
		script.setGameObject(this);
		
	}
	public void clearScripts(){
		this.scripts.clear();
	}

	public Collider getCollider() {
		return collider;
	}

	public void setCollider(Collider collider) {
		this.collider = collider;
		collider.setGameObject(this);
	}

	public List<GameObject> getChildren() {
		return children;
	}

	public void setChildren(List<GameObject> children) {
		this.children = children;
		//TODO: set parent to this
		
	}
	
	public void addChild(GameObject child){
		this.children.add(child);
		//TODO: set parent to this
		
	}
	
	public void clearChildren(){
		this.children.clear();
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
		camera.setGameObject(this);
	}

	public GameObject getParent() {
		return parent;
	}

	public void setParent(GameObject parent) {
		//TODO: remove from current parent and add as child to new parent
		
		
		this.parent = parent;
	}
	
	
	
}
