<%@ page pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <title>���X���e</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
        </head>
        <body>
            <div class=sarch>
                <form method='post' action='sarch'>
                    <p><input type='sarch' name='resSarch'><input type="submit" value="����"></p><br>
                </form>
            </div>

            <jsp:include page="ContentsList.jsp" flush="true" />
            <h1>���X�𓊍e����</h1>
            <form method='post' action='addres'>
            <input type='text' name='resName' size='16'maxlength='16' placeholder='���O(�ȗ���)'><br>
            <textarea type='text' name='resText' placeholder='�R�����g���e' required></textarea><br>
            <input type='submit' value='��������'>
            </form>
        </body>
    </html>