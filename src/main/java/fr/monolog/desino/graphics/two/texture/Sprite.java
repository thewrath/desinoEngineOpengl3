package fr.monolog.desino.graphics.two.texture;

import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;

import org.joml.Matrix2f;
import org.joml.Vector2f;
import org.lwjgl.system.MemoryUtil;

import fr.monolog.desino.graphics.two.Drawable;

public class Sprite extends Drawable {
	
	private TextureRegion textureRegion;
	private FloatBuffer textCoordsBuffer;
	
	public Sprite(Vector2f position, TextureRegion textureRegion) {
		super(position, textureRegion.getSize(), "./shaders/vertexSpriteShader.vs", "./shaders/fragmentSpriteShader.fs");
		this.textureRegion = textureRegion;
		
		try {
			int vboId = glGenBuffers();
	        this.vboIdList.add(vboId);
			
			float[] textCoords = new float[]{
				0.0f, 1.0f,
	            1.0f, 0.0f,
	            0.0f, 0.0f,

	            0.0f, 1.0f,
	            1.0f, 1.0f,
	            1.0f, 0.0f
			};
			
			glBindVertexArray(this.vaoId);
			
	        textCoordsBuffer = MemoryUtil.memAllocFloat(textCoords.length);
	        textCoordsBuffer.put(textCoords).flip();
	        glBindBuffer(GL_ARRAY_BUFFER, vboId);
	        glBufferData(GL_ARRAY_BUFFER, textCoordsBuffer, GL_STATIC_DRAW);
	        glEnableVertexAttribArray(1);
	        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
	        
	        glBindBuffer(GL_ARRAY_BUFFER, 0);
	        glBindVertexArray(0);
	        
	        this.shaderProgram.createUniform("texture_sampler");
	        this.shaderProgram.createUniform("texProjectionMatrix");
	        this.shaderProgram.createUniform("texRegionMatrix");
	        
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (textCoordsBuffer != null) {
                MemoryUtil.memFree(textCoordsBuffer);
            }
		}
	}
	
	@Override
	protected void initVertices() {
		// TODO Auto-generated method stub
		this.vertices = new float[] {
			0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 0.0f,

            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
		};
	}
	
	public void draw() {
		// set uniforms
		this.shaderProgram.setUniform("texture_sampler", 0);
		this.shaderProgram.setUniform("texProjectionMatrix", this.textureRegion.getTexture().getProjectionMatrix());
		this.shaderProgram.setUniform("texRegionMatrix", this.textureRegion.getViewMatrix());
		
		// Activate first texture bank
        glActiveTexture(GL_TEXTURE0);
        // Bind the texture
        glBindTexture(GL_TEXTURE_2D, textureRegion.getTexture().getId());
     	glBindVertexArray(this.vaoId);
	    glEnableVertexAttribArray(0);
	    
	    glDrawArrays(GL_TRIANGLES, 0, this.vertices.length/2);
	    
	    glDisableVertexAttribArray(0);
	    glBindVertexArray(0);
	}
	
	public TextureRegion getTextureRegion() {
		return textureRegion;
	}

	public void setTextureRegion(TextureRegion textureRegion) {
		this.textureRegion = textureRegion;
	}

}
