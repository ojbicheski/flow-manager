/**
 * 
 */
package ca.personal.poc.manage.flow;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import ca.personal.poc.manage.flow.step.FlowContextAwareStep;
import ca.personal.poc.manage.flow.step.FlowStep;
import ca.personal.poc.manage.flow.step.FlowStepOutput;
import ca.personal.poc.manage.flow.step.SkippableStep;
import ca.personal.poc.manage.flow.step.StepFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Orlei Bicheski
 *
 */
@Slf4j
public abstract class Flow<O> extends FlowContextAwareStep implements SkippableStep, FlowStepOutput<O> {

	private List<FlowStep> steps = new ArrayList<>();

	protected void addStep(FlowStep step) {
		if (Objects.isNull(step)) {
			log.error("Flow {} | Step cannot be null.", this.getClass().getSimpleName());
			throw new IllegalArgumentException("Step cannot be null.");
		}

		this.steps.add(step);
	}

	public Flow<O> input(Object...parameters) {
		Arrays.asList(parameters).stream().forEach(getContext()::put);
		return this;
	}
	
	@Override
	public void perform() {
		log.info("Starting Flow [{}] Context ID [{}].", this.getClass().getSimpleName(), getContext().getUuid());
		this.validate();

		if(this.isSkip()) {
			log.info("Skipping Flow.");
			return;
		}

		steps.stream().forEach(step -> trigger(step));
		log.info("Flow [{}] concluded.", this.getClass().getSimpleName());
	}

	private void trigger(FlowStep step) {
		step.validate();

		if (step instanceof SkippableStep) {
			if(((SkippableStep) step).isSkip()) {
				log.info("Skipping Step {} Flow ID {}", step.getClass().getSimpleName(), getContext().getUuid());
				return;
			}
		}
		
		if (step instanceof FlowStepOutput) {
			FlowStepOutput<?> flow = (FlowStepOutput<?>) step;
			((Flow<?>) flow).input(getContext());
			getContext().put(flow.performOutput());
		} else {
			step.perform();
		}
	}
	
	/**
	 * Finishes the execution flow and release objects.
	 */
	public void dispose() {
		getContext().clear();
	}
	
	public static <T> Builder<T> builder(Class<T> clazz) {
		return new Builder<T>(clazz);
	}
	
	public static class Builder<T> {
		private Optional<StepFactory> factory;
		private final T flow;
		
		private Builder(Class<T> clazz) {
			try {
				this.flow = (T) clazz.cast(clazz.getConstructor().newInstance());
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				log.error("Flow {} cannot be instantiated.", clazz.getSimpleName());
				throw new RuntimeException(e);
			}
		}
		
		public Builder<T> withManager(StepFactory factory) {
			this.factory = Optional.ofNullable(factory);
			return this;
		}
		
		public <O extends FlowStep> Builder<T> withStep(Class<O> clazz) {
			((Flow<?>) flow).addStep(clazz.cast(factory
					.orElseThrow(() -> new RuntimeException("Step factory is null."))
					.step(name(clazz))));
			return this;
		}
		
		private <O extends FlowStep> String name(Class<O> clazz) {
			return clazz.getSimpleName().substring(0, 1).toLowerCase().concat(clazz.getSimpleName().substring(1));
		}

		public T build() {
			return this.flow;
		}
	}
}
