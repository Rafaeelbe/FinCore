package com.fincore.fincore.enums;

import java.util.Set;

public enum Categoria {


    SALARIO(TipoTransacao.RECEITA),
    RENDA_EXTRA(TipoTransacao.RECEITA),
    ALIMENTACAO(TipoTransacao.DESPESA),
    MORADIA(TipoTransacao.DESPESA),
    TRANSPORTE(TipoTransacao.DESPESA),
    SAUDE(TipoTransacao.DESPESA),
    EDUCACAO(TipoTransacao.DESPESA),
    LAZER(TipoTransacao.DESPESA),
    OUTROS(TipoTransacao.RECEITA, TipoTransacao.DESPESA);


    private final Set <TipoTransacao> tipoPermitido;

    Categoria(TipoTransacao... tipoPermitido) {
        this.tipoPermitido = Set.of(tipoPermitido);
    }

    public boolean permite (TipoTransacao tipo) {
        return tipoPermitido.contains(tipo);
    }



}
