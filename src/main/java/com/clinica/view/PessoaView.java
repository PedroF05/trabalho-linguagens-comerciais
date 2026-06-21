package com.clinica.view;

import com.clinica.model.Pessoa;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * VIEW
 * Responsável apenas por montar a interface gráfica (componentes, layout,
 * Scene e Stage). Não contém lógica de negócio nem manipula a lista de
 * pessoas diretamente — apenas expõe os componentes através de getters
 * para que o Controller possa capturar valores e registrar os eventos.
 */
public class PessoaView {

    private final Stage stage;

    // Campos de texto do formulário
    private final TextField campoNome = criarCampo("Digite o nome completo");
    private final TextField campoCpf = criarCampo("000.000.000-00");
    private final TextField campoEmail = criarCampo("exemplo@email.com");
    private final TextField campoTelefone = criarCampo("(00) 00000-0000");

    // Botões
    private final Button btnSalvar = new Button("💾 Salvar");
    private final Button btnCancelar = new Button("✖ Cancelar");
    private final Button btnListar = new Button("📋 Listar");

    // Label de feedback
    private final Label labelMensagem = new Label("");

    // Tabela de listagem
    private final TableView<Pessoa> tabela = new TableView<>();

    public PessoaView(Stage stage) {
        this.stage = stage;
        montarTela();
    }

    /**
     * Monta toda a interface: formulário, botões e tabela.
     */
    private void montarTela() {
        stage.setTitle("Sistema de Cadastro de Pessoas - MVC");

        VBox painelPrincipal = new VBox(20);
        painelPrincipal.setPadding(new Insets(25));
        painelPrincipal.setStyle("-fx-background-color: #f0f4f8;");

        // Título
        Label titulo = new Label("Cadastro de Pessoas");
        titulo.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        titulo.setTextFill(Color.web("#2c3e50"));

        // Formulário
        GridPane formulario = new GridPane();
        formulario.setHgap(15);
        formulario.setVgap(12);
        formulario.setPadding(new Insets(20));
        formulario.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #d0d7de;" +
            "-fx-border-radius: 8;" +
            "-fx-background-radius: 8;"
        );

        formulario.add(criarLabel("Nome: *"), 0, 0);
        formulario.add(campoNome, 1, 0);

        formulario.add(criarLabel("CPF: *"), 0, 1);
        formulario.add(campoCpf, 1, 1);

        formulario.add(criarLabel("E-mail:"), 0, 2);
        formulario.add(campoEmail, 1, 2);

        formulario.add(criarLabel("Telefone:"), 0, 3);
        formulario.add(campoTelefone, 1, 3);

        ColumnConstraints col0 = new ColumnConstraints(100);
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setHgrow(Priority.ALWAYS);
        formulario.getColumnConstraints().addAll(col0, col1);

        // Botões
        HBox painelBotoes = new HBox(12);
        painelBotoes.setAlignment(Pos.CENTER_LEFT);

        btnSalvar.setStyle(estiloBotao("#27ae60"));
        btnSalvar.setPrefWidth(130);

        btnCancelar.setStyle(estiloBotao("#e74c3c"));
        btnCancelar.setPrefWidth(130);

        btnListar.setStyle(estiloBotao("#2980b9"));
        btnListar.setPrefWidth(130);

        painelBotoes.getChildren().addAll(btnSalvar, btnCancelar, btnListar);

        labelMensagem.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        // Tabela
        Label tituloTabela = new Label("Pessoas Cadastradas");
        tituloTabela.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        tituloTabela.setTextFill(Color.web("#2c3e50"));

        tabela.setPlaceholder(new Label("Nenhuma pessoa cadastrada ainda."));
        tabela.setPrefHeight(250);
        tabela.setStyle("-fx-border-color: #d0d7de; -fx-border-radius: 6;");

        TableColumn<Pessoa, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(dado -> new SimpleStringProperty(dado.getValue().getNome()));
        colNome.setPrefWidth(180);

        TableColumn<Pessoa, String> colCpf = new TableColumn<>("CPF");
        colCpf.setCellValueFactory(dado -> new SimpleStringProperty(dado.getValue().getCpf()));
        colCpf.setPrefWidth(130);

        TableColumn<Pessoa, String> colEmail = new TableColumn<>("E-mail");
        colEmail.setCellValueFactory(dado -> new SimpleStringProperty(dado.getValue().getEmail()));
        colEmail.setPrefWidth(200);

        TableColumn<Pessoa, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(dado -> new SimpleStringProperty(dado.getValue().getTelefone()));
        colTelefone.setPrefWidth(130);

        tabela.getColumns().addAll(colNome, colCpf, colEmail, colTelefone);

        painelPrincipal.getChildren().addAll(
            titulo,
            formulario,
            painelBotoes,
            labelMensagem,
            tituloTabela,
            tabela
        );

        Scene cena = new Scene(painelPrincipal, 700, 620);
        stage.setScene(cena);
        stage.setResizable(false);
    }

    /** Exibe a janela (Stage). */
    public void mostrar() {
        stage.show();
    }

    // ---------------------------------------------------------
    // Métodos auxiliares de construção visual
    // ---------------------------------------------------------

    private Label criarLabel(String texto) {
        Label label = new Label(texto);
        label.setFont(Font.font("Arial", FontWeight.BOLD, 13));
        label.setTextFill(Color.web("#34495e"));
        return label;
    }

    private TextField criarCampo(String placeholder) {
        TextField campo = new TextField();
        campo.setPromptText(placeholder);
        campo.setStyle(
            "-fx-border-color: #bdc3c7;" +
            "-fx-border-radius: 4;" +
            "-fx-background-radius: 4;" +
            "-fx-padding: 6 10;"
        );
        campo.setPrefHeight(36);
        return campo;
    }

    private String estiloBotao(String corFundo) {
        return "-fx-background-color: " + corFundo + ";" +
               "-fx-text-fill: white;" +
               "-fx-font-weight: bold;" +
               "-fx-font-size: 13px;" +
               "-fx-background-radius: 6;" +
               "-fx-cursor: hand;" +
               "-fx-padding: 8 16;";
    }

    public void exibirMensagem(String texto, String cor) {
        labelMensagem.setText(texto);
        labelMensagem.setTextFill(Color.web(cor));
    }

    // ---------------------------------------------------------
    // Getters - usados pelo Controller para capturar valores
    // e registrar os eventos (setOnAction)
    // ---------------------------------------------------------

    public TextField getCampoNome() { return campoNome; }
    public TextField getCampoCpf() { return campoCpf; }
    public TextField getCampoEmail() { return campoEmail; }
    public TextField getCampoTelefone() { return campoTelefone; }

    public Button getBtnSalvar() { return btnSalvar; }
    public Button getBtnCancelar() { return btnCancelar; }
    public Button getBtnListar() { return btnListar; }

    public TableView<Pessoa> getTabela() { return tabela; }

    public void setDadosTabela(ObservableList<Pessoa> dados) {
        tabela.setItems(dados);
    }
}
