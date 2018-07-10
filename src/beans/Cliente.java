package beans;

import java.io.Serializable;

public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String cif, razonSocial;
	private double facturacionAnual;
	private int numeroEmpleados;
	
	public Cliente() {
		super();
	}
	public Cliente(String cif, String razonSocial, double facturacionAnual, int numeroEmpleados) {
		super();
		this.cif = cif;
		this.razonSocial = razonSocial;
		this.facturacionAnual = facturacionAnual;
		this.numeroEmpleados = numeroEmpleados;
	}
	public String getCif() {
		return cif;
	}
	public void setCif(String cif) {
		this.cif = cif;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public double getFacturacionAnual() {
		return facturacionAnual;
	}
	public void setFacturacionAnual(double facturacionAnual) {
		this.facturacionAnual = facturacionAnual;
	}
	public int getNumeroEmpleados() {
		return numeroEmpleados;
	}
	public void setNumeroEmpleados(int numeroEmpleados) {
		this.numeroEmpleados = numeroEmpleados;
	}
	@Override
	public String toString() {
		return "Cliente [cif=" + cif + ", razonSocial=" + razonSocial + ", facturacionAnual=" + facturacionAnual
				+ ", numeroEmpleados=" + numeroEmpleados + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		return true;
	}
	
	public double ratioFacturacion() {
		return facturacionAnual/numeroEmpleados;
	}
	
}
