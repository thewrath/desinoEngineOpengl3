package fr.monolog.desino.test;

import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2f;

import fr.monolog.desino.common.Engine;
import fr.monolog.desino.common.Game;
import fr.monolog.desino.graphics.two.Renderer;
import fr.monolog.desino.graphics.two.Viewport;
import fr.monolog.desino.graphics.two.shapes.RectangleShape;
import fr.monolog.desino.graphics.two.texture.Sprite;
import fr.monolog.desino.graphics.two.texture.Texture;
import fr.monolog.desino.graphics.two.texture.TextureRegion;
import fr.monolog.desino.inputs.InputProcessor;
import fr.monolog.desino.inputs.JoystickEvent;
import fr.monolog.desino.inputs.KeyboardEvent;
import fr.monolog.desino.inputs.MouseButtonEvent;

public class App implements Game, InputProcessor {

	private Renderer renderer;
	private RectangleShape rectangle;
	private Sprite sprite;
	
	private List<RectangleShape> rectangles;
	private final int NUMBER_OF_RECTANGLE = 5;

	@Override
	public void init() {
		this.renderer = new Renderer(new Viewport(new Vector2f(0.0f, 0.0f), new Vector2f(860f, 640f)));
		this.rectangle = new RectangleShape(new Vector2f(100f, 100f),new Vector2f(100f,100f));
		try {
			Texture texture = Texture.loadFromImage("./src/main/resources/textures/test.png");
			TextureRegion textureRegion = new TextureRegion(texture, new Vector2f(0, 0), texture.getSize());
			this.sprite = new Sprite(new Vector2f(0f,0f), new Vector2f(1200f/4f, 1176f/4f), textureRegion);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.rectangles = new ArrayList<RectangleShape>();
		for (int i = 0; i < NUMBER_OF_RECTANGLE; i++) {
			this.rectangles.add(new RectangleShape(new Vector2f(0f + 10*i, 0f), new Vector2f(100f, 100f)));
		}
	}

	@Override
	public void update(float delta) {
		this.sprite.setRotation(this.sprite.getRotation() + 10f);
		
		this.sprite.setPosition(new Vector2f(this.sprite.getPosition().add(1, 1)));
		for (RectangleShape rectangleShape : rectangles) {
			rectangleShape.setRotation(rectangleShape.getRotation() + 10f);
		}
	}

	@Override
	public void draw() {
		this.renderer.clear();
		this.renderer.draw(this.rectangle);
		this.renderer.draw(this.sprite);
		for (RectangleShape rectangleShape : rectangles) {
			this.renderer.draw(rectangleShape);
		}
	}

	@Override
	public void dispose() {
		this.renderer.dipose();
		
	}
	
	public static void main(String[] args) {
		App game = new App();
		Engine engine = new Engine(game, 30, "Desino Game Engine", 860, 640, true);
		
		engine.getWindow().getInputManager().register(game);
		
		engine.run();
	}

	@Override
	public void handleInputEvent(KeyboardEvent keyboardEvent) {
		// TODO Auto-generated method stub
		System.out.println(keyboardEvent);
	}

	@Override
	public void handleInputEvent(JoystickEvent joystickEvent) {
		// TODO Auto-generated method stub
		System.out.println(joystickEvent);
	}

	@Override
	public void handleInputEvent(MouseButtonEvent mouseEvent) {
		// TODO Auto-generated method stub
		System.out.println(mouseEvent);
	}


}
