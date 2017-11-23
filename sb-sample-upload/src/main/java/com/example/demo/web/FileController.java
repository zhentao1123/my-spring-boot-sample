package com.example.demo.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping(value = "/file")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	public static final String IMG_SAVE_PATH = "/upload/save/";
	
	
	
	/**
	 * 单文件上传
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "upload1")
	@ResponseBody
	public String upload1(@RequestParam("file") MultipartFile file) {
		try {
			upload(file, IMG_SAVE_PATH);
			return "upload successful";
		} catch (Exception e) {
			e.printStackTrace();
			return "upload fail";
		}
	}

	/**
	 * 多文件上传
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "upload2")
	@ResponseBody
	public String upload2(HttpServletRequest request) {
		try {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			List<MultipartFile> multFileList = multiRequest.getFiles("file");
			for(MultipartFile file : multFileList) {
				upload(file, IMG_SAVE_PATH);
			}
			return "upload successful";
		} catch (Exception e) {
			e.printStackTrace();
			return "upload fail";
		}
	}
	
	private void upload(MultipartFile file, String imgSavePath) throws Exception{
		if (file.isEmpty()) {
			throw new Exception("file is empty");
		}
		try {
			// 获取文件名
			String fileName = file.getOriginalFilename();
			logger.info("file name：" + fileName);
			// 获取文件的后缀名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			logger.info("file suffix：" + suffixName);
			// 文件上传路径
			String filePath = imgSavePath;
			// 解决中文问题，liunx下中文路径，图片显示问题
			fileName = UUID.randomUUID() + suffixName;
			File dest = new File(filePath + fileName);
			// 检测是否存在目录
			if (!dest.getParentFile().exists()) {
				dest.getParentFile().mkdirs();
			}
			// 文件保存
			file.transferTo(dest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("上传失败", e);
		}
	}
}
