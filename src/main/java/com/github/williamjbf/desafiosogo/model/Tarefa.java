package com.github.williamjbf.desafiosogo.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "seq_tarefa",allocationSize = 1)
public class Tarefa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_tarefa")
    private long id;

    @NotBlank
    private String titulo;
    @Min(1)
    @Max(10)
    private int frequencia;

    @Min(1)
    @Max(5)
    private int prioridade;
    @Min(1)
    private int minutosNecessario;

    private LocalDate diaAgendado;

    @ManyToOne
    private Status status;

    public Tarefa(){
        this.diaAgendado = LocalDate.now();
        statusPadrao();
    }

    private void statusPadrao(){
        Status status = new Status();
        status.setId(1);
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getMinutosNecessario() {
        return minutosNecessario;
    }

    public void setMinutosNecessario(int minutosNecessario) {
        this.minutosNecessario = minutosNecessario;
    }

    public LocalDate getDiaAgendado() {
        return diaAgendado;
    }

    public void setDiaAgendado(LocalDate diaAgendado) {
        this.diaAgendado = diaAgendado;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }



}
