#version 330

in vec3 extendedColours;
out vec4 fragColor;

void main()
{
	fragColor = vec4(extendedColours, 1.0);
}