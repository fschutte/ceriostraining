package nl.cerios.cinema.service;

public class FilmAgendaServiceException extends RuntimeException {

  public FilmAgendaServiceException(String message) {
    super(message);
  }

  public FilmAgendaServiceException(String message, Throwable cause) {
    super(message, cause);
  }
}
