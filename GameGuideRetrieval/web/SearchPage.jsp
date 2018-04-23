<%-- 
    Document   : SearchPage
    Created on : Apr 7, 2018, 6:11:50 PM
    Author     : dongguo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Game Search</title>
        <link media="all" type="text/css" rel="stylesheet" href="css/app.css">
        <!--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">-->

    </head>
    <body id="home">
        <nav class="navbar navbar-default">
            <div class="container">
<!--                <div class="navbar-header">

                    <a class="navbar-brand" href="http://www.ign.com">
                        <img src="images/botlist-logo.svg">
                    </a>
                   
                </div>-->

                <div class="collapse navbar-collapse" id="app-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a target="_blank" href="http://www.ign.com">IGN</a></li>
                   </ul>
                    <!-- Left Side Of Navbar -->
                    <!--                    <ul class="nav navbar-nav">
                                            <li><a target="_blank" href="http://www.ign.com/articles">Articles</a></li>
                                            <li><a target="_blank" href="http://www.ign.com/wikis">Wiki Guide</a></li>
                                        </ul>-->

                    <!-- Right Side Of Navbar -->
                    <ul class="nav navbar-nav navbar-right">
                        <li><a target="_blank" href="http://www.ign.com/articles">Articles</a></li>
                        <li><a target="_blank" href="http://www.ign.com/wikis">Wiki Guide</a></li>
                    </ul>


                </div>
            </div>
        </nav>
        <div id="hero" >
            <div class="container">
                <div class="row">
                    <h1>Game Search Engine</h1>
                    <img src="images/platform-list.svg" class="platform-list" alt="platform logos">
                    <form class="col-md-6 col-md-offset-3" role="search" method="post" action="SearchPageServ">
                        <div class="form-group">
                            <input name="query" type="text" class="form-control col-xs-8" placeholder="Type your query here">
                            <button type="submit" class="btn btn-custom col-lg-3 col-xs-3">Search</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <!--        <div class="container"><img src="">
                <h1 class="display-1 text-bold">Search anything</h1>
                <h5 class="text-gray-soft text-regular">Search for game articles or wiki guides</h5>
                
                <form class="hero__form" method="post" action="SearchPageServ" >
                    <div class="form-group">
                        <div class="form-group--search form-group--search--left">
                            <label for="searchinput"> Type your query here: 
                            <input class="form-control form-control-lg form-control--rounded" name="s" type="search" id="search" placeholder="Search">
                            <button class="btn-submit" type="submit"></button>
                        </div>
                    </div>
                </form>
            </div>-->
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <!--    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        -->
        <script src="js/jquery.min.js"></script>

        <script src="js/bootstrap.min.js"></script>

        <script src="js/ekko-lightbox.min.js"></script>
    </body>
</html>