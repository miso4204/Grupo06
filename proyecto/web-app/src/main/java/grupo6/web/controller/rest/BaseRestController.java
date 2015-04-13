package grupo6.web.controller.rest;

import grupo6.web.dto.ResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controlador REST base con métodos comunes.  
 * @author caespinosam
 *
 */
public class BaseRestController {
	
	
	/**
	 * Manejador de argumento ilegal por parte del cliente.
	 * Devuelve error por parámeto incorrecto y controlado en el backend.
	 * @param ex la excepción.
	 * @return la respuesta de error
	 */
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<ResponseDTO> handleIllegalArgumentException(IllegalArgumentException ex) {
		
		ResponseDTO r = new ResponseDTO();
		r.setCodigoRespuesta(ResponseDTO.CODIGO_RESPUESTA_PARAMETRO_ILEGAL);
		r.setMensaje(ex.getMessage());	
		
		return new ResponseEntity<ResponseDTO>(r, HttpStatus.OK); 
	}

	/**
	 * Manejador de cualquier excepción general.
	 * Devuelve un error no controlado en el backend.
	 * @param ex la excepción.
	 * @return la respuesta de error
	 */
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<ResponseDTO> handleException(Exception ex) {
		
		ex.printStackTrace();
		ResponseDTO r = new ResponseDTO();
		r.setCodigoRespuesta(ResponseDTO.CODIGO_RESPUESTA_ERROR_GENERICO);
		r.setMensaje(ex.getMessage());	
		return new ResponseEntity<ResponseDTO>(r, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	
	/**
	 * Devuelve una respuesta exitosa OK, la cual contiene como cuerpo el resultado del método.
	 * @param mensaje El mensaje descriptivo de la operación: Usuario creado.
	 * @param cuerpoRespuesta el objeto que representa la respeusta del método: booelans, listas, etc
	 * @return mensaje de exito OK. 
	 */
	public ResponseEntity<ResponseDTO> devolverRespuestaExitosa(String mensaje, Object cuerpoRespuesta) {
		ResponseDTO r = new ResponseDTO();
		r.setCodigoRespuesta(ResponseDTO.CODIGO_RESPUESTA_OK);
		r.setMensaje(mensaje);
		r.setRespuesta(cuerpoRespuesta);	
		return new ResponseEntity<ResponseDTO>(r, HttpStatus.OK);
	}
		
	

}
