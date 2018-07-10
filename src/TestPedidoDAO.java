import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JOptionPane;

import beans.Cliente;
import beans.Pedido;
import dao.ClienteDAOImpl;
import dao.PedidoDAOImpl;

public class TestPedidoDAO {

	public static void main(String[] args) { 
		PedidoDAOImpl my2dao = new PedidoDAOImpl();
		ClienteDAOImpl cdao = new ClienteDAOImpl();
		System.out.println("==========================================");
		Integer opcion = Integer.parseInt(JOptionPane.showInputDialog("Que quieres hacer: \n Insert= 1 \tPedidos de un cliente= 5 \n "
				+ "Update= 2 \tTodos los pedidos= 6 \n "
				+ "Delete= 3 \tPedidos a partir de un importe= 7 \n"
				+ "Buscar ID Pedido= 4 \t Buscar por fecha= 8 \n"));
		
		switch(opcion){
		
		case(1):
			
			String dia = JOptionPane.showInputDialog("Introduce un Fecha de Alta [FROMATO: DD/MM/YYYY]");
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = new Date();
			try {
				fecha = sdf.parse(dia);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date fechasql = new java.sql.Date(fecha.getTime());
			Integer id = Integer.parseInt(JOptionPane.showInputDialog("Introduce un ID de pedido: "));
			String dscrp = JOptionPane.showInputDialog("Introduce una descripcion: ");
			Double impo = Double.parseDouble(JOptionPane.showInputDialog("Introduce un importe: "));
			Cliente cli = cdao.findById(JOptionPane.showInputDialog("Introduce un CIF [FROMATO: XXXXA]"));
			Pedido ped1 = new Pedido(id, dscrp, impo, cli, fechasql);
			System.out.println("Pedidos insertados: " + my2dao.insert(ped1));;
			break;
			
		case(2):

			String dia2 = JOptionPane.showInputDialog("Introduce un Fecha modificada[FROMATO: DD/MM/YYYY]");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha2 = new Date();
			try {
				fecha2 = sdf2.parse(dia2);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date fechasql2 = new java.sql.Date(fecha2.getTime());
			Integer id2 = Integer.parseInt(JOptionPane.showInputDialog("Introduce un pedido a modificar: "));
			String dscrp2 = JOptionPane.showInputDialog("Introduce una descripcion: ");
			Double impo2 = Double.parseDouble(JOptionPane.showInputDialog("Introduce un importe: "));
			Cliente cli2 = cdao.findById(JOptionPane.showInputDialog("Introduce un CIF [FROMATO: XXXXA]"));
			Pedido ped2 = new Pedido(id2, dscrp2, impo2, cli2, fechasql2);
			System.out.println("Pedidos modificados: " + my2dao.update(ped2));;
			break;
			
		case(3):
			Integer id3 = Integer.parseInt(JOptionPane.showInputDialog("Introduce pedido a eliminar: "));
			System.out.println("Pedidos eliminados: " + my2dao.delete(id3));;
			break;
		case(4):
			Integer id4 = Integer.parseInt(JOptionPane.showInputDialog("Introduce pedido a encontrar: "));
			System.out.println(my2dao.findById(id4));;
			break;
		case(5):
			Cliente cli3 = cdao.findById(JOptionPane.showInputDialog("Dame un CIF de un cliente [FROMATO: XXXXA]"));
			List<Pedido> lista = my2dao.PedidoByCliente(cli3.getCif());
			for (Pedido ele: lista)
				System.out.println(ele);
			break;
		case(6):
			List<Pedido> lista2 = my2dao.findAll();
		for (Pedido ele2: lista2)
			System.out.println(ele2);
			break;
		case(7):
			Double impo3 = Double.parseDouble(JOptionPane.showInputDialog("Introduce un importe: "));
			List<Pedido> lista3 = my2dao.pedidosPorImporte(impo3);
			for (Pedido ele3: lista3)
				System.out.println(ele3);
			break;
		case(8):
			String dia3 = JOptionPane.showInputDialog("Introduce un Fecha de busqueda[FROMATO: DD/MM/YYYY]");
			SimpleDateFormat sdf3 = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha3 = new Date();		
			try {
				fecha3 = sdf3.parse(dia3);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			java.sql.Date fechasql3 = new java.sql.Date(fecha3.getTime());
			List<Pedido> lista4 = my2dao.pedidosHoy(fechasql3);
			for (Pedido ele4: lista4)
				System.out.println(ele4);
			break;
			default: JOptionPane.showMessageDialog(null, "Que no machote, que no.");		
	}
		
		System.out.println("==========================================");		
		my2dao.cerrarConexion();

	}

}
