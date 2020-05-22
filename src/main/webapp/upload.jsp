<%@ page contentType="text/html; UTF-8" pageEncoding="utf-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
    <h1>用户上传文件</h1>
    <form action="${pageContext.request.contextPath}/files/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="multipartFile"/><br>
        <input type="submit" value="上传文件"/>
    </form>

    <h1>用户文件下载</h1>
    <a href="${pageContext.request.contextPath}/files/download?filename=小黑.txt">小黑.txt</a>
    <a href="${pageContext.request.contextPath}/files/download?filename=init.sql">init.sql</a>
    <a href="${pageContext.request.contextPath}/files/download?filename=web.xml">web.xml</a>
</body>
</html>