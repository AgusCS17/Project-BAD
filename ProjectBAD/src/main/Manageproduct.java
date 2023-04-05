package main;

import java.util.Random;
import java.util.Vector;

import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.Product;


public class Manageproduct extends BorderPane {
	
	TableView<Product> tablemanage = new TableView<>();
	
	
	Button insert,update,remove,add;
	BorderPane mainpane;
	GridPane gp,gpbtn,gpspinner;
	FlowPane fp;
	Scene manageScene;
	ScrollPane sp;
	Spinner<Integer> sprice;
	Spinner<Integer> sstock,sn;
	Label product,brand,type,price,stock;
	ComboBox<String> ctype;
	TextField producttxt, brandtxt;
	MenuItem item3,item2,item1;
	Vector<Product> manage;
	Connect connect = Connect.getConnection();
	public Manageproduct() {
		manage =  new Vector<Product>();
		mainpane = new BorderPane();
		gp = new GridPane();
		gpspinner = new GridPane();
		fp = new FlowPane();
		gp.setAlignment(Pos.BASELINE_LEFT);
		fp.setAlignment(Pos.TOP_CENTER);
		manageScene = new Scene(mainpane,700,700);
		sp = new ScrollPane();
		
		MenuBar menubar = new MenuBar();
		Menu menu1 = new Menu("Menu");
		Menu menu2 = new Menu("Account");
		
		item1 = new MenuItem("Manage Product");
		item2 = new MenuItem("Logout");
	
		
		
		menubar.getMenus().addAll(menu1,menu2);
		menu1.getItems().addAll(item1);

		menu2.getItems().addAll(item2);
		
		product = new Label("Product ID");
		brand = new Label("Brand");
		type = new Label("Type");
		price = new Label("Price");
		stock = new Label("Stock");
		
		sstock = new Spinner<>(1,50,1);
		sprice = new Spinner<>(0,10000,0);
		
		producttxt = new TextField();
		brandtxt = new TextField();
		
		ctype = new ComboBox<String>();
		ctype.getItems().add("");
		ctype.getItems().add("Sanitary");
		ctype.getItems().add("Drink");
		ctype.getItems().add("Food");
		ctype.getSelectionModel().selectFirst();
		
		insert = new Button("Insert");
		update = new Button("Update");
		remove = new Button("Remove");
		
		
		
		gp.add(product, 0, 0);
		gp.add(producttxt, 0, 1);
		gp.add(brand, 0, 2);
		gp.add(brandtxt, 0, 3);
		gp.add(type, 0, 4);
		gp.add(ctype, 0, 5);
		gp.add(price, 0, 6);
		gp.add(sprice, 0, 7);
		gp.add(stock, 1, 6);
		gp.add(sstock, 1, 7);
		gp.add(insert, 2, 0);
		gp.add(update, 2, 2);
		gp.add(remove, 2, 4);
		
		gp.setHgap(10);
		gp.setVgap(3);
		gp.setPadding(new Insets(5));
		
		TableColumn<Product, String> col1 = new TableColumn<>("ID");
		TableColumn<Product, String> col2 = new TableColumn<>("Type");
		TableColumn<Product, String> col3 = new TableColumn<>("Brand");
		TableColumn<Product, Integer> col4 = new TableColumn<>("Price");
		TableColumn<Product, Integer> col5 = new TableColumn<>("Stock");
		
		col1.setCellValueFactory(new PropertyValueFactory<>("id"));
		col2.setCellValueFactory(new PropertyValueFactory<>("type"));
		col3.setCellValueFactory(new PropertyValueFactory<>("brand"));
		col4.setCellValueFactory(new PropertyValueFactory<>("price"));
		col5.setCellValueFactory(new PropertyValueFactory<>("qty"));
		
		col1.setMinWidth(500/6);
		col2.setMinWidth(500/6);
		col3.setMinWidth(500/6);
		col4.setMinWidth(500/6);
		col5.setMinWidth(500/6);
		
		String query = "SELECT * FROM products";
		
		
		connect.rs = connect.executeQuery(query);
		try {
			while(connect.rs.next()) {
				String id = connect.rs.getString("id");
				String type = connect.rs.getString("type");
				String brand = connect.rs.getString("brand");
				Integer price = connect.rs.getInt("price");
				Integer stock = connect.rs.getInt("stock");
				manage.add(new Product(id, type, brand,price, stock));
			}
		} catch (Exception e) {
			
		}
		
		ObservableList<Product> pd = FXCollections.observableArrayList(manage);
		tablemanage.setItems(pd);
		
		tablemanage.getColumns().addAll(col1,col2,col3,col4,col5);
	
	
		insert.setOnAction(e->{
	        if (brandtxt.getText().equals("")) {
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.setContentText("All field must be filled / choose!");
	            alert.show();
	        } else if (ctype.getSelectionModel().getSelectedIndex()== 0) {
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.setContentText("Product type field must be choose!");
	            alert.show();

	        }
	        else { 
	        String producttext = producttxt.getText();
	        String brandtext = brandtxt.getText();
	        Integer stock = sstock.getValue();
	        Integer price = sprice.getValue();
	        String type = ctype.getSelectionModel().getSelectedItem();

	        Random ran = new Random();
	        String id = "";
	        int a = ran.nextInt(10);
	        int b = ran.nextInt(10);
	        int c = ran.nextInt(10);
	        int d = ran.nextInt(10);
	        id = "Y"+a+b+d+c;

	        String queryy = String.format("INSERT INTO products VALUES('%s', '%s','%s','%s','%s')", id, type, brandtext, price,stock);
	        Connect connect = Connect.getConnection();

	        Integer result = connect.executeUpdate(queryy);


	        if(result!= null ) {

	        Alert    information = new Alert(Alert.AlertType.INFORMATION);
	        information.setContentText("succesfully registered an account");
	        information.setAlertType(Alert.AlertType.INFORMATION);
	        information.show();

	        }
	        else {
	            Alert alert = new Alert(AlertType.WARNING);
	            //error,warning, none, confirmation

	            alert.setContentText("Registration failed!!");
	            alert.setHeaderText("Warning");
	            System.out.println(alert.getResult());
	        }
	        }

	    });
	update.setOnAction((event) -> {
	        if (brandtxt.getText().equals("")) {
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.setContentText("All field must be filled / choose!");
	            alert.show();
	        } else if (ctype.getSelectionModel().getSelectedIndex()== 0) {
	            Alert alert = new Alert(AlertType.WARNING);
	            alert.setContentText("Product type field must be choose!");
	            alert.show();

	        String producttext = producttxt.getText();
	        String brandtext = brandtxt.getText();
	        Integer stock = sstock.getValue();
	        Integer price = sprice.getValue();
	        String type = ctype.getSelectionModel().getSelectedItem();


	        String queri = String.format("UPDATE products SET type ='%s', brand ='%s', price ='%s', stock ='%s' WHERE id = '%s'", 
	                type, brandtext, price,stock, producttxt.getText());

	        connect.executeUpdate(queri);
	        }
	        });
	
	remove.setOnAction(e -> {
        String producttext = producttxt.getText();
        String brandtext = brandtxt.getText();
        Integer stock = sstock.getValue();
        Integer price = sprice.getValue();
        String type = ctype.getSelectionModel().getSelectedItem();

        String quer1 = String.format("DELETE FROM products WHERE id = "+ "'"+ producttxt.getText()+"'");
        connect.executeUpdate(quer1);

    });
		
	tablemanage.setOnMouseClicked((event) -> {
		TableSelectionModel<Product> tableSelectionModel = tablemanage.getSelectionModel();
		tableSelectionModel.setSelectionMode(SelectionMode.SINGLE);
		Product regis = tableSelectionModel.getSelectedItem();
		int rowindex = tablemanage.getSelectionModel().getSelectedIndex();
		producttxt.setText(regis.getId());
		brandtxt.setText(regis.getBrand());
		ctype.getSelectionModel().select(tablemanage.getItems().get(rowindex).getType());
		
		
	});
	
	
	mainpane.setTop(menubar);
	mainpane.setBottom(gp);
	fp.getChildren().add(tablemanage);
	fp.setAlignment(Pos.CENTER_LEFT);
	sp.setContent(tablemanage);
	mainpane.setCenter(fp);
	
	}
	
	public void additem(String id,String type, String brand, Integer price, Integer qty) {
		tablemanage.getItems().add(new Product(id,type,brand,price,qty));
	}
	
}
