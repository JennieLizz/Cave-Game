#version 400 core

in vec2 pass_textureCoords;

out vec4 out_color;

uniform sampler2D texSampler;


void main()
{
    out_color = texture(texSampler, pass_textureCoords);
}