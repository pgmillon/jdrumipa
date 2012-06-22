/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.ishtanzar.java.drupal;

/**
 *
 * @author pgmillon
 */
 public enum ModuleProperty {
  DEPENDENCIES("dependencies") {
    @Override
    public IPropertyParser getParser(ModuleInfoParser parser) {
      return new DependencyPropertyParser(parser);
    }
  };
  
  protected String name;

  private ModuleProperty(String name) {
    this.name = name;
  }

  public static ModuleProperty parse(String property) {
    for (ModuleProperty enumProp : ModuleProperty.values()) {
      if (enumProp.name.equals(property)) {
        return enumProp;
      }
    }
    return null;
  }
  
  public abstract IPropertyParser getParser(ModuleInfoParser parser);
  
}
