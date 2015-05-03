package grupo6.web.dto;

/**
 * Respuesta generica de los servicios REST.
 * 
 * @author caespinosam
 *
 */
public class ResponseDTO {

	/** Posibles códigos de error */
	public static final String CODIGO_RESPUESTA_OK = "OK";
	public static final String CODIGO_RESPUESTA_PARAMETRO_ILEGAL = "ILLEGAL_ARGUMENT";
	public static final String CODIGO_RESPUESTA_ERROR_GENERICO = "ERROR_UNKNOWN";
	public static final String CODIGO_RESPUESTA_NOT_ACTIVE = "NOT_ACTIVE";

	/** Codigo de respuesta: OK, ERROR*, etc */
	private String codigoRespuesta;
	/** Mensaje descriptivo del código de error. Ej: usuario ya existe. */
	private String mensaje;
	/** Respuesta del método invocado. */
	private Object respuesta;

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public Object getRespuesta() {
		return respuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public void setRespuesta(Object respuesta) {
		this.respuesta = respuesta;
	}

}
