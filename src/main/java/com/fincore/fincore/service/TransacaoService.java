package com.fincore.fincore.service;

import com.fincore.fincore.dto.ResumoFinanceiro;
import com.fincore.fincore.entity.Transacao;
import com.fincore.fincore.enums.TipoTransacao;
import com.fincore.fincore.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {
     private final TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public List<Transacao> listarTodas(){
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> buscaPorId(Long id){

        return transacaoRepository.findById(id);

    }

    public Transacao cadastrar(Transacao transacao){

        validarCategoria(transacao);
        return transacaoRepository.save(transacao);

    }

    public Optional<Transacao> atualizar(Long id, Transacao transacao){
        return transacaoRepository.findById(id)
                .map(transacao1 ->{
                    validarCategoria(transacao);

                    transacao1.setDescription(transacao.getDescription());
                    transacao1.setValue(transacao.getValue());
                    transacao1.setTipo(transacao.getTipo());
                    transacao1.setCategoria(transacao.getCategoria());
                    transacao1.setData(transacao.getData());

                    return transacaoRepository.save(transacao1);

                });

    }

    public boolean excluir(Long id){
        if (transacaoRepository.existsById(id)) {
            transacaoRepository.deleteById(id);
            return true;
        }

        return false;

    }


    private void validarCategoria(Transacao transacao){
        if (!transacao.getCategoria().permite(transacao.getTipo())){
            throw new IllegalArgumentException(
                    "A categoria informada não é compatível com o tipo da transação."
            );
        }
    }

    public BigDecimal calcularSaldo(){
        BigDecimal receitas = Optional.ofNullable(
                transacaoRepository.somarTipo(TipoTransacao.RECEITA)
        ).orElse(BigDecimal.ZERO);

        BigDecimal despesas = Optional.ofNullable(
                transacaoRepository.somarTipo(TipoTransacao.DESPESA)
        ).orElse(BigDecimal.ZERO);

        return receitas.subtract(despesas);
    }


    public ResumoFinanceiro resumo(){
        BigDecimal receitas = Optional.ofNullable(
                transacaoRepository.somarTipo(TipoTransacao.RECEITA)
        ).orElse(BigDecimal.ZERO);

        BigDecimal despesas = Optional.ofNullable(
                transacaoRepository.somarTipo(TipoTransacao.DESPESA)
        ).orElse(BigDecimal.ZERO);

        BigDecimal saldo = receitas.subtract(despesas);

        return new ResumoFinanceiro(receitas, despesas, saldo);
    }





    public ResumoFinanceiro resumoPeriodo(LocalDate inicio, LocalDate fim){
        BigDecimal receitas = Optional.ofNullable(
                transacaoRepository.somarTipoEPeriodo (TipoTransacao.RECEITA, inicio, fim)
        ).orElse(BigDecimal.ZERO);

        BigDecimal despesas = Optional.ofNullable(
                transacaoRepository.somarTipoEPeriodo (TipoTransacao.DESPESA, inicio, fim)
        ).orElse(BigDecimal.ZERO);

        BigDecimal saldo = receitas.subtract(despesas);

        return new ResumoFinanceiro(receitas, despesas, saldo);
    }
}
