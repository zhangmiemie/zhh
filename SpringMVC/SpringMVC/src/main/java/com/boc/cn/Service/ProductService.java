package com.boc.cn.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.boc.cn.Controller.VO.Product;
import com.boc.cn.Service.DTO.BuyDTO;
import com.boc.cn.Service.DTO.ProductDTO;
import com.boc.cn.Service.DTO.ProductInfoReqDto;

@Service
public interface ProductService {
	public List<Product> getProducts(ProductInfoReqDto dto);
	public Product getProductInfo(ProductInfoReqDto dto);
	public BuyDTO getBuys(int userId);
	public boolean deleteProduct(int id);
	public Product insertProduct(ProductDTO dto);
	public Product updateProduct(ProductDTO dto);
}
