import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CuentaBancaria {
	
	//Propiedades de la cuenta bancaria
	
	private String iban;
	private String titular;
	private double saldo = 0.0;
	
	private static double descubiertoMaximo = -50.0;
	
	private Movimiento historico[] = new Movimiento [100];
	private int numMovimiento = 0;
	
	
	//Obtiene el iban
	
	private void obtenIban(String iban) {
		this.iban = iban;
	}
	
	//Obtiene el titular
	
	private void obtenTitular(String titular) {
		this.titular = titular;
	}
	
	//Muestra el iban
	
	public void muestraIban() {
		System.out.println("El iban es " + iban);
	}
	
	//Muestra el titular
	
	public void muestraTitular() {
		System.out.println("El titular es " + titular);
	}
	
	//Muestra el saldo
	
	public void muestraSaldo() {
		System.out.println("El saldo es " + saldo);
	}
	
	//Muestra la cuenta
	
	public void muestraCuenta() {
		System.out.println("El iban es " + iban);
		System.out.println("El titular es " + titular);
		System.out.println("El saldo es " + saldo);
	} //Metodo
	
	//Ingresa una cantidad
	
	public void ingreso(String ingreso, String fecha) {
		do {
			if (compruebaMovimiento(ingreso)) {
				historico[numMovimiento] = new Movimiento(numMovimiento + 1, fecha, "Ingreso", ingreso);
				this.saldo += Double.parseDouble(ingreso);
				notificaHacienda(Double.parseDouble(ingreso));
				numMovimiento++;
			}
			else {
				System.out.println("Es un texto, no un numero, vuelca a intentarlo");
			}
		} while (!compruebaMovimiento(ingreso));
	} //Metodo
	
	//Metodo para retirar efectivo
	
	public void retirada(String retirada, String fecha) {
		do {
			if (compruebaMovimiento(retirada)) {
				if (numMovimiento == 100) {
					retrasaHistorico();
					numMovimiento--;
				} //if
				historico[numMovimiento] = new Movimiento(numMovimiento + 1, fecha, "Retirada", retirada);
				if (compruebaSaldo(Double.parseDouble(retirada)) == true) {
					this.saldo -= Double.parseDouble(retirada);
					if (this.saldo < 0.0 ) {
						System.out.println("AVISO: Saldo negativo");
						notificaHacienda(Double.parseDouble(retirada));
						numMovimiento++;
					} //if
				} //if
				else {
					System.out.println("Te vas a quedar en un decubierto mayor al permitido");
				} //else
			}
			else {
				System.out.println("Es un texto, no un numero, vuelca a intentarlo");
			}
		} while (!compruebaMovimiento(retirada));
	} //Metodo
	
	//Metodo para mostrar la lista de movimientos
	
	public void muestraListaMovimientos() {
		for (int i = 0; i < historico.length; i++) {
			if (historico[i] != null) {
				System.out.println("**************");
				historico[i].mostrarInfoMovimiento();
			} //if
		} //for
		if (numMovimiento == 0) {
			System.out.println("No hay movimientos en esta cuenta");
		} //if
	} //Metodo
	
	//Metodo para comprobar el saldo
	
	private boolean compruebaSaldo(double retirada) {
		boolean comprobacion = true;
		if (this.saldo - retirada < descubiertoMaximo) {
			comprobacion = false;
		} //if
		return comprobacion;
	} //Metodo
	
	//Metodo para notificar a la hacienda
	
	private void notificaHacienda(double cantidad) {
		if (cantidad >= 3000) {
			System.out.println("AVISO: Notificar a hacienda");
		} //if
	} //Metodo
	
	private void retrasaHistorico() {
		for (int i = 0; i < historico.length; i++) {
			if (i + 1 != historico.length) {
				historico[i] = historico[i + 1];
			} //if
		} //for
	} //Metodo
	
	//Constructor personalizado
	
	CuentaBancaria(String iban, String titular) {
		obtenIban(iban);
		obtenTitular(titular);
	} //Constructor
	
	
	//Comprobar 
	//Se usa el pattern, para comprobar que es un numero, no un texto
	
	public boolean compruebaMovimiento (String cantidad) {
		Pattern pat = Pattern.compile("[0-9]*"); //da igaul la longitud, si contiene solo numeros esta bien
		Matcher mat = pat.matcher(cantidad);
		return mat.matches();
	}
	
	//Comprobar fecha de un movmiento
	//Se usa el metodo parse de la clase Integer, ya que el pattern no vale
	//Con el pattern, alguien podría meter dia 39 mes 19 y el programa lo daria por bueno
		
	public boolean compruebaFecha (String fecha) {
		boolean fechaCorrecta = false;
		int meses31Dias [] = {1, 3, 5, 7, 8, 10, 12}; //Meses de 31 dias
		int meses30Dias [] = {4, 6, 9, 11}; //Meses de 30 dias
		if (fecha.length() != 10) {
			fechaCorrecta = false;
		} //if
		else if (fecha.indexOf("/") != 2 && fecha.lastIndexOf("/") != 5) {
			fechaCorrecta = false;
		} //else if
		else {
			//Para los meses de 31 dias
			if (Arrays.binarySearch(meses31Dias, Integer.parseInt(fecha.substring(3, 5))) != -1) {
				if (Integer.parseInt(fecha.substring(0, 2)) > 0 && Integer.parseInt(fecha.substring(0, 2)) <= 31) {
					if (Integer.parseInt(fecha.substring(6, fecha.length())) >= 2022) {
					fechaCorrecta = true;
					} //if
				} //if
			} //if
			//Para los meses de 30 dias
			else if (Arrays.binarySearch(meses30Dias, Integer.parseInt(fecha.substring(3, 5))) != -1) {
				if (Integer.parseInt(fecha.substring(0, 2)) > 0 && Integer.parseInt(fecha.substring(0, 2)) <= 30) {
					if (Integer.parseInt(fecha.substring(6, fecha.length())) >= 2022) {
					fechaCorrecta = true;
					} //if
				} //if
			} //if
			//Para el mes de febrero, no se mete sistema de años bisiestos
			else if (Integer.parseInt(fecha.substring(0, 2)) > 0 && Integer.parseInt(fecha.substring(0, 2)) <= 28) {
					if (Integer.parseInt(fecha.substring(6, fecha.length())) >= 2022) {
					fechaCorrecta = true;
					} //if
				} //if
			} //if
		return fechaCorrecta;
	} //Metodo
	
	
	

} //Clase
