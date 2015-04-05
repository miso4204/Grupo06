<!DOCTYPE HTML>
<html>

<head>
    <title>Traveler - Index</title>

	<base href="/web-app/" >
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
        var servicio = GetParameterValues('servicio');  
        var primerPrecio = GetParameterValues('precioUno');  
        var segundoPrecio = GetParameterValues('precioDos');  
        var primeraFecha= GetParameterValues('primeraFecha'); 
        var segundaFecha= GetParameterValues('segundaFecha');  

        function GetParameterValues(param) {  
            var url = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');  
            for (var i = 0; i < url.length; i++) {  
                var urlparam = url[i].split('=');  
                if (urlparam[0] == param) {  
                    return urlparam[1];  
                }  
            }  
        } 
        
        if(servicio==2){
         $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "GET",
                url: "services/producto/buscar_por_precio/" + primerPrecio+"/"+segundoPrecio, 
                contentType: false,
                processData: false,
                success: function(data)
                   {
                    var out = "";
                    var urlIma="";
                    var calificaciones="";
                       
                       var arr=JSON.parse(JSON.stringify(data));




                       for(i = 0; i < arr.length; i++) {
        //-------------------
        //Si el url es cualquier texto, se pone un default
        //-------------------

        if( arr[i].urlImagen.indexOf("http") > -1){
            urlIma=arr[i].urlImagen;
        }
        else{
            urlIma="http://viajes.tinglesa.com.uy/imagenes/img_contenido/fotos/b/es/turismo-en-bariloche.jpg";
        }

                var roadMapData = arr[i].calificaciones;
                var puntuacionGeneral = roadMapData[0].puntuacion; 
                var cantidadReviews = roadMapData[0].cantidadVotantes; 
                

    out+=' <li>'+
                            '<a class="booking-item" href="pages/client/destinationDetails.jsp?id='+arr[i].id+'">'+
                                '<div class="row">'+
                                    '<div class="col-md-3">'+
                                        '<div class="booking-item-img-wrap">'+
                                            '<img src="'+urlIma+'" alt="Image Alternative text" title="'+arr[i].lugar+'" />'+
                                            '<div class="booking-item-img-num"><i class="fa fa-picture-o"></i>29</div>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="col-md-6">'+
                                    '<div class="booking-item-rating">'+estructuraCalificacionBasica(puntuacionGeneral)+''+
                                            '<span class="booking-item-rating-number"><b >'+puntuacionGeneral+'</b> of 5</span><small>('+cantidadReviews+' reviews)</small>'+
                                        '</div>'+
                                        '<h5 class="booking-item-title">'+arr[i].nombre+'</h5>'+
                                        '<p class="booking-item-address"><i class="fa fa-map-marker"></i> '+arr[i].ciudad+'</p><small class="booking-item-last-booked">'+arr[i].lugar+'</small>'+
                                    '</div>'+
                                    '<div class="col-md-3"><span class="booking-item-price-from">from</span><span class="booking-item-price">$'+arr[i].precio+'</span><span>/night</span><br><button class="btn btn-primary" id="addCart">Add to Cart</button>'+
                                    '</div>'+
                                '</div>'+
                            '</a>'+
                        '</li>'
       

                
document.getElementById("ListaProductos").innerHTML = out;

         
    }
    document.getElementById("tamResultado").innerHTML = "<h4>"+arr.length + " Results</h4>";

                   },
                error: function(jqXHR, textStatus, errorMessage) {
                       alert("Error: " + errorMessage);
                }
            });
     }else if (servicio==1) {

        
         $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "GET",
                url: "services/producto/buscar_por_precio/" + primerPrecio+"/"+segundoPrecio, 
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


    }
    else if (servicio==3) {
         $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "GET",
                url: "services/producto/buscar_por_fecha/" + primeraFecha+"/"+segundaFecha, 
                contentType: false,
                processData: false,
                success: function(data)
                   {
                    var out = "";
                     //  alert("respuesta servidor: " + JSON.stringify(data)); 
                       var arr=JSON.parse(JSON.stringify(data));
                       for(i = 0; i < arr.length; i++) {
        var urlIma="";
        if( arr[i].urlImagen.indexOf("http") > -1){
            urlIma=arr[i].urlImagen;
        }
        else{
            urlIma="http://viajes.tinglesa.com.uy/imagenes/img_contenido/fotos/b/es/turismo-en-bariloche.jpg";
        }

                var roadMapData = arr[i].calificaciones;
                var puntuacionGeneral = roadMapData[0].puntuacion; 
                var cantidadReviews = roadMapData[0].cantidadVotantes; 
    out+=' <li>'+
                            '<a class="booking-item" href="pages/client/destinationDetails.jsp?id='+arr[i].id+'">'+
                                '<div class="row">'+
                                    '<div class="col-md-3">'+
                                        '<div class="booking-item-img-wrap">'+
                                            '<img src="'+urlIma+'" alt="Image Alternative text" title="'+arr[i].lugar+'" />'+
                                            '<div class="booking-item-img-num"><i class="fa fa-picture-o"></i>29</div>'+
                                        '</div>'+
                                    '</div>'+
                                    '<div class="col-md-6">'+
                                    '<div class="booking-item-rating">'+estructuraCalificacionBasica(puntuacionGeneral)+''+
                                            '<span class="booking-item-rating-number"><b >'+puntuacionGeneral+'</b> of 5</span><small>('+cantidadReviews+' reviews)</small>'+
                                        '</div>'+
                                        '<h5 class="booking-item-title">'+arr[i].nombre+'</h5>'+
                                        '<p class="booking-item-address"><i class="fa fa-map-marker"></i> '+arr[i].ciudad+'</p><small class="booking-item-last-booked">'+arr[i].lugar+'</small>'+
                                    '</div>'+
                                    '<div class="col-md-3"><span class="booking-item-price-from">from</span><span class="booking-item-price">$'+arr[i].precio+'</span><span>/night</span><br><button class="btn btn-primary" id="addCart">Add to Cart</button>'+
                                    '</div>'+
                                '</div>'+
                            '</a>'+
                        '</li>'
       

                
document.getElementById("ListaProductos").innerHTML = out;

         
    }
    document.getElementById("tamResultado").innerHTML = "<h4>"+arr.length + " Results</h4>";
    

                   },
                error: function(jqXHR, textStatus, errorMessage) {
                       alert("Error: " + errorMessage);
                }
            });
    }; 

    });  
