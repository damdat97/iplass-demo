package org.iplass.tutorial.product;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.ManagerLocator;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.CommandConfig;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.webapi.RestJson;
import org.iplass.mtp.command.annotation.webapi.WebApi;
import org.iplass.mtp.entity.Entity;
import org.iplass.mtp.entity.EntityManager;
import org.iplass.mtp.entity.SearchResult;
import org.iplass.mtp.entity.query.Query;
import org.iplass.mtp.webapi.definition.RequestType;


@ActionMapping(name="tutorial/product/java/inputEdit",
        displayName="Product Edit Registration Input Page(java)",
        privilaged=true,
        result=@Result(type= Result.Type.JSP,
                value="/jsp/tutorial/product/editProduct.jsp",
                templateName="tutorial/product/java/bulkEdit"),
        command=@CommandConfig(commandClass=InputEdit.class))
@WebApi(name="tutorial/product/java/inputEdit",
        displayName="Product Bulk Registration(java)",
        privilaged=true,
        accepts= RequestType.REST_JSON,
        restJson=@RestJson(parameterName="param"))
@CommandClass(name="tutorial/product/java/inputEdit", displayName="Product Edit Registration Display Command(java)")
public class InputEdit implements Command {
    EntityManager em = ManagerLocator.getInstance().getManager(EntityManager.class);
    @Override
    public String execute(RequestContext request) {
        String PARAM_EDIT_ID = "editId";
        String editId = request.getParam(PARAM_EDIT_ID);
        org.iplass.mtp.entity.Entity entity = em.load(editId, Product.DEFINITION_NAME);
        if (entity == null) {
            throw new org.iplass.tutorial.exception.EntityDataNotFoundException("Product" + editId + " is not found.");
        }
        SearchResult<Entity> categories = em.searchEntity(new Query()
                .select("oid", "name")
                .from("tutorial.product.ProductCategory"));
        Product product = new Product();
        product.setOid(entity.getOid());
        product.setName(entity.getValue(Product.NAME));
        product.setPrice(entity.getValue(Product.PRICE));
        request.setAttribute("categories", categories);
        request.setAttribute("product", product);
        return Constants.CMD_EXEC_SUCCESS;
    }

}
