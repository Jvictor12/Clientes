package io.github.jvictor12.Clientes.cliente;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    @Query("FROM tb_cliente WHERE id = ?1")
    Optional<Cliente> findByUuID(String id);
}