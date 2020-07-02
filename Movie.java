package assignment3.part1;

public class Movie {
	
	private int id;
	private String color;
	private String title;
	private int duration;
	private String dirName;
	private String act1Name;
	private String act2Name;
	private String act3Name;
	private String link;
	private String language;
	private String country;
	private String rating;
	private int year;
	private double score;
	
	private String[] values;	// using this to make toString() simpler to write


	public Movie(String string) {
		
		String[] data = string.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
		this.values = data;	// using this to make toString() simpler to write		

		this.id = stringToInt(data[0]);		
		this.color = data[1];
		this.title = data[2] ;
		this.duration = stringToInt(data[3]);
		this.dirName = data[4];
		this.act1Name = data[5];
		this.act2Name = data[6];
		this.act3Name = data[7];
		this.link = data[8];
		this.language = data[9];
		this.country = data[10];
		this.rating = data[11];
		this.year = stringToInt(data[12]);
		this.score = stringToDbl(data[13]);
		
	}
	
	// Converts string to int.
	// Returns -1 if string is empty (null value scanned)
	private int stringToInt(String s) {
		
		if (s.length() == 0)
			return -1;		
		else 
			return Integer.parseInt(s);		
	}
	
	private double stringToDbl(String s) {
		
		if (s.length() == 0)  
			return -1;		
		else 
			return Double.parseDouble(s);		
	}
	
	// Getters
	public int getId() {
		return id;
	}
	public String getColor() {
		return color;
	}
	public String getTitle() {
		return title;
	}
	public int getDuration() {
		return duration;
	}
	public String getDirName() {
		return dirName;
	}
	public String getAct1Name() {
		return act1Name;
	}
	public String getAct2Name() {
		return act2Name;
	}
	public String getAct3Name() {
		return act3Name;
	}
	public String getLink() {
		return link;
	}
	public String getLanguage() {
		return language;
	}
	public String getCountry() {
		return country;
	}
	public String getRating() {
		return rating;
	}
	public int getYear() {
		return year;
	}
	public double getScore() {
		return score;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" __________\n");
		for(String str : values) { // the array containing all of the movie's values.
			if(str.length() == 0) {
				str = "-";
			}
			buffer.append("|"+str+"\n");
		}
		
		buffer.append("\n");
		return buffer.toString();
	}
	
}
