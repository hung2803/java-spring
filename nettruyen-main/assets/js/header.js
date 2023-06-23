"use strict";
$(function () {
    checkLogin();
});

function checkLogin(){
    let username = localStorage.getItem("username");
    let role = localStorage.getItem("role");

    let textUserLogin = '<li class="header__navbar-item header__navbar-user">'
    + '<img src="./assets/img/pikachu.png" alt="" class="header__navbar-user-img" />'
    + '<span class="header__navbar-user-name">'+ username + '</span>'
    + '<ul class="header__navbar-user-menu"><li class="header__navbar-user-item">'
    + '<a href="">Trang Cá Nhân</a></li><li class="header__navbar-user-item">'
    + '<a href="">Cài đặt</a></li><li class="header__navbar-user-item">'
    + '<a href="" onclick="logout()">Đăng xuất</a></li> </ul></li>'


    if(localStorage.getItem("token") === null){
    } else {
     document.getElementById("user-login").innerHTML = textUserLogin;
     if(role == "ADMIN"){
       $('#btn-novels').removeClass('d-none')
     }
    }
 }

 function logout(){
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    localStorage.removeItem("fullName");
    localStorage.removeItem("id");
    localStorage.removeItem("role");
    window.location.href = "/";
 }