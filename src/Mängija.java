

public class M�ngija extends Vastane{
	private int potid;
	
	public M�ngija(int potid, int hp, int str) {
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
