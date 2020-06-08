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
	
	private String W_PR;
	private String W_DB;
	private String PG_PR;
	private String PG_DB;

	private String W_PR_ARC;
	private String W_DB_ARC;
	private String PG_PR_ARC;
	private String PG_DB_ARC;
	
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
	
	public String getW_PR() {
		return W_PR;
	}
	public void setW_PR(String w_PR) {
		W_PR = w_PR;
	}
	public String getW_DB() {
		return W_DB;
	}
	public void setW_DB(String w_DB) {
		W_DB = w_DB;
	}
	public String getPG_PR() {
		return PG_PR;
	}
	public void setPG_PR(String pG_PR) {
		PG_PR = pG_PR;
	}
	public String getPG_DB() {
		return PG_DB;
	}
	public void setPG_DB(String pG_DB) {
		PG_DB = pG_DB;
	}
	public String getW_PR_ARC() {
		return W_PR_ARC;
	}
	public void setW_PR_ARC(String w_PR_ARC) {
		W_PR_ARC = w_PR_ARC;
	}
	public String getW_DB_ARC() {
		return W_DB_ARC;
	}
	public void setW_DB_ARC(String w_DB_ARC) {
		W_DB_ARC = w_DB_ARC;
	}
	public String getPG_PR_ARC() {
		return PG_PR_ARC;
	}
	public void setPG_PR_ARC(String pG_PR_ARC) {
		PG_PR_ARC = pG_PR_ARC;
	}
	public String getPG_DB_ARC() {
		return PG_DB_ARC;
	}
	public void setPG_DB_ARC(String pG_DB_ARC) {
		PG_DB_ARC = pG_DB_ARC;
	}
	
	public GlassPrice()
	{
		super();
	}
	
	public GlassPrice(String w_KT, String w_SV, String pG4_KT, String pG4_SV, String pG6_KT, String pG6_SV,
			String sP2_KT, String sP2_SV, String pB_KT, String pB_SV, String w_PR, String w_DB, String pG_PR,
			String pG_DB, String w_PR_ARC, String w_DB_ARC, String pG_PR_ARC, String pG_DB_ARC) {
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
		W_PR = w_PR;
		W_DB = w_DB;
		PG_PR = pG_PR;
		PG_DB = pG_DB;
		W_PR_ARC = w_PR_ARC;
		W_DB_ARC = w_DB_ARC;
		PG_PR_ARC = pG_PR_ARC;
		PG_DB_ARC = pG_DB_ARC;
	}	
}
