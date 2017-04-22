package codemetropolis.toolchain.mapping.conversions;

import static org.junit.Assert.*;

import org.junit.Test;

import codemetropolis.toolchain.mapping.model.Parameter;

/**
 * Tests SwitchConversion
 * 
 * @author Keleti Márton
 */
public class SwitchConversionTest {

	/**
	 * Tests a switch with default value provided
	 */
	@Test
	public void testWithDefault() {
		SwitchConversion instance = new SwitchConversion();
		Parameter p1 = new Parameter();
		p1.setName("value_1");
		p1.setValue("one");
		Parameter p2 = new Parameter();
		p2.setName("value_2");
		p2.setValue("two");
		Parameter p3 = new Parameter();
		p3.setName("value_3");
		p3.setValue("three");
		Parameter p4 = new Parameter();
		p4.setName("default");
		p4.setValue("many");
		instance.addParameters(p1, p2, p3, p4);

		assertEquals("one", instance.apply("1", null));
		assertEquals("three", instance.apply("3", null));
		assertEquals("many", instance.apply("4", null));
		assertEquals("many", instance.apply("-5", null));
		assertEquals("many", instance.apply("text", null));
	}

	/**
	 * Tests a switch without default value
	 */
	@Test
	public void testWithoutDefault() {
		SwitchConversion instance = new SwitchConversion();
		Parameter p1 = new Parameter();
		p1.setName("value_one");
		p1.setValue("1");
		Parameter p2 = new Parameter();
		p2.setName("value_two");
		p2.setValue("2");
		instance.addParameters(p1, p2);

		assertEquals("1", instance.apply("one", null));
		assertEquals("2", instance.apply("two", null));
		assertNull(instance.apply("text", null));
	}

}
