package by.evidences.domain;

import by.evidences.dao.Identified;

public class Blog implements Identified<Integer> {
	
	private Integer id;
	private String blogNews;
	private String blogTitle;
	private Integer author;

	public Blog() {
		
	}
	
	public Blog(String blogNews, String blogTitle, Integer author) {
		super();

		this.blogNews = blogNews;
		this.blogTitle = blogTitle;
		this.author = author;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer blogId) {
		this.id = blogId;
	}
	
	public String getBlogNews() {
		return blogNews;
	}
	
	public void setBlogNews(String blogNews) {
		this.blogNews = blogNews;
	}
	
	public String getBlogTitle() {
		return blogTitle;
	}
	
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	
	public Integer getAuthor() {
		return author;
	}
	
	public void setAuthor(int author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Blog [id=" + id + ", blogNews=" + blogNews + ", blogTitle=" + blogTitle + ", author=" + author + "]";
	}
}
