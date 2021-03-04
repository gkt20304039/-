<%@ page pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <html>
        <head>
        <link rel="icon" type="image/png" href="pic/fav.png">
            <title>4�����˂�</title>
            <!--CSS-->
	        <link rel="stylesheet" href="https://unpkg.com/ress/dist/ress.min.css">
	        <link rel="stylesheet" href="CSS/stylesheet.css">
	         <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	        <script>
	         $(window).scroll(function () {
                    sessionStorage.scrollTop = $(this).scrollTop();
                });
                $(document).ready(function () {
                    if (sessionStorage.scrollTop != "undefined") {
                        $(window).scrollTop(sessionStorage.scrollTop);
                    }
                });
                
                
	


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
	
	
		<a href= "http://localhost:8080/SBoard/toppage"><img src="pic/ita2.png" alt="4�����˂�" title="4�����˂�" class="example1"></a>
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

        <center> <img src="pic/ita.png" alt="4�����˂�" title="4�����˂�" class="">
           <h1 class="moji2">���悤����4�����˂�i���j��</h1></center>
           
     
          
				<div class="box4">
					<h2>�����L���O</h2>
                            
					<c:forEach var="rank" items="${ranking}" varStatus="status">                
						${status.count}��&ensp;
						<a href="/SBoard/addres?thID=${rank.id}">${rank.name}(${rank.num})&ensp;�X�V�����F${rank.date} </a>
						<br>
					</c:forEach>
                           
            
            
            
            	</div>
            
    </body>
 </html>