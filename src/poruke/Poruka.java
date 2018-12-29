package poruke;

import java.util.GregorianCalendar;

public abstract class Poruka {
	
	private String posiljalac, primalac, poruka;
	private GregorianCalendar vreme;
	
	public String getPosiljalac() {
		return posiljalac;
	}
	
	public void setPosiljalac(String posiljalac) {
		if (posiljalac==null || posiljalac.equals(""))
			throw new RuntimeException("Morate uneti posiljaoca");
		
		this.posiljalac = posiljalac;
	}
	
	public String getPrimalac() {
		return primalac;
	}
	
	public void setPrimalac(String primalac) {
		if (primalac==null || primalac.equals(""))
			throw new RuntimeException("Morate uneti primaoca");
		
		this.primalac = primalac;
	}
	
	public String getPoruka() {
		return poruka;
	}
	
	public void setPoruka(String poruka) {
		if (poruka==null)
			throw new RuntimeException("Morate uneti poruku");
		
		this.poruka = poruka;
	}
	
	public GregorianCalendar getVreme() {
		return vreme;
	}
	
	public void setVreme(GregorianCalendar vreme) {
		if (vreme==null)
			throw new RuntimeException("Morate uneti vreme");
		
		this.vreme = vreme;
	}
	
	public String toString(){
		return "POSILJALAC:"+posiljalac+" PRIMALAC:"+primalac+" PORUKA:"+poruka;
	}

}