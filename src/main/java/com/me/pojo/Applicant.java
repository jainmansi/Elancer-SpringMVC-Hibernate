package com.me.pojo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "applicant")
@PrimaryKeyJoinColumn(name = "personID")
public class Applicant extends Person{
	
	
}
