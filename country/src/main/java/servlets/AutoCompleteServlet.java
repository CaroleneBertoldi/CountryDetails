package servlets;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import site.Autocomplete;
import site.CacheDeListaDePaises;

import com.google.gson.Gson;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/autocomplete/*" })
public class AutoCompleteServlet extends HttpServlet {
   
	@Override
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
      List<String> paises = CacheDeListaDePaises.INSTANCIA.getPaises();
  
      response.setContentType("application/json");
  
      final String param = request.getParameter("term");
      final List<Autocomplete> result = new ArrayList<Autocomplete>();
      for (final String pais : paises) {
        String normalized = Normalizer.normalize(pais, Normalizer.Form.NFD)
            .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        
        if (normalized.toLowerCase().startsWith(param.toLowerCase())) {
          result.add(new Autocomplete(pais, pais));
        }
      }
  
      response.getWriter().write(new Gson().toJson(result));
    }
	
}