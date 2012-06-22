/*
 * The MIT License
 *
 * Copyright 2012 pgmillon.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.ishtanzar.java.drupal;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
