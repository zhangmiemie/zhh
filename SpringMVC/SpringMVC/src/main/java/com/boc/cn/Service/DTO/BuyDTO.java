package com.boc.cn.Service.DTO;

import java.util.List;

import com.boc.cn.Controller.VO.Product;

public class BuyDTO {
	private List<Product> buyList;
	private int total;
	public List<Product> getBuyList() {
		return buyList;
	}
	public void setBuyList(List<Product> buyList) {
		this.buyList = buyList;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
