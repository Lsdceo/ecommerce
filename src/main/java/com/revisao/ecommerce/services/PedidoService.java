package com.revisao.ecommerce.services;

import com.revisao.ecommerce.dto.PedidoDTO;
import com.revisao.ecommerce.entities.Categoria;
import com.revisao.ecommerce.entities.Pedido;
import com.revisao.ecommerce.entities.StatusDoPedido;
import com.revisao.ecommerce.entities.Usuario;
import com.revisao.ecommerce.repositories.PedidoRepository;
import com.revisao.ecommerce.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    public PedidoDTO inserir(PedidoDTO dto){
        Pedido pedido = new Pedido();
        pedido.setMomento(Instant.now());
        pedido.setStatus(StatusDoPedido.AGUARDANDO_PAGAMENTO);

        Usuario user = usuarioRepository.getReferenceById(dto.getClienteid());

        pedido.setCliente(user);

        pedido = pedidoRepository.save(pedido);


        return new PedidoDTO(pedido);
    }

    public PedidoDTO findById(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        return new PedidoDTO(pedido);
    }

    @Transactional
    public PedidoDTO update(Long id, PedidoDTO dto) {
        Pedido pedido = pedidoRepository.getReferenceById(id);
        pedido.setStatus(dto.getStatus());

        if (dto.getClienteid() != null) {
            Usuario user = usuarioRepository.getReferenceById(dto.getClienteid());
            pedido.setCliente(user);
        }

        pedido = pedidoRepository.save(pedido);
        return new PedidoDTO(pedido);
    }

    @Transactional
    public void delete(Long id) {
        pedidoRepository.deleteById(id);
    }

    public List<PedidoDTO> findAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(PedidoDTO::new).toList();
    }

}
