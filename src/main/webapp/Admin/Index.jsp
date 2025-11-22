<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Chỉ import các lớp Java cần thiết --%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Bean.AdminTemplate" %> 
<!DOCTYPE html>
<html data-bs-theme="light" data-startbar="light" dir="ltr" lang="en">
  <head>
    <meta charset="utf-8">
    <title></title>
    <meta content="width=device-width, initial-scale=1, shrink-to-fit=no" name="viewport">
    <meta name="description" content="Premium Multipurpose Admin & Dashboard Template">
    <meta name="author" content="">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <!-- App favicon-->
    <link href="admin/assets/images/T-Solution.png" rel="shortcut icon">
    <!-- App css-->
    <link type="text/css" rel="stylesheet" href="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.css">
    <link type="text/css" rel="stylesheet" href="admin/assets/css/bootstrap.min.css">
    <link type="text/css" rel="stylesheet" href="admin/assets/css/icons.min.css">
    <link type="text/css" rel="stylesheet" href="admin/assets/css/app.min.css">
    <link type="text/css" rel="stylesheet" href="admin/assets/css/main.css">
    <%-- Dùng EL cho đường dẫn CSS (Cách làm tốt) --%>
    <link href="${pageContext.request.contextPath}/Public/Admin/Assets/CSS/Template/ListTemplate.css" rel="stylesheet" type="text/css">
  </head>
  <!-- Top Bar Start-->
  <body>
    <!-- Top Bar Start-->
    <div class="topbar d-print-none">
  <div class="container-xxl">
    <nav class="topbar-custom d-flex justify-content-between" id="topbar-custom">
      <ul class="topbar-item list-unstyled d-inline-flex align-items-center mb-0">
        <li>
          <button class="nav-link mobile-menu-btn nav-icon" id="togglemenu"><i class="iconoir-menu-scale"></i></button>
        </li>
        <li class="mx-3 welcome-text">
          <h3 class="mb-0 fw-bold text-truncate">Good Morning, Tuan!</h3>
          <!-- <h6 class="mb-0 fw-normal text-muted text-truncate fs-14">Here's your overview this week.</h6>-->
        </li>
      </ul>
      <ul class="topbar-item list-unstyled d-inline-flex align-items-center mb-0">
        <li class="hide-phone app-search">
          <form method="get" action="#" role="search">
            <input class="form-control top-search mb-0" placeholder="Search here..." name="search" type="search">
            <button type="submit"><i class="iconoir-search"></i></button>
          </form>
        </li>
        <li class="dropdown"><a class="nav-link dropdown-toggle arrow-none nav-icon" aria-expanded="false" aria-haspopup="false" role="button" href="#" data-bs-toggle="dropdown"><img class="thumb-sm rounded-circle" alt="" src="admin/assets/images/flags/us_flag.jpg"></a>
          <div class="dropdown-menu"><a class="dropdown-item" href="#"><img class="me-2" height="15" alt="" src="admin/assets/images/flags/us_flag.jpg">English</a><a class="dropdown-item" href="#"><img class="me-2" height="15" alt="" src="admin/assets/images/flags/spain_flag.jpg">Spanish</a><a class="dropdown-item" href="#"><img class="me-2" height="15" alt="" src="admin/assets/images/flags/germany_flag.jpg">German</a><a class="dropdown-item" href="#"><img class="me-2" height="15" alt="" src="admin/assets/images/flags/french_flag.jpg">French</a></div>
        </li>
        <!-- end topbar-language-->
        <li class="topbar-item"><a class="nav-link nav-icon" id="light-dark-mode" href="javascript:void(0);"><i class="icofont-moon dark-mode"></i><i class="icofont-sun light-mode"></i></a></li>
        <li class="dropdown topbar-item"><a class="nav-link dropdown-toggle arrow-none nav-icon" aria-expanded="false" aria-haspopup="false" role="button" href="#" data-bs-toggle="dropdown"><i class="icofont-bell-alt"></i><span class="alert-badge"></span></a>
          <div class="dropdown-menu stop dropdown-menu-end dropdown-lg py-0">
            <h5 class="dropdown-item-text m-0 py-3 d-flex justify-content-between align-items-center">Notifications<a class="badge text-body-tertiary badge-pill" href="#"><i class="iconoir-plus-circle fs-4"></i></a></h5>
            <ul class="nav nav-tabs nav-tabs-custom nav-success nav-justified mb-1" role="tablist">
              <li class="nav-item" role="presentation"><a class="nav-link mx-0 active" aria-selected="true" role="tab" href="#All" data-bs-toggle="tab">All<span class="badge bg-primary-subtle text-primary badge-pill ms-1">24</span></a></li>
              <li class="nav-item" role="presentation"><a class="nav-link mx-0" tabindex="-1" aria-selected="false" role="tab" href="#Projects" data-bs-toggle="tab">Projects</a></li>
              <li class="nav-item" role="presentation"><a class="nav-link mx-0" tabindex="-1" aria-selected="false" role="tab" href="#Teams" data-bs-toggle="tab">Team</a></li>
            </ul>
            <div class="ms-0" data-simplebar="" style="max-height:230px;">
              <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="All" tabindex="0" aria-labelledby="all-tab" role="tabpanel">
                  <!-- item--><a class="dropdown-item py-3" href="#"><small class="float-end text-muted ps-2">2 min ago</small>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0 bg-primary-subtle text-primary thumb-md rounded-circle"><i class="iconoir-wolf fs-4"></i></div>
                      <div class="flex-grow-1 ms-2 text-truncate">
                        <h6 class="my-0 fw-normal text-dark fs-13">Your order is placed</h6><small class="text-muted mb-0">
                          Dummy text of the printing and
                            industry.</small>
                      </div>
                      <!-- end media-body-->
                    </div>
                    <!-- end media--></a>
                  <!-- end-item-->
                  <!-- item--><a class="dropdown-item py-3" href="#"><small class="float-end text-muted ps-2">10 min ago</small>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0 bg-primary-subtle text-primary thumb-md rounded-circle"><i class="iconoir-apple-swift fs-4"></i></div>
                      <div class="flex-grow-1 ms-2 text-truncate">
                        <h6 class="my-0 fw-normal text-dark fs-13">Meeting with designers</h6><small class="text-muted mb-0">
                          It is a long established fact that a
                            reader.</small>
                      </div>
                      <!-- end media-body-->
                    </div>
                    <!-- end media--></a>
                  <!-- end-item-->
                  <!-- item--><a class="dropdown-item py-3" href="#"><small class="float-end text-muted ps-2">40 min ago</small>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0 bg-primary-subtle text-primary thumb-md rounded-circle"><i class="iconoir-birthday-cake fs-4"></i></div>
                      <div class="flex-grow-1 ms-2 text-truncate">
                        <h6 class="my-0 fw-normal text-dark fs-13">UX 3 Task complete.</h6><small class="text-muted mb-0">Dummy text of the printing.</small>
                      </div>
                      <!-- end media-body-->
                    </div>
                    <!-- end media--></a>
                  <!-- end-item-->
                  <!-- item--><a class="dropdown-item py-3" href="#"><small class="float-end text-muted ps-2">1 hr ago</small>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0 bg-primary-subtle text-primary thumb-md rounded-circle"><i class="iconoir-drone fs-4"></i></div>
                      <div class="flex-grow-1 ms-2 text-truncate">
                        <h6 class="my-0 fw-normal text-dark fs-13">Your order is placed</h6><small class="text-muted mb-0">
                          It is a long established fact that a
                            reader.</small>
                      </div>
                      <!-- end media-body-->
                    </div>
                    <!-- end media--></a>
                  <!-- end-item-->
                  <!-- item--><a class="dropdown-item py-3" href="#"><small class="float-end text-muted ps-2">2 hrs ago</small>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0 bg-primary-subtle text-primary thumb-md rounded-circle"><i class="iconoir-user fs-4"></i></div>
                      <div class="flex-grow-1 ms-2 text-truncate">
                        <h6 class="my-0 fw-normal text-dark fs-13">Payment Successfull</h6><small class="text-muted mb-0">Dummy text of the printing.</small>
                      </div>
                      <!-- end media-body-->
                    </div>
                    <!-- end media--></a>
                  <!-- end-item-->
                </div>
                <div class="tab-pane fade" id="Projects" tabindex="0" aria-labelledby="projects-tab" role="tabpanel">
                  <!-- item--><a class="dropdown-item py-3" href="#"><small class="float-end text-muted ps-2">40 min ago</small>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0 bg-primary-subtle text-primary thumb-md rounded-circle"><i class="iconoir-birthday-cake fs-4"></i></div>
                      <div class="flex-grow-1 ms-2 text-truncate">
                        <h6 class="my-0 fw-normal text-dark fs-13">UX 3 Task complete.</h6><small class="text-muted mb-0">Dummy text of the printing.</small>
                      </div>
                      <!-- end media-body-->
                    </div>
                    <!-- end media--></a>
                  <!-- end-item-->
                  <!-- item--><a class="dropdown-item py-3" href="#"><small class="float-end text-muted ps-2">1 hr ago</small>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0 bg-primary-subtle text-primary thumb-md rounded-circle"><i class="iconoir-drone fs-4"></i></div>
                      <div class="flex-grow-1 ms-2 text-truncate">
                        <h6 class="my-0 fw-normal text-dark fs-13">Your order is placed</h6><small class="text-muted mb-0">
                          It is a long established fact that a
                            reader.</small>
                      </div>
                      <!-- end media-body-->
                    </div>
                    <!-- end media--></a>
                  <!-- end-item-->
                  <!-- item--><a class="dropdown-item py-3" href="#"><small class="float-end text-muted ps-2">2 hrs ago</small>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0 bg-primary-subtle text-primary thumb-md rounded-circle"><i class="iconoir-user fs-4"></i></div>
                      <div class="flex-grow-1 ms-2 text-truncate">
                        <h6 class="my-0 fw-normal text-dark fs-13">Payment Successfull</h6><small class="text-muted mb-0">Dummy text of the printing.</small>
                      </div>
                      <!-- end media-body-->
                    </div>
                    <!-- end media--></a>
                  <!-- end-item-->
                </div>
                <div class="tab-pane fade" id="Teams" tabindex="0" aria-labelledby="teams-tab" role="tabpanel">
                  <!-- item--><a class="dropdown-item py-3" href="#"><small class="float-end text-muted ps-2">1 hr ago</small>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0 bg-primary-subtle text-primary thumb-md rounded-circle"><i class="iconoir-drone fs-4"></i></div>
                      <div class="flex-grow-1 ms-2 text-truncate">
                        <h6 class="my-0 fw-normal text-dark fs-13">Your order is placed</h6><small class="text-muted mb-0">
                          It is a long established fact that a
                            reader.</small>
                      </div>
                      <!-- end media-body-->
                    </div>
                    <!-- end media--></a>
                  <!-- end-item-->
                  <!-- item--><a class="dropdown-item py-3" href="#"><small class="float-end text-muted ps-2">2 hrs ago</small>
                    <div class="d-flex align-items-center">
                      <div class="flex-shrink-0 bg-primary-subtle text-primary thumb-md rounded-circle"><i class="iconoir-user fs-4"></i></div>
                      <div class="flex-grow-1 ms-2 text-truncate">
                        <h6 class="my-0 fw-normal text-dark fs-13">Payment Successfull</h6><small class="text-muted mb-0">Dummy text of the printing.</small>
                      </div>
                      <!-- end media-body-->
                    </div>
                    <!-- end media--></a>
                  <!-- end-item-->
                </div>
              </div>
            </div>
            <!-- All--><a class="dropdown-item text-center text-dark fs-13 py-2" href="pages-notifications.html">View All<i class="fi-arrow-right"></i></a>
          </div>
        </li>
        <li class="dropdown topbar-item"><a class="nav-link dropdown-toggle arrow-none nav-icon" aria-expanded="false" aria-haspopup="false" role="button" href="#" data-bs-toggle="dropdown"><img class="thumb-lg rounded-circle" alt="" src="admin/assets/images/users/avatar-1.jpg"></a>
          <div class="dropdown-menu dropdown-menu-end py-0">
            <div class="d-flex align-items-center dropdown-item py-2 bg-secondary-subtle">
              <div class="flex-shrink-0"><img class="thumb-md rounded-circle" alt="" src="admin/assets/images/users/avatar-1.jpg"></div>
              <div class="flex-grow-1 ms-2 text-truncate align-self-center">
                <h6 class="my-0 fw-medium text-dark fs-13">William Martin</h6><small class="text-muted mb-0">Front End Developer</small>
              </div>
              <!-- end media-body-->
            </div>
            <div class="dropdown-divider mt-0"></div><small class="text-muted px-2 pb-1 d-block">Account</small><a class="dropdown-item" href="pages-profile.html"><i class="las la-user fs-18 me-1 align-text-bottom"></i>Profile</a><a class="dropdown-item" href="pages-faq.html"><i class="las la-wallet fs-18 me-1 align-text-bottom"></i>Earning</a><small class="text-muted px-2 py-1 d-block">Settings</small><a class="dropdown-item" href="pages-profile.html"><i class="las la-cog fs-18 me-1 align-text-bottom"></i>Account Settings</a><a class="dropdown-item" href="pages-profile.html"><i class="las la-lock fs-18 me-1 align-text-bottom"></i>Security</a><a class="dropdown-item" href="pages-faq.html"><i class="las la-question-circle fs-18 me-1 align-text-bottom"></i>Help Center</a>
            <div class="dropdown-divider mb-0"></div><form style="padding: 6px 24px" id="logoutForm" action="../LogoutServlet" method="post"><button style="border: none; background-color: white; color: red; padding: 0px" type="submit"><i class="las la-power-off fs-18 me-1 align-text-bottom"></i> Logout</button></form>
          </div>
        </li>
      </ul>
      <!-- end topbar-nav-->
    </nav>
    <!-- end navbar-->
  </div>
