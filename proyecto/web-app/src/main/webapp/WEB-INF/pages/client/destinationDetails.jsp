<!DOCTYPE HTML>
<html>

<head>
<title>Ecoturismo</title>

<base href="${pageContext.request.contextPath}/">
<meta content="text/html;charset=utf-8" http-equiv="Content-Type">
<meta name="keywords" content="Template, html, premium, themeforest" />
<meta name="description"
    content="Traveler - Premium template for travel companies">
<meta name="author" content="Tsoy">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- GOOGLE FONTS -->
<link
    href='http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700'
    rel='stylesheet' type='text/css'>
<link
    href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,600'
    rel='stylesheet' type='text/css'>
<!-- /GOOGLE FONTS -->
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/icomoon.css">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet" href="css/mystyles.css">


<script src="js/modernizr.js"></script>
<script src="js/jquery.js"></script>
<script src="js/estructuraCalificacion.js"></script>


<script>  
$(document).ready(function () {  
     var userId = GetParameterValues('id');    
     var currentUrl=   window.location.href ;
     var tipoMo='${usuarioSesion.tipoMoneda}';
        var signoPrecio;
        if(tipoMo=='DOLAR'){
            signoPrecio= 'USD';
        }
        else if (tipoMo=='EURO'){
            signoPrecio='EUR';
        }
        else{
            signoPrecio='COP';
        }

    function GetParameterValues(param) {  
        var url = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');  
        for (var i = 0; i < url.length; i++) {  
            var urlparam = url[i].split('=');  
            if (urlparam[0] == param) {  
                return urlparam[1];  
            }  
        }  
    } 
        

        $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'clientId':  '${usuarioSesion.id}',
                    'tipoMoneda':  '${usuarioSesion.tipoMoneda}'
                },
                datatype:"json",
                type: "GET",
                url: "services/producto/buscar_por_id/" + userId, 
                contentType: false,
                processData: false,
                success: function(data)
                   {


                    var urlIma="";
                    var calificaciones="";
                    //SE TOMA EL JSON DEL PRODUCTO A MOSTRAR   
                    var arr=JSON.parse(JSON.stringify(data));
                    

                    //-------------------
                    //Si el url es cualquier texto, se pone un default
                    //-------------------

                    if( arr.urlImagen.indexOf("http") > -1){
                        urlIma=arr.urlImagen;
                    }
                    else{
                       urlIma="img/Bariloche1.jpg";
                    }

                    var roadMapData = arr.calificaciones;
                    var votado = roadMapData[0].votada;
                    var puntuacionGeneral = roadMapData[0].puntuacion; 
                    var cantidadReviews = roadMapData[0].cantidadVotantes; 
                    var puntuacionUbicacion = roadMapData[1].puntuacion; 
                    var puntuacionAtencion = roadMapData[2].puntuacion; 
                    var puntuacionLimpieza = roadMapData[3].puntuacion; 
                    var puntuacionCuartos = roadMapData[4].puntuacion; 
                    var puntuacionComoidad = roadMapData[5].puntuacion;
                    
                    
                    // Datos del vuelo
                    if (arr.vuelo != 'undefined' && arr.vuelo != null) {
                        
                        var vuelo = arr.vuelo;
                        var aerolinea = vuelo.aerolinea;
                        var numPersonas = vuelo.numPersonas;
                        var precioVuelo = vuelo.precioVuelo;
                        var precioTotal = vuelo.precioTotal;
                        var origen = vuelo.origen;
                        var destino = vuelo.destino;
                        var fechaSalida = vuelo.fechaSalida;
                        var fechaLlegada = vuelo.fechaLlegada;
                        
                        document.getElementById("aerolinea").innerHTML = "<h5> Airline : " + aerolinea + "</h5>";
                        document.getElementById("precioVuelo").innerHTML = "<h5> Fly Price : " + precioVuelo + "</h5>";
                        document.getElementById("origen").innerHTML = "<h5> Source : " + origen + "</h5>";
                        document.getElementById("destino").innerHTML = "<h5> Destination : " + destino + "</h5>";
                        document.getElementById("precioVuelo").innerHTML = "<h5> Fly Price : " + precioVuelo + "</h5>";
                        document.getElementById("fechaSalida").innerHTML = "<h5> Check out : " + fechaSalida + "</h5>";
                        document.getElementById("fechaLlegada").innerHTML = "<h5> Arrival date : " + fechaLlegada + "</h5>";
                        
                      //  document.getElementById("numPersonas").innerHTML =  numPersonas;
                      //  document.getElementById("precioTotal").innerHTML =  precioTotal;
                    }        
                    
                    
                    // Datos del alojamiento
                    if (arr.alojamiento != 'undefined' && arr.alojamiento != null) {
                        var alojamiento = arr.alojamiento;
                        var tipo = alojamiento.tipo;
                        var numMaxPersonas = alojamiento.numMaxPersonas;
                        var numeroNoches = alojamiento.numeroNoches;
                        var precioPorDia = alojamiento.precioPorDia;
                        var precioTotal = alojamiento.precioTotal;
                        var aireAcondicionado = alojamiento.aireAcondicionado;
                        var piscina = alojamiento.piscina;
                        var zonasVerdes = alojamiento.zonasVerdes;
                        var vigilancia = alojamiento.vigilancia;
                        if(tipo!=null){
                            document.getElementById("tipo").innerHTML = "<h5> Lodging type: " + tipo + "</h5>";
                            document.getElementById("numMaxPersonas").innerHTML = "<h5> # Max. Persons : " + numMaxPersonas + "</h5>";
                            document.getElementById("precioPorDia").innerHTML = "<h5> Price for nigth : " + precioPorDia + "</h5>";
                           // document.getElementById("numeroNoches").innerHTML =  numeroNoches;
                           // document.getElementById("precioTotal").innerHTML =  precioTotalAlojamiento;
                        }
                        if(aireAcondicionado!=false)
                            document.getElementById("aireAcondicionado").innerHTML = '<i class="im im-air"></i><span class="booking-item-feature-title"><label  class="control-class">Air conditioning</label></span>';
                        if(piscina!=false)
                            document.getElementById("piscina").innerHTML = '<i class="im im-pool"></i><span class="booking-item-feature-title"><label  class="control-class">Pool</label></span>';
                        if(zonasVerdes!=false)
                            document.getElementById("zonasVerdes").innerHTML = '<i class="fa fa-tree"></i><span class="booking-item-feature-title"><label  class="control-class">Green zone</label></span>';
                        if(vigilancia!=false)
                            document.getElementById("vigilancia").innerHTML = '<i class="im im-business-person"></i><span class="booking-item-feature-title"><label  class="control-class">Security</label></span>';
                        
                        
                    }
                    
                    // Datos del actividades
                    if (arr.actividades != 'undefined' && arr.actividades != null) {
                        
                        var out = "";
                        
                        for(i = 0; i < arr.actividades.length; i++) 
                        {
                            var nombreActividad =  arr.actividades[i].nombreActividad;
                            var descripcion =  arr.actividades[i].descripcion;
                            var fechaActividad = arr.actividades[i].fechaActividad;
                            var costoActividad = arr.actividades[i].costoActividad;
                            var numPersonas = arr.actividades[i].numPersonas;
                            var costoTotal = arr.actividades[i].costoTotal;
                            
                            out+=' <li>'+ 
                                            '<h5 class="booking-item-title">Name Activity: ' + nombreActividad + '</h5>'+
                                            '<h5 class="booking-item-title">Description: ' + descripcion + '</h5>'+
                                            '<h5 class="booking-item-title"> Date:' + fechaActividad + '</h5>'+
                                            '<h5 class="booking-item-title">Cost:' + costoActividad + '</h5>'+
                                            '<h5 class="booking-item-title">Number of persons>' + numPersonas + '</h5>'+
                                            '<h5 class="booking-item-title">Total cost:' + costoTotal + '</h5>'+
                                '</li>'
           
    
                    
                            document.getElementById("ListaActividades").innerHTML = out;
                        }
                    }

                    document.getElementById("nombreProducto").innerHTML = arr.nombre +"<h5>"+arr.lugar+"</h5>";
                    document.getElementById("ciudadProducto").innerHTML = " "+arr.ciudad
                    document.getElementById("precioProducto").innerHTML = "<h2>$ "+arr.precio+signoPrecio+"</h2>"+'<input type="hidden" id="idProductoParaCarrito" name="idProductoParaCarrito" value="'+arr.id+'">';

                    out = "";
                    if(arr.posibleDescuento)
                    {
                        out = '<b style="font-size:20px;">Discounts</b><br>';
                        if(arr.descuentoPse > 0)
                        {
                            out += '<br>Si pagas con PSE: <b style="font-size:20px;">-' + (arr.descuentoPse * 100).toString() + '%</b>';
                        }
                        if(arr.descuentoCash > 0)
                        {
                            out += '<br>Si pagas con cash: <b style="font-size:20px;">-' + (arr.descuentoCash * 100).toString() + '%</b>';
                        }
                        if(arr.descuentoTc > 0)
                        {
                            out += '<br>Si pagas con tarjeta: <b style="font-size:20px;">-' + (arr.descuentoTc * 100).toString() + '%</b>';
                        }
                    }
                    document.getElementById("descuentos").innerHTML = out;
                     
                    document.getElementById("imagenURL").innerHTML = '<img src="'+urlIma+'" alt="Image Alternative text" title="'+arr.lugar+'" />';
                    document.getElementById("estructuraCalificacion").innerHTML = estructuraCalificacion(puntuacionGeneral,cantidadReviews);  
                    document.getElementById("destemail").innerHTML = '<a href="mailto:' + arr.proveedor.email + '?subject=Contactar%20proveedor" target="_top"><i class="fa fa-envelope"></i> Destination E-mail</a>';
                 
                                                               document.getElementById("fbreference").innerHTML = '<div class="fb-share-button" data-href="' + currentUrl + '" data-layout="button_count"></div>'
                                                                              
                    
                    document.getElementById("descriptionPackage").innerHTML =  '<h4 >Description: <small>' + arr.descripcion + '</small></h4>';
                    

                    if(votado==true){
                    document.getElementById("rateProduct").style.visibility = "hidden";
                }
                                     

                },
                error: function(jqXHR, textStatus, errorMessage) {
                 alert("Error: " + errorMessage);
                     }
                });
  });
    </script>
