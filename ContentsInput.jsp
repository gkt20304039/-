<%@ page pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head><title>���X���e</title></head>
        <body>
            <jsp:include page="ContentsList.jsp" flush="true" />
            <h1>���X�𓊍e����</h1>
            <form method='post' action='addres'>
            ���O<input type='text' name='resName'><br>
            �R�����g���e<input type='text' name='resText'><br>
            <input type='submit' value='���e'>
            </form>
        </body>
    </html>