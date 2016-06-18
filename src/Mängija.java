

public class Mängija extends Vastane{
	private int potid;
	
	public Mängija(int potid, int hp, int str) {
		super(hp, str);
		this.potid = potid;
	}
	public int getPotid() {
		return potid;
	}
	public void setPotid(int potid) {
		this.potid = potid;
	}
	public void Heal() {
		if (potid>0) {
			potid--;
			super.setHp(super.getHp()+20);
		}
	}
	

}
