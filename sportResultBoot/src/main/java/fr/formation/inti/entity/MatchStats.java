package fr.formation.inti.entity;

public class MatchStats implements java.io.Serializable {

	private Integer home;
	private Integer away;
	private Integer total;
	
	
	public Integer getHome() {
		return home;
	}


	public void setHome(Integer home) {
		this.home = home;
	}


	public Integer getAway() {
		return away;
	}


	public void setAway(Integer away) {
		this.away = away;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
	}


	public MatchStats(Integer home, Integer away, Integer total) {
		super();
		this.home = home;
		this.away = away;
		this.total = total;
	}
	
	public MatchStats(){
		
	}


	@Override
	public String toString() {
		return "MatchStats [home=" + home + ", away=" + away + ", total=" + total + "]";
	}
	
	
	
}
