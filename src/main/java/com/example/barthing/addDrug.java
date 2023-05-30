package com.example.barthing;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.UUID;
import java.util.regex.Pattern;

public class addDrug {
    public TextField drugName;
    public TextField drugPrice;
    public TextField drugCount;
    public Label errorMsg;

    private boolean InputIsValid() {
        boolean isPriceValid = Pattern.matches("[0-9]+", drugPrice.getText());
        boolean isCountValid = Pattern.matches("[0-9]+", drugCount.getText());
        if(isPriceValid && isCountValid) {
            return true;
        } else {
            return false;
        }
    }

    @FXML
    protected void addDrug() {
        if(InputIsValid()) {
            boolean result = Main.dbConn.addDrugToDB(drugName.getText(), drugPrice.getText(), UUID.randomUUID().toString(), drugCount.getText());

            if(result) {
                mainController.readTheDB();
                drugName.clear();
                drugPrice.clear();
                drugCount.clear();
                Main.SceneController.activate("mainScreen");
            } else {
                System.out.println("error");
            }
        } else {
            errorMsg.setVisible(true);
            System.out.println("Input is not valid");
        }
    }

    @FXML
    protected void goBack() {
        Main.SceneController.activate("mainScreen");
    }
}
