package org.GameEngine.Objects;

public class Component {
	protected String name = "Component";
	protected boolean active = true;
	
	protected GameObject gameObject; //the object the script is attached to
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public GameObject getGameObject() {
		return gameObject;
	}

	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
}
