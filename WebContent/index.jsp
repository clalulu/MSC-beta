<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>User List</title>
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="css/ui.jqgrid.css">
    <script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="js/i18n/grid.locale-en.js"></script>
    <script type="text/javascript" src="js/jquery.jqGrid.min.js"></script>
   
    <script type="text/javascript">
        $(document).ready(function(){
            $("#user_list").jqGrid({
                // ajax 요청주소
                url:"UserListAction",
                // 요청방식
                mtype:"post",
                
                // 결과물 받을 데이터 타입
                datatype:"json",
                // 그리드 갭션
                caption:"User List",
                // 그리드 높이
                height:"auto",
                // 그리드(페이지)마다 보여줄 행의 수 -> 매개변수이름은 "rows"로 요청된다
                rowNum:10,
                // rowNum변경 옵션
                rowList:[10,15,20],
                // 컬럼명
                colNames:["name","email","username","password","cf"],
                // 컬럼 데이터(추가, 삭제, 수정이 가능하게 하려면 autoincrement컬럼을 제외한 모든 컬럼을 editable:true로 지정)
                // edittyped은 text, password, ... input type명을 사용
                colModel:[
                          
                          {name:"name", index:"name", align:"center", editable:true, edittype:"text"},
                          {name:"email", index:"email", align:"center", editable:true, edittype:"text"},
                          {name:"username", index:"username", align:"center", editable:true, edittype:"text"},
                          {name:"password", index:"password", align:"center", editable:true, edittype:"text"},
                          {name:"cf", index:"cf", align:"center", editable:true, edittype:"text"}
                          ],
                // 네비게이션 도구를 보여줄 div요소
                pager:"#pager",
                autowidth:true,
                // 전체 레코드수, 현재레코드 등을 보여줄지 유무
                viewrecords:true,
                // 추가, 수정, 삭제 url
                editurl: "UserEditAction"
            });
           
            // 네비게시션 도구 설정
            $("#user_list").jqGrid(
                    "navGrid",
                    "#pager",
                    {search:false, edit:true, add:true, del:true},
                    {closeAfterEdit: true, reloadAfterSubmit: true},
                    {closeAfterAdd: true, reloadAfterSubmit: true},
                    {reloadAfterSubmit: true}
            );
        });
 
    </script>
</head>
<body>
    <table id="user_list"></table>
    <div id="pager"></div>
    
</body>
</html>