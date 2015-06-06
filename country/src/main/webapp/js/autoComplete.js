$(document).ready(function() {
    $(function() {
        $("#country").autocomplete({
            source: function(request, response) {
                $.ajax({
                    url: "/autocomplete/",
                    type: "POST",
                    data: { term: request.term },

                    dataType: "json",

                    success: function(data) {
                        response(data);
                    }
               }); 
            },   
            select: function(event, ui) {
                var pais = $("#country").get(0).value;
                var ordem = $("#ordem").get(0).value;
                var ordena = $("#ordena").get(0).value;

                var url = "/selecionar?pais=" + pais + "&ordem=" + ordem + "&ordena=" + ordena;

                $(location).attr("href", url);
            }
        });
    });
});