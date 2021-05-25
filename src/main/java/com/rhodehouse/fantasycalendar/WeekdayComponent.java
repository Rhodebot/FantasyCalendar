package com.rhodehouse.fantasycalendar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WeekdayComponent extends JComponent {
	JLabel label;
	JTextField dayName;
	
	public WeekdayComponent(int num) {
		super();
		setLayout(new BorderLayout());
		
		label = new JLabel("Weekday "+num+":");
		dayName = new JTextField();
		
		add(label, BorderLayout.WEST);
		add(dayName, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(200, 25);
	}
	
	public String getDayName() {
		return dayName.getText();
	}
}
