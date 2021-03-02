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
	
	<center><h1>5�����˂�</h1>
	
	<a href= "http://localhost:8080/SBoard/toppage" >�g�b�v�y�[�W</a>
	<a href= "http://localhost:8080/SBoard/addth?bdID=newspl" >�j���[�X����+</a>
	<a href= "http://localhost:8080/SBoard/addth?bdID=prog" >�v���O���~���O</a>
	<a href= "http://localhost:8080/SBoard/addth?bdID=nanj" >�Ȃ�ł�����J(�W���s�^�[)</a>
	
	
	<br>

            <div class=sarch>
                <form method='post' action='sarch'>
                    <p><input type='sarch' name='resSarch'><input type="submit" value="���X����"></p><br>
                </form>
            </div>
	
	<hr sizer=�h5��>
</header>
<div class="haku">
        	

 
            <jsp:include page="ContentsList.jsp" flush="true" />
            <h1>���X�𓊍e����</h1>
         
            <form method='post' action='addres'>
            <input type='text' name='resName' size='16'maxlength='16' placeholder='���O(�ȗ���)'><br>
            <textarea type='text'  name='resText' placeholder='�R�����g���e' required wrap='hard'></textarea><br>
            <input type='submit' value='��������'>
            </form>
       
     <p id="page-top"><a href="#wrap">PAGE TOP</a></p>
    
        </body>
    </html>