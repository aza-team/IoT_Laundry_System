package jdbc_waCount;

public class WaVO {
	private String setTime;
	private String waCount;
	
	public WaVO(String setTime, String waCount){
		this.setTime = setTime;
		this.waCount = waCount;
	}
	
	public WaVO(){}
	public String getSetTime() {
		return setTime;
	}

	public void setSetTime(String setTime) {
		this.setTime = setTime;
	}

	public String getWaCount() {
		return waCount;
	}

	public void setWaCount(String waCount) {
		this.waCount = waCount;
	}
	
	
}
