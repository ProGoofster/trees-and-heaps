import java.util.*;

public class ElectionSystem {

    public static void main(String[] args) {
        // Create an instance of the Election class
        Election election = new Election();

        // Initialize the election with a list of candidates
        List<String> candidates = Arrays.asList("Marcus Fenix", "Dominic Santiago", "Damon Baird", "Cole Train", "Anya Stroud");
        election.initializeCandidates(new LinkedList<>(candidates));

        // Initialize the number of electorate votes to simulate
        int p = 5;

        // Simulate casting votes
        election.castVote("Cole Train");
        election.castVote("Cole Train");
        election.castVote("Marcus Fenix");
        election.castVote("Anya Stroud");
        election.castVote("Anya Stroud");

        // Get the top 3 candidates after 5 votes
        System.out.println("Top 3 candidates after 5 votes: " + election.getTopKCandidates(3));

        // Rig the election for "Marcus Fenix"
        election.rigElection("Marcus Fenix");

        // Get the top 3 candidates after rigging the election
        System.out.println("Top 3 candidates after rigging the election: " + election.getTopKCandidates(3));

        // Audit the election to print all candidates and their votes
        System.out.println("Audit Election:");
        election.auditElection();
    }
}