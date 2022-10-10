<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp" %>

<%@ page import="org.iplass.mtp.command.RequestContext" %>
<%@ page import="org.iplass.mtp.web.template.TemplateUtil" %>
<%@ page import="org.iplass.mtp.entity.Entity" %>
<%@ page import="org.iplass.mtp.entity.SearchResult" %>
<%@ page import="org.iplass.tutorial.product.Product" %>

<%
    RequestContext context = TemplateUtil.getRequestContext();
    @SuppressWarnings("unchecked")
    SearchResult<Entity> categories = (SearchResult<Entity>) context.getAttribute("categories");
    Product product = (Product) context.getAttribute("product");

%>
<html>
<head>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
    function editProduct(webapi, id) {
    id = document.getElementById("idEdit").value;
        let name = document.getElementById("productNameEdit").value;
        let price = document.getElementById("productPriceEdit").value;
        let categoryId = document.getElementById("productCategoryEdit").value;
        let data = {
            name: name,
            price: price,
            productCategory:{
            	oid: categoryId
            }
        }
        console.log(data)
        $.ajax({
        	headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: "${m:tcPath()}/api/" + webapi + id,
            type: "PUT",
            data: JSON.stringify(data),
            success: function (data) {
                alert("Success");
                $("#bulkEdit").reset();            },
            error: function (data) {
            	console.log(data)
                alert("Error");
            }
        })
    }
</script>
<title>Edit Product</title>
</head>
<body>
<form id="bulkEdit" method="post" action="">
<input type="hidden" id="idEdit" name="id" value="<c:out value="<%=product.getOid() %>"/>"/>
<h2>Product Name</h2>
<input type="text" name="productName" id="productNameEdit" value="<c:out value="<%=product.getName() %>"/>"/>
<h2>Category</h2>
    <select name="productCategory" id="productCategoryEdit">
        <% for (Entity e : categories) { %>
        <option value="<c:out value="<%=e.getOid() %>"/>" ><c:out value="<%=e.getName() %>"/></option>
        <% } %>

    </select>
<h2>Price</h2>
<input type="text" name="productPrice" id="productPriceEdit" value="<c:out value="<%=product.getPrice() %>"/>"/>
<input type="button" value="Edit" onclick="editProduct('tutorial/product/java/editProduct/editId=', <c:out value="<%=product.getOid() %>"/>)"/>
</form>
</body>
</html>
