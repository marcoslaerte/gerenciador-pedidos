package me.marcos.gerenciador_pedidos;

import me.marcos.gerenciador_pedidos.principal.Principal;
import me.marcos.gerenciador_pedidos.repository.CategoriaRepository;
import me.marcos.gerenciador_pedidos.repository.FornecedorRepository;
import me.marcos.gerenciador_pedidos.repository.PedidoRepository;
import me.marcos.gerenciador_pedidos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GerenciadorPedidosApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private FornecedorRepository fornecedorRepository;

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorPedidosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(categoriaRepository, produtoRepository, pedidoRepository, fornecedorRepository);
		principal.exibeMenu();
	}
}
