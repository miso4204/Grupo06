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
        $("#formularioCrear").submit(function(e) {
           
            e.preventDefault();     
            var jsonPeticion = JSON.stringify({
                    "nombre": $('#name').val(), 
                    "lugar": $('#destinationName').val(),
                    "ciudad": $('#destinationLocation').val(),
                    "precio": $('#price').val(),
                    "urlImagen": $('#urlImage').val(),
                    "fechaInicio": $('#date').val()
                     });
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "POST",
                url: "services/producto/crear", 
                data: jsonPeticion,
                contentType: false,
                processData: false,
                success: function(data)
                   {
                    var out = "";
                    
                       out+='<div class="alert alert-success" role="alert"><strong>Success!</strong> Your product has been created!.</div>'
                       document.getElementById("errormessage").innerHTML = out;
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                    var out = "";
                    
                       out+='<div class="alert alert-error"><strong>Error!</strong> A problem has been occurred while submitting your data.</div>'
                       document.getElementById("errormessage").innerHTML = out;
                }
            });
        });
});
        </script>

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
$("#buscarPorCiudad").submit(function(e) {
               
            e.preventDefault();                
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json',
                    'tipoMoneda':  '${usuarioSesion.tipoMoneda}'
                },
                datatype:"json",
                type: "GET",
                url: "services/reportes/ventas/" + $('#ciudadSearch').val(), 
                contentType: false,
                processData: false,
                success: function(data)
                   {
                    
                       
                     var out = "";
                     var urlIma="";
                     var calificaciones="";

                     var arr=JSON.parse(JSON.stringify(data));


                var ciudad = arr.ciudad;
                var totalVentas = arr.totalVentas; 
                var totalDineroEnVentas = arr.totalDineroEnVentas; 
                alert(ciudad+"-"+totalVentas+"-"+totalDineroEnVentas);
   out+='<thead>'+
                            '<tr>'+
                                '<th>Type</th>'+
                                '<th>Location</th>'+
                                '<th> Total Sales </th>'+
                                '<th>Total Cash Sales</th>'+
                            '</tr>'+
                        '</thead>'+
                        '<tbody>'+
                            '<tr>'+
                                '<td class="booking-history-type"><i class="fa fa-bolt"></i><small>activity</small>'+
                                '</td>'+
                                '<td class="booking-history-title">'+ciudad+'</td>'+
                                '<td>'+'$'+totalVentas+' '+signoPrecio+'</td>'+
                                '<td>'+'$'+totalDineroEnVentas+' '+signoPrecio+'</td>'+
                            '</tr>'+
                        '</tbody>'
                        document.getElementById("tablaReporte").innerHTML = out;

         
    
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
                    'Content-Type': 'application/json',
                    'tipoMoneda':  '${usuarioSesion.tipoMoneda}'
                },
                datatype:"json",
                type: "GET",
                url: "services/reportes/ventas/" + $('#dateOne').val()+"/"+$('#dateTwo').val(), 
                contentType: false,
                processData: false,
                success: function(data)
                   {
                     
                     var out = "";
                     var urlIma="";
                     var calificaciones="";

                     var arr=JSON.parse(JSON.stringify(data));

               

                var totalVentas = arr.totalVentas; 
                var totalDineroEnVentas = arr.totalDineroEnVentas; 
                  out+='<thead>'+
                            '<tr>'+
                                '<th>Type</th>'+
                                '<th> Total Sales </th>'+
                                '<th>Total Cash Sales</th>'+
                            '</tr>'+
                        '</thead>'+
                        '<tbody>'+
                            '<tr>'+
                                '<td class="booking-history-type"><i class="fa fa-bolt"></i><small>activity</small>'+
                                '</td>'+
                                '<td>'+'$'+totalVentas+' '+signoPrecio+'</td>'+
                                '<td>'+'$'+totalDineroEnVentas+' '+signoPrecio+'</td>'+
                            '</tr>'+
                        '</tbody>'
                        document.getElementById("tablaReporte").innerHTML = out;
    
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                       alert("Error: " + errorMessage);
                }
            });
            

        });

