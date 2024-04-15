package com.myp.myproject.Controller;

import com.myp.myproject.DB.LoadData;
import com.myp.myproject.DB.database;
import com.myp.myproject.Model.Product;
import com.myp.myproject.Model.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardController implements Initializable {

    public Button btnAddPurchase;
    public AnchorPane formAddPurchase;
    public TextField numberProduct;
    public Button AddPurchaseBtn;
    @FXML
    private Button ApllierAddBtn;

    @FXML
    private TextField ApllierID;

    @FXML
    private TextField ApplierAddress;

    @FXML
    private TableColumn<Supplier, String> ApplierColAddress;

    @FXML
    private TableColumn<Supplier, String> ApplierColId;

    @FXML
    private TableColumn<Supplier, String> ApplierColName;

    @FXML
    private TableColumn<Supplier, String> ApplierColPhone;

    @FXML
    private Button ApplierDeleteBtn;

    @FXML
    private AnchorPane ApplierForm;

    @FXML
    private TextField ApplierName;

    @FXML
    private TextField ApplierPhone;

    @FXML
    private TextField ApplierSearchBtn;

    @FXML
    private TableView<Supplier> ApplierTableView;

    @FXML
    private Button ApplierUpdateBtn;

    @FXML
    private AnchorPane DashBoardForm;

    @FXML
    private AnchorPane DashboardForm;

    @FXML
    private Label NumberSale;

    @FXML
    private AnchorPane ProductForm;

    @FXML
    private Button PurchaseBtn;

    @FXML
    private Button PurchaseCancelBtn;

    @FXML
    private TableColumn<?, ?> PurchaseColName;

    @FXML
    private TableColumn<?, ?> PurchaseColPrice;

    @FXML
    private TableColumn<?, ?> PurchaseColProId;

    @FXML
    private TableColumn<?, ?> PurchaseColQuantity;

    @FXML
    private TableColumn<?, ?> PurchaseColTotal;

    @FXML
    private TableColumn<?, ?> PurchaseColType;

    @FXML
    private TableColumn<?, ?> PurchaseColUnit;

    @FXML
    private Button PurchaseCreateBtn;

    @FXML
    private AnchorPane PurchaseForm;

    @FXML
    private TextField PurchaseProductSearch;

    @FXML
    private TextField PurchaseSearchBtn;

    @FXML
    private TableView<?> PurchaseTableView;

    @FXML
    private TextField PurchaseTotalBtn;

    @FXML
    private Button ReportBtn;

    @FXML
    private AnchorPane ReportForm;

    @FXML
    private Button SaleBtn;

    @FXML
    private Button SaleCancelBtn;

    @FXML
    private TableColumn<?, ?> SaleColName;

    @FXML
    private TableColumn<?, ?> SaleColPrice;

    @FXML
    private TableColumn<?, ?> SaleColQuantity;

    @FXML
    private TableColumn<?, ?> SaleColSTT;

    @FXML
    private TableColumn<?, ?> SaleColTotal;

    @FXML
    private TableColumn<?, ?> SaleColType;

    @FXML
    private AnchorPane SaleForm;

    @FXML
    private Button SalePayBtn;

    @FXML
    private ComboBox<?> SalePaymentType;

    @FXML
    private Spinner<?> SaleQuantity;

    @FXML
    private TextField SaleSearchBtn;

    @FXML
    private TableView<?> SaleTableView;

    @FXML
    private Text SaleTotal;

    @FXML
    private Button SupplierBtn;

    @FXML
    private Button homeBtn;

    @FXML
    private Label income;

    @FXML
    private BarChart<?, ?> incomechart;

    @FXML
    private Button logoutBtn;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private Button productAddBtn;

    @FXML
    private Button productBtn;

    @FXML
    private TableColumn<Product, String> productColID;

    @FXML
    private TableColumn<Product, String> productColName;

    @FXML
    private TableColumn<Product, String> productColPurcchasePrice;

    @FXML
    private TableColumn<Product, String> productColQuantity;

    @FXML
    private TableColumn<Product, String> productColSalePrice;

    @FXML
    private TableColumn<Product, String> productColType;

    @FXML
    private TableColumn<Product, String> productColUnit;

    @FXML
    private Button productDeleteBtn;

    @FXML
    private TextField productId;

    @FXML
    private ImageView productImage;

    @FXML
    private Button productImportBtn;

    @FXML
    private TextField productName;

    @FXML
    private TextField productUnit;

    @FXML
    private TextField productPurchasePrice;

    @FXML
    private TextField productQuantity;

    @FXML
    private TextField productSalePrice;

    @FXML
    private TextField productSearchBtn;

    @FXML
    private TableView<Product> productTableView;

    @FXML
    private ComboBox<String> productType;

    @FXML
    private Button productUpdateBtn;

    @FXML
    private Button SaleAddBtn;

    @FXML
    private AreaChart<?, ?> productchart;

    @FXML
    private Label productsale;

    private Connection conn;
    private PreparedStatement pst;
    private Statement statement;
    private ResultSet rs;
    private Image image;

    public void addProductAdd(ActionEvent event) {
        String sql = "INSERT INTO product(id, type, name, unit, salePrice,purchasePrice,quantity,image,date) " +
                "VALUES(?,?,?,?,?,?,?,?,?)";
        conn = database.connect();
        try {
            Alert alert;
            if (productId.getText().isEmpty() || productName.getText().isEmpty() || productUnit.getText().isEmpty() || productSalePrice.getText().isEmpty() || productPurchasePrice.getText().isEmpty() || productQuantity.getText().isEmpty() || productType.getSelectionModel().getSelectedItem() == null || LoadData.path == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng nhập đầy đủ thông tin");
                alert.showAndWait();
            }else {
                String checkData = "SELECT id FROM product WHERE id = '"+productId.getText()+"'";
                statement = conn.createStatement();
                rs = statement.executeQuery(checkData);
                if (rs.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Mã sản phẩm đã tồn tại");
                    alert.showAndWait();
                }else{
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, productId.getText());
                    pst.setString(2, (String) productType.getSelectionModel().getSelectedItem());
                    pst.setString(3, productName.getText());
                    pst.setString(4, productUnit.getText());
                    pst.setString(5, productSalePrice.getText());
                    pst.setString(6, productPurchasePrice.getText());
                    pst.setString(7, productQuantity.getText());

                    String url = LoadData.path;
                    url = url.replace("\\", "\\\\");
                    pst.setString(8, url);
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    pst.setString(9, sqlDate.toString());
                    pst.executeUpdate();
                    addProductShowListData();
                    addProductClear();
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void addProductUpdate() {
        String url = LoadData.path;
        url = url.replace("\\", "\\\\");
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "UPDATE product SET type = '"+productType.getSelectionModel().getSelectedItem()
                +"', name = '"+productName.getText()
                +"' , salePrice = '"+productSalePrice.getText()
                +"', purchasePrice = '"+productPurchasePrice.getText()
                +"', quantity = '"+productQuantity.getText()
                +"', image = '"+url
                +"', date = '"+sqlDate+"' WHERE id = '"+productId.getText()+"'";
        conn = database.connect();
        try {
            Alert alert;
            if (productId.getText().isEmpty() || productName.getText().isEmpty() || productUnit.getText().isEmpty() || productSalePrice.getText().isEmpty() || productPurchasePrice.getText().isEmpty() || productQuantity.getText().isEmpty() || productType.getSelectionModel().getSelectedItem() == null || LoadData.path == null) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng nhập đầy đủ thông tin");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Update");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc chắn muốn cập nhật sản phẩm " + productId.getText() + "?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Cập nhật thành công");
                    alert.showAndWait();
                    addProductShowListData();
                    addProductClear();

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addProductClear() {
        productId.clear();
        productName.clear();
        productQuantity.clear();
        productSalePrice.clear();
        productPurchasePrice.clear();
        productUnit.clear();
        productType.getSelectionModel().clearSelection();
        productImage.setImage(null);
    }

    public void addProductDelete(){
        String sql = "DELETE FROM product WHERE id = '"+productId.getText()+"'";
        conn = database.connect();
        try {
            Alert alert;
            if (productId.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng chọn sản phẩm cần xóa");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc chắn muốn xóa sản phẩm " + productId.getText() + "?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Xóa thành công");
                    alert.showAndWait();
                    addProductShowListData();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addImageProduct() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File file = fileChooser.showOpenDialog(mainForm.getScene().getWindow());
        if (file != null) {
            LoadData.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 94, 94, false, true);
            productImage.setImage(image);

        }
    }

    private String[] listType = {"Bánh kẹo", "Nước Giải khát", "Sữa", "Trà Cà phê", "Thực phẩm khô", "Thực phẩm đóng hộp", "Đồ uống có cồn", "Gia vị Dầu ăn", "Khác"};
    public void AddlistType() {
        List<String> list = new ArrayList<>();
        for (String s : listType) {
            list.add(s);
        }
        ObservableList observableList = FXCollections.observableArrayList(list);
        productType.setItems(observableList);

    }

    public void addProductSearch(){
        FilteredList<Product> filteredList = new FilteredList<>(addProductsList, b -> true);
        productSearchBtn.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(product.getName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if(product.getType().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if(String.valueOf(product.getId()).contains(lowerCaseFilter)) {
                    return true;
                }else if(String.valueOf(product.getSalePrice()).contains(lowerCaseFilter)) {
                    return true;
                }else return false;
            });
        });
        SortedList<Product> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(productTableView.comparatorProperty());
        productTableView.setItems(sortedList);

    }


    public ObservableList<Product> addProductListData() {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product";
        conn = database.connect();
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                productList.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"), rs.getString("type"), rs.getDouble("purchasePrice"), rs.getDouble("salePrice"), rs.getString("unit"), rs.getString("image"), rs.getDate("date")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    };

    private ObservableList<Product> addProductsList;
    public void addProductShowListData(){      //addProductShowListData
        addProductsList = addProductListData();
        productColID.setCellValueFactory(new PropertyValueFactory<>("id"));
        productColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        productColQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        productColPurcchasePrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        productColSalePrice.setCellValueFactory(new PropertyValueFactory<>("salePrice"));
        productColUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        productTableView.setItems(addProductsList);
    }

    public void addProductSelect() {
        Product product = productTableView.getSelectionModel().getSelectedItem();
        int count = productTableView.getSelectionModel().getSelectedIndex();
        if ((count -1 ) < -1) return;
        productId.setText(String.valueOf(product.getId()));
        productName.setText(product.getName());
        productQuantity.setText(String.valueOf(product.getQuantity()));
        productSalePrice.setText(String.valueOf(product.getSalePrice()));
        productPurchasePrice.setText(String.valueOf(product.getPurchasePrice()));
        productUnit.setText(String.valueOf(product.getUnit()));

        String url = "file:" + product.getImage();
        image = new Image(url, 94, 94, false, true);
        productImage.setImage(image);
        LoadData.path = product.getImage();
    }

// Supplier
    public ObservableList<Supplier> addSupplierListData(){
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM supplier";
        conn = database.connect();
        try {
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                supplierList.add(new Supplier(rs.getInt("id"), rs.getString("name"), rs.getString("address"), rs.getString("tel")));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return supplierList;
    }

    public void addSupplierAdd(ActionEvent event){
        String sql = "INSERT INTO supplier(id, name, address, tel) " +
                "VALUES(?,?,?,?)";
        conn = database.connect();
        try {
            Alert alert;
            if (ApllierID.getText().isEmpty() || ApplierName.getText().isEmpty() || ApplierAddress.getText().isEmpty() || ApplierPhone.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng nhập đầy đủ thông tin");
                alert.showAndWait();
            }else {
                String checkData = "SELECT id FROM supplier WHERE id = '"+ApllierID.getText()+"'";
                statement = conn.createStatement();
                rs = statement.executeQuery(checkData);
                if (rs.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Mã nhà cung cấp đã tồn tại");
                    alert.showAndWait();
                }else{
                    pst = conn.prepareStatement(sql);
                    pst.setString(1, ApllierID.getText());
                    pst.setString(2, ApplierName.getText());
                    pst.setString(3, ApplierAddress.getText());
                    pst.setString(4, ApplierPhone.getText());
                    pst.executeUpdate();
                    addSupplierShowListData();
                    addProductClear();
                }

            }
    }catch (Exception e){
        e.printStackTrace();
    }
    }
    public void addSupplierUpdate(){
        String sql = "UPDATE supplier SET name = '"+ApplierName.getText()
                +"', address = '"+ApplierAddress.getText()
                +"', tel = '"+ApplierPhone.getText()
                +"' WHERE id = '"+ApllierID.getText()+"'";
        conn = database.connect();
        try {
            Alert alert;
            if (ApllierID.getText().isEmpty() || ApplierName.getText().isEmpty() || ApplierAddress.getText().isEmpty() || ApplierPhone.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng nhập đầy đủ thông tin");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Update");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc chắn muốn cập nhật nhà cung cấp " + ApllierID.getText() + "?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Cập nhật thành công");
                    alert.showAndWait();
                    addSupplierShowListData();
                    addSupplierClear();
                }
            }
    }catch (Exception e){
        e.printStackTrace();
    }
    }

    public void addSupplierClear(){
        ApllierID.clear();
        ApplierName.clear();
        ApplierAddress.clear();
        ApplierPhone.clear();
    }

    public void addSupplierDelete(){
        String sql = "DELETE FROM supplier WHERE id = '"+ApllierID.getText()+"'";
        conn = database.connect();
        try {
            Alert alert;
            if (ApllierID.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Vui lòng chọn nhà cung cấp cần xóa");
                alert.showAndWait();
            }else {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete");
                alert.setHeaderText(null);
                alert.setContentText("Bạn có chắc chắn muốn xóa nhà cung cấp " + ApllierID.getText() + "?");
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(sql);
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("Xóa thành công");
                    alert.showAndWait();
                    addSupplierShowListData();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addSupplierSearch(){
        FilteredList<Supplier> filteredList = new FilteredList<>(addSuppliersList, b -> true);
        ApplierSearchBtn.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(supplier -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(supplier.getName().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if(supplier.getAddress().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }else if(String.valueOf(supplier.getId()).contains(lowerCaseFilter)) {
                    return true;
                }else if(String.valueOf(supplier.getTel()).contains(lowerCaseFilter)) {
                    return true;
                }else return false;
            });
        });
    }

    private ObservableList<Supplier> addSuppliersList;
    public void addSupplierShowListData(){
        addSuppliersList = addSupplierListData();
        ApplierColId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ApplierColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ApplierColAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        ApplierColPhone.setCellValueFactory(new PropertyValueFactory<>("tel"));
        ApplierTableView.setItems(addSuppliersList);
    }
    public void addSupplierSelect() {
        Supplier supplier = ApplierTableView.getSelectionModel().getSelectedItem();
        int count = ApplierTableView.getSelectionModel().getSelectedIndex();
        if ((count -1 ) < -1) return;
        ApllierID.setText(String.valueOf(supplier.getId()));
        ApplierName.setText(supplier.getName());
        ApplierAddress.setText(supplier.getAddress());
        ApplierPhone.setText(supplier.getTel());
    }



    public void changeView(ActionEvent event) {
        if (event.getSource() == homeBtn) {
            DashBoardForm.setVisible(true);
            ProductForm.setVisible(false);
            SaleForm.setVisible(false);
            ApplierForm.setVisible(false);
            ReportForm.setVisible(false);
            PurchaseForm.setVisible(false);
            formAddPurchase.setVisible(false);
            homeBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #3998ef,  #5bbffb)");
            productBtn.setStyle("-fx-background-color: white");
            SaleBtn.setStyle("-fx-background-color: white");
            SupplierBtn.setStyle("-fx-background-color: white");
            PurchaseBtn.setStyle("-fx-background-color: white");
            ReportBtn.setStyle("-fx-background-color: white");


        } else if (event.getSource() == productBtn) {
            ProductForm.setVisible(true);
            DashBoardForm.setVisible(false);
            SaleForm.setVisible(false);
            ApplierForm.setVisible(false);
            ReportForm.setVisible(false);
            PurchaseForm.setVisible(false);
            formAddPurchase.setVisible(false);
            productBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #3998ef,  #5bbffb)");
            homeBtn.setStyle("-fx-background-color: white");
            SaleBtn.setStyle("-fx-background-color: white");
            SupplierBtn.setStyle("-fx-background-color: white");
            PurchaseBtn.setStyle("-fx-background-color: white");
            ReportBtn.setStyle("-fx-background-color: white");
            addProductShowListData();
            AddlistType();
            addProductSearch();


        } else if (event.getSource() == SaleBtn) {
            SaleForm.setVisible(true);
            DashBoardForm.setVisible(false);
            ProductForm.setVisible(false);
            ApplierForm.setVisible(false);
            ReportForm.setVisible(false);
            PurchaseForm.setVisible(false);
            formAddPurchase.setVisible(false);
            SaleBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #3998ef,  #5bbffb)");
            homeBtn.setStyle("-fx-background-color: white");
            productBtn.setStyle("-fx-background-color: white");
            SupplierBtn.setStyle("-fx-background-color: white");
            PurchaseBtn.setStyle("-fx-background-color: white");
            ReportBtn.setStyle("-fx-background-color: white");

        } else if (event.getSource() == SupplierBtn) {
            ApplierForm.setVisible(true);
            DashBoardForm.setVisible(false);
            ProductForm.setVisible(false);
            SaleForm.setVisible(false);
            ReportForm.setVisible(false);
            PurchaseForm.setVisible(false);
            formAddPurchase.setVisible(false);
            SupplierBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #3998ef,  #5bbffb)");
            homeBtn.setStyle("-fx-background-color: white");
            productBtn.setStyle("-fx-background-color: white");
            SaleBtn.setStyle("-fx-background-color: white");
            PurchaseBtn.setStyle("-fx-background-color: white");
            ReportBtn.setStyle("-fx-background-color: white");
            addSupplierShowListData();
            addSupplierSearch();


        } else if (event.getSource() == PurchaseBtn) {
            formAddPurchase.setVisible(true);
            DashBoardForm.setVisible(false);
            ProductForm.setVisible(false);
            SaleForm.setVisible(false);
            ApplierForm.setVisible(false);
            ReportForm.setVisible(false);
            PurchaseForm.setVisible(false);
            PurchaseBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #3998ef,  #5bbffb)");
            homeBtn.setStyle("-fx-background-color: white");
            productBtn.setStyle("-fx-background-color: white");
            SaleBtn.setStyle("-fx-background-color: white");
            SupplierBtn.setStyle("-fx-background-color: white");
            ReportBtn.setStyle("-fx-background-color: white");


        } else if (event.getSource() == ReportBtn) {
            ReportForm.setVisible(true);
            DashBoardForm.setVisible(false);
            ProductForm.setVisible(false);
            SaleForm.setVisible(false);
            ApplierForm.setVisible(false);
            PurchaseForm.setVisible(false);
            formAddPurchase.setVisible(false);
            ReportBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #3998ef,  #5bbffb)");
            homeBtn.setStyle("-fx-background-color: white");
            productBtn.setStyle("-fx-background-color: white");
            SaleBtn.setStyle("-fx-background-color: white");
            SupplierBtn.setStyle("-fx-background-color: white");
            PurchaseBtn.setStyle("-fx-background-color: white");


        } else if (event.getSource() == btnAddPurchase) {
            PurchaseForm.setVisible(true);
            ReportForm.setVisible(false);
            DashBoardForm.setVisible(false);
            ProductForm.setVisible(false);
            SaleForm.setVisible(false);
            ApplierForm.setVisible(false);
            formAddPurchase.setVisible(false);
            PurchaseBtn.setStyle("-fx-background-color: linear-gradient(to bottom, #3998ef,  #5bbffb)");
            homeBtn.setStyle("-fx-background-color: white");
            productBtn.setStyle("-fx-background-color: white");
            SaleBtn.setStyle("-fx-background-color: white");
            SupplierBtn.setStyle("-fx-background-color: white");
            ReportBtn.setStyle("-fx-background-color: white");
        
        }

    }

    private double x = 0;
    private double y = 0;

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Logout");
            alert.setHeaderText(null);
            alert.setContentText("Bạn có chắc chắn muốn đăng xuất?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get().equals(ButtonType.OK)) {
                logoutBtn.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/Login.fxml"));
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
                scene.setOnMouseReleased((MouseEvent event) -> {
                    finalStage.setOpacity(1);
                });
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            } else return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) mainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addProductShowListData();
        AddlistType();
        addProductSearch();
        addProductShowListData();

    }
}
