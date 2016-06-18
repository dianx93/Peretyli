
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class Aken extends Application {

	public static boolean mängLäbi = false;  //kontrollib, kas mäng on läbi
	
	//Mängu akna ja töö korraldus:
	
	@Override
	public void start(final Stage primaryStage) throws IOException {
		
		final BorderPane juur = new BorderPane();
	    Group asjad = new Group();
	    
	    //teeme mängus osalejad
	    final Mängija vend = new Mängija(6, 100, 10);
		final Vastane õde = new Vastane(100, 16);
				
		//teeme stseeni
		final Scene stseen1 = new Scene(juur, 500, 500, Color.WHITE);
	    primaryStage.setTitle("Peretüli");
	    primaryStage.setScene(stseen1);
	    
	    //tekstiridade lugemine
	    java.io.File fail = new java.io.File("src/tekstid.txt");
		java.util.Scanner sc = new java.util.Scanner(fail);
		final ArrayList<String> tekstid = new ArrayList<String>();
		while (sc.hasNextLine()) {
			String rida = sc.nextLine();
			tekstid.add(rida);
		}
		sc.close();

	    //teeme visuaalsed asjad	
		Image vennapilt=new Image("file:src\\vend.png");
		ImageView ivend = new ImageView();
		ivend.setImage(vennapilt);
		ivend.setX(100);
		ivend.setY(110);
		
		Image õepilt = new Image("file:src\\õde.png");
		ImageView iõde = new ImageView();
		iõde.setImage(õepilt);
		iõde.setX(ivend.getX()+200);
		iõde.setY(ivend.getY());
		
		final Rectangle v_jaks = new Rectangle(ivend.getX(), ivend.getY()+stseen1.getWidth()/5+70, vend.getHp(), 30);
		final Rectangle õ_jaks = new Rectangle(iõde.getX(), iõde.getY()+170, 100, 30);
		v_jaks.setFill(Color.GREEN);
		v_jaks.setStroke(Color.BLACK);
		õ_jaks.setFill(Color.GREEN);
		õ_jaks.setStroke(Color.BLACK);
			
		//venna joogipudelid
		ImageView[] pudelid = new ImageView[6];
		final Group joogid = new Group();
		Image pudelipilt= new Image("file:src\\pudel.png");
		
		for (int i = 0; i<6; i++) {
			pudelid[i] =new ImageView();
			pudelid[i].setImage(pudelipilt);
			pudelid[i].setX(ivend.getX()-5+i*30);
			pudelid[i].setY(v_jaks.getY()+30);
			joogid.getChildren().add(pudelid[i]);
			
		}
		
		asjad.getChildren().add(joogid);
		
		final Text v_elud= new Text(v_jaks.getX(), v_jaks.getY()-2, vend.getHp()+"");
		final Text õ_elud= new Text(õ_jaks.getX(), õ_jaks.getY()-2, õde.getHp()+"");
		
		Text taust= new Text(70, 0, tekstid.get(0)+"\n"+tekstid.get(1)+"\n"+tekstid.get(2)+
				"\n"+tekstid.get(3)+"\n"+tekstid.get(4)+"\n"+tekstid.get(5)
				);
		taust.setFont(Font.font("MV Boli", 12));
		
		//erinevad nupud
		Image ründepilt = new Image("file:src\\ründa.png");
		ImageView iründa = new ImageView();
		iründa.setImage(ründepilt);
		iründa.setX(iõde.getX());
		iründa.setY(õ_jaks.getY()+õ_jaks.getHeight()+20);
		
		Image kaitsepilt = new Image("file:src\\kaitse.png");
		ImageView ikaitse = new ImageView();
		ikaitse.setImage(kaitsepilt);
		ikaitse.setX(iõde.getX()+60);
		ikaitse.setY(õ_jaks.getY()+õ_jaks.getHeight()+20);
		 
		Image lõpupilt = new Image("file:src\\lõpp.png");
		ImageView ilõpp = new ImageView();
		ilõpp.setImage(lõpupilt);
		ilõpp.setX(ikaitse.getX()+70);
		ilõpp.setY(ikaitse.getY()-15);
		
		Image logipilt=new Image("file:src\\folder.png");
		ImageView ilogi = new ImageView();
		ilogi.setImage(logipilt);
		ilogi.setX(ilõpp.getX()+25);
		ilogi.setY(ilõpp.getY()-100);
		
		//nupp, mida ei tohi vajutada:
		Image punanepilt=new Image("file:src\\nupp.png");
		ImageView inupp=new ImageView();
		inupp.setImage(punanepilt);
		inupp.setX(ilõpp.getX());
		inupp.setY(ilõpp.getY()-200);
		
		
		//jooksev seis
		final Text olukord = new Text(70, iründa.getY()+90, tekstid.get(7));
		olukord.setFont(Font.font("MV Boli", 12));
		
		asjad.getChildren().addAll(ivend, iõde, v_jaks, õ_jaks, iründa, ikaitse, ilõpp, õ_elud, v_elud, taust,
				olukord, ilogi, inupp);
		juur.setCenter(asjad);
		primaryStage.show();
	    
		//logi:
		ilogi.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent mex) {
				int võite=0, kaotusi=0, allaandmisi=0;
				try{
					java.io.File fail = new java.io.File("logi.txt");
					java.util.Scanner sc = new java.util.Scanner(fail);
					
					while (sc.hasNextLine()) {
						String rida = sc.nextLine();
						if (rida.equals("võit")){
							võite++;
							}
						else if (rida.equals("kaotus")){
							kaotusi++;
							}
						else if (rida.equals("allaandmine")){
							allaandmisi++;
							}
						}
					sc.close();
					}
				catch(FileNotFoundException e){
					System.out.println(e);
					}
				
				final Text tvõit = new Text(100, 100, "Võite: "+võite);
				tvõit.setFont(Font.font("MV Boli", 12));
				final Text tkaotus = new Text(100, 100, "Kaotuseid: "+kaotusi);
				tkaotus.setFont(Font.font("MV Boli", 12));
				final Text taa = new Text(100, 100, "Allaandmisi: "+allaandmisi);
				taa.setFont(Font.font("MV Boli", 12));
				
			    final Stage logi = new Stage();
			    VBox vBox = new VBox(10);
		        vBox.setAlignment(Pos.CENTER);
		        vBox.getChildren().addAll(tvõit, tkaotus, taa);
		 
		        //stseeni loomine ja näitamine
		        Scene stseen3 = new Scene(vBox);
		        logi.setScene(stseen3);
		        logi.show();
		    }
		}
		);
		
		//joomisel:
		joogid.setOnMousePressed(new EventHandler<MouseEvent>() {
	    	public void handle(MouseEvent mex) {
	    		int õ_tegevus = (int) Math.round(Math.random()*1+1); //õe tegevus
	    		if (õ_tegevus !=1){ //kui õde ei ründa
	    			vend.Heal();
	    			olukord.setText(tekstid.get(20));
		    		
		    		if (vend.getHp()>=200) {
		    			õ_jaks.setX(õ_jaks.getX()+25);
		    			õ_elud.setX(õ_jaks.getX());
		    		}
		    		v_jaks.setWidth(vend.getHp());
		    		v_elud.setText(vend.getHp()+"");
		    		}
		    	else { //kui õde ründab
	    			olukord.setText(tekstid.get(12));
					vend.setPotid(vend.getPotid()-1);
	    		}
	    		joogid.getChildren().remove(vend.getPotid());
	    	}
	    }
	    );
		
		//ründamisel:
		iründa.setOnMousePressed(new EventHandler<MouseEvent>() {
	    	public void handle(MouseEvent mex) {
	    		int õ_tegevus = (int) Math.round(Math.random()*1+1); //õe tegevus
	    		if (õ_tegevus !=1){ //kui õde ei ründa
	    			int muutuja = (int) Math.round(Math.random()*1+1);
					if (muutuja==1) { //rünne võidab
						õde.setHp(õde.getHp()-vend.getStr()/2);
						olukord.setText(tekstid.get(16));
						surmaKontroll(õde, olukord, tekstid, õ_jaks, õ_elud);
						if (mängLäbi){
							logitäitmine("võit");
							sulge(juur, primaryStage);}
						
					}
					else {
						olukord.setText(tekstid.get(17));
					}
				}
		    	else { //kui õde ründab
		    		int muutuja = (int) Math.round(Math.random()*1+1);
					if (muutuja==1) {
						õde.setHp(õde.getHp()-vend.getStr());
						olukord.setText(tekstid.get(18));
						surmaKontroll(õde, olukord, tekstid, õ_jaks, õ_elud);
						if (mängLäbi){
							logitäitmine("võit");
							sulge(juur, primaryStage);}
						
					}
					else {
						vend.setHp(vend.getHp()-õde.getStr());
						olukord.setText(tekstid.get(19));
						surmaKontroll(vend, olukord, tekstid, v_jaks, v_elud);
						if (mängLäbi){
							logitäitmine("kaotus");
							sulge(juur, primaryStage);}
						
					}
				}
	    		
	    	}
	    	
	    });
		
		//kaitsmisel:
		ikaitse.setOnMousePressed(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent mex) {
		 		int õ_tegevus = (int) Math.round(Math.random()*1+1); //õe tegevus
			   	if (õ_tegevus !=1){ //kui õde ei ründa
		    		olukord.setText(tekstid.get(13));}
		   		else { //kui õde ründab
		   			int muutuja = (int) Math.round(Math.random()*1+1);
					if (muutuja==1) {
						vend.setHp(vend.getHp()-õde.getStr()/2);
						olukord.setText(tekstid.get(14));
						surmaKontroll(vend, olukord, tekstid, v_jaks, v_elud);
						if (mängLäbi){
							logitäitmine("kaotus");
							sulge(juur, primaryStage);}
					}
					else {
						olukord.setText(tekstid.get(15));
					}
				}
			  }
		    });
				
		
		//allaandmisel:
		ilõpp.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent mex) {
				olukord.setText(tekstid.get(10));
				olukord.setUnderline(true);
				olukord.setFill(Color.RED);
				mängLäbi=true;
				logitäitmine("allaandmine");
				sulge(juur, primaryStage);
		}
		});
		
		//kui vajutatakse punast nuppu:
		inupp.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent mex) {
				try {
					throw new PunaneNuppErind();
				} catch (PunaneNuppErind e) {
					olukord.setText("Oli ju jutt, et seda nuppu ei vajuta!");
				}
		}
		});
		
		//esc vajutamisel aken sulgub:
		stseen1.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    public void handle(KeyEvent ke) {
		    	if(ke.getCode()==KeyCode.ESCAPE){
		    	primaryStage.close();
		    	}
		    }
		});
		
		
		
	}
	//mõned meetodid, et koodi lühendada
	
	//kontroll, kas keegi kaotas
	public static void surmaKontroll(Vastane vend, Text olukord, ArrayList<String> tekstid, Rectangle v_jaks, Text v_elud) {
		if (vend.getHp()<=0) {
			if (vend instanceof Mängija ){
				olukord.setText(tekstid.get(9));}
			else {olukord.setText(tekstid.get(8));}
			v_jaks.setWidth(0);
			v_elud.setText("0");
			olukord.setUnderline(true);
			olukord.setFill(Color.RED);
			mängLäbi=true;
		}
		else{
		v_jaks.setWidth(vend.getHp());
		v_elud.setText(vend.getHp()+"");}
		
	}
	
	//logi täitmine
	public static void logitäitmine(String txt){
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("logi.txt", true)));
		    out.println(txt);
		    out.close();
		} catch (IOException e) {}
	}
	
	//sulgeb akna kuhugi vajutamisel 
	public static void sulge(BorderPane aken, final Stage stage) {
		aken.setOnMousePressed(new EventHandler<MouseEvent>() { 
		    public void handle(MouseEvent mex) {
		    	stage.close();
		    }
		});
	
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
