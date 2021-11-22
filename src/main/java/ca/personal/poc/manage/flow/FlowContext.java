/** */
package ca.personal.poc.manage.flow;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * "Context" that is available in all {@code FlowContextAwareStep}.
 *
 * <p>
 * Used to store any data during a flow.
 */
public class FlowContext {

	private Map<String, Object> data;

	private UUID uuid;

	private boolean changed;

	public FlowContext() {
		this.uuid = UUID.randomUUID();
		this.data = new HashMap<>();
	}
	
	public FlowContext inherit(FlowContext context) {
		this.data = context.getData();
		return this;
	}

	public UUID getUuid() {
		return this.uuid;
	}
	
	public Map<String, Object> getData() {
		return this.data;
	}
	
	/**
	 * Add value to context. Null value is ignored, it also changes the value of
	 * 'changed'.
	 *
	 * <p>
	 * <b>Put null value removes existing key from context, otherwise replace the
	 * value.
	 *
	 * @see FlowContext#isChanged()
	 * @param value value
	 */
	public void put(Object value) {
		if (Objects.isNull(value)) {
			throw new IllegalArgumentException("The parameter Value cannot be NULL");
		}

		put(value.getClass().getName(), value);
	}

	/**
	 * Add value to context. Null value is ignored, it also changes the value of
	 * 'changed'.
	 *
	 * <p>
	 * <b>Put null value removes existing key from context, otherwise replace the
	 * value.
	 *
	 * @see FlowContext#isChanged()
	 * @param key   key
	 * @param value value
	 */
	public void put(String key, Object value) {
		changed = Objects.isNull(this.data.get(key)) || this.data.get(key).equals(value);
		this.data.put(key, value);
	}

	public <T> T get(Class<T> type) {
		if (Objects.isNull(type)) {
			throw new IllegalArgumentException("The parameter Type cannot be NULL");
		}

		return get(type.getName(), type);
	}

	public <T> T get(String key, Class<T> type) {
		if (Objects.isNull(type)) {
			throw new IllegalArgumentException("The parameter Type cannot be NULL");
		}

		return type.cast(get(key));
	}

	public Object get(String key) {
		if (!containsKey(key)) {
			throw new IllegalArgumentException("Object not found. Key: " + key);
		}

		return this.data.get(key);
	}

	/**
	 * Used to identify the data in context has changed
	 *
	 * <p>
	 * <b>Put same value for a key is not considered 'changed'
	 *
	 * @return true changed, otherwise false.
	 */
	public boolean isChanged() {
		return changed;
	}

	public void resetChangedFlag() {
		changed = false;
	}

	public <T> void remove(Class<T> type) {
		if (Objects.isNull(type)) {
			throw new IllegalArgumentException("The parameter Type cannot be NULL");
		}

		remove(type.getClass().getName());
	}

	public void remove(String key) {
		this.data.remove(key);
	}

	public <T> boolean containsKey(Class<T> type) {
		if (Objects.isNull(type)) {
			throw new IllegalArgumentException("The parameter Type cannot be NULL");
		}

		return containsKey(type.getName());
	}

	public <T> boolean containsKey(String key) {
		return this.data.containsKey(key);
	}

	public Set<Entry<String, Object>> entrySet() {
		return this.data.entrySet();
	}

	/** 
	 * Remove all data in the context, also rest changed flag 
	 */
	public void clear() {
		this.data.clear();
		changed = false;
	}
}
