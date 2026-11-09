package com.fincore.fincore.entity;

import com.fincore.fincore.enums.Categoria;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fincore.fincore.enums.TipoTransacao;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table (name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank (message = "A descrição é obrigatória!")
    private String description;
    @NotNull(message = "O valor é obrigatório!")
    @DecimalMin(value = "0.01", message = "O valor minímo é R$ 0,01")
    private BigDecimal value;
    @NotNull (message = "O tipo da transação é obrigatório!")
    @Enumerated (EnumType.STRING)
    private TipoTransacao tipo;
    @NotNull(message = "A categoria é obrigatória")
    @Enumerated (EnumType.STRING)
    private Categoria categoria;
    @NotNull(message = "data é obrigatória")
    private LocalDate data;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoTransacao tipo) {
        this.tipo = tipo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
