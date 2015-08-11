package servlets;

import historico.Historico;
import historico.TipoDeOrdenacao;
import informacoes.ObterInformacoesDeUmPais;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import pojos.NomeDePaisComparator;
import site.CacheDeListaDePaises;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;

enum ServletHelper {

  INSTANCIA;

  public static void carregar(ServletRequest request, ServletResponse response) throws ServletException, IOException {
    ServletHelper helper = INSTANCIA;

    helper.carregaPais(request);
    helper.carregaHistorico(request);

    helper.dispatch(request, response, "WEB-INF/jsp/index.jsp");
  }

  public static void ordenar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletHelper helper = INSTANCIA;

    helper.setOrdenar(request);

    helper.redirect(request, response, "/");
  }

  public static void selecionar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletHelper helper = INSTANCIA;

    helper.salvaHistorico(request);

    helper.redirect(request, response, "/");
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

  private void carregaHistorico(ServletRequest request) {
    Historico historico = Historico.INSTANCIA;

    String ordem = Integer.toString(historico.getTipoDeOrdenacao().ordinal());
    boolean ordena = historico.isOrdena();
    List<ItemDeHistorico> itensDeHistorico = historico.getListaDePaises();

    request.setAttribute("opcoes", TipoDeOrdenacao.values());
    request.setAttribute("ordem", ordem);
    request.setAttribute("ordena", Boolean.toString(ordena));
    request.setAttribute("itensDeHistorico", itensDeHistorico);
  }

  private void setOrdenar(ServletRequest request) {
    String ordem = request.getParameter("ordem");
    boolean ordena = Boolean.parseBoolean(request.getParameter("ordena"));

    Historico historico = Historico.INSTANCIA;

    try {
      TipoDeOrdenacao tipoDeOrdenacao = TipoDeOrdenacao.values()[Integer.parseInt(ordem)];
      historico.setTipoDeOrdenacao(tipoDeOrdenacao);
    } catch (Exception e) {
      ordem = Integer.toString(historico.getTipoDeOrdenacao().ordinal());
    }

    historico.setOrdena(ordena);
  }

  private void salvaHistorico(ServletRequest request) {
    String pais = request.getParameter("pais");

    if (Collections.binarySearch(CacheDeListaDePaises.INSTANCIA.getPaises(), pais, NomeDePaisComparator.INSTANCE) >= 0) {
      Historico.INSTANCIA.addPais(pais);
    }
  }

  private void dispatch(ServletRequest request, ServletResponse response, String path) throws ServletException, IOException {
    RequestDispatcher dispatcher = request.getRequestDispatcher(path);
    dispatcher.forward(request, response);
  }

  private void redirect(ServletRequest request, HttpServletResponse response, String path) throws IOException {
    Map<String, String[]> parameterMap = request.getParameterMap();
    
    Map<String, List<String>> map;
    map = Maps.transformValues(parameterMap, ArrayToList.INSTANCE);
    
    ListMultimap<String, String> listMultimap = ArrayListMultimap.create();
    for (Entry<String, List<String>> entry : map.entrySet()) {
      listMultimap.putAll(entry.getKey(), entry.getValue());
    }
    
    ListMultimap<String, String> encodedListMultimap;
    encodedListMultimap = Multimaps.transformValues(listMultimap, StringToEncoded.INSTANCE);
    
    Collection<Entry<String, String>> entries = encodedListMultimap.entries();
    String url = path + "?" + Joiner.on("&").join(entries);

    response.sendRedirect(url);
  }

  private enum ArrayToList implements Function<String[], List<String>> {
    INSTANCE;
    @Override
    public List<String> apply(String[] input) {
      return Lists.newArrayList(input);
    }
  }

  private enum StringToEncoded implements Function<String, String> {
    INSTANCE;
    @Override
    public String apply(String input) {
      String res = "";
      try {
        res = URLEncoder.encode(input, "UTF-8");
      } catch (UnsupportedEncodingException e) {

      }

      return res;
    }
  }

}