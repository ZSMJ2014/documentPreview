/**
 * 
 */
package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * @author ang
 *
 */
public class GetPdf extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String fileName=request.getParameter("fileName");
		response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
        File outputFile=new File(fileName);
        sendFile(outputFile, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
		doPost(request, response);
	}
	private void sendFile(File file, HttpServletResponse response) throws IOException {
		response.setContentLength((int) file.length());
        InputStream inputStream = null;
        try {
        	inputStream = new FileInputStream(file);
            IOUtils.copy(inputStream, response.getOutputStream());
        } finally {
        	IOUtils.closeQuietly(inputStream);
        }
	}
}
