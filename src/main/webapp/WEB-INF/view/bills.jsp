<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 6/25/20
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Bills</title>
</head>
<body>

Bill: ${bill.baseBillId.basePrintNo}
</br>

Title: ${bill.title}
</br>

Status: ${bill.status}
</br>

json: ${bill.toString()}
</br>

</body>
</html>
