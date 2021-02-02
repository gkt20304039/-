<%@ page pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core タグライブラリ--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head><title>レス投稿</title></head>
        <body>
            <jsp:include page="ContentsList.jsp" flush="true" />
            <h1>レスを投稿する</h1>
            <form method='post' action='addres'>
            名前<input type='text' name='resName'><br>
            コメント内容<input type='text' name='resText'><br>
            <input type='submit' value='投稿'>
            </form>
        </body>
    </html>