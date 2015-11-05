package org.GameEngine.Objects;
import java.util.*;

public class GameObject {

	private String name = "Empty";
	private Transform transform = new Transform();
	private Renderer renderer;
	private Collider collider;
	private List<Script> scripts;
	private List<GameObject> children;
	
	
	public GameObject(){
		renderer = null;
		
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
	}

	public Renderer getRenderer() {
		return renderer;
	}

	public void setRenderer(Renderer renderer) {
		this.renderer = renderer;
	}

	public List<Script> getScripts() {
		return scripts;
	}

	public void setScripts(List<Script> scripts) {
		this.scripts = scripts;
	}
	
	public void addScript(Script script){
		this.scripts.add(script);
	}
	public void clearScripts(){
		this.scripts.clear();
	}

	public Collider getCollider() {
		return collider;
	}

	public void setCollider(Collider collider) {
		this.collider = collider;
	}

	public List<GameObject> getChildren() {
		return children;
	}

	public void setChildren(List<GameObject> children) {
		this.children = children;
	}
	
	public void addChild(GameObject child){
		this.children.add(child);
	}
	
	public void clearChildren(){
		this.children.clear();
	}
	
	
	
}
