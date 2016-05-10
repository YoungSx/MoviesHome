package link.shangxin.movies;

public class Movie {

	 private String name;
     private String totals;
     private String statistics;
     private String averaging;
     private String attendance;
     private String people;
     private String fare;
     private String boxoffice;
     
     
     
	public Movie(String name, String totals, String statistics,
			String averaging, String attendance, String people, String fare,
			String boxoffice) {
		super();
		this.name = name;
		this.totals = totals;
		this.statistics = statistics;
		this.averaging = averaging;
		this.attendance = attendance;
		this.people = people;
		this.fare = fare;
		this.boxoffice = boxoffice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTotals() {
		return totals;
	}
	public void setTotals(String totals) {
		this.totals = totals;
	}
	public String getStatistics() {
		return statistics;
	}
	public void setStatistics(String statistics) {
		this.statistics = statistics;
	}
	public String getAveraging() {
		return averaging;
	}
	public void setAveraging(String averaging) {
		this.averaging = averaging;
	}
	public String getAttendance() {
		return attendance;
	}
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getFare() {
		return fare;
	}
	public void setFare(String fare) {
		this.fare = fare;
	}
	public String getBoxoffice() {
		return boxoffice;
	}
	public void setBoxoffice(String boxoffice) {
		this.boxoffice = boxoffice;
	}

}
