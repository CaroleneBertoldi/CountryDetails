package pojos;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

public enum NomeDePaisComparator implements Comparator<String> {
  INSTANCE;
  @Override
  public int compare(String o1, String o2) {
    return Collator.getInstance(new Locale("pt", "BR")).compare(o1, o2);
  }
}
