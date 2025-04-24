<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>글쓰기 버튼 클릭시의 동작 구현</title>

    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link rel="stylesheet" href="/static/css/notice_board_reg.css">
    <script defer src="/static/js/admin/notice/board/reg.js"></script>
</head>

<body>
   <!--
      JSP 내부 스크립트 내에서는 EL 표현식이 정상적으로 인식되지만, 외부 스크립트 내에서는
      EL 표현식이 정상적으로 인식되지 않음에 따라, 당 reg.jsp 에서 아래와같이 임의 히든 태그를
      설정하고, 해당 태그의 data- 프로퍼티에 EL 을 심어 reg.js 내의 전역변수에서 dataset
      속성을 이용하여 추출.
   -->
   <div 
      class="hidden"
      data-pageNum="${pageNum}"
      data-searchField="${searchField}"
      data-searchWord="${searchWord}"></div>
       
    <h1 id="logo"><a href="/">PSYLAB</a></h1>
    
    <div id="body">
        <main>
            <h2>공지사항 등록</h2>

            <form name="regNoticeBoard">
                <div class="margin-top first">
                    <h3 class="hidden">공지사항 입력</h3>
                    
                    <div class="table">
                        <label for="title">제목</label>
                        <input type="text" id="title"  name="title" />
                        
                        <label for="files">첨부파일</label>
                        
                        <!--
                           파일 전송시에는 type 을 file 로 설정해야지만, 우선 실습 편의상 text 로 설정하여 실습.
                           
                           ※ Axios 를 통해 file 전송시에는 Content-Type 을 multipart/form-data 형식으로
                             설정해야 하는데, 서블릿에서 JSON 데이터 처리를 위한 Jackson 라이브러리인 ObjectMapper
                             는 multipart/form-data 형식을 직접 파싱 불가.
                        -->
                        <input type="text" id="files"  name="files" />
                        
                        <label for="content">내용</label>
                        <textarea class="content" id="content" name="content"></textarea>
                        
                        <!--
                           체크 박스에 value 속성을 true, false 로 설정한 경우에는 체크를 하지 않은 경우에도
                           서블릿에서 자동으로 false 로 인식.
                        -->
                       <input type="checkbox" id="pub" name="pub" value="true" checked>
                       <label for="pub" for="pub" >바로공개</label> 
                    </div>
                </div>
                
                <div class="btn">
               <button type="button" name="regBtn" >등록</button>
               <button type="button" name="cancel" >취소</button>
            </div>
            </form>
        </main>
    </div>
</body>

</html>