package org.rootcode.springboot.model;

public class CountryGdpInput {

	private String id;
	private String name;
	private String code;
	private String year;
	private String value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CountryGdpInput [id=" + id + ", name=" + name + ", code=" + code + ", year=" + year + ", value=" + value
				+ "]";
	}
}
