<%@ page language="java" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<%@ page import="org.iplass.mtp.entity.Entity"%>
<%@ page import="org.iplass.mtp.entity.SearchResult"%>
<%@ page import="org.iplass.mtp.command.RequestContext"%>
<%@ page import="org.iplass.mtp.web.template.TemplateUtil"%>
<%
  RequestContext context = TemplateUtil.getRequestContext();
  @SuppressWarnings("unchecked")
  SearchResult<Entity> categories = (SearchResult<Entity>) context.getAttribute("listCategory");
%>
<html>
<head>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript">
        function button_onclick(action) {
            var $form = $("#bulkForm");
            $form.attr("action", "${m:tcPath()}/" + action).submit();
        }
        
    </script>
</head>
<body>
<h2>List Category</h2>
<form id="bulkForm" method="get" action="">
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Category</th>

  </tr>
  </thead>
  <tbody>
  <% for (Entity e : categories) { %>
  <tr>
    <td><c:out value="<%=e.getOid() %>"/></td>
    <td>
    <input type="button" value="<c:out value="<%=e.getName() %>"/>" onclick="button_onclick('tutorial/productCategory/java/detail?categoryId=%s' + <%=e.getOid() %>)"/>
      
    </td>
   
  </tr>
  <%}%>
  </tbody>
</table>
</form>
</body>
</html>