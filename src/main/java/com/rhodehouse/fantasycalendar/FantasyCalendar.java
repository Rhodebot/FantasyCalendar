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

import j2html.tags.ContainerTag;

import static j2html.TagCreator.*;

public class FantasyCalendar{

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		JButton genButton = new JButton("Generate");
		genButton.addActionListener(.new GenerateButtonHandler());
		frame.setLayout(new BorderLayout());
		
		frame.add(new JSpinner(), BorderLayout.NORTH);
		frame.add(new MonthView(), BorderLayout.CENTER);
		frame.add(genButton, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);

	}
	
	private static void generate() {
		File f = new File("test.html");
		Month m = new Month (31, 7, 5);
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
	
	private class GenerateButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			generate();
		}
	}

}
