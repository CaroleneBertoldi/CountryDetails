<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>Country Details</title>

    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/blog-home.css" rel="stylesheet">
    <link href="../css/jquery-ui.css" rel="stylesheet">
    
    <script src="../js/jquery.js"></script>
    <script src="../js/jquery-ui.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/autoComplete.js"> </script>
    <script src="../js/combobox.js"> </script>
</head>

<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand">Country Details</a>
            </div>
        </div>
    </nav>

    <div class="container">

        <div class="row">

            <div class="col-md-7">
                
                <div class="well">
               		<form>
               			<input type="hidden" id="ordem" value="${ordem}">
               			<input type="hidden" id="ordena" value="${ordena}">
               			
	                   	<label for="country"> Pesquisar país: </label>
	                    <div class="input-group">
		                    <input type="text" id="country" placeholder="Pesquisar país sem acentos" class="form-control">
	                        <span class="input-group-btn">
	                            <a class="btn btn-default" role="button">
	                                <span class="glyphicon glyphicon-search"></span>
	                        	</a>
	                        </span>
	                    </div>
                	</form>
                </div>

				<c:if test="${empty pais}">
					<div class="panel panel-info">
					  <div class="panel-body">
					    Nenhum país selecionado.
					  </div>
					</div>				
				</c:if>
				
				<c:if test="${not empty pais}">
	                <h2>${informacoes.nome}</h2>
	                <hr>
	                <div class="row">
	                	<div class="col-md-8">
		                	<img class="img-responsive" src="${informacoes.bandeira}" alt=""><br>
		                	
			                <p><b>Capital:</b> ${informacoes.capital}</p>
			                <p><b>Língua oficial:</b> ${informacoes.linguaOficial}</p>
			                <p><b>Área:</b> ${informacoes.area}</p>
			                <p><b>População:</b> ${informacoes.populacao}</p>
			                <p><b>PIB:</b> ${informacoes.PIB}</p>
			                <p><b>IDH:</b> ${informacoes.IDH}</p>
			                <p><b>Moeda:</b> ${informacoes.moeda}</p>                
			                <p><b>Código ISO:</b> ${informacoes.codigoISO}</p>              
	                	</div>
	                	<div class="col-md-4">
		                	<img class="img-responsive" class="img-polaroid" src="${informacoes.localizacaoNoMundo}" alt="">
	                	</div>
	                </div>
                </c:if>
                
            </div>

            <div class="col-md-5">
	            <div class="well">
	                <span class="glyphicon glyphicon-wrench"></span> <b>Configurações de ordenação:</b> <br><br>
					<form>
               			<input type="hidden" id="pais" value="${pais}">
               			<input type="hidden" id="ordena" value="${ordena}">
               			
					    <label for="inputTipoOrdenacao"><b>Algorítmo de ordenação:</b></label>
						<select id="tipoOrdenacao" onchange="ordenacao();">
						    <c:forEach var="opcao" items="${opcoes}">
						        <option value="${opcao}" ${opcao == ordem ? 'selected="selected"' : ''}>${opcao}</option>
						    </c:forEach>
						</select>
					</form>
				</div>
					
                <div class="well">
                    <span class="glyphicon glyphicon-list"> </span> <b>Histórico:</b>      
                    
                    <br><br>
                    
                    <c:if test="${empty itensDeHistorico}">
						<div class="panel panel-info">
						  <div class="panel-body">
						    Não existem países pesquisados anteriormente.
						  </div>
						</div>
                    </c:if>
                    <c:if test="${not empty itensDeHistorico}">
	                    <div class="row">
	                        <div class="col-lg-12">
	                            <table class="table">
	                            	<thead>
	                            		<tr>
	                            			<th>Data / hora</th>
	                            			<th> País 
	                            				<a href="/ordenar?${not empty pais ? 'pais='.concat(pais).concat('&') : ''}ordem=${ordem}&ordena=${not ordena}" title="${ordena ? 'Retirar ordenação' : 'Ordenar'}">
                            						<span class="glyphicon ${ordena ? 'glyphicon-sort-by-order' : 'glyphicon-sort-by-alphabet'}"></span>
	                            				</a>
	                            			</th>
	                            		</tr>
	                            	</thead>
	                            	<tbody>
										<c:forEach var="item" items="${itensDeHistorico}">
			                                <tr>
			                                	<td><span class="glyphicon glyphicon-time"></span> ${item.data}</td>
			                                	<td><a href="/selecionar?pais=${item.nome}&ordem=${ordem}&ordena=${ordena}"> ${item.nome}</a></td>
			                                </tr>
										</c:forEach>
	                            	</tbody>
								</table>
	                        </div>                       
	                    </div>
                   </c:if> 
                    
                </div>

            </div>

        </div>

        <hr>

        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Country Details 2015</p>
                </div>
            </div>
        </footer>

    </div>

</body>

</html>