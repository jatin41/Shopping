package com.shopp.Shopping.web.dto;

public class ProductsDto {
	private Long id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ProductsDto(Long id, String name, String information, double price, byte[] data, String base64Image) {
		super();
		this.id = id;
		this.name = name;
		this.information = information;
		this.price = price;
		this.data = data;
		this.base64Image = base64Image;
	}
	private String name;
	private String information;
	private double price;
	 private byte[] data;
	 private String base64Image;

	public ProductsDto(String name, String information, double price, byte[] data, String base64Image) {
		super();
		this.name = name;
		this.information = information;
		this.price = price;
		this.data = data;
		this.base64Image = base64Image;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public ProductsDto(String name, String information, double price) {
		super();
		this.name = name;
		this.information = information;
		this.price = price;
		
		
		
	}

	//	public ProductsDto(String name, String information, double price) {
//		super();
//		this.name = name;
//		this.information = information;
//		this.price = price;
//	}
	public ProductsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	
}
