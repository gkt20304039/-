<%@ page pageEncoding="Windows-31J"
    contentType="text/html;charset=Windows-31J" %>
    
<%--JSTL 1.1.2 core �^�O���C�u����--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <link rel="icon" type="image/png" href="./pic/kanban2.png">
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
                $("a[href^=#shita]").click(function(){
                    $('html, body').animate({
                      scrollTop: $(document).height()
                    },500);
                    return false;
                });
            });
        </script>
    </head>
    <body>
        <header class="sample">
            <a href= "/SBoard/toppage" class="example1"><img src="./pic/ita2.png" alt="4�����˂�" title="4�����˂�"></a>
            <div class="waku">
                    <a href= "/SBoard/toppage" class="sticky">�g�b�v�y�[�W</a>
                    <a href= "/SBoard/addth?bdID=newspl" class="sticky">�j���[�X����+</a>
                    <a href= "/SBoard/addth?bdID=prog" class="sticky">�v���O���~���O</a>
                    <a href= "/SBoard/addth?bdID=nanj" class="sticky">�Ȃ�ł�����J(�W���s�^�[)</a>	
            </div>
                <%-- �{�����Ă���y�[�W�ɂ���ăX���b�h�������ƃ��X�������̕\���𕪂��� --%>
                <c:if test="${thisPage == 'response' or not empty responses}">
                    <div class=sarch>
                        <form method="post" action="sarch" class="search_container">
                            <input type="sarch" name="resSarch" placeholder="�L�[���[�h����"><input type="submit" value="����">
                        </form>
                    </div>
                </c:if>

                <c:if test="${thisPage == 'thread' or not empty threads}">
                    <div class="sarch">
                        <form method="post" action="sarch" class="search_container">
                            <input type="sarch" name="thSarch" placeholder="�L�[���[�h����"><input type="submit" value="����">
                        </form>
                    </div>
                </c:if>
                <c:if test="${thisPage != 'top'}">
                    <p id="page-top"><a href="#wrap">��TOP</a></p>
		            <p class="down"><a href="#shita">�����e</a></p>
                </c:if>
            <!-- <hr sizer=�h5��> -->    
        </header>
    </body>
</html>