package com.github.williamjbf.desafiosogo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "seq_usuario",allocationSize = 1)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_usuario")
    private long id;
    @NotBlank
    private String nome;

    @Min(1)
    private int tempoDisponivelMinutos;

    @NotBlank
    @Column(unique = true)
    private String login;
    @NotBlank
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
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

    public int getTempoDisponivelMinutos() {
        return tempoDisponivelMinutos;
    }

    public void setTempoDisponivelMinutos(int tempoDisponivelMinutos) {
        this.tempoDisponivelMinutos = tempoDisponivelMinutos;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(login, usuario.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
