package com.senac.gustavo.dto.response;

import com.senac.gustavo.entity.FolhaPagamento;
import com.senac.gustavo.entity.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class FuncionarioDtoResponse {
    private int id;
    private String matricula;

    private String nome;

    private LocalDate dataNascimento;

    private String chaveAcesso;

    private  Integer status;

    private Set<FolhaPagamento> folhaPagamento;
    private List<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chave_acesso) {
        this.chaveAcesso = chave_acesso;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<FolhaPagamento> getFolhaPagamento() {
        return folhaPagamento;
    }

    public void setFolhaPagamento(Set<FolhaPagamento> folhaPagamento) {
        this.folhaPagamento = folhaPagamento;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
