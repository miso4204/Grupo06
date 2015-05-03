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

    	
// Funcion que se ejecuta al precionar el boton con id save para alamcenar el tipoMoneda seleccionado
$('#save').click(function() { var tipoMoneda = $("input:radio[name ='tipoMoneda']:checked").val();   });    	
    	

$("#cambiarPass").submit(function(e) {
            
            e.preventDefault();     
            var jsonPeticion = JSON.stringify({
                    "userName": '${usuarioSesion.usuario}', 
                    "passActual": $('#currentPass').val(),
                    "passNuevo": $('#newPass').val(),
                    "passNuevoValidate": $('#newAgainPass').val()
                     });
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "POST",
                url: "services/usuario/change_pass", 
                data: jsonPeticion,
                contentType: false,
                processData: false,
                success: function(data)
                   {
                	
                	 var out = "";
                  	if (data.codigoRespuesta == 'OK') {
                  		                       
                  	  out+='<div class="alert alert-success" role="alert"><strong>Success!</strong> Your password has been updated!.</div>'
                          document.getElementById("errormessage").innerHTML = out;
                  	}
                  	else {
                  		 out+='<div class="alert alert-error"><strong>Error!</strong> ' + data.mensaje + '</div>'
                               document.getElementById("errormessage").innerHTML = out;
                  	}                   
                     
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                    var out = "";
                    
                       out+='<div class="alert alert-error"><strong>Error!</strong> A problem has been occurred while submitting your data.</div>'
                       document.getElementById("errormessage").innerHTML = out;
                }
            });
        });
    });
    

    // Funcion para setear el tipo de moneda del cliente
    $(function() {
        var $radios = $('input:radio[name=tipoMoneda]');
        if($radios.is(':checked') === false) {
            $radios.filter('[value=${usuarioSesion.tipoMoneda}]').prop('checked', true);
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

            <!-- TOP AREA -->
 <div class="gap"></div>
                    <div class="container">
                    <div class="row">
                <div class="col-md-3">
                    <aside class="user-profile-sidebar">
                        <div class="user-profile-avatar text-center">
                            <img src="img/user_image.png" alt="Image Alternative text" title="AMaze" />
                            <h5>${usuarioSesion.nombre}</h5>
                            <p>Member Since April 2015</p>
                        </div>
                        <ul class="list user-profile-nav">
                            <li><a href="pages/client/payment.jsp"><i class="fa fa-user"></i>My cart</a>
                            </li>
                            <li><a href="pages/client/user_profile.jsp"><i class="fa fa-cog"></i>Settings</a>
                            </li>
                        </ul>
                    </aside>
                </div>
                <div class="col-md-9">
                    <div class="row">
                        <div class="col-md-5">
                            <form action="pages/update_profile" id="changeProfile" method="post">
                                <h4>Personal Infomation</h4>
                                <div class="form-group form-group-icon-left"><i class="fa fa-user input-icon"></i>
                                    <label>User Name</label>
                                    <input class="form-control" value="${usuarioSesion.usuario}" type="text" disabled/>
                                </div>
                                <div class="form-group form-group-icon-left"><i class="fa fa-user input-icon"></i>
                                    <label>Full Name</label>
                                    <input id="nombre" name="nombre" class="form-control" value="${usuarioSesion.nombre}" type="text" required />
                                </div>
                                <div class="form-group form-group-icon-left"><i class="fa fa-envelope input-icon"></i>
                                    <label>E-mail</label>
                                    <input id="email" name="email" class="form-control" value="${usuarioSesion.email}" type="email" required/>
                                </div>
                                <div class="form-group form-group-icon-left"><i class="fa fa-phone input-icon"></i>
                                    <label>Phone Number</label>
                                    <input id="telefono" name="telefono" class="form-control" value="${usuarioSesion.telefono}" type="text" required/>
                                </div>                               
                                <div class="form-group form-group-icon-left"><i class="fa fa-map-marker input-icon"></i>
                                    <label>Street Address</label>
                                    <input id="direccion" name="direccion" class="form-control" value="${usuarioSesion.direccion}" type="text" required/>
                                </div>
                                
                                                              
                                <label>Select your prefered kind money</label>                      
								<label class="radio-inline radio-small"> <input class="i-radio" type="radio" name="tipoMoneda" id="tipoMoneda" value="DOLAR"> Dollar </label>
								<label class="radio-inline radio-small"><input class="i-radio" type="radio" name="tipoMoneda" id="tipoMoneda" value="COLOMBIAN_PESOS">Pesos Colombianos </label>
								<label class="radio-inline radio-small"><input class="i-radio" type="radio" name="tipoMoneda" id="tipoMoneda" value="EURO">Euro </label>
								
                                <hr>
                                <input type="submit" class="btn btn-primary" id="save" value="Save Changes">
                                <h3>${mensaje}</h3>
                            </form>
                        </div>
                        <div class="col-md-4 col-md-offset-1">
                            <h4>Change Password</h4>
                            <form id="cambiarPass" action=""  method="post">
                                <div class="form-group form-group-icon-left"><i class="fa fa-lock input-icon"></i>
                                    <label>Current Password</label>
                                    <input class="form-control" id="currentPass" type="password" required/>
                                </div>
                                <div class="form-group form-group-icon-left"><i class="fa fa-lock input-icon"></i>
                                    <label>New Password</label>
                                    <input class="form-control" id="newPass" type="password" required/>
                                </div>
                                <div class="form-group form-group-icon-left"><i class="fa fa-lock input-icon"></i>
                                    <label>New Password Again</label>
                                    <input class="form-control" id="newAgainPass" type="password" required />
                                </div>
                                <hr />
                                <button class="btn btn-primary" type="submit"  "value="Change Password">Change Password</button>
                            	<div id="errormessage"></div>
                            </form>
                        </div>
                    </div>

                </div>
            </div>        


                    </div>
                    <!-- END TOP AREA  -->


                    <div class="gap"></div>
                    


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


