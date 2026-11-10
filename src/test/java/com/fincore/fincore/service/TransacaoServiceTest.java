package com.fincore.fincore.service;

import com.fincore.fincore.dto.ResumoFinanceiro;
import com.fincore.fincore.entity.Transacao;
import com.fincore.fincore.enums.Categoria;
import com.fincore.fincore.enums.TipoTransacao;
import com.fincore.fincore.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @Mock
    private TransacaoRepository transacaoRepository;

    @InjectMocks
    private TransacaoService transacaoService;

    @Test

    void calcularSaldo(){
        when(transacaoRepository.somarTipo(TipoTransacao.RECEITA))
                .thenReturn(new BigDecimal("3500.00"));

        when(transacaoRepository.somarTipo(TipoTransacao.DESPESA))
                .thenReturn(new BigDecimal("1000.00"));

        BigDecimal resultado = transacaoService.calcularSaldo();

        assertEquals (new BigDecimal("2500.00"), resultado);
    }

    @Test
    void resumoFinanceiro(){
        when(transacaoRepository.somarTipo(TipoTransacao.RECEITA))
                .thenReturn(new BigDecimal("3500.00"));

        when(transacaoRepository.somarTipo(TipoTransacao.DESPESA))
                .thenReturn(new BigDecimal("1000.00"));

        ResumoFinanceiro resultado = transacaoService.resumo();

        assertEquals (new BigDecimal("3500.00"), resultado.getReceitas());
        assertEquals (new BigDecimal("1000.00"), resultado.getDespesas());
        assertEquals (new BigDecimal("2500.00"), resultado.getSaldo());

    }

    @Test
    void retornarZero(){
        when(transacaoRepository.somarTipo(TipoTransacao.RECEITA))
                .thenReturn(null);

        when(transacaoRepository.somarTipo(TipoTransacao.DESPESA))
                .thenReturn(null);

        BigDecimal resultado = transacaoService.calcularSaldo();

        assertEquals (BigDecimal.ZERO, resultado);


    }

    @Test
    void retornarInvalido() {
        Transacao transacao = new Transacao();

        transacao.setTipo(TipoTransacao.RECEITA);
        transacao.setCategoria(Categoria.ALIMENTACAO);

        assertThrows(IllegalArgumentException.class, () ->
                transacaoService.cadastrar(transacao));
    }

}
