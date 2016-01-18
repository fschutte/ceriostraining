package nl.cerios.cinema.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import nl.cerios.cinema.domain.FilmAgendaItem;

@Stateless
public class FilmAgendaService {
	
	@Resource(name="jdbc/filmagenda")
	private DataSource db;
	
	public List<FilmAgendaItem> getFilmAgendaItems() {
		
		try (Connection conn = db.getConnection();
				Statement stmt = conn.createStatement()) {
			
			List<FilmAgendaItem> items = new ArrayList<FilmAgendaItem>();
			try (ResultSet rs = stmt.executeQuery("SELECT id,titel,datum FROM FILMAGENDA")) {

				while (rs.next()) {
			        long id = rs.getLong("id");
			        String titel = rs.getString("titel");
			        java.sql.Date sqlDatum = rs.getDate("datum");
			        //LocalDate datum = sqlDatum==null ? null : sqlDatum.toLocalDate();
			        // alternatief met Optional
			        LocalDate datum = Optional.ofNullable(sqlDatum).map(java.sql.Date::toLocalDate).orElse(null);
			        
			        items.add(new FilmAgendaItem(id, titel, datum));
				}
				return items;
			}
		} catch (Exception e) {
			throw new FilmAgendaServiceException("Something went terribly wrong while retrieving the filmagenda", e);
		}
	}
	
}
