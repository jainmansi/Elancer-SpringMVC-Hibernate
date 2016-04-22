package com.me.pojo;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "client")
@PrimaryKeyJoinColumn(name = "personID")
public class Client extends Person{

		
}
