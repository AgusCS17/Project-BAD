package main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.Connect;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Cartlist;
import model.User;

public class Main extends Application{
	
	
	Scene mainScene;
	FlowPane fpbtn;
	Connect connect = Connect.getConnection();
	Cart cart;
	TableView<Cartlist> carts = new TableView<>();
	Vector<Cartlist> car;

	Productlist productlist;
	public Vector<User> userList;
	public boolean isLogin = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		fpbtn = new FlowPane();
		car = new Vector<Cartlist>();
		userList = new Vector<>();
		
		BorderPane mainpane = new BorderPane();
		Register register = new Register();
		Userpage userpage = new Userpage();
		Adminpage adminpage = new Adminpage();
		
		Manageproduct manage = new Manageproduct();
		
		mainScene = new Scene(mainpane,500,500);
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(10.0);
		gp.setHgap(10.0);
		
	Label emaillbl = new Label("Email");
	Label passwordlbl = new Label("Password");
	
	
	TextField emailfld = new TextField();
	PasswordField passwordfld = new PasswordField();
	
	//get email value 
	
	
	Button loginbtn = new Button("Login");
	Button registerbtn = new Button("Register");
	//bikin event
	
loginbtn.setOnAction((event) ->{
		
		String id1 = connect.getIdUsers(emailfld.getText(), passwordfld.getText());
		String query =
				"SELECT * FROM users WHERE EMAIL  = ? AND PASSWORD = ?  ";
		
		cart = new Cart(id1);
		productlist = new Productlist(id1);
		PreparedStatement ps = connect.prepare(query);				
			// param 1 itu nanya tanda tanyanya yang keberapa
		if (emailfld.getText().equals("") && passwordfld.getText().equals("")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setContentText("All field must be filled!");
			alert.show();
		}else {
		try {
			System.out.println(emailfld.getText());						
			ps.setString(1, emailfld.getText());
			ps.setString(2, passwordfld.getText());
			ResultSet res = ps.executeQuery();
			if(res.next()) {
				String roles = res.getString("role");
				if (roles.equalsIgnoreCase("user")){
					isLogin = true;
					String id = res.getString("id");
					stage.setScene(userpage.userScene);				
					
				}
				if (roles.equalsIgnoreCase("admin")) {
					stage.setScene(adminpage.adminScene);
				}
								
			}else {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setContentText("Account doesn't match!");
					alert.show();
				}		
			// buat ngambil data dari databas
			while(res.next()) {

				emailfld.setText("");
				passwordfld.setText("");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			
		}
		}
		cart.item1.setOnAction(e->{
			stage.setTitle("Main Menu");
			stage.setScene(productlist.listscene);
		});
		
		cart.item3.setOnAction(e->{
			stage.setTitle("Main Menu");
			stage.setScene(mainScene);
		});

		productlist.item2.setOnAction(e->{
			stage.setTitle("Main Menu");
			ObservableList<Cartlist> jp = FXCollections.observableArrayList(car);
			carts.setItems(jp);	
			stage.setScene(cart.cartpage);
			
		});
			
		productlist.item3.setOnAction(e->{
			stage.setTitle("Main Menu");
			stage.setScene(mainScene);
		});
		
	});

	
			
	
	registerbtn.setOnAction((e) ->{
		stage.setTitle("Register");
		stage.setScene(register.registerScene);
	});
	
	fpbtn.getChildren().addAll(loginbtn,registerbtn);
	fpbtn.setAlignment(Pos.BOTTOM_RIGHT);
	fpbtn.setHgap(10);
	fpbtn.setPadding(new Insets(10));
	
	gp.add(emaillbl, 0, 0);
	gp.add(emailfld, 0, 1);
	gp.add(passwordlbl, 0, 2);
	gp.add(passwordfld, 0, 3);
	
	mainpane.setCenter(gp);
	mainpane.setBottom(fpbtn);
	mainpane.setPadding(new Insets(170));
	
	gp.setAlignment(Pos.CENTER);
	gp.setHgap(10);
	gp.setVgap(10);
	
	register.loginbtn.setOnAction(e->{
		
		stage.setScene(mainScene);
	});
	
	userpage.item3.setOnAction(e->{
		stage.setTitle("Main Menu");
		stage.setScene(mainScene);
	});
	
	adminpage.item2.setOnAction(e->{
		stage.setTitle("Main Menu");
		stage.setScene(mainScene);
	});
	
	userpage.item1.setOnAction(e->{
		stage.setTitle("Main Menu");
		stage.setScene(productlist.listscene);
	});
	
	userpage.item2.setOnAction(e->{
		stage.setTitle("Main Menu");
		stage.setScene(cart.cartpage);
	});
	
	manage.item2.setOnAction(e->{
		stage.setTitle("Main Menu");
		stage.setScene(manage.manageScene);
	});
	
	
	adminpage.item1.setOnAction(e->{
		stage.setTitle("Main Menu");
		stage.setScene(manage.manageScene);
	});
	
	
	
		stage.setScene(mainScene);
		stage.setTitle("Login");
		stage.show();
		
	}

}
