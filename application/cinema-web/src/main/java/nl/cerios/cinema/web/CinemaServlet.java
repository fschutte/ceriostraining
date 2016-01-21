package nl.cerios.cinema.web;

import nl.cerios.cinema.domain.FilmAgendaItem;
import nl.cerios.cinema.service.FilmAgendaService;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class CinemaServlet
 */
@WebServlet("/CinemaServlet")
public class CinemaServlet extends HttpServlet {

  private static final String DATE_FORMAT = "dd-MM-yyyy";
  private static final long serialVersionUID = 1L;

  @EJB
  private FilmAgendaService service;

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    List<FilmAgendaItem> items = service.getFilmAgendaItems();

    StringBuilder html = new StringBuilder("<!DOCTYPE html>")
        .append("<html>")
        .append("<head><title>Filmagenda</title></head>")
        .append("<body><h1>Filmagenda</h1>")
        .append("<table>")
        .append("<thead><th>Filmtitel</th><th>speelt op:</th></thead>")
        .append("<tbody>");
    for (FilmAgendaItem item : items) {
      html.append("<tr><td>")
          .append(item.getTitel())
          .append("</td><td>")
          .append(format(item.getDatum()))
          .append("</td></tr>");
    }
    html.append("</tbody>")
        .append("</table>")
        .append("</body>")
        .append("</html>");

    response.setContentType("text/html;charset=UTF-8");
    response.getWriter().write(html.toString());
  }

  private String format(Date datum) {
    return datum != null ? new SimpleDateFormat(DATE_FORMAT).format(datum) : "onbekend";
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
  }
}
