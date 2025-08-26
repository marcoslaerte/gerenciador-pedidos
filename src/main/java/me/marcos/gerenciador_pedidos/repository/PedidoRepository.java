package me.marcos.gerenciador_pedidos.repository;

import me.marcos.gerenciador_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    public List<Pedido> findByDataEntregaIsNull();

    public List<Pedido> findByDataEntregaIsNotNull();

    public List<Pedido> findByDataAfter(LocalDate data);

    public List<Pedido> findByDataBefore(LocalDate data);

    public List<Pedido> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);

    @Query("SELECT p FROM Pedido p WHERE p.data BETWEEN :inicio AND :fim")
    List<Pedido> buscarPedidosPorPeriodo(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

}
