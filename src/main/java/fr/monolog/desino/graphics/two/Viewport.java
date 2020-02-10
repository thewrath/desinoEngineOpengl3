package fr.monolog.desino.graphics.two;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Viewport {
	
	private Vector2f position;
	private Vector2f size;
	
	private final Matrix4f projectionMatrix;
    private final Matrix4f viewMatrix;
	
	public Viewport (Vector2f position, Vector2f size) {
		this.position = position;
		this.size = size;
		
		this.projectionMatrix = (new Matrix4f()).setOrtho(this.position.x, this.size.x, this.size.y, this.position.y, -1, 1);
		this.viewMatrix = new Matrix4f();
		
	}
	
	public final Matrix4f getProjectionMatrix() {
        return projectionMatrix;
    }
	
	public Matrix4f getViewMatrix(Vector2f offset, float angle, Vector2f scale) {
        return viewMatrix.translation(new Vector3f(offset.x, offset.y, 0)).
                rotateX((float)Math.toRadians(0.0f)).
                rotateY((float)Math.toRadians(0.0f)).
                rotateZ((float)Math.toRadians(angle)).
                scale(scale.x, scale.y, 1);
    }

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}

	public Vector2f getSize() {
		return size;
	}

	public void setSize(Vector2f size) {
		this.size = size;
	}
	
}
