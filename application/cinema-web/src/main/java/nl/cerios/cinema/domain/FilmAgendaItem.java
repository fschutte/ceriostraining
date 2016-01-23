package nl.cerios.cinema.domain;

import java.util.Date;

public class FilmAgendaItem {

  private long id;
  private String titel;
  private Date datum;

  public FilmAgendaItem(long id, String titel, Date datum) {
    this.id = id;
    this.titel = titel;
    this.datum = datum;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitel() {
    return titel;
  }

  public void setTitel(String titel) {
    this.titel = titel;
  }

  public Date getDatum() {
    return datum;
  }

  public void setDatum(Date datum) {
    this.datum = datum;
  }
}
