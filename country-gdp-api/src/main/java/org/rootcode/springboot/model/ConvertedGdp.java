package org.rootcode.springboot.model;

public class ConvertedGdp {

	private long id;
	private String country;
	private String alpha2code;
	private String alpha3code;
	private int value;

	public ConvertedGdp(long id, String country, String alpha2code, String alpha3code, int value) {
		super();
		this.id = id;
		this.country = country;
		this.alpha2code = alpha2code;
		this.alpha3code = alpha3code;
		this.value = value;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAlpha2code() {
		return alpha2code;
	}

	public void setAlpha2code(String alpha2code) {
		this.alpha2code = alpha2code;
	}

	public String getAlpha3code() {
		return alpha3code;
	}

	public void setAlpha3code(String alpha3code) {
		this.alpha3code = alpha3code;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "ConvertedGdp [id=" + id + ", country=" + country + ", alpha2code=" + alpha2code + ", alpha3code="
				+ alpha3code + ", value=" + value + "]";
	}

}
