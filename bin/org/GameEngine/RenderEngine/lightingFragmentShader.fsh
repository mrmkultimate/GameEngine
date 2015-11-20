#version 330
#define MAX_LIGHTS_NUMBER 8

in vec3 fNormal;
in vec2 fTexCoord;
in vec3 fCoord;
in vec4 vColor;
out vec4 fColor;

struct Light {
	vec3 ambient;
	vec3 diffuse;
	vec3 specular;
	vec4 position;
	vec3 direction;
	float cutoff;
	float exponent;
};

struct Material {  
  vec3  ambient;         
  vec3  diffuse;         
  vec3  specular;        
  float shininess;
};

uniform Material material;
uniform Light lights [MAX_LIGHTS_NUMBER];
uniform vec3 camera_position;
uniform bool      useTexture;  
uniform sampler2D texSampler;  


vec3 directionLight(Light light){
	vec3 L = normalize(light.position.xyz);
	vec3 s = normalize(L+normalize((camera_position-fCoord)));
	float diffuse = max(0., dot(L, normalize(fNormal)));
	float specular = max(0., pow(dot(s, normalize(fNormal)), material.shininess));

	vec3 ret = vec3(0.0f);
	ret += material.ambient * light.ambient;
	ret += material.diffuse * light.diffuse * diffuse;
	ret += material.specular * light.specular * specular;

	return ret;
}

vec3 pointLight(Light light){
	vec3 L = normalize(light.position.xyz-fCoord);
	vec3 s = normalize(L+normalize((camera_position-fCoord)));
	float diffuse = max(0., dot(L, normalize(fNormal)));
	float specular = max(0., pow(dot(s, normalize(fNormal)), material.shininess));

	vec3 ret = vec3(0.0f);
	ret += material.ambient * light.ambient;
	ret += material.diffuse * light.diffuse * diffuse;
	ret += material.specular * light.specular * specular;

	return ret;
}

vec3 reflectionLight(Light light){
	vec3 L = normalize(light.position.xyz-fCoord);
	vec3 s = normalize(L+normalize((camera_position-fCoord)));
	float diffuse = max(0., dot(L, normalize(fNormal)));
	float specular = max(0., pow(dot(s, normalize(fNormal)), material.shininess));

	vec3 ret = vec3(0.0f);
	ret += material.ambient * light.ambient;
	ret += material.diffuse * light.diffuse * diffuse;
	ret += material.specular * light.specular * specular;

	float center = 0.;
	vec3 v = -L;
	float cos_a = dot(v, light.direction);
	if (cos_a >= light.cutoff)
		center = pow(max(0., cos_a), light.exponent);

	return ret*center;

}

vec3 computeLight(int light_index){
	Light light = lights[light_index];
	if(light.position == vec4(0.0)) { return vec3(0.0);}
	if(light.position.w == 0.0 ) {return directionLight(light);}
	if(light.cutoff == 0) {return pointLight(light);}
	return reflectionLight(light);
}


void main() {
	// adds color
	fColor = vColor;
	if(useTexture) {
		color = texture(texSampler, fTexCoord);
	}
	
	vec3 lighting = vec3(0.);
	for(int i = 0; i < MAX_LIGHTS_NUMBER; i++) {
		lighting += computeLight(i);
	}
	fColor = vec4(lighting,1.) * fColor;
}