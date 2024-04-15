module com.myp.myproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.myp.myproject to javafx.fxml;
    opens com.myp.myproject.Controller to javafx.fxml;
    opens com.myp.myproject.Model to javafx.fxml;

    exports com.myp.myproject;
    exports com.myp.myproject.Controller;
    exports com.myp.myproject.Model;
}