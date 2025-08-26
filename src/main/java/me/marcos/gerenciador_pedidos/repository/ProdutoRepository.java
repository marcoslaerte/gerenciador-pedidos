package me.marcos.gerenciador_pedidos.repository;

import me.marcos.gerenciador_pedidos.model.Categoria;
import me.marcos.gerenciador_pedidos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findByNome(String nome);

    public List<Produto> findByCategoriaNome(String categoriaNome);

    public List<Produto> findByPrecoGreaterThan(Double preco);

    public List<Produto> findByPrecoLessThan(Double preco);

    public List<Produto> findByNomeContaining(String termo);

    public List<Produto> findByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

    public List<Produto> findByCategoriaNomeOrderByPrecoDesc(String categoriaNome);

    public long countByCategoriaNome(String categoriaNome);

    public long countByPrecoGreaterThan(Double preco);

    public List<Produto> findByPrecoLessThanOrNomeContaining(Double preco, String termo);

    public List<Produto> findTop3ByPrecoOrderByPrecoDesc(Double preco);

    public List<Produto> findTop5ByCategoriaNomeOrderByPrecoAsc(String categoriaNome);

    @Query("SELECT p FROM Produto p WHERE p.preco > :preco")
    List<Produto> buscarPorPrecoMaior(@Param("preco") Double preco);

    @Query("SELECT p FROM Produto p ORDER by p.preco ASC")
    List<Produto> buscarOrdenadoPorPrecoAsc();

    @Query("SELECT p FROM Produto p ORDER by p.preco DESC")
    List<Produto> buscarOrdenadoPorPrecoDesc();

    @Query("SELECT p FROM Produto p WHERE p.nome ILIKE :letra% ")
    List<Produto> buscarProdutosPorLetraInicial(@Param("letra") String letra);

    @Query("SELECT AVG(p.preco) FROM Produto p")
    Double calcularMediaPrecoProdutos();

    @Query("SELECT MAX(p.preco) FROM Produto p WHERE p.categoria.nome = :categoria")
    Double buscarPrecoMaximoPorCategoria(@Param("categoria") String categoria);

    @Query("SELECT p.nome, COUNT(p) FROM Produto p INNER JOIN Categoria c GROUP BY c.nome")
    List<Object[]> contarProdutosPorCategoria();

    @Query("SELECT p.nome, COUNT(p) FROM Produto p INNER JOIN Categoria c GROUP BY c.nome HAVING COUNT(p) > :quantidade")
    List<Object[]> categoriaComMaisDe(@Param("quantidade") long quantidade);

    @Query("SELECT p FROM Produto p WHERE (:nome IS NULL OR p.nome = :nome) AND (:categoria IS NULL OR p.categoria.nome = :categoria) ")
    List<Produto> buscarProdutosFiltrados(@Param("nome") String nome, @Param("categoria") String categoria);

    @Query(value = "SELECT * FROM Produto ORDER BY preco DESC LIMIT 5", nativeQuery = true)
    List<Produto> buscarTop5ProdutosMaisCaros();
}
