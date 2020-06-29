<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
