package main;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;



import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.Cartlist;
import model.Product;



public class Productlist extends BorderPane{
	
	TableView<Product> tableproduct = new TableView<>();
	TableView<Cartlist> cart = new TableView<>();
	Vector<Cartlist> carts;
	Button add;
	BorderPane mainpane;
	Scene listscene;
	ScrollPane sp;
	Spinner<Integer> sn;
	MenuItem item3,item2,item1;
	Vector<Product> product;
	Connect connect = Connect.getConnection();
	
	public Productlist(String id1) {
	
		carts = new Vector<Cartlist>();
		product =  new Vector<Product>();
		mainpane = new BorderPane();
		GridPane gp = new GridPane();
		FlowPane fp = new FlowPane();
		gp.setAlignment(Pos.BASELINE_LEFT);
		fp.setAlignment(Pos.TOP_CENTER);
		listscene = new Scene(mainpane,700,600);
		sp = new ScrollPane();
		
		MenuBar menubar = new MenuBar();
		Menu menu1 = new Menu("Menu");
		Menu menu2 = new Menu("Account");
		
		item1 = new MenuItem("Product List");
		item2 = new MenuItem("Cart");
		item3 = new MenuItem("Logout");
		
		
		menubar.getMenus().addAll(menu1,menu2);
		menu1.getItems().addAll(item1,item2);

		menu2.getItems().addAll(item3);
		
		
		Label productlbl = new Label("Product ID");
		Label qtylbl = new Label("Qty");
		
		TextField producfld = new TextField();
		sn = new Spinner<>(1,99,0);

		add =  new Button("Add To Cart");
		
		
		gp.add(productlbl, 0, 0);
		gp.add(producfld, 0, 1);
		gp.add(qtylbl, 0, 2);
		gp.add(sn, 0, 3);
		gp.add(add, 0, 5);
	
		mainpane.setBottom(gp);
		
		gp.setHgap(6);
		gp.setVgap(6);
		gp.setPadding(new Insets(15));
		
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
				product.add(new Product(id, type, brand,price, stock));
			}
		} catch (Exception e) {
			
		}
		
		ObservableList<Product> pd = FXCollections.observableArrayList(product);
		tableproduct.setItems(pd);
		
		
		
	tableproduct.getColumns().addAll(col1,col2,col3,col4,col5);
	
	
	
	
	add.setOnAction(e->{
		
		
		
		Connect connect = Connect.getConnection();

		// param 1 itu nanya tanda tanyanya yang keberapa
		if (producfld.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("All field must be filled!");
			alert.show();	
		}else if(sn.getValue()==0){
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("All field must be filled!");
			alert.show();
		}else {
			String pdid = producfld.getText();
			Integer qtys = sn.getValue();
	
			String query1 = String.format("INSERT INTO carts VALUES('%s', '%s','%s')",id1,pdid,qtys);
			Integer result = connect.executeUpdate(query1);
			
			
			if(result!= null ) {
				
				Alert	information = new Alert(Alert.AlertType.INFORMATION);
				information.setContentText("succesfully registered an account");
				information.setAlertType(Alert.AlertType.INFORMATION);
				information.show();	
				
			ObservableList<Cartlist> jp = FXCollections.observableArrayList(carts);
			cart.setItems(jp);
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
	
	

	mainpane.setTop(menubar);
	
	fp.getChildren().add(tableproduct);
	fp.setAlignment(Pos.CENTER_LEFT);
	sp.setContent(tableproduct);
	mainpane.setCenter(fp);
	
	}
	
	public void additem(String id,String type, String brand, Integer price, Integer qty) {
		tableproduct.getItems().add(new Product(id,type,brand,price,qty));
	}
	
	
}
