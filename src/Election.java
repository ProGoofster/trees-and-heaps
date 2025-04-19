import java.util.*;

public class Election {

    private static class Candidate {
        String name;
        int votes;

        Candidate(String name, int votes) {
            this.name = name;
            this.votes = votes;
        }
    }

    private PriorityQueue<Candidate> maxHeap;
    private Map<String, Candidate> candidateMap;

    public Election() {
        // Max heap based on votes
        maxHeap = new PriorityQueue<>((a, b) -> b.votes - a.votes);
        candidateMap = new HashMap<>();
    }

    //initialize the election system with the list
    //of candidates given
    void initializeCandidates(LinkedList<String> candidates) {
        for (String candidate : candidates) {
            Candidate c = new Candidate(candidate, 0);
            maxHeap.add(c);
            candidateMap.put(candidate, c);
        }
    }

    //Simulate a vote for the specified candidate and update the priority
    //queue
    void castVote(String candidate) {
        if (!candidateMap.containsKey(candidate)) {
            System.out.println("Candidate not found!");
            return;
        }
        Candidate c = candidateMap.get(candidate);
        maxHeap.remove(c);
        c.votes++;
        maxHeap.add(c);
    }

    //Simulate a vote for a random candidate and update the priority queue.
    void castRandomVote() {
        List<String> candidates = new ArrayList<>(candidateMap.keySet());
        if (candidates.isEmpty()) return;
        String randomCandidate = candidates.get(new Random().nextInt(candidates.size()));
        castVote(randomCandidate);
    }

    //Simulate enough votes for the given candidate to win the
    //election and update the priority queue. (Note: The total number of votes should be p)
    void rigElection(String candidate){
        if (!candidateMap.containsKey(candidate)) return;

        Candidate c = candidateMap.get(candidate);
        maxHeap.remove(c);
        c.votes = Integer.MAX_VALUE; // Ensure this candidate wins
        maxHeap.add(c);
    }

    //return the top k candidates with the most votes.
    List<String> getTopKCandidates(int k){
        List<String> result = new ArrayList<>();
        List<Candidate> temp = new ArrayList<>();

        for (int i = 0; i < k && !maxHeap.isEmpty(); i++) {
            Candidate c = maxHeap.poll();
            result.add(c.name);
            temp.add(c);
        }

        // Restore the heap
        maxHeap.addAll(temp);
        return result;
    }

    //Print to console all the candidates and how many votes they got in order from
    //the candidate with the most votes to the candidate with the least amount of votes.
    void auditElection(){
        List<Candidate> temp = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            Candidate c = maxHeap.poll();
            System.out.println(c.name + ": " + c.votes + " votes");
            temp.add(c);
        }

        // Restore the heap
        maxHeap.addAll(temp);
    }
}