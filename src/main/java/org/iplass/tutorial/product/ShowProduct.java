package org.iplass.tutorial.product;

import java.util.ArrayList;
import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.ManagerLocator;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.CommandConfig;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.command.annotation.webapi.WebApi;
import org.iplass.mtp.webapi.definition.MethodType;
import org.iplass.mtp.webapi.definition.RequestType;
import org.iplass.tutorial.productCategory.ProductCategory;
import org.iplass.mtp.entity.Entity;
import org.iplass.mtp.entity.EntityManager;
import org.iplass.mtp.entity.SearchResult;
import org.iplass.mtp.entity.query.Query;

@ActionMapping(name="tutorial/product/java/showProduct",
displayName="List product",
privilaged=true,
result=@Result(type=Type.JSP,
    value="/jsp/tutorial/product/showProduct.jsp",
    templateName="tutorial/product/java/showProduct"),
command=@CommandConfig(commandClass=ShowProduct.class)
)
@WebApi(name="tutorial/product/java/showProduct",
methods = MethodType.GET,
accepts = RequestType.REST_JSON,
responseType = "application/json",
allowRequestContentTypes = "application/json",
publicWebApi = true,
results = {
    "products"
})
@CommandClass(name="tutorial/product/java/showProduct", displayName="List Product")
public class ShowProduct implements Command{

	@Override
	public String execute(RequestContext request) {
		EntityManager em = ManagerLocator.getInstance().getManager(EntityManager.class);
        Query query = new Query().select(Product.OID, Product.NAME, Product.PRICE, ProductCategory.NAME).from(Product.DEFINITION_NAME);
        SearchResult<Entity> result = em.searchEntity(query);
        List<Product> products = new ArrayList<>();
        for (Entity entity : result.getList()) {
            Product product = new Product();
            product.setOid(entity.getOid());
            product.setName(entity.getValue(Product.NAME));
            product.setPrice(entity.getValue(Product.PRICE));
            product.setProductCategory(entity.getValue(Product.PRODUCT_CATEGORY.concat(".").concat(ProductCategory.NAME)));
            products.add(product);
        }
        
        
        request.setAttribute("products", products);
	    return Constants.CMD_EXEC_SUCCESS;
	}

}
