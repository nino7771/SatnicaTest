package mojaSatnica;
	

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.io.PrintWriter;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends Application {
/**
 * 
 * Author Sinisa Babic 
	 * 
	 */

Label lblImeIPrezime;
TextField txtImeIPrezime;
Label lblObjekat;
TextField txtObjekat;
Label lblDatum;
TextField txtDatum;
Label lblSatiDolazak;
TextField txtSatiDolazak;
Label lblMinutiDolazak;
TextField txtMinutiDolazak;
Label lblSatiOdlazak;
TextField txtSatiOdlazak;
Label lblMinutiOdlazak;
TextField txtMinutiOdlazak;
Label lblBonus;
TextField txtBonus;
Label lblNormalniKoeficijent;
TextField txtNormalniKoeficijent;
Label lblRedovniSati;
TextField txtRedovniSati;
Label lblVanredniSati;
Label lblExtraKoeficijent;
TextField txtExtraKoeficijent;
TextField txtVanredniSati;
Button btnCalculate;
Button btnSave;
Button btnLoad;
Button btnOpen;
Button btnRemove;
Button btnClear;
Button btnUpdate;
TableView<Model> table;
TableColumn<Model,String> col1;
TableColumn<Model,String> col2;
TableColumn<Model,Integer> col3;
TableColumn<Model,Integer> col4;
TableColumn<Model,Integer> col5;
TableColumn<Model,Integer> col6;
TableColumn<Model,Integer> col7; 
TableColumn<Model,Integer> col8;
TableColumn<Model,Double> col9;
Label lblTotalSati;
TextField txtTotalSati;
Label lblTotalMinuti;
TextField txtTotalMinuti;
Label lblTotalBonus;
TextField txtTotalBonus;
Label lblPlata;
TextField txtPlata;
FileChooser fileChooser=new FileChooser();     
private Desktop desktop = Desktop.getDesktop();
ObservableList<Model> list = null ;				//upotrebljava se u metodu saveButton
String [] niz;									//upotrebljava se u metodu loadButton
String[] niz2;									//upotrebljava se u metodu setup						
String str = null;								//upotrebljava se u metodu loadButton
String reg="\\s+";								//upotrebljava se u metodu loadButton
String test;									//upotrebljava se u metodu setup
BufferedReader bf;								//upotrebljava se u metodu setup i loadbButton
@Override
public void start(Stage primaryStage) {
	try {
//////--------------------------------TOP PANEL------------------------------------------------------------------------/////
		
		HBox topPanel=new HBox();
		lblImeIPrezime=new Label("Ime i prezime: ");
		txtImeIPrezime=new TextField();
		txtImeIPrezime.setPrefWidth(100);
		txtImeIPrezime.setFocusTraversable(true);
		txtImeIPrezime.setOnKeyPressed(e->{
			if(txtImeIPrezime.isFocused() && e.getCode()==KeyCode.RIGHT)
				txtObjekat.requestFocus();
			
		});
		
		lblObjekat=new Label("Objekat: ");
		txtObjekat=new TextField();
		txtObjekat.setPrefWidth(100);
		txtObjekat.setOnKeyPressed(e->{
			if(txtObjekat.isFocused() && e.getCode()==KeyCode.LEFT)
				txtImeIPrezime.requestFocus();
			else if (txtObjekat.isFocused() && e.getCode()==KeyCode.RIGHT)
				txtDatum.requestFocus();
		});
		
		lblDatum=new Label("Datum: ");
		txtDatum=new TextField();
		txtDatum.setPrefWidth(80);
		txtDatum.setOnKeyPressed(e->{
			if(txtDatum.isFocused() && e.getCode()==KeyCode.LEFT)
				txtObjekat.requestFocus();
			else if (txtDatum.isFocused() && e.getCode()==KeyCode.RIGHT)
				txtSatiDolazak.requestFocus();
		});
		
		lblSatiDolazak=new Label("Dolazak sati: ");
		txtSatiDolazak=new TextField();
		txtSatiDolazak.setPrefWidth(40);
		txtSatiDolazak.setOnKeyPressed(e->{
			if(txtSatiDolazak.isFocused() && e.getCode()==KeyCode.LEFT)
				txtDatum.requestFocus();
			else if (txtSatiDolazak.isFocused() && e.getCode()==KeyCode.RIGHT)
				txtMinutiDolazak.requestFocus();
		});
		
		lblMinutiDolazak=new Label("Minuti dolazak: ");
		txtMinutiDolazak=new TextField();
		txtMinutiDolazak.setPrefWidth(40);
		txtMinutiDolazak.setFocusTraversable(false);
		txtMinutiDolazak.setOnKeyPressed(e->{
			if(txtMinutiDolazak.isFocused() && e.getCode()==KeyCode.LEFT)
				txtSatiDolazak.requestFocus();
			else if (txtMinutiDolazak.isFocused() && e.getCode()==KeyCode.RIGHT)
				txtSatiOdlazak.requestFocus();
				
			e.consume();
			
		});
		
		lblSatiOdlazak=new Label("Odlazak sati: ");
		txtSatiOdlazak=new TextField();
		txtSatiOdlazak.setPrefWidth(40);
		txtSatiOdlazak.setFocusTraversable(false);
		txtSatiOdlazak.setOnKeyPressed(e->{
			if(txtSatiOdlazak.isFocused() && e.getCode()==KeyCode.LEFT)
				txtMinutiDolazak.requestFocus();
			else if (txtSatiOdlazak.isFocused() && e.getCode()==KeyCode.RIGHT)
				txtMinutiOdlazak.requestFocus();
			
		});
		
		lblMinutiOdlazak=new Label("Odlazak minuti: ");
		txtMinutiOdlazak=new TextField();
		txtMinutiOdlazak.setPrefWidth(40);
		txtMinutiOdlazak.setFocusTraversable(false);
		txtMinutiOdlazak.setOnKeyPressed(e->{
			if(txtMinutiOdlazak.isFocused() && e.getCode()==KeyCode.LEFT)
				txtSatiOdlazak.requestFocus();
			else if(txtMinutiOdlazak.isFocused()&&e.getCode()==KeyCode.RIGHT)
				txtBonus.requestFocus();
		
				
		});
		
		lblBonus=new Label("Bonus: ");
		txtBonus=new TextField("0.00");
		txtBonus.setPrefWidth(60);
		txtBonus.setOnKeyPressed(e->{
			if(txtBonus.isFocused() && e.getCode()==KeyCode.LEFT)
				txtMinutiOdlazak.requestFocus();
			else if(txtBonus.isFocused()&&e.getCode()==KeyCode.RIGHT)
				txtNormalniKoeficijent.requestFocus();
		
				
		});
		
		topPanel.setPadding(new Insets(10,10,10,10));
		topPanel.setSpacing(8);
		topPanel.setStyle("-fx-background-color:#A9A9A9");
		topPanel.getChildren().addAll(lblImeIPrezime,txtImeIPrezime,lblObjekat,txtObjekat,lblDatum,txtDatum,
				lblSatiDolazak,txtSatiDolazak,lblMinutiDolazak,txtMinutiDolazak,lblSatiOdlazak,
				txtSatiOdlazak,lblMinutiOdlazak,txtMinutiOdlazak,lblBonus,txtBonus);
		
//////--------------------------------TABLE-----------------------------------------------------------//////////////////		
		table=new TableView<>();
		
		col1=new TableColumn<>("Objekat");
		col1.setCellValueFactory(new PropertyValueFactory<>("objekat"));
		col1.setCellFactory(TextFieldTableCell.forTableColumn());
		
		
		col2=new TableColumn<>("Datum");
		col2.setCellValueFactory(new PropertyValueFactory<>("datum"));
		col2.setCellFactory(TextFieldTableCell.forTableColumn());
		
		
		col3=new TableColumn<>("Dolazak sati");
		col3.setCellValueFactory(new PropertyValueFactory<>("satiDolazak"));
		col3.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		
		col4=new TableColumn<>("Dolazak minuti");
		col4.setCellValueFactory(new PropertyValueFactory<>("minutiDolazak"));
		col4.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		
		col5=new TableColumn<>("Odlazak sati");
		col5.setCellValueFactory(new PropertyValueFactory<>("satiOdlazak"));
		col5.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		
		col6=new TableColumn<>("Odlazak minuti");
		col6.setCellValueFactory(new PropertyValueFactory<>("minutiOdlazak"));
		col6.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		
		col7=new TableColumn<>("Ukupno sati");
		col7.setCellValueFactory(new PropertyValueFactory<>("ukupnoSati"));
		col7.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		
		col8=new TableColumn<>("Ukupno minuti");
		col8.setCellValueFactory(new PropertyValueFactory<>("ukupnoMinuti"));
		col8.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		
		col9=new TableColumn<>("Bonus");
		col9.setCellValueFactory(new PropertyValueFactory<>("bonus"));
		col9.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		
		
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		table.getColumns().add(col1);
		table.getColumns().add(col2);
		table.getColumns().add(col3);
		table.getColumns().add(col4);
		table.getColumns().add(col5);
		table.getColumns().add(col6);
		table.getColumns().add(col7);
		table.getColumns().add(col8);
		table.getColumns().add(col9);
		
///////////////////////---------------BUTTON PANEL----------------------------------------------------////////////////////
		HBox buttonPanel=new HBox();
		
		btnCalculate=new Button("Calculate");
		btnCalculate.setPrefWidth(100);
		btnCalculate.setOnAction(e->loadData());
		//podesavanje action listenera za obracunavanje vrednosti i prosledjivanje tih vrednosti u tabelu i
		//podesavanje fokusa u tekstualnim poljima
		btnCalculate.setOnKeyPressed(e->{
			
			if(btnCalculate.isFocused() && e.getCode()==KeyCode.RIGHT)
				btnSave.requestFocus();
			else if(btnCalculate.isFocused() && e.getCode()==KeyCode.LEFT)
				txtNormalniKoeficijent.requestFocus();
		});
		
		
		
		btnSave=new Button("Save");
		btnSave.setPrefWidth(100);
		btnSave.setOnKeyPressed(e->{
			if(btnSave.isFocused() && e.getCode()==KeyCode.LEFT)
				btnCalculate.requestFocus();
			else if(btnSave.isFocused() && e.getCode()==KeyCode.RIGHT)
			btnLoad.requestFocus();
		});
		btnSave.setOnAction(e ->{
			
			FileChooser fc=new FileChooser();
			File file=fc.showSaveDialog(primaryStage);
			if(file!=null) 
				saveButton(file);
				});
		
		btnLoad=new Button("Load");
		btnLoad.setPrefWidth(100);
		btnLoad.setOnKeyPressed(e->{
			 if(btnLoad.isFocused() && e.getCode()==KeyCode.LEFT)
					btnSave.requestFocus();
			 else if(btnLoad.isFocused() && e.getCode()==KeyCode.RIGHT)
				btnOpen.requestFocus();
		});
		
		btnLoad.setOnAction(e->{
			
			FileChooser fc=new FileChooser();
			File file=fc.showOpenDialog(primaryStage);
			if(file!=null) {
			setup(file);
			loadButton(file);
			}
		});
		
		btnOpen=new Button("Open");
		btnOpen.setPrefWidth(100);
		btnOpen.setOnKeyPressed(e->{
			 if(btnOpen.isFocused() && e.getCode()==KeyCode.LEFT)
					btnLoad.requestFocus();
			 else if(btnOpen.isFocused() && e.getCode()==KeyCode.RIGHT)
				btnRemove.requestFocus();
		});
		btnOpen.setOnAction(e->{
			
			File file=fileChooser.showOpenDialog(primaryStage);
			if(file!=null) 
				openButton(file);
		});
		
		btnRemove=new Button("Remove");
		btnRemove.setPrefWidth(100);
		btnRemove.setOnKeyPressed(e->{
				if(btnRemove.isFocused() && e.getCode()==KeyCode.LEFT)
				btnOpen.requestFocus();
				else if(btnRemove.isFocused() && e.getCode()==KeyCode.RIGHT)
					btnClear.requestFocus();
		});
		btnRemove.setOnAction(e->{
			removeButton();
		});
		btnClear=new Button("Clear");
		btnClear.setPrefWidth(100);
		btnClear.setOnKeyPressed(e->{
			if(btnClear.isFocused() && e.getCode()==KeyCode.LEFT)
				btnRemove.requestFocus();
			else if(btnClear.isFocused() && e.getCode()==KeyCode.RIGHT) {
				
				txtImeIPrezime.requestFocus();
				}
			 });
		btnClear.setOnAction(e->buttonClear());
		
	
		
		
		buttonPanel.setPadding(new Insets(10,10,10,10));
		buttonPanel.setSpacing(120);
		buttonPanel.getChildren().addAll(btnCalculate,btnSave,btnLoad,btnOpen,
				btnRemove,btnClear);
//////--------------------------------BOTTOM PANEL--------------------------------------------------///////////////////		
		
		HBox bottomPanel=new HBox();
		lblNormalniKoeficijent=new Label("Koeficijent: ");
		txtNormalniKoeficijent=new TextField();
		txtNormalniKoeficijent.setPrefWidth(60);
		txtNormalniKoeficijent.setOnKeyPressed(e->{
			if(txtNormalniKoeficijent.isFocused() && e.getCode()==KeyCode.RIGHT)
				txtRedovniSati.requestFocus();
			else if(txtNormalniKoeficijent.isFocused() && e.getCode()==KeyCode.LEFT)
				txtImeIPrezime.requestFocus();
		});
		

		lblRedovniSati=new Label("Redovni sati: ");
		txtRedovniSati=new TextField();
		txtRedovniSati.setPrefWidth(60);
		txtRedovniSati.setOnKeyPressed(e->{
			if(txtRedovniSati.isFocused() && e.getCode()==KeyCode.RIGHT)
				txtExtraKoeficijent.requestFocus();
			else if(txtRedovniSati.isFocused() && e.getCode()== KeyCode.LEFT)
				txtNormalniKoeficijent.requestFocus();
		});
		

		lblExtraKoeficijent=new Label("Extra koeficijent: ");
		txtExtraKoeficijent=new TextField();
		txtExtraKoeficijent.setPrefWidth(60);
		txtExtraKoeficijent.setOnKeyPressed(e->{
			if(txtExtraKoeficijent.isFocused() && e.getCode() == KeyCode.RIGHT)
				txtTotalBonus.requestFocus();
			else if(txtExtraKoeficijent.isFocused() && e.getCode()==KeyCode.LEFT)
				txtRedovniSati.requestFocus();
		});
		

		lblVanredniSati=new Label("Vanredni sati: ");
		txtVanredniSati=new TextField();
		txtVanredniSati.setPrefWidth(60);
		txtVanredniSati.setFocusTraversable(false);
		txtVanredniSati.setEditable(false);
		txtVanredniSati.setMouseTransparent(true);
		
		lblTotalSati=new Label("Total sati: ");
		txtTotalSati=new TextField();
		txtTotalSati.setPrefWidth(80);
		txtTotalSati.setFocusTraversable(false);
		txtTotalSati.setEditable(false);
		txtTotalSati.setMouseTransparent(true);
		
		lblTotalMinuti=new Label("Total minuti: ");
		txtTotalMinuti=new TextField();
		txtTotalMinuti.setPrefWidth(80);
		txtTotalMinuti.setFocusTraversable(false);
		txtTotalMinuti.setEditable(false);
		txtTotalMinuti.setMouseTransparent(true);
		
	
		lblTotalBonus=new Label("Bonus: ");
		txtTotalBonus=new TextField("0");
		txtTotalBonus.setPrefWidth(80);
		txtTotalBonus.setOnKeyPressed(e->{
			if(txtTotalBonus.isFocused() && e.getCode() == KeyCode.RIGHT)
				btnCalculate.requestFocus();
				
			else if(txtTotalBonus.isFocused() && e.getCode()==KeyCode.LEFT)
				txtExtraKoeficijent.requestFocus();
		});
		
		
		lblPlata=new Label("Plata: ");
		txtPlata=new TextField("0");
		txtPlata.setPrefWidth(80);
		txtPlata.setFocusTraversable(false);
		txtPlata.setEditable(false);
		txtPlata.setMouseTransparent(true);
		txtPlata.setStyle("-fx-font-weight:bold; -fx-font-size:18;");
		
		bottomPanel.setPadding(new Insets(20,10,10,10));
		bottomPanel.setSpacing(8);
		bottomPanel.setStyle("-fx-background-color:#A9A9A9");
		bottomPanel.getChildren().addAll(lblNormalniKoeficijent,txtNormalniKoeficijent,lblRedovniSati,txtRedovniSati,
				lblExtraKoeficijent,txtExtraKoeficijent,lblVanredniSati,txtVanredniSati,lblTotalSati,txtTotalSati,
				lblTotalMinuti,txtTotalMinuti,lblTotalBonus,txtTotalBonus,lblPlata,txtPlata);
		
		VBox mainPanel = new VBox();
		mainPanel.getChildren().addAll(topPanel,table,buttonPanel,bottomPanel);
		Scene scene = new Scene(mainPanel,1200,600);
		primaryStage.setScene(scene);
		primaryStage.show();
	} catch(Exception e) {
		e.printStackTrace();
	}
}//start method

	
	//Metod koji podatke iz tekst polja ubacuje u tabelu i poziva druge metode da izvrse obracun podataka i
	//sve te podatke upise u tabelu.
	public void loadData() {
		Calculate c=new Calculate();
		try {
		
		c.setImeIPrezime(txtImeIPrezime.getText());	
		c.setObjekat(txtObjekat.getText());
		c.setDatum(txtDatum.getText());
		c.setSatiDolazak(Integer.parseInt(txtSatiDolazak.getText()));
		c.setMinutiDolazak(Integer.parseInt(txtMinutiDolazak.getText()));
		c.setSatiOdlazak(Integer.parseInt(txtSatiOdlazak.getText()));
		c.setMinutiOdlazak(Integer.parseInt(txtMinutiOdlazak.getText()));
		c.setBonus(Double.parseDouble(txtBonus.getText().replace(",", ".")));
		c.setNormalniKoeficijent(Double.parseDouble(txtNormalniKoeficijent.getText()));
		c.setRedovniSati(Integer.parseInt(txtRedovniSati.getText()));
		c.setExtraKoeficijent(Double.parseDouble(txtExtraKoeficijent.getText()));
		c.setUkupnoSati(c.ukupnoSati());
		c.setUkupnoMinuti(c.ukupnoMinuti());
		c.setTotalMinuti(c.totalMinuti());
		c.setTotalSati(c.totalSati());
		c.setVanredniSati(c.vanredniSati());
		
			
		c.setTotalBonus(c.obracunTotalBonusa());
		c.setNPlata(c.obracunPlate());
		
		txtTotalSati.setText(String.format("%d",c.getTotalSati()));
		txtTotalMinuti.setText(String.format("%d",c.getTotalMinuti()));
		txtVanredniSati.setText(String.format("%d",c.getVanredniSati()));
		txtTotalBonus.setText(String.format("%.2f", c.getTotalBonus()));
		txtPlata.setText(String.format("%.2f",c.getNPlata()));
		
		table.getItems().add(c);
		
		//Proveravamo da li su unosi sat i minuti ispravni sat da je manji od 24 i veci od 0 i
		//minuti da su manji od 60
		if((c.getSatiDolazak() > 24 || c.getSatiDolazak() < 0) || (c.getSatiOdlazak() > 24 || c.getSatiOdlazak() < 0) ||
			(c.getMinutiDolazak() > 59 || c.getMinutiDolazak() < 0) || (c.getMinutiOdlazak() > 59 || c.getMinutiOdlazak() < 0)) 
				{ 
					Alert alert=new Alert(AlertType.ERROR);
					alert.setTitle("Error in loadData method");
					alert.setContentText("Molmo vas da proverite unos podataka \n "
					+ "i format unosa 24/1 podatka");
					alert.show();
					return ;
				}
		
		}catch(NumberFormatException e) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error in loadData method");
			alert.setContentText("Molmo vas da proverite unos podataka \n "
					+ "i format unosa podatka");
			alert.show();
			e.printStackTrace();
		}
	}//loadData()
	
	//Metod koji se aktivira pritiskom dugmeta save i upisuje podatke iz tabele na hard disk u vidu teksta.
	//Kao parametar se koristi klasa file i koristi se PrintWriter i FileOutputStream
	public void saveButton(File file) {
		
		try {
		PrintWriter pw=new PrintWriter(new FileOutputStream(file));	
		table.getSelectionModel();
		list=table.getItems();
		//Upisiujemo ime i prezime na vrh dokumenta
		pw.write(String.format("%50s",txtImeIPrezime.getText()));
		//Dodajemo praznu liniju kako bi se napravio razmak od kolumni
		pw.println();
		//Upisujemo nazive kolumni na dokument
		pw.write(col1.getText()+"             "+col2.getText()+"     "+col3.getText()+"  "+col4.getText()+"  "+col5.getText()
				+"  "+col6.getText()+"    "+col7.getText()+"  "+col8.getText()+" "+col9.getText()+"\n");
		//Prazna linija da se napravi razmak od vrednosti koje se upisuju u tabelu
		pw.println();
		//Upisujemo podatke u tabelu
		for (Model m:list) {
			pw.write((String.format("%-15s", m.getObjekat()))+"   "+(String.format("%-11s", m.getDatum()))
					+"   "+(String.format("%5d", m.getSatiDolazak()))+"   "+(String.format("%10d", m.getMinutiDolazak()))
					+"   "+(String.format("%15d", m.getSatiOdlazak()))+"   "+(String.format("%15d", m.getMinutiOdlazak()))
					+"   "+(String.format("%10d", m.getUkupnoSati()))+"   "+(String.format("%10d", m.getUkupnoMinuti()))
					+"   "+(String.format("%.2f", m.getBonus()))
					+"\n");
			
		}
		 
		pw.println();
		//Dodajemo nazive za zavrsni dio dokumenta
		pw.write(String.format("%s", lblNormalniKoeficijent.getText()+"  "+lblRedovniSati.getText()+"  "
					+lblExtraKoeficijent.getText()+"  "+lblVanredniSati.getText()+"  "+lblTotalSati.getText()+"  "
					+lblTotalMinuti.getText()+"  "+lblBonus.getText())+lblPlata.getText());
		//Prazna linija koja pravi razmak od zavrsnog djela dokumenta
		pw.println();
		//Dodajemo zavrsne vrednosti  na dokument redovni  koeficijent, redovni sati,vanredni koeficijent,
		//total sati, vanredni sati, total minuti, total bonus,plata. 
		pw.write("   "+String.format("%10s", txtNormalniKoeficijent.getText()+"  "+
				(String.format("%15s",txtRedovniSati.getText()))+"  "+
				(String.format("%15s",txtExtraKoeficijent.getText()))+"  "+
				(String.format("%20s",txtVanredniSati.getText()))+"  "+
				(String.format("%20s",txtTotalSati.getText()))+"  "+
				(String.format("%10s",txtTotalMinuti.getText()))+"  "+
				(String.format("%10s", txtTotalBonus.getText()))+"  "+
				(String.format("%10s",txtPlata.getText()))));
				
		pw.close();
		}catch(Exception e) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error in saveButton method");
			alert.setContentText("Exception error in saveButton method");
			alert.show();
			e.printStackTrace();
		}
		
	}//saveButton()
	
	
	//Metod koji se aktivira pritiskom na load button koji zajedno sa setup metodon omogucava da se podaci
	//iz fajla koji su prethodno sacuvani saveButton metodom ponovo pojave u tabeli
	public void loadButton(File file){
		String strLetters = "^[a-zA-Z]*$";
		String strNumbers = "^[0-9]";
		try {
		
			bf=new BufferedReader(new FileReader(file));
			//Ispisuje sa dokumenta ime i prezime u txt polje ime i prezime
			txtImeIPrezime.setText(bf.readLine().trim());
			//Preskacemo dve linije na dokumentu kako bi smo omogucili da se pojave vrednosti
			//koje se trebaju naci u tabeli. Zapravo preskacemo nazive kolumni i jednu praznu liniju
			//, jer sve to nije potrebno unositi u tabelu.
			bf.readLine();
			bf.readLine();
			//While petlja koja ispituje uslov da li je string str prazan i ukoliko nije prazan petlja 
			//se aktivira
			while(!(str=bf.readLine()).isEmpty()) {
			
				//niz je jednodimenzionalan niz koji se koristi da bi se prazninama u stringu razdvojili u 
				//elemente string niza
				niz=str.split(reg);
				System.out.println("niz0 = "+niz[0] + " niz1 = " + niz[1] + " niz2 = " + niz[2]);
				//Ukoliko naziv objekta ima samo jednu rijec
				if((((niz[0].matches(strLetters)))
						|| (niz[0].contains("Š"))
						|| (niz[0].contains("š"))
						|| (niz[0].contains("Đ")) 
						|| (niz[0].contains("đ")) 
						|| (niz[0].contains("Č")) 
						|| (niz[0].contains("č"))
						|| (niz[0].contains("Ć")) 
						|| (niz[0].contains("ć"))
						|| (niz[0].contains("Ž"))
						|| (niz[0].contains("ž")))
						&& (niz[2].matches(strNumbers))){
					
					System.out.println("first if");
					txtObjekat.setText(niz[0]);
					txtDatum.setText(niz[1]);
					txtSatiDolazak.setText(niz[2]);
					txtMinutiDolazak.setText(niz[3]);
					txtSatiOdlazak.setText(niz[4]);
					txtMinutiOdlazak.setText(niz[5]);
					txtBonus.setText(niz[8]);
				}
			
				//Ukoliko naziv objekta dve reci
				//Koristimo metod contains kako bi samo omogucili mala i velika slova šđčćž
				else if(((niz[0].matches(strLetters) 
						|| niz[0].contains("Š")
						|| niz[0].contains("š")
						|| niz[0].contains("Đ")
						|| niz[0].contains("đ") 
						|| niz[0].contains("Č") 
						|| niz[0].contains("č")
						|| niz[0].contains("Ć") 
						|| niz[0].contains("ć")
						|| niz[0].contains("ž"))
						&& (niz[1].matches(strLetters)
						|| niz[1].contains("Š")
						|| niz[1].contains("š")
						|| niz[1].contains("Đ")
						|| niz[1].contains("đ") 
						|| niz[1].contains("Č") 
						|| niz[1].contains("č")
						|| niz[1].contains("Ć") 
						|| niz[1].contains("ć")
						|| niz[1].contains("ž"))		
						&& (niz[3].matches(strNumbers))
						)) {
					System.out.println("second if");
					txtObjekat.setText(niz[0]+" "+niz[1]);
					txtDatum.setText(niz[2]);
					txtSatiDolazak.setText(niz[3]);
					txtMinutiDolazak.setText(niz[4]);
					txtSatiOdlazak.setText(niz[5]);
					txtMinutiOdlazak.setText(niz[6]);
					txtBonus.setText(niz[9]);
					}
				//Upisujemo podatke iz dokumenta u tabelu
				loadData();
				}
			bf.close();
			
		} catch (FileNotFoundException e) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error in loadButton method");
			alert.setContentText("File not found error in loadButton method");
			alert.show();
			e.printStackTrace();
		} catch (IndexOutOfBoundsException ex) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error in loadButton method");
			alert.setContentText("Tekst polje ime i prezime moraju imati dve reci ili \n"
					+ "naziv objekta moze imati maksimalno dve reci \n"
					+ "molimo vas da selektujete red i pritisnete remove");
			alert.show();
			ex.printStackTrace();
		} catch (IOException e) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error in loadButton method");
			alert.setContentText("IOException error in loadButton method");
			alert.show();
			e.printStackTrace();
		}
	}//loadButton()
	
	//Metod koji se aktivira sa pritiskom na load button i omogucava da se iz fajla ispisuje u tabelu 
	//podaci za: txtNormalniKoeficijent,txtRedovniSati i txtRedovniSati.
	public void setup(File file) {
		try {
			
			bf=new BufferedReader(new FileReader(file));
			bf.readLine();
			bf.readLine();
			bf.readLine();
			
			//Koristimo praznu while loop jer nam podaci za tabelu ne trebaju za to sam koristio 
			//load metod)
			while(!bf.readLine().isEmpty()) {
				
				}
			//Preskacemo jednu liniju
			bf.readLine();
			//Sadrzaj stavljamo string test
			test=bf.readLine();
			//String test pretvaramo u string niz koristeci regex izraz upisuje u matricu podatke
			//gdje praznine predstavljaju granicnik za sljedeci element koji se upisuje u  tekstualna
			//polja.
			niz2=test.split(reg);
			//Upisuju se elementi string niza u odgovarajuca tekst polja
			txtNormalniKoeficijent.setText(niz2[1]);
			txtRedovniSati.setText(niz2[2]);
			txtExtraKoeficijent.setText(niz2[3]);
			bf.close();
		
		} catch (FileNotFoundException e) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error in setup method");
			alert.setContentText("File not found error in setup method");
			alert.show();
			
			e.printStackTrace();
		} catch (IOException e) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error in setup method");
			alert.setContentText("IOException error in setup method");
			alert.show();
			e.printStackTrace();
		}
	}//setup()
	//Ovaj metod se aktivira kada se pritsne  open i otvara fajl u text editoru notepad wordu itd.
	
	public void openButton(File file) {
		try {
			desktop.open(file);
		} catch (IOException e) {
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("Error in openButton method");
			alert.setContentText("IOException error in openButton method");
			alert.show();
			e.printStackTrace();
		}
	} //openButton()
	//int tempUkupnoSati;
	//int tempUkupnoMinuti;
	
	//Ovaj metod se aktivira pritiskom na remove dugme.
	//Prvo selektujemo red u tabeli koji zelimo obrisati pa pritisnemo dugme remove i izbrisemo selektovani red
	//Nakon toga se ponvo obracunava sadrzaj tabele bez izbrisanog reda 
	public void removeButton() {
		Calculate c=new Calculate();
		c.provera=true;
		ObservableList<Model> items,selected ;
		items=table.getItems();
		selected=table.getSelectionModel().getSelectedItems();
		
		for(Model m:selected) {
			c.setSatiDolazak(m.getSatiDolazak());
			c.setMinutiDolazak(m.getMinutiDolazak());
			c.setSatiOdlazak(m.getSatiOdlazak());
			c.setMinutiOdlazak(m.getMinutiOdlazak());
			c.setUkupnoSati(m.getUkupnoSati());
			c.setUkupnoMinuti(m.getUkupnoMinuti());
			c.setBonus(m.getBonus());
			c.setNormalniKoeficijent(Double.parseDouble(txtNormalniKoeficijent.getText()));
			c.setExtraKoeficijent(Double.parseDouble(txtExtraKoeficijent.getText()));
			c.setRedovniSati(Integer.parseInt(txtRedovniSati.getText()));
			c.setTotalMinuti(Integer.parseInt(txtTotalMinuti.getText()));
			c.setTotalSati(Integer.parseInt(txtTotalSati.getText()));
			c.setVanredniSati(Integer.parseInt(txtVanredniSati.getText()));
			c.setTotalBonus(Double.parseDouble(txtTotalBonus.getText().replace(",", ".")));
			c.setNPlata(Double.parseDouble(txtPlata.getText().replace(",", ".")));
			
			txtTotalMinuti.setText(String.format("%d",c.totalMinuti()));
			txtTotalSati.setText(String.format("%d",c.totalSati()));
			txtVanredniSati.setText(String.format("%d",c.vanredniSati()));
			txtTotalBonus.setText(String.format("%.2f", c.obracunTotalBonusa()));
			txtPlata.setText(String.format("%.2f",c.obracunPlate()));
		
			items.remove(m);
			if(selected.isEmpty()) {
				break;
				}
		}//for petlja
		
	}//removeButton()
	
	//Metod koji se aktivira kada se pritisne dugme clear i briše sve podatke iz tabele
	public void buttonClear(){
		Calculate c = new Calculate();
		txtImeIPrezime.clear();
		txtObjekat.clear();
		txtDatum.clear();
		txtSatiDolazak.clear();
		txtMinutiDolazak.clear();
		txtSatiOdlazak.clear();
		txtMinutiOdlazak.clear();
		txtBonus.setText("0");
		txtNormalniKoeficijent.clear();
		txtRedovniSati.clear();
		txtExtraKoeficijent.clear();
		txtVanredniSati.clear();
		txtTotalSati.clear();
		txtTotalMinuti.clear();
		txtTotalBonus.clear();
		txtPlata.clear();
		c.setImeIPrezime(null);
		c.setObjekat(null);
		c.setDatum(null);
		c.setSatiDolazak(0);
		c.setMinutiDolazak(0);
		c.setSatiOdlazak(0);
		c.setMinutiOdlazak(0);
		c.setBonus(0);
		c.setNormalniKoeficijent(0);
		c.setRedovniSati(0);
		c.setExtraKoeficijent(0);
		c.setUkupnoSati(0);
		c.setUkupnoMinuti(0);
		c.setTotalMinuti(0);
		c.setTotalSati(0);
		c.setVanredniSati(0);
		c.setTotalBonus(0);
		c.setNPlata(0);
		Calculate.tempTotalSati=0;
		Calculate.tempTotalMinuti=0;
		Calculate.plata=0;
		Calculate.totalBonus1=0; 
		table.getItems().clear();
	}//buttonClear()
	

	
	
	public static void main(String[] args) {
		launch(args);
	}//main method

	
}
