package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import entities.Candidate;

public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		List<Candidate> collection = new ArrayList<>();
		Map<String, Integer> election = new TreeMap<>();
		Candidate candidate = null;
		
		System.out.print("Enter file full path: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			String line = br.readLine();
			while (line != null) {
				String[] list = line.split(",");
				String name = list[0];
				int vote = Integer.parseInt(list[1]);
			
				if (election.isEmpty() == true || election.containsKey(name) == false) {
					candidate = new Candidate(name, vote);
					election.put(candidate.getName(), candidate.getVote());
				}
				else {
					int votesSoFar = election.get(name);
					candidate = new Candidate(name, vote + votesSoFar);
					election.put(candidate.getName(), candidate.getVote());
				}
				line = br.readLine();
			}
			
			System.out.println();
			
			for (String key : election.keySet()) {
				System.out.println(key + ": " + election.get(key));
			}	
		}
		catch (RuntimeException e) {
			System.out.println("Error: " + e.getMessage());
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

}
