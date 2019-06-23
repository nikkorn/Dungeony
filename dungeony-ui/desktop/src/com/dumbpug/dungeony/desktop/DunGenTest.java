package com.dumbpug.dungeony.desktop;


import com.dumbpug.dungeony.dungen.DunGen;
import com.dumbpug.dungeony.dungen.DunGenConfiguration;
import com.dumbpug.dungeony.dungen.DunGenPrinter;
import com.dumbpug.dungeony.dungen.Dungeon;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Test for DunGen.
 */
public class DunGenTest {

	/**
	 * Program Entry.
	 * @param args
	 */
	public static void main(String[] args) {
		DunGenConfiguration config = new DunGenConfiguration();
		config.seed                = 12345;
		
		Dungeon dungeon = DunGen.generate("../core/assets/rooms", config);
		
		String dateTime = new SimpleDateFormat("yyyy_MM_dd__HH_mm_ss_SSS").format(new Date());
		
		DunGenPrinter.print(dateTime, "", dungeon);
	}
}
