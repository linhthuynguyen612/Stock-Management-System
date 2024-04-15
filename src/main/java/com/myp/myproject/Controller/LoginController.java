package com.myp.myproject.Controller;

import com.myp.myproject.DB.database;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Button close;

    @FXML
    private AnchorPane main_form;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private double x = 0;
    private double y = 0;

    public void login() {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        conn = database.connect();
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, username.getText());
            pst.setString(2, password.getText());
            rs = pst.executeQuery();
            Alert alert;
            if(username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng nhập tên đăng nhập và mật khẩu");
                alert.showAndWait();
            }else{
                if(rs.next()){
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Dang nhap thanh cong");

                    btnLogin.getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(loader.load());

                    scene.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
                    Stage finalStage = stage;
                    scene.setOnMouseDragged((MouseEvent event) -> {
                        finalStage.setX(event.getScreenX() - x);
                        finalStage.setY(event.getScreenY() - y);
                    });
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.setTitle("Dashboard");
                    stage.show();
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Sai tên đăng nhập hoặc mật khẩu");
                    alert.showAndWait();
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
