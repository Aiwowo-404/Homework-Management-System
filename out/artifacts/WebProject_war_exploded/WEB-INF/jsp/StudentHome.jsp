<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="com.axw.Pojo.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Student student= (Student) request.getSession().getAttribute(request.getSession().getId()+"student");

    List<HashMap<String,String>> homework= (List<HashMap<String, String>>) request.getAttribute("homework");
    String p = "";
    p+="<tr><th>老师姓名</th><th>作业</th><th>状态</th><th>操作</th><tr>";
    for (int i = 0; i <homework.size() ; i++) {
        HashMap<String,String> hashMap=new HashMap<String, String>();
        hashMap=homework.get(i);
        p+="<tr>";
        p+="<td style='width:20%';>"+hashMap.get("name")+"</td>";
        p+="<td style='width:30%';>"+hashMap.get("homework")+"</td>";
        p+="<td style='width:30%;display:none;'>"+hashMap.get("tid")+"</td>";
        p+="<td style='width:30%;display:none;'>"+hashMap.get("sid")+"</td>";
        p+="<td style='width:30%';>"+hashMap.get("flag")+"</td>";
        if(hashMap.get("flag").equals("未提交")){
            p+="<td style='width:20%';><button onclick='submit(this)'>提交作业</button></td>";
        }else{
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
            text-align: center;
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
<div style="margin: 0 auto;width: 50%;text-align: center">
    <p>学生：<%=student.getName() %>  账号：<%=student.getId() %>  密码：<%=student.getPassword() %>
    </p>
</div>
<div style="margin:0 auto;width: 50%;">
    <div style="display: inline-block">
        <button id="show">查看作业</button>
        <button id="logout">注销</button>
    </div>
    <div id="homework" style="margin-top: 50px;display: none">
        <table style="width: 100%">
            <%=p%>
        </table>
        <form id="form" style="margin-top: 50px;display: none;">
            <label>作业内容</label>
            <div>
                 <textarea id="hom" name="homework" readonly style="width: 100%;height: 200px;"></textarea>
            </div>

            <input id="sid" type="text" name="sid" hidden>
            <input id="tid" type="text" name="tid" hidden>
            <label>回答</label>
            <textarea name="feedback" style="width: 100%;height: 200px;" required></textarea>
        </form>
        <button type="submit" id="go">提交</button>
    </div>
</div>

</body>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script>
    $("#show").click(function () {
        $("#homework").show();
    });
    function submit(obj) {
        $("#hom").val($(obj).parent().parent().children("td").eq(1).html());
        $("#tid").val($(obj).parent().parent().children("td").eq(2).html());
        $("#sid").val($(obj).parent().parent().children("td").eq(3).html());
        $("#form").show();
    }
    $("#go").click(function () {
        $.ajax({
            url:"/student/submitHomework",
            type:"post",
            data:$("#form").serialize(),
            success:function (data) {
                if(data=="true"){
                    alert("提交成功");
                    window.location.reload();
                }else {
                    alert("提交失败");
                }
            }
        });
    });
    $("#logout").click(function () {
        $.ajax({
            url:"/student/logout",
            type:"post",
            success:function (data) {
                if(data=="true"){
                    window.location.reload();
                }
            }
        });
    });
</script>
</html>
