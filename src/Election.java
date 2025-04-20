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
    // O(n log n) time, n for the loop, log n for adding to maxHeap
    // O(n) space, because the memory grows proportionally with the amount of input
    void initializeCandidates(LinkedList<String> candidates) {
        for (String candidate : candidates) {
            Candidate c = new Candidate(candidate, 0);
            maxHeap.add(c);
            candidateMap.put(candidate, c);
        }
    }

    //Simulate a vote for the specified candidate and update the priority
    //queue
    // o(n) time, remove from maxHeap is o(n) in java,
    // o(1), no new storage
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
    // o(n) time same as cast vote
    // o(n) space new arraylist
    void castRandomVote() {
        List<String> candidates = new ArrayList<>(candidateMap.keySet());
        if (candidates.isEmpty()) return;
        String randomCandidate = candidates.get(new Random().nextInt(candidates.size()));
        castVote(randomCandidate);
    }

    //Simulate enough votes for the given candidate to win the
    //election and update the priority queue. (Note: The total number of votes should be p)
    // O(n) time because maxheap remove
    // O(1) space, amount of memory doesn't grow
    void rigElection(String candidate){
        if (!candidateMap.containsKey(candidate)) return;

        // Find the current highest vote count
        int maxVotes = 0;
        for (Candidate c : maxHeap) {
            maxVotes = Math.max(maxVotes, c.votes);
        }

        // Update the selected candidate's votes to be one more than the highest
        Candidate c = candidateMap.get(candidate);
        maxHeap.remove(c);
        c.votes = maxVotes + 1;
        maxHeap.add(c);
    }

    //return the top k candidates with the most votes.
    // O(k log n) time where k is integer k
    // O(k) space
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
    // time O(n log n) n for loop, log n for add
    // space O(n), for the list
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