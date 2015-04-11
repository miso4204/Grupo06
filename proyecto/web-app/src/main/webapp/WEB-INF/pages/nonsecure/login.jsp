<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
  <base href="${pageContext.request.contextPath}/" >
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Login</title>

  <!-- Bootstrap -->
  
  <script src="js/jquery.js"></script>
  <link href="css/bootstrap.css" rel="stylesheet">

<script type="text/javascript">
    $(document).ready(function() {
        $("#formularioCrearUsuarioNormal").submit(function(e) {
           
            e.preventDefault();     
            var jsonPeticion = JSON.stringify({
                    "usuario": $('#normalUser').val(), 
                    "password": $('#normalPass').val(),
                    "nombre": $('#normalFN').val(),
                    "direccion": $('#normalSA').val(),
                    "telefono": $('#normalPN').val(),
                    "rol": 'CLIENT',
                    "email": $('#normalEmail').val()
                     });
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "POST",
                url: "services/usuario/crear", 
                data: jsonPeticion,
                contentType: false,
                processData: false,
                success: function(data)
                   {
                	 var out = "";
                 	if (data.codigoRespuesta == 'OK') {
                 		                       
                          out+='<div class="alert alert-success" role="alert"><strong>Success!</strong> Your user has been created!.</div>'
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
$("#formularioCrearProvider").submit(function(e) {
           
            e.preventDefault();     
            var jsonPeticion = JSON.stringify({
                    "usuario": $('#proUser').val(), 
                    "password": $('#proPass').val(),
                    "nombre": $('#proFN').val(),
                    "direccion": $('#proSA').val(),
                    "telefono": $('#proPN').val(),
                    "rol": 'PROVIDER',
                    "email": $('#proEmail').val(),
                    "website": $('#provWS').val()
                     });
            $.ajax({
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                datatype:"json",
                type: "POST",
                url: "services/usuario/crear", 
                data: jsonPeticion,
                contentType: false,
                processData: false,
                success: function(data)
                   {
                	 var out = "";
                	if (data.codigoRespuesta == 'OK') {
                		                       
                         out+='<div class="alert alert-success" role="alert"><strong>Success!</strong> Your user has been created!.</div>'
                         document.getElementById("errormessage2").innerHTML = out;
                	}
                	else {
                		 out+='<div class="alert alert-error"><strong>Error!</strong> ' + data.mensaje + '</div>'
                             document.getElementById("errormessage2").innerHTML = out;
                	}
                   
                   },
                error: function(jqXHR, textStatus, errorMessage) {
                    var out = "";
                    
                       out+='<div class="alert alert-error"><strong>Error!</strong> A problem has been occurred while submitting your data.</div>'
                       document.getElementById("errormessage2").innerHTML = out;
                }
            });
        });
});
        </script>
  </head>
  <body>
     <div class="container">    
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-info" >
                <div class="panel-heading">
                    <div class="panel-title">Sign In</div>
                    <div style="float:right; font-size: 80%; position: relative; top:-10px"><a href="#">Forgot password?</a></div>
                </div>     

                <div style="padding-top:30px" class="panel-body" >

                    <div style="display:none" id="login-alert" class="alert alert-danger col-sm-12"></div>
                    <h3>${mensajeError}</h3>
                    <form id="loginform" class="form-horizontal" role="form" action="pages/login" method="post">

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                            <input id="login-username" type="text" class="form-control" name="username" value="" placeholder="username or email">                                        
                        </div>

                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="login-password" type="password" class="form-control" name="password" placeholder="password">
                        </div>

                        <div style="margin-top:10px" class="form-group">
                            <!-- Button -->

                            <div class="col-sm-12 controls">
                              <a id="btn-login"  class="btn btn-success" onclick="$(this).closest('form').submit()">Login  </a>


                          </div>
                      </div>


                      <div class="form-group">
                        <div class="col-md-12 control">
                            <div style="border-top: 1px solid#888; padding-top:15px; font-size:85%" >
                                Don't have an account! 
                                <a href="#" onClick="$('#loginbox').hide(); $('#signupbox').show()">
                                    Sign Up Here
                                </a>
                            </div>
                        </div>
                    </div>    
                </form>     



            </div>                     
        </div>  
    </div>
    <div id="signupbox" style="display:none; margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="panel-title">Sign Up</div>
                <div style="float:right; font-size: 85%; position: relative; top:-10px"><a id="signinlink" href="#" onclick="$('#signupbox').hide(); $('#loginbox').show()">Sign In</a></div>
            </div>  
            <div class="panel-body" >
                

                    <div id="signupalert" style="display:none" class="alert alert-danger">
                        <p>Error:</p>
                        <span></span>
                    </div>
                    <div class="tabbable">
                        <ul class="nav nav-tabs" id="flightChooseTab">
                            <li class="active"><a href="#flight-search-1" data-toggle="tab">As User</a>
                            </li>
                            <li><a href="#flight-search-2" data-toggle="tab">As Provider</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="flight-search-1">
                                 <form id="formularioCrearUsuarioNormal" class="form-horizontal" action="" method="post">
                                    <div class="form-group">
                                        <label for="email" class="col-md-3 control-label">User</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" name="email" id="normalUser" placeholder="User" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-md-3 control-label">Password</label>
                                        <div class="col-md-9">
                                            <input type="password" class="form-control" name="passwd" id="normalPass" placeholder="Password" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="firstname" class="col-md-3 control-label">Full Name</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" name="firstname" id="normalFN" placeholder="Full Name" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="lastname" class="col-md-3 control-label">Street Address</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" name="lastname" id="normalSA" placeholder="Street Address" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-md-3 control-label">Phone number</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" name="passwd" id="normalPN" placeholder="Phone number" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="icode" class="col-md-3 control-label">E-mail</label>
                                        <div class="col-md-9">
                                            <input type="email" class="form-control" name="icode" id="normalEmail" placeholder="email@gmail.com" required>
                                        </div>
                                    </div>
                                    

                                    <div class="form-group">
                                        <!-- Button -->                                        
                                        <div class="col-md-offset-3 col-md-9">
                                            <button id="btn-signup" type="submit" class="btn btn-info"><i class="icon-hand-right"></i> &nbsp Sign Up</button> 
                                             <div id="errormessage"></div>
                                        </div>
                                    </div>      
                                </form>
                            </div>
                            <div class="tab-pane fade" id="flight-search-2">
                                 <form id="formularioCrearProvider" class="form-horizontal"  action="" method="post">
                                 <div class="form-group">
                                        <label for="email" class="col-md-3 control-label">User</label>
                                        <div class="col-md-9">
                                            <input type="email" class="form-control" name="email" id="proUser" placeholder="User" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-md-3 control-label">Password</label>
                                        <div class="col-md-9">
                                            <input type="password" class="form-control" name="passwd" id="proPass" placeholder="Password" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="firstname" class="col-md-3 control-label">Full Name</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" name="firstname" id="proFN" placeholder="Full Name" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="lastname" class="col-md-3 control-label">Street Address</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" name="lastname" id="proSA" placeholder="Street Address" required>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="password" class="col-md-3 control-label">Phone number</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" name="passwd" id="proPN" placeholder="Phone number" required>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="icode" class="col-md-3 control-label">E-mail</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" name="icode" id="proEmail" placeholder="email@gmail.com" required>
                                        </div>
                                    </div>
                                <div class="form-group">
                                        <label for="icode" class="col-md-3 control-label">Web Site</label>
                                        <div class="col-md-9">
                                            <input type="text" class="form-control" name="icode" id="provWS" placeholder="http://www.hoooola.com" required>
                                        </div>
                                    </div>

                                <div class="form-group">
                                    <!-- Button -->                                        
                                    <div class="col-md-offset-3 col-md-9">
                                        <button id="btn-signup" type="submit" class="btn btn-info"><i class="icon-hand-right"></i> &nbsp Sign Up</button>
                                         <div id="errormessage2"></div> 
                                    </div>
                                </div>      
                            </form>
                        </div>
                    </div>
                </div>


                
            </div>
        </div>




    </div> 
</div>

<script src="js/bootstrap.js"></script>


</body>
</html>