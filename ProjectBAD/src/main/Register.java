package main;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import database.Connect;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;


public class Register extends BorderPane {

	BorderPane mainpane;
	Scene registerScene;
	Button loginbtn;
	
	public Register() {
		// TODO Auto-generated constructor stub
		 mainpane = new BorderPane();
		 registerScene = new Scene(mainpane, 700, 600);
	
		FlowPane genderflow;
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		
		FlowPane fpbtn = new FlowPane();
		FlowPane cek = new FlowPane();
		Label namelbl = new Label("Name");
		Label usernamelbl = new Label("Username");
		Label passwordlbl = new Label("Password");
		Label confirmpasslbl = new Label("Confirm Password");
		Label emaillbl = new Label("Email");
		Label phonelbl = new Label("PhoneNumber");
		Label genderlbl = new Label("Gender");
		Label agrelbl = new Label("I agree with Term and Condition");
		
		RadioButton maleRadio,femaleRadio;
		ToggleGroup gendergroup;
		
		TextField namefld = new TextField();
		TextField usernamefld = new TextField();
		TextField emailfld = new TextField();
		TextField phonefld = new TextField();
		PasswordField passfld = new PasswordField();
		PasswordField confirmfld = new PasswordField();
		
		CheckBox agrecek = new CheckBox();
		
		 loginbtn = new Button("Login");
		Button registerbtn = new Button("Register");
		
		maleRadio = new RadioButton("Male");
		femaleRadio = new RadioButton("Female");
		gendergroup = new ToggleGroup();
		genderflow = new FlowPane();
		
		maleRadio.setToggleGroup(gendergroup);
		femaleRadio.setToggleGroup(gendergroup);
		
		genderflow.getChildren().add(maleRadio);
		genderflow.getChildren().add(femaleRadio);
		
		registerbtn.setOnAction((event) ->{
			
			String name = namefld.getText();
			String username = usernamefld.getText();
			
			String password = passfld.getText();
			String confirmpassword = confirmfld.getText();
			String email = emailfld.getText();
			String phonenumber = phonefld.getText();
		
			if(name.length()<5 || name.length()>20) {
				
				Alert alert = new Alert(AlertType.WARNING);
				//error,warning, none, confirmation
				
				alert.setContentText("Name must be between 5 to 20 word!");
				alert.setHeaderText("Warning");
				System.out.println(alert.getResult());
				alert.show();
			} else if(username.length()<3 || username.length()>10) {
				Alert alert = new Alert(AlertType.WARNING);
				//error,warning, none, confirmation
				
				alert.setContentText("Username must be between 3 to 10 word!");
				alert.setHeaderText("Warning");
				System.out.println(alert.getResult());
				alert.show();
			}else if(password.length()<3 || password.length()>10) {
				Alert alert = new Alert(AlertType.WARNING);
				//error,warning, none, confirmation
				
				alert.setContentText("password must be between 3 to 10 word!");
				alert.setHeaderText("Warning");
				System.out.println(alert.getResult());
				alert.show();
			}else if(!confirmpassword.equals(password)) {
				Alert alert = new Alert(AlertType.WARNING);
				//error,warning, none, confirmation
				
				alert.setContentText("Password doesn't match!");
				alert.setHeaderText("Warning");
				System.out.println(alert.getResult());
				alert.show();
			}else if(!email.endsWith("@gmail.com")) {
				Alert alert = new Alert(AlertType.WARNING);
				//error,warning, none, confirmation
				
				alert.setContentText("Email must contain 1@, unique, and ends with @gmail.com");
				alert.setHeaderText("Warning");
				System.out.println(alert.getResult());
				alert.show();
			}else if(phonenumber.length() == 10 && !phonenumber.startsWith("+62")) {
				Alert alert = new Alert(AlertType.WARNING);
				//error,warning, none, confirmation
				
				alert.setContentText("Phonenumber must start with +62 and 10 ");
				alert.setHeaderText("Warning");
				System.out.println(alert.getResult());
				alert.show();
			}else if(gendergroup.getSelectedToggle()== null) {
				Alert alert = new Alert(AlertType.WARNING);
				//error,warning, none, confirmation
				
				alert.setContentText("You must choose the gender");
				alert.setHeaderText("Warning");
				System.out.println(alert.getResult());
				alert.show();
			}else if (!agrecek.isSelected()) {
				Alert alert = new Alert(AlertType.WARNING);
				//error,warning, none, confirmation
				
				alert.setContentText("You must aggree with the aggreement");
				alert.setHeaderText("Warning");
				System.out.println(alert.getResult());
				alert.show();
			}else {
				String nama = namefld.getText();
				String user = usernamefld.getText();
				String pass = passfld.getText(); 
				String confirmpass= confirmfld.getText();
				String emails = emailfld.getText();
				String phonenumb = phonefld.getText();
				String gender="";
				String id = "";
				String roles = "user";
				Random ran = new Random();
			;
				int a = ran.nextInt(10);
				int b = ran.nextInt(10);
				int c = ran.nextInt(10);
				int d = ran.nextInt(10);
				id = "U"+a+b+d+c;
				if (femaleRadio.isSelected()) {
					gender = "female";
				}
				else if (maleRadio.isSelected()) {
					gender = "male";
				}
				
					String query = String.format("INSERT INTO users VALUES('%s', '%s','%s','%s','%s','%s','%s','%s')",id, nama,roles, user,pass,emails,phonenumb,gender);
					Connect connect = Connect.getConnection();
					
					Integer result = connect.executeUpdate(query);
					
					
					if(result!= null ) {
				
					Alert	information = new Alert(Alert.AlertType.INFORMATION);
					information.setContentText("succesfully registered an account");
					information.setAlertType(Alert.AlertType.INFORMATION);
					information.show();
					namefld.setText("");
					passfld.setText("");
					confirmfld.setText("");
					usernamefld.setText("");
					emailfld.setText("");
					phonefld.setText("");
					agrecek.setSelected(false);
					maleRadio.setSelected(false);
					femaleRadio.setSelected(false);
						
					}
					else {
						Alert alert = new Alert(AlertType.WARNING);
						//error,warning, none, confirmation
						
						alert.setContentText("Registration failed!!");
						alert.setHeaderText("Warning");
						System.out.println(alert.getResult());
					}
					
			}
			
			}
		
		
		);
		
		cek.getChildren().addAll(agrecek,agrelbl);
		cek.setHgap(10);
		genderflow.setHgap(10);
		mainpane.setCenter(gp);
		mainpane.setBottom(fpbtn);
		mainpane.setPadding(new Insets(100));
		
		fpbtn.getChildren().addAll(loginbtn,registerbtn);
		fpbtn.setAlignment(Pos.BOTTOM_RIGHT);
		fpbtn.setHgap(10);
		fpbtn.setPadding(new Insets(10));
		
		gp.add(namelbl, 0, 0);
		gp.add(namefld, 0, 1);
		gp.add(usernamelbl, 0, 2);
		gp.add(usernamefld, 0, 3);
		gp.add(passwordlbl, 0,4 );
		gp.add(passfld, 0,5 );
		gp.add(confirmpasslbl, 0,6 );
		gp.add(confirmfld, 0,7 );
		gp.add(emaillbl, 0,8 );
		gp.add(emailfld, 0,9 );
		gp.add(phonelbl, 0,10 );
		gp.add(phonefld, 0,11 );
		gp.add(genderlbl, 0,12);
		gp.add(genderflow, 0,13 );
		gp.add(cek, 0,14 );
	
		gp.setHgap(20);
		gp.setVgap(5);
		
		
	}

}
