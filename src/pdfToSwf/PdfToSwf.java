/**
 * 
 */
package pdfToSwf;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ang
 *
 */
public class PdfToSwf {

	/**
	 * 将pdf转换成swf格式，转换1-20页
	 * @param pdfFile pdf文件
	 * @param swfFile swf文件
	 * @param swfApiPath swfTools工具地址
	 * @throws Exception
	 */
	public static void convert(File pdfFile,File swfFile,String swfApiPath) throws Exception{
		Runtime runtime = Runtime.getRuntime();
        if (!swfFile.exists()) {
            if (pdfFile.exists()) {
                Process p = runtime.exec(swfApiPath + " " + pdfFile.getPath() + " -o " + swfFile.getPath() + " -T 9 -p 1-20");
                InputStream inputStream = new BufferedInputStream(p.getInputStream());
                int ptr = 0;
                while ((ptr = inputStream.read()) != -1) {

                }
            }else{
                throw new Exception("转换pdf到swf文件时，pdf不存在！");
            }
        }else{
            throw new Exception("swf文件已经存在");
        }
	}
}
