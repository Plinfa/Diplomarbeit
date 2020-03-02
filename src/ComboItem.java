
public class ComboItem {
	
		private String name;
	    private int PartieNr;

	    public ComboItem(String name, int PartieNr)
	    {
	        this.name = name;
	        this.PartieNr = PartieNr;
	    }

	    @Override
	    public String toString()
	    {
	        return name ;
	    }

	    public String getname()
	    {
	        return name;
	    }

	    public int getPartieNr()
	    {
	        return PartieNr;
	    }
}
