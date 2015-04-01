<html>

<head>

<link rel="stylesheet" href="resources/css/test.css" type="text/css">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#formularioCrear").submit(function(e) {
           
            e.preventDefault();     
            var jsonPeticion = JSON.stringify({
            		"ciudad": $('#ciudad').val(), 
            		"descripcion": $('#descripcion').val() });
            alert("enviando json: " + jsonPeticion);
            $.ajax({
            	headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "POST",
                url: "services/ofertas/crear", 
                data: jsonPeticion,
                contentType: false,
		        processData: false,
                success: function(data)
		           {
		               alert("Oferta creada, respuesta servidor: " + data); 
		           },
		        error: function(jqXHR, textStatus, errorMessage) {
		        	   alert("Error: " + errorMessage);
	            }
            });
        });
		
		
		$("#formularioListar").submit(function(e) {
	           
            e.preventDefault();                
            $.ajax({
            	headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "GET",
                url: "services/busquedas/listas_todas", 
                contentType: false,
		        processData: false,
                success: function(data)
		           {
		               alert("respuesta servidor: " + JSON.stringify(data)); 
		           },
		        error: function(jqXHR, textStatus, errorMessage) {
		        	   alert("Error: " + errorMessage);
	            }
            });
        });
		
		
		$("#formularioBuscar").submit(function(e) {
	           
            e.preventDefault();                
            $.ajax({
            	headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "GET",
                url: "services/busquedas/buscar_por_ciudad/" + $('#ciudadBuscar').val(), 
                contentType: false,
		        processData: false,
                success: function(data)
		           {
		               alert("respuesta servidor: " + JSON.stringify(data)); 
		           },
		        error: function(jqXHR, textStatus, errorMessage) {
		        	   alert("Error: " + errorMessage);
	            }
            });
        });
	});
</script>
</head>

<body>
	<form id="formularioCrear" action="" method="post">
		Ciudad: <input type="text" name="ciudad" id="ciudad" > <br>
		Descripción: <input type="text" name="descripcion" id="descripcion"> <br>
		<input class="styled-button-1" type="submit" name="crear" value="Crear">
	</form>
	 <br> <br>
	<form id="formularioListar" action="" method="post">		
		<input class="styled-button-1" type="submit" name="listar" value="Listar todas">
	</form>
	 <br> <br>
	<form id="formularioBuscar" action="" method="post">		
		Ciudad: <input type="text" name="ciudadBuscar" id="ciudadBuscar" > <br>		
		<input class="styled-button-1" type="submit" name="crear" value="Buscar por ciudad">
	</form>
</body>
</html>
