
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

	public static boolean m�ngL�bi = false;  //kontrollib, kas m�ng on l�bi
	
	//M�ngu akna ja t�� korraldus:
	
	@Override
	public void start(final Stage primaryStage) throws IOException {
		
		final BorderPane juur = new BorderPane();
	    Group asjad = new Group();
	    
	    //teeme m�ngus osalejad
	    final M�ngija vend = new M�ngija(6, 100, 10);
		final Vastane �de = new Vastane(100, 16);
				
		//teeme stseeni
		final Scene stseen1 = new Scene(juur, 500, 500, Color.WHITE);
	    primaryStage.setTitle("Peret�li");
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
		
		Image �epilt = new Image("file:src\\�de.png");
		ImageView i�de = new ImageView();
		i�de.setImage(�epilt);
		i�de.setX(ivend.getX()+200);
		i�de.setY(ivend.getY());
		
		final Rectangle v_jaks = new Rectangle(ivend.getX(), ivend.getY()+stseen1.getWidth()/5+70, vend.getHp(), 30);
		final Rectangle �_jaks = new Rectangle(i�de.getX(), i�de.getY()+170, 100, 30);
		v_jaks.setFill(Color.GREEN);
		v_jaks.setStroke(Color.BLACK);
		�_jaks.setFill(Color.GREEN);
		�_jaks.setStroke(Color.BLACK);
			
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
		final Text �_elud= new Text(�_jaks.getX(), �_jaks.getY()-2, �de.getHp()+"");
		
		Text taust= new Text(70, 0, tekstid.get(0)+"\n"+tekstid.get(1)+"\n"+tekstid.get(2)+
				"\n"+tekstid.get(3)+"\n"+tekstid.get(4)+"\n"+tekstid.get(5)
				);
		taust.setFont(Font.font("MV Boli", 12));
		
		//erinevad nupud
		Image r�ndepilt = new Image("file:src\\r�nda.png");
		ImageView ir�nda = new ImageView();
		ir�nda.setImage(r�ndepilt);
		ir�nda.setX(i�de.getX());
		ir�nda.setY(�_jaks.getY()+�_jaks.getHeight()+20);
		
		Image kaitsepilt = new Image("file:src\\kaitse.png");
		ImageView ikaitse = new ImageView();
		ikaitse.setImage(kaitsepilt);
		ikaitse.setX(i�de.getX()+60);
		ikaitse.setY(�_jaks.getY()+�_jaks.getHeight()+20);
		 
		Image l�pupilt = new Image("file:src\\l�pp.png");
		ImageView il�pp = new ImageView();
		il�pp.setImage(l�pupilt);
		il�pp.setX(ikaitse.getX()+70);
		il�pp.setY(ikaitse.getY()-15);
		
		Image logipilt=new Image("file:src\\folder.png");
		ImageView ilogi = new ImageView();
		ilogi.setImage(logipilt);
		ilogi.setX(il�pp.getX()+25);
		ilogi.setY(il�pp.getY()-100);
		
		//nupp, mida ei tohi vajutada:
		Image punanepilt=new Image("file:src\\nupp.png");
		ImageView inupp=new ImageView();
		inupp.setImage(punanepilt);
		inupp.setX(il�pp.getX());
		inupp.setY(il�pp.getY()-200);
		
		
		//jooksev seis
		final Text olukord = new Text(70, ir�nda.getY()+90, tekstid.get(7));
		olukord.setFont(Font.font("MV Boli", 12));
		
		asjad.getChildren().addAll(ivend, i�de, v_jaks, �_jaks, ir�nda, ikaitse, il�pp, �_elud, v_elud, taust,
				olukord, ilogi, inupp);
		juur.setCenter(asjad);
		primaryStage.show();
	    
		//logi:
		ilogi.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent mex) {
				int v�ite=0, kaotusi=0, allaandmisi=0;
				try{
					java.io.File fail = new java.io.File("logi.txt");
					java.util.Scanner sc = new java.util.Scanner(fail);
					
					while (sc.hasNextLine()) {
						String rida = sc.nextLine();
						if (rida.equals("v�it")){
							v�ite++;
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
				
				final Text tv�it = new Text(100, 100, "V�ite: "+v�ite);
				tv�it.setFont(Font.font("MV Boli", 12));
				final Text tkaotus = new Text(100, 100, "Kaotuseid: "+kaotusi);
				tkaotus.setFont(Font.font("MV Boli", 12));
				final Text taa = new Text(100, 100, "Allaandmisi: "+allaandmisi);
				taa.setFont(Font.font("MV Boli", 12));
				
			    final Stage logi = new Stage();
			    VBox vBox = new VBox(10);
		        vBox.setAlignment(Pos.CENTER);
		        vBox.getChildren().addAll(tv�it, tkaotus, taa);
		 
		        //stseeni loomine ja n�itamine
		        Scene stseen3 = new Scene(vBox);
		        logi.setScene(stseen3);
		        logi.show();
		    }
		}
		);
		
		//joomisel:
		joogid.setOnMousePressed(new EventHandler<MouseEvent>() {
	    	public void handle(MouseEvent mex) {
	    		int �_tegevus = (int) Math.round(Math.random()*1+1); //�e tegevus
	    		if (�_tegevus !=1){ //kui �de ei r�nda
	    			vend.Heal();
	    			olukord.setText(tekstid.get(20));
		    		
		    		if (vend.getHp()>=200) {
		    			�_jaks.setX(�_jaks.getX()+25);
		    			�_elud.setX(�_jaks.getX());
		    		}
		    		v_jaks.setWidth(vend.getHp());
		    		v_elud.setText(vend.getHp()+"");
		    		}
		    	else { //kui �de r�ndab
	    			olukord.setText(tekstid.get(12));
					vend.setPotid(vend.getPotid()-1);
	    		}
	    		joogid.getChildren().remove(vend.getPotid());
	    	}
	    }
	    );
		
		//r�ndamisel:
		ir�nda.setOnMousePressed(new EventHandler<MouseEvent>() {
	    	public void handle(MouseEvent mex) {
	    		int �_tegevus = (int) Math.round(Math.random()*1+1); //�e tegevus
	    		if (�_tegevus !=1){ //kui �de ei r�nda
	    			int muutuja = (int) Math.round(Math.random()*1+1);
					if (muutuja==1) { //r�nne v�idab
						�de.setHp(�de.getHp()-vend.getStr()/2);
						olukord.setText(tekstid.get(16));
						surmaKontroll(�de, olukord, tekstid, �_jaks, �_elud);
						if (m�ngL�bi){
							logit�itmine("v�it");
							sulge(juur, primaryStage);}
						
					}
					else {
						olukord.setText(tekstid.get(17));
					}
				}
		    	else { //kui �de r�ndab
		    		int muutuja = (int) Math.round(Math.random()*1+1);
					if (muutuja==1) {
						�de.setHp(�de.getHp()-vend.getStr());
						olukord.setText(tekstid.get(18));
						surmaKontroll(�de, olukord, tekstid, �_jaks, �_elud);
						if (m�ngL�bi){
							logit�itmine("v�it");
							sulge(juur, primaryStage);}
						
					}
					else {
						vend.setHp(vend.getHp()-�de.getStr());
						olukord.setText(tekstid.get(19));
						surmaKontroll(vend, olukord, tekstid, v_jaks, v_elud);
						if (m�ngL�bi){
							logit�itmine("kaotus");
							sulge(juur, primaryStage);}
						
					}
				}
	    		
	    	}
	    	
	    });
		
		//kaitsmisel:
		ikaitse.setOnMousePressed(new EventHandler<MouseEvent>() {
		    public void handle(MouseEvent mex) {
		 		int �_tegevus = (int) Math.round(Math.random()*1+1); //�e tegevus
			   	if (�_tegevus !=1){ //kui �de ei r�nda
		    		olukord.setText(tekstid.get(13));}
		   		else { //kui �de r�ndab
		   			int muutuja = (int) Math.round(Math.random()*1+1);
					if (muutuja==1) {
						vend.setHp(vend.getHp()-�de.getStr()/2);
						olukord.setText(tekstid.get(14));
						surmaKontroll(vend, olukord, tekstid, v_jaks, v_elud);
						if (m�ngL�bi){
							logit�itmine("kaotus");
							sulge(juur, primaryStage);}
					}
					else {
						olukord.setText(tekstid.get(15));
					}
				}
			  }
		    });
				
		
		//allaandmisel:
		il�pp.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent mex) {
				olukord.setText(tekstid.get(10));
				olukord.setUnderline(true);
				olukord.setFill(Color.RED);
				m�ngL�bi=true;
				logit�itmine("allaandmine");
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
	//m�ned meetodid, et koodi l�hendada
	
	//kontroll, kas keegi kaotas
	public static void surmaKontroll(Vastane vend, Text olukord, ArrayList<String> tekstid, Rectangle v_jaks, Text v_elud) {
		if (vend.getHp()<=0) {
			if (vend instanceof M�ngija ){
				olukord.setText(tekstid.get(9));}
			else {olukord.setText(tekstid.get(8));}
			v_jaks.setWidth(0);
			v_elud.setText("0");
			olukord.setUnderline(true);
			olukord.setFill(Color.RED);
			m�ngL�bi=true;
		}
		else{
		v_jaks.setWidth(vend.getHp());
		v_elud.setText(vend.getHp()+"");}
		
	}
	
	//logi t�itmine
	public static void logit�itmine(String txt){
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
