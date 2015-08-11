package pojos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.base.Throwables;

public class ItemDeHistorico {

  public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

  private String nome;
  private Date date;

  public ItemDeHistorico(String pais) {
    nome = pais;
    date = new Date();
  }

  public ItemDeHistorico(String pais, String data) {
    this(pais);

    try {
      date = DATE_FORMAT.parse(data);

    } catch (ParseException e) {
      throw Throwables.propagate(e);
    }
  }

  public String getNome() {
    return nome;
  }

  public Date getDate() {
    return date;
  }

  public String getData() {
    return DATE_FORMAT.format(date);
  }

}