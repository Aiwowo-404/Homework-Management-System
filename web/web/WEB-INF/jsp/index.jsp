<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/3/2
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <style>
        body {
            background-color: silver;
            font-family: "Microsoft YaHei",sans-serif;
        }
        h1{
            text-align: center;
            font-size: 45px;
            margin: 30px 0px;
            letter-spacing: 3px;
            color: #535c68;
            font-family: '宋体';
            font-weight: 500;
        }
        h3{
            color: #ffffff;
            font-size: 20px;
        }
        .card {
            width: 400px;
            background-color: #fff;
            margin: 0 auto;
            text-align: center;
            padding: 20px 15px 20px 15px;
            border-radius: 5px;
        }
        .choose{
            display: inline-block;
            background-color: silver;
            width: 30%;
            text-align: center;
        }
        .form-inline{
            padding: 10px;
            display: inline-block;
        }
        .form-input {
            margin-bottom: 20px;
            color: #000;
            font-size: 18px;
            padding-left: 15px;
            width: 80%;
            height: 40px;
            border: silver 1px solid;
            border-radius: 5px;
            outline: none;
        }

        .form-input:active {
            border: black 1px solid;
        }

        .submit {
            border-radius: 5px;
            background-color: aqua;
            color: #fff;
            border: none;
            font-size: 15px;
            padding: 10px 25px 10px 25px;
        }

        .submit:hover {
            background-color: darkturquoise;
        }

        .submit:active {
            background-color: darkturquoise;
            border: none;
        }
    </style>
</head>
<body>
<h1>
    作业管理系统
</h1>
<div class="card">
    <div style="margin-bottom: 20px">
        <div class="choose" id="teacher">
            <h3>教师</h3>
        </div>
        <div class="choose" id="student">
            <h3>学生</h3>
        </div>
    </div>
    <form id="form" action="/goLoginTeacher" method="post">
        <div class="form-inline">
            <label>账号：</label>
            <input class="form-input" type="text" name="id" required>
        </div>
        <div class="form-inline">
            <label>密码：</label>
            <input class="form-input" type="password" name="password" required>
        </div>
        <div style="text-align: center">
            <input class="submit" type="submit" value="登录">
        </div>
    </form>
</div>
</body>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script>
    $(document).ready(function () {
        $("#teacher").css("background-color","#535c68");
        $("#form").attr("action","/goLoginTeacher");
    });
    $("#teacher").click(function () {
        $("#teacher").css("background-color","#535c68");
        $("#student").css("background-color","silver");
        $("#form").attr("action","/goLoginTeacher");
    });
    $("#student").click(function () {
        $("#student").css("background-color","#535c68");
        $("#teacher").css("background-color","silver");
        $("#form").attr("action","/goLoginStudent");
    });
</script>
</html>
