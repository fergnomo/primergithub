package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import beans.Cliente;

public class ClienteDAOImpl implements ClienteDAO{

	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sentencia;
	
	public ClienteDAOImpl() {
		String url, user, pwd;
		
			url= "jdbc:oracle:thin:@localhost:1521:xe";
			user= "CLIENTE";
			pwd="CLIENTE";
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection(url,user,pwd);
			
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("-----Conexion fallida-----");
				e.printStackTrace();
			}
			System.out.println("-----Conexion establecida cliente-----");

	}
	
	@Override
	public int insert(Cliente cliente) {
		sentencia = "insert into clientes values(?,?,?,?)";
		int i = -1;
		try {
			ps=conn.prepareStatement(sentencia);
			ps.setString(1, cliente.getCif());
			ps.setString(2, cliente.getRazonSocial());
			ps.setDouble(3, cliente.getFacturacionAnual());
			if(cliente.getNumeroEmpleados() == 0)
				ps.setNull(4, Types.INTEGER);
			else
			ps.setInt	(4, cliente.getNumeroEmpleados());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return i;
	}
			
	@Override
	public int update(Cliente cliente) {
		sentencia = "update clientes set razon_social = ?, facturacion_anual = ?, num_empleados = ? where cif = ?";		
		int i = -1;
		try {
			ps =conn.prepareStatement(sentencia);
			
			ps.setString(1, cliente.getRazonSocial());
			ps.setDouble(2, cliente.getFacturacionAnual());
			ps.setInt(3, cliente.getNumeroEmpleados());
			ps.setString(4, cliente.getCif());
			
			i = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		
		return i;
	}

	@Override
	public int delete(String clavepk) {
		sentencia = "delete clientes where cif = ?";
		int i = -1;
		try {
			ps=conn.prepareStatement(sentencia);
			ps.setString(1, clavepk);
			i = ps.executeUpdate();	
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public Cliente findById(String clavepk) {
		Cliente cliente = null;
		sentencia = "select * from clientes where cif = ?";
		try {
			ps=conn.prepareStatement(sentencia);
			ps.setString(1, clavepk);
			rs = ps.executeQuery();
			if(rs.next()) {
				cliente = getCliente();	
			}	
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		List<Cliente> lista = new ArrayList<Cliente>();
		Cliente cliente = null; 
		sentencia = "select * from clientes";
		try {
			ps = conn.prepareStatement(sentencia);
			rs = ps.executeQuery();
			
			while(rs.next()){
				cliente = getCliente();
				lista.add(cliente);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

	@Override
	public List<Cliente> clientesPorFacturacion(double factura) {
		List<Cliente> lista = new ArrayList<Cliente>();
		Cliente cliente = null; 
		sentencia = "select * from clientes where facturacion_anual > ?";
		try {
			ps = conn.prepareStatement(sentencia);
			ps.setDouble(1, factura);
			rs = ps.executeQuery();
			
			while(rs.next()){
				cliente = getCliente();
				lista.add(cliente);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

	private Cliente getCliente() throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setCif(rs.getString(1));
		cliente.setRazonSocial(rs.getString(2));
		cliente.setFacturacionAnual(rs.getDouble(3));
		cliente.setNumeroEmpleados(rs.getInt(4));
		
		return cliente;
	}
	
	public void cerrarConexion() {
		try {
			conn.close();
			System.out.println("=======conexion cerrada cliente========");;
		} catch (SQLException e) {
			System.out.println("=======conexion abierta cliente========");
			e.printStackTrace();
		}
	}

}
