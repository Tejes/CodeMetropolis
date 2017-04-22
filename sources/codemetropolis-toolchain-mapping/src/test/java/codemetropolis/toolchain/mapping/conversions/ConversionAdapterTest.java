package codemetropolis.toolchain.mapping.conversions;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import codemetropolis.toolchain.mapping.conversions.ConversionAdapter.AdaptedConversion;
import codemetropolis.toolchain.mapping.model.Parameter;

/**
 * Tests the ConversionAdapter
 * 
 * @author Keleti Márton
 */
public class ConversionAdapterTest {

	/**
	 * Tests the marshal() method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMarshalConversion() throws Exception {
		ConversionAdapter adapter = new ConversionAdapter();

		Conversion conv1 = new ToIntConversion();
		AdaptedConversion aconv1 = adapter.marshal(conv1);
		assertEquals("to_int", aconv1.type);
		assertNotNull(aconv1.parameters);
		assertEquals(0, aconv1.parameters.size());

		Conversion conv2 = new SwitchConversion();
		Parameter p1 = new Parameter();
		p1.setName("aParameter");
		p1.setValue("someValue");
		conv2.addParameter(p1);
		AdaptedConversion aconv2 = adapter.marshal(conv2);
		assertEquals("switch", aconv2.type);
		assertNotNull(aconv2.parameters);
		assertSame(p1, aconv2.parameters.get(0));
		assertEquals(1, aconv2.parameters.size());
	}

	/**
	 * Tests the unmarshal() method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUnmarshalAdaptedConversion() throws Exception {
		ConversionAdapter adapter = new ConversionAdapter();

		AdaptedConversion aconv1 = new AdaptedConversion();
		aconv1.parameters = new ArrayList<>();
		aconv1.type = "quantization";
		Conversion conv1 = adapter.unmarshal(aconv1);
		assertTrue(conv1 instanceof QuantizationConversion);
		assertEquals(0, conv1.getParameters().size());

		AdaptedConversion aconv2 = new AdaptedConversion();
		aconv2.type = "switch";
		aconv2.parameters = new ArrayList<>();
		Parameter p1 = new Parameter();
		p1.setName("someParameter");
		p1.setValue("anotherValue");
		Parameter p2 = new Parameter();
		p2.setName("yetAnotherParameter");
		p2.setValue("nthValue");
		aconv2.parameters.add(p1);
		aconv2.parameters.add(p2);
		Conversion conv2 = adapter.unmarshal(aconv2);
		assertTrue(conv2 instanceof SwitchConversion);
		assertEquals(2, conv2.parameters.size());
		assertSame(p1, conv2.parameters.get(0));
		assertSame(p2, conv2.parameters.get(1));
	}

}
