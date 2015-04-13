<!DOCTYPE HTML>
<html>

<head>
    <title>Ecoturismo</title>

	<base href="${pageContext.request.contextPath}/" >
    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta name="keywords" content="Template, html, premium, themeforest" />
    <meta name="description" content="Traveler - Premium template for travel companies">
    <meta name="author" content="Tsoy">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- GOOGLE FONTS -->
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400italic,400,300,600' rel='stylesheet' type='text/css'>
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

                    document.getElementById("nombreProducto").innerHTML = arr.nombre +"<h5>"+arr.lugar+"</h5>";
                    document.getElementById("ciudadProducto").innerHTML = " "+arr.ciudad
                    document.getElementById("precioProducto").innerHTML = "<h2>$ "+arr.precio+"</h2>"+'<input type="hidden" id="idProductoParaCarrito" name="idProductoParaCarrito" value="'+arr.id+'">'; 
                    document.getElementById("imagenURL").innerHTML = '<img src="'+urlIma+'" alt="Image Alternative text" title="'+arr.lugar+'" />';
                    document.getElementById("estructuraCalificacion").innerHTML = estructuraCalificacion(puntuacionGeneral,cantidadReviews);  
                     
                 
                   document.getElementById("fbreference").innerHTML = '<div class="fb-share-button" data-href="' + currentUrl + '" data-layout="button_count"></div>'
                    document.getElementById("twreference").innerHTML = '<a class="twitter-share-button" href="' + currentUrl + '">Tweet</a>'
		
                    document.getElementById("descriptionPackage").innerHTML =  '<h4 class="lh1em">Description: ' + arr.descripcion + '</h4>';
                    document.getElementById("activitiesPackage").innerHTML =  '<h4 class="lh1em">Activities: ' + arr.actividades + '</h4>';
                       
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
  js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.3&appId=1405235139756704";
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
                        <a class="logo" href="index.jsp">
                            <img src="img/logoSm.png" alt="Image Alternative text" title="Image Title" />
                        </a>
                    </div>
                    <div class="col-md-3 col-md-offset-2">
                        <form class="main-header-search">
                            <div class="form-group form-group-icon-left">
                                <i class="fa fa-search input-icon"></i>
                                <input type="text" class="form-control">
                            </div>
                        </form>
                    </div>
                    <div class="col-md-4">
                        <div class="top-user-area clearfix">
                            <ul class="top-user-area-list list list-horizontal list-border">
                                <li class="top-user-area-avatar">
                                    <a href="pages/client/user_profile.jsp">
                                        <img class="origin round" src="img/40x40.png" alt="Image Alternative text" title="AMaze" />Hi, ${usuarioSesion.nombre}</a>
                                    </li>
                                    <li><a href="pages/logout">Sign Out</a>
                                    </li>

                                    <li class="top-user-area-lang nav-drop">
                                        <a href="#">
                                            <img src="img/flags/32/uk.png" alt="Image Alternative text" title="Image Title" />ENG<i class="fa fa-angle-down"></i><i class="fa fa-angle-up"></i>
                                        </a>
                                        <ul class="list nav-drop-menu">
                                            <li>
                                                <a title="German" href="#">
                                                    <img src="img/flags/32/de.png" alt="Image Alternative text" title="Image Title" /><span class="right">GER</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="nav">
                    <ul class="slimmenu" id="slimmenu">                       
                        <li>
                                <a href="pages/client/indexUser.jsp">Search</a>
                        </li>
                        <li>
                                <a href="pages/client/payment.jsp">My shopping cart</a>
                            </li>
                    </ul>
                </div>
            </div>
        </header>


        <div class="container">

            <div class="booking-item-details">
                <header class="booking-item-header">
                    <div class="row">
                        <div class="col-md-9">
                            <h2 class="lh1em" id="nombreProducto">Universidad de los Andes (?)</h2>
                            <p class="lh1em text-small" ><i class="fa fa-map-marker"id="ciudadProducto"></i></p>
                            <ul class="list list-inline text-small">
                                <li><a href="mailto:${usuarioSesion.email}?Subject=Hello%20again" target="_top" ><i class="fa fa-envelope"></i> Destination E-mail</a>
                                </li>
                                <li><a href="${usuarioSesion.website}"><i class="fa fa-home"></i> Destionation Website</a>
                                </li>
                                <li><i class="fa fa-phone"></i> ${usuarioSesion.telefono}</li>
                            </ul>
                        </div>
                        <div class="col-md-3">
                            <p class="booking-item-header-price"><small>price from</small>  <span class="text-lg" id="precioProducto">$350</span>/night</p>
                        </div>
                    </div>
                </header>
                <div class="row"> 
                    <div class="col-md-6">
                        <div class="tabbable booking-details-tabbable">
                            <ul class="nav nav-tabs" id="myTab">
                                <li class="active"><a href="#tab-1" data-toggle="tab"><i class="fa fa-camera"></i>Photos</a>
                                </li>
                                <li><a href="#google-map-tab" data-toggle="tab"><i class="fa fa-map-marker"></i>On the Map</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane fade in active" id="tab-1">
                                    <div class="fotorama" data-allowfullscreen="true" data-nav="thumbs" id ="imagenURL">
                                        <img src="img/800x600.png" alt="Image Alternative text" title="hotel PORTO BAY RIO INTERNACIONAL rooftop pool" />
                                        <img src="img/800x600.png" alt="Image Alternative text" title="hotel PORTO BAY SERRA GOLF library" />
                                        <img src="img/800x600.png" alt="Image Alternative text" title="The pool" />
                                        <img src="img/800x600.png" alt="Image Alternative text" title="hotel PORTO BAY SERRA GOLF suite2" />
                                        <img src="img/800x600.png" alt="Image Alternative text" title="hotel PORTO BAY LIBERDADE" />
                                        
                                    </div>
                                </div>
                                <div class="tab-pane fade" id="google-map-tab">
                                    <div id="map-canvas" style="width:100%; height:500px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="booking-item-meta" id="estructuraCalificacion">
                            <h2 class="lh1em mt40">Exeptional!</h2>
                            <h3>97% <small >of guests recommend</small></h3>
                            <div class="booking-item-rating">
                                <ul class="icon-list icon-group booking-item-rating-stars">
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                    <li><i class="fa fa-star"></i>
                                    </li>
                                </ul><span class="booking-item-rating-number"><b >4.7</b> of 5 <small class="text-smaller">guest rating</small></span>
                                <p><a class="text-default" href="#">based on 1535 reviews</a>
                                </p>
                            </div>
                        </div>
                        <!--RATING-->
                        <div id ="rateProduct" class="row">
                            <form id="calificar" action="" method="post" >
                            <div class="col-md-8">
                                <div id="descriptionPackage"  class="lh1em">Rate</div>
                                <div id="activitiesPackage"  class="lh1em">Rate</div>
                                <ul   class="list booking-item-raiting-summary-list stats-list-select">
                                            <li onclick="calificar()">
                                                <div class="booking-item-raiting-list-title">Your rating:</div>
                                                <ul class="icon-group booking-item-rating-stars">
                                                    <li><i  class="fa fa-star"></i>
                                                    </li>
                                                    <li><i  class="fa fa-star"></i>
                                                    </li>
                                                    <li><i  class="fa fa-star"></i>
                                                    </li>
                                                    <li><i  class="fa fa-star"></i>
                                                    </li>
                                                    <li><i  class="fa fa-star"></i>
                                                    </li>
                                                </ul>
                                            </li>
                                        </ul>
                            </div>
                        </form>
                            
                            
                            
                        </div>

                        <!--
                        <div class="row">
                            <div class="col-md-8">
                                <h4 class="lh1em">Traveler raiting</h4>
                                <ul class="list booking-item-raiting-list">
                                    <li>
                                        <div class="booking-item-raiting-list-title">Exellent</div>
                                        <div class="booking-item-raiting-list-bar">
                                            <div style="width:91%;"></div>
                                        </div>
                                        <div class="booking-item-raiting-list-number">1223</div>
                                    </li>
                                    <li>
                                        <div class="booking-item-raiting-list-title">Very Good</div>
                                        <div class="booking-item-raiting-list-bar">
                                            <div style="width:6%;"></div>
                                        </div>
                                        <div class="booking-item-raiting-list-number">61</div>
                                    </li>
                                    <li>
                                        <div class="booking-item-raiting-list-title">Average</div>
                                        <div class="booking-item-raiting-list-bar">
                                            <div style="width:5%;"></div>
                                        </div>
                                        <div class="booking-item-raiting-list-number">40</div>
                                    </li>
                                    <li>
                                        <div class="booking-item-raiting-list-title">Poor</div>
                                        <div class="booking-item-raiting-list-bar">
                                            <div style="width:3%;"></div>
                                        </div>
                                        <div class="booking-item-raiting-list-number">15</div>
                                    </li>
                                    <li>
                                        <div class="booking-item-raiting-list-title">Terrible</div>
                                        <div class="booking-item-raiting-list-bar">
                                            <div style="width:1%;"></div>
                                        </div>
                                        <div class="booking-item-raiting-list-number">9</div>
                                    </li>
                                </ul>
                            </div>
                            
                            
                        </div>-->
                        <form id="addCart">
                         <button class="btn btn-primary btn-lg" type="submit"  >Add to cart</button>
                         </form>
                         <div id="fbreference" > </div>
                         <div id="twreference" > </div>
                         
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
                        <a class="logo" href="index.html">
                            <img src="img/logoSm.png" alt="Image Alternative text" title="Image Title" /> 
                        </a>
                        <p class="mb20">Booking, reviews and advices on hotels, resorts, flights, vacation rentals, travel packages, and lots more!</p>
                        <ul class="list list-horizontal list-space">
                            <li>
                                <a class="fa fa-facebook box-icon-normal round animate-icon-bottom-to-top" href="#"></a>
                            </li>
                            <li>
                                <a class="fa fa-twitter box-icon-normal round animate-icon-bottom-to-top" href="#"></a>
                            </li>
                            <li>
                                <a class="fa fa-google-plus box-icon-normal round animate-icon-bottom-to-top" href="#"></a>
                            </li>
                            <li>
                                <a class="fa fa-linkedin box-icon-normal round animate-icon-bottom-to-top" href="#"></a>
                            </li>
                            <li>
                                <a class="fa fa-pinterest box-icon-normal round animate-icon-bottom-to-top" href="#"></a>
                            </li>
                        </ul>
                    </div>

                    <div class="col-md-3">
                        <h4>Newsletter</h4>
                        <form>
                            <label>Enter your E-mail Address</label>
                            <input type="text" class="form-control">
                            <p class="mt5"><small>*We Never Send Spam</small>
                            </p>
                            <input type="submit" class="btn btn-primary" value="Subscribe">
                        </form>
                    </div>
                    <div class="col-md-2">
                        <ul class="list list-footer">
                            <li><a href="#">About US</a>
                            </li>
                            <li><a href="#">Press Centre</a>
                            </li>
                            <li><a href="#">Best Price Guarantee</a>
                            </li>
                            <li><a href="#">Travel News</a>
                            </li>
                            <li><a href="#">Jobs</a>
                            </li>
                            <li><a href="#">Privacy Policy</a>
                            </li>
                            <li><a href="#">Terms of Use</a>
                            </li>
                            <li><a href="#">Feedback</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col-md-4">
                        <h4>Have Questions?</h4>
                        <h4 class="text-color">+1-202-555-0173</h4>
                        <h4><a href="#" class="text-color">support@traveler.com</a></h4>
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
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
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


