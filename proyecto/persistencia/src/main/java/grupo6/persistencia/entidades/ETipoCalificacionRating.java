package grupo6.persistencia.entidades;

/**
 * Los tipos de calificacion en un rating.
 * 
 * @author caespinosam
 *
 */
public enum ETipoCalificacionRating {
	
	EXECELENTE(5),
	MUYBUENO (4),
	REGULAR (3),
	MALO (2),
	PESIMO (1);
	
	/** El puntaje asociado a la calificacion.*/
	private int puntaje;
	
	/**
	 * Constructor.
	 * @param puntaje el puntaje  asociado.
	 */
	ETipoCalificacionRating (int puntaje) {
		this.puntaje = puntaje;
	}
	
	
	/**
	 * Retorna el puntaje asociado.
	 * @return el puntaje asociado.
	 */
	public int getPuntaje() {
		return puntaje;
	}
	
}
