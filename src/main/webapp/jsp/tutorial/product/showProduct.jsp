<%@ page language="java" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<%@ page import="org.iplass.mtp.entity.Entity"%>
<%@ page import="java.util.List" %>
<%@ page import="org.iplass.tutorial.product.Product" %>
<%@ page import="org.iplass.mtp.entity.SearchResult"%>
<%@ page import="org.iplass.mtp.command.RequestContext"%>
<%@ page import="org.iplass.mtp.web.template.TemplateUtil"%>
<%
    RequestContext context = TemplateUtil.getRequestContext();
    @SuppressWarnings("unchecked")
    List<Product> list = (List<Product>) context.getAttribute("products");
%>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

    <script type="text/javascript">

        function button_onclick(oid) {
			if(confirm("Are you sure you want to delete this product ?")) {
            $.ajax({
                url: "${m:tcPath()}/" + "tutorial/product/java/deleteProduct?deleteId=" + oid,
                type: "DELETE",
                	success: function() {
                		 alert("Delete Successful!");
                         location.reload();
                    },
                    error: function() {
                    	console.log("1")
                    }
            
            })
        }
        }
        
    </script>
</head>
<body>
<a href="${m:tcPath()}/tutorial/product/java/inputProduct">
			Create Product</a>
<h2>List Product</h2>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Product Name</th>
        <th>Price</th>
        <th>Category</th>

    </tr>
    </thead>
    <tbody>
    <% for (Product e : list) { %>
  <tr>
    <td><c:out value="<%=e.getOid() %>"/></td>
        <td><c:out value="<%=e.getName() %>"/></td>
    
    <td>
		  <c:out value="<%=e.getPrice() %>"/>    
    </td>
   <td>
		  <c:out value="<%=e.getProductCategory() %>"/>    
    </td>
    <td>
            <a href="${m:tcPath()}/tutorial/product/java/inputEdit?editId=<%=e.getOid() %>">Edit</a>
            <a href="javascript:button_onclick('<%=e.getOid() %>')">Delete</a>
        </td>
  </tr>
  <%}%>
    </tbody>
</table>

</body>
</html>