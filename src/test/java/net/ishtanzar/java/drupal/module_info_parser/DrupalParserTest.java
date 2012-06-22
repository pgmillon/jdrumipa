package net.ishtanzar.java.drupal.module_info_parser;

import java.io.File;
import java.net.URL;
import net.ishtanzar.java.drupal.ModuleInfoParser;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class DrupalParserTest {

  /**
   * Rigourous Test :-)
   */
  @Test
  public void iCanReadAnInfoFile() throws Exception {
    ModuleInfoParser parser = new ModuleInfoParser();
    assertNotNull(parser);
    
    URL url = DrupalParserTest.class.getResource("/my_package.info");
    assertNotNull(url);
    
    File file = new File(url.toURI());
    assertNotNull(file);
    
    parser.read(file);
    
    assertEquals(19, parser.getDependencies().size());
  }
}
