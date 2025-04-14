<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 에서의 논리, 삼항조건, empty 연산자, boolean 값 처리, 태그 요소의 프로퍼티 인식 처리, EL 내 문자열 구분자(’), 코드 블럭내 EL 사용 불가</title>
</head>

<body>
   <h1>EL 에서의 논리, 삼항조건, empty 연산자, boolean 값 처리, 태그 요소의 프로퍼티 인식 처리, EL 내 문자열 구분자(’), 코드 블럭내 EL 사용 불가</h1>
   <hr>
   
   <h2>논리 연산자</h2>
   <div>${param.num1}, ${param.num2} 모두 짝수 : ${param.num1 % 2 == 0 && param.num2 % 2 == 0  }</div>
   <div>${param.num1}, ${param.num2} 둘중 하나 짝수 : ${param.num1 % 2 == 0 || param.num2 % 2 == 0  }</div>
   <div>${param.num1}, ${param.num2} 모두 홀수 : ${!(param.num1 % 2 == 0 || param.num2 % 2 == 0)  }</div>
   <hr>
   
   <h2>대체 논리 연산자</h2>
   <div>${param.num1}, ${param.num2} 모두 짝수 : ${param.num1 % 2 == 0 and param.num2 % 2 == 0  }</div>
   <div>${param.num1}, ${param.num2} 둘중 하나 짝수 : ${param.num1 % 2 == 0 or param.num2 % 2 == 0  }</div>
   <div>${param.num1}, ${param.num2} 모두 홀수 : ${not(param.num1 % 2 == 0 || param.num2 % 2 == 0)  }</div>
   <hr>
   
   <!-- 
      EL 에서는 조건식 부분에 js 와 유사하게 불린값이 아닌 요소의 지정이 가능한데,
      null, 빈 문자열(""), 0, 빈 컬렉션, 문자열/숫자 리터럴 모두 false 로 평가됨에 주의.
      단, 대상이 Object 인 경우에는 null 이 아니면, true 로 평가되어 JSTL 에서는
      c:set 을 이용한 변수 선언시 Object 로 평가되어 값(value)이 문자열/숫자 리터럴로
      설정된 경우 모두 true 로 평가. 
      또한 EL 내 문자열은 싱글쿼터를 이용하여 표현.
   -->
   <h2>EL 내 boolean 값 처리와 문자열 구분자</h2>
   <div>${param.num1 ?  param.num1 : '정수1 은 반드시 입력되어야 합니다.'}</div>
   <hr>
   
   
   <h2>삼항조건 연산자와 empty 연산자</h2>
   <div>${param.num1 == '' || param.num1 == null ? '정수1 은 반드시 입력되어야 합니다.' : param.num1 }</div>
   
   <!-- 
      empty 연산자를 통해 널 체크와 빈 문자열 체크를 한번에 처리 가능.
   -->
   <div>${empty param.num1 ? '정수1 은 반드시 입력되어야 합니다' : param.num1 }</div>
   <hr>
   
   <h2>EL 에서의 태그 요소 프로퍼티 처리</h2>
   <!-- 
      EL 에서 태그 요소의 프로퍼티 처리를 위해서는 아래와같이 문자열로
      처리해야함에 주의.
      단, 프로퍼티에 해당하는 값은 별도의 문자열 구분자 필요치 않음. 
   -->
   <div ${not empty param.num1 ? 'class = test': '' }>EL 에서의 태그 요소 프로퍼티 처리</div>
   <hr>
   
   <h2>코드블럭 내에서는 EL 사용불가</h2>
<%--    <%${param.num1 == '' || param.num1 == null ? '정수1 은 반드시 입력되어야 합니다.' : param.num1 }%> --%>
</body>
</html>