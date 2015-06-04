package servlets;
 
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
@WebServlet(urlPatterns = {/* "/" ,*/ "/ordenar"})
public class CarregarServlet extends HttpServlet {
  
  @Override
  public void init() throws ServletException {
    // carregar historico e lista de paises
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // carregar parametros
    
    // carregar informacoes do nome
    
    // fazer ordenações do metodo
    
    // colocar parametros na response
    
    // redirecionar para /
    RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
    dispatcher.forward(req, resp);
  }
  
}