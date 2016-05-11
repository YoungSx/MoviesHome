package link.shangxin.movies;

public class RecentMovie {
	private String tvTitle;
	private String playDate;
	private String star;
	private String director;
	private String type;
	private String story;
	private String state;
	
	public RecentMovie(String tvTitle, String playDate, String star,
			String director, String type, String story, String state) {
		super();
		this.tvTitle = tvTitle;
		this.playDate = playDate;
		this.star = star;
		this.director = director;
		this.type = type;
		this.story = story;
		this.state = state;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTvTitle() {
		return tvTitle;
	}
	public void setTvTitle(String tvTitle) {
		this.tvTitle = tvTitle;
	}
	public String getPlayDate() {
		return playDate;
	}
	public void setPlayDate(String playDate) {
		this.playDate = playDate;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	
}
