package poruke.sms.api;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.Test;

import poruke.sms.api.SMSAPI;
import test.TestUtil;

public class SMSAPITest {
	
	@Test
	public void metoda_vratiPoruke_vidljivost() {
		assertTrue("Metoda vratiPoruke nije javna", TestUtil.hasMethodModifier(SMSAPI.class, "vratiPoruke",
				new Class<?>[] { String.class }, Modifier.PUBLIC));
	}

	@Test
	public void metoda_arhivirajPoruke_vidljivost() {
		assertTrue("Metoda arhivirajPoruke nije javna",
				TestUtil.hasMethodModifier(SMSAPI.class, "arhivirajPoruke", null, Modifier.PUBLIC));
	}
}
