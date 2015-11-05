package org.GameEngine.RenderEngine;

import java.util.*;

import org.GameEngine.Math.*;

public class Mesh {

	private List<Vector3f> vertices;
	private List<Integer> verticesIndex;
	
	private List<Vector3f> normals;
	
	private List<Vector2f> textureVertices;
	
	private List<Color> colors;
	
	
	private boolean verticesUpdated = false;
	private boolean normalsUpdated = false;
	private boolean textureVerticesUpdated = false;
	private boolean colorsUpdated = false;
	
	
	public List<Vector3f> getVertices() {
		return vertices;
	}
	public void setVertices(List<Vector3f> vertices) {
		this.vertices = vertices;
		verticesUpdated = true;
	}
	public void setVertices2D(List<Vector2f> vertices){
		this.vertices.clear();
		for(Vector2f vertice:vertices){
			this.vertices.add(new Vector3f(vertice.x,vertice.y,0));
		}
		verticesUpdated = true;
		
	}
	public List<Vector3f> getNormals() {
		return normals;
	}
	public void setNormals(List<Vector3f> normals) {
		this.normals = normals;
		normalsUpdated = true;
	}
	public List<Vector2f> getTextureVertices() {
		return textureVertices;
	}
	public void setTextureVertices(List<Vector2f> uvVertices) {
		this.textureVertices = uvVertices;
		textureVerticesUpdated = true;
	}
	public List<Integer> getVerticesIndex() {
		return verticesIndex;
	}
	public void setVerticesIndex(List<Integer> verticesIndex) {
		this.verticesIndex = verticesIndex;
		verticesUpdated = true;
	}
	
	
	public boolean isVerticesUpdated() {
		return verticesUpdated;
	}
	public void setVerticesUpdated(boolean verticesUpdated) {
		this.verticesUpdated = verticesUpdated;
	}
	public boolean isNormalsUpdated() {
		return normalsUpdated;
	}
	public void setNormalsUpdated(boolean normalsUpdated) {
		this.normalsUpdated = normalsUpdated;
	}
	public boolean isTextureVerticesUpdated() {
		return textureVerticesUpdated;
	}
	public void setTextureVerticesUpdated(boolean textureVerticesUpdated) {
		this.textureVerticesUpdated = textureVerticesUpdated;
	}
	public List<Color> getColors() {
		return colors;
	}
	public void setColors(List<Color> colors) {
		this.colors = colors;
		colorsUpdated = true;
	}
	public boolean isColorsUpdated() {
		return colorsUpdated;
	}
	public void setColorsUpdated(boolean colorsUpdated) {
		this.colorsUpdated = colorsUpdated;
	}
	
}
