#version 330

uniform mat4 gameObjectTransformationMatrix;

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
	gl_Position = gameObjectTransformationMatrix*vec4(position, 1);
	vColor = color;
}