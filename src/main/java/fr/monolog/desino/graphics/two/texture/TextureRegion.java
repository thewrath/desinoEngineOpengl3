package fr.monolog.desino.graphics.two.texture;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public class TextureRegion {

	private Texture texture;
	private Vector2f origin;
	private Vector2f size;
	
	private Matrix4f viewMatrix;
	
	public TextureRegion(Texture texture, Vector2f origin, Vector2f size) {
		this.texture = texture;
		this.origin = origin;
		this.size = size;
		
		this.viewMatrix = new Matrix4f();
		this.viewMatrix = this.viewMatrix.translation(this.origin.x, this.origin.y, 0).
                scale(this.size.x, this.size.y, 1);
	}
	
	public Matrix4f getViewMatrix() {
        return this.viewMatrix;
    }
	
	public Texture getTexture() {
		return this.texture;
	}
}
