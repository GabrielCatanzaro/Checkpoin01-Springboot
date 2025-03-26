package br.com.fiap.checkpoint1.service;

import br.com.fiap.checkpoint1.model.Pedido;
import br.com.fiap.checkpoint1.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> listarPedidos() {
        return repository.findAll();
    }

    public Optional<Pedido> buscarPedidoPorId(Long id) {
        return repository.findById(id);
    }

    public Pedido salvarPedido(Pedido pedido) {
        return repository.save(pedido);
    }

    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        return repository.findById(id).map(p -> {
            p.setClienteNome(pedidoAtualizado.getClienteNome());
            p.setValorTotal(pedidoAtualizado.getValorTotal());
            return repository.save(p);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void deletarPedido(Long id) {
        repository.deleteById(id);
    }
}
