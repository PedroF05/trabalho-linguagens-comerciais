import com.clinica.controller.PessoaController;
import com.clinica.view.PessoaView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe principal da aplicação (pacote default).
 * Responsável apenas por inicializar a View e o Controller,
 * conectando as camadas do padrão MVC.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Cria a View (monta a interface gráfica)
        PessoaView view = new PessoaView(stage);

        // Cria o Controller, ligando a View ao Model
        new PessoaController(view);

        // Exibe a janela
        view.mostrar();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
