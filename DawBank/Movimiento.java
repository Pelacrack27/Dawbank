
public class Movimiento {

	//Propiedades de un movimiento
	
	private int id;
	private String fecha;
	private String tipo;
	private String cantidad;
	
	//Inserta id
	
	private void setId(int id) {
		this.id = id;
	} //Metodo

	//Inserta fecha
	
	private void setFecha(String fecha) {
		this.fecha = fecha;
	} //Metodo

	//Inserta tipo
	
	private void setTipo(String tipo) {
		this.tipo = tipo;
	} //Metodo

	//Inserta cantidad
	
	private void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	} //Metodo
	
	//Muestra id
	
	private int getId() {
		return id;
	} //Metodo

	//Muestra fecha
	
	private String getFecha() {
		return fecha;
	} //Metodo
	
	//Muestra tipo

	private String getTipo() {
		return tipo;
	} //Metodo

	//Muestra cantidad
	
	private String getCantidad() {
		return cantidad;
	} //Metodo
	
	//Muestra la info del movimiento
	
	public void mostrarInfoMovimiento() {
		System.out.println("Id:" + getId());
		System.out.println("Fecha:" + getFecha());
		System.out.println("Tipo:" + getTipo());
		System.out.println("Cantidad:" + getCantidad());
	} //Metodo
	
	//Constructor personalizado
	
	public Movimiento(int id, String fecha, String tipo, String cantidad) {
		setId(id);
		setFecha(fecha);
		setTipo(tipo);
		setCantidad(cantidad);
	} //Constructor
}
