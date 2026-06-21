# Sistema de Cadastro de Pessoas - Arquitetura MVC

**Trabalho 3** - Refatoração do Trabalho 2 para o padrão MVC (Model-View-Controller)

## 📋 Funcionalidades (mantidas do Trabalho 2)

- Tela de cadastro com campos: **Nome**, **CPF**, **E-mail** e **Telefone**
- Botão **Salvar**: captura os valores e armazena em `ArrayList<Pessoa>`
- Botão **Cancelar**: limpa todos os campos do formulário
- Botão **Listar**: exibe os registros na `TableView` e no console
- Validação: Nome e CPF são obrigatórios (`IllegalArgumentException`)

## 🏗 Arquitetura MVC

```
src/main/java/
├── Main.java                              <- Inicializa View e Controller
└── com/clinica/
    ├── model/
    │   └── Pessoa.java                    <- Dados e regras de validação
    ├── view/
    │   └── PessoaView.java                <- Interface gráfica (Scene/Stage)
    └── controller/
        └── PessoaController.java          <- Eventos e ligação View ↔ Model
```

### Responsabilidade de cada camada

**Model (`Pessoa.java`)**
Representa os dados de uma pessoa. Possui atributos privados, construtor com
validação e getters/setters. Não conhece a interface gráfica.

**View (`PessoaView.java`)**
Monta todos os componentes visuais (campos de texto, botões, tabela) dentro
de uma `Scene` associada a um `Stage`. Expõe os componentes via getters para
que o Controller possa capturar valores e registrar eventos. Não contém
lógica de negócio nem manipula listas de dados diretamente.

**Controller (`PessoaController.java`)**
Recebe a View no construtor e registra os eventos dos botões (`setOnAction`).
Captura os valores digitados com `getText()`, instancia objetos `Pessoa`
(Model), armazena no `ArrayList<Pessoa>` em memória e atualiza a `TableView`
da View.

**Main (`Main.java`)**
Ponto de entrada da aplicação (pacote default). Cria a `View`, cria o
`Controller` passando a View, e exibe a janela.

## 🚀 Como Executar

### Pré-requisitos
- Java 17 ou superior
- Maven instalado

### Rodar o projeto
```bash
mvn javafx:run
```

## 🔗 Relação com os trabalhos anteriores

- **Trabalho 1**: lógica de POO em console (`Paciente.java`)
- **Trabalho 2**: interface JavaFX em uma única classe (`Pessoa` + `MainApp`)
- **Trabalho 3**: mesma funcionalidade do Trabalho 2, agora separada em
  Model, View e Controller, mantendo todo o comportamento original.
