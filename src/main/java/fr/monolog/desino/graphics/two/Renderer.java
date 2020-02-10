package fr.monolog.desino.graphics.two;


import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer {
	
	private Viewport viewport;
	
	public Renderer(Viewport viewport){
		this.viewport = viewport;
		this.init();
	}
	
	private void init() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void draw(Drawable object){
		object.getShaderProgram().bind();
		object.getShaderProgram().setUniform("viewMatrix", this.viewport.getViewMatrix(object.getPosition(), object.getRotation(), object.getSize()));
		object.getShaderProgram().setUniform("projectionMatrix", this.viewport.getProjectionMatrix());
		object.draw();
		object.getShaderProgram().unbind();
	}
	
	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	public void dipose(){
		glDisableVertexAttribArray(0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}
}
