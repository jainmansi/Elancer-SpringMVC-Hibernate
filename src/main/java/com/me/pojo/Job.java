package com.me.pojo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "job")
public class Job {

	@Id
	@GeneratedValue
	@Column(name = "jobId", unique = true, nullable = false)
	private int jobId;
	
	@Column(name ="jobTitle")
	private String jobTitle;
	
	@Column(name = "description")
	private String jobDescription;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", nullable = false)
	private JobCategory jobCategory;
	
	@Column(name = "pay")
	private int pay;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "clientId", referencedColumnName="personID")
	private Client postedBy;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="job", cascade = CascadeType.REMOVE)
	private Collection<JobApplication> applications = new ArrayList<JobApplication>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "personID", insertable = false, updatable = false)
	private Applicant assignedTo;
	
	Job(){
		this.applications = new ArrayList<JobApplication>();
	}
	
	public Job(int jobId, Client postedBy, Applicant assignedTo, JobCategory jobCategory){
		this.jobId = jobId;
		this.jobCategory = jobCategory;
		this.postedBy = postedBy;
		this.applications = new ArrayList<JobApplication>();
		this.assignedTo = assignedTo;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	public Client getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(Client postedBy) {
		this.postedBy = postedBy;
	}

	public Collection<JobApplication> getApplications() {
		return applications;
	}

	public void setApplications(Collection<JobApplication> applications) {
		this.applications = applications;
	}

	public Applicant getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(Applicant assignedTo) {
		this.assignedTo = assignedTo;
	}

	public int getJobId() {
		return jobId;
	}
	
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public JobCategory getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(JobCategory jobCategory) {
		this.jobCategory = jobCategory;
	}
	
}
