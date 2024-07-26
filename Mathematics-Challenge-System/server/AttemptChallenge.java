package server;

public class AttemptChallenge {
    private int challengeNo;
    private int participantId;
    private int marks;

    public AttemptChallenge(int challengeNo, int participantId, int marks) {
        this.challengeNo = challengeNo;
        this.participantId = participantId;
        this.marks = marks;
    }

    public void setChallengeNo(int challengeNo) {
        this.challengeNo = challengeNo;
    }

    public int getChallengeNo() {
        return challengeNo;
    }

    public void setParticipantId(int participantId) {
        this.participantId = participantId;
    }

    public int getParticipantId() {
        return participantId;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public int getMarks() {
        return marks;
    }

    // Method to get a question based on questionId (example implementation)
    public int getQuestion(int questionId) {
        // Example implementation, replace with actual logic
        return questionId;
    }

    // Method to get report (example implementation)
    public void getReport(AttemptChallenge attemptChallenge) {
        // Example implementation, replace with actual logic
        System.out.println("Challenge No: " + attemptChallenge.getChallengeNo());
        System.out.println("Participant ID: " + attemptChallenge.getParticipantId());
        System.out.println("Marks Obtained: " + attemptChallenge.getMarks());
        // Add more details as needed
    }

    // Method for re-attempting challenge (example implementation)
    public void reAttempt() {
        // Example implementation, replace with actual logic
        System.out.println("Attempting challenge again...");
        // Add logic to re-attempt challenge
    }
}
    
    
    

