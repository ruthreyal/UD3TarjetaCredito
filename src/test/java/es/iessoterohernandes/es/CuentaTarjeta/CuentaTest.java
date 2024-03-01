package es.iessoterohernandes.es.CuentaTarjeta;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaTest {

	private static Cuenta cuenta;
	
	@BeforeEach
	public void init() throws Exception {
		cuenta = new Cuenta("123456", "Paco pepe");
	}

	@Test
    public void testIngresar() throws Exception {
        cuenta.ingresar(100.0);
        assertEquals(100.0, cuenta.getSaldo());
    }

    @Test
    public void testIngresarCantidadNegativa() throws Exception {
        cuenta.ingresar(-100.0);
    }

    @Test
    public void testRetirar() throws Exception {
        cuenta.ingresar(200.0);
        cuenta.retirar(100.0);
        assertEquals(100.0, cuenta.getSaldo());
    }

    @Test
    public void testRetirarCantidadNegativa() throws Exception {
        cuenta.retirar(-100.0);
    }

    @Test
    public void testRetirarSaldoInsuficiente() throws Exception {
        cuenta.retirar(100.0);
    }

    @Test
    public void testIngresarConcepto() throws Exception {
        cuenta.ingresar("Ingreso efectivo", 500.0);
        assertEquals(500.0, cuenta.getSaldo());
    }

    @Test
    public void testRetirarConcepto() throws Exception {
        cuenta.ingresar(1000.0);
        cuenta.retirar("Retirada cajero", 200.0);
        assertEquals(800.0, cuenta.getSaldo());
    }

    @Test
    public void testRetirarConceptoSaldoInsuficiente() throws Exception {
        cuenta.retirar("Retirada cajero", 200.0);
    }

}
