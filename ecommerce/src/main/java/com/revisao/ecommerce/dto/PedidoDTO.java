package com.revisao.ecommerce.dto;

import com.revisao.ecommerce.entities.StatusDoPedido;

import java.time.Instant;

public record PedidoDTO(Long id, Instant momento, StatusDoPedido status) {
}