<script type="text/javascript">
    $(document).ready(function() {
        $("#addCart").submit(function(e) {
            e.preventDefault();     
            var jsonPeticion = JSON.stringify({
                    "userName": '${usuarioSesion.usuario}', 
                    "idProducto": $('#idProductoParaCarrito').val()
                     });
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "PUT",
                url: "services/carrito/agregar", 
                data: jsonPeticion,
                contentType: false,
                processData: false,
                success: function(data)
                   {
                   
                    var out='<div class="alert alert-success" role="alert"><strong>Success!</strong> Your product has been added!.</div>'
                       document.getElementById("addCart").innerHTML = out;
                     
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                     alert("Error al agregar al carrito");
                }
            });
        });
});
        </script>

<script>
function calificar() {

    var calificacion = document.getElementsByClassName("selected");
  
     var jsonPeticion = JSON.stringify({
                    "clienteId": '${usuarioSesion.id}', 
                    "servicioId": $('#idProductoParaCarrito').val(),
                    "puntaje": calificacion.length
                     });
     $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "POST",
                url: "services/producto/calificar_producto", 
                data: jsonPeticion,
                contentType: false,
                processData: false,
                success: function(data)
                   {
                   
                    var out='<div class="alert alert-success" role="alert"><strong>Success!</strong> Your product has been rated!.</div>'
                       document.getElementById("rateProduct").innerHTML = out;
                     
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                     var out='<div class="alert alert-error" role="alert"><strong>Error!</strong> Your product has not been rated!.</div>'
                       document.getElementById("rateProduct").innerHTML = out;
                }
            });
    
}
</script>


