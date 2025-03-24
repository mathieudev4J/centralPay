package com.spring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Urls {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String urls;
	
	@Column
	private String shortUrl;
	
	public Urls(String origine, String shortUrl) {
		this.urls=origine;
		this.shortUrl=shortUrl;
	}
	public boolean equals(Object o) {
		if ((o instanceof Urls) && ((Urls)o).urls == urls){
			return true;
		}
		else {
			return false; 
		}
	}
	public int hashcode() {
		return urls.length();
	}
	public Urls(String url) {
		this.urls=url;
	}
	
}
