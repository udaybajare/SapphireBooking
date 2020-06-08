package com.sapphire.entity;

public class LensPrices {

	int lPrice;
	int rPrice;
	int totalPrice;

	public LensPrices() {

	}

	public LensPrices(int lPrice, int rPrice, int totalPrice) {
		super();
		this.lPrice = lPrice;
		this.rPrice = rPrice;
		this.totalPrice = totalPrice;
	}

	public int getlPrice() {
		return lPrice;
	}

	public void setlPrice(int lPrice) {
		this.lPrice = lPrice;
	}

	public int getrPrice() {
		return rPrice;
	}

	public void setrPrice(int rPrice) {
		this.rPrice = rPrice;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}	
}
