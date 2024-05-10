import java.io.Serializable;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

public class RecordBackup {
	
	private static final long serialVersionUID = 1L;
	
	@JacksonXmlProperty(localName = "Game Title", isAttribute = false)
    private String GameTitle;
    @JacksonXmlProperty(localName = "Game Poster", isAttribute = false)
    private String GamePoster;
    @JacksonXmlProperty(localName = "Game Release Date", isAttribute = false)
    private String GameReleaseDate;
    @JacksonXmlProperty(localName = "Game Developer", isAttribute = false)
    private String GameDeveloper;
    @JacksonXmlProperty(localName = "Genre", isAttribute = false)
    private String Genre;
    @JacksonXmlProperty(localName = "Platforms", isAttribute = false)
    private String Platforms;
    @JacksonXmlProperty(localName = "Product Rating", isAttribute = false)
    private String ProductRating;
    @JacksonXmlProperty(localName = "Overall Metascore", isAttribute = false)
    private int OverallMetascore;
    @JacksonXmlProperty(localName = "Overall User Rating", isAttribute = false)
    private int OverallUserRating;
    @JacksonXmlProperty(localName = "ReviewerName", isAttribute = false)
    private String ReviewerName;
    @JacksonXmlProperty(localName = "ReviewerType", isAttribute = false)
    private String ReviewerType;
    @JacksonXmlProperty(localName = "Rating Given By The Reviewer", isAttribute = false)
    private int RatingGivenByTheReviewer;
    @JacksonXmlProperty(localName = "Review Text", isAttribute = false)
    private String ReviewText;
    
    
	public String getGameTitle() {
		return GameTitle;
	}
	public void setGameTitle(String gameTitle) {
		GameTitle = gameTitle;
	}
	public String getGamePoster() {
		return GamePoster;
	}
	public void setGamePoster(String gamePoster) {
		GamePoster = gamePoster;
	}
	public String getGameReleaseDate() {
		return GameReleaseDate;
	}
	public void setGameReleaseDate(String gameReleaseDate) {
		GameReleaseDate = gameReleaseDate;
	}
	public String getGameDeveloper() {
		return GameDeveloper;
	}
	public void setGameDeveloper(String gameDeveloper) {
		GameDeveloper = gameDeveloper;
	}
	public String getGenre() {
		return Genre;
	}
	public void setGenre(String genre) {
		Genre = genre;
	}
	public String getPlatforms() {
		return Platforms;
	}
	public void setPlatforms(String platforms) {
		Platforms = platforms;
	}
	public String getProductRating() {
		return ProductRating;
	}
	public void setProductRating(String productRating) {
		ProductRating = productRating;
	}
	public int getOverallMetascore() {
		return OverallMetascore;
	}
	public void setOverallMetascore(int overallMetascore) {
		OverallMetascore = overallMetascore;
	}
	public int getOverallUserRating() {
		return OverallUserRating;
	}
	public void setOverallUserRating(int overallUserRating) {
		OverallUserRating = overallUserRating;
	}
	public String getReviewerName() {
		return ReviewerName;
	}
	public void setReviewerName(String reviewerName) {
		ReviewerName = reviewerName;
	}
	public String getReviewerType() {
		return ReviewerType;
	}
	public void setReviewerType(String reviewerType) {
		ReviewerType = reviewerType;
	}
	public int getRatingGivenByTheReviewer() {
		return RatingGivenByTheReviewer;
	}
	public void setRatingGivenByTheReviewer(int ratingGivenByTheReviewer) {
		RatingGivenByTheReviewer = ratingGivenByTheReviewer;
	}
	public String getReviewText() {
		return ReviewText;
	}
	public void setReviewText(String reviewText) {
		ReviewText = reviewText;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
	

	
}
