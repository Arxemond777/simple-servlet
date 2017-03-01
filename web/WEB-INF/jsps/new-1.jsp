<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    /*TODO Скриплет*/
    List<Integer> listOnPage = new ArrayList<Integer>();
    for(int i = 0; i < 10; ++i) {
        listOnPage.add(i);
    }
%>

<%--Чтоб переменные из TODO скриплета работали так ${vatiable}--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<c:set var="listOnPage" value="<%=listOnPage%>" />

<%@ taglib tagdir="/WEB-INF/tags/scripts" prefix="scripts"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <scripts:header title="Simple"/>
    jsp2
    <hr/>
    <c:forEach var="item" items="${items}">
        <div>Id : <c:out value="${item.id}"/></div>
        <div>Name : <c:out value="${item.name}"/></div>
        <%--<c:forEach var="val" items="${item}">
            <div>Name : <c:out value="${val.value}"/></div>
            <div>Key : <c:out value="${val.key}"/></div>
            &lt;%&ndash;<div>: <c:out value="${val.name}"/></div>&ndash;%&gt;
            <br>
        </c:forEach>--%>
    </c:forEach>

    <hr/>
    ${param.city}
    <hr/>
    ${requestScope.message}
    <hr/>
    фбсд
    <hr/>
    ${requestScope.list}
    <hr/>
    <div>From listOnPage</div>
    <%=listOnPage%>
    <hr/>
    <div>Custom from jstl</div>
    ${listOnPage}
    <hr/>
    <c:forEach var="n" items="${listOnPage}">
        <c:if test="${n%2 == 0}">
            ${n} <br>
        </c:if>
    </c:forEach>
    <hr/>
    if/else
    <%--if eles--%>
    <c:forEach var="n" items="${listOnPage}">
        <c:choose>
            <c:when test="${n%2 == 0}">
                    ${n} <br>
            </c:when>
            <c:otherwise>
                не четное <br>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <hr/>
    10 item in listOnPage
    ${fn:length(listOnPage)}
    <br>
    <c:set var="text" value="asdsad"/>
    ${fn:toUpperCase(text)}

</body>
</html>
