import java.util.List;
import beans.Cliente;
import dao.ClienteDAOImpl;

public class TestClienteDAO {

	public static void main(String[] args) {
		ClienteDAOImpl mydao = new ClienteDAOImpl();
		
		//Cliente cli1 = new Cliente("4444A", "Mi Casa", 1500000, 0);
		
		//System.out.println("resultado: " + mydao.insert(cli1));
		
		//System.out.println(mydao.findById("1111A"));
		//System.out.println(mydao.delete("4444A"));
		/*
		System.out.println("==========================================");
		List<Cliente> lista = mydao.findAll();
		for (Cliente ele: lista)
			System.out.println(ele);
		*/
		
		Cliente cli2 = new Cliente("1111A", "Adios", 1789600, 32);
		System.out.println(	mydao.update(cli2));
		
	}

}
