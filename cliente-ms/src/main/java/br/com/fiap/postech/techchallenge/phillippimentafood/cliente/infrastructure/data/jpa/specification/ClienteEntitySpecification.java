package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.specification;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.entity.ClienteEntity;
import org.springframework.data.jpa.domain.Specification;

public class ClienteEntitySpecification {

    public static Specification<ClienteEntity> comNome(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }

    public static Specification<ClienteEntity> comCpf(String cpf) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("cpf"), cpf.replaceAll("\\D", ""));
    }

    public static Specification<ClienteEntity> comEmail(String email) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("email"), "%" + email + "%");
    }
}
