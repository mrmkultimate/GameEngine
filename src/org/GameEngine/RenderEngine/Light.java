package org.GameEngine.RenderEngine;

import org.GameEngine.Objects.Component;

public class Light extends Component {

	private Color diffuse = new Color(1,1,1,1);
	private Color specular = new Color(0,0,0,1);
	
	public Light(){
		setName("Light");
	}

	public Color getDiffuse() {
		return diffuse;
	}

	public void setDiffuse(Color diffuse) {
		this.diffuse = diffuse;
	}

	public Color getSpecular() {
		return specular;
	}

	public void setSpecular(Color specular) {
		this.specular = specular;
	}
	
}
