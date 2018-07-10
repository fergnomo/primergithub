package dao;

import java.util.List;

import beans.Cliente;

public interface ClienteDAO {

	public int insert(Cliente cliente);
	public int update(Cliente cliente);
	public int delete(String clavepk);
	public Cliente findById(String clavepk);
	public List<Cliente> findAll();
	
	public List<Cliente> clientesPorFacturacion(double factura);
	

}
