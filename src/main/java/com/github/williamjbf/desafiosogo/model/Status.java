package com.github.williamjbf.desafiosogo.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "seq_tarefa",allocationSize = 1, initialValue = 3)
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_status")
    private long id;

    private String descricao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
