package com.example.barthing;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.util.ArrayList;

public class searchController {
    @FXML private TableView<Drug> table;
    @FXML private TableColumn<Drug, Integer> Id;
    @FXML private TableColumn<Drug, String > Name;
    @FXML private TableColumn<Drug, Integer> Price;
    @FXML private TableColumn<Drug, Integer> Amount;
    @FXML private TableColumn<Drug, String> Btn;

    @FXML
    private ChoiceBox searchCategory;

    @FXML
    private TextField searchQuery;

    private static ObservableList<Drug> Drugs = FXCollections.observableArrayList();

    @FXML
    protected void Search() {
        String category = (String) searchCategory.getValue();
        String search = searchQuery.getText();

        ArrayList<Drug> searchDrugs = Main.dbConn.searchDrugs(search, category);
        Drugs.clear();
        Drugs.addAll(searchDrugs);
        System.out.println(Drugs);
        setTheTable();
    }

    @FXML
    protected void setTheTable() {
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
                                        setTheTable();
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

    protected boolean DeleteOne(Drug drug) {
        return Main.dbConn.deleteOne(drug.getId());
    }

    @FXML
    protected void goBack() {
        mainController.readTheDB();
        Main.SceneController.activate("mainScreen");
    }
}
