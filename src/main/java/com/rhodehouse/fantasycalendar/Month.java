package com.rhodehouse.fantasycalendar;

import java.util.ArrayList;

import static j2html.TagCreator.*;
import j2html.tags.ContainerTag;

public class Month {
	int numDays;
	int weekLength;
	int startDay;
	String[][] events;
	
	public Month(int numDays, int weekLength, int startDay) {
		this.numDays = numDays;
		this.weekLength = weekLength;
		this.startDay = startDay;
		
		events = new String[(int) Math.ceil((numDays+startDay)/(double) weekLength)][weekLength];
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
					row.add(td(p(Integer.toString((weekLength*i)+j - startDay))));
				}
			}
			rows.add(tr(each(row, r -> r)));
		}
		
		return tbody(each(rows, r -> r));
		
		/*ArrayList<ContainerTag> rows = new ArrayList<ContainerTag>(events.length);
		for (int i=0; i < events.length; i++) {
			ArrayList<ContainerTag> week = new ArrayList<ContainerTag>(weekLength);
			for (int j=0; j < weekLength; j++) {
				week.add(td(p(Integer.toString((i*weekLength)+(j+1))), p(events[i][j] == null ? " " : events[i][j])));
			}
			rows.add(tr(each(week, w -> td(p(w)))));
		}
		ContainerTag t = table(each(rows, r -> r));
		
		return t;
		*/
	}
	
	public ContainerTag toHTML() {
		ContainerTag result = table(generateTableBody());
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
		
		return string;
	}
}
