<!DOCTYPE HTML>
<html>

<head>
    <title>Traveler - Index</title>

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
<script>
var xmlhttp = new XMLHttpRequest();
var url = "services/producto/listar";

xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        var myArr = JSON.parse(xmlhttp.responseText);
        myFunction(myArr);
    }
}
xmlhttp.open("GET", url, true);
xmlhttp.send();

function myFunction(arr) {
    var out = "";
    var i;
    for(i = 0; i < arr.length; i++) {
   
       

                    out+='<div class="col-md-4">'+
                                    '<div class="thumb">'+
                                        '<a class="hover-img" href="destinationDetails.jsp">'+
                                            '<img src="http://www.pnncocuy.com/images/picgallery/park_del_cocuy_l.jpg" alt="Image Alternative text" title="Viva Las Vegas" />'+
                                            '<div class="hover-inner hover-inner-block hover-inner-bottom hover-inner-bg-black hover-hold">'+
                                                '<div class="text-small">'+
                                                    <!--....................-->
                                                    <!-- ESTRELLAS  -->
                                                    <!--....................-->
                                                    '<ul class="icon-group text-tiny text-color">'+
                                                        '<li><i class="fa fa-star"></i>'+
                                                        '</li>'+
                                                        '<li><i class="fa fa-star"></i>'+
                                                        '</li>'+
                                                        '<li><i class="fa fa-star"></i>'+
                                                        '</li>'+
                                                        '<li><i class="fa fa-star"></i>'+
                                                        '</li>'+
                                                        '<li><i class="fa fa-star-half-empty"></i>'+
                                                        '</li>'+
                                                    '</ul>'+
                                                    '<h5>'+arr[i].nombre+'</h5>'+
                                                    '<p>'+arr[i].lugar+'-'+arr[i].ciudad+'</p>'+
                                                    '<p class="mb0"> offers from $'+arr[i].precio+'</p>'+
                                                '</div>'+
                                            '</div>'+
                                        '</a>'+
                                    '</div>'+
                                '</div>'

document.getElementById("ListaProductos").innerHTML = out;
// alert("Producto creado, respuesta servidor: " +  arr[i].nombre); 

         
    }


     
}
</script>

<script type="text/javascript">
    $(document).ready(function() {
    	
    	
   $("#buscarPorCiudad").submit(function(e) {
            
            e.preventDefault();                
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "GET",
                url: "services/producto/buscar_por_lugar/" + $('#ciudad').val(), 
                contentType: false,
                processData: false,
                success: function(data)
                   {
                	if (data.codigoRespuesta == 'OK') {
                       var respuesta_servicio = JSON.stringify(data.respuesta)
                       var form = $('<form action="pages/client/searchResult.jsp?servicio=1&lugar='+encodeURIComponent($("#ciudad").val()) +'" method="post">' +
						  '<input type="hidden" name="respuestajson" value=' +respuesta_servicio+' />' +
						  '</form>');
						$('body').append(form);
						form.submit();
                	}
                	else {
                		alert("Error: " + data.mensaje);
                	}
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                       alert("Error: " + errorMessage);
                }
            });
            

        });  	
    	
    	
$("#buscarPorPrecio").submit(function(e) {
               
            e.preventDefault();                
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "GET",
                url: "services/producto/buscar_por_precio/" + $('#priceOne').val()+"/"+$('#priceTwo').val(), 
                contentType: false,
                processData: false,
                success: function(data)
                   {
                     
                       var respuesta_servicio = JSON.stringify(data)
                       var form = $('<form action="pages/client/searchResult.jsp?servicio=2&precioUno='+encodeURIComponent($("#priceOne").val()) +'&precioDos='+encodeURIComponent($("#priceTwo").val())+'" method="post">' +
  '<input type="hidden" name="respuestajson" value=' +respuesta_servicio+' />' +
  '</form>');
$('body').append(form);
form.submit();
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                       alert("Error: " + errorMessage);
                }
            });
            

        });

