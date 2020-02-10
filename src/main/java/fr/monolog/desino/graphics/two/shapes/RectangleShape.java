package fr.monolog.desino.graphics.two.shapes;

import org.joml.Vector2f;

import fr.monolog.desino.graphics.two.Drawable;
 
public class RectangleShape extends Drawable {
	
	public RectangleShape(Vector2f position, Vector2f size) {
		super(position, size, "./shaders/vertexShader.vs", "./shaders/fragmentShader.fs");
	}
	
	protected void initVertices() {
		this.vertices = new float[] {
			0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 0.0f,

            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
		};
				
	}
}
