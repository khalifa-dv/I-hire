/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Question;
import Models.Test;
import Validation.TestQuestionValidation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author e.bentijani
 */
public class TestInterfaceController implements Initializable {

    private ChoiceBox<String> fxDuree;
    @FXML
    private Button fxaddQuestions;
    @FXML
    private TextField fxTopic;
    @FXML
    private TextField fxTotalQuestions;
    @FXML
    private ChoiceBox<String> fxDuration;
    @FXML
    private Text fxErrorTopic;
    @FXML
    private Text fxErrorTotalQuestions;
    @FXML
    private Button btnCancel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fxDuration.getItems().add("15 Min");
        fxDuration.getItems().add("30 Min");
        fxDuration.getItems().add("45 Min");
        fxDuration.getItems().add("1H");
        fxDuration.getItems().add("1H 30Min");
        fxDuration.getItems().add("2H");
        fxDuration.setValue("15 Min");

    }

    @FXML
    private void addQuestions(ActionEvent event) {
        Test test = new Test();

        fxErrorTopic.setText(TestQuestionValidation.ifInputStringEmptyDO("Titre", fxTopic.getText()));
        fxErrorTotalQuestions.setText(TestQuestionValidation.validateStringOfNumber("Nombre de question à selectionné",
                fxTotalQuestions.getText()));

        if (fxErrorTopic.getText().trim().isEmpty() && fxErrorTotalQuestions.getText().trim().isEmpty()) {
            switch (fxDuration.getValue()) {
                case "15 Min":
                    test.setDuration(15);
                    break;
                case "30 Min":
                    test.setDuration(30);
                    break;
                case "45 Min":
                    test.setDuration(45);
                    break;
                case "1H":
                    test.setDuration(60);
                    break;
                case "1H 30Min":
                    test.setDuration(90);
                    break;

                case "2H":
                    test.setDuration(120);
                    break;
                default:
                // code block
                }

            test.setTopic(fxTopic.getText());
            test.setTotalQuestions(Integer.valueOf(fxTotalQuestions.getText()));
            test.setCreatedDate(new Date());
            test.setUpdatedDate(new Date());
            test.setQuestions(new ArrayList<Question>());
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "../GUI/QuestionInterface.fxml"
                    )
            );
            try {
                Parent root = loader.load();
                QuestionInterfaceController controller = loader.getController();
                controller.setTest(test);
  

                fxaddQuestions.getScene().setRoot(root);
                controller.jsp(test);
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }
    }

    @FXML
    private void cancel(ActionEvent event) {
    }

}
//
//    Stage stage = (Stage) fxaddQuestions.getScene().getWindow();
//    stage.close();
