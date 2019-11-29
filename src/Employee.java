import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Employee {
	
	ArrayList<Kalendarwochen> wochen;
	

	private int persnr;
	private String name;
	 

	public Employee(int persnr) {
		this.persnr=persnr;
		wochen =new ArrayList<Kalendarwochen>();
		arrayList_fuellen();

	}
	
	public void arrayList_fuellen() {
		
		for (int i=0; i<=107; i++) {
			Kalendarwochen xx =new Kalendarwochen();
			wochen.add(xx);
			
		}
	
		Kalendarwochen ausgabe =new Kalendarwochen();
		for (int i=0; i<=107; i++) {
			ausgabe= wochen.get(i);
			//System.out.println(ausgabe.isKrank());
			//System.out.println(ausgabe.isUrlaub());
			//System.out.println(ausgabe.getZugeteilt().getNameProj());
		}
	}
	
	
    public ArrayList<Kalendarwochen> getWochen() {
		return wochen;
	}

	public void setWochen(ArrayList<Kalendarwochen> wochen) {
		this.wochen = wochen;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getPersnr() {
		return persnr;
	}

	public void setPersnr(int persnr) {
		this.persnr = persnr;
	}
	
	
}