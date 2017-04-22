package codemetropolis.toolchain.mapping.conversions;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ToIntConversion
 * 
 * @author Keleti Márton
 */
public class ToIntConversionTest {

	/**
	 * Tests conversions to int
	 */
	@Test
	public void testApply() {
		ToIntConversion instance = new ToIntConversion();

		assertEquals(6, instance.apply("6", null));
		assertEquals(-921, instance.apply("-921", null));
		assertEquals(62, instance.apply("62.4", null));
		assertEquals(564, instance.apply("564.99999", null));
		assertEquals(-5294, instance.apply("-5294.75", null));
		assertEquals(88, instance.apply(88.61, null));
		assertEquals(0, instance.apply("-0", null));
		assertEquals(0, instance.apply(Double.NaN, null));
		assertEquals(Integer.MAX_VALUE, instance.apply(Double.POSITIVE_INFINITY, null));
		assertEquals(Integer.MIN_VALUE, instance.apply(Double.NEGATIVE_INFINITY, null));
	}

}
