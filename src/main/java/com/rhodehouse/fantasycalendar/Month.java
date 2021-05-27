package com.rhodehouse.fantasycalendar;

import java.util.ArrayList;

import javax.swing.text.html.HTML.Attribute;

import static j2html.TagCreator.*;
import j2html.tags.ContainerTag;

public class Month {
	String name;
	int numDays;
	ArrayList<String> weekDays;
	int startDay;
	String[][] events;
	
	public Month(String name, int numDays, ArrayList<String> weekDays, int startDay) {
		this.name = name;
		this.numDays = numDays;
		this.weekDays = weekDays;
		this.startDay = startDay;
		
		events = new String[(int) Math.ceil((numDays+startDay)/(double) weekDays.size())][weekDays.size()];
	}
	
	private ContainerTag generateTableHead() {
		ContainerTag firstRow, secondRow;
		
		firstRow = tr(th(name).attr("colspan", weekDays.size()));
		secondRow = tr(each(weekDays, day -> td(day)));
		
		return thead(firstRow, secondRow);
	}
	
	private ContainerTag generateTableBody() {
		ArrayList<ContainerTag> rows = new ArrayList<ContainerTag>(events.length);
		int empties = startDay;
		int endDays = 0;
		
		for (int i=0; i < events.length; i++) {
			ArrayList<ContainerTag> row = new ArrayList<ContainerTag>();
			for (int j=1; j <= weekDays.size(); j++) {
				if (empties > 0 ) {
					row.add(td());
					empties--;
				} else if (((weekDays.size() * i) + j) - startDay > numDays) {
					row.add(td());
					endDays++;
				} else {
					row.add(td(p(Integer.toString((weekDays.size()*i)+j - startDay)), p(events[i][j-1] == null ? " " : events[i][j-1])));
				}
			}
			rows.add(tr(each(row, r -> r)));
		}
		return tbody(each(rows, r -> r));
	}
	
	public ContainerTag toHTML() {
		ContainerTag result = table(generateTableHead(), generateTableBody());
		return result;
	}
	
	public String toString() {
		String string = name+"\n";
		int empties = startDay;
		int endDays = 0;
		
		for (int i=0; i < weekDays.size(); i++)
			string += weekDays.get(i)+" ";
		
		string += "\n";
		
		for (int i=0; i < events.length; i++) {
			string += "[ ";
			for (int j=1; j <= weekDays.size(); j++)
				if (empties > 0) {
					string += "  ";
					empties--;
				} else if (((weekDays.size() * i)+j)-startDay > numDays) {
					string += "  ";
					endDays++;
				} else {
					string += (((i*weekDays.size()) + j)-startDay)+ " ";
				}
			string += "]\n";
		}
		
		return string;
	}
}