$("#buscarPorRatingCiudad").submit(function(e) {
               
            e.preventDefault();                
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                datatype:"json",
                type: "GET",
                url: "services/reportes/rating/" + $('#ciudadRatingSearch').val(), 
                contentType: false,
                processData: false,
                success: function(data)
                   {
                    
                       
                     var out = "";
                     var urlIma="";
                     var calificaciones="";

                     var arr=JSON.parse(JSON.stringify(data));
                    
                       out+='<thead>'+
                            '<tr>'+
                                '<th> Type</th>'+
                                '<th> Location</th>'+
                                '<th> Product Name</th>'+
                                '<th> Average Rate</th>'+
                                '<th> Reviews</th>'+
                            '</tr>'+
                        '</thead>'
                for(i = 0; i < arr.length; i++) {

                var ciudad = arr[i].ciudadProducto;
                var nombreProducto = arr[i].nombreProducto; 
                var calificacionPromedio = arr[i].calificacionPromedio; 
                var votantes = arr[i].votantes;
  
                   out+='<tbody>'+
                            '<tr>'+
                                '<td class="booking-history-type"><i class="fa fa-star-o"></i><small>activity</small>'+
                                '</td>'+
                                '<td class="booking-history-title">'+ciudad+'</td>'+
                                '<td class="booking-history-title">'+nombreProducto+'</td>'+
                                '<td>'+calificacionPromedio+'</td>'+
                                '<td>'+votantes+'</td>'+
                            '</tr>'+
                        '</tbody>'
                        document.getElementById("tablaReporteRating").innerHTML = out;

                      }
    
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                       alert("Error: " + errorMessage);
                }
            });
            

        });
