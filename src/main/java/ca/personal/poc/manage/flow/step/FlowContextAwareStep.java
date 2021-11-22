package ca.personal.poc.manage.flow.step;

import org.springframework.beans.factory.annotation.Autowired;

import ca.personal.poc.manage.flow.FlowContext;
import ca.personal.poc.manage.flow.FlowContextManager;

public abstract class FlowContextAwareStep implements FlowStep {

	@Autowired
	private FlowContextManager flowContextManager;

	public FlowContext getContext() {
		return flowContextManager.getContext();
	}
}
