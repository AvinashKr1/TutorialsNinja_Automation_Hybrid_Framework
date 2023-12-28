package experiment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Experiments {
	
	private String var;
	protected Properties properties;
	private final String CONFIG_FILE_PATH = "E:\\Eclipse Workspace\\TutorialsNinjaProj\\src\\main\\java\\tnp\\qa\\config\\config.properties";
	
	public Experiments() {
		this.var = "Some value";
		File file = new File(CONFIG_FILE_PATH);
		try {
			FileInputStream fis = new FileInputStream(file);
			properties = new Properties();
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getVar() {
		System.out.println(properties.getProperty("URL"));
		return this.var;
	}
	
	
	public static Experiments getInstanceOf() {
		return new Experiments();
	}
	
	
	
	
	
	
	

	

}
