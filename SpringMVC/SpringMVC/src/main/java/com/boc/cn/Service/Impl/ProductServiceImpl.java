package com.boc.cn.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boc.cn.Controller.VO.Product;
import com.boc.cn.Dao.ProductDao;
import com.boc.cn.Dao.TransDao;
import com.boc.cn.Service.ProductService;
import com.boc.cn.Service.DTO.BuyDTO;
import com.boc.cn.Service.DTO.ProductDTO;
import com.boc.cn.Service.DTO.ProductInfoReqDto;
import com.boc.cn.Service.DTO.TransDTO;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private TransDao transDao;

	public List<Product> getProducts(ProductInfoReqDto dto) {
		Integer userType=dto.getUserType();
		Integer userId = dto.getUserId();
		List<Product>productList = new ArrayList<Product>();
		List<ProductDTO> products = productDao.getProducts();
		try{
			if (products != null) {
				for (ProductDTO productDto : products) {
					Product product = new Product();
					PropertyUtils.copyProperties(product, productDto);
					if(userType != null){
						if (0==userType && userId == productDto.getBuyerId())
							product.setBuy(true);
						if (1 == userType
								&& productDto.getBuyerId() != null)
							product.setSell(true);
					}
					productList.add(product);
				}
			}
		}catch(Exception e){
			System.out.println("系统异常");
			return null;
		}
		return productList;
	}

	public Product getProductInfo(ProductInfoReqDto dto) {
		Product product = new Product();
		ProductDTO productDto = productDao.getProductInfo(dto.getId());
		try{
			PropertyUtils.copyProperties(product, productDto);
		}catch(Exception e){
			return null;
		}
		if(dto.getUserId()!=null && dto.getUserType()!=null){
			List<TransDTO> trans = null;
			if(1==dto.getUserType()){
				trans = transDao.getTrans(dto.getId(),null);
				if(trans.size()>0){
					product.setSell(true);
					//product.setSaleNum(trans.size());
					product.setBuyNum(trans.size());
				}else{
					product.setBuyNum(0);
				}
			}else{
				trans = transDao.getTrans(dto.getId(),dto.getUserId());
				if(trans.size()>0){
					product.setBuy(true);
					product.setBuyNum(trans.size());
					product.setBuyPrice(trans.get(0).getPrice());
				}else{
					product.setBuyNum(1);
				}
			}
		}
		return product;
	}

	public BuyDTO getBuys(int userId) {
		List<Product> buyList = new ArrayList<Product>();
		List<ProductDTO> products = productDao.getBuys(userId);
		BuyDTO buyDto = new BuyDTO();
		int total= 0;
		try{
			if (products != null) {
				for (ProductDTO productDto : products) {
					Product product = new Product();
					PropertyUtils.copyProperties(product, productDto);
					total+=productDto.getBuyPrice();
					buyList.add(product);
				}
				buyDto.setBuyList(buyList);
				buyDto.setTotal(total);
			}
		}catch(Exception e){
			System.out.println("系统异常");
			return null;
		}
		return buyDto;
	}

	public boolean deleteProduct(int id) {
		boolean flag = productDao.deleteProduct(id);
		return flag;
	}

	public Product insertProduct(ProductDTO dto) {
		boolean flag = productDao.insertProduct(dto);
		Product product = null;
		try{
			if(flag){
				product = new Product();
				PropertyUtils.copyProperties(product, dto);
				product.setId(productDao.findProduct(dto));
			}
		}catch(Exception e){
			System.out.println("交易异常");
			return null;
		}
		
		return product;
	}

	public Product updateProduct(ProductDTO dto) {
		boolean flag = productDao.updateProduct(dto);
		Product product = null;
		try{
			if(flag){
				product = new Product();
				PropertyUtils.copyProperties(product, dto);
			}
		}catch(Exception e){
			System.out.println("交易异常");
			return null;
		}
		
		return product;
	}

}
