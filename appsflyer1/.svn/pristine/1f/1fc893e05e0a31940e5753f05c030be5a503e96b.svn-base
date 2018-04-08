package com.ami.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class UploadUtil {

	/*
	 * 上传图片
	 */
	public static String uploadImage(HttpServletRequest request,File sourceFile,String imageFileName,String imageContentType){
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		/*String userdir = System.getProperty("user.dir").replaceAll("\\\\", "/");
        AppConstant.systemDir = userdir ;
        logger.info("upload path---------------:::"+userdir);
        String imgPath = userdir + "/deploy/system.properties";
        logger.info("upload path---------------:::"+imgPath);*/
		if(sourceFile == null){
			return null;
		}
		Date date = new Date();
		long time = date.getTime();
		String contextPath = "/img/"+time+"_"+imageFileName;
        String imgPath = realPath+contextPath;
        FileInputStream in = null;
		FileOutputStream out = null;
		try {
			in = new FileInputStream(sourceFile);
			out = new FileOutputStream(imgPath);
			byte[] b = new byte[1024];
			int len = 0;
			while((len=in.read(b))!=-1){
				out.write(b, 0, len);
			}
			return contextPath;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
