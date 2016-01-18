package nl.cerios.cinema.domain;

import java.time.LocalDate;
import java.util.Optional;

public class FilmAgendaItem {
	private long id;
	private String titel;
	private LocalDate datum;

	public FilmAgendaItem(long id, String titel, LocalDate datum) {
		this.id=id;
		this.titel=titel;
		this.datum=datum;
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
	public LocalDate getDatum() {
		return datum;
	}
	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}
	
	
}
