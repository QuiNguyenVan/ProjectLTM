<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Chỉ import các lớp Java cần thiết --%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Bean.AdminTemplate" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh Sách Mẫu</title>
    
    <%-- Icon --%>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    
    <%-- Base css --%>
    <link href="${pageContext.request.contextPath}/Public/Admin/Assets/CSS/Style.css?v0" rel="stylesheet" type="text/css">
    
    <%-- Dùng EL cho đường dẫn CSS (Cách làm tốt) --%>
    <link href="${pageContext.request.contextPath}/Public/Admin/Assets/CSS/Template/ListTemplate.css?v0" rel="stylesheet" type="text/css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>

    <div class="container">
        <h3 class="inner-title">Danh Sách Các Mẫu</h3>
        
        <%-- Hiển thị thông báo lỗi (Nếu có, dùng EL) --%>
        <% if (request.getAttribute("errorMessage") != null) { %>
            <div style="color: red; padding: 10px; border: 1px solid red; margin-bottom: 15px;">
                Lỗi: <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>
		
        <table class="data-table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Tên Mẫu</th>
                    <th>Nội Dung (Tóm tắt)</th>
                    <th>Thao Tác</th>
                </tr>
            </thead>
            <tbody>
                
                <%
                    // 1. Lấy danh sách từ request và kiểm tra NULL
                    List<AdminTemplate> templates = (List<AdminTemplate>)request.getAttribute("templates");
                    System.out.println("List.jsp - Số lượng template: " + (templates != null ? templates.size() : "null"));
                    if (templates != null && !templates.isEmpty()) {
                        // 2. Vòng lặp bằng Scriptlet
                        for (AdminTemplate template : templates) {
                            String contentSummary = template.getContent();
                            
                            // 3. Logic cắt chuỗi bằng Scriptlet
                            if (contentSummary != null && contentSummary.length() > 80) {
                                contentSummary = contentSummary.substring(0, 80) + "...";
                            } 
                %>
                
                <tr>
                    <%-- 4. Hiển thị dữ liệu bằng Expression --%>
                    <td><%= template.getId() %></td>
                    <td><%= template.getTemplateName() %></td>
                    <td><%= contentSummary %></td>
                    <td style="text-align: center;" class="actions-cell">
                        <%-- 5. Đường dẫn action dùng kết hợp EL và Expression --%>
                        <!-- Button trigger modal -->
						<i class="fa-regular fa-eye" style="border: none; background-color: white;" data-bs-toggle="modal" data-bs-target="#exampleModal<%= template.getId() %>"></i>
						
						<!-- Modal -->
						<div class="modal fade" id="exampleModal<%= template.getId() %>" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						  <div class="modal-dialog">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1>
						        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						      </div>
						      <div class="modal-body">
						        <%= template.getContent() %>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						      </div>
						    </div>
						  </div>
						</div>
                        <form method="get" action="${pageContext.request.contextPath}/AdminEditTemplateServlet" style="display: inline;" target="_self">
						    <input type="hidden" name="templateId" value="<%= template.getId() %>">						    
						    <button type="submit" style="background: none; border: none; padding: 0; cursor: pointer;" class="action-edit">
						        <i style="font-weight: 500" class="fa-regular fa-pen-to-square"></i>
						    </button>
						</form>
						<form method="post" action="${pageContext.request.contextPath}/AdminDeleteTemplateServlet" style="display: inline;" target="_self">
						    <input type="hidden" name="templateId" value="<%= template.getId() %>">						    
						    <button type="submit" style="background: none; border: none; padding: 0; cursor: pointer;" class="action-delete">
						        <i style="font-weight: 500" class="fa-solid fa-trash-can"></i>
						    </button>
						</form>
                    </td>
                </tr>
                
                <%
                        } // Kết thúc vòng lặp
                    } else {
                %>
                
                <%-- Trường hợp không có dữ liệu --%>
                <tr>
                    <td colspan="4" style="text-align: center;">
                        <p style="margin-top: 10px; color: #888;">Chưa có bản ghi Template nào.</p>
                    </td>
                </tr>
                
                <%
                    } // Kết thúc khối if/else
                %>
                
            </tbody>
        </table>
    </div>
    
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</body>
</html>