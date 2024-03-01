package es.iessoterohernandes.es.CuentaTarjeta;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DebitoTest {
	
	 private static Debito debito;

	    @BeforeEach
	    public void setUp() {
	        debito = new Debito("123456", "Paco pepe", new Date());
	    }

	    @Test
	    public void testRetirar() throws Exception {
	        double saldoInicial = debito.getSaldo();
	        double cantidadARetirar = 500;
	        debito.retirar(cantidadARetirar);
	        assertEquals(saldoInicial - cantidadARetirar, debito.getSaldo());
	    }

	    @Test
	    public void testIngresar() throws Exception {
	        double saldoInicial = debito.getSaldo();
	        double cantidadAIngresar = 200;
	        debito.ingresar(cantidadAIngresar);
	        assertEquals(saldoInicial + cantidadAIngresar, debito.getSaldo());
	    }

	    @Test
	    public void testPagoEnEstablecimiento() throws Exception {
	        double saldoInicial = debito.getSaldo();
	        double cantidadAPagar = 100;
	        debito.pagoEnEstablecimiento("Tienda", cantidadAPagar);
	        assertEquals(saldoInicial - cantidadAPagar, debito.getSaldo());
	    }

}
