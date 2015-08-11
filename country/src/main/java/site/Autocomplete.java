package site;

public class Autocomplete {

  private final String label;
  private final String value;

  public Autocomplete(String label, String value) {
    this.label = label;
    this.value = value;
  }

  public final String getLabel() {
    return this.label;
  }

  public final String getValue() {
    return this.value;
  }

}