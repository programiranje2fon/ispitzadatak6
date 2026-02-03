# Zadatak 1

Napraviti javnu apstraktnu klasu **Poruka** u paketu **poruke** koja ima:
* Privatni atribut **posiljalac** koji predstavlja identifikator korisnika koji je poslao poruku (String).
* Privatni atribut **primalac** koji predstavlja identifikator korisnika koji treba da primi poruku (String).
* Privatni atribut **poruka** koji predstavlja tekst poruke.
* Privatni atribut **vreme** koji predstavlja datum i vreme slanja poruke (klasa LocalDateTime)
* Odgovarajuće javne get i set metode za ove atribute. Nedozvoljene vrednosti za atribute pošiljalac i
primalac su NULL i prazan String, a poruka i vreme ne smeju biti NULL. U slučaju unosa ovih
nedozvoljenih vrednosti potrebno je baciti izuzetak sa odgovarajućim tekstom.
* Redefinisanu metodu toString klase Object koja vraća String u kome se nalaze neki podaci poruke u
formatu **“POSILJALAC #### PRIMALAC #### PORUKA ####”**.

Napraviti javnu klasu **SMS** u paketu **poruke.sms** koja nasleđuje klasu Poruka i ima:
* __Redefinisanu__ metodu setPoruka koja ne dozvoljava unos NULL Stringa, ali ni Stringa dužeg od 170
znakova. U slučaju unosa nedozvoljenih vrednosti baciti izuzetak sa odgovarajućim tekstom.

Napraviti javni interfejs **SMSAPI** u paketu **poruke.sms.api** koji ima:
* Javnu metodu vratiPoruke koja kao ulaz prima identifikator korisnika i vraća listu objekata Poruka.
* Javnu metodu arhivirajPoruke koja nema ulazne parametre i ne vraća ništa.

Napraviti javnu klasu **SMSCentrala** u paketu **poruke** koja implem. interfejs **SMSAPI** i ima:
* Privatni atribut **poruke** koji predstavlja niz obj. klase Poruka.Niz inicijalizovati na 1000 mesta.
* Javnu metodu **unesi** koja kao ulaz prima objekat klase Poruka i unosi ovu poruku na prvo slobodno
mesto u nizu. Mesto je slobodno ako je element na tom mestu NULL. Kad se izvrši unos, potrebno
je u tekstualni fajl “backup.txt” upisati sve poruke iz niza (preskočiti NULL elemente tj. prazna
mesta).
* __Implementiranu metodu arhivirajPoruke__ koja u tekstualni fajl “arhiva.txt” unosi sve poruke iz prošle
godine i korišćenjem metode toString. Sve arhivirane poruke je potrebno izbrisati iz
niza.
* __Implementiranu metodu vratiPoruke__ koja vraća listu onih poruka u kojima se pojavljuju makar dva
smajlija. Poruke u listi treba da budu poređane tako da na početku budu najstarije poruke pa tek
onda novije. Pri tome, zna se da se smajli piše isključivo na ovaj način: :)

# Zadatak 2 - Ispravka koda

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