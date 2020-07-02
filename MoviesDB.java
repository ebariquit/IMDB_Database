package assignment3.part1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class MoviesDB {

	public Movie[] movies;
	@SuppressWarnings("rawtypes")
	private Map<String, RedBlackTree> indexTreeMap = new HashMap<String, RedBlackTree>();
	
	RedBlackTree<Integer, HashSet<Integer>> yearRBT = new RedBlackTree<Integer, HashSet<Integer>>();
	RedBlackTree<Double, HashSet<Integer>> scoreRBT = new RedBlackTree<Double, HashSet<Integer>>();
	RedBlackTree<String, HashSet<Integer>> languageRBT = new RedBlackTree<String, HashSet<Integer>>();
	RedBlackTree<String, HashSet<Integer>> ratingRBT = new RedBlackTree<String, HashSet<Integer>>();	
	
	public MoviesDB() throws IOException {	
		
		FileReader fReader = new FileReader("movie_metadata.csv");
		BufferedReader reader = new BufferedReader(fReader);
		reader.readLine(); // burn header line
		
		String dataRow;
		int index = 0;
		movies = new Movie[5041];
		
		// Parse file, update movie-array and RBT's as you go.
		while ((dataRow = reader.readLine()) != null) {
			Movie movie = new Movie(dataRow);
			movies[index] = movie;
			
			// Do NOT add movies to the RBTs if they are missing the required key(s).
			if (movie.getYear() > 0) // eg: add it to yearRBT IFF year isn't null (stored as -1)
				yearRBT.put(movie.getYear(), updateHashSet_year(movie));
			
			if (movie.getScore() > 0) 
				scoreRBT.put(movie.getScore(), updateHashSet_score(movie));
			
			if (movie.getLanguage().length() > 0) 
				languageRBT.put(movie.getLanguage(), updateHashSet_language(movie));
			
			if (movie.getRating().length() > 0)
				ratingRBT.put(movie.getRating(), updateHashSet_rating(movie));
			
			index++;
		}
		
		indexTreeMap.put("year", yearRBT);
		indexTreeMap.put("score", scoreRBT);
		indexTreeMap.put("language", languageRBT);
		indexTreeMap.put("rating", ratingRBT);

		reader.close();
	}
	
	public void print(int id) {
		System.out.println(movies[id-1]);
	}
	
	public HashSet<Integer> searchByYear(int year) {
		
		@SuppressWarnings("unchecked")
		RedBlackTree<Integer, HashSet<Integer>> search = indexTreeMap.get("year");
		return search.get(year);
	}
	
	public HashSet<Integer> searchByIMDBScore(double score) {

		@SuppressWarnings("unchecked")
		RedBlackTree<Double, HashSet<Integer>> search = indexTreeMap.get("score");
		return search.get(score);
	}
	
	public HashSet<Integer> searchByLanguage(String language) {

		@SuppressWarnings("unchecked")
		RedBlackTree<String, HashSet<Integer>> search = indexTreeMap.get("language");
		return search.get(language);
	}
	
	public HashSet<Integer> serachByContentRating(String rating) {

		@SuppressWarnings("unchecked")
		RedBlackTree<String, HashSet<Integer>> search = indexTreeMap.get("rating");
		return search.get(rating);
	}

	/*
	 *  'updateHashSet_x()' methods check xRBT for an existing node with specified key.
	 *  eg: does yearRBT have a node for 2016 (the year of the movie-parameter)?
	 *  If this node exists, copy and add to its HashSet of movie ID's.
	 *  If not, make a new HashSet.
	 *  
	 */
	
	private HashSet<Integer> updateHashSet_year(Movie movie) {
		HashSet<Integer> movieIDs = new HashSet<Integer>();
		
		int year = movie.getYear();
		int id = movie.getId();
		
		// If there is no node for this year in the RBT
		if(yearRBT.get(year) == null)
			movieIDs.add(id);
		
		else {
			// Copy the set stored at the node and add to it
			movieIDs = yearRBT.get(year);
			movieIDs.add(id);
		}
		
		return movieIDs;
	}
	
	private HashSet<Integer> updateHashSet_score(Movie movie) {
		HashSet<Integer> movieIDs = new HashSet<Integer>();
		
		double score = movie.getScore();
		int id = movie.getId();
		
		// If there is no node for this year in the RBT
		if(scoreRBT.get(score) == null)
			movieIDs.add(id);
		
		else {
			// Copy the set stored at the node and add to it
			movieIDs = scoreRBT.get(score);
			movieIDs.add(id);
		}
		
		return movieIDs;
	}
	
	private HashSet<Integer> updateHashSet_language(Movie movie) {
		HashSet<Integer> movieIDs = new HashSet<Integer>();
		
		String language = movie.getLanguage();
		int id = movie.getId();
		
		// If there is no node for this year in the RBT
		if(languageRBT.get(language) == null)
			movieIDs.add(id);
		
		else {
			// Copy the set stored at the node and add to it
			movieIDs = languageRBT.get(language);
			movieIDs.add(id);
		}
		
		return movieIDs;
	}
	
	private HashSet<Integer> updateHashSet_rating(Movie movie) {
		HashSet<Integer> movieIDs = new HashSet<Integer>();
		
		String rating = movie.getRating();
		int id = movie.getId();
		
		// If there is no node for this year in the RBT
		if(ratingRBT.get(rating) == null)
			movieIDs.add(id);
		
		else {
			// Copy the set stored at the node and add to it
			movieIDs = ratingRBT.get(rating);
			movieIDs.add(id);
		}
		
		return movieIDs;
	}
	
}
