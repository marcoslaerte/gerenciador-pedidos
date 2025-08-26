package me.marcos.gerenciador_pedidos.principal;

import me.marcos.gerenciador_pedidos.model.Categoria;
import me.marcos.gerenciador_pedidos.model.Fornecedor;
import me.marcos.gerenciador_pedidos.model.Pedido;
import me.marcos.gerenciador_pedidos.model.Produto;
import me.marcos.gerenciador_pedidos.repository.CategoriaRepository;
import me.marcos.gerenciador_pedidos.repository.FornecedorRepository;
import me.marcos.gerenciador_pedidos.repository.PedidoRepository;
import me.marcos.gerenciador_pedidos.repository.ProdutoRepository;

import java.time.LocalDate;
import java.util.List;

public class Principal {

    private CategoriaRepository categoriaRepository;
    private ProdutoRepository produtoRepository;
    private PedidoRepository pedidoRepository;
    private FornecedorRepository fornecedorRepository;

    public Principal(
            CategoriaRepository categoriaRepository,
            ProdutoRepository produtoRepository,
            PedidoRepository pedidoRepository,
            FornecedorRepository fornecedorRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.fornecedorRepository = fornecedorRepository;
    }

    public void exibeMenu() {
//        Produto produto = new Produto("Notebook", 3500.0);
//        Categoria categoria = new Categoria(1L, "Eletrônicos");
//        Pedido pedido = new Pedido(1L, LocalDate.now());
//
//        produtoRepository.save(produto);
//        categoriaRepository.save(categoria);
//        pedidoRepository.save(pedido);

        // Criando categorias
//        Categoria categoriaEletronicos = new Categoria(null, "Eletrônicos");
//        Categoria categoriaLivros = new Categoria(null, "Livros");

        Categoria categoriaEletronicos = new Categoria(1L, "Eletrônicos");
        Categoria categoriaLivros = new Categoria(2L, "Livros");
        categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

        // Criando fornecedores
        Fornecedor fornecedorTech = new Fornecedor("Tech Supplier");
        Fornecedor fornecedorLivros = new Fornecedor("Livraria Global");
        fornecedorRepository.saveAll(List.of(fornecedorTech, fornecedorLivros));

        // Criando produtos e associando às categorias
        Produto produto1 = new Produto("Notebook", 3500.0, categoriaEletronicos, fornecedorTech);
        Produto produto2 = new Produto("Smartphone", 2500.0, categoriaEletronicos, fornecedorTech);
        Produto produto3 = new Produto("Livro de Java", 100.0, categoriaLivros, fornecedorLivros);
        Produto produto4 = new Produto("Livro de Spring Boot", 150.0, categoriaLivros, fornecedorLivros);
        produtoRepository.saveAll(List.of(produto1, produto2, produto3));

        // Associando produtos às categorias
//        categoriaEletronicos.setProdutos(List.of(produto1, produto2));
//        categoriaLivros.setProdutos(List.of(produto3, produto4));

        // Salvando categorias (cascateia produtos automaticamente, se configurado)
//        categoriaRepository.saveAll(List.of(categoriaEletronicos, categoriaLivros));

        // Criando pedidos e associando produtos
        Pedido pedido1 = new Pedido(1L, LocalDate.now(), LocalDate.now().plusMonths(1));
        pedido1.setProdutos(List.of(produto1, produto3));
        Pedido pedido2 = new Pedido(2L, LocalDate.now().minusDays(1), LocalDate.now().plusMonths(2));
        pedido2.setProdutos(List.of(produto2));
        pedidoRepository.saveAll(List.of(pedido1, pedido2));

        // Testando a persistência e o relacionamento
//        System.out.println("Categorias e seus produtos:");
//        categoriaRepository.findAll().forEach(categoria -> {
//            System.out.println("Categoria: " + categoria.getNome());
//            categoria.getProdutos().forEach(produto ->
//                    System.out.println(" - Produto: " + produto.getNome())
//            );
//        });

        // Testando consultas e verificando os relacionamentos
        System.out.println("Produtos na categoria Eletrônicos:");
        categoriaRepository.findById(1L).ifPresent(categoria ->
                categoria.getProdutos().forEach(produto ->
                        System.out.println(" - " + produto.getNome())
                )
        );

        System.out.println("\nPedidos e seus produtos:");
        pedidoRepository.findAll().forEach(pedido -> {
            System.out.println("Pedido " + pedido.getId() + ":");
            pedido.getProdutos().forEach(produto ->
                    System.out.println(" - " + produto.getNome())
            );
        });

        System.out.println("\nProdutos e seus fornecedores:");
        produtoRepository.findAll().forEach(produto ->
                System.out.println("Produto: " + produto.getNome() +
                        ", Fornecedor: " + produto.getFornecedor().getNome())
        );

    }
}
