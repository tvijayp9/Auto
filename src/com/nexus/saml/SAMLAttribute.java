/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nexus.saml;

import java.util.List;

/**
 *
 * @author vasanth
 */
public class SAMLAttribute {
    private final String name;
  private final List<String> values;

  public SAMLAttribute(String name, List<String> values) {
    this.name = name;
    this.values = values;
  }

  public String getName() {
    return name;
  }

  public List<String> getValues() {
    return values;
  }

  public String getValue() {
    return join(", ", values);
  }
    private static String join(String separator, List<String> input) {

        if (input == null || input.size() <= 0) return "";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.size(); i++) {

            sb.append(input.get(i));

            // if not the last item
            if (i != input.size() - 1) {
                sb.append(separator);
            }

        }

        return sb.toString();

    }
  @Override
  public String toString() {
    return "SAMLAttribute{" +
      "name='" + name + '\'' +
      ", values=" + values +
      '}';
  }
}
