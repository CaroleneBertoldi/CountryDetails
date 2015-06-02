package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/index")
public class MyServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, 
	        HttpServletResponse response) 
	        throws ServletException, IOException {
	    
	    PrintWriter out = response.getWriter();
                        
        // buscando os parâmetros no request
        String nome = request.getParameter("nome");
        
        // imprime o nome do contato que foi adicionado
        out.println("<html>");
        out.println("<body>");
        out.println("Contato " + nome +
                " adicionado com sucesso");
        out.println("</body>");
        out.println("</html>");
	}
	
}