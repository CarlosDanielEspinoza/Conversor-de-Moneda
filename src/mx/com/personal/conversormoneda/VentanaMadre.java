package mx.com.personal.conversormoneda;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class VentanaMadre {
	final private String[] opciones_conversion = {"Conversión de Moneda"};
	final private String[] opciones_divisas = {"Peso Mexicano a Dolar", "Dolar a Peso Mexicano"};
	final private String titulo = "Conversor";
	
	private Boolean validarCantidad(String cantidad) {
		Boolean cantidad_validada;
		try {
			Double.parseDouble(cantidad);
			cantidad_validada = true;
		}catch(NumberFormatException exception) {
			cantidad_validada = false;
		}
		return cantidad_validada;
	}
	
	public void start() {
		
		Object options = JOptionPane.showInputDialog(null, "Elije una opción de conversión: ", titulo, 1, null,opciones_conversion, opciones_conversion[0]);
			
		if(options == null) {
			mensajeAlerta("Programa Finalizado"); //Finalizar programa por Cancelar	
		}else {
			cantidad();	
		}
	}
	
	private void cantidad() {
		while(true) {
			double cantidad;
			String cantidad_previa;
			
			Object options = JOptionPane.showInputDialog(null, "Introduce la cantidad: ", titulo, 0);
			
			//Verificar que el valor de options no sea Null
			if(options != null) {
				cantidad_previa = options.toString();
			}else {
				mensajeAlerta("Programa Finalizado"); //Finalizar programa por Cancelar
				break;
			}
			
			//Validar que el valor de options sea un numero
			if(validarCantidad(cantidad_previa)) {
				cantidad = Double.parseDouble(cantidad_previa);;
			}else {
				mensajeAlerta("Cantidad no Valida: Intente de nuevo.");
				continue;
			}
			
			if(!conversionDivisa(cantidad)) break;
			if(continuar()) break;
		}
		
	}
	
	private Boolean conversionDivisa(double cantidad) {
		//Manipulacion de divisa y resultado
		DecimalFormat df = new DecimalFormat("#.00");
		
		Object options = JOptionPane.showInputDialog(null, "Elige una opción: ", titulo, 0, null, opciones_divisas, opciones_divisas[0]);
		
		if(options == null) {
			mensajeAlerta("Programa Finalizado"); //Finalizar programa por Cancelar
			return false;
		}
		
		
		if(options.toString() == opciones_divisas[0]) {
			resultado(df.format(cantidad * 0.058),df.format(cantidad));
		}else if(options.toString() == opciones_divisas[1]) {
			resultado(df.format(cantidad * 17.2), df.format(cantidad));
		}
		
		return true;
	}
	
	
	
	private void resultado(String resultado, String cantidad) {
		JOptionPane.showMessageDialog(null, "Resultado: " + cantidad + " = "+ resultado);
	}
	
	private Boolean continuar() {
		Object opciones = JOptionPane.showConfirmDialog(null, "¿Desea continuar?","Continuar?", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
		String valor_opciones = opciones.toString();
		if(valor_opciones.equals("1")) {
			mensajeAlerta("Programa Finalizado");
			return true;	
		}
		return false;
	}
	
	private void mensajeAlerta(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
}
