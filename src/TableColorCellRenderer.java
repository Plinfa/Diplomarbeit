import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class TableColorCellRenderer implements TableCellRenderer {
	private static final TableCellRenderer RENDERER=new DefaultTableCellRenderer();
    @Override
    public Component getTableCellRendererComponent(
       JTable table, Object value, 
       boolean isSelected, boolean hasFocus, 
       int row, int column) {
    	
    	Component c =RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    	
    	if (row==6) {
    		Object result =table.getModel().getValueAt(row, column);
    		int ben=Integer.parseInt(result.toString());
    		Color color=null;
    		
    		if (ben>0) {
    			color=Color.RED;
    		}
    		
    	}
    	
    	
    	return c;
    }
    
 }