#version 400 core

in vec3 position;
in vec3 texcoord;

out vec2 fragTexCoord;

void main()
{
    gl_Position = vec4(position, 1.0);
    fragTexCoord = texcoord;
}