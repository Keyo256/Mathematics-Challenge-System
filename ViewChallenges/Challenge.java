package server;

import java.util.Date;

public class Challenge {
    private int challengeNumber;
    private String challengeName;
    private Date startDate;
    private Date  endDate;
    private int duration;
    public Challenge(int challengeNumber,String challengeName,Date startDate, Date endDate,int duration){
        this.challengeNumber = challengeNumber;
        this.challengeName = challengeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
    }
    //setters and getters
    public void setChallengeNumber(int challengeNumber){
        this.challengeNumber = challengeNumber;
    }

    public int getChallengeNumber() {
        return challengeNumber;
    }
    public void setChallengeName(String challengeName){
        this.challengeName = challengeName;
    }
    public String getChallengeName(){
        return challengeName;
    }
    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }
    public Date getStartDate(){
        return startDate;
    }
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    public Date getEndDate(){
        return endDate;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }
    // get questions for a given challenge
    public String[]getQuestions(int challengeNumber){
        return new String[0];
    }
}
