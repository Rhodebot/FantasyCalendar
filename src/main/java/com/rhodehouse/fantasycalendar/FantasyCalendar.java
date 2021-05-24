package com.rhodehouse.fantasycalendar;

import java.awt.GridLayout;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.print.PrintService;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSpinner;

import j2html.tags.ContainerTag;

import static j2html.TagCreator.*;

public class FantasyCalendar{

	public static void main(String[] args) {
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
		
		JFrame frame = new JFrame();
		JButton genButton = new JButton();
		frame.setLayout(new GridLayout(3, 1));
		
		frame.add(new JSpinner());
		frame.add(new MonthView());
		frame.add(genButton);
		
		frame.pack();
		frame.setVisible(true);
		
		
		
		/*
		File f;
		FileWriter writ;
		ArrayList<ContainerTag> a = new ArrayList<ContainerTag>();
		
		a.add(table(tr(td(p("a1")),td(p("a2"))),tr(td(p("a3")),td(p("a4")))));
		a.add(table(tr(td(p("b1")),td(p("b2"))),tr(td(p("b3")),td(p("b4")))));
		
		f = new File("test.html");
		try {
			f.createNewFile();
			writ = new FileWriter(f);
			ContainerTag fileBody = body(
					p("testing..."), p("twasting'''"),
					each(a, tbl -> tbl)
				);
			String fileContents = 
			html(
				head(
					title("Test File")
				), fileBody
			).render();
			writ.write(fileContents);
			writ.close();
		} catch (IOException e) {
			return;
		}
		*/
		
	}

}