</head>

<body>

    <!-- FACEBOOK WIDGET -->
    <div id="fb-root"></div>
    <script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=476675579110595";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
    <!-- /FACEBOOK WIDGET -->
    <!-- tiwtter-->
    <script>
window.twttr=(function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],t=window.twttr||{};if(d.getElementById(id))return t;js=d.createElement(s);js.id=id;js.src="https://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);t._e=[];t.ready=function(f){t._e.push(f);};return t;}(document,"script","twitter-wjs"));
</script>
    <!-- /tiwtter-->
    <div class="global-wrap">
        <header id="main-header">
            <div class="header-top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                            <a class="logo" href="index.jsp"> <img src="img/logoSm.png"
                                alt="Image Alternative text" title="Image Title" />
                            </a>
                        </div>
                        <div class="col-md-3 col-md-offset-2">
                            <form class="main-header-search">
                                <div class="form-group form-group-icon-left">
                                    <i class="fa fa-search input-icon"></i> <input type="text"
                                        class="form-control">
                                </div>
                            </form>
                        </div>
                        <div class="col-md-4">
                            <div class="top-user-area clearfix">
                                <ul class="top-user-area-list list list-horizontal list-border">
                                    <li class="top-user-area-avatar"><a
                                        href="pages/client/user_profile.jsp"> <img
                                            class="origin round" src="img/40x40.png"
                                            alt="Image Alternative text" title="AMaze" />Hi,
                                            ${usuarioSesion.nombre}
                                    </a></li>
                                    <li><a href="pages/logout">Sign Out</a></li>

                                    <li class="top-user-area-lang nav-drop"><a href="#"> <img
                                            src="img/flags/32/uk.png" alt="Image Alternative text"
                                            title="Image Title" />ENG<i class="fa fa-angle-down"></i><i
                                            class="fa fa-angle-up"></i>
                                    </a>
                                        <ul class="list nav-drop-menu">
                                            <li><a title="German" href="#"> <img
                                                    src="img/flags/32/de.png" alt="Image Alternative text"
                                                    title="Image Title" /><span class="right">GER</span>
                                            </a></li>
                                        </ul></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="nav">
                    <ul class="slimmenu" id="slimmenu">
                        <li><a href="pages/client/indexUser.jsp">Search</a></li>
                        <li><a href="pages/client/payment.jsp">My shopping cart</a></li>
                    </ul>
                </div>
            </div>
        </header>


        <div class="container">

            <div class="booking-item-details">
                <header class="booking-item-header">
                    <div class="row">
                        <div class="col-md-9">
                            <h2 class="lh1em" id="nombreProducto">Universidad de los
                                Andes (?)</h2>
                            <p class="lh1em text-small">
                                <i class="fa fa-map-marker" id="ciudadProducto"></i>
                            </p>
                            <ul class="list list-inline text-small">
                                <li id="destemail"></li>
                            </ul>
                        </div>
                        <div class="col-md-3">
                            <p class="booking-item-header-price">
                                <small>price from</small> <span class="text-lg"
                                    id="precioProducto">$350</span>/night<br><br>
                                <small id='descuentos'><b>Si pagas con PSE tienes 30% de descuento</b></small>
                            </p>
                        </div>
                    </div>
                </header>
                <div class="row">
                    <div class="col-md-6">
                        <div class="tabbable booking-details-tabbable">
                            <ul class="nav nav-tabs" id="myTab">
                                <li class="active"><a href="#tab-1" data-toggle="tab"><i
                                        class="fa fa-camera"></i>Photos</a></li>
                                <li><a href="#flyinfo" data-toggle="tab"><i
                                        class="fa fa-plane"></i>Fly info</a></li>
                                <li><a href="#actividades" data-toggle="tab"><i
                                        class="im im-fitness"></i>Activities</a></li>
                                <li><a href="#lodging" data-toggle="tab"><i
                                        class="fa fa-home"></i>Lodging Info.</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="tab-1">
                                    <div class="fotorama" data-allowfullscreen="true"
                                        data-nav="thumbs" id="imagenURL">
                                        <img src="img/800x600.png" alt="Image Alternative text"
                                            title="hotel PORTO BAY RIO INTERNACIONAL rooftop pool" /> <img
                                            src="img/800x600.png" alt="Image Alternative text"
                                            title="hotel PORTO BAY SERRA GOLF library" /> <img
                                            src="img/800x600.png" alt="Image Alternative text"
                                            title="The pool" /> <img src="img/800x600.png"
                                            alt="Image Alternative text"
                                            title="hotel PORTO BAY SERRA GOLF suite2" /> <img
                                            src="img/800x600.png" alt="Image Alternative text"
                                            title="hotel PORTO BAY LIBERDADE" />

                                    </div>
                                </div>
                                <div class="tab-pane fade" id="flyinfo">
                                    <div>
                                        <h3>Fly Information</h3>
                                        <div  id="aerolinea"></div>
                                        <div  id="origen"></div>
                                        <div  id="destino"></div>
                                        <div  id="fechaSalida"></div>
                                        <div  id="fechaLlegada"></div>
                                        <div  id="precioVuelo"></div>
  <!--
                                        <div>
                                            <label for="numPersonas" class="control-label"># Person</label>
                                            <input type="text"  class="form-control" id="numPersonas" name="numPersonas">
                                        </div>


                                        <div>
                                            <label for="precioTotal" class="control-label">Total Price</label>
                                            <input type="text"  class="form-control" id="precioTotal" name="precioTotal">
                                        </div>
                                    -->
                                    </div>
                                    
                                </div>
                                <div class="tab-pane fade" id="actividades">
                                    <h3>Activities Information</h3>
                                    <div class="row mt20">
                                        <div class="col-md-8">
                                            <ul id="ListaActividades" class="booking-item-features booking-item-features-expand mb30 clearfix">
                                        </div>

                                        
                                    </div>
                                </div>
                                 <div class="tab-pane fade" id="lodging">
                                    <h3>Lodging Information</h3>
                                    <div class="row mt20">
                                        <div class="col-md-8">
                                            <div>
                                                <div  id="tipo"></div>
                                                <div  id="numMaxPersonas"></div>
                                                <div  id="precioPorDia"></div>
                                                <!--
                                                <div>
                                                    <label for="numeroNoches" class="control-label"># Nigth</label>
                                                    <input type="text"  class="form-control" id="numeroNoches" name="numeroNoches">
                                                </div>
                                                <div>
                                                    <label for="precioTotalAlojamiento" class="control-label">Total Price</label>
                                                    <input type="text"  class="form-control" id="precioTotalAlojamiento" name="precioTotalAlojamiento">
                                                </div>
                                            -->
                                            </div>
                                        </div>

                                        
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="booking-item-meta" id="estructuraCalificacion">
                            <h2 class="lh1em mt40">Exeptional!</h2>
                            <h3>
                                97% <small>of guests recommend</small>
                            </h3>
                            <div class="booking-item-rating">
                                <ul class="icon-list icon-group booking-item-rating-stars">
                                    <li><i class="fa fa-star"></i></li>
                                    <li><i class="fa fa-star"></i></li>
                                    <li><i class="fa fa-star"></i></li>
                                    <li><i class="fa fa-star"></i></li>
                                    <li><i class="fa fa-star"></i></li>
                                </ul>
                                <span class="booking-item-rating-number"><b>4.7</b> of 5
                                    <small class="text-smaller">guest rating</small>
                                <a class="text-default" href="#">based on 1535 reviews</a></span>

                            </div>
                        </div>
                        <h3>Facilities</h3>
                        <div class="row mt20">
                                        <div class="col-md-8">
                                            <ul class="booking-item-features booking-item-features-expand mb30 clearfix">
                                                <li id="piscina"style="float:right"></li>
                                                <li id="zonasVerdes"style="float:right"></li>
                                            
                                                <li id="vigilancia"style="float:right"></li>
                                            
                                                <li id="aireAcondicionado"style="float:right"></li>
                                             </ul>
                                        </div>
                                        
                                    </div>
                        
                                                
                        
                        
                        
                                            
                    
                        <!--RATING-->
                        <div id="rateProduct" class="row">
                            <form id="calificar" action="" method="post">
                                <div class="col-md-8">

                                    <ul
                                        class="list booking-item-raiting-summary-list stats-list-select">
                                        <li onclick="calificar()">
                                            <div class="booking-item-raiting-list-title">Your
                                                rating:</div>
                                            <ul class="icon-group booking-item-rating-stars">
                                                <li><i class="fa fa-star"></i></li>
                                                <li><i class="fa fa-star"></i></li>
                                                <li><i class="fa fa-star"></i></li>
                                                <li><i class="fa fa-star"></i></li>
                                                <li><i class="fa fa-star"></i></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </form>



                        </div>

                        <form id="addCart">
                            <button class="btn btn-primary btn-lg" type="submit">Add
                                to cart</button>
                        </form>
                                                                          <div id="fbreference" > </div>
                                                                                              </div>

                </div>
                <div class="gap"></div>
            </div>
        </div>
        <!--...........................-->
        <!-- FINAL LISTA DE DESTINOS  -->
        <!--...........................-->


        <footer id="main-footer">
            <div class="container">
                <div class="row row-wrap">
                    <div class="col-md-3">
                        <a class="logo" href="index.html"> <img src="img/logoSm.png"
                            alt="Image Alternative text" title="Image Title" />
                        </a>
                        <p class="mb20">Booking, reviews and advices on hotels,
                            resorts, flights, vacation rentals, travel packages, and lots
                            more!</p>
                        <ul class="list list-horizontal list-space">
                            <li><a
                                class="fa fa-facebook box-icon-normal round animate-icon-bottom-to-top"
                                href="#"></a></li>
                            <li><a
                                class="fa fa-twitter box-icon-normal round animate-icon-bottom-to-top"
                                href="#"></a></li>
                            <li><a
                                class="fa fa-google-plus box-icon-normal round animate-icon-bottom-to-top"
                                href="#"></a></li>
                            <li><a
                                class="fa fa-linkedin box-icon-normal round animate-icon-bottom-to-top"
                                href="#"></a></li>
                            <li><a
                                class="fa fa-pinterest box-icon-normal round animate-icon-bottom-to-top"
                                href="#"></a></li>
                        </ul>
                    </div>

                    <div class="col-md-3">
                        <h4>Newsletter</h4>
                        <form>
                            <label>Enter your E-mail Address</label> <input type="text"
                                class="form-control">
                            <p class="mt5">
                                <small>*We Never Send Spam</small>
                            </p>
                            <input type="submit" class="btn btn-primary" value="Subscribe">
                        </form>
                    </div>
                    <div class="col-md-2">
                        <ul class="list list-footer">
                            <li><a href="#">About US</a></li>
                            <li><a href="#">Press Centre</a></li>
                            <li><a href="#">Best Price Guarantee</a></li>
                            <li><a href="#">Travel News</a></li>
                            <li><a href="#">Jobs</a></li>
                            <li><a href="#">Privacy Policy</a></li>
                            <li><a href="#">Terms of Use</a></li>
                            <li><a href="#">Feedback</a></li>
                        </ul>
                    </div>
                    <div class="col-md-4">
                        <h4>Have Questions?</h4>
                        <h4 class="text-color">+1-202-555-0173</h4>
                        <h4>
                            <a href="#" class="text-color">support@traveler.com</a>
                        </h4>
                        <p>24/7 Dedicated Customer Support</p>
                    </div>

                </div>
            </div>
        </footer>

        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/slimmenu.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <script src="js/bootstrap-timepicker.js"></script>
        <script src="js/nicescroll.js"></script>
        <script src="js/dropit.js"></script>
        <script src="js/ionrangeslider.js"></script>
        <script src="js/icheck.js"></script>
        <script src="js/fotorama.js"></script>
        <script
            src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
        <script src="js/typeahead.js"></script>
        <script src="js/card-payment.js"></script>
        <script src="js/magnific.js"></script>
        <script src="js/owl-carousel.js"></script>
        <script src="js/fitvids.js"></script>
        <script src="js/tweet.js"></script>
        <script src="js/countdown.js"></script>
        <script src="js/gridrotator.js"></script>
        <script src="js/custom.js"></script>
    </div>
</body>

</html>


