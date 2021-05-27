package com.rhodehouse.fantasycalendar;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import static j2html.TagCreator.*;

public class FantasyCalendar extends JFrame{
	private JButton genButton;
	private JSpinner startDaySpinner;
	private ComponentPanel monthView, weekView;
	private JPanel mainPanel;
	private Month[] months;

	public FantasyCalendar() {
		genButton = new JButton("Generate");
		startDaySpinner = new JSpinner();
		genButton.addActionListener(new GenerateButtonHandler());
		monthView = new ComponentPanel("Months", new MonthComponent(1), new MonthAddHandler());
		weekView = new ComponentPanel("Weekdays",new WeekdayComponent(1), new WeekdayAddHandler());
		mainPanel = new JPanel(new BorderLayout());
		
		setLayout(new BorderLayout());
		
		add(startDaySpinner, BorderLayout.NORTH);
		mainPanel.add(weekView, BorderLayout.WEST);
		mainPanel.add(monthView, BorderLayout.EAST);
		add(mainPanel, BorderLayout.CENTER);
		add(genButton, BorderLayout.SOUTH);
		
		pack();
	}
	
	private void generate(int startDay) {
		ArrayList<String> weekdayNames = new ArrayList<String>(weekView.getNumComponents());
		months = new Month[monthView.getNumComponents()];
		for (int i=0; i < weekdayNames.size();i++)
			weekdayNames.add(((WeekdayComponent) weekView.getCalComponents().get(i)).getDayName());
		
		for (int i=0; i < monthView.getNumComponents(); i++) {
			MonthComponent monthComp = (MonthComponent) monthView.getCalComponents().get(i);
			months[i] = new Month(monthComp.getMonthName(), monthComp.getNumDays(), weekdayNames, startDay);
		}
		
		System.out.println(toString());
		outputToFile(toString(),"test.html");
	}
	
	public String toString() {
		String result = "";
		
		for (int i=0; i < months.length; i++)
			result += months[i].toString();
		
		return result;
	}
	
	private void outputToFile(String s, String filename) {
		File f = new File(filename);
		try {
			f.createNewFile();
			FileWriter writ = new FileWriter(f);
			String fileContents = html(head(title("Month Testing")), body(months[0].toHTML())).render();
			writ.write(fileContents);
			writ.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class MonthAddHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			monthView.addComp(new MonthComponent(monthView.getCalComponents().size()+1));
		}
	}
	
	private class WeekdayAddHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			weekView.addComp(new WeekdayComponent(weekView.getCalComponents().size()+1));
		}
	}
	
	private class GenerateButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			generate((int) startDaySpinner.getValue());
		}
	}

}
