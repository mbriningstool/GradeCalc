import java.io.Serializable;
public class Grade implements Serializable {
	private static final long serialVersionUID = 1L;
	private String gradeName;
	private float totalPoints;
	private float receivedPoints;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( gradeName == null ) ? 0 : gradeName.hashCode() );
		result = prime * result + Float.floatToIntBits( totalPoints );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		Grade other = ( Grade ) obj;
		if ( gradeName == null ) {
			if ( other.gradeName != null )
				return false;
		} else if ( !gradeName.equals( other.gradeName ) )
			return false;
		if ( Float.floatToIntBits( totalPoints ) != Float.floatToIntBits( other.totalPoints ) )
			return false;
		return true;
	}
		
	public Grade ( String name , float total , float received ){
		gradeName = name;
		totalPoints = total;
		receivedPoints = received;
		while( totalPoints <= 0 ){
			totalPoints = Utility.
				getInt( "The total points must be greater than 0 please input another value. " );
		}
		while( receivedPoints < 0 ){
			receivedPoints = Utility.
				getInt( "The points received must be 0 or greater please input another value. " );
		}
				
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName( String gradeName ) {
		this.gradeName = gradeName;
	}

	public float getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints( float totalPoints ) {
		this.totalPoints = totalPoints;
	}

	public float getReceivedPoints() {
		return receivedPoints;
	}

	public void setReceivedPoints( float receivedPoints ) {
		this.receivedPoints = receivedPoints;
	}
	public String toString(){
		return gradeName + " " + receivedPoints + " / " + totalPoints;
	}
}
	