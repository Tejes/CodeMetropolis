package codemetropolis.toolchain.mapping.conversions;

import java.util.SortedMap;
import java.util.TreeMap;

import codemetropolis.toolchain.mapping.model.Limit;
import codemetropolis.toolchain.mapping.model.Parameter;

public class QuantizationConversion extends Conversion {

	public static final String NAME = "quantization";
	
	@Override
	public Object apply(Object value, Limit limit) {
		SortedMap<Integer, String> levelsMap = new TreeMap<>();
		SortedMap<Integer, Double> thresholdMap = new TreeMap<>();
		
		for(Parameter p : parameters) {
			if(p.getName().matches("level[0-9]+")) {
				int num = Integer.parseInt(p.getName().substring(5));
				levelsMap.put(num, p.getValue());
			} else if(p.getName().matches("threshold[0-9]+")) {
				int num = Integer.parseInt(p.getName().substring(9));
				thresholdMap.put(num, toDouble(p.getValue()));
			}
		}
		
		String[] levels = levelsMap.values().toArray(new String[0]);
		double dValue = toDouble(value);
		
		if(levelsMap.size() == thresholdMap.size() + 1) {
			int i = 0;
			for(Double threshold : thresholdMap.values()) {
				if(dValue <= threshold) {
					return levels[i];
				}
				++i;
			}
			return levels[i];
		} else {
			double distance = limit.getMax() - limit.getMin();
			double step = distance / levels.length;

			for(int i = 0; i < levels.length; i++) {
				double levelLimit = (i + 1) * step + limit.getMin();
				if(dValue <= levelLimit) {
					return levels[i];
				}
			}
		}
		
		return null;
	}
	
}
