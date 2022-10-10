package org.iplass.tutorial.productCategory;

import org.iplass.mtp.entity.Entity;
import org.iplass.mtp.entity.GenericEntity;

/**
 * ProductCategory Entity。
 */
public class ProductCategory extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "tutorial.product.ProductCategory";

	/** Parent Category */
	public static final String PARENT_CATEGORY = "parentCategory";

	public ProductCategory() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * Parent Categoryを返します。
	 * 
	 * @return Parent Category
	 */
	public Entity getParentCategory() {
		return getValue(PARENT_CATEGORY);
	}

	/**
	 * Parent Categoryを設定します。
	 * 
	 * @param parentCategory Parent Category
	 */
	public void setParentCategory(Entity parentCategory) {
		setValue(PARENT_CATEGORY, parentCategory);
	}

}
