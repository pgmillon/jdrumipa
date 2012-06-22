/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ishtanzar.java.drupal;

import java.util.List;

/**
 *
 * @author pgmillon
 */
class DependencyPropertyParser implements IPropertyParser {

  protected ModuleInfoParser parser;

  public DependencyPropertyParser(ModuleInfoParser parser) {
    this.parser = parser;
  }
  
  public void parse(List<String> keys, String value) {
    parser.getDependencies().add(value);
  }
  
}