</div>
    <!-- Top Bar End-->
    <!-- leftbar-tab-menu-->
    <div class="startbar d-print-none">
  <!-- start brand-->
  <div class="brand"><a class="logo" href="/undefined/dashboard"><span><img style="width: 150px" alt="logo-small" src="admin/assets/images/T-Solution.png"></span></a></div>
  <!-- end brand-->
  <!-- start startbar-menu-->
  <div class="startbar-menu">
    <div class="startbar-collapse" id="startbarCollapse" data-simplebar="">
      <div class="d-flex align-items-start flex-column w-100">
        <!-- Navigation--> 
        <ul class="navbar-nav mb-auto w-100">
          <li class="menu-label pt-0 mt-0">
            <!--
            <small class="label-border">
              <div class="border_left hidden-xs"></div>
              <div class="border_right"></div>
              </small>
            --><span>Main Menu</span>
          </li>
          <li class="nav-item"><a class="nav-link" aria-controls="sidebarDashboard" aria-expanded="false" role="button" data-bs-toggle="collapse" href="#sidebarDashboard"><i class="iconoir-home-simple menu-icon"></i><span>Tổng Quan</span></a>
            <div class="collapse" id="sidebarDashboard">
              <ul class="nav flex-column">
                <li class="nav-item"><a class="nav-link" href="./Dashboard/Analytics.jsp" target="main">Phân Tích</a></li>
                <!-- end nav-item-->

              </ul>
              <!-- end nav-->
            </div>
            <!-- end startbarApplications-->
          </li>
          <!-- end nav-item-->
          <li class="nav-item"><a class="nav-link" aria-controls="sidebarArticle" aria-expanded="false" role="button" data-bs-toggle="collapse" href="#sidebarArticle"><i class="iconoir-journal-page menu-icon"></i><span>Quản Lý Mẫu</span></a>
            <div class="collapse" id="sidebarArticle">
              <ul class="nav flex-column">
                <li class="nav-item"><a class="nav-link" href="./Template/Create.jsp" target="main">Tạo Mẫu</a></li>
                <!-- end nav-item-->
                <li class="nav-item">
                	<form id="menuForm" class="nav-link" action="../AdminListTemplateServlet" method="get" target="main">
					    <input id="submitBtn" style="border: none; background-color: white; padding: 0px" type="submit" value="Xem Danh Sách Mẫu" />
					</form>
                </li>
                <!-- end nav-item-->
                <li class="nav-item"><a class="nav-link" href="./Dashboard/Dashboard.jsp" target="main">Danh Mục Mẫu</a></li>
                <!-- end nav-item-->
              </ul>
              <!-- end nav-->
            </div>
            <!-- end startbarApplications-->
          </li>
          <!-- end nav-item-->
        </ul>
        <!-- end navbar-nav--->
      </div>
    </div>
    <!-- end startbar-collapse-->
  </div>
  <!-- end startbar-menu-->
