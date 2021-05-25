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
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ComponentPanel extends JPanel {
	private JButton addButton;
	private JButton removeButton;
	private JPanel innerPanel;
	private JPanel buttonPanel;
	private JScrollPane scrollContainer;
	private GridLayout panelLayout;
	
	ArrayList<JComponent> components = new ArrayList<JComponent>();
	
	public ComponentPanel(JComponent firstComponent, ActionListener addHandler) {
		super(new BorderLayout());
		
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		addButton.addActionListener(addHandler);
		removeButton.addActionListener(new RemoveButtonHandler());
		
		panelLayout = new GridLayout(6,1);
		innerPanel = new JPanel(panelLayout);
		buttonPanel = new JPanel(new GridLayout(1,2));
		
		addComp(firstComponent);
		buttonPanel.add(addButton);
		buttonPanel.add(removeButton);
		
		innerPanel.add(components.get(0));
		scrollContainer = new JScrollPane(innerPanel);
		
		scrollContainer.setPreferredSize(new Dimension(280,200));
		
		this.setBorder(BorderFactory.createTitledBorder("Months"));
		
		add(scrollContainer, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void addComp(JComponent newComponent) {
		if (components.size() >= 6)
			panelLayout.setRows(panelLayout.getRows()+1);
		components.add(newComponent);
		innerPanel.add(newComponent);
		innerPanel.revalidate();	
	}
	
	public ArrayList<JComponent> getCalComponents() {
		return components;
	}
	
	private class RemoveButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (components.size() > 1) {
				JComponent component = components.get(components.size()-1);
				components.remove(component);
				innerPanel.remove(component);
				innerPanel.revalidate();
				innerPanel.repaint();
			}
		}
	}
}
