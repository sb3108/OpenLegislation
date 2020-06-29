<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Open Legislation 2.0</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/src/bulma.css"/>
</head>
<body>

<section class="hero">
  <div class="hero-body">
    <div class="container">
      <h1 class="title">
        Transcripts
      </h1>
    </div>
  </div>
</section>

<div class="container">

  ${param}
  <br><br>
  ${paramValues}
  <br><br>
  <%--${pageScope}--%>
  <%--<br><br>--%>
  <%--${pageContext}--%>
  <%--<br><br>--%>
  <%--${paramValues}--%>
  <%--<br><br>--%>
  <%--${header.host}--%>
  <%--<br><br>--%>

    ${view.term}
    <br><br>

  <form autocomplete="off">
    <div class="field">
      <label class="label">Search for transcripts</label>
      <div class="control">
        <input class="input" type="text" name="term" value="${view.term}"
               placeholder="e.g. &quot;a phrase&quot; or text">
      </div>
    </div>

    <div class="field">
      <label class="label">Published Year</label>
      <div class="control">
        <div class="select">
          <select name="year">
            <c:forEach items="${view.availableYears}" var="year">
              <c:choose>
                <c:when test="${year.equals(view.selectedYear)}">
                  <option selected>${year}</option>
                </c:when>
                <c:otherwise>
                  <option>${year}</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>
        </div>
      </div>
    </div>

    <div class="field">
      <div class="control">
        <button class="button is-link">Search</button>
      </div>
    </div>
  </form>

  <hr>

    <%--<%@ include file="pagination.jsp"%>--%>
    <jsp:include page="pagination.jsp">
      <jsp:param name="view" value="view"/>
    </jsp:include>

  <%--TRANSCRIPT LIST--%>
  <ul>
    <c:forEach items="${view.results}" var="transcript">
      <li>
        <a href="/session_transcripts/${transcript.dateTime}">${transcript.dateTime}</a>
        - ${transcript.sessionType}
      </li>
    </c:forEach>
  </ul>
</div>
</body>
</html>
