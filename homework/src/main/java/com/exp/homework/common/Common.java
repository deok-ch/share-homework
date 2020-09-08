package com.exp.homework.common;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.springframework.web.multipart.MultipartFile;

public class Common {

	public static Long replacePath(String path, String rep) {	//url cuting
		String replaceString = path.replace(rep, "");
		Long returnVal = Long.parseLong(replaceString);
		return returnVal;
	}
	
	public static String fileuploads(MultipartFile files) throws IOException{
		String baseDir = "D:\\summernoteImage";
		File Folder = new File(baseDir);
		
		// 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
		if (!Folder.exists()) {
			try{
			    Folder.mkdir(); //폴더 생성합니다.
		        } 
		        catch(Exception e){
			    e.getStackTrace();
			}        
			
	    }
		String filetype[] = files.getContentType().toString().split("/");
		String format1 = new SimpleDateFormat("YYYYMMDDHHmmss").format(System.currentTimeMillis());
		
		
		String filePath = "/summernoteImage/" + format1.toString()+"."+filetype[1].toString();
		files.transferTo(new File(filePath)); //바로 업로드 하지말것.
		return filePath;
	}
}
