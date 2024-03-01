package es.iessoterohernandes.es.CuentaTarjeta;

import java.util.Vector;
import java.util.Date;

public class Credito extends Tarjeta
{
	protected double mCredito;
	protected Vector mMovimientos;

	public Credito(String numero, String titular, Date fechaCaducidad, double credito)
	{
		super(numero, titular, fechaCaducidad);
		mCredito=credito;
		mMovimientos=new Vector();
	}
	
	public void retirar(double x) throws Exception 
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Retirada en cajero automatico");
		double comision =(x*0.05<3.0 ? 3 : x*0.05);  // Añadimos una comisi�n de un 5%, manimo de 3 euros.
		m.setImporte(x+comision);
		//se suma el importe que se retira más la comision para se reste del credito disponible
		if (x+comision>getCreditoDisponible()) {
			throw new Exception("Cr�dito insuficiente");
		}else {
			mMovimientos.addElement(m);
		}
			
	}
	
	public void ingresar(double x) throws Exception 
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Ingreso en cuenta asociada (cajero automatico)");
		m.setImporte(x);
		mCredito+=x;
		//se añade la variable más el ingreso para que se sume al credito disponible
		/*mMovimientos.addElement(m);*/
		mCuentaAsociada.ingresar(x);
	}
	
	public void pagoEnEstablecimiento(String datos, double x) throws Exception 
	{
		Movimiento m=new Movimiento();
		m.setConcepto("Compra a credito en: " + datos);
		m.setImporte(x);
		mMovimientos.addElement(m);
	}
	
	public double getSaldo() 
	{
		double r=0.0;
		for (int i=0; i<this.mMovimientos.size(); i++) 
		{
			Movimiento m=(Movimiento) mMovimientos.elementAt(i);
			r+=m.getImporte();
		}
		return r;
	}
	
	public double getCreditoDisponible() 
	{
		return mCredito-getSaldo();
	}
	
	public void liquidar(int mes, int anio) 
	{
		Movimiento liq=new Movimiento();
		liq.setConcepto("Liquidacion de operaciones tarj. cr�dito, " + (mes+1) + " de " + (anio+1900));
		double r=0.0;
		for (int i=0; i<this.mMovimientos.size(); i++) 
		{
			Movimiento m=(Movimiento) mMovimientos.elementAt(i);
			if (m.getFecha().getMonth()+1==mes && m.getFecha().getYear()+1900==anio)
				r+=m.getImporte();
		}
		liq.setImporte(-r);
		if (r!=0) {
			this.mMovimientos.addElement(liq);
			mCuentaAsociada.addMovimiento(liq);
		}
			
			
	}
}