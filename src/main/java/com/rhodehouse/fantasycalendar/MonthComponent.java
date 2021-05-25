package com.rhodehouse.fantasycalendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class MonthComponent extends JComponent {
	JLabel monthLabel;
	JTextField monthName;
	JSpinner numDays;
	
	public MonthComponent(int number) {
		super();
		setLayout(new BorderLayout());
		monthLabel = new JLabel("Month "+number+":");
		monthName = new JTextField(20);
		numDays = new JSpinner(new SpinnerNumberModel(0, 0, 30, 1));
		
		add(monthLabel, BorderLayout.WEST);
		add(monthName, BorderLayout.CENTER);
		add(numDays, BorderLayout.EAST);
		
		setVisible(true);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(200, 25);
	}
	
	public String getMonthName() {
		return monthName.getText();
	}
	
	public int getNumDays() {
		return (int) numDays.getValue();
	}
}
