
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

	public static boolean m�ngL�bi = false;  //kontrollib, kas m�ng on l�bi
	
	//M�ngu akna ja t�� korraldus:
	
	@Override
	public void start(final Stage primaryStage) throws FileNotFoundException {
		
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
		Rectangle vend_pilt = new Rectangle(100, 150, stseen1.getWidth()/5, stseen1.getWidth()/5);
		Image vennapilt=new Image("file:\\C:\\Users\\Diana\\oop\\R�hmat��2\\src\\vend.png");
		ImageView ivend = new ImageView();
		ivend.setImage(vennapilt);
		ivend.setX(vend_pilt.getX());
		ivend.setY(vend_pilt.getY()-40);
		
		Rectangle �de_pilt = new Rectangle(vend_pilt.getX()+200, vend_pilt.getY(), 100, 100);
		Image �epilt = new Image("file:\\C:\\Users\\Diana\\oop\\R�hmat��2\\src\\�de.png");
		ImageView i�de = new ImageView();
		i�de.setImage(�epilt);
		i�de.setX(�de_pilt.getX());
		i�de.setY(�de_pilt.getY()-40);
		//iv1.setFitWidth();
		//iv1.setFitHeight(�de_pilt.getWidth());
		
		�de_pilt.setFill(Color.RED);
		final Rectangle v_jaks = new Rectangle(vend_pilt.getX(), vend_pilt.getY()+vend_pilt.getHeight()+30, vend.getHp(), 30);
		final Rectangle �_jaks = new Rectangle(�de_pilt.getX(), �de_pilt.getY()+�de_pilt.getHeight()+30, �de_pilt.getWidth(), 30);
		v_jaks.setFill(Color.GREEN);
		v_jaks.setStroke(Color.BLACK);
		�_jaks.setFill(Color.GREEN);
		�_jaks.setStroke(Color.BLACK);
			
		//venna joogipudelid
		ImageView[] pudelid = new ImageView[6];
		final Group joogid = new Group();
		Image pudelipilt= new Image("file:\\C:\\Users\\Diana\\oop\\R�hmat��2\\src\\pudel.png");
		
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
		final Text �_elud= new Text(�_jaks.getX(), �_jaks.getY()-2, �de.getHp()+"");
		
		Text taust= new Text(70, 0, tekstid.get(0)+"\n"+tekstid.get(1)+"\n"+tekstid.get(2)+
				"\n"+tekstid.get(3)+"\n"+tekstid.get(4)+"\n"+tekstid.get(5)
				);
		taust.setFont(Font.font("MV Boli", 12));
		
		//allaandmis, r�nde ja kaitse nupud
		Rectangle r�nda = new Rectangle(�de_pilt.getX(), �_jaks.getY()+�_jaks.getHeight()+30, 40, 40);
		Image r�ndepilt = new Image("file:\\C:\\Users\\Diana\\oop\\R�hmat��2\\src\\r�nda.png");
		ImageView ir�nda = new ImageView();
		ir�nda.setImage(r�ndepilt);
		ir�nda.setX(r�nda.getX());
		ir�nda.setY(r�nda.getY()-10);
		Rectangle kaitse = new Rectangle(�de_pilt.getX()+�de_pilt.getWidth()-40, �_jaks.getY()+�_jaks.getHeight()+30, 40, 40);
		Image kaitsepilt = new Image("file:\\C:\\Users\\Diana\\oop\\R�hmat��2\\src\\kaitse.png");
		ImageView ikaitse = new ImageView();
		ikaitse.setImage(kaitsepilt);
		ikaitse.setX(kaitse.getX());
		ikaitse.setY(kaitse.getY()-10);
		Rectangle l�pp = new Rectangle(kaitse.getX()+kaitse.getWidth()+30, kaitse.getY(), 50, 50); 
		Image l�pupilt = new Image("file:\\C:\\Users\\Diana\\oop\\R�hmat��2\\src\\l�pp.png");
		ImageView il�pp = new ImageView();
		il�pp.setImage(l�pupilt);
		il�pp.setX(l�pp.getX());
		il�pp.setY(l�pp.getY()-25);
		r�nda.setFill(Color.RED);
		kaitse.setFill(Color.SKYBLUE);
		//l�pp.setFill(Color.PURPLE);
		
		final Text r�nde_txt = new Text(r�nda.getX()+5, r�nda.getY()-2, "r�nda");
		final Text kaitse_txt = new Text(kaitse.getX()+5, kaitse.getY()-2, "kaitse");
		final Text l6putxt = new Text(l�pp.getX()+5, l�pp.getY()-2, "alistu");
		final Text joomine_txt = new Text(r�nda.getX()-150, r�nda.getY()+60, "Jookide varu");
		final Text �eraas = new Text(r�nda.getX()+20, vend_pilt.getY()-5, "�de");
		final Text vennakene = new Text(r�nda.getX()-150, �eraas.getY(), "Vend");
		final Text elud_txt = new Text(r�nda.getX()-90, v_elud.getY(), "Vastupanu jaks");
		
		
		//jooksev seis
		final Text olukord = new Text(70, r�nda.getY()+r�nda.getHeight()+40, tekstid.get(7));
		olukord.setFont(Font.font("MV Boli", 12));
		
		asjad.getChildren().addAll(ivend, i�de, v_jaks, �_jaks, ir�nda, ikaitse, il�pp, �_elud, v_elud, taust,
				olukord);
		juur.setCenter(asjad);
		primaryStage.show();
	    
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
						if (m�ngL�bi){sulge(juur, primaryStage);}
						
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
						if (m�ngL�bi){sulge(juur, primaryStage);}
						
					}
					else {
						vend.setHp(vend.getHp()-�de.getStr());
						olukord.setText(tekstid.get(19));
						surmaKontroll(vend, olukord, tekstid, v_jaks, v_elud);
						if (m�ngL�bi){sulge(juur, primaryStage);}
						
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
						if (m�ngL�bi){sulge(juur, primaryStage);}
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
		il�pp.setOnMousePressed(new EventHandler<MouseEvent>(){
			public void handle(MouseEvent mex) {
				olukord.setText(tekstid.get(10));
				olukord.setUnderline(true);
				olukord.setFill(Color.RED);
				m�ngL�bi=true;
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
