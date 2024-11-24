package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.specification;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.entity.ClienteEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.domain.Specification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ClienteEntitySpecificationTest {

    @Test
    void testComNome() {
        // Arrange
        String nome = "João";
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        Root<ClienteEntity> root = mock(Root.class);
        // Simulando o comportamento de criteriaBuilder.like
        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.like(root.get("nome"), "%" + nome + "%")).thenReturn(predicate);
        // Act
        Specification<ClienteEntity> spec = ClienteEntitySpecification.comNome(nome);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);
        // Assert
        verify(criteriaBuilder).like(root.get("nome"), "%" + nome + "%");
        assertEquals(predicate, result);
    }

    @Test
    void testComCpf() {
        // Arrange
        String cpf = "123.456.789-00";
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        Root<ClienteEntity> root = mock(Root.class);
        // Simulando o comportamento de criteriaBuilder.equal
        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.equal(root.get("cpf"), "12345678900")).thenReturn(predicate);
        // Act
        Specification<ClienteEntity> spec = ClienteEntitySpecification.comCpf(cpf);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);
        // Assert
        verify(criteriaBuilder).equal(root.get("cpf"), "12345678900");
        assertEquals(predicate, result);
    }

    @Test
    void testComEmail() {
        // Arrange
        String email = "joao@example.com";
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        Root<ClienteEntity> root = mock(Root.class);
        // Simulando o comportamento de criteriaBuilder.like
        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.like(root.get("email"), "%" + email + "%")).thenReturn(predicate);
        // Act
        Specification<ClienteEntity> spec = ClienteEntitySpecification.comEmail(email);
        Predicate result = spec.toPredicate(root, query, criteriaBuilder);
        // Assert
        verify(criteriaBuilder).like(root.get("email"), "%" + email + "%");
        assertEquals(predicate, result);
    }
}
