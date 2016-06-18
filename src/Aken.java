
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class Aken extends Application {

	public static boolean mängLäbi = false;  //kontrollib, kas mäng on läbi
	
	//Mängu akna ja töö korraldus:
	
	@Override
	public void start(final Stage primaryStage) throws FileNotFoundException {
		
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
		Rectangle vend_pilt = new Rectangle(100, 150, stseen1.getWidth()/5, stseen1.getWidth()/5);
		Image vennapilt=new Image("file:\\C:\\Users\\Diana\\oop\\Rühmatöö2\\src\\vend.png");
		ImageView ivend = new ImageView();
		ivend.setImage(vennapilt);
		ivend.setX(vend_pilt.getX());
		ivend.setY(vend_pilt.getY()-40);
		
		Rectangle õde_pilt = new Rectangle(vend_pilt.getX()+200, vend_pilt.getY(), 100, 100);
		Image õepilt = new Image("file:\\C:\\Users\\Diana\\oop\\Rühmatöö2\\src\\õde.png");
		ImageView iõde = new ImageView();
		iõde.setImage(õepilt);
		iõde.setX(õde_pilt.getX());
		iõde.setY(õde_pilt.getY()-40);
		//iv1.setFitWidth();
		//iv1.setFitHeight(õde_pilt.getWidth());
		
		õde_pilt.setFill(Color.RED);
		final Rectangle v_jaks = new Rectangle(vend_pilt.getX(), vend_pilt.getY()+vend_pilt.getHeight()+30, vend.getHp(), 30);
		final Rectangle õ_jaks = new Rectangle(õde_pilt.getX(), õde_pilt.getY()+õde_pilt.getHeight()+30, õde_pilt.getWidth(), 30);
		v_jaks.setFill(Color.GREEN);
		v_jaks.setStroke(Color.BLACK);
		õ_jaks.setFill(Color.GREEN);
		õ_jaks.setStroke(Color.BLACK);
			
		//venna joogipudelid
		ImageView[] pudelid = new ImageView[6];
		final Group joogid = new Group();
		Image pudelipilt= new Image("file:\\C:\\Users\\Diana\\oop\\Rühmatöö2\\src\\pudel.png");
		
		for (int i = 0; i<6; i++) {
			pudelid[i] =new ImageView();
			new Rectangle(vend_pilt.getX()+i*30, v_jaks.getY()+60, 20, 40);
			//pudelid[i].setFill(Color.BROWN);
			pudelid[i].setImage(pudelipilt);
			pudelid[i].setX(vend_pilt.getX()-5+i*30);
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
		
		//allaandmis, ründe ja kaitse nupud
		Rectangle ründa = new Rectangle(õde_pilt.getX(), õ_jaks.getY()+õ_jaks.getHeight()+30, 40, 40);
		Image ründepilt = new Image("file:\\C:\\Users\\Diana\\oop\\Rühmatöö2\\src\\ründa.png");
		ImageView iründa = new ImageView();
		iründa.setImage(ründepilt);
		iründa.setX(ründa.getX());
		iründa.setY(ründa.getY()-10);
		Rectangle kaitse = new Rectangle(õde_pilt.getX()+õde_pilt.getWidth()-40, õ_jaks.getY()+õ_jaks.getHeight()+30, 40, 40);
		Image kaitsepilt = new Image("file:\\C:\\Users\\Diana\\oop\\Rühmatöö2\\src\\kaitse.png");
		ImageView ikaitse = new ImageView();
		ikaitse.setImage(kaitsepilt);
		ikaitse.setX(kaitse.getX());
		ikaitse.setY(kaitse.getY()-10);
		Rectangle lõpp = new Rectangle(kaitse.getX()+kaitse.getWidth()+30, kaitse.getY(), 50, 50); 
		Image lõpupilt = new Image("file:\\C:\\Users\\Diana\\oop\\Rühmatöö2\\src\\lõpp.png");
		ImageView ilõpp = new ImageView();
		ilõpp.setImage(lõpupilt);
		ilõpp.setX(lõpp.getX());
		ilõpp.setY(lõpp.getY()-25);
		ründa.setFill(Color.RED);
		kaitse.setFill(Color.SKYBLUE);
		//lõpp.setFill(Color.PURPLE);
		
		final Text ründe_txt = new Text(ründa.getX()+5, ründa.getY()-2, "ründa");
		final Text kaitse_txt = new Text(kaitse.getX()+5, kaitse.getY()-2, "kaitse");
		final Text l6putxt = new Text(lõpp.getX()+5, lõpp.getY()-2, "alistu");
		final Text joomine_txt = new Text(ründa.getX()-150, ründa.getY()+60, "Jookide varu");
		final Text õeraas = new Text(ründa.getX()+20, vend_pilt.getY()-5, "Õde");
		final Text vennakene = new Text(ründa.getX()-150, õeraas.getY(), "Vend");
		final Text elud_txt = new Text(ründa.getX()-90, v_elud.getY(), "Vastupanu jaks");
		
		
		//jooksev seis
		final Text olukord = new Text(70, ründa.getY()+ründa.getHeight()+40, tekstid.get(7));
		olukord.setFont(Font.font("MV Boli", 12));
		
		asjad.getChildren().addAll(ivend, iõde, v_jaks, õ_jaks, iründa, ikaitse, ilõpp, õ_elud, v_elud, taust,
				olukord);
		juur.setCenter(asjad);
		primaryStage.show();
	    
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
						if (mängLäbi){sulge(juur, primaryStage);}
						
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
						if (mängLäbi){sulge(juur, primaryStage);}
						
					}
					else {
						vend.setHp(vend.getHp()-õde.getStr());
						olukord.setText(tekstid.get(19));
						surmaKontroll(vend, olukord, tekstid, v_jaks, v_elud);
						if (mängLäbi){sulge(juur, primaryStage);}
						
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
						if (mängLäbi){sulge(juur, primaryStage);}
						//v_jaks.setWidth(vend.getHp());
						//v_elud.setText(vend.getHp()+"");
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
				sulge(juur, primaryStage);
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
