/**
 * 
 */
package openoffice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import com.sun.star.io.IOException;

/**
 * @author ang
 *
 */
public class OfficeToHtml {

	/**
	 * @param args
	 * @throws java.io.IOException 
	 */
	public static void main(String[] args) throws java.io.IOException {
		FileInputStream fileInputStream=new FileInputStream("E:\\Dr.com linux-Centos6.2客户端使用说明.doc");
		doc2Html(fileInputStream, new File("E:\\"));

	}
	/**
	 * 转换文件
	 * @param fromFileInputStream: 
	 * @throws java.io.IOException 
	 * */
	public static String doc2Html(InputStream fromFileInputStream, File toFileFolder) throws java.io.IOException{
		String soffice_host = "D:\\Application\\openoffice4";
		String soffice_port = "8100";
		Date date = new Date();
		SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMddHHmmss");
		String timesuffix = sdf.format(date);
		String htmFileName = "htmlfile"+timesuffix+".html";
		String docFileName = "docfile"+timesuffix+".doc";

		File htmlOutputFile = new File(toFileFolder.toString()+File.separatorChar+htmFileName);		
		File docInputFile = new File(toFileFolder.toString()+File.separatorChar+docFileName);
		OutputStream os = new FileOutputStream(docInputFile);
		int bytesRead = 0;
		 byte[] buffer = new byte[1024 * 8];
		while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
		}

		os.close();
		fromFileInputStream.close();
		// convert
		DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
		configuration.setPortNumber(8100);
		configuration.setOfficeHome(soffice_host);
		OfficeManager officeManager=configuration.buildOfficeManager();
		OfficeDocumentConverter converter=new OfficeDocumentConverter(officeManager);
		officeManager.start();
		converter.convert(docInputFile, htmlOutputFile);
		officeManager.stop();
/*		File  htmlOutputFile_rn = new File
		(htmlOutputFile.getAbsolutePath().substring(0,htmlOutputFile.getAbsolutePath().lastIndexOf("."))+".htm");
		htmlOutputFile.renameTo(htmlOutputFile_rn);
		return htmlOutputFile_rn.getName();*/

		//转换完之后删除word文件
		docInputFile.delete();
//		log.debug("删除上传文件："+docInputFile.getName());
		return htmFileName;
	}

}
