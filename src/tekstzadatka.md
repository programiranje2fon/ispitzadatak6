#Zadatak 2 – jun 2012

Napraviti javnu apstraktnu klasu **Poruka** u paketu **poruke** koja ima:
* Privatni atribut **posiljalac** koji predstavlja identifikator korisnika koji je poslao poruku (String).
* Privatni atribut **primalac** koji predstavlja identifikator korisnika koji treba da primi poruku (String).
* Privatni atribut **poruka** koji predstavlja tekst poruke.
* Privatni atribut **vreme** koji predstavlja datum i vreme slanja poruke (klasa GregorianCalendar)
* Odgovarajuće javne get i set metode za ove atribute. Nedozvoljene vrednosti za atribute pošiljalac i
primalac su NULL i prazan String, a poruka i vreme ne smeju biti NULL. U slučaju unosa ovih
nedozvoljenih vrednosti potrebno je baciti izuzetak sa odgovarajućim tekstom.
* Redefinisanu metodu toString klase Object koja vraća String u kome se nalaze neki podaci poruke u
formatu **“POSILJALAC_####_PRIMALAC_####_PORUKA_####”**.

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