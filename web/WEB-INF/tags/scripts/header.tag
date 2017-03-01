<%@ tag pageEncoding="UTF-8" language="java" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>${title}</h1>

<c:set var="Simple" value="Simple" />
<c:set var="Simple_4" value="Simple 4" />
<div class="header">
    Header
    <div>
        <a href="/simple">
            ${title == Simple ? title : Simple}<br>
            <%--<c:choose>
                <c:when test="${title == Simple}">
                    ${title} <br>
                </c:when>
                <c:otherwise>
                    ${Simple} <br>
                </c:otherwise>
            </c:choose>--%>
        </a>
        <a href="/simple4">
            ${title == Simple_4 ? title : Simple_4}<br>
            <%--<c:choose>
                <c:when test="${title == Simple_4}">
                    ${title} <br>
                </c:when>
                <c:otherwise>
                    ${Simple_4} <br>
                </c:otherwise>
            </c:choose>--%>
        </a>
    </div>
</div>
