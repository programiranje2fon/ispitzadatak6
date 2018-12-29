U produžetku teksta je dat kod klase sa metodom koja bi trebalo da sa tastature učita 3 cela broja u tri reda
koji predstavljaju dan, mesec i godinu rođenja neke osobe. Nakon toga, metoda bi trebalo da izračuna
numerološki horoskopski znak (odnosno broj) te osobe. Numerološki znaci su brojevi 1, 2, 3, 4, 5, 6, 7, 8, 9,
ali i 11 i 22. Numerološki broj se izračunava tako što se saberu sve cifre iz dana, meseca i godine rođenja
osobe. Moguće je da rezultat takvog sabiranja bude i neki dvocifren broj koji nije 11 ili 22, pa se onda
sabiranje cifara ponavlja nad novodobijenim rezultatom koliko god puta je potrebno. Na primer:
1. za osobu rođenu datuma 1.1.2000. numerološki znak je 1 + 1 + 2 + 0 + 0 + 0 = 4
2. za osobu rođenu datuma 28.12.1980. numerološki znak je 2 + 8 + 1 + 2 + 1 + 9 + 8 + 0 = 31 (pa se
nastavlja sabiranje) = 3 + 1 = 4
3. za osobu rođenu datuma 9.12.1979. numerološki znak je 9 + 1 + 2 + 1 + 9 + 7 + 9 = 38 = 3 + 8 = 11
4. za osobu rođenu datuma 9.12.1978. numerološki znak je 9 + 1 + 2 + 1 + 9 + 7 + 8 = 37 = 3 + 7 = 10
= 1 + 0 = 1

Dati kod se kompajlira, ali ne radi to šta treba. Napraviti javnu klasu **UcitavanjeSaTastature4** u paketu
**ispravka_koda**, prekucati u nju kod koji je dat i, __uz minimalne izmene__ ga ispraviti tako da funkcioniše
kako treba. Napraviti test klasu i, koristeći njenu main metodu, pozvati metodu
**ucitajIIzracunajNumeroloskiBroj()** i proveriti njen rad.

	package ispravka_koda;
	
	import java.util.Scanner;
	
	public class UcitavanjeSaTastature4 {
		
		public static void ucitajIIzracunajNumeroloskiBroj() {
			
			Scanner s = new Scanner(System.in);
			
			System.out.println("Unesite dan,mesec i godinu u 3 reda");
			
			int brojSaSvimCiframa = (s.nextInt() * 100 + s.nextInt()) * 1000 + s.nextInt();
			
			int sumaCifara;
			
			do{
				
				sumaCifara = 0;
				
				while (brojSaSvimCiframa > 0) {
					
					sumaCifara = sumaCifara + brojSaSvimCiframa % 10;
					
					brojSaSvimCiframa = brojSaSvimCiframa / 10;
				
				}		
				
				sumaCifara = brojSaSvimCiframa;
			
			}while(sumaCifara > 9 && sumaCifara!=11 && sumaCifara!=22);
			
			System.out.println("Vas numeroloski broj je: "+sumaCifara);
			
		}
	
	}