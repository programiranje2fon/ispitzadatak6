package poruke;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import poruke.Poruka;
import poruke.SMSCentrala;
import poruke.sms.SMS;
import test.TestUtil;

public class SMSCentralaTest {

	SMSCentrala instance;

	@Before
	public void setUp() throws Exception {
		instance = new SMSCentrala();
	}

	@After
	public void tearDown() throws Exception {
		instance = null;
	}

	@Test
	public void atribut_poruke() {
		assertTrue("Nije definisan atribut poruke", TestUtil.doesFieldExist(SMSCentrala.class, "poruke"));
	}

	@Test
	public void atribut_poruke_vidljivost() {
		assertTrue("Atribut poruke nije privatan",
				TestUtil.hasFieldModifier(SMSCentrala.class, "poruke", Modifier.PRIVATE));
	}

	@Test
	public void atribut_poruke_pocetnaVrednost() {
		Poruka[] value = (Poruka[]) TestUtil.getFieldValue(instance, "poruke");
		assertTrue("Niz poruka nije inicijalizovan na 1000 mesta", value.length == 1000);
	}

	@Test
	public void metoda_unesi_vidljivost() {
		assertTrue("Metoda unesi nije javna", TestUtil.hasMethodModifier(SMSCentrala.class, "unesi",
				new Class<?>[] { Poruka.class }, Modifier.PUBLIC));
	}

