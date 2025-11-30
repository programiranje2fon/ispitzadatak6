package poruke.sms;

import poruke.Poruka;

public class SMS extends Poruka {
	
	public void setPoruka(String poruka){
		if (poruka==null || poruka.length()>170)
			throw new RuntimeException("Morate uneti poruku koja nije duza od 170 znakova");
		
		super.setPoruka(poruka);
	}

}