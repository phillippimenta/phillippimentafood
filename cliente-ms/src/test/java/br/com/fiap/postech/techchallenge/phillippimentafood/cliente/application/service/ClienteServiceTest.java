package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.service;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.exception.CPFClienteJaCadastradoException;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.CPF;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Cliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Email;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.ClienteJpaRepository;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.ClienteRepository;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.entity.ClienteEntity;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.mapper.ClienteEntityMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClienteServiceTest {

    private ClienteService clienteService;
    @Mock
    private ClienteJpaRepository clienteJpaRepository;
    private ClienteEntityMapper clienteEntityMapper;
    private AutoCloseable mock;

    @BeforeEach
    void setup() {
        this.mock = MockitoAnnotations.openMocks(this);
        this.clienteEntityMapper = new ClienteEntityMapper();
        this.clienteService = new ClienteService(new ClienteRepository(clienteJpaRepository, clienteEntityMapper));
    }

    @AfterEach
    void tearDown() throws Exception {
        this.mock.close();
    }

    @Test
    public void deveCadastrarCliente_QuandoDadosSaoValidos() {
        // Arrange
        String nome = "Ulysses Guimarães";
        CPF cpf = new CPF("826.492.800-50");
        Email email = new Email("ulyssesguimaraes@mailinator.com");
        Cliente cliente = new Cliente(cpf, nome, email);
        ClienteEntity clienteEntity = this.clienteEntityMapper.fromDomain(cliente);
        clienteEntity.setId(1L);
        when(this.clienteJpaRepository.obterPorCpf(any(String.class))).thenReturn(null);
        when(this.clienteJpaRepository.save(any(ClienteEntity.class))).thenReturn(clienteEntity);
        // Act
        Cliente clienteRegistrado = this.clienteService.cadastrarCliente(cliente);
        // Assert
        verify(this.clienteJpaRepository, times(1)).save(any());
        assertThat(clienteRegistrado)
                .isInstanceOf(Cliente.class)
                .isNotNull();
        assertThat(clienteRegistrado.getCpf()).isEqualTo(cpf);
        assertThat(clienteRegistrado.getNome()).isEqualTo(nome);
        assertThat(clienteRegistrado.getEmail()).isEqualTo(email);
    }

    @Test
    public void deveGerarExcecao_QuandoCadastrarCliente_CPFJaExiste() {
        // Arrange
        CPF cpf = new CPF("826.492.800-50");
        Email email = new Email("ulyssesguimaraes@mailinator.com");
        Cliente cliente = new Cliente(cpf, "Ulysses Guimarães", email);
        ClienteEntity clienteEntity = this.clienteEntityMapper.fromDomain(cliente);
        clienteEntity.setId(1L);
        when(this.clienteJpaRepository.obterPorCpf(any(String.class))).thenReturn(clienteEntity);
        String mensagem = String.format("Cliente com CPF %s já cadastrado.", cpf.getNumeroFormatado());
        // Act and Assert
        assertThatThrownBy(() -> this.clienteService.cadastrarCliente(cliente))
                .isInstanceOf(CPFClienteJaCadastradoException.class)
                .hasMessage(mensagem);
    }
}
