package codemetropolis.toolchain.mapping.conversions;

import static org.junit.Assert.*;

import org.junit.Test;

import codemetropolis.toolchain.mapping.model.Limit;

/**
 * Tests NormalizeConversion
 * 
 * @author Keleti Márton
 */
public class NormalizeConversionTest {

	/**
	 * Tests normalizing
	 */
	@Test
	public void testApply() {
		NormalizeConversion instance = new NormalizeConversion();

		Limit limit = new Limit();
		limit.add(-54.234);
		limit.add(964841.231);

		assertEquals(3.8892710517610314e-4, instance.apply(321.04, limit));
		assertEquals(0.0, instance.apply(-54.234, limit));
		assertEquals(1.0, instance.apply(964841.231, limit));
	}

}
