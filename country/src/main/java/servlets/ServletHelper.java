package servlets;

import historico.Historico;
import historico.TipoDeOrdenacao;
import informacoes.ObterInformacoesDeUmPais;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import pojos.InformacoesDeUmPais;
import pojos.ItemDeHistorico;
import site.CacheDeListaDePaises;

import com.google.common.base.Throwables;

public class ServletHelper {
  
  private static ServletHelper instancia;
  
  public static void carregar(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    ServletHelper helper = instancia();
    
    helper.carregaPais(request);
    helper.carregaHistorico(request);
  }
  
  public static void ordenar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletHelper helper = instancia();
    
    carregar(request, response);
    
    helper.dispatch(request, response, "WEB-INF/jsp/index.jsp");
  }
  
  public static void selecionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletHelper helper = instancia();
    
    helper.salvaHistorico(request);
    
    carregar(request, response);
    
    helper.dispatch(request, response, "WEB-INF/jsp/index.jsp");
  }
  
  private static synchronized ServletHelper instancia() {
    return instancia != null ? instancia : new ServletHelper();
  }
  
  private void salvaHistorico(HttpServletRequest request) {
    String pais = request.getParameter("pais");
    
    Historico.INSTANCIA.addPais(pais);
  }
  
  private void carregaPais(ServletRequest request) {
    String pais = request.getParameter("pais");
    
    InformacoesDeUmPais informacoes = null;
    if (pais != null) {
      try {
        Document doc = Jsoup.parse(new URL(CacheDeListaDePaises.INSTANCIA.getUrl(pais)), 0);
        informacoes = new ObterInformacoesDeUmPais(doc).carregarInformacoes(pais);
        
      } catch (IOException e) {
        throw Throwables.propagate(e);
      }
    }
    
    request.setAttribute("pais", pais);
    request.setAttribute("informacoes", informacoes);
  }
  
  private void carregaHistorico(ServletRequest request) {
    String ordem = request.getParameter("ordem");
    boolean ordena = Boolean.parseBoolean(request.getParameter("ordena"));
    
    
    Historico historico = Historico.INSTANCIA;
    ordem = ordem != null ? ordem : historico.getTipoDeOrdenacao().name();
    
    historico.setOrdena(ordena);
    historico.setTipoDeOrdenacao(TipoDeOrdenacao.valueOf(ordem));
    List<ItemDeHistorico> itensDeHistorico = historico.getListaDePaises();
    
    request.setAttribute("opcoes", TipoDeOrdenacao.values());
    request.setAttribute("ordem", ordem);
    request.setAttribute("ordena", Boolean.toString(ordena));
    request.setAttribute("itensDeHistorico", itensDeHistorico);
  }
  
  private void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher(path);
    dispatcher.forward(request, response); 
  }

}