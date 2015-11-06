#version 330
uniform vec4 gameObjectPosition;
uniform mat4 gameObjectRotation;
uniform mat4 gameObjectScale;

uniform vec4 ambientProduct, diffuseProduct, specularProduct;
uniform vec4 lightPosition;
uniform float shininess;

uniform mat4 modelViewMatrix;
uniform mat4 projectionMatrix;


layout(location=0) in vec3 position;
layout(location=1) in vec4 color;
out vec4 vColor;
void main(void)
{
	gl_Position = vec4(position, 0)+gameObjectPosition;
	vColor = color;
}