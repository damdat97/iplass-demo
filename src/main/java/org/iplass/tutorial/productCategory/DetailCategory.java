package org.iplass.tutorial.productCategory;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.ManagerLocator;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.entity.Entity;
import org.iplass.mtp.entity.EntityManager;
import org.iplass.mtp.entity.SearchResult;
import org.iplass.mtp.entity.query.Query;

@ActionMapping(name = "tutorial/productCategory/java/detailCategory",
        displayName = "Detail Category",
        privilaged = true,
        result = @Result(type = Result.Type.JSP,
                value = "/jsp/tutorial/productCategory/detailCategory.jsp",
                templateName = "tutorial/productCategory/java/detailCategory"))
@CommandClass(name = "tutorial/productCategory/java/DetailCategory", displayName = "Detail Category")
public class DetailCategory implements Command {
    private final String PARAM_CATEGORY_ID = "categoryId";

    @Override
    public String execute(RequestContext request) {
        String categoryId = request.getParam(PARAM_CATEGORY_ID);
        EntityManager em = ManagerLocator.getInstance().getManager(EntityManager.class);
        //Retrieving the Category Data
        SearchResult<Entity> categoryIdSearch = em.searchEntity(new Query()
                .select("oid", "name")
                .from("tutorial.product.ProductCategory")
                .where("oid" + " = " + categoryId));
        request.setAttribute("category", categoryIdSearch);
        return Constants.CMD_EXEC_SUCCESS;
    }
}
