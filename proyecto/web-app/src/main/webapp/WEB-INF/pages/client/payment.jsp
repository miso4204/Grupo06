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

    $(document).ready(function() {
        $(document).on("submit", "#removeProduct", function(e) {
            e.preventDefault();     
            var jsonPeticion = JSON.stringify({
                    "userName": '${usuarioSesion.usuario}', 
                    "idProducto": $( this ).find('input[id="idProductoParaCarrito"]').val()
                     });
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "PUT",
                url: "services/carrito/remover", 
                data: jsonPeticion,
                contentType: false,
                processData: false,
                success: function(data)
                   {
                      location.reload();
                
                     
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                     alert("Error al agregar al carrito");
                }
            });
        });

});

        </script>

<script type="text/javascript">
    $(document).ready(function() {
        $("#payPSE").submit(function(e) {
        
            e.preventDefault();     
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "POST",
                url: "services/payment/pay_pse/"+'${usuarioSesion.usuario}', 
                data: false,
                contentType: false,
                processData: false,
                success: function(data)
                   {
                  
                   var form = $('<form action="pages/client/successPayment.jsp" method="post">' +
                    '<input type="hidden" name="respuestajson" value="" />' +
                    '</form>');
                    $('body').append(form);
                    form.submit(); /*var o href="pages/client/indexUser.jsp"*/
                     
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                     alert("Error al agregar al carrito");
                }
            });
        });
        
$("#cash").submit(function(e) {
            
            e.preventDefault();     
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "POST",
                url: "services/payment/pay_cash/"+'${usuarioSesion.usuario}', 
                data: false,
                contentType: false,
                processData: false,
                success: function(data)
                   {
                  
                   var form = $('<form action="pages/client/successPayment.jsp" method="post">' +
                    '<input type="hidden" name="respuestajson" value="" />' +
                    '</form>');
                    $('body').append(form);
                    form.submit(); /*var o href="pages/client/indexUser.jsp"*/
                     
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                     alert("Error al agregar al carrito");
                }
            });
        });
$("#payCredit").submit(function(e) {
        
            e.preventDefault();     
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "POST",
                url: "services/payment/pay_credit/"+'${usuarioSesion.usuario}', 
                data: false,
                contentType: false,
                processData: false,
                success: function(data)
                   {
                  
                   var form = $('<form action="pages/client/successPayment.jsp" method="post">' +
                    '<input type="hidden" name="respuestajson" value="" />' +
                    '</form>');
                    $('body').append(form);
                    form.submit(); /*var o href="pages/client/indexUser.jsp"*/
                     
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                     alert("Error al agregar al carrito");
                }
            });
        });
});
        </script>

       

