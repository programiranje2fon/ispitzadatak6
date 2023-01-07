package ispravka_koda;

import java.util.Scanner;

public class UcitavanjeSaTastature4 {
	public static void ucitajIIzracunajNumeroloskiBroj() {
		Scanner s = new Scanner(System.in);
		System.out.println("Unesite dan,mesec i godinu u 3 reda");
		int brojSaSvimCiframa = (s.nextInt() * 100 + s.nextInt()) * 1000 + s.nextInt();
		int sumaCifara;
		do {
			sumaCifara = 0;
			while (brojSaSvimCiframa > 0) {
				sumaCifara = sumaCifara + brojSaSvimCiframa % 10;
				brojSaSvimCiframa = brojSaSvimCiframa / 10;
			}
			
			brojSaSvimCiframa = sumaCifara;
		} while (sumaCifara > 9 && sumaCifara != 11 && sumaCifara != 22);
		System.out.println("Vas numeroloski broj je: " + sumaCifara);
	}
}