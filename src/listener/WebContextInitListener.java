/**
 * 
 */
package listener;

import java.io.File;
import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;

import officeToHtml.OfficeToHtml;
import officeToPdf.OfficeToPdf;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;

/**
 * @author ang
 *
 */
public class WebContextInitListener implements ServletContextListener {
	public static final String PARAMETER_OFFICE_PORT = "office.port";
	public static final String PARAMETER_OFFICE_HOME = "office.home";
	public static final String PARAMETER_OFFICE_PROFILE = "office.profile";
	

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		OfficeToPdf.officeManager.stop();
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext=event.getServletContext();
		DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
		String officePortParam = servletContext.getInitParameter(PARAMETER_OFFICE_PORT);
		if (officePortParam != null) {
		    configuration.setPortNumber(Integer.parseInt(officePortParam));
		}
		String officeHomeParam = servletContext.getInitParameter(PARAMETER_OFFICE_HOME);
		if (officeHomeParam != null) {
		    configuration.setOfficeHome(new File(officeHomeParam));
		}

		OfficeToPdf.officeManager = configuration.buildOfficeManager();
		OfficeToPdf.converter = new OfficeDocumentConverter(OfficeToPdf.officeManager);
		OfficeToPdf.officeManager.start();
	}


}
