package ca.personal.poc.manage.flow;

import org.springframework.stereotype.Component;

@Component
public class FlowContextManager {
	
	private static final ThreadLocal<FlowContext> flowContexts = new ThreadLocal<>() {
		@Override
		protected FlowContext initialValue() {
			return new FlowContext();
		}
	};

	public FlowContext getContext() {
		return flowContexts.get();
	}

}
