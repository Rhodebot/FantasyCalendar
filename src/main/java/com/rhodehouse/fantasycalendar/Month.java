package com.rhodehouse.fantasycalendar;

import java.util.ArrayList;

import javax.swing.text.html.HTML.Attribute;

import static j2html.TagCreator.*;
import j2html.tags.ContainerTag;

public class Month {
	String name;
	int numDays;
	int weekLength;
	int startDay;
	String[][] events;
	
	public Month(String name, int numDays, int weekLength, int startDay) {
		this.name = name;
		this.numDays = numDays;
		this.weekLength = weekLength;
		this.startDay = startDay;
		
		events = new String[(int) Math.ceil((numDays+startDay)/(double) weekLength)][weekLength];
	}
	
	private ContainerTag generateTableHead() {
		ContainerTag firstRow, secondRow;
		
		firstRow = tr(th(name).attr("colspan", weekLength));
		//secondRow = tr()
		
		return thead(firstRow);
	}
	
	private ContainerTag generateTableBody() {
		ArrayList<ContainerTag> rows = new ArrayList<ContainerTag>(events.length);
		int empties = startDay;
		int endDays = 0;
		
		for (int i=0; i < events.length; i++) {
			ArrayList<ContainerTag> row = new ArrayList<ContainerTag>();
			for (int j=1; j <= weekLength; j++) {
				if (empties > 0 ) {
					row.add(td());
					empties--;
				} else if (((weekLength * i) + j) - startDay > numDays) {
					row.add(td());
					endDays++;
				} else {
					row.add(td(p(Integer.toString((weekLength*i)+j - startDay)), p(events[i][j-1] == null ? " " : events[i][j-1])));
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
		String string = new String();
		int empties = startDay;
		int endDays = 0;
		
		for (int i=0; i < events.length; i++) {
			string += "[ ";
			for (int j=1; j <= weekLength; j++)
				if (empties > 0) {
					string += "  ";
					empties--;
				} else if (((weekLength * i)+j)-startDay > numDays) {
					string += "  ";
					endDays++;
				} else {
					string += (((i*weekLength) + j)-startDay)+ " ";
				}
			string += "]\n";
		}
		
		return name+"\n"+string;
	}
}
