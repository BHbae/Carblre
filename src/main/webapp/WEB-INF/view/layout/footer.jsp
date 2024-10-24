<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<footer>
    <div id="in--footer">
        <div id="footer--logo">
            <a href="/" alt="푸터 로고">logo</a>
        </div>

        <div id="footer--container">
            <p>
                <strong>주소</strong> 부산 부산진구 중앙대로 749
            </p>
            <p>
                <strong>대표자 </strong>배병호 &nbsp; | &nbsp;
                <strong>사업자등록번호 </strong>123-68-01286 &nbsp; | &nbsp;
                <strong>법률상담접수 </strong>1688-8282&nbsp; | &nbsp;
                <strong>대표책임변호사 </strong>박태현
            </p>
            <p>Copyright © 2024, CARBLRE. All Rights Reserved.</p>
            <p>
                <a href="/privacy/disclaimer">면책공고</a>&nbsp; | &nbsp;
                <a href="/privacy/liability">유한책임</a>&nbsp; | &nbsp;
                <a href="/privacy/policy">개인정보처리방침</a>&nbsp; | &nbsp;
                <a href="/privacy/refusal">이메일무단수집거부</a>
            </p>
        </div>
    </div>
    <div id="scroll--top" onclick="window.scrollTo(0,0);">
        <a href="#">
            <div>></div>
            <span>TOP</span>
        </a>
    </div>
</footer>

<script>
    $(function () {
        $(window).scroll(function () {
            if ($(this).scrollTop() > 10) {
                $('#scroll--top').fadeIn();
            } else {
                $('#scroll--top').fadeOut();
            }
        });

    });
</script>

</body>

</html>