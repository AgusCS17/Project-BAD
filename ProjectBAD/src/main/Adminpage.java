package main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import model.Product;

public class Adminpage extends BorderPane{


	
	
	Button insert,update,remove;
	BorderPane mainpane;
	GridPane gp;
	FlowPane fp;
	Scene adminScene;
	ScrollPane sp;
	Spinner<Integer> sprise;
	Spinner<Integer> sstock;
	Label product,brand,type,price,stock;
	ComboBox<String> ctype;
	MenuItem item3,item2,item1;
	
	
	public Adminpage() {
		mainpane = new BorderPane();
		adminScene = new Scene(mainpane,700,600);
		
		MenuBar menubar = new MenuBar();
		Menu menu1 = new Menu("Menu");
		Menu menu2 = new Menu("Account");
		
		item1 = new MenuItem("Manage Product");
		item2 = new MenuItem("Logout");
		
		
		menubar.getMenus().addAll(menu1,menu2);
		menu1.getItems().addAll(item1);

		menu2.getItems().addAll(item2);
		
		
		mainpane.setTop(menubar);
	
	

	
	}

}
