package com.rhodehouse.fantasycalendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MonthView extends JPanel {
	private JButton addButton;
	private JPanel monthPanel;
	private JScrollPane monthContainer;
	private GridLayout panelLayout;
	
	ArrayList<MonthComponent> monthComponents = new ArrayList<MonthComponent>();
	
	public MonthView() {
		super(new BorderLayout());
		
		addButton = new JButton("Add");
		addButton.addActionListener(new AddMonthHandler());
		
		panelLayout = new GridLayout(6,1);
		monthPanel = new JPanel(panelLayout);
		
		monthComponents.add(new MonthComponent());
		
		monthPanel.add(monthComponents.get(0));
		monthContainer = new JScrollPane(monthPanel);
		
		monthContainer.setPreferredSize(new Dimension(280,200));
		
		this.setBorder(BorderFactory.createTitledBorder("Months"));
		
		add(monthContainer, BorderLayout.CENTER);
		add(addButton, BorderLayout.SOUTH);
	}
	
	public ArrayList<MonthComponent> getMonthComponents() {
		return monthComponents;
	}
	
	private class AddMonthHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			MonthComponent newComponent = new MonthComponent();			
			
			if (monthComponents.size() >= 6)
				panelLayout.setRows(panelLayout.getRows()+1);
			monthComponents.add(newComponent);
			monthPanel.add(newComponent);
			monthPanel.revalidate();			
		}
	}

}
