package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import beans.Cliente;
import beans.Pedido;

public class PedidoDAOImpl implements PedidoDAO {
	
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sentencia;
	
	public PedidoDAOImpl() {
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
			System.out.println("-----Conexion establecida pedido-----");

	}
	 
	@Override
	public int insert(Pedido pedido) {
		sentencia = "insert into pedidos values(?,?,?,?,?)";
		int i = -1;
		try {
			ps=conn.prepareStatement(sentencia);
			ps.setInt(1, pedido.getIdPedido());
			ps.setString(2, pedido.getDscr());
			ps.setDouble(3, pedido.getImporte());
			ps.setDate(5, pedido.getFechaAlta());
			ps.setString(4, pedido.getCliente().getCif());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int update(Pedido pedido) {
		sentencia = "update pedidos set descripcion = ?, importe = ?, cif = ?, fecha_alta = ? where id_pedido = ?";		
		int i = -1;
		try {
			ps =conn.prepareStatement(sentencia);
			
			ps.setString(1, pedido.getDscr());
			ps.setDouble(2, pedido.getImporte());
			ps.setString(3, pedido.getCliente().getCif());
			ps.setDate(4, pedido.getFechaAlta());
			ps.setInt(5, pedido.getIdPedido());
			
			i = ps.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return i;
	}

	@Override
	public int delete(int clavepk) {
		sentencia = "delete pedidos where id_pedido = ?";
		int i = -1;
		try {
			ps=conn.prepareStatement(sentencia);
			ps.setInt(1, clavepk);
			i = ps.executeUpdate();	
		
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return i;
	}

	@Override
	public Pedido findById(int clavepk) {
		Pedido pedido = null;
		sentencia = "SELECT P.ID_PEDIDO, P.DESCRIPCION, P.IMPORTE, P.CIF, C.RAZON_SOCIAL," + 
				"        C.FACTURACION_ANUAL, C.NUM_EMPLEADOS, P.FECHA_ALTA" + 
				"        FROM CLIENTES C JOIN PEDIDOS P ON P.CIF = C.CIF WHERE ID_PEDIDO = ?";
		try {
			ps=conn.prepareStatement(sentencia);
			ps.setInt(1, clavepk);
			rs = ps.executeQuery();
			if(rs.next()) {
				pedido = getPedido();	
			}	
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return pedido;
	}

	@Override
	public List<Pedido> findAll() {
		List<Pedido> lista = new ArrayList<Pedido>();
		Pedido pedido = null; 
		sentencia = "SELECT P.ID_PEDIDO, P.DESCRIPCION, P.IMPORTE, P.CIF, C.RAZON_SOCIAL," + 
				"        C.FACTURACION_ANUAL, C.NUM_EMPLEADOS, P.FECHA_ALTA" + 
				"        FROM CLIENTES C JOIN PEDIDOS P ON P.CIF = C.CIF";
		try {
			ps = conn.prepareStatement(sentencia);
			rs = ps.executeQuery();
			
			while(rs.next()){
				pedido = getPedido();
				lista.add(pedido);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Pedido> pedidosPorImporte(double importe) {
		List<Pedido> lista = new ArrayList<Pedido>();
		Pedido pedido = null; 
		sentencia = "SELECT P.ID_PEDIDO, P.DESCRIPCION, P.IMPORTE, P.CIF, C.RAZON_SOCIAL, "
				+ "C.FACTURACION_ANUAL, C.NUM_EMPLEADOS, P.FECHA_ALTA FROM CLIENTES C JOIN PEDIDOS P ON P.CIF = C.CIF WHERE IMPORTE > ?";
		try {
			ps = conn.prepareStatement(sentencia);
			ps.setDouble(1, importe);
			rs = ps.executeQuery();
			
			while(rs.next()){
				pedido = getPedido();
				lista.add(pedido);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Pedido> PedidoByCliente(String clavepk) {
		List<Pedido> lista = new ArrayList<Pedido>();
		Pedido pedido = null; 
		sentencia = "SELECT P.ID_PEDIDO, P.DESCRIPCION, P.IMPORTE, P.CIF, C.RAZON_SOCIAL, "
				+ "C.FACTURACION_ANUAL, C.NUM_EMPLEADOS, P.FECHA_ALTA FROM CLIENTES C JOIN PEDIDOS P ON P.CIF = C.CIF WHERE P.CIF = ?";
		try {
			ps = conn.prepareStatement(sentencia);
			ps.setString(1, clavepk);
			rs = ps.executeQuery();
			
			while(rs.next()){
				pedido = getPedido();
				lista.add(pedido);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	@Override
	public List<Pedido> pedidosHoy(Date date) {
		List<Pedido> lista = new ArrayList<Pedido>();
		Pedido pedido = null; 
		sentencia = "SELECT P.ID_PEDIDO, P.DESCRIPCION, P.IMPORTE, P.CIF, C.RAZON_SOCIAL, "
				+ "C.FACTURACION_ANUAL, C.NUM_EMPLEADOS, P.FECHA_ALTA FROM CLIENTES C JOIN PEDIDOS P ON P.CIF = C.CIF WHERE P.FECHA_ALTA = ?";
		try {
			ps = conn.prepareStatement(sentencia);
			ps.setDate(1, date);
			rs = ps.executeQuery();
			
			while(rs.next()){
				pedido = getPedido();
				lista.add(pedido);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	private Pedido getPedido() throws SQLException {
		Pedido pedido = new Pedido();
		Cliente cliente = new Cliente();
		cliente.setCif(rs.getString(4));
		cliente.setRazonSocial(rs.getString(5));
		cliente.setFacturacionAnual(rs.getDouble(6));
		cliente.setNumeroEmpleados(rs.getInt(7));
		pedido.setIdPedido(rs.getInt(1));
		pedido.setDscr(rs.getString(2));
		pedido.setImporte(rs.getDouble(3));
		pedido.setCliente(cliente);
		
		pedido.setFechaAlta(rs.getDate(8));
		return pedido;
}
	public void cerrarConexion() {
		try {
			conn.close();
			System.out.println("=======conexion cerrada pedido========");
		} catch (SQLException e) {
			System.out.println("=======conexion abierta pedido========");
			e.printStackTrace();
		}
	}

}
