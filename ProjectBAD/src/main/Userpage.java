package main;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class Userpage extends BorderPane {
	
	
	BorderPane mainpane;
	Scene userScene;
	MenuItem item3,item2,item1;
	
	public Userpage() {
		 mainpane = new BorderPane();
		 userScene = new Scene(mainpane, 700, 600);
		 MenuBar menubar = new MenuBar();
			Menu menu1 = new Menu("Menu");
			Menu menu2 = new Menu("Account");
		
			item1 = new MenuItem("Product List");
			item2 = new MenuItem("Cart");
			item3 = new MenuItem("Logout");
			
			
			menubar.getMenus().addAll(menu1,menu2);
			menu1.getItems().addAll(item1,item2);

			menu2.getItems().addAll(item3);
			
			
			mainpane.setTop(menubar);
	}
   
}
