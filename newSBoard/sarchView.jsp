<%@ page
    pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
    
        <head>
        	<link rel="icon" type="image/png" href="pic/fav.png">
            <title>�X���b�h�ꗗ</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
	        	         <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

    <script>
     $(function() {
    var topBtn = $('#page-top');    
    topBtn.hide();
    //�X�N���[����100�ɒB������{�^���\��
    $(window).scroll(function () {
        if ($(this).scrollTop() > 50) {
            topBtn.fadeIn();
        } else {
            topBtn.fadeOut();
        }
    });
    //�X�N���[�����ăg�b�v
    topBtn.click(function () {
        $('body,html').animate({
            scrollTop: 0
        }, 500);
        return false;
    });
});
$(function(){
    $("a[href^=#page-bottom]").click(function(){
        $('html, body').animate({
          scrollTop: $(document).height()
        },1500);
        return false;
    });
});
</script>
</head>
<body>
	<header class="sample">
	<img src="pic/ita2.png" alt="4�����˂�" title="4�����˂�" class="example1">
		<p class="down"><a href="#shita">��</a></p>
			<div class="waku">
				<a href= "http://localhost:8080/SBoard/toppage"class="sticky">�g�b�v�y�[�W</a>
				<a href= "http://localhost:8080/SBoard/addth?bdID=newspl"class="sticky">�j���[�X����+</a>
				<a href= "http://localhost:8080/SBoard/addth?bdID=prog"class="sticky">�v���O���~���O</a>
				<a href= "http://localhost:8080/SBoard/addth?bdID=nanj" class="sticky">�Ȃ�ł�����J(�W���s�^�[)</a>
			</div>
			 <div class=sarch>
				<form method='post' action='sarch' class="search_container">
                    <p><input type='text' name='thSarch'size="25" placeholder="�L�[���[�h����"><input type="submit"></p><br>
                </form>
            </div>
           
	
	
	</header>
	<div class="haku"></div>
            
      <div class="box3">
            <h1>��������</h1>
	        <c:if test="${not empty threads}">
	            <c:forEach var="thread" items="${threads}">
                    <a href="/SBoard/addres?thID=${thread.id}">${thread.name}&ensp;${thread.date}</a><br>
                </c:forEach>
            </c:if>

            <c:if test="${not empty responses}">
	            <c:forEach var="res" items="${responses}">
                    <table>
                        <tr class="attr"><td>${res.num}&ensp;<span>${res.name}<span></td><td>&ensp;${res.date}</td></tr>
                        <tr class="attr2"><td colspan="2">${res.text}</td></tr>
                    </table>
	            </c:forEach>
            </c:if>
            <div class=ma><a href="javascript:history.back()">[�O�̃y�[�W�ɖ߂�]</a></div>
            </div>
        </body>
    </html>