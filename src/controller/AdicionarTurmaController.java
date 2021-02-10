package controller;

import classes.Turma;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.sql.*;
import java.util.*;
import static classes.Turma.*;

import java.sql.SQLException;


public class AdicionarTurmaController {

    @FXML
    private TableView<Turma> tabela;

    @FXML
    private TableColumn<Turma, String> coluna_turma;

    @FXML
    private TableColumn<Turma, String> coluna_curso;

    @FXML
    private TableColumn<Turma, Integer> col_ano;

    @FXML
    private TextField textfiel_turma;

    @FXML
    private TextField textfield_curso;

    @FXML
    private Button button_adicionar;

    @FXML
    private Button button_cancelar;

    @FXML
    private ComboBox<?> cb_curso;

    @FXML
    private ComboBox<?> cb_ano;

    private ObservableList<Turma> turmas;
    public Connection connection;

    public void data_insert(){
        //Guardar informações vindas dos textfields
        String turma= this.textfiel_turma.getText();
        int ano = Integer.parseInt(this.textfield_curso.getText());
        String sql_insert = "INSERT INTO turma (designacao, idCurso) VALUES (?, ?)";



    }




    public void initialize()  {
        turmas = FXCollections.observableArrayList();

        this.coluna_turma.setCellValueFactory(new PropertyValueFactory<Turma,String>("nome"));
        this.coluna_curso.setCellValueFactory(new PropertyValueFactory<Turma,String>("curso"));
        this.col_ano.setCellValueFactory(new PropertyValueFactory<Turma,Integer>("Ano"));


        try {
            connectDB();
        } catch (IOException e) {
            e.printStackTrace();
        }

        queryTurmas();
        this.tabela.setItems(turmas);

    }





    public void connectDB() throws IOException {
        Properties p = new Properties();
        InputStream is = new FileInputStream("C:\\Users\\Rafael\\IdeaProjects\\Exemplo_fx\\src\\dbConfig.properties");
        p.load(is);
        try {
            connection = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("INFO");
            alert.setContentText("Ligado à BD!");
            alert.showAndWait();
        } catch (SQLException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERRO");
            System.out.println(e);
            alert.setContentText("Não ligado à BD!");
            alert.showAndWait();
        }
    }

    public void queryTurmas()  {
        String sql = "SELECT nomeTurma, nomeCurso, anoTurma FROM turma, curso, ano_curso where turma.curso_idCurso=curso.idCurso and turma.idAno=ano_curso.idAno";
        try {
            Statement stm = this.connection.createStatement();
            ResultSet result = stm.executeQuery(sql);
            while (result.next()){
                String nome = result.getString(1);
                String curso= result.getString(2);
                int ano = result.getInt(3);
                Turma t = new Turma(nome,curso,ano);
                turmas.add(t);
                this.tabela.refresh();



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
