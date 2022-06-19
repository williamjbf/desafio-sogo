package com.github.williamjbf.desafiosogo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@SequenceGenerator(name = "seq_usuario",allocationSize = 1)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_usuario")
    private long id;
    private String nome;
    @Column(unique = true)
    private String login;
    private String senha;

    @OneToMany(targetEntity = Projeto.class,cascade = CascadeType.ALL)
    @JoinColumn(name ="usuario_id",referencedColumnName = "id")
    private List<Projeto> projetos = new ArrayList<>();

    public void adicionarProjeto(Projeto projeto){
        this.projetos.add(projeto);
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
