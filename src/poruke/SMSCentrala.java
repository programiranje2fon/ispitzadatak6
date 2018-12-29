package poruke;

import java.io.*;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import poruke.sms.api.SMSAPI;

public class SMSCentrala implements SMSAPI {
	
	private Poruka[] poruke = new Poruka[1000];
	
	public void unesi(Poruka p){
		for (int i = 0; i < poruke.length; i++) 
			if (poruke[i]==null){
				poruke[i]=p;
				break;
			}
		
		try{
			PrintWriter out = new PrintWriter(new FileWriter("backup.txt"));
			
			for (int i = 0; i < poruke.length; i++)
				if (poruke[i]!=null)
					out.println(poruke[i]);
			
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void arhivirajPoruke() {
		//Izracunavanje koja je prosla godina
		int proslaGodina = new GregorianCalendar().get(GregorianCalendar.YEAR)-1;
		
		try{
			PrintWriter out = new PrintWriter(new FileWriter("arhiva.txt"));
			//Prolazenje kroz niz i smestanje svake poruke koja nije null i
			//koja je iz prosle godine u fajl i brisanje iz niza
			for (int i = 0; i < poruke.length; i++) 
				if (poruke[i]!=null && 
				poruke[i].getVreme().get(GregorianCalendar.YEAR)==proslaGodina){
					//Upisivanje poruke u fajl
					out.println(poruke[i].toString());
					//Tek onda brisanje poruke iz niza
					poruke[i]=null;
				}
			
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public LinkedList<Poruka> vratiPoruke(String korisnik) {
		LinkedList<Poruka> novePoruke = new LinkedList<Poruka>();
		
		//Pretrazivanje liste poruka i pronalazenje poruka koje imaju
		//bar dva smajlija. Prvi smajli se pronalazi preko indexOf metode
		//pretragom celog teksta poruke tj. poruke[i].getPoruka().indexOf(":)")
		//a drugi preko indexOf metode ali pretragom onog dela teksta poruke
		//koji je posle prvog smajlija tj pretrazuje se u okviru
		//poruke[i].getPoruka().substring(poruke[i].getPoruka().indexOf(":)")+2).
		for (int i = 0; i < poruke.length; i++)
			if (poruke[i]!=null &&
				poruke[i].getPoruka().indexOf(":)")!=-1 &&
				poruke[i].getPoruka().substring(poruke[i].getPoruka().indexOf(":)")+2).indexOf(":)")!=-1){
					//Svaka takva poruka koja se nadje, se unosi u listu ali tako da poruke budu
					//poredjane po vremenu poruke. Kad se u novoj listi nadje poruka koja je
					//poslata kasnije od nove, nova se unosi na njeno mesto a sve ostale se 
					//pomeraju udesno.
					for (int j = 0; j < novePoruke.size(); j++)
						if (poruke[i].getVreme().before(novePoruke.get(j).getVreme())){
							novePoruke.add(j, poruke[i]);
							break;
						}
								
					//Ako nova poruka ipak nije uneta (najnovijeg je datuma ili
					//je lista prazna, unosi se na kraj liste
					if (!novePoruke.contains(poruke[i]))
						novePoruke.addLast(poruke[i]);
					}
				
		
		return novePoruke;
	}

	

}