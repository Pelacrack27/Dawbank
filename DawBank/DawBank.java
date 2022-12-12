import java.util.*; //Importamos el java.util
import java.util.regex.Matcher; //Importamos el matcher
import java.util.regex.Pattern; //Importamos el pattern

public class DawBank {

	public static void main(String[] args) {
		Scanner entrada = new Scanner (System.in);
		
		//Creamos las variables

		System.out.println("**DAWBANK**");
		System.out.println("Este es el menu principal");
		String opcion = "";
		String titular;
		String iban;
		String fecha;
		
		//Pedimos el iban y al titular para crear una cuenta
		//ES2549378592758340782954 , para pruebas
		
		System.out.println("Antes de empezar, cree una cuenta porfavor");
		
		//Se inserta el iban y se comprueba el formato
		
		do {
			System.out.println("Inserte el IBAN:");
			iban = entrada.nextLine();
			if (!compruebaIBAN(iban)) {
				System.out.println("Formato incorrecto, vuelva a intentarlo");
			} //if
		} while (!compruebaIBAN(iban)); //do-while
		
		//Se inserta el titular y se comprueba que no es nulo
		
		do {
			System.out.println("Inserte el titular:");
			titular = entrada.nextLine();
			if (titular == null) {
				System.out.println("Inserte un titular porfavor");
			} //if
		} while (titular == null); //do-while
		
		//Creamos la cuenta
		
		CuentaBancaria cuenta = new CuentaBancaria(iban, titular);
		
		//Ejecutamos el menu
		
		do {
			
			menu.muestraMenu();
			
			//Pedimos la opcion del usuario
			
			opcion = entrada.nextLine();
			
			//Convertimos a mayusculas la opcion del usuario
			
			opcion = opcion.toUpperCase();
			
			//Switch
			
			switch (opcion) {
			case "M":
				cuenta.muestraCuenta(); //Muestra la cuenta
				espera();
				break;
			case "B":
				cuenta.muestraIban(); //Muestra el iban
				espera();
				break;
			case "T":
				cuenta.muestraTitular(); //Muestra el titular
				espera();
				break;
			case "D":
				cuenta.muestraSaldo(); //Muestra el saldo
				espera();
				break;
			case "I": 
				String ingreso;
				do {
					System.out.println("Inserte la cantidad a ingresar:");
					ingreso = entrada.nextLine();
					if (!cuenta.compruebaMovimiento(ingreso)) {
						System.out.println("Formato incorrecto, vuelva a intentarlo");
					} //if
				} while (!cuenta.compruebaMovimiento(ingreso)); //do-while
				do {
					System.out.println("Inserte la fecha del ingreso (formato dd/mm/aaaa):");
					fecha = entrada.nextLine();
					if (!cuenta.compruebaFecha(fecha)) {
						System.out.println("Formato incorrecto, vuelva a intentarlo");
					}
					else {
						cuenta.ingreso(ingreso, fecha); //Hace el ingreso
					}
				} while (!cuenta.compruebaFecha(fecha)); //do-while
				espera();
				break;
			case "R":
				String retirada;
				do {
					System.out.println("Inserte la cantidad a retirar:");
					retirada = entrada.nextLine();
					if (!cuenta.compruebaMovimiento(retirada)) {
						System.out.println("Formato incorrecto, vuelva a intentarlo");
					} //if
				} while (!cuenta.compruebaMovimiento(retirada)); //do-while
				do {
					System.out.println("Inserte la fecha de la retirada (formato dd/mm/aaaa):");
					fecha = entrada.nextLine();
					if (!cuenta.compruebaFecha(fecha)) {
						System.out.println("Formato incorrecto, vuelva a intentarlo");
					} //if
					else {
						cuenta.retirada(retirada, fecha); //Hace la retirada
					}
				} while (!cuenta.compruebaFecha(fecha)); //do-while
				espera();
				break;
			case "L":
				cuenta.muestraListaMovimientos(); //Muestra la lista de movimientos
				espera();
				break;
			case "S":
				System.out.println("Saliendo del programa..."); //Sale del programa
				break;
			default:
				System.out.println("Error de entrada, vuelva a intentarlo..."); //Muestra error de entrada
				espera();
			}
				
		} while (opcion.matches("[^S]") || opcion.length() > 1 || opcion.length() == 0);
		
		entrada.close(); //Cierra scanner
	} //Metodo Main


	//Metodo para comprobar el iban
	//No se mete en la clase cuentabancaria, ya que se ejecuta antes de generar un objeto de esa clase
	
	public static boolean compruebaIBAN (String iban) {
		Pattern pat = Pattern.compile("[A-Z]{2}[0-9]{22}");
		Matcher mat = pat.matcher(iban);
		return mat.matches();
	} //Metodo
	
	
	//Metodo de espera
	//No se cierra el scanner, se puede usar mas veces
	
	@SuppressWarnings("resource")
	public static void espera() {
		Scanner espera = new Scanner (System.in);
		System.out.println("**************");
		System.out.println("Presione enter");
		espera.next();
	} //Metodo
} //Clase Dawbank
