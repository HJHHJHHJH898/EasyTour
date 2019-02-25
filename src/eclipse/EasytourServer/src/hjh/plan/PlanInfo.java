package hjh.plan;

public class PlanInfo {
	private String dest;
	private String starttime;
	private String endtime;
	private String choice_luggage;
	
	
	public PlanInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlanInfo(String dest, String starttime, String endtime, String choice_luggage) {
		super();
		this.dest = dest;
		this.starttime = starttime;
		this.endtime = endtime;
		this.choice_luggage = choice_luggage;
	}
	@Override
	public String toString() {
		return "PlanInfo [dest=" + dest + ", starttime=" + starttime + ", endtime=" + endtime + ", choice_luggage="
				+ choice_luggage + ", hashCode()=" + hashCode() + ", getDest()=" + getDest() + ", getStarttime()="
				+ getStarttime() + ", getEndtime()=" + getEndtime() + ", getChoice_luggage()=" + getChoice_luggage()
				+ ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((choice_luggage == null) ? 0 : choice_luggage.hashCode());
		result = prime * result + ((dest == null) ? 0 : dest.hashCode());
		result = prime * result + ((endtime == null) ? 0 : endtime.hashCode());
		result = prime * result + ((starttime == null) ? 0 : starttime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlanInfo other = (PlanInfo) obj;
		if (choice_luggage == null) {
			if (other.choice_luggage != null)
				return false;
		} else if (!choice_luggage.equals(other.choice_luggage))
			return false;
		if (dest == null) {
			if (other.dest != null)
				return false;
		} else if (!dest.equals(other.dest))
			return false;
		if (endtime == null) {
			if (other.endtime != null)
				return false;
		} else if (!endtime.equals(other.endtime))
			return false;
		if (starttime == null) {
			if (other.starttime != null)
				return false;
		} else if (!starttime.equals(other.starttime))
			return false;
		return true;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getChoice_luggage() {
		return choice_luggage;
	}
	public void setChoice_luggage(String choice_luggage) {
		this.choice_luggage = choice_luggage;
	}
	
	
}
