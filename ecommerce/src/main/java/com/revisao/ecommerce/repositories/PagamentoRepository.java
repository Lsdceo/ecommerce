package com.revisao.ecommerce.repositories;


import com.revisao.ecommerce.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
