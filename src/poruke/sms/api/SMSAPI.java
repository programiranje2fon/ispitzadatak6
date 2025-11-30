package poruke.sms.api;

import java.util.LinkedList;

import poruke.Poruka;

public interface SMSAPI {
	
	public LinkedList<Poruka> vratiPoruke (String korisnik);
	
	public void arhivirajPoruke();

}