	private String vratiSadrzajFajla(String naziv) {
		String fajl = new String();
		try (BufferedReader br = new BufferedReader(new FileReader(naziv))) {
			String line;
			while ((line = br.readLine()) != null) {
				fajl = fajl.concat(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
			assertTrue("Ne postoji fajl " + naziv, false);
		}
		return fajl;
	}

	private void proveriBackup() {
		Poruka[] poruke = (Poruka[]) TestUtil.getFieldValue(instance, "poruke");
		String fajl = vratiSadrzajFajla("backup.txt");

		for (Poruka p : poruke) {
			if (p != null) {
				// nepohodno je ovako zbog mogucnosti
				// da razlicite poruke izaju isti
				// rezultat koji vraca toString
				if (fajl.indexOf(p.toString()) != -1) {
					fajl = fajl.replaceFirst(p.toString(), "");
				} else {
					assertTrue("Nisu sve poruke upisane u fajl backup.txt", false);
				}
			}
		}

	}

	@Test(timeout = 2000)
	public void metoda_unesi() {
		inicijalizuj();
		Poruka[] poruke = (Poruka[]) TestUtil.getFieldValue(instance, "poruke");
		int slobodno = -1;
		for (int i = 0; i < poruke.length; i++) {
			if (poruke[i] == null) {
				slobodno = i;
				break;
			}
		}

		Poruka p = new SMS();

		instance.unesi(p);

		assertTrue("Poruka nije uneta na prvo slobodno mesto", poruke[slobodno].equals(p));

		proveriBackup();
	}

	@Test
	public void metoda_arhivirajPoruke_vidljivost() {
		assertTrue("Metoda arhivirajPoruke nije javna",
				TestUtil.hasMethodModifier(SMSCentrala.class, "arhivirajPoruke", null, Modifier.PUBLIC));
	}

	private LinkedList<Poruka> vratiListu() {
		LinkedList<Poruka> lista = new LinkedList<>();
		int proslaGodina = new GregorianCalendar().get(GregorianCalendar.YEAR) - 1;
		Poruka[] poruke = (Poruka[]) TestUtil.getFieldValue(instance, "poruke");
		for (Poruka p : poruke) {
			if (p != null && p.getVreme().get(GregorianCalendar.YEAR) == proslaGodina) {
				lista.add(p);
			}
		}
		return lista;
	}

	private void proveriDaLiSuObrisane(LinkedList<Poruka> lista) {
		Poruka[] poruke = (Poruka[]) TestUtil.getFieldValue(instance, "poruke");
		for (Poruka p : poruke) {
			if (p != null) {
				assertTrue("Iz niza poruka nisu obrisane sve poruke iz prosle godine", !lista.contains(p));
			}
		}
	}

	private void proveriArhivu(LinkedList<Poruka> lista) {
		String fajl = vratiSadrzajFajla("arhiva.txt");

		for (Poruka p : lista) {
			if (fajl.indexOf(p.toString()) != -1) {
				// nepohodono u slucaju da postoje vise
				// istih trojki posiljalac, primalac, poruka

				fajl = fajl.replaceFirst(p.toString(), "");
			} else {
				assertTrue("Nisu upisane sve proslogodisnje poruke u fajl arhiva.txt", false);
			}
		}

		Poruka[] poruke = (Poruka[]) TestUtil.getFieldValue(instance, "poruke");

		for (Poruka p : poruke) {
			if (p != null) {
				assertTrue("U fajl arhiva.txt su upisane i poruke koje nisu iz prosle godine",
						fajl.indexOf(p.toString()) == -1);
			}
		}
	}

	public void dodaj(Poruka p) {
		Poruka[] poruke = (Poruka[]) TestUtil.getFieldValue(instance, "poruke");
		for (int i = 0; i < poruke.length; i++)
			if (poruke[i] == null) {
				poruke[i] = p;
				break;
			}
	}

	private void inicijalizuj() {
		Poruka p = new SMS();
		String poruka = "Zdravo";
		String posiljalac = "Marko";
		String primalac = "Darko";
		GregorianCalendar vreme = new GregorianCalendar();
		GregorianCalendar vreme1 = new GregorianCalendar();
		vreme1.add(GregorianCalendar.YEAR, -1);

		p.setPoruka(poruka);
		p.setPosiljalac(posiljalac);
		p.setPrimalac(primalac);
		p.setVreme(vreme);
		dodaj(p);

		Poruka p1 = new SMS();
		p1.setPoruka(poruka);
		p1.setPosiljalac(posiljalac);
		p1.setPrimalac(primalac);
		p1.setVreme(vreme1);
		dodaj(p1);

		Poruka p2 = new SMS();
		p2.setPoruka(poruka);
		p2.setPosiljalac(posiljalac);
		p2.setPrimalac(primalac);
		p2.setVreme(vreme1);
		dodaj(p2);
	}

	// Za metodu arhiviraj poruke, promenio sam da
	// se ispis vrsi koriscenjem toString metode
	// jer je vec implementirana, i promenjeno
	// je da se upisuje u txt fajl, posto mi je receno
	// da su binarni izbaceni

	@Test(timeout = 2000)
	public void metoda_arhivirajPoruke() {
		inicijalizuj();
		LinkedList<Poruka> lista = vratiListu();
		instance.arhivirajPoruke();
		proveriDaLiSuObrisane(lista);
		proveriArhivu(lista);
	}

	@Test
	public void metoda_vratiPoruke_vidljivost() {
		assertTrue("Metoda vratiPoruke nije javna", TestUtil.hasMethodModifier(SMSCentrala.class, "vratiPoruke",
				new Class<?>[] { String.class }, Modifier.PUBLIC));
	}

	private LinkedList<Poruka> vratiPorukePomocna(String korisnik) {
		LinkedList<Poruka> novePoruke = new LinkedList<Poruka>();
		Poruka[] poruke = (Poruka[]) TestUtil.getFieldValue(instance, "poruke");

		for (int i = 0; i < poruke.length; i++)
			if (poruke[i] != null && poruke[i].getPoruka().indexOf(":)") != -1
					&& poruke[i].getPoruka().substring(poruke[i].getPoruka().indexOf(":)") + 2).indexOf(":)") != -1) {

				for (int j = 0; j < novePoruke.size(); j++)
					if (poruke[i].getVreme().before(novePoruke.get(j).getVreme())) {
						novePoruke.add(j, poruke[i]);
						break;
					}

				if (!novePoruke.contains(poruke[i]))
					novePoruke.addLast(poruke[i]);
			}

		return novePoruke;
	}

	private void inicijalizuj1() {
		Poruka p = new SMS();
		String poruka = "Zdravo :) :)";
		String poruka1 = "Zdravo :)(::";
		String poruka2 = ":)Zdravo :)";
		String posiljalac = "Marko";
		String primalac = "Darko";
		GregorianCalendar vreme = new GregorianCalendar();
		GregorianCalendar vreme1 = new GregorianCalendar();
		vreme1.add(GregorianCalendar.YEAR, -1);

		p.setPoruka(poruka);
		p.setPosiljalac(posiljalac);
		p.setPrimalac(primalac);
		p.setVreme(vreme);
		dodaj(p);

		Poruka p1 = new SMS();
		p1.setPoruka(poruka1);
		p1.setPosiljalac(posiljalac);
		p1.setPrimalac(primalac);
		p1.setVreme(vreme1);
		dodaj(p1);

		Poruka p2 = new SMS();
		p2.setPoruka(poruka2);
		p2.setPosiljalac(posiljalac);
		p2.setPrimalac(primalac);
		p2.setVreme(vreme1);
		dodaj(p2);
	}

	@Test(timeout = 2000)
	public void metoda_vratiPoruke() {
		String korisnik = "Marko";
		inicijalizuj1();
		LinkedList<Poruka> ocekivana = vratiPorukePomocna(korisnik);
		LinkedList<Poruka> stvarna = instance.vratiPoruke(korisnik);

		for (Poruka p : ocekivana) {
			assertTrue("U listi se ne nalaze nalazi poruka " + p.getPoruka(), stvarna.contains(p));
		}

		for (Poruka p : stvarna) {
			assertTrue("U listi se nalazi poruka " + p.getPoruka(), ocekivana.contains(p));
		}

		assertTrue("U listi se postoje poruke koje su ponovljene vise puta", ocekivana.size() == stvarna.size());

		for (int i = 0; i < ocekivana.size(); i++) {
			Poruka p1 = ocekivana.get(i);
			Poruka p2 = stvarna.get(i);
			assertTrue("U listi se poruka sa vremenom " + p2.getVreme().getTime().toString() + " nalazi pre poruke "
					+ p1.getVreme().getTime().toString(), p1.equals(p2) || p1.getVreme().equals(p2.getVreme()));
		}
	}
}
