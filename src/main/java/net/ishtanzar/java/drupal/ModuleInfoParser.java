/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ishtanzar.java.drupal;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author pgmillon
 */
public class ModuleInfoParser {

  Pattern pattern = Pattern.compile("\\s*"
          + "((?:"
            + "[^=;\\[\\]]|"
            + "\\[[^\\[\\]]*\\]"
          + ")+?)"
          + "\\s*=\\s*"
          + "(?:"
          + "([^\\r\\n]*?)"
          + ")\\s*");
  
  protected List<String> dependencies = new ArrayList<String>();

  public List<String> getDependencies() {
    return dependencies;
  }
  
  public void read(File file) throws FileNotFoundException, IOException {
    String line;
    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
    while((line = reader.readLine()) != null) {
      Matcher matcher = pattern.matcher(line);
      if(matcher.matches()) {
        List<String> keys;
        
        String key = matcher.group(1);
        String value = matcher.group(2);
        
        keys = Arrays.asList(StringUtils.stripStart(key, "]").split("\\]?\\["));
        
        if(!keys.isEmpty()) {
          ModuleProperty moduleProperty = ModuleProperty.parse(keys.get(0));
          if(moduleProperty != null) {
            IPropertyParser propertyParser = moduleProperty.getParser(this);
            propertyParser.parse(keys, value);
          }
        }
      }
    }
  }
  
}
