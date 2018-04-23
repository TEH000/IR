<%-- 
    Document   : ShowResult
    Created on : Apr 9, 2018, 12:46:03 PM
    Author     : dongguo
--%>

<%@page import="com.ir.Class.MyDocument"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Search Result</title>
        <!--<link media="all" type="text/css" rel="stylesheet" href="css/app.css">-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>
    <body id="home">
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="SearchPage.jsp">Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarColor02">
                <%
                    String cate = request.getAttribute("cate").toString();
                    if (cate != null) {
                        if (cate.equals("all")) {
                %>
                <ul class="navbar-nav mr-auto" id="myNavbar">
                    <form class="form-inline"  action="ShowResultServ" method="post">
                        <li class="nav-item">
                            <a class="nav-link active"  href="<c:url value="/ShowResultServ?category=all&query=${query}"/>"  >All <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item"  >
                            <a class="nav-link" href="<c:url value="/ShowResultServ?category=article&query=${query}"/>"   >Article</a>
                        </li>
                        <li class="nav-item"  >
                            <a class="nav-link"  href="<c:url value="/ShowResultServ?category=guide&query=${query}"/> " >Wiki Guide</a>
                        </li>
                    </form>
                </ul><%
                    }

                    if (cate.equals("article")) {
                %><ul class="navbar-nav mr-auto" id="myNavbar">
                    <form class="form-inline"  action="ShowResultServ" method="post">
                        <li class="nav-item">
                            <a class="nav-link "  href="<c:url value="/ShowResultServ?category=all&query=${query}"/>"  >All <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item"  >
                            <a class="nav-link active" href="<c:url value="/ShowResultServ?category=article&query=${query}"/>"   >Article</a>
                        </li>
                        <li class="nav-item"  >
                            <a class="nav-link"  href="<c:url value="/ShowResultServ?category=guide&query=${query}"/> " >Wiki Guide</a>
                        </li>
                    </form>
                </ul>
                <%  }
                if (cate.equals("guide")) { %>
                <ul class="navbar-nav mr-auto" id="myNavbar">
                    <form class="form-inline"  action="ShowResultServ" method="post">
                        <li class="nav-item">
                            <a class="nav-link"  href="<c:url value="/ShowResultServ?category=all&query=${query}"/>"  >All <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item"  >
                            <a class="nav-link" href="<c:url value="/ShowResultServ?category=article&query=${query}"/>"   >Article</a>
                        </li>
                        <li class="nav-item"  >
                            <a class="nav-link active"  href="<c:url value="/ShowResultServ?category=guide&query=${query}"/> " >Wiki Guide</a>
                        </li>
                    </form>
                </ul>
                <%  }
                } else {

                %>
                <ul class="navbar-nav mr-auto" id="myNavbar">
                    <form class="form-inline"  action="ShowResultServ" method="post">
                        <li class="nav-item">
                            <a class="nav-link active"  href="<c:url value="/ShowResultServ?category=all&query=${query}"/>"  >All <span class="sr-only">(current)</span></a>
                        </li>
                        <li class="nav-item"  >
                            <a class="nav-link" href="<c:url value="/ShowResultServ?category=article&query=${query}"/>"   >Article</a>
                        </li>
                        <li class="nav-item"  >
                            <a class="nav-link"  href="<c:url value="/ShowResultServ?category=guide&query=${query}"/> " >Wiki Guide</a>
                        </li>
                    </form>
                </ul>
                <%}%>

                <form class="form-inline" method="post" action="SearchPageServ">
                    <input name="query" class="form-control mr-sm-2" type="search" placeholder="${query}" aria-label="Search">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </form>
    </nav>
    <hr>
    <c:set var="totalDocs" value="${requestScope.totalDocs}"/>
    <c:set var="docsPerPage" value="${requestScope.docsPerPage}"/>
    <c:set var="totalPages" value="${requestScope.totalPages}"/>
    <c:set var="beginIndex" value="${requestScope.beginIndex}"/>
    <c:set var="endIndex" value="${requestScope.endIndex}"/>
    <c:set var="page" value="${requestScope.page}"/>
    <c:set var="currentPageDocs" value="${requestScope.docs.subList(beginIndex,endIndex)}"/>
    <div class="container-fluid">
        <div class="row flex-xl-nowrap">
            <div class="col-12 col-md-9 col-xl-8 py-md-3 pl-md-5 bd-content">
                <tbody>
                    <c:forEach items="${currentPageDocs}" var="items">

                    <div class="view">
                        <ul>

                            <li class="col-md-10">
                                <div class="row">
                                    <a class="col-md-10" href="${items.url()}">${items.title()}</a>
                                </div>
                            </li>

                            <div class="row">
                                <div class="col-sm-1">
                                    <label class="col-sm-1 col-form-label">Category:</label>
                                </div>
                                <div class="col-md-10">
                                    <label class="col-md-10 col-form-label">${items.category()}</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-1">
                                    <label class="col-sm-1 col-form-label">Time:</label>
                                </div>
                                <div class="col-md-10">
                                    <label class="col-md-10 col-form-label">${items.date()}</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-1">
                                    <label class="col-sm-1 col-form-label">Content:</label>
                                </div>
                                <div class="col-md-10">
                                    <label class="col-md-10 col-form-label">${items.content()}</label>
                                </div>
                            </div>
                        </ul>    
                    </div>
                </c:forEach>
                </tbody>
            </div>
            <div class="d-none d-xl-block col-xl-3 bd-toc">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">You are looking for:</th>
                            <th scope="col">${query}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th scope="row">Total number of documents:</th>
                            <td>${totalDocs}</td>
                        </tr>
                        <tr>
                            <th scope="row">Documents per page:</th>
                            <td>${docsPerPage}</td>
                        </tr>
                        <tr>
                            <th scope="row">Total pages:</th>
                            <td>${totalPages}</td>
                        </tr>
                        <tr>
                            <th scope="row">Currant page:</th>
                            <td>${page}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <hr>
    <div class="text-center">
        <form name="list"  action="ShowResultServ" method="post">
            <nav>
                <ul class="pagination">
                    <li class="page-item"><a  class="page-link" href="<c:url value="/ShowResultServ?page=1&query=${query}"/>">First Page</a></li>
                    <li class="page-item"><a  class="page-link" href="<c:url value="/ShowResultServ?page=${page-1>1?page-1:1}&query=${query}"/>">&laquo;</a></li>
                        <c:forEach begin="1" end="${totalPages}" varStatus="loop">
                            <c:set var="active" value="${loop.index==page?'active':''}"/>
                        <li class="${active} page-item"><a class="page-link" href="<c:url value="/ShowResultServ?page=${loop.index}&query=${query}"/>">${loop.index}</a></li>
                        </c:forEach>
                    <li class="page-item"><a class="page-link" href="<c:url value="/ShowResultServ?page=${page+1<totalPages?page+1:totalPages}&query=${query}"/>">&raquo;</a></li>
                    <li class="page-item"><a class="page-link" href="<c:url value="/ShowResultServ?page=${totalPages}&query=${query}"/>">Last Page</a></li>
                </ul>
            </nav>
        </form>
    </div>
    <script src="js/jquery.min.js"></script>

    <script src="js/bootstrap.min.js"></script>

    <script src="js/ekko-lightbox.min.js"></script>
</body>
</html>
