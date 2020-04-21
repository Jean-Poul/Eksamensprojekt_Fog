<!DOCTYPE html>
<html>
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
    <link rel="stylesheet" href="#">
    <!-- header title -->
    <title>${requestScope.message}</title>
</head>
<body>
<header role="banner">
    <a class="logo navbar-brand shadow p-3 mb-5 rounded" href="index.jsp" title="Logo">
        <img src="images/logo.PNG" alt="Fog logo">
    </a>
    <div class="headerText px-3 py-3">
        <p>
        Til dig, der går op i detaljen <br>
        Dig, der banker, borer, skærer, skruer, former, vinkler, farver <br>
        Tak, fordi du gør dig umage
        </p>
    </div>
</header>
<nav role="navigation" class="navbar navbar-dark py-0">
    <div class="container">
<div class="leftMenu navbar-nav navbar-expand-sm">
        <a class="nav-item nav-link active" href="index.jsp" title="">Home</a>
        <a class="nav-item nav-link" href="FrontController?target=redirect&destination=customerpage" title="">Bestil</a>
    </div>
    <div class="rightMenu navbar-nav navbar-expand-sm ml-sm-auto">
        <a class="nav-item nav-link" href="FrontController?target=redirect&destination=login" title="">Log ind</a>
    </div>
    </div>
</nav>