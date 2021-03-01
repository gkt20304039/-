<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
            <title>�X���b�h�ꗗ</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
        </head>
        <body>
            <div class=sarch>
                <form method='post' action='sarch'>
                    <p><input type='sarch' name='thSarch'><input type="submit" value="����"></p><br>
                </form>
            </div>

            <h1>�X���b�h�ꗗ</h1>
	        
	        <c:forEach var="thread" items="${threads}">
                <a href="/SBoard/addres?thID=${thread.id}">${thread.name}&ensp;${thread.date}</a><br>
            </c:forEach>

            <!--�����L���O��\��-->
            <div class="ranking">
                <h2>�����L���O</h2>
                <c:forEach var="rank" items="${ranking}" varStatus="status">
                    ${status.count}��&ensp;<a href="/SBoard/addres?thID=${rank.id}">${rank.name}(${rank.num})&ensp;�X�V�����F${rank.date}</a>
                </c:forEach>
            </div>
            
            <h1>�X���b�h���쐬����</h1>
            <form method='post' action='addth'>
            <input type='text' name='thName' size='32'maxlength='32' placeholder='�X���b�h�^�C�g��'><br>
            <input type='text' name='resName' size='16'maxlength='16' placeholder='���O(�ȗ���)'><br>
            <textarea type='text' name='resText' placeholder='�R�����g���e' required></textarea><br>
            <input type='submit' value='�쐬'>
            </form>
        </body>
    </html>