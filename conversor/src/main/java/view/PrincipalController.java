package view;

import java.net.URL;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class PrincipalController implements Initializable {

    private final char ds = new DecimalFormatSymbols(Locale.getDefault(Locale.Category.FORMAT)).getDecimalSeparator();
    private final NumberFormat nf = NumberFormat.getInstance(Locale.getDefault());

    @FXML
    private Button btnLimp;

    @FXML
    private Button button;

    @FXML
    private Button btnFechar;

    @FXML
    private RadioButton celsius;

    @FXML
    private RadioButton fah;

    @FXML
    private TextField txtfld;

    @FXML
    private Text txt1;

    @FXML
    private Text txt2;

    @FXML
    private Label label;

    @FXML
    private Tooltip ttip;

    private int cond;

    private double valor;

    private double n;

    @FXML
    private void rdbtnCel(ActionEvent event) {
        cond = 1;
        txtfld.setPromptText("Celsius");
        txt1.setFill(Color.GREEN);
        txt2.setFill(Color.GREY);
    }

    @FXML
    private void rdbtnFah(ActionEvent event) {
        cond = 2;
        txtfld.setPromptText("Fahrenheit");
        txt2.setFill(Color.GREEN);
        txt1.setFill(Color.GREY);
    }

    @FXML
    private void btnLimpAction(ActionEvent event) {
        txtfld.clear();
    }

    @FXML
    private void btnFecharAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            valor = nf.parse(txtfld.getText()).doubleValue();
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (cond) {
            case 1:
                n = (9 / 5d) * valor;
                label.setText("Temperatura\nconvertida:\n" + nf.format(n + 32) + "°F");

                break;
            case 2:
                n = 5 / 9d;
                label.setText("Temperatura\nconvertida:\n" + nf.format((valor - 32) * n) + "°C");
                break;
            default:
                break;
        }
    }

    private void habilitarBotoes() {
        button.setDisable(txtfld.getText().isEmpty());
        btnLimp.setDisable(txtfld.getText().isEmpty());
        label.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtfld.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (!newValue.matches("(\\-)?\\d*(\\" + ds + "\\d*)?")
                    && !newValue.isEmpty()) {
                        txtfld.setText(oldValue);
                    }
                    habilitarBotoes();
                });
    }
}
