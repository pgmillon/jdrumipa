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
public interface IPropertyParser {
  
  public void parse(List<String> keys, String value);
  
}
