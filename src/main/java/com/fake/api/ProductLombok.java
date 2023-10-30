package com.fake.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductLombok {

	
	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	//Rating - static inner class
	
	@Data
	@NoArgsConstructor
	public static class Rating{
		private double rate;
		private int count;
	}
}
