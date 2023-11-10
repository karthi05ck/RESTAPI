package com.fake.api;




import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductLombok {

	
	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	public ProductLombok(String title, double price, String description, String category, String image, Rating rating) {
		
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image = image;
		this.rating = rating;
	}
	
	//Rating - static inner class
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Rating{
		private double rate;
		private int count;
	}
}
