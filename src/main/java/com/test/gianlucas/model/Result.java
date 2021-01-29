package com.test.gianlucas.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"adult",
"backdrop_path",
"genre_ids",
"id",
"original_language",
"original_title",
"overview",
"popularity",
"poster_path",
"release_date",
"title",
"video",
"vote_average",
"vote_count"
})
public class Result {

@JsonProperty("adult")
private boolean adult;
@JsonProperty("backdrop_path")
private String backdropPath;
@JsonProperty("genre_ids")
private List<Integer> genreIds = null;
@JsonProperty("id")
private int id;
@JsonProperty("original_language")
private String originalLanguage;
@JsonProperty("original_title")
private String originalTitle;
@JsonProperty("overview")
private String overview;
@JsonProperty("popularity")
private double popularity;
@JsonProperty("poster_path")
private String posterPath;
@JsonProperty("release_date")
private String releaseDate;
@JsonProperty("title")
private String title;
@JsonProperty("video")
private boolean video;
@JsonProperty("vote_average")
private double voteAverage;
@JsonProperty("vote_count")
private int voteCount;

@JsonProperty("adult")
public boolean isAdult() {
return adult;
}

@JsonProperty("adult")
public void setAdult(boolean adult) {
this.adult = adult;
}

@JsonProperty("backdrop_path")
public String getBackdropPath() {
return backdropPath;
}

@JsonProperty("backdrop_path")
public void setBackdropPath(String backdropPath) {
this.backdropPath = backdropPath;
}

@JsonProperty("genre_ids")
public List<Integer> getGenreIds() {
return genreIds;
}

@JsonProperty("genre_ids")
public void setGenreIds(List<Integer> genreIds) {
this.genreIds = genreIds;
}

@JsonProperty("id")
public int getId() {
return id;
}

@JsonProperty("id")
public void setId(int id) {
this.id = id;
}

@JsonProperty("original_language")
public String getOriginalLanguage() {
return originalLanguage;
}

@JsonProperty("original_language")
public void setOriginalLanguage(String originalLanguage) {
this.originalLanguage = originalLanguage;
}

@JsonProperty("original_title")
public String getOriginalTitle() {
return originalTitle;
}

@JsonProperty("original_title")
public void setOriginalTitle(String originalTitle) {
this.originalTitle = originalTitle;
}

@JsonProperty("overview")
public String getOverview() {
return overview;
}

@JsonProperty("overview")
public void setOverview(String overview) {
this.overview = overview;
}

@JsonProperty("popularity")
public double getPopularity() {
return popularity;
}

@JsonProperty("popularity")
public void setPopularity(double popularity) {
this.popularity = popularity;
}

@JsonProperty("poster_path")
public String getPosterPath() {
return posterPath;
}

@JsonProperty("poster_path")
public void setPosterPath(String posterPath) {
this.posterPath = posterPath;
}

@JsonProperty("release_date")
public String getReleaseDate() {
return releaseDate;
}

@JsonProperty("release_date")
public void setReleaseDate(String releaseDate) {
this.releaseDate = releaseDate;
}

@JsonProperty("title")
public String getTitle() {
return title;
}

@JsonProperty("title")
public void setTitle(String title) {
this.title = title;
}

@JsonProperty("video")
public boolean isVideo() {
return video;
}

@JsonProperty("video")
public void setVideo(boolean video) {
this.video = video;
}

@JsonProperty("vote_average")
public double getVoteAverage() {
return voteAverage;
}

@JsonProperty("vote_average")
public void setVoteAverage(double voteAverage) {
this.voteAverage = voteAverage;
}

@JsonProperty("vote_count")
public int getVoteCount() {
return voteCount;
}

@JsonProperty("vote_count")
public void setVoteCount(int voteCount) {
this.voteCount = voteCount;
}

}
