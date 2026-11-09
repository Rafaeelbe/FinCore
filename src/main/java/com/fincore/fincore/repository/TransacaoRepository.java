package com.fincore.fincore.repository;

import com.fincore.fincore.entity.Transacao;
import com.fincore.fincore.enums.TipoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;


public interface TransacaoRepository extends JpaRepository <Transacao,Long> {

    @Query("SELECT SUM(t.value) FROM Transacao t WHERE t.tipo = :tipo")
    BigDecimal somarTipo (@Param("tipo")TipoTransacao tipo);

    @Query("""
    SELECT SUM(t.value)
    FROM Transacao t
    WHERE t.tipo = :tipo
    AND t.data BETWEEN :inicio AND :fim
""")
    BigDecimal somarTipoEPeriodo(@Param("tipo") TipoTransacao tipo, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
