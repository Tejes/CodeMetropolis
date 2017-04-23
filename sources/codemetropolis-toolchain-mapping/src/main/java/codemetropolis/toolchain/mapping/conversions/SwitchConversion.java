package codemetropolis.toolchain.mapping.conversions;

import java.util.HashMap;
import java.util.Map;

import codemetropolis.toolchain.mapping.model.Limit;
import codemetropolis.toolchain.mapping.model.Parameter;

public class SwitchConversion extends Conversion {

	public static final String NAME = "switch";
	
	private final Map<String, String> cases = new HashMap<>();
	private String defaultValue = null;

	@Override
	public Object apply(Object value, Limit limit) {
		init();

		for (Map.Entry<String, String> entry : cases.entrySet()) {
			if (entry.getKey().equals(value)) {
				return entry.getValue();
			}
		}
		return defaultValue;
	}

	private void init() {
		if (!initialized) {
			cases.clear();
			defaultValue = null;

			for(Parameter p : parameters) {
				if(p.getName().equals("default")) {
					defaultValue = p.getValue();
					break;
				} else if(p.getName().matches("^value_.*")) {
					cases.put(p.getName().split("value_")[1], p.getValue());
				}
			}

			initialized = true;
		}
	}

}
