<%@ page pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<html>
    <head>		        
    <title>4�����˂�-���X�ꗗ</title>
    <link rel="icon" type="image/png" href="pic/fav.png">
    <!--CSS-->
    <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
    <link rel="stylesheet" href="./CSS/stylesheet.css">
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp" flush="true" />
        <div class="haku"></div>
        <div class=box3>
            <jsp:include page="ContentsList.jsp" flush="true" />
            <hr size="10">
            <h1>���X�𓊍e����</h1>
            
            <form method="post" action="addres">
                <div class="migi1">
                    <input type="text" name="resName" size="16" maxlength="16" placeholder="���O(�ȗ���)">
                </div><br>
                <textarea type="text"  name="resText" placeholder="�R�����g���e" required wrap="hard"></textarea><br>
                <div class="migi">
                    <input type="submit" value="���e">
                </div>
            </form>
        </div>
        <p id="shita"></p>
        <footer></fotter>
    </body>
</html>
