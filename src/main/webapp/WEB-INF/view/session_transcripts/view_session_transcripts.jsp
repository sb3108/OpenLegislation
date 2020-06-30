<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Open Legislation 2.0</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/src/bulma.css"/>
  <script type="text/javascript" src="/static/js/turbolinks.js"></script>
</head>
<body>

<section class="hero is-info is-small">
  <div class="hero-body">
    <div class="container">
      <h1 class="title">
        Transcripts
      </h1>
    </div>
  </div>
</section>

<section>

  <%--SEARCH FORM--%>
  <div class="container" style="padding: 1.5rem; background:#f5f5f5;">
    <form autocomplete="off">
      <div class="field">
        <label class="label">Search for transcripts</label>
        <div class="control">
          <input class="input" type="text" name="term" value="${view.term}" onchange="this.form.submit()"
                 placeholder="e.g. &quot;a phrase&quot; or text">
        </div>
      </div>

      <div class="field is-grouped">
        <div class="control">
          <div class="label">Type</div>
          <label class="radio">
            <input type="radio" name="type" value="Session" onchange="this.form.submit()">
            Session
          </label>
          <label class="radio">
            <input type="radio" name="type" value="Public Hearing" onchange="this.form.submit()">
            Public Hearing
          </label>
        </div>

        <div class="control">
          <div class="label">Published Year</div>
          <div class="select">
            <select name="year" onchange="this.form.submit()">
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
  </div>

  <section class="section">
    <div class="container">
      <%--PAGINATION--%>
      <%--<%@ include file="pagination.jsp"%>--%>
      <jsp:include page="pagination.jsp">
        <jsp:param name="view" value="view"/>
      </jsp:include>
    </div>
  </section>

  <section>
    <div class="container">
      <%--TRANSCRIPT LIST--%>
      <ul>
        <c:forEach items="${view.results}" var="transcript">
          <li style="margin-top: 10px;">
            <a href="/session_transcripts/${transcript.dateTime}">
                <%--TODO May be worth creating our own jstl tag for this type of operation--%>
              <fmt:parseDate value="${transcript.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" type="both" var="prettyDate"/>
              <b><fmt:formatDate value="${prettyDate}" type="both"/></b></a>
            - ${transcript.sessionType}
          </li>
        </c:forEach>
      </ul>
    </div>
  </section>
</section>
</body>
</html>
