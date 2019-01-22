package poruke.sms;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import poruke.Poruka;
import poruke.sms.SMS;
import test.TestUtil;

public class SMSTest {
	SMS instance;

	@Before
	public void setUp() throws Exception {
		instance = new SMS();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}
	
	@Test
	public void metoda_setPoruka_vidljivost() {
		assertTrue("Metoda setPoruka nije javna", TestUtil.hasMethodModifier(Poruka.class, "setPoruka",
				new Class<?>[] { String.class }, Modifier.PUBLIC));
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setPoruka_null() {
		String poruka = null;
		instance.setPoruka(poruka);
		assertTrue("Za prosledjeni argument " + poruka + " metoda setPoruka ne baca neproveravani izuzetak", false);
	}
	
	@Test(expected = RuntimeException.class)
	public void metoda_setPoruka_duziOd170() {
		String poruka = "A message is a discrete unit of communication intended by the source for consumption by some recipient or group of recipients. A message may be delivered by various means, including courier, telegraphy, carrier pigeon and electronic bus. A message can be the content of a broadcast. An interactive exchange of messages forms a conversation.";
		instance.setPoruka(poruka);
		assertTrue("Za prosledjeni string duzi od 170 znakova metoda setPoruka ne baca neproveravani izuzetak", false);
	}

	@Test
	public void metoda_setPoruka_zdravo() {
		String poruka = "zdravo";
		try {
			instance.setPoruka(poruka);
		} catch (RuntimeException e) {
			assertTrue("Za prosledjeni argument " + poruka + " metoda setPoruka baca neproveravani izuzetak", false);
		}
		String actual = (String) TestUtil.getFieldValue(instance, "poruka");
		assertTrue(
				"Za prosledjeni argument " + poruka
						+ ", nakon izvrsetka metode setPoruka vrednost abributa poruka je " + actual,
				poruka.equals(actual));
	}
}
