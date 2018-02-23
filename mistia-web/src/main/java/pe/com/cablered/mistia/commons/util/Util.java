package pe.com.cablered.mistia.commons.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import pe.com.eb.model.Usuario;

public class Util {
	
	public static String getHost(){
		return "MIPC";
	}
	
	public static Timestamp getSimpleTime(){
		return new Timestamp(Calendar.getInstance().getTime().getTime());
	}

	public static Date getSimpleDate(){
		return Calendar.getInstance().getTime();
	}
	
	
	public static final Usuario getUser(){
		
		return new Usuario("RCHANAME");
	}
}