$("#buscarPorFecha").submit(function(e) {
               
            e.preventDefault();                
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "GET",
                url: "services/producto/buscar_por_fecha/" + $('#dateOne').val()+"/"+$('#dateTwo').val(), 
                contentType: false,
                processData: false,
                success: function(data)
                   {
                       
                       var respuesta_servicio = JSON.stringify(data)
                       var form = $('<form action="pages/client/searchResult.jsp?servicio=3&primeraFecha='+encodeURIComponent($("#dateOne").val()) +'&segundaFecha='+encodeURIComponent($("#dateTwo").val())+'" method="post">' +
  '<input type="hidden" name="respuestajson" value=' +respuesta_servicio+' />' +
  '</form>');
$('body').append(form);
form.submit();
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
<!--                             <li><a href="hotels.html">Products</a> -->
<!--                                 <ul> -->
<!--                                     <li><a href="hotel-details.html">Details</a> -->
<!--                                         <ul> -->
<!--                                             <li><a href="hotel-details.html">Layout 1</a> -->
<!--                                             </li> -->
<!--                                             <li><a href="hotel-details-2.html">Layout 2</a> -->
<!--                                             </li> -->
<!--                                             <li><a href="hotel-details-3.html">Layout 3</a> -->
<!--                                             </li> -->
<!--                                             <li><a href="hotel-details-4.html">Layout 4</a> -->
<!--                                             </li> -->
<!--                                         </ul> -->
<!--                                     </li> -->
                                    
<!--                                 </ul> -->
<!--                             </li> -->
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

            <!-- TOP AREA -->
            <div class="top-area show-onload">
                <div class="bg-holder full">
                    <div class="bg-mask"></div>
                    <div class="bg-parallax" style="background-image:url(img/409520.jpg);"></div>
                    <div class="bg-content">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-8">
                                    <div class="search-tabs search-tabs-bg mt50">
                                        <h1>Find Your Perfect Trip</h1>
                                        <div class="tabbable">
                                            <ul class="nav nav-tabs" id="myTab">
                                                <li class="active"><a href="#tab-1" data-toggle="tab"><i class="fa fa-map-marker"></i> <span >Location</span></a>
                                                </li>
                                                <li><a href="#tab-2" data-toggle="tab"><i class="fa fa-money"></i> <span >Price</span></a>
                                                </li>
                                                <li><a href="#tab-3" data-toggle="tab"><i class="fa fa-calendar"></i> <span >Date</span></a>                                            </ul>
                                                    <div class="tab-content">
                                                        <div class="tab-pane fade in active" id="tab-1">
                                                            <h2>Search and Save by location</h2>
                                                            <form id="buscarPorCiudad" action="" method="post" >
                                                                <div class="form-group form-group-lg form-group-icon-left"><i class="fa fa-map-marker input-icon"></i>
                                                                    <label>Where are you going?</label>
                                                                    <input class="typeahead form-control" placeholder="City, Airport or Point of Interest" type="text" id="ciudad" required/>
                                                                </div>
                                                                
                                                                <button class="btn btn-primary btn-lg" type="submit">Search for location</button>
                                                            </form>
                                                        </div>
                                                        <div class="tab-pane fade " id="tab-2">
                                                            <h2>Search and Save by price</h2>
                                                            <form id="buscarPorPrecio" action="" method="post">
                                                                <div class="form-group form-group-lg form-group-icon-left"><i class="fa fa-money input-icon"></i>
                                                                    <label>Where are you going?</label>
                                                                    <input class="typeahead form-control" placeholder="100$" type="number" id="priceOne" min="0" required />
                                                                </div>
                                                                <div class="form-group form-group-lg form-group-icon-left"><i class="fa fa-money input-icon"></i>
                                                                   <label>to..</label>
                                                                    <input class="typeahead form-control" placeholder="300$" type="number" id="priceTwo" min="0" required/>
                                                                </div>
                                                                
                                                                <button class="btn btn-primary btn-lg" type="submit" id="mandarPrecios">Search for location</button>
                                                            </form>
                                                        </div>
                                                        <div class="tab-pane fade" id="tab-3">
                                                            <h2>Search and Save by Date</h2>
                                                            <form id="buscarPorFecha" action=""  method="post">
                                                                <div class="form-group form-group-lg form-group-icon-left">
                                                                    <label>When do you want to travel?</label>
                                                                </div>
                                                                <div class="input-daterange" data-date-format="yyyy-m-d">
                                                                    <div class="row">
                                                                        <div class="col-md-3">
                                                                            <div class="form-group form-group-lg form-group-icon-left"><i class="fa fa-calendar input-icon input-icon-highlight"></i>
                                                                                <label>Check-in</label>
                                                                                <input class="form-control" name="start" type="text" id="dateOne"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-3">
                                                                            <div class="form-group form-group-lg form-group-icon-left"><i class="fa fa-calendar input-icon input-icon-highlight"></i>
                                                                                <label>Check-out</label>
                                                                                <input class="form-control" name="end" type="text" id="dateTwo"/>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-3">

                                                                        </div>

                                                                    </div>
                                                                </div>
                                                                <button class="btn btn-primary btn-lg" type="submit">Search for location</button>
                                                            </form>
                                                        </div>


                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- END TOP AREA  -->


                    <div class="gap"></div>
                    <!--...........................-->
                    <!-- INICIO LISTA DE DESTINOS  -->
                    <!--...........................-->
                    <div class="container">
                        <div class="col-md-12">
                            <h3 class="mb20">Popular Destinations</h3>
                            <div class="row row-wrap" id="ListaProductos">

                                
                                
                            </div>
                            
                            <div class="gap gap-small"></div>
                        </div>
                        <div class="gap gap-small"></div>
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


