package link.shangxin.movies;

public class Movie {

	 private String name;
     private String wk;
     private String wboxoffice;
     private String tboxoffice;

     
     
	public Movie(String name, String wk, String wboxoffice,
			String tboxoffice) {
		super();
		this.name = name;
		this.wk = wk;
		this.wboxoffice = wboxoffice;
		this.tboxoffice = tboxoffice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWk() {
		return wk;
	}
	public void setWk(String wk) {
		this.wk = wk;
	}
	public String getWboxoffice() {
		return wboxoffice;
	}
	public void setWboxoffice(String wboxoffice) {
		this.wboxoffice = wboxoffice;
	}
	public String getTboxoffice() {
		return tboxoffice;
	}
	public void setTboxoffice(String tboxoffice) {
		this.tboxoffice = tboxoffice;
	}

}
