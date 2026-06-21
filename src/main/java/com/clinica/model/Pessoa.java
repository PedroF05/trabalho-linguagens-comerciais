package com.clinica.model;

/**
 * MODEL
 * Representa uma Pessoa cadastrada no sistema.
 * Responsável apenas pelos dados e regras do domínio (encapsulamento e validação).
 * Não conhece nada sobre a interface gráfica (View) ou o Controller.
 */
public class Pessoa {

    // Atributos privados (encapsulamento)
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    /**
     * Construtor com validação de dados obrigatórios.
     */
    public Pessoa(String nome, String cpf, String email, String telefone) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório.");
        }

        this.nome = nome.trim();
        this.cpf = cpf.trim();
        this.email = email != null ? email.trim() : "";
        this.telefone = telefone != null ? telefone.trim() : "";
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) this.nome = nome.trim();
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) {
        if (cpf != null && !cpf.trim().isEmpty()) this.cpf = cpf.trim();
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        this.email = email != null ? email.trim() : "";
    }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) {
        this.telefone = telefone != null ? telefone.trim() : "";
    }

    /**
     * Exibe os dados no console.
     */
    public void exibirDados() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("E-mail: " + email);
        System.out.println("Telefone: " + telefone);
    }

    @Override
    public String toString() {
        return nome + " | " + cpf + " | " + email + " | " + telefone;
    }
}
