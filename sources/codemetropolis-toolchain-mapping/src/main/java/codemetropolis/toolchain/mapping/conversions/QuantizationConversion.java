package codemetropolis.toolchain.mapping.conversions;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import codemetropolis.toolchain.mapping.model.Limit;
import codemetropolis.toolchain.mapping.model.Parameter;

public class QuantizationConversion extends Conversion {

	public static final String NAME = "quantization";
	
	private final SortedMap<Integer, String> levelsMap = new TreeMap<>();
	private final SortedMap<Integer, Double> thresholdMap = new TreeMap<>();

	@Override
	public Object apply(Object value, Limit limit) {
		init();

		double dValue = toDouble(value);
		
		if(levelsMap.size() == thresholdMap.size() + 1) {
			int i = 0;
			for(Double threshold : thresholdMap.values()) {
				if(dValue <= threshold) {
					return levelsMap.get(i);
				}
				++i;
			}
			return levelsMap.get(i);
		} else {
			double distance = limit.getMax() - limit.getMin();
			double step = distance / levelsMap.size();

			for (Map.Entry<Integer, String> entry : levelsMap.entrySet()) {
				double levelLimit = (entry.getKey() + 1) * step + limit.getMin();
				if(dValue <= levelLimit) {
					return levelsMap.get(entry.getKey());
				}
			}
		}
		
		return null;
	}
	
	private void init() {
		if (!initialized) {
			levelsMap.clear();
			thresholdMap.clear();
			for(Parameter p : parameters) {
				if(p.getName().matches("level[0-9]+")) {
					int num = Integer.parseInt(p.getName().substring(5));
					levelsMap.put(num, p.getValue());
				} else if(p.getName().matches("threshold[0-9]+")) {
					int num = Integer.parseInt(p.getName().substring(9));
					thresholdMap.put(num, toDouble(p.getValue()));
				}
			}
			initialized = true;
		}
	}

}
