package com.rhodehouse.fantasycalendar;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//there's probably a better way to do this.
		FantasyCalendar fancal = new FantasyCalendar();
		fancal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fancal.setVisible(true);
	}

}
