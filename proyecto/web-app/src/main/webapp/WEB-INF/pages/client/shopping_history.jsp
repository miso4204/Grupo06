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

<script type="text/javascript">

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
		
    $(document).ready(function() {
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "GET",
                url: "services/carrito/historial/" + '${usuarioSesion.usuario}', 
                contentType: false,
                processData: false,
                success: function(data)
                   {
                     var arregloFacturas = JSON.parse(JSON.stringify(data));
                     var out = "";
					 for(i = 0; i < arregloFacturas.length; i++) {
					 /*
                                 <div class="booking-item-payment">
                                    <header class="clearfix"></header>
                                    
                                    <p id="facturasId"/>
                                    </p>
                                </div>
								*/
						if(i == 0){
						out += '<h4>Shopping History:</h4>';
						}
						
						out += '<div class="booking-item-payment">';
						out += '<header class="clearfix"></header>';
						out += '<p> Fecha de Pago: ' + arregloFacturas[i].fechaPago + '</p>';
						out += '<p> Total Pagado: ' + arregloFacturas[i].totalPagado + ' ' + signoPrecio + '</p>';
						out += '<p> Tipo Pago: </p>';
						if( arregloFacturas[i].tipoPago == 'PSE'){
							out += '<img class="pp-img" src="http://portalacademico.ugca.edu.co/ugc/images/PSE.png" alt="Image Alternative text" title="Image Title" />';
						}else if( arregloFacturas[i].tipoPago == 'CREDIT_CARD'){
							out += '<img class="pp-img" src="http://cdn.iphonehacks.com/wp-content/uploads/2014/09/Credit-Card-Visa.jpg" alt="Image Alternative text" title="Image Title" />';
						}else if( arregloFacturas[i].tipoPago == 'CASH_ON_DELIVERY'){
							out += '<img class="pp-img" src="http://enchantess.com/wp-content/uploads/2013/09/cod.png" alt="Image Alternative text" title="Image Title" />';
						}
						out += '</div>';
						
					 }
					 
					 document.getElementById("facturasId").innerHTML = out;
                     
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                     alert("Error al cargar facturas");
                }
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
                            <li>
                                <a href="pages/client/indexUser.jsp">Search</a>
                            </li>
                            <li>
                                <a href="pages/client/payment.jsp">My shopping cart</a>
                            </li>
                            <li>
                                <a href="pages/client/shopping_history.jsp">Shopping History</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </header>

                                 
								 

						<div id="facturasId" class="container">
						<h4>Shopping History:</h4>
                                
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


