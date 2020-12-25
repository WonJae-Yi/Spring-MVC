package com.yiwon.web.entity;

public class EncJumin {
	
	public String getCustno() {
		return custno;
	}

	public void setCustno(String custno) {
		this.custno = custno;
	}

	public String getEncJumin() {
		return encJumin;
	}

	public void setEncJumin(String encJumin) {
		this.encJumin = encJumin;
	}

	private String custno;
	private String encJumin;
	
	
	public EncJumin() {
		
	}
	
	public EncJumin(String custno, String encJumin) {
		this.custno = custno;
		this.encJumin = encJumin;
	}
	
	

}
