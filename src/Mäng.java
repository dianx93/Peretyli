
import java.util.Scanner;

public class M�ng {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		M�ngija vend = new M�ngija(6, 100, 10);
		Vastane �de = new Vastane(100, 16);
		System.out.println("Sa j�uad koju, kell on 11 p�eval.");
		System.out.println("Oma l�rmamisega �ratad sa �les magava �e, kellel juhtumisi on kohutav peavalu");
		System.out.println("Sa ei tea, miks, sest sind ei olnud eile kodus. Miskip�rast on ta sinu peale hullult vihane.");
		System.out.println("Te hakkate vaidlema, kuid sa ei saa v�ga aru, mille �le vaidlus k�ib, m�istad vaid, kes n��d teisele �ra pani.");
		System.out.println("Sul on k�lmikus �ks kuuspakk ka.");
		System.out.println("1-joo, 2-r�nda, 3-kaitse, 4-anna alla");
		System.out.println("3... 2... 1... Fight!");
		while (true) {
			if (�de.getHp()<=0) {
				System.out.println("�de andis j�rele ja l�ks sulle uusi jooke tooma. Palju �nne!");
				break;
			}
			if (vend.getHp()<=0) {
				System.out.println("Sa kaotasid ja l�hed h�bitundega magama.");
				break;
			}
			System.out.println("Sinu jaks: "+vend.getHp());
			System.out.println("�e jaks: "+�de.getHp());
			System.out.println("Mida sa j�rgmisena teed?");
		     String tegevus= scan.next();
		     char v_tegevus ='x';
		     if (tegevus.equals("1")) {
		    	 v_tegevus='h';
		     }
		     else if (tegevus.equals("2")) {
		    	 v_tegevus='r';
		     }
		     else if (tegevus.equals("3")) {
		    	 v_tegevus='k';
		     }
		     else if (tegevus.equals("4")) {
		    	 System.out.println("Sa andsid alla. N�rk oled! M�ng l�bi.");
		    	 break;
		     }
		     else {
		    	 System.out.println("Sa mida vennas? Ise ka aru saad, mida sa teha tahad?");
		    	 System.out.println("(meeldetuletuseks: 1-joo, 2-r�nda, 3-kaitse, 4-anna alla)");
		     }
		     int tegevus2 = (int) Math.round(Math.random()*1+1);
		     char �_tegevus;
		     if (tegevus2 ==1) {
		    	 �_tegevus='r';
		     }
		     else {
		    	 �_tegevus='k';
		     
		     }
		     tegevus(vend, �de, v_tegevus, �_tegevus);
		     
		   
		}
		if(scan != null) {
	        scan.close();}
		

	}
	public static void tegevus(M�ngija vend, Vastane �de, char v_tegevus, char �_tegevus) {
		if (v_tegevus=='h'){
			if (�_tegevus=='k') {
				vend.Heal();
				}
			else {
				if (vend.getPotid()<1){
					System.out.println("Sul on joogid otsas.");
				}
				else {
				System.out.println("�de v�ttis su joogi ja viskas selle aknast v�lja.");
				vend.setPotid(vend.getPotid()-1);
				System.out.println("Jooke alles: "+vend.getPotid());}
			}
			
		}
		else if (v_tegevus=='k') {
			if (�_tegevus =='k') {
				System.out.println("Te m�lemad kaitsete end, keegi ei saa haiget.");
			}
			else {
				int muutuja = (int) Math.round(Math.random()*1+1);
				if (muutuja==1) {
					vend.setHp(vend.getHp()-�de.getStr()/2);
					System.out.println("Su argumendid pole piisavalt veenvad.");
				}
				else {
					System.out.println("�de j��b hetkeks vait aga laseb siis uue hooga edasi."); //kaitse v�idab
				}
				
			}
		}
		else if (v_tegevus=='r') {
			if (�_tegevus=='k') {
				int muutuja = (int) Math.round(Math.random()*1+1);
				if (muutuja==1) {
					�de.setHp(�de.getHp()-vend.getStr()/2);
					System.out.println("Sa ei tea t�pselt, mida sa �tlesid, aga �de solvus meeletult.");
				}
				else {
					System.out.println(); //kaitse v�idab
					System.out.println("�de ajab vist t�itsa loogilist juttu...");
				}
			}
			else {
				int muutuja = (int) Math.round(Math.random()*1+1);
				if (muutuja==1) {
					�de.setHp(�de.getHp()-vend.getStr());
					System.out.println("Sinu h��l oli tugevam, �de lihtsalt ei j�ua sinust �le r��kida.");
				}
				else {
					vend.setHp(vend.getHp()-�de.getStr());
					System.out.println("�de karjus sinust �le ja teda v�ga ei huvita, mida sa �tlesid.");
				}
				
			}
		}
		
		
	}

}
