package com.lex.accounting.entity;

import java.util.List;

public class ParentCategoryEntity extends BaseEntity{

	private String name = "";
	private List<SubCategoryEntity> subList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubCategoryEntity> getSubList() {
		return subList;
	}

	public void setSubList(List<SubCategoryEntity> subList) {
		this.subList = subList;
	}
	
}
