

public class M�ngija {
	private int potid;
	private int hp;
	private int str;
	public M�ngija(int potid, int hp, int str) {
		this.potid = potid;
		this.hp = hp;
		this.str = str;
	}
	public int getPotid() {
		return potid;
	}
	public void setPotid(int potid) {
		this.potid = potid;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	
	public void Heal() {
		if (potid>0) {
			potid--;
			hp=hp+20;
			System.out.println("V�tsid k�lmikust uue joogi, j�id kohe �ra ka. N��d on palju julgem olla.");
			System.out.println("Jooke alles: " + potid);
		}
		else {
			System.out.println("Sul on joogid otsas.");
		}
	}
	

}
