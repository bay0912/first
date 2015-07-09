package com.bxj.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	
	static String getProperty(String key){
		Properties props = new Properties();
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config/tank.properties"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return props.getProperty(key);
		
	}

}
