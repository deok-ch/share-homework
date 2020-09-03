package com.exp.homework.common;

public class Common {

	public static Long replacePath(String path, String rep) {	//url cuting
		String replaceString = path.replace(rep, "");
		Long returnVal = Long.parseLong(replaceString);
		return returnVal;
	}
}
