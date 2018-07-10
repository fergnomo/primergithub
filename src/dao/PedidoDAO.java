package dao;

import java.sql.Date;
import java.util.List;

import beans.Pedido;

public interface PedidoDAO {
	public int insert(Pedido pedido);
	public int update(Pedido pedido);
	public int delete(int clavepk);
	public Pedido findById(int clavepk);
	public List<Pedido> findAll();
	public List<Pedido> PedidoByCliente(String clavepk);
	public List<Pedido> pedidosPorImporte(double importe);	
	public List<Pedido> pedidosHoy(Date date);
	
}
