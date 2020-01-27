package test.java.com.commander;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Properties;

import org.junit.Test;

import com.fasterxml.jackson.core.json.WriterBasedJsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class PersistPropertiesTest {

	private static final String TEST_PROPERTIES_ID = "TestAppProperties";

	public static void main(String[] args) {
		
		
	}

	@Test
	public void checkWriteProps() {
		Properties prop = new Properties();
				

		try (OutputStream oStream = new FileOutputStream(TEST_PROPERTIES_ID)) {
			prop.setProperty("h2.usrname", "HDulaney");
			prop.setProperty("h2.pword", "whatever12");
			prop.setProperty("h2.capacity", "34555B");
			prop.setProperty("h2.uri", "c:/Users/Jordon Lightgate/h2/chache");

			prop.store(oStream, "H2 Local Properties List");

			

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		;
		assertTrue(prop.toString().compareTo((checkReadProps(prop).toString())) == 0);
		
		prop.toString();
	}

	public Properties checkReadProps(Properties prop) {

		Properties testProperties = new Properties();

		try (InputStream inStream = new FileInputStream(TEST_PROPERTIES_ID)) {

			testProperties.load(inStream);

		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return testProperties;

	}

}
