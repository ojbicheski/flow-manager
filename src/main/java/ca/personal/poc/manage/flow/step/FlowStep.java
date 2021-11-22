package ca.personal.poc.manage.flow.step;

public interface FlowStep {

	/**
	 * Validates all dependencies required to Step be executed.
	 * <ul>
	 * <li>Context parameters</li>
	 * <li>Services</li>
	 * </ul>
	 */
	void validate();
	
	/**
	 * Executes Step.
	 */
	void perform();
	
}
