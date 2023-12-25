package com.example.caps.dta;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.caps.model.Category;

import lombok.Data;

@Data
public class ProductDTO {

	
	private Long id;
	public ProductDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDTO(Long id, String name, int categoryId, double price, String car_model, String description) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.price = price;
		this.car_model = car_model;
		this.description = description;
	}
	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", categoryId=" + categoryId + ", price=" + price
				+ ", car_model=" + car_model + ", description=" + description + ", imageName=" + imageName
				+ ", getId()=" + getId() + ", getName()=" + getName() + ", getCategoryId()=" + getCategoryId()
				+ ", getPrice()=" + getPrice() + ", getCar_model()=" + getCar_model() + ", getDescription()="
				+ getDescription() + ", getImageName()=" + getImageName() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getCar_model() {
		return car_model;
	}
	public void setCar_model(String car_model) {
		this.car_model = car_model;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	private String name;
	private int categoryId;
	private double price;
	private String car_model;
	private String description;
	public ProductDTO(Long id, String name, int categoryId, double price, String car_model, String description,
			String imageName) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.price = price;
		this.car_model = car_model;
		this.description = description;
		this.imageName = imageName;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	private String imageName;
	}


