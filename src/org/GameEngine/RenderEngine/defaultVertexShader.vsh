#version 330
uniform vec3 gameObjectPosition;
layout(location=0) in vec3 position;
layout(location=1) in vec4 color;
out vec4 vColor;
void main(void)
{
	gl_Position = vec4(position+gameObjectPosition, 1.0);
	vColor = color;
}