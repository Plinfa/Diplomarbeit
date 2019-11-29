import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.*;

import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;

public class Project {

	protected String nameProj;
	protected int kostenstelle; // Projektnummer
	protected Date startdatum;
	protected Date enddatum;
	protected String kundenname;
	protected int kundennummer;
	private int requiredempl;
	private int wantedempl;
	private int assignedempl;
	
	private double kosten;
	

	public Project(int kostenstelle, String nameProj, Date startdatum, Date enddatum) {
		this.kostenstelle=kostenstelle;
		this.nameProj = nameProj;
		this.startdatum= startdatum;
		this.enddatum= enddatum;
		
		

	}

	
	
	public Date getStartdatum() {
		
		return startdatum;
	}


	public void setStartdatum(Date startdatum) {
		this.startdatum = startdatum;
	}


	public Date getEnddatum() {
		return enddatum;
	}


	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}


	

	public String getNameProj() {
		return nameProj;
	}

	public void setNameProj(String nameProj) {
		this.nameProj = nameProj;
	}

	public int getKostenstelle() {
		return kostenstelle;
	}

	public void setKostenstelle(int kostenstelle) {
		this.kostenstelle = kostenstelle;
	}

	

	public int getRequiredempl() {
		return requiredempl;
	}

	public void setRequiredempl(int requiredempl) {
		this.requiredempl = requiredempl;
	}

	public int getWantedempl() {
		return wantedempl;
	}

	public void setWantedempl(int wantedempl) {
		this.wantedempl = wantedempl;
	}

	public int getAssignedempl() {
		return assignedempl;
	}

	public void setAssignedempl(int assignedempl) {
		this.assignedempl = assignedempl;
	}


	public double getKosten() {
		return kosten;
	}

	public void setKosten(double kosten) {
		this.kosten = kosten;
	}

	public String getKundenname() {
		return kundenname;
	}

	public void setKundenname(String kundenname) {
		this.kundenname = kundenname;
	}

	public int getKundennummer() {
		return kundennummer;
	}

	public void setKundennummer(int kundennummer) {
		this.kundennummer = kundennummer;
	}


}
