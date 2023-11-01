package com.pet.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@JsonSerialize
//@JsonIgnoreProperties(ignoreUnknown = true)
public class PetTestPojo {

	private Integer id;
	private Category category;
	private String name;
	private List<String> photoUrls;
	private List<Tags> tags;
	private String status;

	public PetTestPojo() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<String> getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;

	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public PetTestPojo(Integer id, Category category, String name, List<String> photoUrls, List<Tags> tags,
			String status) {

		this.id = id;
		this.category = category;
		this.name = name;
		this.photoUrls = photoUrls;
		this.tags = tags;
		this.status = status;
	}

	@Data
	@AllArgsConstructor(access = AccessLevel.PACKAGE)
	@NoArgsConstructor(access = AccessLevel.PACKAGE)
	@Builder
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonSerialize
	public static class Category {

		public Category() {

		}

		public Category(int id, String name) {

			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		private int id;
		private String name;

	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonSerialize
	public static class Tags {

		public Tags() {

		}

		private int id;
		private String name;

		public Tags(int id, String name) {

			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

}
