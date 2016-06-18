
import java.util.Scanner;

public class Mäng {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Mängija vend = new Mängija(6, 100, 10);
		Vastane õde = new Vastane(100, 16);
		System.out.println("Sa jõuad koju, kell on 11 päeval.");
		System.out.println("Oma lärmamisega äratad sa üles magava õe, kellel juhtumisi on kohutav peavalu");
		System.out.println("Sa ei tea, miks, sest sind ei olnud eile kodus. Miskipärast on ta sinu peale hullult vihane.");
		System.out.println("Te hakkate vaidlema, kuid sa ei saa väga aru, mille üle vaidlus käib, mõistad vaid, kes nüüd teisele ära pani.");
		System.out.println("Sul on külmikus üks kuuspakk ka.");
		System.out.println("1-joo, 2-ründa, 3-kaitse, 4-anna alla");
		System.out.println("3... 2... 1... Fight!");
		while (true) {
			if (õde.getHp()<=0) {
				System.out.println("Õde andis järele ja läks sulle uusi jooke tooma. Palju õnne!");
				break;
			}
			if (vend.getHp()<=0) {
				System.out.println("Sa kaotasid ja lähed häbitundega magama.");
				break;
			}
			System.out.println("Sinu jaks: "+vend.getHp());
			System.out.println("Õe jaks: "+õde.getHp());
			System.out.println("Mida sa järgmisena teed?");
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
		    	 System.out.println("Sa andsid alla. Nõrk oled! Mäng läbi.");
		    	 break;
		     }
		     else {
		    	 System.out.println("Sa mida vennas? Ise ka aru saad, mida sa teha tahad?");
		    	 System.out.println("(meeldetuletuseks: 1-joo, 2-ründa, 3-kaitse, 4-anna alla)");
		     }
		     int tegevus2 = (int) Math.round(Math.random()*1+1);
		     char õ_tegevus;
		     if (tegevus2 ==1) {
		    	 õ_tegevus='r';
		     }
		     else {
		    	 õ_tegevus='k';
		     
		     }
		     tegevus(vend, õde, v_tegevus, õ_tegevus);
		     
		   
		}
		if(scan != null) {
	        scan.close();}
		

	}
	public static void tegevus(Mängija vend, Vastane õde, char v_tegevus, char õ_tegevus) {
		if (v_tegevus=='h'){
			if (õ_tegevus=='k') {
				vend.Heal();
				}
			else {
				if (vend.getPotid()<1){
					System.out.println("Sul on joogid otsas.");
				}
				else {
				System.out.println("Õde võttis su joogi ja viskas selle aknast välja.");
				vend.setPotid(vend.getPotid()-1);
				System.out.println("Jooke alles: "+vend.getPotid());}
			}
			
		}
		else if (v_tegevus=='k') {
			if (õ_tegevus =='k') {
				System.out.println("Te mõlemad kaitsete end, keegi ei saa haiget.");
			}
			else {
				int muutuja = (int) Math.round(Math.random()*1+1);
				if (muutuja==1) {
					vend.setHp(vend.getHp()-õde.getStr()/2);
					System.out.println("Su argumendid pole piisavalt veenvad.");
				}
				else {
					System.out.println("Õde jääb hetkeks vait aga laseb siis uue hooga edasi."); //kaitse võidab
				}
				
			}
		}
		else if (v_tegevus=='r') {
			if (õ_tegevus=='k') {
				int muutuja = (int) Math.round(Math.random()*1+1);
				if (muutuja==1) {
					õde.setHp(õde.getHp()-vend.getStr()/2);
					System.out.println("Sa ei tea täpselt, mida sa ütlesid, aga õde solvus meeletult.");
				}
				else {
					System.out.println(); //kaitse võidab
					System.out.println("Õde ajab vist täitsa loogilist juttu...");
				}
			}
			else {
				int muutuja = (int) Math.round(Math.random()*1+1);
				if (muutuja==1) {
					õde.setHp(õde.getHp()-vend.getStr());
					System.out.println("Sinu hääl oli tugevam, õde lihtsalt ei jõua sinust üle rääkida.");
				}
				else {
					vend.setHp(vend.getHp()-õde.getStr());
					System.out.println("Õde karjus sinust üle ja teda väga ei huvita, mida sa ütlesid.");
				}
				
			}
		}
		
		
	}

}
