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
	
	
		<p id="page-top"><a href="#wrap">��</a></p>	
		<p class="down"><a href="#shita">��</a></p>
			<div class="waku">
				<a href= "http://localhost:8080/SBoard/toppage"class="sticky">�g�b�v�y�[�W</a>
				<a href= "http://localhost:8080/SBoard/addth?bdID=newspl"class="sticky">�j���[�X����+</a>
				<a href= "http://localhost:8080/SBoard/addth?bdID=prog"class="sticky">�v���O���~���O</a>
				<a href= "http://localhost:8080/SBoard/addth?bdID=nanj" class="sticky">�Ȃ�ł�����J(�W���s�^�[)</a>
			</div>
	

            <div class=sarch>
                <form method='post' action='sarch' class="search_container">
                    <p><input type='text' name='resSarch'size="25" placeholder="�L�[���[�h����"><input type="submit"></p><br>
                </form>
                
            </div>
             
	</header>

	<div class="haku"></div>

 	
	<div class=box3>
 
            <jsp:include page="ContentsList.jsp" flush="true" />
         	<hr size="10"> 
            	<h1>���X�𓊍e����</h1> 
         
            		<form method='post' action='addres'>
           				<div class="migi1"> <input type='text' name='resName' size='16'maxlength='16' placeholder='���O(�ȗ���)'></div><br>
            			<textarea type='text'  name='resText' placeholder='�R�����g���e' required wrap='hard'></textarea><br>
            			<div class="migi"><input type='submit' value='�@���e�@'></div>
            		</form>
            
      </div>
    
     <p id="shita"></p>
     
     
     
     
    <footer></fotter>
       
</body>        
</html>