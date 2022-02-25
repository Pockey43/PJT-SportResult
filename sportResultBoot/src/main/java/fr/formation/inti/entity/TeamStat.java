package fr.formation.inti.entity;

public class TeamStat implements java.io.Serializable {
	

  
	
	@Override
	public String toString() {
		return "TeamStat [team=" + team + ", form=" + form + ", matchPlayed=" + matchPlayed + ", matchWins=" + matchWins
				+ ", matchDraws=" + matchDraws + ", matchLost=" + matchLost + ", goalFor=" + goalFor + ", goalAgainst="
				+ goalAgainst + ", serieVictoire=" + serieVictoire + "]";
	}

	private Team team;
    private String form;
    private MatchStats matchPlayed;
    private MatchStats matchWins;
    private MatchStats matchDraws;
    private MatchStats matchLost;
    private MatchStats goalFor;
    private MatchStats goalAgainst;
    private MatchStats serieVictoire;
    
	public TeamStat(String form, MatchStats matchPlayed, MatchStats matchWins,
			MatchStats matchDraws, MatchStats matchLost, MatchStats goalFor, MatchStats goalAgainst,
			MatchStats serieVictoire, Team team) {
		
		super();
		this.team = team;
		this.form = form;
		this.matchPlayed = matchPlayed;
		this.matchWins = matchWins;
		this.matchDraws = matchDraws;
		this.matchLost = matchLost;
		this.goalFor = goalFor;
		this.goalAgainst = goalAgainst;
		this.serieVictoire = serieVictoire;
	}

	public TeamStat() {
		// TODO Auto-generated constructor stub
	}

	

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public MatchStats getMatchPlayed() {
		return matchPlayed;
	}

	public void setMatchPlayed(MatchStats matchPlayed) {
		this.matchPlayed = matchPlayed;
	}

	public MatchStats getMatchWins() {
		return matchWins;
	}

	public void setMatchWins(MatchStats matchWins) {
		this.matchWins = matchWins;
	}

	public MatchStats getMatchDraws() {
		return matchDraws;
	}

	public void setMatchDraws(MatchStats matchDraws) {
		this.matchDraws = matchDraws;
	}

	public MatchStats getMatchLost() {
		return matchLost;
	}

	public void setMatchLost(MatchStats matchLost) {
		this.matchLost = matchLost;
	}

	public MatchStats getGoalFor() {
		return goalFor;
	}

	public void setGoalFor(MatchStats goalFor) {
		this.goalFor = goalFor;
	}

	public MatchStats getGoalAgainst() {
		return goalAgainst;
	}

	public void setGoalAgainst(MatchStats goalAgainst) {
		this.goalAgainst = goalAgainst;
	}

	public MatchStats getSerieVictoire() {
		return serieVictoire;
	}

	public void setSerieVictoire(MatchStats serieVictoire) {
		this.serieVictoire = serieVictoire;
	}
	
	
    
    
    
    
    
    
    
   
   

}
