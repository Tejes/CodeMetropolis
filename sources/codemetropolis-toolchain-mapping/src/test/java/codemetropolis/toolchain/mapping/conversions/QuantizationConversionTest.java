package codemetropolis.toolchain.mapping.conversions;

import static org.junit.Assert.*;

import org.junit.Test;

import codemetropolis.toolchain.mapping.model.Limit;
import codemetropolis.toolchain.mapping.model.Parameter;

/**
 * Unit test for QuantizationConversion
 * 
 * @author Keleti Márton
 */
public class QuantizationConversionTest {

	/**
	 * Tests the default linear quantization without custom thresholds.
	 */
	@Test
	public void testLinearQuantization() {
		QuantizationConversion instance = new QuantizationConversion();
		Parameter p1 = new Parameter();
		p1.setName("level0");
		p1.setValue("glass");
		Parameter p2 = new Parameter();
		p2.setName("level1");
		p2.setValue("sand");
		Parameter p3 = new Parameter();
		p3.setName("level2");
		p3.setValue("planks");
		Parameter p4 = new Parameter();
		p4.setName("level3");
		p4.setValue("stone");
		Parameter p5 = new Parameter();
		p5.setName("level4");
		p5.setValue("obsidian");
		instance.addParameters(p1, p2, p3, p4, p5);

		Limit limit = new Limit();
		limit.add(152.324);
		limit.add(-6.23); // intervals: -6,23 25,4808 57,1916 88,9024 120,6132
							// 152,324

		assertEquals("glass", instance.apply(1.0, limit));
		assertEquals("glass", instance.apply(-6.23, limit));
		assertEquals("glass", instance.apply(25.4808, limit));
		assertEquals("sand", instance.apply(25.4808000000000004, limit));
		assertEquals("stone", instance.apply(92.1548, limit));
		assertEquals("obsidian", instance.apply(152.324, limit));
	}

	/**
	 * Tests the quantization when custom threshold parameters are provided.
	 */
	@Test
	public void testCustomQuantization() {
		QuantizationConversion instance = new QuantizationConversion();
		Parameter p1 = new Parameter();
		p1.setName("level0");
		p1.setValue("glass");
		Parameter p2 = new Parameter();
		p2.setName("level1");
		p2.setValue("sand");
		Parameter p3 = new Parameter();
		p3.setName("level2");
		p3.setValue("planks");
		Parameter p4 = new Parameter();
		p4.setName("level3");
		p4.setValue("stone");
		Parameter p5 = new Parameter();
		p5.setName("level4");
		p5.setValue("obsidian");
		instance.addParameters(p1, p2, p3, p4, p5);

		Parameter t1 = new Parameter();
		t1.setName("threshold0");
		t1.setValue("2");
		Parameter t2 = new Parameter();
		t2.setName("threshold1");
		t2.setValue("4");
		Parameter t3 = new Parameter();
		t3.setName("threshold2");
		t3.setValue("8");
		Parameter t4 = new Parameter();
		t4.setName("threshold3");
		t4.setValue("16");
		instance.addParameters(t1, t2, t3, t4);

		Limit limit = new Limit();
		limit.add(152.324);
		limit.add(-6.23);

		assertEquals("glass", instance.apply(-1.0, limit));
		assertEquals("glass", instance.apply(2.0, limit));
		assertEquals("sand", instance.apply(3.9999, limit));
		assertEquals("planks", instance.apply(6.0, limit));
		assertEquals("obsidian", instance.apply(20.6, limit));
	}

}
