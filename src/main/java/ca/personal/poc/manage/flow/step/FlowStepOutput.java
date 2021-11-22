package ca.personal.poc.manage.flow.step;

public interface FlowStepOutput<O> {

	/**
	 * 
	 * @param clazz reference class to be returned as Step output.
	 * @return Returns Output based on Class<O>
	 */
	O performOutput();
	
}
