package ca.personal.poc.manage.flow.step;

public interface SkippableStep extends FlowStep {
	
	/**
	 * Checks if Step needs to be skipped.
	 * 
	 * @return Boolean TRUE when Step must be skipped
	 */
	boolean isSkip();
	
}
