package codemetropolis.toolchain.mapping.conversions;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the ToDoubleConversion
 * 
 * @author Keleti Márton
 */
public class ToDoubleConversionTest {

	/**
	 * Tests conversions to double
	 */
	@Test
	public void testApply() {
		ToDoubleConversion instance = new ToDoubleConversion();

		assertEquals(1.0, instance.apply("1", null));
		assertEquals(-6.15, instance.apply("-6.15", null));
		assertEquals(0.684, instance.apply(".684", null));
		assertEquals(5.6, instance.apply(5.6, null));
		assertEquals(Double.POSITIVE_INFINITY, instance.apply("Infinity", null));
		assertEquals(Double.NEGATIVE_INFINITY, instance.apply("-Infinity", null));
		assertEquals(Double.NaN, instance.apply("NaN", null));
		assertEquals(-0.0, instance.apply("-0", null));
		assertNotEquals(0.0, instance.apply("-0", null));
	}

}
