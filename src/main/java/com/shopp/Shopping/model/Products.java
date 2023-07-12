package com.shopp.Shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Products {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String name;
		private String information;
		private double price;
		private int quantity;
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public Products(Long id, String name, String information, double price, int quantity, byte[] data) {
			super();
			this.id = id;
			this.name = name;
			this.information = information;
			this.price = price;
			this.quantity = quantity;
			this.data = data;
		}
		@Column(columnDefinition = "bytea")
		private byte[] data;

		
		
		
		
		public Products(Long id, String name, String information, double price, byte[] imageData) {
			super();
			this.id = id;
			this.name = name;
			this.information = information;
			this.price = price;
			this.data = imageData;
		}
		public Products() {
			super();
			// TODO Auto-generated constructor stub
		}
//		public Products(Long id, String name, String information, double price) {
//			super();
//			this.id = id;
//			this.name = name;
//			this.information = information;
//			this.price = price;
//		}
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
		public byte[] getData() {
			return data;
		}
		public void setData(byte[] data) {
			this.data = data;
		}
		
		
}
