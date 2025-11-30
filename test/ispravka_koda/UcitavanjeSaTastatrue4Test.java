package ispravka_koda;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ispravka_koda.UcitavanjeSaTastature4;

public class UcitavanjeSaTastatrue4Test {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private InputStream in;
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;
	private final InputStream originalIn = System.in;

	@Before
	public void setUp() throws Exception {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void tearDown() throws Exception {
		System.setOut(originalOut);
		System.setErr(originalErr);
		System.setIn(originalIn);
	}

	private int pomocna(int d, int m, int y) {
		Scanner s = new Scanner(System.in);
		System.out.println("Unesite dan,mesec i godinu u 3 reda");
		int brojSaSvimCiframa = (d * 100 + m) * 1000 + y;
		int sumaCifara;

		do {
			sumaCifara = 0;
			while (brojSaSvimCiframa > 0) {
				sumaCifara = sumaCifara + brojSaSvimCiframa % 10;
				brojSaSvimCiframa = brojSaSvimCiframa / 10;
			}

			brojSaSvimCiframa = sumaCifara;
		} while (sumaCifara > 9 && sumaCifara != 11 && sumaCifara != 22);

		return sumaCifara;
	}

	private void setInput(int d, int m, int y) {
		String s = d + "\n" + m + "\n" + y + "\n";
		in = new ByteArrayInputStream(s.getBytes());
		System.setIn(in);
	}

	private void proveri(int d, int m, int y) {
		setInput(d, m, y);
		UcitavanjeSaTastature4.ucitajIIzracunajNumeroloskiBroj();
		outContent.toString();
		String ispis = outContent.toString();
		int rezultat = Integer.parseInt(ispis.substring(ispis.indexOf(':') + 1).trim());
		int ocekivano = pomocna(d, m, y);
		assertTrue(
				"Metoda ucitajIIzracunajNumeroloskiBroj za uneti datum rodjenja " + d + "." + m + "." + y
						+ ". vraca da je numeroloski broj " + rezultat + ", umesto " + ocekivano,
				rezultat == ocekivano);
	}

	@Test(timeout = 2000)
	public void metoda_ucitajIIzracunajNumeroloskiBroj_1_1_2000() {
		proveri(1,1,2000);
	}
	
	@Test(timeout = 2000)
	public void metoda_ucitajIIzracunajNumeroloskiBroj_28_12_1980() {
		proveri(28,12,1980);
	}
	
	@Test(timeout = 2000)
	public void metoda_ucitajIIzracunajNumeroloskiBroj_9_12_1979() {
		proveri(9,12,1979);
	}
	
	@Test(timeout = 2000)
	public void metoda_ucitajIIzracunajNumeroloskiBroj_9_12_1978() {
		proveri(9,12,1978);
	}
	
	@Test(timeout = 2000)
	public void metoda_ucitajIIzracunajNumeroloskiBroj_5_6_2003() {
		proveri(5,6,2003);
	}
	
	@Test(timeout = 2000)
	public void metoda_ucitajIIzracunajNumeroloskiBroj_7_8_2010() {
		proveri(7,8,2010);
	}
}
