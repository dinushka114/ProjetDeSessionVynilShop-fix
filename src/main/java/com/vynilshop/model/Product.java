/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vynilshop.model;

public class Product {

    private int id;
    private String name;
    private String artist;
    private Double price;
    private String image;
    private String genre;
    private String description;
    private int year;
    
    public Product() {
	}

	public Product(int id, String name, String artist, Double price, String image, String genre, String description, int year) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.price = price;
		this.image = image;
		this.genre = genre;
		this.description = description;
		this.year = year;
	}
       
         public Product( int id , String name, String artist, Double price, String genre, String description, int year) {
		super();
                this.id = id;
		this.name = name;
		this.artist = artist;
		this.price = price;
		this.genre = genre;
		this.description = description;
		this.year = year;
	}
        
        
        public Product( String name, String artist, Double price, String genre, String description, int year) {
		super();
		this.name = name;
		this.artist = artist;
		this.price = price;
		this.genre = genre;
		this.description = description;
		this.year = year;
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

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", artist=" + artist + ", price=" + price + ", image="
				+ image + ", genre=" + genre + ", description=" + description + ", year=" + year + "]";
	}

}
