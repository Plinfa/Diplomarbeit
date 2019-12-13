import java.awt.EventQueue;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.Task;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class GUI_Gantt_ChartDesigner {

	public JFrame frame4;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	
	//Wartecker Marcel
	public GUI_Gantt_ChartDesigner(JDBC_MariaDB jdbc,IntervalCategoryDataset dataset) {
		
		initialize(jdbc, dataset);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JDBC_MariaDB jdbc,IntervalCategoryDataset dataset ) {
		frame4 = new JFrame();
		frame4.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI_Gantt_ChartDesigner.class.getResource("/ressources/EQOS.jpg")));
		frame4.setTitle("Personal- und Projektmanager");
		frame4.setBounds(100, 100, 1100, 624);
		frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		
		//IntervalCategoryDataset dataset = jdbc.zeitplanung();
		

		JFreeChart chart = ChartFactory.createGanttChart("Projektplanung", // Chart title
				"Projekte", // X-Axis Label
				"Timeline", // Y-Axis Label
				dataset);

		ChartPanel panel = new ChartPanel(chart);
		frame4.setContentPane(panel);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1084, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 585, Short.MAX_VALUE)
		);
		panel.setLayout(gl_panel);

	}
	
	

	private IntervalCategoryDataset getZeitplanMitarbeiter() {

		
		//mit Methoden von Project
		TaskSeries gruppe = new TaskSeries("Gruppe");
		gruppe.add(new Task("Nices", Date.from(LocalDate.of(2019, 8, 3).atStartOfDay().toInstant(ZoneOffset.UTC)),
				Date.from(LocalDate.of(2019, 9, 30).atStartOfDay().toInstant(ZoneOffset.UTC))));

		
		//ohne Project
		TaskSeries series2 = new TaskSeries("andereGruppe");
		series2.add(
				new Task("Nices", Date.from(LocalDate.of(2019, 7, 3).atStartOfDay().toInstant(ZoneOffset.UTC)),
						Date.from(LocalDate.of(2019, 7, 30).atStartOfDay().toInstant(ZoneOffset.UTC))));

		

		TaskSeriesCollection dataset = new TaskSeriesCollection();
		dataset.add(gruppe);
		dataset.add(series2);
		return dataset;
	}

}
