package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;

public class MainController {
    private Pane view;

    @FXML
    private BorderPane bpLabel;

    @FXML
    private Button btnAdicionarTurma;

    @FXML
    private Button btnAdicionarCurso;

    @FXML
    private Button btnAdicionarSistema;

    @FXML
    private Button inicio;

    @FXML
    void p_inicio(ActionEvent event) throws  IOException {


    }

    @FXML
    void adicionarCurso(ActionEvent event) throws IOException {
        Pane view = getView("/view/adicionarCursoView.fxml");
        bpLabel.setCenter(view);
    }

    @FXML
    void adicionarSistema(ActionEvent event) throws IOException {
        Pane view = getView("/view/adicionarSistemaView.fxml");
        bpLabel.setCenter(view);
    }

    @FXML
    void adicionarTurma(ActionEvent event) throws IOException {
        Pane view = getView("/view/adicionarTurmaView.fxml");
        bpLabel.setCenter(view);
    }

    //método para colocar a nova view na MainView
    public Pane getView(String fileName) throws IOException {
        URL fileURL = Main.class.getResource(fileName);
        view = new FXMLLoader().load(fileURL);
        return view;
    }
}
