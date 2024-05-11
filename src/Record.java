import java.io.Serializable;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

public class Record implements Comparable<Record>{
	
	private static final long serialVersionUID = 1L;
	

	@JacksonXmlProperty(localName = "GameTitle", isAttribute = false)
    private String GameTitle = "default";
    @JacksonXmlProperty(localName = "GamePoster", isAttribute = false)
    private String GamePoster = "default";
    @JacksonXmlProperty(localName = "GameReleaseDate", isAttribute = false)
    private String GameReleaseDate = "default";
    @JacksonXmlProperty(localName = "GameDeveloper", isAttribute = false)
    private String GameDeveloper = "default";
    @JacksonXmlProperty(localName = "Genre", isAttribute = false)
    private String Genre = "default";
    @JacksonXmlProperty(localName = "Platforms", isAttribute = false)
    private String Platforms = "default";
    @JacksonXmlProperty(localName = "ProductRating", isAttribute = false)
    private String ProductRating = "default";
    @JacksonXmlProperty(localName = "OverallMetascore", isAttribute = false)
    private float OverallMetascore = 0;
    @JacksonXmlProperty(localName = "OverallUserRating", isAttribute = false)
    private float OverallUserRating = 0;
    @JacksonXmlProperty(localName = "ReviewerName", isAttribute = false)
    private String ReviewerName = "default";
    @JacksonXmlProperty(localName = "ReviewerType", isAttribute = false)
    private String ReviewerType = "default";
    @JacksonXmlProperty(localName = "RatingGivenByTheReviewer", isAttribute = false)
    private float RatingGivenByTheReviewer = 0;
    @JacksonXmlProperty(localName = "ReviewText", isAttribute = false)
    private String ReviewText = "default";
    
    @Override
    public String toString() {
    	return this.GameTitle+"("+this.OverallMetascore+")"+"-("+this.RatingGivenByTheReviewer+")"+"-("+this.Platforms+")";
    }
    
    public int compareTo(Record o) {
        if (this.OverallMetascore != o.getOverallMetascore()) {
          return (int)o.getOverallMetascore() - (int)this.OverallMetascore;
        }
        else {
        	if (this.RatingGivenByTheReviewer != o.getRatingGivenByTheReviewer()) {
                return (int)o.getRatingGivenByTheReviewer() - (int)this.RatingGivenByTheReviewer;
              }
              return this.GameTitle.compareTo(o.getGameTitle());
        }
      }
    
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
	public float getOverallMetascore() {
		return OverallMetascore;
	}
	public void setOverallMetascore(int overallMetascore) {
		OverallMetascore = overallMetascore;
	}
	public float getOverallUserRating() {
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
	public float getRatingGivenByTheReviewer() {
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