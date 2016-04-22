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

@Entity
@Table(name = "job")
public class Job {

	@Id
	@GeneratedValue
	@Column(name = "jobId", unique = true, nullable = false)
	private int jobId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId", nullable = false)
	private JobCategory jobCategory;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "personID")
	private Client postedBy;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="job", cascade = CascadeType.REMOVE)
	private Collection<JobApplication> applications = new ArrayList<JobApplication>();
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "personID", insertable = false, updatable = false)
	private Applicant assignedTo;
	
	Job(){
	}
	
	public Job(int jobId, Client postedBy, Applicant assignedTo, JobCategory jobCategory){
		this.jobId = jobId;
		this.jobCategory = jobCategory;
		this.postedBy = postedBy;
		this.applications = new ArrayList<JobApplication>();
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

//	public Client getPostedBy() {
//		return postedBy;
//	}
//
//	public void setPostedBy(Client postedBy) {
//		this.postedBy = postedBy;
//	}

//	public Collection<Applicant> getApplicants() {
//		return applicants;
//	}
//
//	public void setApplicants(Collection<Applicant> applicants) {
//		this.applicants = applicants;
//	}
//
//	public Applicant getAssignedTo() {
//		return assignedTo;
//	}
//
//	public void setAssignedTo(Applicant assignedTo) {
//		this.assignedTo = assignedTo;
//	}
	
}
