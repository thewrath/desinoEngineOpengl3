package fr.monolog.desino.inputs;

public class InputEvent {
	
	private int actionCode;
	private int inputCode;
	
	public InputEvent(int actionCode, int inputCode) {
		this.actionCode = actionCode;
		this.inputCode = inputCode;
	}
	
	public int getActionCode() {
		return actionCode;
	}
	
	public int getInputCode() {
		return inputCode;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " action code : " + this.actionCode + " input code : " + this.inputCode;
	}
}
