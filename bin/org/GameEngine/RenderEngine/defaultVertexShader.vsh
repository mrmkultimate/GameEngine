#version 330

uniform mat4 gameObjectTransformationMatrix;

uniform vec4 ambientProduct, diffuseProduct, specularProduct;
uniform vec4 lightPosition;
uniform float shininess;

uniform mat4 modelViewMatrix;
uniform mat4 projectionMatrix;


layout(location=0) in vec3 position;
layout(location=1) in vec4 color;
layout(location=2) in vec3 normal;
layout(location=3) in vec2 vTexCoord;

out vec4 vColor;

void main(void)
{

	vec3 pos = (modelViewMatrix *gameObjectTransformationMatrix*vec4(position, 1)).xyz;
	vec3 light = (modelViewMatrix*lightPosition).xyz;
	vec3 L = normalize( light - pos );

	
	vec3 E = normalize( -pos );
	vec3 H = normalize( L + E );
	
	vec4 NN = gameObjectTransformationMatrix*vec4(normal,0);

	// Transform vertex normal into eye coordinates
	   
	vec3 N = normalize( (modelViewMatrix*NN).xyz);

	// Compute terms in the illumination equation
	vec4 ambient = ambientProduct;

	float Kd = max( dot(L, N), 0.0 );
	vec4  diffuse = Kd*diffuseProduct;

	float Ks = pow( max(dot(N, H), 0.0), shininess );
	vec4  specular = Ks * specularProduct;
	
	
	if( dot(L, N) < 0.0 ) {
		specular = vec4(0.0, 0.0, 0.0, 1.0);
	} 
	
	vec3 lighting = (ambient+diffuse+specular).xyz;

	gl_Position = projectionMatrix*modelViewMatrix*gameObjectTransformationMatrix*vec4(position, 1);
	vColor = vec4(lighting,1)*color;
	
}