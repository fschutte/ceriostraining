package nl.cerios.cinema.service;

import nl.cerios.cinema.domain.FilmAgendaItem;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class FilmAgendaService {

  @Resource(name = "jdbc/cinema-web")
  private DataSource db;

  public List<FilmAgendaItem> getFilmAgendaItems() {

    try (Connection conn = db.getConnection();
         Statement stmt = conn.createStatement()) {

      List<FilmAgendaItem> items = new ArrayList<>();
      try (ResultSet rs = stmt.executeQuery("SELECT id, datum, titel FROM FilmAgenda")) {

        while (rs.next()) {
          long id = rs.getLong("id");
          Date sqlDatum = rs.getDate("datum");
          String titel = rs.getString("titel");
          Date datum = sqlDatum == null ? null : new Date(sqlDatum.getTime());
          items.add(new FilmAgendaItem(id, titel, datum));
        }

        return items;
      }
    } catch (SQLException e) {
      throw new FilmAgendaServiceException("Something went terribly wrong while retrieving the filmagenda.", e);
    }
  }
}
