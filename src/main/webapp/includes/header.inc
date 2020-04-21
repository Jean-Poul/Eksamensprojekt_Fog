<!-- Encoding -->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!-- End encoding -->
<!DOCTYPE html>
<html>
<!-- Meta tags, external links & site title -->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Responsive -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <!-- External CSS -->
    <link rel="stylesheet" href="css/styles.css" type="text/css">
    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css2?family=Caveat:wght@700&display=swap" rel="stylesheet">
    <!-- header title -->
    <title>${requestScope.message}</title>
</head>
<!-- End meta tags, external links & site title -->

<!-- Body -->
<body>

<!-- Header -->
<header role="banner" class="jumbotron-fluid">
    <!-- Header text -->
    <div class="headerText px-3 py-3">
        <p>
        Til dig, der går op i detaljen <br>
        Dig, der banker, borer, skærer, skruer, former, vinkler, farver <br>
        Tak, fordi du gør dig umage
        </p>
    </div>
    <!-- end header text -->

    <!-- Blur effect -->
    <div class="blur py-0 px-0">
    </div>
    <!-- End blur effect -->

</header>
<!-- End header -->

<!-- Navigation -->
<nav class="navbar navbar-expand-lg">

        <!-- Logo -->
        <a class="logo navbar-brand ml-5" href="index.jsp" title="Logo">
            <img height="136" width="136" src="images/logo.png" alt="Fog logo">
        </a>
        <!-- end logo -->

    <!-- Hamburger menu -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <!-- End hamburger menu -->

    <!-- Collapse -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <!-- Container -->
        <div class="container">
        <!-- Navigation links -->
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active mr">
                <a class="nav-link" href="index.jsp">Home<span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item ml-5">
                <a class="nav-link" href="FrontController?target=redirect&destination=customerpage">Bestil</a>
            </li>
        </ul>

            <a class="nav-link btn badge-primary my-2 my-sm-0" href="FrontController?target=redirect&destination=login">Log ind<span class="sr-only">(current)</span></a>

            <!-- End navigation links -->
        </div>
        <!-- End container -->
    </div>
    <!-- End collapse -->
</nav>
<!-- End navigation -->