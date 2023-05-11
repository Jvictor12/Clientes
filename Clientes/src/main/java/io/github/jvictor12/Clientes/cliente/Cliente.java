package io.github.jvictor12.Clientes.cliente;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotEmpty(message = "{campo.nome.obrigatorio}")
    private String nome;

    @CPF (message = "{campo.cpf.invalido}")
    @NotEmpty (message = "{campo.cpf.obrigatorio}")
    private String CPF;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        this.dataCadastro = LocalDate.now();
    }

}
