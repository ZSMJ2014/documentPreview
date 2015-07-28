/**
 * 
 */
package officeToPdf;

import java.io.File;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.OfficeManager;

/**
 * @author ang
 *
 */
public class OfficeToPdf {

	public static OfficeManager officeManager;
	public static OfficeDocumentConverter converter;
	public static void Convert(File inputFile,File outputFile){
		converter.convert(inputFile, outputFile);
	}
}
