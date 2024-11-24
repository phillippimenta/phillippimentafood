package br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.messaging;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.service.OrdemProducaoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class PedidoConsumer {

    private final OrdemProducaoService ordemProducaoService;
    private static final Logger LOGGER = Logger.getLogger(PedidoConsumer.class.getName());

    public PedidoConsumer(OrdemProducaoService ordemProducaoService) {
        this.ordemProducaoService = ordemProducaoService;
    }

    @RabbitListener(queues = "producao_solicitacao")
    public void receberPedidoParaProducao(String pedido) {
        UUID pedidoIdExterno = UUID.fromString(pedido);
        LOGGER.log(Level.INFO, "Recebido pedido para produção com id externo: {0}", new Object[]{pedidoIdExterno});
        ordemProducaoService.criarOrdemProducao(pedidoIdExterno);
    }
}
