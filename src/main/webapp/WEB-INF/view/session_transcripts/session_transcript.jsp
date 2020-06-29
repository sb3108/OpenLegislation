<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Open Legislation 2.0</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/src/bulma.css"/>
</head>
<body>

<section class="hero is-info is-small">
  <div class="hero-body">
    <div class="container">
      <h1 class="title">
        Session Transcript:
        <fmt:parseDate value="${transcript.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="prettyDate"/>
        <fmt:formatDate value="${prettyDate}" type="date"/>
      </h1>
    </div>
  </div>
</section>

<section>
  <div class="container" style="padding: 1.5rem; background:#f5f5f5;">
    <h3 class="title is-4">${transcript.sessionType}</h3>
    <h4 class="subtitle">
      <fmt:parseDate value="${transcript.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="prettyDate"/>
      <fmt:formatDate value="${prettyDate}" type="both"/>, ${transcript.location}
    </h4>
  </div>
</section>

<section class="section">
  <div class="container">
  <pre>
    ${transcript.text}
  </pre>
  </div>
</section>

</body>
</html>
