
public class Grade {
	
	private String gradeName;
	private float totalPoints;
	private float receivedPoints;
	
	public Grade (String name, float total, float received){
		gradeName = name;
		totalPoints = total;
		receivedPoints = received;
		while( totalPoints <= 0){
			totalPoints = Utility.getInt("Error G14. The total points must be greater than 0 please input another value. ");
		}
		while( receivedPoints < 0){
			receivedPoints = Utility.getInt("Error G17. The points received must be 0 or greater please input another value. ");
		}
				
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public float getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(float totalPoints) {
		this.totalPoints = totalPoints;
	}

	public float getReceivedPoints() {
		return receivedPoints;
	}

	public void setReceivedPoints(float receivedPoints) {
		this.receivedPoints = receivedPoints;
	}
	public String toString(){
		return gradeName + " " + receivedPoints + " / " + totalPoints;
	}
}
	