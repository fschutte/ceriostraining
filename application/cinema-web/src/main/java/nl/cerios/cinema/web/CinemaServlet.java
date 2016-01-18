package nl.cerios.cinema.web;

import static java.util.stream.Collectors.joining;

import javax.ejb.EJB;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.cerios.cinema.domain.FilmAgendaItem;
import nl.cerios.cinema.service.FilmAgendaService;

/**
 * Servlet implementation class CinemaServlet
 */
@WebServlet("/CinemaServlet")
public class CinemaServlet extends HttpServlet {
	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

	private static final long serialVersionUID = 1L;

	@EJB
	private FilmAgendaService service;
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<FilmAgendaItem> items = service.getFilmAgendaItems();
		
		String thead = "<thead><th>Filmtitel</th><th>Speelt op:</th></thead>";
		String tbody = items.stream()
			.map(s -> "<tr><td>"+s.getTitel()+"</td><td>"+format(s.getDatum())+"</td></tr>")
			.collect(joining("", "<tbody>", "</tbody>"));

		
		String html = 
				"<!DOCTYPE html><html><head><title>Filmagenda</title></head>"+
				"<body><h1>Filmagenda</h1>"+
				"<table>"+thead+tbody+"</table>"+
				"</body></html>";
		
	    response.setContentType("text/html;charset=UTF-8");
	    response.getWriter().write(html);
	    	
	}

	private String format(LocalDate datum) {
		return Optional.ofNullable(datum)
				.map(d -> d.format(DATE_FORMAT))
				.orElse("onbekend");
		
		// dit is hetzelfde als:
		//  if (datum!=null) return datum.format(...)
		//  else {return "onbekend";}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
