package com.ami.mail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ami.common.TimeUtil;



public class ExcelHelper {

	public static InputStream xlsxExcel2007(List<?> xlsx){//HttpServletResponse response){
		if(xlsx == null || xlsx.size()==0){
			return null;
		}
		  // 创建Excel文档
		  XSSFWorkbook hwb = new XSSFWorkbook();
		  //通过反射，获取对象的字段
		  Field[] fields = xlsx.get(0).getClass().getDeclaredFields();
		  String[] names = new String[fields.length];
		  for(int i = 0;i<fields.length;i++){
			  names[i]=fields[i].getName();
		  }
		  // sheet 对应一个工作页
		  XSSFSheet sheet = hwb.createSheet("pldrxkxxmb");
		  XSSFRow firstrow = sheet.createRow(0); // 下标为0的行开始
		  XSSFCell[] firstcell = new XSSFCell[fields.length];
		  for (int j = 0; j < fields.length; j++) {
			  firstcell[j] = firstrow.createCell(j);
			  firstcell[j].setCellValue(new XSSFRichTextString(names[j]));
		  }
		  for (int i = 0; i < xlsx.size(); i++) {
			  // 创建一行
			  XSSFRow row = sheet.createRow(i + 1);
			  // 得到要插入的每一条记录
			  Object obj = xlsx.get(i);
			  Field[] field = obj.getClass().getDeclaredFields();
			  for (int j = 0; j < field.length; j++) {
				  String name = field[j].getName();
				  String type = field[j].getGenericType().toString();
				  field[j].setAccessible(true);
				  String n = name.substring(0, 1).toUpperCase();
				  name = n+name.substring(1, name.length());
				  // 在一行内循环
				  try {
					  Method m;
					  if(type.equals("class java.lang.String")){
//						 m = ReflectionUtils.getDeclaredMethod(obj, "get"+name, null);
						 m = obj.getClass().getDeclaredMethod("get"+name);
						 m.setAccessible(true);
						 String value = (String)m.invoke(obj);
						 XSSFCell fd = row.createCell(j);
						 if(value != null){
							 fd.setCellValue(value);
						 }
					  }
					  if(type.equals("class java.lang.Integer")){
						 m = obj.getClass().getDeclaredMethod("get"+name);
						 m.setAccessible(true);
						 Integer value = (Integer)m.invoke(obj);
						 XSSFCell fd = row.createCell(j);
						 if(value != null){
							 fd.setCellValue(value);
						 }
					  }
					  if (type.equals("class java.lang.Short"))
			          {
			                m = obj.getClass().getDeclaredMethod("get" + name);
			                m.setAccessible(true);
			                Short value = (Short) m.invoke(obj);
			                XSSFCell fd = row.createCell(j);
			                if(value != null){
			                	fd.setCellValue(value);
			                }
			          }
		            if (type.equals("class java.lang.Double"))
		            {
		                m = obj.getClass().getDeclaredMethod("get" + name);
		                m.setAccessible(true);
		                Double value = (Double) m.invoke(obj);
		                XSSFCell fd = row.createCell(j);
		                if(value != null){
		                	fd.setCellValue(value);
		                }
		            }
		            if (type.equals("class java.lang.Boolean"))
		            {
		                m = obj.getClass().getDeclaredMethod("get" + name);
		                m.setAccessible(true);
		                Boolean value = (Boolean) m.invoke(obj);
		                XSSFCell fd = row.createCell(j);
						fd.setCellValue(value);
		            }
		            if (type.equals("class java.util.Date"))
		            {
		                m = obj.getClass().getDeclaredMethod("get" + name);
		                m.setAccessible(true);
		                Date value = (Date) m.invoke(obj);
		                XSSFCell fd = row.createCell(j);
		                if(value != null){
		                	fd.setCellValue(TimeUtil.format(value));
		                }
		            }
				  } catch (Exception e) {
					 e.printStackTrace();
				  }
			  }
		  }
		  // 创建文件输出流，准备输出电子表格
	//      OutputStream out = new FileOutputStream("D:/pldrxkxxmb.xls");
	//      hwb.write(out);
	//      out.close();
		try {
		 /* SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		  String now = format.format(new Date());
		  String exportFileName = xlsx.get(0).getClass().getSimpleName()+".xlsx";//导出文件名
		  // 表示以附件的形式把文件发送到客户端
		  response.setHeader("Content-Disposition", "attachment;filename=" + new String((exportFileName).getBytes(), "ISO-8859-1"));//设定输出文件头
		  response.setContentType("application/vnd.ms-excel;charset=UTF-8");// 定义输出类型 
		  // 通过response的输出流把工作薄的流发送浏览器形成文件
		  OutputStream outStream;
			outStream = response.getOutputStream();
			hwb.write(outStream);
			outStream.flush();
			outStream.close();
			hwb.close();*/
			 ByteArrayOutputStream baos = new ByteArrayOutputStream();  
			 hwb.write(baos);  
             baos.flush();  
             byte[] bt = baos.toByteArray();  
             InputStream is = new ByteArrayInputStream(bt, 0, bt.length);  
             baos.close();  
             return is;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
