package com.kurotkin.app.controller;

import com.kurotkin.model.dao.ProductDao;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MainController extends Controller{
    @FXML private Button load;
    private ProductTableController tableController;
    private ProductDao productDao;

    @Autowired
    public MainController(ProductTableController tableController, ProductDao productDao) {
        this.tableController = tableController;
        this.productDao = productDao;
    }

    // Processing of a click of a download button
    @FXML
    public void onClickLoad() {
        tableController.fillTable(productDao.getAllProducts());
        load.setDisable(true);
    }

}
