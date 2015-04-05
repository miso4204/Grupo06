/*
        *Función para armar las estrellas de colores
        */ 
function estructuraCalificacion(puntuacion,reviews) {
                    var estructuraCalificacion="";
                //Estructura estrellas.
                if (puntuacion==0){
                    estructuraCalificacion +='<h2 class="lh1em mt40">Not recommend!</h2>'+
                                            '<div class="booking-item-rating">'+
                                            '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                           ' </ul>'+
                                           '<b >'+puntuacion+'</b> of 5 <small class="text-smaller">guest rating</small></span>'+
                                            '<p><a class="text-default" href="#">based on '+reviews+' reviews</a>'+
                                            '</p>'+
                                         '</div>'
                }
                else if (puntuacion==1) { 
                estructuraCalificacion += '<h2 class="lh1em mt40">Normal!</h2>'+
                                            '<div class="booking-item-rating">'+
                                            '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                           ' </ul>'+
                                           '<b >'+puntuacion+'</b> of 5 <small class="text-smaller">guest rating</small></span>'+
                                            '<p><a class="text-default" href="#">based on '+reviews+' reviews</a>'+
                                            '</p>'+
                                         '</div>'}
                else if (puntuacion==2) { 
                estructuraCalificacion +=  '<h2 class="lh1em mt40">Good!</h2>'+
                                            '<div class="booking-item-rating">'+
                                            '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                           ' </ul>'+
                                           '<b >'+puntuacion+'</b> of 5 <small class="text-smaller">guest rating</small></span>'+
                                            '<p><a class="text-default" href="#">based on '+reviews+' reviews</a>'+
                                            '</p>'+
                                         '</div>'}
                else if (puntuacion==3) { 
                estructuraCalificacion +=  '<h2 class="lh1em mt40">Very Good!</h2>'+
                                            '<div class="booking-item-rating">'+
                                            '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                           ' </ul>'+
                                           '<b >'+puntuacion+'</b> of 5 <small class="text-smaller">guest rating</small></span>'+
                                            '<p><a class="text-default" href="#">based on '+reviews+' reviews</a>'+
                                            '</p>'+
                                         '</div>'}
                else if (puntuacion==4) { 
                estructuraCalificacion +=  '<h2 class="lh1em mt40">Perfect!</h2>'+
                                            '<div class="booking-item-rating">'+
                                            '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                           ' </ul>'+
                                           '<b >'+puntuacion+'</b> of 5 <small class="text-smaller">guest rating</small></span>'+
                                            '<p><a class="text-default" href="#">based on '+reviews+' reviews</a>'+
                                            '</p>'+
                                         '</div>'}
                else if (puntuacion==5) { 
                estructuraCalificacion +=  '<h2 class="lh1em mt40">Exeptional!</h2>'+
                                            '<div class="booking-item-rating">'+
                                            '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                           ' </ul>'+
                                           '<b >'+puntuacion+'</b> of 5 <small class="text-smaller">guest rating</small></span>'+
                                            '<p><a class="text-default" href="#">based on '+reviews+' reviews</a>'+
                                            '</p>'+
                                         '</div>'};
                return estructuraCalificacion;
            }      

    
/*
        *Función para armar las estrellas de colores
        */ 
function estructuraCalificacionBasica(a) {
                    var estructuraCalificacion="";
                //Estructura estrellas.
                if (a==0){
                    estructuraCalificacion += '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                           ' </ul>'
                }
                else if (a==1) { 
                estructuraCalificacion += '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                           ' </ul>'}
                else if (a==2) { 
                estructuraCalificacion += '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                           ' </ul>'}
                else if (a==3) { 
                estructuraCalificacion += '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                           ' </ul>'}
                else if (a==4) { 
                estructuraCalificacion += '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-empty"></i>'+
                                                '</li>'+
                                           ' </ul>'}
                else if (a==5) { 
                estructuraCalificacion += '<ul class="icon-group booking-item-rating-stars">'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star-"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                                '<li><i class="fa fa-star"></i>'+
                                                '</li>'+
                                           ' </ul>'};
                return estructuraCalificacion;
            }     

            /*
        *Función para armar las caritas de colores
        */ 
function estructuraCalificacionCaritas(puntuacionUbicacion,puntuacionAtencion,puntuacionLimpieza,puntuacionCuartos,puntuacionComodidad) {
                    var estructuraCalificacion="";
                //Estructura caritas.
                if (puntuacionUbicacion==0){
                    estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Location</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'
                }
                else if (puntuacionUbicacion==1) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Location</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionUbicacion==2) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Location</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionUbicacion==3) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Location</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionUbicacion==4) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Location</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionUbicacion==5) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Location</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'};
                if (puntuacionAtencion==0){
                    estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Service</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'
                }
                else if (puntuacionAtencion==1) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Service</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionAtencion==2) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Service</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionAtencion==3) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Service</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionAtencion==4) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Service</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionAtencion==5) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Service</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'};
                if (puntuacionLimpieza==0){
                    estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Clearness</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'
                }
                else if (puntuacionLimpieza==1) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Clearness</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionLimpieza==2) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Clearness</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionLimpieza==3) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Clearness</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionLimpieza==4) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Clearness</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionLimpieza==5) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Clearness</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'};
                if (puntuacionCuartos==0){
                    estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Rooms</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'
                }
                else if (puntuacionCuartos==1) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Rooms</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionCuartos==2) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Rooms</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionCuartos==3) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Rooms</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionCuartos==4) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Rooms</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionCuartos==5) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Rooms</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'};
                if (puntuacionComodidad==0){
                    estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Comfort</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'
                }
                else if (puntuacionComodidad==1) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Comfort</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionComodidad==2) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Comfort</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionComodidad==3) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Comfort</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionComodidad==4) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Comfort</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o text-gray"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'}
                else if (puntuacionComodidad==5) { 
                estructuraCalificacion += '<li>'+
                                        '<div class="booking-item-raiting-list-title">Comfort</div>'+
                                        '<ul class="icon-group booking-item-rating-stars">'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                            '<li><i class="fa fa-smile-o"></i>'+
                                            '</li>'+
                                        '</ul>'+
                                    '</li>'};
                return estructuraCalificacion;
            }     
                

                
