<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Country Details</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/blog-home.css" rel="stylesheet">
</head>

<body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">Country Details</a>
            </div>
        </div>
    </nav>

    <div class="container">

        <div class="row">

            <div class="col-md-7">

                <h1 class="page-header">
                    Country details
                </h1>
                
                <!-- Search -->
                <div class="well">
                    <h4>Pesquisar país</h4>
                    <div class="input-group">
                        <input type="text" class="form-control">
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button">
                                <span class="glyphicon glyphicon-search"></span>
                        </button>
                        </span>
                    </div>
                </div>

                <h2>
                    <a href="#">Brasil</a>
                </h2>
                
                <hr>
                
                <div class="row">
                	<div class="col-md-8">
	                	<img class="img-responsive" src="http://upload.wikimedia.org/wikipedia/commons/thumb/0/05/Flag_of_Brazil.svg/125px-Flag_of_Brazil.svg.png" alt=""><br>
	                	
		                <p><b>Capital:</b> Brasí­lia</p>
		                <p><b>Língua oficial:</b> Português</p>
		                <p><b>Área:</b> 8 515 767,049</p>
		                <p><b>População:</b> 202 768 562</p>
		                <p><b>PIB:</b> US$ 2,244 trilhões</p>
		                <p><b>IDH:</b> 0,744</p>
		                <p><b>Moeda:</b> Real (BRL)</p>                
		                <p><b>Código ISO:</b> BRA</p>              
                	</div>
                	<div class="col-md-4">
	                	<img class="img-responsive" class="img-polaroid" src="http://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/BRA_orthographic.svg/260px-BRA_orthographic.svg.png" alt="">
                	</div>
                </div>
                
                <hr>

                <ul class="pager">
                    <li class="previous">
                        <a href="#">&larr; Older</a>
                    </li>
                    <li class="next">
                        <a href="#">Newer &rarr;</a>
                    </li>
                </ul>

            </div>

            <div class="col-md-5">
	            <div class="well">
	                <span class="glyphicon glyphicon-wrench"></span> Configurações de ordenação  <br><br>
					<form>
					    <label class="control-label" for="inputTipoOrdenacao">Algorítmo de ordenação:</label>
					    <div class="controls">
							<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1">
							Bubblesort
							<input type="radio" name="optionsRadios" id="optionsRadios2" value="option2" checked>
							Quicksort
					    </div> <br>
					  	<button type="submit" class="btn">Aplicar</button>
					</form>
				</div>
					
                <div class="well">
                    <span class="glyphicon glyphicon-list"></span> Histórico <br><br>
                    <div class="row">
                        <div class="col-lg-6">
                            <ul class="list-unstyled">
                                <li><span class="glyphicon glyphicon-time"></span><a href="#"> hora</a>
                                </li>
                                <li><span class="glyphicon glyphicon-time"></span><a href="#"> hora</a>
                                </li>
                                <li><span class="glyphicon glyphicon-time"></span><a href="#"> hora</a>
                                </li>
                                <li><span class="glyphicon glyphicon-time"></span><a href="#"> hora</a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-lg-6">
                            <ul class="list-unstyled">
                                <li><a href="#">Argentina</a>
                                </li>
                                <li><a href="#">Alemanha</a>
                                </li>
                                <li><a href="#">Canadá</a>
                                </li>
                                <li><a href="#">Estados Unidos</a>
                                </li>
                            </ul>
                        </div>                        
                    </div>
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

    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>