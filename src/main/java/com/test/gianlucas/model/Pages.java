package com.test.gianlucas.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"page",
"results",
"total_pages",
"total_results"
})
public class Pages {

	@JsonProperty("page")
	private int page;
	@JsonProperty("results")
	private List<Result> results = null;
	@JsonProperty("total_pages")
	private int totalPages;
	@JsonProperty("total_results")
	private int totalResults;
	
	@JsonProperty("page")
	public int getPage() {
	return page;
	}
	
	@JsonProperty("page")
	public void setPage(int page) {
	this.page = page;
	}
	
	@JsonProperty("results")
	public List<Result> getResults() {
	return results;
	}
	
	@JsonProperty("results")
	public void setResults(List<Result> results) {
	this.results = results;
	}
	
	@JsonProperty("total_pages")
	public int getTotalPages() {
	return totalPages;
	}
	
	@JsonProperty("total_pages")
	public void setTotalPages(int totalPages) {
	this.totalPages = totalPages;
	}
	
	@JsonProperty("total_results")
	public int getTotalResults() {
	return totalResults;
	}
	
	@JsonProperty("total_results")
	public void setTotalResults(int totalResults) {
	this.totalResults = totalResults;
	}

}
