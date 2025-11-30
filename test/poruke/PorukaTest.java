package poruke;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Modifier;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import poruke.Poruka;
import poruke.sms.SMS;
import test.TestUtil;

public class PorukaTest {
	Poruka instance;

	@Before
	public void setUp() throws Exception {
		instance = new SMS();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	@Test
	public void atribut_posiljalac() {
		assertTrue("Nije definisan atribut posiljalac", TestUtil.doesFieldExist(Poruka.class, "posiljalac"));
	}

	@Test
	public void atribut_posiljalac_vidljivost() {
		assertTrue("Atribut posiljalac nije privatan",
				TestUtil.hasFieldModifier(Poruka.class, "posiljalac", Modifier.PRIVATE));
	}

	@Test
	public void atribut_primalac() {
		assertTrue("Nije definisan atribut primalac", TestUtil.doesFieldExist(Poruka.class, "primalac"));
	}

	@Test
	public void atribut_primalac_vidljivost() {
		assertTrue("Atribut primalac nije privatan",
				TestUtil.hasFieldModifier(Poruka.class, "primalac", Modifier.PRIVATE));
	}

	@Test
	public void atribut_poruka() {
		assertTrue("Nije definisan atribut poruka", TestUtil.doesFieldExist(Poruka.class, "poruka"));
	}

	@Test
	public void atribut_poruka_vidljivost() {
		assertTrue("Atribut poruka nije privatan", TestUtil.hasFieldModifier(Poruka.class, "poruka", Modifier.PRIVATE));
	}

	@Test
	public void atribut_vreme() {
		assertTrue("Nije definisan atribut vreme", TestUtil.doesFieldExist(Poruka.class, "vreme"));
	}

	@Test
	public void atribut_vreme_vidljivost() {
		assertTrue("Atribut vreme nije privatan", TestUtil.hasFieldModifier(Poruka.class, "vreme", Modifier.PRIVATE));
	}

	@Test
	public void metoda_setPosiljalac_vidljivost() {
		assertTrue("Metoda setPosiljalac nije javna", TestUtil.hasMethodModifier(Poruka.class, "setPosiljalac",
				new Class<?>[] { String.class }, Modifier.PUBLIC));
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setPosiljalac_null() {
		String posiljalac = null;
		instance.setPosiljalac(posiljalac);
		assertTrue("Za prosledjeni argument " + posiljalac + " metoda setKorisnik ne baca neproveravani izuzetak",
				false);
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setPosiljalac_prazanString() {
		String posiljalac = "";
		instance.setPosiljalac(posiljalac);
		assertTrue("Za prosledjeni prazan string metoda setPosiljalac ne baca neproveravani izuzetak", false);
	}

	@Test
	public void metoda_setPosiljalac_Marko() {
		String posiljalac = "Marko";
		try {
			instance.setPosiljalac(posiljalac);
		} catch (RuntimeException e) {
			assertTrue("Za prosledjeni argument " + posiljalac + " metoda setPosiljalac baca neproveravani izuzetak",
					false);
		}
		String actual = (String) TestUtil.getFieldValue(instance, "posiljalac");
		assertTrue(
				"Za prosledjeni argument " + posiljalac
						+ ", nakon izvrsetka metode setPosiljalac vrednost abributa posiljalac je " + actual,
				posiljalac.equals(actual));
	}

	@Test
	public void metoda_setPrimalac_vidljivost() {
		assertTrue("Metoda setPrimalac nije javna", TestUtil.hasMethodModifier(Poruka.class, "setPrimalac",
				new Class<?>[] { String.class }, Modifier.PUBLIC));
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setPrimalac_null() {
		String primalac = null;
		instance.setPrimalac(primalac);
		assertTrue("Za prosledjeni argument " + primalac + " metoda setKorisnik ne baca neproveravani izuzetak", false);
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setPrimalac_prazanString() {
		String primalac = "";
		instance.setPrimalac(primalac);
		assertTrue("Za prosledjeni prazan string metoda setPrimalac ne baca neproveravani izuzetak", false);
	}

	@Test
	public void metoda_setPrimalac_Darko() {
		String primalac = "Darko";
		try {
			instance.setPrimalac(primalac);
		} catch (RuntimeException e) {
			assertTrue("Za prosledjeni argument " + primalac + " metoda setPrimalac baca neproveravani izuzetak",
					false);
		}
		String actual = (String) TestUtil.getFieldValue(instance, "primalac");
		assertTrue(
				"Za prosledjeni argument " + primalac
						+ ", nakon izvrsetka metode setPrimalac vrednost abributa primalac je " + actual,
				primalac.equals(actual));
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

	@Test
	public void metoda_setPoruka_zdravo() {
		String poruka = "zdravo";
		try {
			instance.setPoruka(poruka);
		} catch (RuntimeException e) {
			assertTrue("Za prosledjeni argument " + poruka + " metoda setPoruka baca neproveravani izuzetak", false);
		}
		String actual = (String) TestUtil.getFieldValue(instance, "poruka");
		assertTrue("Za prosledjeni argument " + poruka
				+ ", nakon izvrsetka metode setPoruka vrednost abributa poruka je " + actual, poruka.equals(actual));
	}

	@Test
	public void metoda_setVreme_vidljivost() {
		assertTrue("Metoda setVreme nije javna", TestUtil.hasMethodModifier(Poruka.class, "setVreme",
				new Class<?>[] { GregorianCalendar.class }, Modifier.PUBLIC));
	}

	@Test(expected = RuntimeException.class)
	public void metoda_setVreme_null() {
		GregorianCalendar vreme = null;
		instance.setVreme(vreme);
		assertTrue("Za prosledjeni argument null metoda setVreme ne baca neproveravani izuzetak", false);
	}

	@Test
	public void metoda_setVreme_trenutniDatum() {
		GregorianCalendar vreme = new GregorianCalendar();
		try {
			instance.setVreme(vreme);
		} catch (RuntimeException e) {
			assertTrue("Za prosledjeni argument " + vreme.getTime().toString()
					+ " metoda setVreme baca neproveravani izuzetak", false);
		}
		GregorianCalendar actual = (GregorianCalendar) TestUtil.getFieldValue(instance, "vreme");
		assertTrue(
				"Za prosledjeni argument " + vreme.getTime().toString()
						+ ", nakon izvrsetka metode setVreme vrednost abributa vreme je " + actual.getTime().toString(),
				vreme.equals(actual));
	}

	@Test
	public void metoda_toString() {
		String posiljalac = "Marko";
		String primalac = "Darko";
		String poruka = "zdravo";
		instance.setPosiljalac(posiljalac);
		instance.setPrimalac(primalac);
		instance.setPoruka(poruka);
		posiljalac = (String) TestUtil.getFieldValue(instance, "posiljalac");
		primalac = (String) TestUtil.getFieldValue(instance, "primalac");
		poruka = (String) TestUtil.getFieldValue(instance, "poruka");
		String result = instance.toString();

		int indexPosiljalac = result.indexOf("POSILJALAC");
		assertTrue("String koji vraca metoda toString ne sadrzi rec \"POSILJALAC\"", indexPosiljalac != -1);

		int indexPrimalac = result.indexOf("PRIMALAC");
		assertTrue("String koji vraca metoda toString ne sadrzi rec \"PRIMALAC\"", indexPrimalac != -1);
		assertTrue("Rec \"PRIMALAC\" se ne nalazi na ispravnom mestu u rezultatu koji vraca metoda toString",
				indexPrimalac > indexPosiljalac);

		int indexPoruka = result.indexOf("PORUKA");
		assertTrue("String koji vraca metoda toString ne sadrzi rec \"PORUKA\"", indexPoruka != -1);
		assertTrue("Rec \"PORUKA\" se ne nalazi na ispravnom mestu u rezultatu koji vraca metoda toString",
				indexPoruka > indexPrimalac);

		int indexPosiljalacValue = result.indexOf(posiljalac);
		assertTrue("String koji vraca metoda toString ne sadrzi vrednost atributa posiljalac",
				indexPosiljalacValue != -1);
		assertTrue(
				"String koji vraca metoda toString nije u doborom formatu, vrednost atributa posiljalac nije na ispravnom mestu",
				indexPosiljalacValue > indexPosiljalac && indexPosiljalacValue < indexPrimalac);

		int indexPrimalacValue = result.indexOf(primalac);
		assertTrue("String koji vraca metoda toString ne sadrzi vrednost atributa primalac", indexPrimalacValue != -1);
		assertTrue(
				"String koji vraca metoda toString nije u doborom formatu, vrednost atributa primalac nije na ispravnom mestu",
				indexPrimalacValue > indexPrimalac && indexPrimalacValue < indexPoruka);

		int indexPorukaValue = result.indexOf(poruka);

		assertTrue("String koji vraca metoda toString ne sadrzi vrednost atributa poruka", indexPorukaValue != -1);

		assertTrue(
				"String koji vraca metoda toString nije u doborom formatu, vrednost atributa poruka nije na ispravnom mestu",
				indexPorukaValue > indexPoruka);
	}

}
