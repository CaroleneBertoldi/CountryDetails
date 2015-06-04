package servlets;
 
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@SuppressWarnings("serial")
@WebServlet("/selecionar")
public class SelecionarServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // carregar parametros
    
    // carregar informacoes do nome
    
    // salvar no historico
    
    // fazer ordenações do metodo
    
    // colocar parametros na response
    
    // redirecionar para /
    RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
    dispatcher.forward(req, resp);
  }
  
}