</script>

</head>

<body>

    <!-- FACEBOOK WIDGET -->
    <div id="fb-root"></div>
    <script>
    (function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0];
        if (d.getElementById(id)) return;
        js = d.createElement(s);
        js.id = id;
        js.src = "//connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.0";
        fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));
    </script>
    <!-- /FACEBOOK WIDGET -->
    <div class="global-wrap">
        <header id="main-header">
            <div class="header-top">
                <div class="container">
                    <div class="row">
                        <div class="col-md-3">
                            <a class="logo" href="index.html">
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
                                        <a href="user-profile.html">
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
                            <li><a href="hotels.html">Products</a>
                                <ul>
                                    <li><a href="hotel-details.html">Details</a>
                                        <ul>
                                            <li><a href="hotel-details.html">Layout 1</a>
                                            </li>
                                            <li><a href="hotel-details-2.html">Layout 2</a>
                                            </li>
                                            <li><a href="hotel-details-3.html">Layout 3</a>
                                            </li>
                                            <li><a href="hotel-details-4.html">Layout 4</a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </header>


            <div class="container">
                <div class="row"> 

                <div class="col-md-10">
                    <h3 class="booking-title" id="tamResultado">  results <small><a class="popup-text" href="#search-dialog" data-effect="mfp-zoom-out">Change search</a></small></h3>
                    
                        
                    <div class="nav-drop booking-sort">
                        <h5 class="booking-sort-title"><a href="#">Sort: Aviability<i class="fa fa-angle-down"></i><i class="fa fa-angle-up"></i></a></h5>
                        <ul class="nav-drop-menu">
                            <li><a href="#">Price (low to high)</a>
                            </li>
                            <li><a href="#">Price (hight to low)</a>
                            </li>
                            <li><a href="#">Rating</a>
                            </li>
                            <li><a href="#">Distance</a>
                            </li>
                            <li><a href="#">Number of Reviews</a>
                            </li>
                        </ul>
                    </div>
            <!--...........................-->
            <!-- INICIO LISTA DE DESTINOS  -->
            <!--...........................-->
                    <ul class="booking-list" id="ListaProductos">
                        
                        <li>
                            <a class="booking-item" href="#">
                                <div class="row">
                                    <div class="col-md-3">
                                        <div class="booking-item-img-wrap">
                                            <img src="img/800x600.png" alt="Image Alternative text" title="LHOTEL PORTO BAY SAO PAULO suite lhotel living room" />
                                            <div class="booking-item-img-num"><i class="fa fa-picture-o"></i>29</div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="booking-item-rating">
                                            <ul class="icon-group booking-item-rating-stars">
                                                <li><i class="fa fa-star"></i>
                                                </li>
                                                <li><i class="fa fa-star"></i>
                                                </li>
                                                <li><i class="fa fa-star"></i>
                                                </li>
                                                <li><i class="fa fa-star"></i>
                                                </li>
                                                <li><i class="fa fa-star-half-empty"></i>
                                                </li>
                                            </ul><span class="booking-item-rating-number"><b >4.4</b> of 5</span><small>(406 reviews)</small>
                                        </div>
                                        <h5 class="booking-item-title">Bryant Park Hotel</h5>
                                        <p class="booking-item-address"><i class="fa fa-map-marker"></i> New York, NY (Chelsea)</p><small class="booking-item-last-booked">Latest booking: 1 hour ago</small>
                                    </div>
                                    <div class="col-md-3"><span class="booking-item-price-from">from</span><span class="booking-item-price">$207</span><span>/night</span><span class="btn btn-primary">Book Now</span>
                                    </div>
                                </div>
                            </a>
                        </li>
                        
                        
                    
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                        
                    </ul>
                    <div class="row">
                        <div class="col-md-6">
                            <p><small>521 hotels found in New York. &nbsp;&nbsp;Showing 1 – 15</small>
                            </p>
                            <ul class="pagination">
                                <li class="active"><a href="#">1</a>
                                </li>
                                <li><a href="#">2</a>
                                </li>
                                <li><a href="#">3</a>
                                </li>
                                <li><a href="#">4</a>
                                </li>
                                <li><a href="#">5</a>
                                </li>
                                <li><a href="#">6</a>
                                </li>
                                <li><a href="#">7</a>
                                </li>
                                <li class="dots">...</li>
                                <li><a href="#">43</a>
                                </li>
                                <li class="next"><a href="#">Next Page</a>
                                </li>
                            </ul>
                        </div>
                        <div class="col-md-6 text-right">
                            <p>Not what you're looking for? <a class="popup-text" href="#search-dialog" data-effect="mfp-zoom-out">Try your search again</a>
                            </p>
                        </div>
                    </div>
                
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
                                    <img src="img/logoSm.png" alt="Image Alternative text" title="Image Title" /> <h2>ECOTURISMO</h2>
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


