package com.sapphire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GlassPriceReadyStock {
	
	@Id
	int Id;
	
	 private String Sph;
	 private String Cyl;
	 private String W_SV_NN;
	 private String W_SV_PP;
	 private String W_SV_PN;
	 private String PG_SV_NN;
	 private String PG_SV_PP;
	 private String PG_SV_PN;
	 private String W_KT_P;
	 private String W_KT_N;
	 private String PG_KT_P;
	 private String PG_KT_N;
	 private String W_KT;
	 private String PG_KT;
	
	 
	public GlassPriceReadyStock() {
	
		
	}

	
	
	public GlassPriceReadyStock(String sph, String cyl, String w_SV_NN,
			String w_SV_PP, String w_SV_PN, String pG_SV_NN, String pG_SV_PP,
			String pG_SV_PN, String w_KT_P, String w_KT_N, String pG_KT_P,
			String pG_KT_N, String w_KT, String pG_KT) {
		super();
		Sph = sph;
		Cyl = cyl;
		W_SV_NN = w_SV_NN;
		W_SV_PP = w_SV_PP;
		W_SV_PN = w_SV_PN;
		PG_SV_NN = pG_SV_NN;
		PG_SV_PP = pG_SV_PP;
		PG_SV_PN = pG_SV_PN;
		W_KT_P = w_KT_P;
		W_KT_N = w_KT_N;
		PG_KT_P = pG_KT_P;
		PG_KT_N = pG_KT_N;
		W_KT = w_KT;
		PG_KT = pG_KT;
	}



	



	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getSph() {
		return Sph;
	}

	public void setSph(String sph) {
		Sph = sph;
	}

	public String getCyl() {
		return Cyl;
	}

	public void setCyl(String cyl) {
		Cyl = cyl;
	}

	public String getW_SV_NN() {
		return W_SV_NN;
	}

	public void setW_SV_NN(String w_SV_NN) {
		W_SV_NN = w_SV_NN;
	}

	public String getW_SV_PP() {
		return W_SV_PP;
	}

	public void setW_SV_PP(String w_SV_PP) {
		W_SV_PP = w_SV_PP;
	}

	public String getW_SV_PN() {
		return W_SV_PN;
	}

	public void setW_SV_PN(String w_SV_PN) {
		W_SV_PN = w_SV_PN;
	}

	public String getPG_SV_NN() {
		return PG_SV_NN;
	}

	public void setPG_SV_NN(String pG_SV_NN) {
		PG_SV_NN = pG_SV_NN;
	}

	public String getPG_SV_PP() {
		return PG_SV_PP;
	}

	public void setPG_SV_PP(String pG_SV_PP) {
		PG_SV_PP = pG_SV_PP;
	}

	public String getPG_SV_PN() {
		return PG_SV_PN;
	}

	public void setPG_SV_PN(String pG_SV_PN) {
		PG_SV_PN = pG_SV_PN;
	}

	public String getW_KT_P() {
		return W_KT_P;
	}

	public void setW_KT_P(String w_KT_P) {
		W_KT_P = w_KT_P;
	}

	public String getW_KT_N() {
		return W_KT_N;
	}

	public void setW_KT_N(String w_KT_N) {
		W_KT_N = w_KT_N;
	}

	public String getPG_KT_P() {
		return PG_KT_P;
	}

	public void setPG_KT_P(String pG_KT_P) {
		PG_KT_P = pG_KT_P;
	}

	public String getPG_KT_N() {
		return PG_KT_N;
	}

	public void setPG_KT_N(String pG_KT_N) {
		PG_KT_N = pG_KT_N;
	}

	public String getW_KT() {
		return W_KT;
	}

	public void setW_KT(String w_KT) {
		W_KT = w_KT;
	}

	public String getPG_KT() {
		return PG_KT;
	}

	public void setPG_KT(String pG_KT) {
		PG_KT = pG_KT;
	}
	
	
	
	
	 
	 

}
