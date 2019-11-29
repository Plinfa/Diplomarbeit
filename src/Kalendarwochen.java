
import java.util.*;
import java.util.GregorianCalendar;
public class Kalendarwochen {
	
	
	private boolean krank;
	private boolean urlaub;
	private boolean schulung;
	private Project zugeteilt;
	
	
	public Kalendarwochen() {
		
		
		zugeteilt=null;
		krank=false;
		urlaub=false;
		schulung=false;
		
	}

	public boolean isKrank() {
		return krank;
	}

	public void setKrank(boolean krank) {
		this.krank = krank;
	}

	public boolean isUrlaub() {
		return urlaub;
	}

	public void setUrlaub(boolean urlaub) {
		this.urlaub = urlaub;
	}

	public Project getZugeteilt() {
		return zugeteilt;
	}

	public void setZugeteilt(Project zugeteilt) {
		
		
		
		this.zugeteilt = zugeteilt;
	}

	public boolean isSchulung() {
		return schulung;
	}

	public void setSchulung(boolean schulung) {
		this.schulung = schulung;
	}
	
}
