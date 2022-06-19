package com.github.williamjbf.desafiosogo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "seq_projeto",allocationSize = 1)
public class Projeto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_projeto")
    private long id;
    private String titulo;

    @OneToMany(targetEntity = Tarefa.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="projeto_id",referencedColumnName = "id")
    private List<Tarefa> tarefas = new ArrayList<>();

    public void adicionarTarefas(Tarefa tarefa){
        this.tarefas.add(tarefa);
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Tarefa> tarefas) {
        this.tarefas = tarefas;
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


}
