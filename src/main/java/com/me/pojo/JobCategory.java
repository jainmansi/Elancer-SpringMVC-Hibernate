package com.me.pojo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "categorytable")
public class JobCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "categoryId", unique = true, nullable = false)
	private int categoryId;
	
	@Column(name = "categoryName")
	private String categoryName;
	
	@Column(name = "description")
	private String description;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "jobCategory")
	//@JoinColumn(name = "jobId")
    private Collection<Job> jobs = new ArrayList<Job>();
	
	JobCategory(){		
	}
	
	public JobCategory(String categoryName, String description){
		this.categoryName = categoryName;
		this.description = description;
	}
	
	public Collection<Job> getJobs() {
		return jobs;
	}

	public void setJobs(Collection<Job> jobs) {
		this.jobs = jobs;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