$("#buscarPorRatingPaquete").submit(function(e) {
               
            e.preventDefault();                
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                datatype:"json",
                type: "GET",
                url: "services/reportes/ratingPaquete/" + $('#packageSearch').val(), 
                contentType: false,
                processData: false,
                success: function(data)
                   {
                    
                       
                     var out = "";
                     var urlIma="";
                     var calificaciones="";

                     var arr=JSON.parse(JSON.stringify(data));
                    
                       out+='<thead>'+
                            '<tr>'+
                                '<th> Type</th>'+
                                '<th> Package Name</th>'+
                                '<th> Average Rate</th>'+
                                '<th> Reviews</th>'+
                            '</tr>'+
                        '</thead>'
                for(i = 0; i < arr.length; i++) {

                
                var nombrePaquete = arr[i].nombreProducto; 
                var calificacionPromedio = arr[i].calificacionPromedio; 
                var votantes = arr[i].votantes;
  
                   out+='<tbody>'+
                            '<tr>'+
                                '<td class="booking-history-type"><i class="fa fa-star-o"></i><small>activity</small>'+
                                '</td>'+
                                '<td class="booking-history-title">'+nombrePaquete+'</td>'+
                                '<td>'+calificacionPromedio+'</td>'+
                                '<td>'+votantes+'</td>'+
                            '</tr>'+
                        '</tbody>'
                        document.getElementById("tablaReporteRating").innerHTML = out;

                      }
    
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
                                        <a href="user-profile.jsp">
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
            </header>

            <!--...........................-->
            <!-- INICIO LISTA DE DESTINOS  -->
            <!--...........................-->
            <div class="container">
                <div class="col-md-12">
                    <h3 class="mb20">ADMINISTRATION</h3>
                    <div class="row row-wrap">
                        <div class="col-md-12">
                            <div class="tabbable">
                                <ul class="nav nav-tabs" id="myTab">
<!--                                     <li class="active"><a href="#tab-1" data-toggle="tab">Product Registration</a> -->
<!--                                     </li> -->
                                    <li class="active"><a href="#tab-2" data-toggle="tab">Sales reports</a>
                                    </li>
                                    <li><a href="#tab-3" data-toggle="tab">Rating reports</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                      <!-- REPOORTES ----------------------------------- -->
                      <!-- REPOORTES ----------------------------------- -->

                    <div class="tab-pane fade in active" id="tab-2">
                        
                                        <h2>Search for Reports</h2>
                                        
                                            <div class="tabbable">
                                                <ul class="nav nav-pills nav-sm nav-no-br mb10" id="flightChooseTab">
                                                    <li class="active"><a href="#flight-search-1" data-toggle="tab">By date</a>
                                                    </li>
                                                    <li><a href="#flight-search-2" data-toggle="tab">By location</a>
                                                    </li>
                                                </ul>
                                                <div class="tab-content">
                                                    <div class="tab-pane fade in active" id="flight-search-1">
                                                        <div class="row">
                                                            
                                                            <div class="col-md-6">
                                                                <div class="input-daterange" data-date-format="yyyy-m-d">
                                                                    <div class="row">
                                                                        <div class="col-md-4">
                                                                            <div class="form-group form-group-lg form-group-icon-left"><i class="fa fa-calendar input-icon input-icon-highlight"></i>
                                                                                <label>Initial Date</label>
                                                                                <input class="form-control" name="start" type="text" id="dateOne" />
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-md-4">
                                                                            <div class="form-group form-group-lg form-group-icon-left"><i class="fa fa-calendar input-icon input-icon-highlight"></i>
                                                                                <label>Final Date</label>
                                                                                <input class="form-control" name="end" type="text" id="dateTwo"/>
                                                                            </div>
                                                                        </div>
                                                                         <div class="col-md-4"> 
                                                                            <label>Search</label>
                                                                            <form id="buscarPorFecha" action=""  method="post">
                                                                                 <button class="btn btn-primary btn-lg" type="submit">Search</button>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="flight-search-2">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group form-group-lg form-group-icon-left"><i class="fa fa-map-marker input-icon"></i>
                                                                            <label>From</label>
                                                                            <input class="typeahead form-control" id="ciudadSearch"placeholder="City, country" type="text" />
                                                                        </div>

                                                                    </div>
                                                                    <div class="col-md-4"> 
                                                                            <label>Search</label>
                                                                            <form id="buscarPorCiudad" action=""  method="post">
                                                                                 <button class="btn btn-primary btn-lg" type="submit">Search</button>
                                                                            </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                    
                        <table class="table table-bordered table-striped table-booking-history" id="tablaReporte">
                       
                    </table>
                                            </div>
                        <div class="tab-pane fade" id="tab-3">
                            <h2>Search for Reports</h2>
                                        
                                            <div class="tabbable">
                                                <ul class="nav nav-pills nav-sm nav-no-br mb10" id="flightChooseTab">
                                                    <li class="active"><a href="#search-1" data-toggle="tab">By package</a>
                                                    </li>
                                                    <li><a href="#search-2" data-toggle="tab">By location</a>
                                                    </li>
                                                </ul>
                                                <div class="tab-content">
                                                    <div class="tab-pane fade in active" id="search-1">
                                                         <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group form-group-lg form-group-icon-left"><i class="fa fa-map-marker input-icon"></i>
                                                                            <label>From</label>
                                                                            <input class="typeahead form-control" id="packageSearch"placeholder="Package name" type="text" />
                                                                        </div>

                                                                    </div>
                                                                    <div class="col-md-4"> 
                                                                            <label>Search</label>
                                                                            <form id="buscarPorRatingPaquete" action=""  method="post">
                                                                                 <button class="btn btn-primary btn-lg" type="submit">Search</button>
                                                                            </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="tab-pane fade" id="search-2">
                                                        <div class="row">
                                                            <div class="col-md-6">
                                                                <div class="row">
                                                                    <div class="col-md-6">
                                                                        <div class="form-group form-group-lg form-group-icon-left"><i class="fa fa-map-marker input-icon"></i>
                                                                            <label>From</label>
                                                                            <input class="typeahead form-control" id="ciudadRatingSearch"placeholder="City, country" type="text" />
                                                                        </div>

                                                                    </div>
                                                                    <div class="col-md-4"> 
                                                                            <label>Search</label>
                                                                            <form id="buscarPorRatingCiudad" action=""  method="post">
                                                                                 <button class="btn btn-primary btn-lg" type="submit">Search</button>
                                                                            </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                    
                        <table class="table table-bordered table-striped table-booking-history" id="tablaReporteRating">
                       
                    </table>
                        </div>
                    </div>
                </div>
            </div>
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
                    <a class="logo" href="index.jsp">
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


