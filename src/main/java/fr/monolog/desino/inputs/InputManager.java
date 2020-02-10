package fr.monolog.desino.inputs;

import java.util.ArrayList;
import java.util.List;

public class InputManager {
	
	private List<InputProcessor> inputProcessors;
	
	public InputManager() {
		this.inputProcessors = new ArrayList<InputProcessor>();
	}
	
	public void register(InputProcessor inputProcessor) {
		this.inputProcessors.add(inputProcessor);
	}
	
	public void dispatch(KeyboardEvent keyboardEvent) {
		for (InputProcessor inputProcessor : inputProcessors) {
			inputProcessor.handleInputEvent(keyboardEvent);
		}
	}
	
	public void dispatch(JoystickEvent joystickEvent) {
		for (InputProcessor inputProcessor : inputProcessors) {
			inputProcessor.handleInputEvent(joystickEvent);
		}
	}
	
	public void dispatch(MouseButtonEvent mouseEvent) {
		for (InputProcessor inputProcessor : inputProcessors) {
			inputProcessor.handleInputEvent(mouseEvent);
		}
	}
}
