package org.iplass.tutorial.product;

import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.CommandConfig;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.webapi.WebApi;

@ActionMapping(name = "tutorial/product/java/editProduct", 
displayName = "Product Edit Page(java)",
command = @CommandConfig(commandClass = EditProduct.class),
result = @Result(type = Result.Type.REDIRECT,
value = "dispEdit"))
@WebApi(name = "tutorial/product/java/editProduct", 
methods = org.iplass.mtp.webapi.definition.MethodType.PUT,
accepts = org.iplass.mtp.webapi.definition.RequestType.REST_JSON,
restJson = @org.iplass.mtp.command.annotation.webapi.RestJson(parameterName = "param"), 
responseType = "application/json",

		publicWebApi = true)
@CommandClass(name = "tutorial/product/java/editProduct")
public class EditProduct implements org.iplass.mtp.command.Command {
	org.iplass.mtp.entity.EntityManager em = org.iplass.mtp.ManagerLocator.getInstance()
			.getManager(org.iplass.mtp.entity.EntityManager.class);

	@Override
	public String execute(org.iplass.mtp.command.RequestContext request) {
		String PARAM_EDIT_ID = "editId";
		String editId = request.getParam(PARAM_EDIT_ID);
		String productName = request.getParam("productName");
        String productCategory = request.getParam("productCategory");
        String productPrice = request.getParam("productPrice");
		org.iplass.mtp.entity.Entity entity = em.load(editId, Product.DEFINITION_NAME);
		if (entity == null) {
			throw new org.iplass.tutorial.exception.EntityDataNotFoundException("Product " + editId + " is not found.");
		}
        entity.setName(productName);
        entity.setValue("price", productPrice);
        entity.setValue("productCategory", productCategory);

		
        em.update(entity, new org.iplass.mtp.entity.UpdateOption(true));
		request.setAttribute("dispEdit",
				org.iplass.mtp.web.template.TemplateUtil.getTenantContextPath() + "/tutorial/product/java/showProduct");

		return org.iplass.gem.command.Constants.CMD_EXEC_SUCCESS;
	}

}