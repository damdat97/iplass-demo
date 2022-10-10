package org.iplass.tutorial.product;

import org.iplass.mtp.entity.Entity;
import org.iplass.mtp.entity.GenericEntity;

/**
 * Product Entity。
 */
public class Product extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "tutorial.product.Product";

	/** Price */
	public static final String PRICE = "price";
	/** ProductCategory */
	public static final String PRODUCT_CATEGORY = "productCategory";

	public Product() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * Priceを返します。
	 * 
	 * @return Price
	 */
	public Long getPrice() {
		return getValue(PRICE);
	}

	/**
	 * Priceを設定します。
	 * 
	 * @param price Price
	 */
	public void setPrice(Long price) {
		setValue(PRICE, price);
	}

	/**
	 * ProductCategoryを返します。
	 * 
	 * @return ProductCategory
	 */
	public Entity getProductCategory() {
		return getValue(PRODUCT_CATEGORY);
	}

	/**
	 * ProductCategoryを設定します。
	 * 
	 * @param productCategory ProductCategory
	 */
	public void setProductCategory(Entity productCategory) {
		setValue(PRODUCT_CATEGORY, productCategory);
	}
	
	

}
