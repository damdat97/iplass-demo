package org.iplass.tutorial.productCategory;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.ManagerLocator;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.CommandConfig;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.entity.Entity;
import org.iplass.mtp.entity.EntityManager;
import org.iplass.mtp.entity.SearchResult;
import org.iplass.mtp.entity.query.Query;

@ActionMapping(name="tutorial/productCategory/java/showCategory",
displayName="List Category",
privilaged=true,
result=@Result(type=Type.JSP,
    value="/jsp/tutorial/productCategory/listCategory.jsp",
    templateName="tutorial/productCategory/java/showCategory"),
command=@CommandConfig(commandClass=ShowCategory.class)
)
@CommandClass(name="tutorial/product/java/showCategory", displayName="List Category")
public class ShowCategory implements Command{

	@Override
	public String execute(RequestContext request) {
		EntityManager em = ManagerLocator.getInstance().getManager(EntityManager.class);

	    SearchResult<Entity> listCategory = em.searchEntity(new Query()
	        .select("oid", "name")
	        .from("tutorial.product.ProductCategory"));
	    request.setAttribute("listCategory", listCategory);
	    
	    return Constants.CMD_EXEC_SUCCESS;
	}

}
