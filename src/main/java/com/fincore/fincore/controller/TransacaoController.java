package com.fincore.fincore.controller;

import com.fincore.fincore.dto.ResumoFinanceiro;
import com.fincore.fincore.entity.Transacao;
import com.fincore.fincore.service.TransacaoService;
import jakarta.validation.Valid;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class TransacaoController {

    private final TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping (path = "/transacoes")
    public List<Transacao> listar(){
        return transacaoService.listarTodas();
    }

    @GetMapping (path = "/transacoes/id/{id}")
    public ResponseEntity <Transacao> listarPorId(@PathVariable Long id){
        return transacaoService.buscaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping (path = "/transacoes/saldo")
    public BigDecimal consultarSaldo(){
        return transacaoService.calcularSaldo();
    }

    @GetMapping (path = "/transacoes/resumo")
    public ResumoFinanceiro resumo(){
        return transacaoService.resumo();
    }

    @GetMapping (path = "/transacoes/resumo/periodo")
    public ResumoFinanceiro resumoPeriodo(@RequestParam LocalDate inicio, @RequestParam LocalDate fim){
        return transacaoService.resumoPeriodo(inicio, fim);
    }


    @PostMapping (path = "/transacoes")
    public Transacao criar(@Valid @RequestBody Transacao transacao){
        return transacaoService.cadastrar(transacao);

    }

    @PutMapping (path = "/transacoes/{id}")
    public ResponseEntity<Transacao> atualizar(@PathVariable Long id, @Valid @RequestBody Transacao transacao){
        return transacaoService.atualizar(id, transacao)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping (path = "/transacoes/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        if (transacaoService.excluir(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
