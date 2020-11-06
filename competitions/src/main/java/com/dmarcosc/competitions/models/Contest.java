package com.dmarcosc.competitions.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contests")
public class Contest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private boolean status;

	public Contest() {
	}
	
	public Contest(String title, String description, boolean status) {
		this.title = title;
		this.description = description;
		this.status = status;
	}
	
	public long getId() {
		return id;
	}
	
	
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "Contest [id=" + id + ", title=" + title + ", desc=" + description + ", status=" + status + "]";
	}
}