<script type="text/javascript">
    $(document).ready(function() {
       
              
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'clienteId':  '${usuarioSesion.id}' 
                },
                datatype:"json",
                type: "GET",
                url: "services/carrito/listar/" + '${usuarioSesion.usuario}', 
                contentType: false,
                processData: false,
                success: function(data)
                   {


                    var out="";
                    var urlIma="";
                
                    
                    //SE TOMA EL JSON DE LOS PRODUCTOS DEL CARRITO A MOSTRAR   
                   var arr=JSON.parse(JSON.stringify(data));

                       for(i = 0; i < arr.length; i++) {
                            //-------------------
                            //Si el url es cualquier texto, se pone un default
                            //-------------------

                            if( arr[i].urlImagen.indexOf("http") > -1){
                                urlIma=arr[i].urlImagen;
                            }
                            else{
                                urlIma="img/Bariloche1.jpg";
                            }
                           out+='<a class="booking-item-payment-img" href="#">'+
                                        '<img src="'+urlIma+'" alt="Image Alternative text" title="hotel 1" />'+
                                    '</a>'+
                                    '<h5 class="booking-item-payment-title" id="nombrePro"><a href="#">'+arr[i].nombre+'</a></h5>'+
                                   '<form id="removeProduct">'+
                                   '<input type="hidden" id="idProductoParaCarrito" name="idProductoParaCarrito" value="'+arr[i].id+'">'+ 
                                        '<p class="booking-item-address" id="lugarPro"><i class="fa fa-map-marker"></i> Lugat</p>'+
                                        '<small class="booking-item-last-booked" id="precioPro">Price:'+arr[i].precio+' </small><br>'+
                                        '<small class="booking-item-last-booked" id="ciudadPro">'+arr[i].ciudad+'</small>'+
                                     '<div><button class="btn btn-primary btn-small" type="submit onclick="remover()">Remove</button></div>'+
                                    '</form>'
                        
                       }
                    document.getElementById("productosCarritos").innerHTML =out;
                    
                                     

                },
                error: function(jqXHR, textStatus, errorMessage) {
                 alert("Error: " + errorMessage);
                     }
                });  
        });

            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'clienteId':  '${usuarioSesion.id}' 
                },
                datatype:"json",
                type: "GET",
                url: "services/carrito/total/" + '${usuarioSesion.usuario}', 
                contentType: false,
                processData: false,
                success: function(data)
                   {


                    
                    var totalCarrito=JSON.stringify(data);
                    var out="Total: " +'<span>$ '+totalCarrito+'</span>' ;
                    
                   document.getElementById("totalCarrito").innerHTML =out;
                                     

                },
                error: function(jqXHR, textStatus, errorMessage) {
                 alert("Error: " + errorMessage);
                     }
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
                        </ul>
                    </div>
                </div>
            </header>


            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <img class="pp-img" src="http://portalacademico.ugca.edu.co/ugc/images/PSE.png" alt="Image Alternative text" title="Image Title" />
                        <p>Important: You will be redirected to PSE's website to securely complete your payment.</p>
                        <form id="payPSE">
                           <button class="btn btn-primary" type="submit">Checkout via PSE</a>   

                           </form>
                       </div>
                       

                       <div class="col-md-4">
                        <h4>Pay via Credit/Debit Card</h4>
                        <ul class="card-select">
                            <li>
                                <img class="card-select-img" src="img/payment/visa-curved-64px.png" alt="Image Alternative text" title="Image Title" />
                                <div class="card-select-data">
                                    <p class="card-select-number">xxxx xxxx xxxx 1456</p>
                                    <input class="form-control card-select-cvc" type="text" placeholder="CVC" />
                                </div>
                            </li>
                            <li>
                                <img class="card-select-img" src="img/payment/maestro-curved-64px.png" alt="Image Alternative text" title="Image Title" />
                                <div class="card-select-data">
                                    <p class="card-select-number">xxxx xxxx xxxx 6698</p>
                                    <input class="form-control card-select-cvc" type="text" placeholder="CVC" />
                                </div>
                            </li>
                        </ul>
                        <div class="gap gap-small"></div>
                        <h4>Pay with new Card</h4>
                        <form class="cc-form">
                            <div class="clearfix">
                                <div class="form-group form-group-cc-number">
                                    <label>Card Number</label>
                                    <input class="form-control" placeholder="xxxx xxxx xxxx xxxx" type="text" /><span class="cc-card-icon"></span>
                                </div>
                                <div class="form-group form-group-cc-cvc">
                                    <label>CVC</label>
                                    <input class="form-control" placeholder="xxxx" type="text" />
                                </div>
                            </div>
                            <div class="clearfix">
                                <div class="form-group form-group-cc-name">
                                    <label>Cardholder Name</label>
                                    <input class="form-control" type="text" />
                                </div>
                                <div class="form-group form-group-cc-date">
                                    <label>Valid Thru</label>
                                    <input class="form-control" placeholder="mm/yy" type="text" />
                                </div>
                            </div>
                            </form>
                            <div class="checkbox checkbox-small">
                                <label>
                                    <input class="i-check" type="checkbox" checked/>Add to My Cards</label>
                                </div>
                                <form id="payCredit">
                                <input class="btn btn-primary" type="submit" value="Proceed Payment" />
                                </form>
                            
                        </div>

						<h4>Pay with cash</h4>
						<img class="pp-img" src="https://dl.dropboxusercontent.com/u/62595112/cash_on_delivery.png" alt="Cash on delivery" title="Cash on delivery" />
                        <p>Important: You will be redirected to Cash on delivery website to securely complete your payment.</p>
                        <form id="cash">
                           <button class="btn btn-primary" type="submit">Checkout via cash on delivery</a>   
                           </form>
                       </div>

                        <div class="col-md-4" >
                            <h4>Shopping Cart:</h4>
                            <div class="booking-item-payment">
                                <header class="clearfix" id= "productosCarritos">
                                   
                                </header>
                                
                                <p class="booking-item-payment-total" id="totalCarrito">Total: <span>$1,155</span>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>  
                <div class="gap"></div>
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


