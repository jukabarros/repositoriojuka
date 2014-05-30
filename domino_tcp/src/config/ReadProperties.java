package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class ReadProperties implements Serializable {
	
	private static final long serialVersionUID = 751059493118259767L;

	public Properties getProp() throws IOException {
        Properties props = new Properties();
        
        FileInputStream file = new FileInputStream(
                "./configGame.properties");
        props.load(file);
        return props;
 
 
    }
 
 }