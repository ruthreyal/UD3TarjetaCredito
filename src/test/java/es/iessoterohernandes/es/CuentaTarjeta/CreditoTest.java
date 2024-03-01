package es.iessoterohernandes.es.CuentaTarjeta;




import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;


public class CreditoTest {

	private static Credito credito;
	private static Cuenta cuenta;
	
	@Before
	public void init() throws Exception {

	       credito = new Credito("1234", "Paco pepe", new Date(), 1000.0);
	       cuenta = new Cuenta("123456","Paco Pepe");
	       credito.setCuenta(cuenta);
	    
	}

	@Test
    public void testRetirar() {
        try {
            credito.retirar(500.0);
            assertEquals(475.0, credito.getCreditoDisponible());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testIngresar() {
        try {
            credito.ingresar(200.0);
            assertEquals(1200.0, credito.getCreditoDisponible());
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }

    @Test
    public void testPagoEnEstablecimiento() {
        try {
            credito.pagoEnEstablecimiento("Establecimiento", 300.0);
            assertEquals(300.0, credito.getSaldo(), 0.01);
        } catch (Exception e) {
            fail("Se lanzó una excepción inesperada: " + e.getMessage());
        }
    }
    
    @Test
    public void testLidiquidar() throws Exception {
    	credito.ingresar(500);
    	credito.pagoEnEstablecimiento("Pago supermercado", 100);
    	credito.liquidar(3, 2024);
    	assertEquals(0.0,credito.getSaldo());
    	
    }
    
   

    
}
