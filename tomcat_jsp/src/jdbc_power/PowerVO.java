package jdbc_power;

public class PowerVO {
	private String macineNum;
	private String setCheck;
	
	public PowerVO(String macineNum, String setCheck){
		this.macineNum = macineNum;
		this.setCheck = setCheck;
	}
	
	public PowerVO(){}
	
	public String getMacineNum() {
		return macineNum;
	}
	public void setMacineNum(String macineNum) {
		this.macineNum = macineNum;
	}
	public String getSetCheck() {
		return setCheck;
	}
	public void setSetCheck(String setCheck) {
		this.setCheck = setCheck;
	}
	
}
