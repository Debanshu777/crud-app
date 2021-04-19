package com.Debanshu777.mayadata;

public class DetailsPojo {
	int slNo ;
    String MName ;
    int NPeople ;
    String SDate ;
    String STime ;
    String ETime ;
	public DetailsPojo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DetailsPojo(int slNo, String mName, int nPeople, String sDate, String sTime, String eTime) {
		super();
		this.slNo = slNo;
		MName = mName;
		NPeople = nPeople;
		SDate = sDate;
		STime = sTime;
		ETime = eTime;
	}
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	public String getMName() {
		return MName;
	}
	public void setMName(String mName) {
		MName = mName;
	}
	public int getNPeople() {
		return NPeople;
	}
	public void setNPeople(int nPeople) {
		NPeople = nPeople;
	}
	public String getSDate() {
		return SDate;
	}
	public void setSDate(String sDate) {
		SDate = sDate;
	}
	public String getSTime() {
		return STime;
	}
	public void setSTime(String sTime) {
		STime = sTime;
	}
	public String getETime() {
		return ETime;
	}
	public void setETime(String eTime) {
		ETime = eTime;
	}
    
}