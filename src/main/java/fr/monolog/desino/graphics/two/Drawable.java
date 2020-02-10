package fr.monolog.desino.graphics.two;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import fr.monolog.desino.utils.ShaderProgram;
import fr.monolog.desino.utils.Utils;

public abstract class Drawable {
	
	protected float[] vertices;
	protected Vector2f position;
	protected Vector2f origin;
	protected float rotation;
	protected Vector2f size;
	
	// LWJGL stuff	
	protected int vaoId;
	protected List<Integer> vboIdList;
	protected ShaderProgram shaderProgram;
	
	public Drawable(Vector2f position, Vector2f size, String vertexShaderPath, String fragmentShaderPath){
		this.position = position;
		this.origin = new Vector2f(0, 0);
		this.rotation = 0.0f;
		this.size = size;
		this.vboIdList = new ArrayList<Integer>();
		try {
			this.shaderProgram = new ShaderProgram();
			this.initVertices();
			this.initShaderProgram(vertexShaderPath, fragmentShaderPath);
			this.createBuffers();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	protected abstract void initVertices();
	
	protected void initShaderProgram(String vertexShaderPath, String fragmentShaderPath) throws Exception {
		this.shaderProgram.createVertexShader(Utils.readFromFile(vertexShaderPath));
		this.shaderProgram.createFragmentShader(Utils.readFromFile(fragmentShaderPath));
		this.shaderProgram.link();

		this.shaderProgram.createUniform("projectionMatrix");
		this.shaderProgram.createUniform("viewMatrix");
	}
	
	protected void createBuffers() {
		FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(this.vertices.length);
		verticesBuffer.put(this.vertices).flip();
		
        this.vaoId = glGenVertexArrays();
        glBindVertexArray(vaoId);

        int vboId = glGenBuffers();
        this.vboIdList.add(vboId);
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
        
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
	}
	
	public void draw() {
		glBindVertexArray(this.vaoId);
	    glEnableVertexAttribArray(0);
	    
	    glDrawArrays(GL_TRIANGLES, 0, this.vertices.length/2);
	    
	    glDisableVertexAttribArray(0);
	    glBindVertexArray(0);
	}
	
	protected void dispose() {
		glDisableVertexAttribArray(0);

        // Delete the VBOs
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        for (int vboId : this.vboIdList) {
            glDeleteBuffers(vboId);
        }

        // Delete the texture
        this.shaderProgram.dispose();

        // Delete the VAO
        glBindVertexArray(0);
        glDeleteVertexArrays(this.vaoId);
	}

	public float[] getVertices() {
		return vertices;
	}

	public void setVertices(float[] vertices) {
		this.vertices = vertices;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position = position;
	}
	
	public Vector2f getOrigin() {
		return origin;
	}
	
	public void setOrigin(Vector2f origin) {
		this.origin = origin;
	}

	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public Vector2f getSize() {
		return size;
	}

	public void setSize(Vector2f size) {
		this.size = size;
	}

	public int getVaoId() {
		return vaoId;
	}

	public void setVaoId(int vaoId) {
		this.vaoId = vaoId;
	}

	public ShaderProgram getShaderProgram() {
		return shaderProgram;
	}

	public void setShaderProgram(ShaderProgram shaderProgram) {
		this.shaderProgram = shaderProgram;
	}
}
