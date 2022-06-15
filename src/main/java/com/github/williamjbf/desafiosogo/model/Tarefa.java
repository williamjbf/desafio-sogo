package com.github.williamjbf.desafiosogo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "seq_tarefa",allocationSize = 1)
public class Tarefa{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_tarefa")
    private long id;

    private String titulo;

    private int frequencia;

    private int prioridade;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarefa tarefa = (Tarefa) o;
        return id == tarefa.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
