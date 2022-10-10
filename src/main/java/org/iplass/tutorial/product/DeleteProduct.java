package org.iplass.tutorial.product;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.ManagerLocator;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.CommandConfig;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.webapi.WebApi;
import org.iplass.mtp.entity.query.condition.predicate.Equals;
import org.iplass.mtp.web.template.TemplateUtil;
import org.iplass.mtp.webapi.definition.MethodType;
import org.iplass.mtp.webapi.definition.RequestType;
import org.iplass.mtp.entity.*;
import org.iplass.mtp.entity.query.Query;
import org.iplass.mtp.command.annotation.webapi.RestJson;
import org.iplass.mtp.command.annotation.webapi.WebApi;
import org.iplass.tutorial.exception.EntityDataNotFoundException;
import org.iplass.tutorial.productCategory.ProductCategory;

import java.util.ArrayList;
import java.util.List;

@ActionMapping(name = "tutorial/product/java/deleteProduct",
        command = @CommandConfig(commandClass = DeleteProduct.class),
result = @Result(type= Result.Type.REDIRECT, value="dispDele"))
@WebApi(name = "tutorial/product/java/deleteProduct",
        methods = MethodType.DELETE,
        accepts = RequestType.REST_JSON,
        restJson = @RestJson(parameterName = "param"),
        responseType = "application/json",
        publicWebApi = true)
@CommandClass(name = "tutorial/product/java/deleteProduct")
public class DeleteProduct implements Command {
    public static final String RESULT_REDIRECT_URL = "redirectURL";
    EntityManager em = ManagerLocator.getInstance().getManager(EntityManager.class);


    @Override
    public String execute(RequestContext request) {
        String PARAM_DELETE_ID = "deleteId";
        String deleteId = request.getParam(PARAM_DELETE_ID);
        Entity entity = em.load(deleteId, Product.DEFINITION_NAME);
        if (entity == null) {
            throw new EntityDataNotFoundException("Product" + deleteId + " is not found.");
        }
        em.delete(entity, new DeleteOption(true));
        request.setAttribute("dispDele", TemplateUtil.getTenantContextPath() + "/tutorial/product/java/showProduct");
        return Constants.CMD_EXEC_SUCCESS;
    }

}

