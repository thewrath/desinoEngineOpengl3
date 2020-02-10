#version 330

layout(location = 0) in vec2 position;

uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

void main()
{
    gl_Position = projectionMatrix * viewMatrix * vec4(position, 0.0, 1.0);
}

