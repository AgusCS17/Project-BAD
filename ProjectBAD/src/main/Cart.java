package main;

import java.util.Vector;

import database.Connect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.Cartlist;
import model.Product;


public class Cart extends BorderPane{
	
	Button remove, checkout;
	Spinner<Integer> sn;
	BorderPane mainpane;
	Scene cartpage;
	MenuItem item3,item2,item1;

	ScrollPane sp;
	FlowPane fp;	
	TableView<Cartlist> cart = new TableView<>();
	Vector<Cartlist> carts;
	Connect connect = Connect.getConnection();

	
	
	public Cart(String id1) {
		
		carts = new Vector<Cartlist>();
		sp = new ScrollPane();
		fp = new FlowPane();
		mainpane = new BorderPane();
		GridPane gp = new GridPane();
		cartpage = new Scene(mainpane,600,650);
		MenuBar menubar = new MenuBar();
		Menu menu1 = new Menu("Menu");
		Menu menu2 = new Menu("Account");
	
		item1 = new MenuItem("Product List");
		item2 = new MenuItem("Cart");
		item3 = new MenuItem("Logout");
		
		
		menubar.getMenus().addAll(menu1,menu2);
		menu1.getItems().addAll(item1,item2);

		menu2.getItems().addAll(item3);
		
		
		TableColumn<Cartlist, String> col1 = new TableColumn<>("Product Id");
		TableColumn<Cartlist, String> col2 = new TableColumn<>("brand");
		TableColumn<Cartlist, Integer> col3 = new TableColumn<>("qty");
		TableColumn<Cartlist, Integer> col4 = new TableColumn<>("Price");
		TableColumn<Cartlist, Integer> col5 = new TableColumn<>("Total ");
		
		col1.setCellValueFactory(new PropertyValueFactory<>("productid"));
		col2.setCellValueFactory(new PropertyValueFactory<>("brand"));
		col3.setCellValueFactory(new PropertyValueFactory<>("qty"));
		col4.setCellValueFactory(new PropertyValueFactory<>("price"));
		col5.setCellValueFactory(new PropertyValueFactory<>("total"));
		
		col1.setMinWidth(500/6);
		col2.setMinWidth(500/6);
		col3.setMinWidth(500/6);
		col4.setMinWidth(500/6);
		col5.setMinWidth(500/6);
		
		cart.getColumns().addAll(col1,col2,col3,col4,col5);
		
		String query = "SELECT CT.productID,PD.brand,SUM(CT.qty)AS 'qty' ,PD.price,PD.price*CT.qty AS 'Total Price' FROM carts CT JOIN users US ON CT.userID = US.id JOIN products PD "
					+"ON CT.productID = PD.id WHERE US.id = " + "'"+id1+"'"+"GROUP BY CT.productID, PD.brand";
					
		connect.rs = connect.executeQuery(query);
		try {
			while(connect.rs.next()) {
			String productid = connect.rs.getString("productID");
			String brand = connect.rs.getString("brand");
			Integer qty = connect.rs.getInt("qty");
			Integer price = connect.rs.getInt("price");
			Integer total = connect.rs.getInt("Total Price");
			carts.add(new Cartlist(productid, brand, qty, price, total));
							}
			} catch (Exception e) {
						
						}
						
		ObservableList<Cartlist> jp = FXCollections.observableArrayList(carts);
		cart.setItems(jp);	
		
	
		
		Label productlbl = new Label("Product ID");
		Label qtylbl = new Label("Qty");
		Label total = new Label("Total Price: ");
		TextField producfld = new TextField();
		TextField totalfld = new TextField();
		sn = new Spinner<>(1,20,1);
		
		

		remove =  new Button("Remove from the cart");
		checkout = new Button("Checkout");
	
		remove.setOnAction(e->{

			String query2 = "DELETE FROM carts WHERE productID ="+ "'"+producfld.getText()+"'";
			connect.executeUpdate(query2);
			
			ObservableList<Cartlist> pd = FXCollections.observableArrayList(carts);
			cart.setItems(pd);	
		});
		
		checkout.setOnAction(e->{
			String query3 = "DELETE FROM carts WHERE userID =" + "'"+id1+"'";
			connect.executeUpdate(query3);
		});
		
		gp.add(productlbl, 0, 1);
		gp.add(producfld, 0, 2);
		gp.add(qtylbl, 0, 3);
		gp.add(sn, 0, 4);
		gp.add(remove, 0, 5);
		gp.add(checkout, 0, 6);
		
		gp.setHgap(6);
		gp.setVgap(6);
		gp.setPadding(new Insets(25));
		sp.setContent(cart);
		fp.getChildren().add(cart);
		
		mainpane.setCenter(fp);
		mainpane.setTop(menubar);
		mainpane.setBottom(gp);
	}
	
	public void setItems(ObservableList<TableView<Cartlist>> jp) {
		

		
	}
	
}
