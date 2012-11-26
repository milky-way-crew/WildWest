<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Milky-way-team presents</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/login.css" rel="stylesheet">
    <link href="resources/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
  </head>

  <body>

    <div class="navbar navbar-fixed-top navbar-inverse">
      <div class="navbar-inner">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span> 
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#">Milky-way games</a>
          <div class="nav-collapse">
            <ul class="nav">
              <!-- <li class="active"><a href="#">Home</a></li> -->
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul>
<!--             <ul class="nav pull-right">
              <li><a href="http://wp.me/p2m9ik-j6">Back To Untame</a></li>
            </ul> -->
                      <ul class="nav pull-right">
            <li class="divider-vertical"></li>
            <li class="dropdown"><a class="dropdown-toggle" href="#"
              data-toggle="dropdown">Sign In <strong class="caret"></strong></a>
              <div class="dropdown-menu"
                style="padding: 15px; padding-bottom: 0px;">
                <form:form id="loginHere" method='post' action='login'
                  commandName="user">
                  <fieldset>
                    <h2>
                      <legend>Sign in</legend>
                    </h2>
                    <div class="control-group">
                      <label class="control-label">Login</label>
                      <div class="controls">
                        <div class="box transparent">
                          <form:input style="margin-bottom: 15px;" type="text"
                            path="login" class="input-xlarge" id="login" name="login"
                            rel="popover" data-placement='left'
                            data-content="What is your login, buddy?"
                            placeholder="What is your login, buddy?"
                            data-original-title="Login field" />
                          <form:errors path="login" cssClass="btn-mini btn-danger"></form:errors>
                        </div>
                      </div>
                    </div>


                    <div class="control-group">
                      <h3>
                        <label class="control-label">Password</label>
                      </h3>
                      <div class="controls">
                        <div class="box transparent">
                          <form:input style="margin-bottom: 15px;" type="password"
                            path="password" class="input-xlarge" id="password"
                            name="password" rel="popover" data-placement='left'
                            data-content="What is your password?"
                            data-original-title="Password field" placeholder = "keep your password in safe place"/>
                          <form:errors path="password" cssClass="btn-mini btn-danger"></form:errors>
                        </div>
                      </div>
                    </div>

                    <div class="control-group">
                      <label class="control-label"></label>
                      <div class="controls">
                        <button type="submit" id="sign-in"
                          class="btn btn-primary btn-block">
                          <i class="icon-user icon-white"></i> Sign in
                        </button>
                        <a class="btn btn-primary btn-info" href="register"><font
                          color="white"><i class="icon-heart icon-white"></i>
                            Register</font></a>
                      </div>
                    </div>

                  </fieldset>
                </form:form>
              </div></li>
          </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div id="content" class="container well">

      <!-- Main hero unit for a primary marketing message or call to action -->
          <div id="myCarousel" class="carousel slide">
          <!-- Carousel items -->
          <div class="carousel-inner">
          <!-- 
            <div class="active item"><img src="http://placehold.it/1200x450/0e53a7/00000&text=at first: its gorgerous" /></div>
            <div class="item"><img src="http://placehold.it/1200x450/00af64/00000&text=second: easy to play" /></div>
            <div class="item"><img src="http://placehold.it/1200x450/E8117F/00000&text=third: redtube.com" /></div>
           -->
            <div class="active item"><img src="resources/img/slide-1.png" alt=""></div>
            <div class="item"><img src="resources/img/slide-2.png" alt=""></div>
            <div class="item"><img src="resources/img/slide-3.png" alt=""></div>
            <div class="item"><img src="resources/img/slide-4.png" alt=""></div>


          </div>
          <!-- Carousel nav -->
          <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
          <a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
        </div>

        <div class="row" id="call-to-action">
          
          <div class="span9">
              <h2>Our games so sweeeeet!</h2>
          </div><!-- end text -->

          <a href="register" class="btn btn-large btn-success offset1">Wow, i wanna try!</a>

        </div><!-- end cta -->

      <div class="row">
        <div class="span4">
          <h2>So cool...</h2>
           <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <!-- <p><a class="btn" href="#">View details &raquo;</a></p> -->
        </div>
        <div class="span4">
          <h2>So awesome...</h2>
           <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <!-- <p><a class="btn" href="#">View details &raquo;</a></p> -->
       </div>
        <div class="span4">
          <h2>So amazing...</h2>
          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
          <!-- <p><a class="btn" href="#">View details &raquo;</a></p> -->
        </div>
      </div>

      <hr>

      <footer>
        <p>&copy; milky-way-team 2012</p>
      </footer>

    </div> <!-- /container -->

    <!-- Le javascript
    ================================================== -->
    <!-- // <script src="resources/js/bootstrap.min.js"></script> -->
    <script src="resources/js/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap-dropdown.js"></script>
    <script type="text/javascript" src="resources/js/bootstrap-tooltip.js"></script>
    <!-- // <script type="text/javascript" src="resources/js/bootstrap-popover.js"></script> -->
    <script src="resources/js/bootstrap-carousel.js"></script>
    <script type="text/javascript" src="resources/js/jquery.validate.js"></script>
    <script type="text/javascript" src="resources/js/login.js"></script>

    <script type="text/javascript">
    $('.carousel').carousel()
    </script>

  </body>
</html>