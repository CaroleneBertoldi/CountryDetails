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

enum ServletHelper {
  
  INSTANCIA;
  
  public static void carregar(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    ServletHelper helper = INSTANCIA;
    
    helper.carregaPais(request);
    helper.carregaHistorico(request);
  }
  
  public static void ordenar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletHelper helper = INSTANCIA;
    
    carregar(request, response);
    
    helper.dispatch(request, response, "WEB-INF/jsp/index.jsp");
  }
  
  public static void selecionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletHelper helper = INSTANCIA;
    
    helper.carregaPais(request);
    helper.salvaHistorico(request);
    helper.carregaHistorico(request);
    
    helper.dispatch(request, response, "WEB-INF/jsp/index.jsp");
  }
  
  private void carregaPais(ServletRequest request) {
    String pais = request.getParameter("pais");
    
    InformacoesDeUmPais informacoes = null;
    if (pais != null) {
      try {
        Document doc = Jsoup.parse(new URL(CacheDeListaDePaises.INSTANCIA.getUrl(pais)), 0);
        informacoes = new ObterInformacoesDeUmPais(doc).carregarInformacoes(pais);
        
      } catch (IOException e) {
        pais = null;
      }
    }
    
    request.setAttribute("pais", pais);
    request.setAttribute("informacoes", informacoes);
  }
  
  private void salvaHistorico(HttpServletRequest request) {
    String pais = (String) request.getAttribute("pais");
    
    if (pais != null) {
      Historico.INSTANCIA.addPais(pais);
    }
  }
  
  private void carregaHistorico(ServletRequest request) {
    String ordem = request.getParameter("ordem");
    boolean ordena = Boolean.parseBoolean(request.getParameter("ordena"));
    
    Historico historico = Historico.INSTANCIA;

    try {
      TipoDeOrdenacao tipoDeOrdenacao = TipoDeOrdenacao.values()[Integer.parseInt(ordem)];
      historico.setTipoDeOrdenacao(tipoDeOrdenacao);
    } catch(Exception e) {
      ordem = Integer.toString(historico.getTipoDeOrdenacao().ordinal());
    }
    
    historico.setOrdena(ordena);
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