<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.axw.Pojo.Teacher" %>
<%@ page import="com.axw.Pojo.Student" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Teacher teacher = (Teacher) request.getSession().getAttribute(request.getSession().getId() + "teacher");
    List<Student> list = (List<Student>) request.getAttribute("students");
    List<HashMap<String,String>> homework= (List<HashMap<String, String>>) request.getAttribute("homework");
    String o = "";
    for (Student student : list) {
        o += "<p><input type='checkbox' name='student' value=" + student.getId() + ">" + student.getName() + "</p>";
    }
    String p = "";
    p+="<tr><th>姓名</th><th>作业</th><th>状态</th><th>回答</th><tr>";
    for (int i = 0; i <homework.size() ; i++) {
        HashMap<String,String> hashMap=new HashMap<String, String>();
        hashMap=homework.get(i);
        p+="<tr>";
        p+="<td style='width:20%';>"+hashMap.get("sname")+"</td>";
        p+="<td style='width:30%';>"+hashMap.get("homework")+"</td>";
        p+="<td style='width:30%';>"+hashMap.get("flag")+"</td>";
        if(hashMap.get("feedback")!=null){
            p+="<td style='width:20%';>"+hashMap.get("feedback")+"</td>";
        }else {
            p+="<td style='width:20%';></td>";
        }
        p+="</tr>";
    }
%>
<html>
<head>
    <title>主页</title>
    <style>
        button {
            border-radius: 5px;
            background-color: aqua;
            color: #fff;
            border: none;
            font-size: 15px;
            padding: 10px 25px 10px 25px;
        }

        button:hover {
            background-color: darkturquoise;
        }

        textarea {
            outline: none;
            font-size: 18px;
        }
        table{
            border: #535c68 1px solid;
        }
        table tr{
            border: #535c68 1px solid;
        }
        table th{
            min-height: 50px;
        }
        table td{
            min-height: 50px;
            border: #535c68 1px solid;
        }
    </style>
</head>
<body>
<div style="margin: 0 auto;width: 50%">
    <p>老师：<%=teacher.getName() %>  账号：<%=teacher.getId() %>  密码：<%=teacher.getPassword() %>
    </p>
</div>
<div style="margin:0 auto;width: 50%;">
    <div style="display: inline-block">
        <button id="release">发布作业</button>
        <button id="show">查看作业完成情况</button>
    </div>
    <div id="homework" style="margin-top: 50px;display: none">
        <form action="/releaseHomework" method="post">
            <label>作业内容</label>
            <textarea name="homework" style="width: 100%;height: 200px;" required></textarea>
            <button id="choose" style="margin-top: 20px" type="button">选择学生</button>
            <div id="addstudent" style="display: none;">
                <%=o%>
            </div>
            <button type="submit">提交发布</button>
        </form>
    </div>
    <div id="HomeworkState" style="margin-top: 50px;display: none">
        <table style="width: 100%;text-align: center">
            <%=p%>
        </table>
    </div>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script>
    $("#release").click(function () {
        $("#homework").show();
        $("#HomeworkState").hide();
    });
    $("#show").click(function () {
        $("#homework").hide();
        $("#HomeworkState").show();
    })
    $("#choose").click(function () {
        if ($("#addstudent").css("display") == "none") {
            $("#addstudent").show();
        } else {
            $("#addstudent").hide();
        }
    });
</script>
</html>
