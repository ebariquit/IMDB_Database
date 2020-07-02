package assignment3.part1;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;

public class Client {
	
	public static void main(String[] args) throws IOException {
		
		MoviesDB movieDB = new MoviesDB();
		HashSet<Integer> result1 = movieDB.searchByYear(2012);
		HashSet<Integer> result2 = movieDB.searchByIMDBScore(6.1);

		HashSet<Integer> intersection = new HashSet<Integer>(result1);
		intersection.retainAll(result2);
		
		
		if (intersection != null) {
			System.out.println(result1);
		}
		
		Iterator<Integer> idIterator = intersection.iterator();
		while(idIterator.hasNext()) {
			int id = idIterator.next();
			movieDB.print(id);
		}
		
	}
	
	
	
}
