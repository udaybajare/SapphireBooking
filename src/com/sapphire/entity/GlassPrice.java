package com.sapphire.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class GlassPrice {
	
	@Id
	private int rowIndex;
	
	private String W_KT;
	private String W_SV;
	private String PG4_KT;
	private String PG4_SV;
	private String PG6_KT;
	private String PG6_SV;
	private String SP2_KT;
	private String SP2_SV;
	private String PB_KT;
	private String PB_SV;
	private String EXE_WT;
	private String EXE_PG;
	
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int index) {
		this.rowIndex = index;
	}
	
	public String getW_KT() {
		return W_KT;
	}
	public void setW_KT(String w_KT) {
		W_KT = w_KT;
	}
	public String getW_SV() {
		return W_SV;
	}
	public void setW_SV(String w_SV) {
		W_SV = w_SV;
	}
	public String getPG4_KT() {
		return PG4_KT;
	}
	public void setPG4_KT(String pG4_KT) {
		PG4_KT = pG4_KT;
	}
	public String getPG4_SV() {
		return PG4_SV;
	}
	public void setPG4_SV(String pG4_SV) {
		PG4_SV = pG4_SV;
	}
	public String getPG6_KT() {
		return PG6_KT;
	}
	public void setPG6_KT(String pG6_KT) {
		PG6_KT = pG6_KT;
	}
	public String getPG6_SV() {
		return PG6_SV;
	}
	public void setPG6_SV(String pG6_SV) {
		PG6_SV = pG6_SV;
	}
	public String getSP2_KT() {
		return SP2_KT;
	}
	public void setSP2_KT(String sP2_KT) {
		SP2_KT = sP2_KT;
	}
	public String getSP2_SV() {
		return SP2_SV;
	}
	public void setSP2_SV(String sP2_SV) {
		SP2_SV = sP2_SV;
	}
	public String getPB_KT() {
		return PB_KT;
	}
	public void setPB_KT(String pB_KT) {
		PB_KT = pB_KT;
	}
	public String getPB_SV() {
		return PB_SV;
	}
	public void setPB_SV(String pB_SV) {
		PB_SV = pB_SV;
	}
	public String getEXE_WT() {
		return EXE_WT;
	}
	public void setEXE_WT(String eXE_WT) {
		EXE_WT = eXE_WT;
	}
	public String getEXE_PG() {
		return EXE_PG;
	}
	public void setEXE_PG(String eXE_PG) {
		EXE_PG = eXE_PG;
	}
	public GlassPrice(String w_KT, String w_SV, String pG4_KT, String pG4_SV, String pG6_KT, String pG6_SV,
			String sP2_KT, String sP2_SV, String pB_KT, String pB_SV, String eXE_WT, String eXE_PG) {
		super();
		W_KT = w_KT;
		W_SV = w_SV;
		PG4_KT = pG4_KT;
		PG4_SV = pG4_SV;
		PG6_KT = pG6_KT;
		PG6_SV = pG6_SV;
		SP2_KT = sP2_KT;
		SP2_SV = sP2_SV;
		PB_KT = pB_KT;
		PB_SV = pB_SV;
		EXE_WT = eXE_WT;
		EXE_PG = eXE_PG;
	}	
}
