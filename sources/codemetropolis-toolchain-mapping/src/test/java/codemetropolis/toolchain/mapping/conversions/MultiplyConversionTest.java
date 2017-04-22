package codemetropolis.toolchain.mapping.conversions;

import static org.junit.Assert.*;

import org.junit.Test;

import codemetropolis.toolchain.mapping.model.Parameter;

/**
 * Tests MultiplyConversion
 * 
 * @author Keleti Márton
 */
public class MultiplyConversionTest {

	/**
	 * Tests whether we can multiply together two numbers
	 */
	@Test
	public void testApply() {
		MultiplyConversion instance = new MultiplyConversion();
		Parameter p1 = new Parameter();
		p1.setName("multiplier");
		p1.setValue(".16");
		instance.addParameter(p1);

		assertEquals(1.6, instance.apply(10.0, null));
		assertEquals(-0.264, instance.apply(-1.65, null));
		assertEquals(1046771863.251424, instance.apply(6542324145.3214, null));

		instance.clearParameters();
		Parameter p2 = new Parameter();
		p2.setName("multiplier");
		p2.setValue("-1563.657");
		instance.addParameter(p2);

		assertEquals(-10749413.210838, instance.apply(6874.534, null));
		assertEquals(338.60878188039, instance.apply(-0.21654927, null));
		assertEquals(-0.1013249736, instance.apply(0.0000648, null));
	}

}
