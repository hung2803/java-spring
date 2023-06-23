let baseUrlAuth = "http://localhost:8686/api/v1";

function Account(username, password) {
    this.username = username;
    this.password = password;
}
  
function AccountSignUp(username, password, fullName, dateOfBirth, phoneNumber, email) {
    this.username = username;
    this.password = password;
    this.fullName = fullName;
    this.dateOfBirth = dateOfBirth;
    this.phoneNumber = phoneNumber;
    this.email = email;
}

function login() {
    event.preventDefault()
    let username = document.getElementById("inputUsername").value;
    let password = document.getElementById("password").value;
    let account = new Account(username, password);
    //   ------------------------------------- API ĐĂNG NHẬP -------------------------------------
    $.ajax({
      url: baseUrlAuth + "/auth/login-jwt",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(account),
      error: function (err) {
        confirm(err.responseJSON.message)
      },
      success: function (data) {
        localStorage.setItem("fullName", data.fullName);
        localStorage.setItem("id", data.id);
        localStorage.setItem("role", data.role);
        localStorage.setItem("token", data.token);
        localStorage.setItem("username", data.username);
        window.location.href = "./index.html"
      }
    });
}

function signUp() {
  event.preventDefault()
  let username = document.getElementById("username-su").value;
  let password = document.getElementById("password-su").value;
  let fullName = document.getElementById("fullName-su").value;
  let dateOfBirth = document.getElementById("dateOfBirth-su").value;
  let phoneNumber = document.getElementById("phoneNumber-su").value;
  let email = document.getElementById("email-su").value;

  let account = new AccountSignUp(username, password, fullName, dateOfBirth, phoneNumber, email);

    // ------------------------------------- CALL API ĐĂNG KÝ -------------------------------------
    $.ajax({
      url: baseUrlAuth + "/account/create",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(account),
      error: function (err) {
          confirm(err.responseJSON.message)
      },
      success: function (data) {
          // window.location.href = "./index.html"
          window.location.href = "./login.html"
      }
  });
  window.location.href = "./login.html"
}