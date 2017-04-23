package codemetropolis.toolchain.mapping.conversions;

import codemetropolis.toolchain.mapping.model.Limit;
import codemetropolis.toolchain.mapping.model.Parameter;

public class MultiplyConversion extends Conversion {

	public static final String NAME = "multiply";

	private double multiplier;

	@Override
	public Object apply(Object value, Limit limit) {
		init();
		return toDouble(value) * multiplier;
	}

	private void init() {
		if (!initialized) {
			multiplier = 1;
			for(Parameter p : parameters) {
				if(p.getName().matches("multiplier")) {
					multiplier = Double.parseDouble(p.getValue());
				}
			}
			initialized = true;
		}
	}
	
}
