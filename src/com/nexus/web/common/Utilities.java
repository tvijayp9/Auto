package com.nexus.web.common;

import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;

public class Utilities {
	
	
	
	public static List getYNCombo() {
		List list = new ArrayList();
		//Yes
		DataBean y = new DataBean("Y","Yes");
		list.add(y);
		//NO
		DataBean n = new DataBean("N","No");
		list.add(n);
		return list;
	}
	
	}
