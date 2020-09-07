package com.exp.homework.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.exp.homework.common.Common;

@Controller
public class FileController {

	@PostMapping("/fileupload")
	public @ResponseBody String fileupload(@RequestParam(name = "file") MultipartFile file) throws IOException{
		String url = Common.fileuploads(file);
		System.out.println(url);
		return url;
	}
}
