package fr.monolog.desino.common;

import fr.monolog.desino.common.glfw.Window;

public class Engine {
	
	private Game game;
	private boolean running;
	private final Window window;
	//test
	private int frames = 0;
	private long lastTime = System.nanoTime();
	private double delta = 0.0;
    private double ns;
    private long time = System.currentTimeMillis();
    private int updates = 0;
    	
	public Engine(Game game, double refreshTime, String title, int width, int height, boolean vSync){
		this.game = game;
		this.window = new Window(title,width,height,vSync);
		this.init(refreshTime);
	}
	
	private void init(double refreshTime){
		this.running = true;
		this.window.init();
		this.game.init();
		this.ns = 1000000000.0 / refreshTime;
	}
	
	public void run() {
		this.loop();
	}
	
	private void loop(){
		while(this.running && !this.window.windowShouldClose()){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1.0){
				this.update((float) delta);
				updates++;
	            delta--;
			}
			
			this.draw();
			frames++;
			
			if (System.currentTimeMillis() - time > 1000) {
				time += 1000;
	            System.out.println(frames + " fps" + updates + "ups");
	            frames = 0;
	            updates = 0;
			}
			
		}
		
		this.dispose();
	}
	
	private void update(float delta){
		this.game.update(delta);
	}

	private void draw(){
		this.game.draw();
		this.window.update();
	}
	
	private void dispose(){
		this.game.dispose();
	}

	public Window getWindow() {
		return window;
	}
	
}
