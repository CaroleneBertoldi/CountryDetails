function ordenacao(){     
	 $(function(){
    	var pais = $("#pais").get(0).value;
    	var ordem = $("#tipoOrdenacao").get(0).value;
    	var ordena = $("#ordena").get(0).value;
    	
    	if (pais != "") {
			pais = "pais=" + pais + "&";
		}
    	var url = "/ordenar?" + pais + "ordem=" + ordem + "&ordena=" + ordena;
    	
    	$(location).attr("href", url);
    });
}