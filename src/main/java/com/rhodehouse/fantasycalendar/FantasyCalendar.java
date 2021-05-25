package com.rhodehouse.fantasycalendar;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import static j2html.TagCreator.*;

public class FantasyCalendar extends JFrame{
	private JButton genButton;
	private JSpinner startDaySpinner;
	private ComponentPanel monthView, weekView;

	public FantasyCalendar() {
		genButton = new JButton("Generate");
		startDaySpinner = new JSpinner();
		genButton.addActionListener(new GenerateButtonHandler());
		monthView = new ComponentPanel(new MonthComponent(1), new MonthAddHandler());
		weekView = new ComponentPanel(new WeekdayComponent(1), new WeekdayAddHandler());
		
		setLayout(new BorderLayout());
		
		add(startDaySpinner, BorderLayout.NORTH);
		add(weekView, BorderLayout.CENTER);
		add(genButton, BorderLayout.SOUTH);
		
		pack();
	}
	
	private void generate(int startDay) {
		File f = new File("test.html");
		Month m = new Month (((MonthComponent) monthView.getCalComponents().get(0)).getMonthName(),31, 7, startDay);
		System.out.print(m);
		
		try {
			f.createNewFile();
			FileWriter writ = new FileWriter(f);
			String fileContents = html(head(title("Month Testing")), body(m.toHTML())).render();
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
