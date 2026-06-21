package com.clinica.controller;

import com.clinica.model.Pessoa;
import com.clinica.view.PessoaView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

/**
 * CONTROLLER
 * Faz a ponte entre a View e o Model:
 * - Captura os valores digitados na View (getText())
 * - Instancia objetos Pessoa (Model) a partir desses dados
 * - Armazena os registros em memória (ArrayList<Pessoa>)
 * - Atualiza a View (TableView, mensagens, limpeza de campos)
 * - Trata os eventos dos botões (Salvar, Cancelar, Listar)
 */
public class PessoaController {

    private final PessoaView view;

    // Armazenamento em memória
    private final ArrayList<Pessoa> listaPessoas = new ArrayList<>();

    // Lista observável usada pela TableView da View
    private final ObservableList<Pessoa> dadosTabela = FXCollections.observableArrayList();

    public PessoaController(PessoaView view) {
        this.view = view;
        this.view.setDadosTabela(dadosTabela);
        registrarEventos();
    }

    /**
     * Liga os botões da View aos métodos de lógica deste Controller.
     */
    private void registrarEventos() {
        view.getBtnSalvar().setOnAction(evento -> salvarPessoa());
        view.getBtnCancelar().setOnAction(evento -> limparCampos());
        view.getBtnListar().setOnAction(evento -> listarPessoas());
    }

    /**
     * Captura os dados do formulário (View), cria um Pessoa (Model)
     * e armazena na lista em memória.
     */
    private void salvarPessoa() {
        String nome = view.getCampoNome().getText();
        String cpf = view.getCampoCpf().getText();
        String email = view.getCampoEmail().getText();
        String telefone = view.getCampoTelefone().getText();

        try {
            // Instancia o Model com os dados vindos da View
            Pessoa novaPessoa = new Pessoa(nome, cpf, email, telefone);

            listaPessoas.add(novaPessoa);
            dadosTabela.add(novaPessoa);

            System.out.println("\n--- Pessoa Cadastrada ---");
            novaPessoa.exibirDados();

            view.exibirMensagem("✔ Pessoa cadastrada com sucesso!", "#27ae60");
            limparCampos();

        } catch (IllegalArgumentException e) {
            view.exibirMensagem("⚠ Erro: " + e.getMessage(), "#e74c3c");
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    /**
     * Limpa os campos do formulário na View (botão Cancelar).
     */
    private void limparCampos() {
        view.getCampoNome().clear();
        view.getCampoCpf().clear();
        view.getCampoEmail().clear();
        view.getCampoTelefone().clear();
        view.getCampoNome().requestFocus();
        view.exibirMensagem("Campos limpos.", "#7f8c8d");
    }

    /**
     * Lista todos os registros no console e garante que a tabela
     * da View esteja atualizada (botão Listar).
     */
    private void listarPessoas() {
        if (listaPessoas.isEmpty()) {
            view.exibirMensagem("Nenhuma pessoa cadastrada ainda.", "#e67e22");
            System.out.println("\nNenhuma pessoa cadastrada.");
            return;
        }

        System.out.println("\n===== Lista de Pessoas Cadastradas =====");
        for (int i = 0; i < listaPessoas.size(); i++) {
            System.out.println("--- Registro " + (i + 1) + " ---");
            listaPessoas.get(i).exibirDados();
        }
        System.out.println("Total: " + listaPessoas.size() + " pessoa(s).");

        view.exibirMensagem(
            "Total cadastrado: " + listaPessoas.size() + " pessoa(s). (Ver console para detalhes)",
            "#2980b9"
        );
    }
}
