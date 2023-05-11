package io.github.jvictor12.Clientes.servico;

import io.github.jvictor12.Clientes.cliente.Cliente;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "tb_servico")
public class Servico {

    @Id
    private Integer id;

    @NotNull
    private String descricao;

    @ManyToOne
    @JoinColumn (name = "id_cliente")
    private Cliente cliente;

}
