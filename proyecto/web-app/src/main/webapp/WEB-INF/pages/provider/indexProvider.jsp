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
 <script src="js/bootstrap-datetimepicker.js"></script>

 <script type="text/javascript">

$(document).ready(function() {
    var template = $('#template'),
        id = 0;
    
    $("#add-line").click(function() {
        var row = template.clone();
        template.find("input:text").val("");
        row.attr('id', 'row_' + (++id));
        row.find('.remove').show();
        template.before(row);
    });
    
    $('.form-fields').on('click', '.remove', function(){
        $(this).closest('tr').remove();
    });
});


 </script>


<script type="text/javascript">


    $(document).ready(function() {
         var aireAcondicionado=false;
         var piscina = false;
         var zonasVerdes=false;
         var vigilancia=false;
         var actividadesAgregadas="[";
         var checkVueloSeleccionado=false;
         var obj;
     $("#flightChecked").on('ifChecked', function(event){
         $("#airline").prop('required',true);
         $("#flightPrice").prop('required',true);
         $("#origenFlight").prop('required',true);
         checkVueloSeleccionado=true;
         
         
            
    });
     
     $("#flightChecked").on('ifUnchecked', function(event){
        $("#airline").prop('required',false);
        $("#flightPrice").prop('required',false);
        $("#origenFlight").prop('required',false);

     });

     $("#lodgingChecked").on('ifChecked', function(event){
         $("#occupancy").prop('required',true);
         $("#lodgingrice").prop('required',true);
         
            
    });
     
     $("#lodgingChecked").on('ifUnchecked', function(event){
        $("#occupancy").prop('required',false);
        $("#lodgingrice").prop('required',false);
        
        
     });

     $("#activityChecked").on('ifChecked', function(event){
         $("#airline").prop('required',true);
         $("#flightPrice").prop('required',true);
         
            
    });
     
     $("#activityChecked").on('ifUnchecked', function(event){
        $("#airline").prop('required',false);
        $("#flightPrice").prop('required',false);

     });

     $("#swPool").on('ifChecked', function(event){
        piscina= true;      
    });
     
      $("#security").on('ifChecked', function(event){
        vigilancia= true;      
    });

      $("#greenArea").on('ifChecked', function(event){
        zonasVerdes= true;      
    });
       $("#airCond").on('ifChecked', function(event){
        aireAcondicionado= true;      
    });
     
     $("#tablaAct").each(function (index3) {
        alert("tabla");
        var rowCount = $("#tablaAct tbody tr").length;
        alert(rowCount);
        var i=0;
        $("#tablaAct tbody tr").each(function (index) 
        {
               alert("fila"+index);

            var campo1, campo2, campo3, campo4;
            i++;

            $(this).children("td").each(function (index2) 
            {
                
                switch (index2) 
                {
                    case 0: campo1 = $("#activityName").val();
                            break;
                    case 1: campo2 = $("#activityDescription").val();
                            break;
                    case 2: campo3 = $("#activityPrice").val();
                            break;
                    case 3: campo4 = $("#dateActivity").val();
                            break;
                }
                  
            })
            actividadesAgregadas=actividadesAgregadas+'{"nombreActividad":"'+ campo1 +
            '","descripcion":"' + campo2 + '","fechaActividad":"' + campo4 + '","costoActividad":"' + campo3 + '"}';
            if(i==rowCount){
                actividadesAgregadas=actividadesAgregadas+"]";
            }
            else{
                actividadesAgregadas=actividadesAgregadas+",";
            }
            
             })
            alert(actividadesAgregadas);
             obj = jQuery.parseJSON(actividadesAgregadas);

            //var obj = JSON.parse(actividadesAgregadas);
            alert(JSON.stringify(obj));
        });
   



        $("#formularioCrear").submit(function(e) {
           
           e.preventDefault();   
            //datos actividad
            /*var nombreAct=$('#activityName').val();
            var fechaActividad = $('#dateActivity').val();
            var descripcion= $('#activityDescription').val();
            var costoActividad= $('#activityPrice').val();
            */

            //datos vuelos
            var vuelo="";
            var precioVuelo = "";
            var origen= "";
            var destino= "";
            var fechaSalida= "";
            var fechaLlegada="";

            //datos Alojamiento
            var tipo ="";
            var numMaxPersonas = 0;
            var precioPorDia ="";
            if(checkVueloSeleccionado){
            //datos vuelos
             //vuelo=$('#airline').val();
             precioVuelo = $('#flightPrice').val();
             origen= $('#origenFlight').val();
             destino= $('#destinationLocation').val();
             fechaSalida= $('#dateOneFlight').val();
             fechaLlegada= $('#dateTwoFlight').val();
            }
            else{
             vuelo="";
             precioVuelo = "";
             origen= "";
             destino= "";
             fechaSalida= "";
             fechaLlegada="";

            }

            //datos Alojamiento
             //tipo =
             numMaxPersonas = 0;
             //precioPorDia =('#lodgingPrice').val();



            

              
            var activities = $('input[name=activities]:checked').map(function() { 
                return this.value; 
            }).get().join(',');

            var jsonPeticion = JSON.stringify({
                    "nombre": $('#name').val(), 
                    "lugar": $('#destinationName').val(),
                    "ciudad": $('#destinationLocation').val(),
                    "precio": $('#price').val(),
                    "urlImagen": $('#urlImage').val(),
                    "fechaInicio": $('#date').val(),
                    "tipoMoneda": $('#tipoMoneda:checked').val(),
                    "proveedorId": '${usuarioSesion.id}',
                    "descripcion":  $('#description').val(),
                    "actividades" : obj,
                  "vuelo" :{"aerolinea":$('#airline:checked').val(), "precioVuelo":precioVuelo, "origen":origen, "destino":destino,"fechaSalida":fechaSalida, "fechaLlegada":fechaLlegada},
                  "alojamiento":{"tipo":$('#tipoAlojamiento:checked').val(),"numMaxPersonas":"9","precioPorDia":$('#lodgingPrice').val(),"aireAcondicionado":aireAcondicionado,"piscina":piscina,
                  "zonasVerdes":zonasVerdes, "vigilancia":vigilancia}
                    
                   
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
                                        <a  href="pages/provider/provider_profile.jsp">
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
                    <h3 class="mb20">PROVIDER</h3>
                    <div class="row row-wrap">
                        <div class="col-md-12">
                            <div class="tabbable">
                                <ul class="nav nav-tabs" id="myTab">
                                    <li class="active"><a href="#tab-1" data-toggle="tab">Product Registration</a>
                                    </li>
                                    <li><a href="#tab-2" data-toggle="tab">Aplicar descuentos</a>
                                    </li>
                                    <li><a href="#tab-3" data-toggle="tab">Functionality ZYX</a>
                                    </li>
                                </ul>
                                <div class="tab-content">
                                    <div class="tab-pane fade in active" id="tab-1">
                                        <div class="col-md-12">
                                            <form id="formularioCrear" action="" method="post" novalidate>
                                                <div class="row">
                                                    <div class="col-md-6">

                                                        <div class="form-group form-group-icon-left"><i class="fa fa-pencil input-icon input-icon-bounce"></i>
                                                            <label>Name</label>
                                                            <input class="form-control" placeholder="Paquete Semana Santa" type="text" name="name" id="name" required />
                                                        </div>
                                                        <div class="form-group form-group-icon-left"><i class="fa fa-plane input-icon input-icon-bounce"></i>
                                                            <label>Destination name</label>
                                                            <input class="form-control" placeholder="Nevado de Santa Marta" type="text" name="destinationName" id="destinationName" required/>
                                                        </div>
                                                        <div class="form-group form-group-icon-left"><i class="fa fa-map-marker input-icon input-icon-bounce"></i>
                                                            <label>Destination Location</label>
                                                            <input class="form-control" placeholder="Santa Marta - Colombia" type="text" name="destinationLocation" id="destinationLocation" required/>
                                                        </div>
                                                        <div class="form-group form-group-icon-left"><i class="fa fa-money input-icon input-icon-bounce"></i>
                                                            <label>Price</label>
                                                            <input class="form-control" placeholder="2000" type="number" name="price" id="price"  min="0" required />
                                                        </div>

                                                        <div class="form-group form-group-icon-left"><i class="fa fa-calendar input-icon input-icon-bounce"></i>
                                                            <label>Date</label>
                                                            <input class="form-control" placeholder="01-12-2015" type="date" name="date" id="date" required/>
                                                        </div>
                                                        <div class="form-group form-group-icon-left"><i class="fa fa-picture-o input-icon input-icon-bounce"></i>
                                                            <label>URL Image</label>
                                                            <input class="form-control" placeholder="http://imagen.png" type="text" name="urlImage" id="urlImage" required/>
                                                        </div>

                                                        <label>Select your prefered kind money</label>  
                                                        <div class="radio-inline radio-small">
                                                          <label><input class="i-radio"  type="radio" id="tipoMoneda" name="tipoMoneda" value="DOLAR">Dollar</label>
                                                      </div>
                                                      <div class="radio-inline radio-small">
                                                          <label><input class="i-radio"  type="radio" id="tipoMoneda" name="tipoMoneda" value="COLOMBIAN_PESOS">Pesos Colombianos</label>
                                                      </div>
                                                      <div class="radio-inline radio-small">
                                                          <label><input class="i-radio"  type="radio" id="tipoMoneda" name="tipoMoneda" value="EURO">Euro</label>
                                                      </div>


                                                  </div>

                                                  <div class="col-md-6">
                                           <div class="tabbable">
                                                <ul class="nav nav-tabs" id="myTab">
                                                    <li class="active">
                                                        <a href="#flight" data-toggle="tab">
                                                             <div class="checkbox-inline checkbox-small">
                                                                <input class="i-check" type="checkbox"  id="flightChecked" name="flightChecked" value="flight">Flights<br>
                                                             </div>
                                                         </a>
                                                    </li> 
                                                    <li>
                                                        <a href="#lodging" data-toggle="tab">
                                                            <div class="checkbox-inline checkbox-small">
                                                                <input class="i-check" type="checkbox" id="lodgingChecked" name="lodgingChecked" value="flight">Lodging<br>
                                                             </div>
                                                         </a>
                                                    </li>
                                                    <li><a href="#activities" data-toggle="tab">
                                                         <div class="checkbox-inline checkbox-small">
                                                                <input class="i-check" type="checkbox" id="activityChecked"  name="activityChecked" value="flight">Activities<br>
                                                             </div>

                                                    </a>
                                                    </li>
                                                </ul>
                                             <div class="tab-content">
                                                <div class="tab-pane fade in active" id="flight">
                                                    <div class="col-md-12">
                                                        <div class="row">
                                                            
                                                                <div class="form-group form-group-lg form-group-icon-left">
                                                                    <label>Select a flight</label>
                                                                </div>
                                                                <div class="input-daterange" data-date-format="yyyy-m-d">
                                                                    <div class="row">
                                                                        <div class="col-md-6">
                                                                            
                                                                                <label>Check-in</label>
                                                                                <input size="16" type="text" value="2012-06-15 14:45" readonly class="form_datetime"id="dateOneFlight">


                                                                        </div>
                                                                        <div class="col-md-6">
                                                                            
                                                                                <label>Check-out</label>
                                                                                 <input size="16" type="text" value="2012-06-15 14:45" readonly class="form_datetime" id="dateTwoFlight">
                                                                            
                                                                        </div>
                                                                        
                                                                    </div>
                                                                </div>
                                                                <div class="form-group form-group-icon-left"><i class="fa fa-plane input-icon input-icon-bounce"></i>
                                                                    <label>Origen flight</label>
                                                                    <input class="form-control" placeholder="Cali" type="text" name="origenFlight" id="origenFlight" />
                                                                </div>
                                                                <div class="form-group form-group-icon-left">
                                                                    <label>Select the airline</label> 
                                                        <div class="radio-inline radio-small">
                                                          <label><input class="i-radio"  type="radio" id="airline" name="airline" value="LAN">Lan</label>
                                                      </div>
                                                      <div class="radio-inline radio-small">
                                                          <label><input class="i-radio"  type="radio" id="airline" name="airline" value="AVIANCA">Avianca</label>
                                                      </div>
                                                      <div class="radio-inline radio-small">
                                                          <label><input class="i-radio"  type="radio" id="airline" name="airline" value="VIVA_COLOMBIA">Viva Colombia</label>
                                                      </div>
                                                                </div>
                                                                <div class="form-group form-group-icon-left"><i class="fa fa-money input-icon input-icon-bounce">
                                                                </i>
                                                                <label>Price per person</label>
                                                                <input class="form-control" placeholder="2000" type="number" name="flightPrice" id="flightPrice"  min="0"  />
                                                            </div>
                                                        
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane fade" id="lodging">
                                                <div class="col-md-12">
                                                    <div class="row">
                                                        
                                                            <div class="form-group form-group-lg form-group-icon-left">
                                                                    <label>Select facilities</label>
                                                                </div>
                                                            <div class="checkbox-inline checkbox-small">
                                                                <input class="i-check" type="checkbox" id ="airCond" name="airCond" value="airCond">Air conditioner<br>
                                                            </div>
                                                            <div class="checkbox-inline checkbox-small">
                                                                <input class="i-check" type="checkbox"  id ="swPool"  name="swPool" value="swPool" >Swimming pool<br>
                                                            </div>
                                                            <div class="checkbox-inline checkbox-small">
                                                                <input class="i-check" type="checkbox"  id ="security" name="security" value="security" >Security<br>

                                                            </div>
                                                            <div class="checkbox-inline checkbox-small">
                                                                <input class="i-check" type="checkbox"  id ="greenArea" name="greenArea" value="greenArea" >Green areas<br>

                                                            </div>
                                                            <div class="gap gap-mini"></div>
                                                            <label>Select the type of</label>  
                                                        <div class="radio-inline radio-small">
                                                          <label><input class="i-radio"  type="radio" id="tipoAlojamiento" name="tipoAlojamiento" value="HOTEL">Hotel</label>
                                                      </div>
                                                      <div class="radio-inline radio-small">
                                                          <label><input class="i-radio"  type="radio" id="tipoAlojamiento" name="tipoAlojamiento" value="FINCA">Finca</label>
                                                      </div>
                                                      <div class="radio-inline radio-small">
                                                          <label><input class="i-radio"  type="radio" id="tipoAlojamiento" name="tipoAlojamiento" value="HOSTAL">Hostal</label>
                                                      </div>
                                                            
                                                            <div class="form-group form-group-icon-left"><i class="fa fa-money input-icon input-icon-bounce">
                                                            </i>
                                                            <label>Price per person</label>

                                                            <input class="form-control" placeholder="price per person" type="number" name="lodgingPrice" id="lodgingPrice"  min="0" />
                                                        </div>
                                                    
                                                </div>
                                            </div>
                                        </div>

                                                <div class="tab-pane fade" id="activities">
                                                    <div>
                                                      <div class="form-fields">
                                                        <table id="tablaAct"class="table table-bordered table-striped table-booking-history">
                                                            <thead>
                                                          <tr>
                                                            <th>Activity Name</th>
                                                            <th>Description</th>
                                                            <th>Price per person</th>
                                                            <th>Date</th>
                                                            <th></th>
                                                        </tr>
                                                         </thead>
                                                         <tbody>
                                                        <tr id="template">
                                                            <td><input id ="activityName"type="text" class="form_id" style="width:100px" size="5px"value=""/></td>
                                                            <td><input id = "activityDescription"type="text" class="form_name" style="width:100px" size="5px"value=""/></td>
                                                            <td><input id = "activityPrice" type="number" class="form_col3" style="width:100px" value="0" size="5px" value="3"/></td>
                                                            <td><div class="input-daterange" data-date-format="yyyy-m-d">
                                                                    <div class="row">
                                                                    
                                                                        <div class="col-md-6">
                                                                            <div class="form-group form-group-lg form-group-icon-left">
                                                                                <input class="form-control" name="end" type="text" id="dateActivity"/>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div></td>
                                                            <td><input type="button" class="remove" value="remove" style="display:none" /></td>
                                                        </tr>
                                                    </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                            <br>
                                                <input class="btn btn-ghost btn-default" type="button" id="add-line" value="Add Activity" >
                                            
                                                </div>

                                            </div>

                                           </div>
                                 

                            </div>

                        </div>

                                            <div class="form-group form-group-icon-left">
                                                    <button class="btn btn-primary btn-lg" type="submit" >Register</button>
                                                </div>
                                                <div class="form-group form-group-icon-left">
                                                   <div id="errormessage"></div>
                                               </div>
                                           
                    </form>
                </div>
            </div>
                      <div class="tab-pane fade" id="tab-2">
                        
                        <div class="row">
                            <div class="col-md-6">

                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <form id ="asd"action="#" role="form">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                     <button type="button" class="btn btn-labeled btn-success">
                                                     <span class="btn-label"><i class="fa fa-thumbs-up"></i></span>PSE</button>
                                                </span>
                                                <input type="text" class="form-control" placeholder="(%) Discount " required="">
                                                <span class="input-group-btn">
                                                  <button class="btn btn-danger btn-circle" type="submit">Disapply discount</button>
                                              </span>                        
                                          </div>
                                      </form>
                                  </div>
                              </div>
                              <div class="panel panel-default">
                                    <div class="panel-body">
                                        <form id ="asd2" action="#" role="form">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                    <button type="button" class="btn btn-labeled btn-danger">
                                                     <span class="btn-label"><i class="fa fa-thumbs-down"></i></span>Card</button>
                                                </span>
                                                <input type="text" class="form-control" placeholder="(%) Discount " required="">
                                                <span class="input-group-btn">
                                                  <button class="btn btn-success btn-circle" type="submit">Apply discount</button>
                                              </span>                        
                                          </div>
                                      </form>
                                  </div>
                              </div>
                              <div class="panel panel-default">
                                    <div class="panel-body">
                                        <form action="#" role="form">
                                            <div class="input-group">
                                                <span class="input-group-btn">
                                                     <button type="button" class="btn btn-labeled btn-danger">
                                                     <span class="btn-label"><i class="fa fa-thumbs-down"></i></span>Cash</button>
                                                </span>
                                                <input type="text" class="form-control" placeholder="(%) Discount " required="">
                                                <span class="input-group-btn">
                                                  <button class="btn btn-success btn-circle" type="submit">Apply discount</button>
                                              </span>                        
                                          </div>
                                      </form>
                                  </div>
                              </div>



                          </div>

                          <div class="col-md-6">
                            <label>Discounts.</label>
                            



                        </div>

                    </div>

                </div>
                        <div class="tab-pane fade" id="tab-3">
                            <p class="mt10">PROXIMAMENTE...</p>
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

   <script type="text/javascript">
    $(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});
</script> 
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


