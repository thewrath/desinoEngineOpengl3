package fr.monolog.desino.inputs;

public interface InputProcessor {
	void handleInputEvent(KeyboardEvent keyboardEvent);
	void handleInputEvent(JoystickEvent joystickEvent);
	void handleInputEvent(MouseButtonEvent mouseEvent);
}
