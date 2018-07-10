package beans;

import java.io.Serializable;
import java.sql.Date;

public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;
		private Integer idPedido;
		private String dscr;
		private Double importe;
		private Cliente cliente;
		private Date fechaAlta;
		
		
		public Date getFechaAlta() {
			return fechaAlta;
		}
		public void setFechaAlta(Date fechaAlta) {
			this.fechaAlta = fechaAlta;
		}
		public Integer getIdPedido() {
			return idPedido;
		}
		public void setIdPedido(Integer idPedido) {
			this.idPedido = idPedido;
		}
		public String getDscr() {
			return dscr;
		}
		public void setDscr(String dscr) {
			this.dscr = dscr;
		}
		public Double getImporte() {
			return importe;
		}
		public void setImporte(Double importe) {
			this.importe = importe;
		}
		public Cliente getCliente() {
			return cliente;
		}
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((idPedido == null) ? 0 : idPedido.hashCode());
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
			Pedido other = (Pedido) obj;
			if (idPedido == null) {
				if (other.idPedido != null)
					return false;
			} else if (!idPedido.equals(other.idPedido))
				return false;
			return true;
		}
		
		@Override
		public String toString() {
			return "Pedido [idPedido=" + idPedido + ", dscr=" + dscr + ", importe=" + importe + ", cliente=" + cliente
					+ ", fechaAlta=" + fechaAlta + "]";
		}

		public Pedido(Integer idPedido, String dscr, Double importe, Cliente cliente, Date fechaAlta) {
			super();
			this.idPedido = idPedido;
			this.dscr = dscr;
			this.importe = importe;
			this.cliente = cliente;
			this.fechaAlta = fechaAlta;
		}
		public Pedido() {
			super();
		}
		
		
}
