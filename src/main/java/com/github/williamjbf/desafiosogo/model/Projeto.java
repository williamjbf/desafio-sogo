package com.github.williamjbf.desafiosogo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@SequenceGenerator(name = "seq_projeto",allocationSize = 1)
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_projeto")
    private long id;
    private String titulo;

    @OneToMany
    private List<Tarefa> tarefas;

    public void adicionarTarefa(Tarefa tarefa){
        this.tarefas.add(tarefa);
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

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

}
