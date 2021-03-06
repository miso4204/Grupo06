package grupo6.persistencia.entidades;

import grupo6.modulo.payment.dao.enums.TipoMoneda;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad que representa el producto o paquete turistico, el cual se vende en
 * el marketplace.
 * 
 * @author caespinosam
 * 
 */
@Entity
@SequenceGenerator(name = "seq_producto", initialValue = 1, allocationSize = 100)
public class Producto implements Serializable {

	/**
	 * UID generado.
	 */
	private static final long serialVersionUID = -8658863654377821942L;

	/** Identificador único. **/
	private Long id;
	/** Nombre del paquete producto. Ej: paquete semana santa. **/
	private String nombre;
	/** Lugar. ej: Finca xxx **/
	private String lugar;
	/** Ciudad. Ej: Medellin **/
	private String ciudad;
	/** Precio del paquete en COP. **/
	private double precio;
	/** Fecha inicio en que se debe tomar el paquete. **/
	private Date fechaInicio;
	/** Fecha fin del paquete. Opcional **/
	private Date fechaFin;
	/** Fecha en que se incia la publicacion en el marketplace. **/
	private Date fechaInicioPublicacion;
	/** Fecha en que fianliza la publicacion en el marketplace. **/
	private Date fechaFinPublicacion;
	/** Fecha en que se crea el paquete en el sistema. **/
	private Date fechaCreacion;
	/** Direccion o coordenada usada por google maps. Opcional **/
	private String direccionGoogleMaps;
	/** Las fotos del producto.**/
    private String urlImagen;
    /** El id dle proveedor quien creó producto.*/
    private Long proveedorId;
    /** Texto descriptivo del paquete.*/
    private String descripcionPaquete;
    
    
    /** Las actividades asociadas al paquete turistico.*/
    private List<Actividad> actividades = new ArrayList<Actividad>();
    
    
    /** Tipo de moneda**/
    private TipoMoneda tipoMoneda;
    
    private long idVuelo; // Id del vuelo asociado (opcional)
    private long idAlojamiento; //Id del alojamiento asociado (opcional) 

    
    @Column(name="VUELO", nullable=true)
	public long getIdVuelo() {
		return idVuelo;
	}

	public void setIdVuelo(long idVuelo) {
		this.idVuelo = idVuelo;
	}
	
	@Column(name="ALOJAMIENTO", nullable=true)
	public long getIdAlojamiento() {
		return idAlojamiento;
	}

	public void setIdAlojamiento(long idAlojamiento) {
		this.idAlojamiento = idAlojamiento;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_producto")
	@Column(name="ID")
	public Long getId() {
		return id;
	}

	@Column(nullable = false, length = 100)
	public String getNombre() {
		return nombre;
	}

	@Column(nullable = false, length = 50)
	public String getLugar() {
		return lugar;
	}

	@Column(nullable = false, length = 50)
	public String getCiudad() {
		return ciudad;
	}

	@Column(nullable = false)
	public double getPrecio() {
		return precio;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaInicio() {
		return fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaFin() {
		return fechaFin;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaInicioPublicacion() {
		return fechaInicioPublicacion;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaFinPublicacion() {
		return fechaFinPublicacion;
	}

	@Temporal(TemporalType.DATE)
	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	@Column(length = 100)
	public String getDireccionGoogleMaps() {
		return direccionGoogleMaps;
	}
		
	@Column(length = 500)
	public String  getUrlImagen() {
		return urlImagen;
	}
	
	@Column(length = 1000)
	public String getDescripcionPaquete() {
		return descripcionPaquete;
	}

	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Actividad> getActividades() {
		return actividades;
	}

	@Enumerated(EnumType.STRING)
	public TipoMoneda getTipoMoneda() {
		return tipoMoneda;
	}
	
	public Long getProveedorId() {
		return proveedorId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setFechaInicioPublicacion(Date fechaInicioPublicacion) {
		this.fechaInicioPublicacion = fechaInicioPublicacion;
	}

	public void setFechaFinPublicacion(Date fechaFinPublicacion) {
		this.fechaFinPublicacion = fechaFinPublicacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public void setDireccionGoogleMaps(String direccionGoogleMaps) {
		this.direccionGoogleMaps = direccionGoogleMaps;
	}
	
	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	
	public void setProveedorId(Long proveedorId) {
		this.proveedorId = proveedorId;
	}
	
	public void setDescripcionPaquete(String descripcionPaquete) {
		this.descripcionPaquete = descripcionPaquete;
	}
	
	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}
	

	public void setTipoMoneda(TipoMoneda tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}
	
}