</div>
<!-- end startbar-->
<div class="startbar-overlay d-print-none"></div>
    <!-- end leftbar-tab-menu-->
    <div class="page-wrapper">
      <!-- Page Content-->
      <div class="page-content">
        <div class="container-xxl">
        	<!-- Main -->
        	<iframe style="width: 100%; height: 500px;" name="main" src="Template/List.jsp"></iframe>
        	<!-- End main -->
        </div>
        <!-- container-->
        <!-- Start Footer-->
        <footer class="footer text-center text-sm-start d-print-none">
  <div class="container-xxl">
    <div class="row">
      <div class="col-12">
        <div class="card mb-0 rounded-bottom-0">
          <div class="card-body">
            <p class="text-muted mb-0">©
              <script>document.write(new Date().getFullYear())</script> T Solution<span class="text-muted d-none d-sm-inline-block float-end">Crafted with<i class="iconoir-heart text-danger"></i>by Tuan</span>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</footer>
        <!-- end footer-->
      </div>
      <!-- end page content-->
    </div>
    <!-- end page-wrapper-->
    <!-- Javascript-->
    <!-- vendor js-->
    <script src="https://cdn.jsdelivr.net/npm/notyf@3/notyf.min.js"></script>
    <script src="admin/assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="admin/assets/libs/simplebar/simplebar.min.js"></script>
    <script src="admin/assets/js/app.js"></script>
    <script src="admin/assets/js/main.js"></script>
  </body>
  <!-- end body-->
</html>