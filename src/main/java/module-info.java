module com.example.lms {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;

    opens com.example.lms to javafx.fxml;
    exports com.example.lms;
}