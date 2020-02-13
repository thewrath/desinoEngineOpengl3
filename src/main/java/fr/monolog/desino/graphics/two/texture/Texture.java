package fr.monolog.desino.graphics.two.texture;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.lwjgl.system.MemoryStack;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;

public class Texture {

    private final int id;
    private Vector2f size;
    private Matrix4f projectionMatrix;
    
    private Texture(int id, Vector2f size) {
    	this.id = id;
    	this.size = size;
    	this.projectionMatrix = (new Matrix4f()).setOrtho(0, this.size.x, this.size.y, 0, 0, 1);
    }

    public void bind() {
        glBindTexture(GL_TEXTURE_2D, id);
    }
    
    public void unbind() {
    	glBindTexture(GL_TEXTURE_2D, 0);
    }

    public int getId() {
        return id;
    }

    public static Texture loadFromImage(String fileName) throws Exception {
        ByteBuffer buf;
        // Load Texture file
        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer channels = stack.mallocInt(1);

            buf = stbi_load(fileName, w, h, channels, 4);
            if (buf == null) {
                throw new Exception("Image file [" + fileName  + "] not loaded: " + stbi_failure_reason());
            }
            
            // Create a new OpenGL texture
            int textureId = glGenTextures();
            // Bind the texture
            glBindTexture(GL_TEXTURE_2D, textureId);

            // Tell OpenGL how to unpack the RGBA bytes. Each component is 1 byte size
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);

            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            // Upload the texture data
            int width = w.get();
            int height = h.get();
            
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
            // Generate Mip Map
            glGenerateMipmap(GL_TEXTURE_2D);

            stbi_image_free(buf);
            return new Texture(textureId, new Vector2f(width, height));
        }
    }
    
    public Vector2f getSize() {
		return size;
	}

	public void dispose() {
        glDeleteTextures(id);
    }

	public Matrix4f getProjectionMatrix() {
		return projectionMatrix;
	}

	public void setProjectionMatrix(Matrix4f projectionMatrix) {
		this.projectionMatrix = projectionMatrix;
	}
}
