package com.revisao.ecommerce.controllers;


import com.revisao.ecommerce.dto.PedidoDTO;
import com.revisao.ecommerce.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoDTO> insert(@RequestBody PedidoDTO dto){
        dto = pedidoService.inserir(dto);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
        PedidoDTO dto = pedidoService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll() {
        List<PedidoDTO> pedidos = pedidoService.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable Long id, @RequestBody PedidoDTO dto) {
        dto = pedidoService.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
