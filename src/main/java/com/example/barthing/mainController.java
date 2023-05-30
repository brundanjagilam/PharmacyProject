package com.example.barthing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {
    @FXML private TableView<Drug> table;
    @FXML private TableColumn<Drug, Integer> Id;
    @FXML private TableColumn<Drug, String > Name;
    @FXML private TableColumn<Drug, Integer> Price;
    @FXML private TableColumn<Drug, Integer> Amount;
    @FXML private TableColumn<Drug, String> Btn;

    private static ObservableList<Drug> Drugs = FXCollections.observableArrayList();

    @FXML
    protected void addDrugBtn() {
        Main.SceneController.activate("addDrugs");
    }

    public static void readTheDB() {
        Drugs.clear();
        Drugs.addAll(Main.dbConn.getDrugs());
    }

    @FXML
    protected void Refresh() {
        readTheDB();
        Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Price.setCellValueFactory(new PropertyValueFactory<>("Price"));
        Amount.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        Callback<TableColumn<Drug, String>, TableCell<Drug, String>> cellFactory
                = //
                new Callback<>() {
                    @Override
                    public TableCell call(final TableColumn<Drug, String> param) {
                        final TableCell<Drug, String> cell = new TableCell<>() {

                            final Button btn = new Button("Delete");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Drug drug = getTableView().getItems().get(getIndex());
                                        if(DeleteOne(drug)) {
                                            Drugs.remove(drug);
                                        }
                                        Refresh();
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        Btn.setCellFactory(cellFactory);
        table.setItems(Drugs);
    }

    @FXML
    protected void Search() {
        Main.SceneController.activate("searchDrugs");
    }

    protected boolean DeleteOne(Drug drug) {
        return Main.dbConn.deleteOne(drug.getId());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Refresh();
    }
}