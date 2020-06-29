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

<c:url value="/session_transcripts" var="url">
  <c:param name="term" value="${view.term}"/>
  <c:param name="year" value="${view.selectedYear}"/>
  <c:param name="limit" value="${view.limit}"/>
  <c:param name="offset" value="${view.offset}"/>
</c:url>

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

  <form>
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

  <%--PAGINATION--%>
  <nav class="pagination is-centered" role="navigation" aria-label="pagination">
    <%--PREVIOUS PAGE--%>
    <c:url value="" var="url">
      <c:param name="term" value="${view.term}"/>
      <c:param name="year" value="${view.selectedYear}"/>
      <c:param name="limit" value="${view.limit}"/>
      <c:param name="offset" value="${view.pagination.previousPage.pageOffset}"/>
    </c:url>
    <c:choose>
      <c:when test="${view.pagination.hasPreviousPage}">
        <a class="pagination-previous" href="${url}">Previous Page</a>
      </c:when>
      <c:otherwise>
        <a class="pagination-previous" href="${url}" disabled>Previous Page</a>
      </c:otherwise>
    </c:choose>

    <%--NEXT PAGE--%>
    <c:url value="" var="url">
      <c:param name="term" value="${view.term}"/>
      <c:param name="year" value="${view.selectedYear}"/>
      <c:param name="limit" value="${view.limit}"/>
      <c:param name="offset" value="${view.pagination.nextPage.pageOffset}"/>
    </c:url>
    <c:choose>
      <c:when test="${view.pagination.hasNextPage}">
        <a class="pagination-next" href="${url}">Next Page</a>
      </c:when>
      <c:otherwise>
        <a class="pagination-next" href="${url}" disabled>Next Page</a>
      </c:otherwise>
    </c:choose>

    <%--PAGINATION PAGES--%>
    <ul class="pagination-list">
      <c:if test="${view.pagination.showJumpToFirstPage}">
        <c:url value="" var="url">
          <c:param name="term" value="${view.term}"/>
          <c:param name="year" value="${view.selectedYear}"/>
          <c:param name="limit" value="${view.limit}"/>
          <c:param name="offset" value="${view.pagination.firstPage.pageOffset}"/>
        </c:url>
        <li><a class="pagination-link" href="${url}">${view.pagination.firstPage.pageNumber}</a></li>
        <li><span class="pagination-ellipsis">&hellip;</span></li>
      </c:if>
      <c:forEach items="${view.pagination.pages}" var="page">
        <c:if test="${page.current}">
          <li><a class="pagination-link is-current">${page.pageNumber}</a></li>
        </c:if>
        <c:if test="${!page.current}">
          <li>
            <c:url value="" var="url">
              <c:param name="term" value="${view.term}"/>
              <c:param name="year" value="${view.selectedYear}"/>
              <c:param name="limit" value="${view.limit}"/>
              <c:param name="offset" value="${page.pageOffset}"/>
            </c:url>
            <a class="pagination-link" href="${url}">${page.pageNumber}</a>
          </li>
        </c:if>
      </c:forEach>
      <c:if test="${view.pagination.showJumpToLastPage}">
        <c:url value="" var="url">
          <c:param name="term" value="${view.term}"/>
          <c:param name="year" value="${view.selectedYear}"/>
          <c:param name="limit" value="${view.limit}"/>
          <c:param name="offset" value="${view.pagination.lastPage.pageOffset}"/>
        </c:url>
        <li><span class="pagination-ellipsis">&hellip;</span></li>
        <li><a class="pagination-link" href="${url}">${view.pagination.lastPage.pageNumber}</a></li>
      </c:if>
    </ul>
  </nav>

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
