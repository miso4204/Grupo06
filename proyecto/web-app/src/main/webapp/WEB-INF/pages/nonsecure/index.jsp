<html>

<head>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		
		$("#formularioListar").submit(function(e) {
	           
            e.preventDefault();                
            $.ajax({
            	headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "GET",
                url: "services/test/fecha_actual", 
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
	
	<form id="formularioListar" action="" method="post">		
		<input type="submit" name="listar" value="Fecha Actual REST">
	</form>
	
</body>

MarketPlace   
<a href="cliente/index">Cliente</a> 
<a href="proveedor/index">Proveedor</a> 
<a href="admin/index">Admin</a> 
</html>
