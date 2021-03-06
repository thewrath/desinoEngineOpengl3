#version 330

layout(location = 0) in vec2 position;
layout(location = 1) in vec2 texCoord;

uniform mat4 viewMatrix;
uniform mat4 projectionMatrix;

uniform mat4 texRegionMatrix;
uniform mat4 texProjectionMatrix;

out vec2 outTexCoord;

void main()
{
    gl_Position = projectionMatrix * viewMatrix * vec4(position, 0.0, 1.0);
    outTexCoord = (texProjectionMatrix * texRegionMatrix * vec4(texCoord, 0.0, 1.0)).xy;
}

