
let novelsName = "";
let categoryName = "";
let authorName = "";
let status = "";
let sortType = "asc";
let sortBy = "id";
let pageSize = 8;
let pageNumber = 1;
let novelsList = [];


let baseUrlNovels = "http://localhost:8686/api/v1/novels";



$(function (){
getfilterNovels();
getAllNovels()
});

function SearchNovelsRequest(novelsName, categoryName, authorName, status,
  sortType, sortBy, pageSize, pageNumber) {

  this.novelsTitle = novelsName;
  this.categoryName = categoryName;
  this.author = authorName;
  this.status = status;
  this.sortType = sortType;
  this.sortField = sortBy;
  this.size = pageSize;
  this.page = pageNumber;
}

function CreatNovelsRequest(id, name, image, authorName, status, categoryName) {
  this.id = id;
  this.novelsTitle = name;
  this.image = image;
  this.author = authorName;
  this.categoryName = categoryName;
  this.status = status;
}

function getfilterNovels() {
// lấy ra các điều kiện filter, gán vào các biến
getlistNovels();

}

// tìm kiếm theo tên
function fillterNovelsName(){

// lấy ra các giá trị filter để tìm kiếm
novelsName = $("#search-Novels").val()  
getlistNovels();
}

function getlistNovels(){
let request = new SearchNovelsRequest(novelsName, categoryName, authorName, status,
  sortType, sortBy, pageSize, pageNumber)
//   ------------------------------------- API TÌM KIẾM NOVELS -------------------------------------
$.ajax({
  url: baseUrlNovels + "/search",
  type: "POST",
  beforeSend: function (xhr) {
    xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem("token"));
  },
  contentType: "application/json",
  data: JSON.stringify(request),
  error: function (err) {
    console.log(err)
    confirm(err.responseJSON.message)
  },
  success: function (data) {
    novelsList = data.content;
    fillNovelsToTable(novelsList);
    buildPaginationNovels(data.number + 1, data.totalPages);
  }
});
}

//   ------------------------------------- API GETALL NOVELS -------------------------------------
function getAllNovels(){

  $.ajax({
    url: baseUrlNovels + "/get-all",
    type: "GET",
    contentType: "application/json",
    error: function (err) {
      confirm(err.responseJSON.message)
    },
    success: function (data) {
      for(var i = 0; i<data.length; i++){
         $('#image-novels-header').append(
          `<div id="header-`+i+`" class="carousel-item col-12 col-sm-6 col-md-4 col-lg-3">
            <img style="height: 100%;"  src="`+ data[i].image +`" class="img-fluid mx-auto d-block" alt="img`+(i+1)+`">
            <div class="carousel-caption d-none d-md-block">
              <p class="font-weight-bold"> `+ data[i].novelsTitle+`</p>
            </div>
          </div>`
        )
      }
      $("#header-0").addClass('active')
    }
  });
}

function confirmDelete(id){
  $("#novelsId-update").empty().append(`<input type="text" hidden id="novelsId" value="`+id+`">`)
  $('#modalConfirmDelete').modal('show')
}
function fillNovelsToTable(data){
// xóa tất cả data trước khi f5 làm mới lại trang
    $("#content-novels").empty()
    let role = localStorage.getItem("role");
    let element ;
    for (var i = 0; i < data.length; i++){
      if (role === "ADMIN"){
        element = `   <div class="col-l-3 col-s-6 mt-4">
        <div class="item-comic">
            <div class="img-wrap-update" style="position: relative;">
              <div id="novelsId-update">
              </div>
                <a href="">
                    <img src="`+ data[i].image +`"></img>
                    <div class="carousel-caption d-none d-md-block p-0 img-name-novels">
                        <p>`+ data[i].novelsTitle +`</p>
                    </div>
                </a>
            </div>
            <div class="comic-info">
              <button style="width:48%" class="btn btn-success" type="button" onclick="updateNovels(`+data[i].id +`)" >Sửa</button>
              <button style="width:48%" class="btn btn-danger" type="button" onclick="confirmDelete(`+data[i].id +`)" >Xóa</button>
            </div>
        </div>
        </div> `
      }else {
        element = `   <div class="col-l-3 col-s-6 mt-4">
        <div class="item-comic">
            <div class="img-wrap-update" style="position: relative;">
                <a href="">
                    <img src="`+ data[i].image +`"></img>
                    <div class="carousel-caption d-none d-md-block p-0 img-name-novels">
                        <p>`+ data[i].novelsTitle +`</p>
                    </div>
                </a>
            </div>
            <div class="comic-info">
            <a href="https://truyenfull.vn/me-vo-khong-loi-ve-982891/chuong-1/">
            <button class="btn btn-success p-1" type="button" >Đọc Truyện</button>
            </a>
              
              <button class="btn btn-danger p-1" type="button" onclick="reviewer(`+ data[i].id + `)" >Đánh Giá</button>
              <h7 class="pl-3">`+ data[i].totalRating + `<i class="fa fa-star text-warning" aria-hidden="true"></i></h7>
            </div>
        </div>
        </div> `
      }
      //filter cập nhật lại data
          $("#content-novels").append(element)
          
    }
}

function reviewer(novelsId){
  removeStarReview()
  let accountId = localStorage.getItem("id");
  $.ajax({
    url: baseUrlReviewNovels + "/" + accountId + "/" + novelsId,
    type: "POST",
    beforeSend: function (xhr) {
      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem("token"));
    },
    contentType: "application/json",
    error: function (err) {
      confirm(err.responseJSON.message)
    },
    success: function(res){
      if(res != null){
        $("#novels-id-update").val(novelsId)
        $("#Novels-Review-Id").val(res.id)
        setStarReview(res.rating)
        $('#review-content').val(res.content)
        $('#modalReview').modal('show')
      }
    }
  });
}

function buildPaginationNovels(number, totalPages){
   // kiểm tra nếu trang hiện tại là trang đầu -> disable đi
   if (number === 1) {
    $("#pagination").empty().append(`<li class="pagination-item">
                              <a class="pagination-item__link">
                              <
                              </a></li>`);
  } else {
    $("#pagination").empty().append(`<li class="pagination-item">
                              <a href="#" class="pagination-item__link " onclick="prePage()">
                                <
                              </a></li>`);
  }

  // Dùng hàm for để build ra số trang. Kiểm tra xem trang hiện tại là bao nhiêu thì background vàng
  for (let index = 1; index <= totalPages; index++) {
    if (number === (index)) {
      $('#pagination').append(`<li class="pagination-item pagination-item--active">
                                <a href="" class="pagination-item__link" onclick="chosePage(`+ index + `)">` + index + `</a>
                              </li>`);
    } else {
      $('#pagination').append(`<li class="pagination-item">
                                <a href="" class="pagination-item__link" onclick="chosePage(`+ index + `)">` + index + `</a>
                              </li>`);
    }
  }

  // Kiểm tra nếu trang hiện tại là trang cuối -> disable đi
  if (number === totalPages) {
    $("#pagination").append(`<li class="pagination-item">
                              <a class="pagination-item__link "">
                                >
                              </a></li>`);
  } else {
    $("#pagination").append(`<li class="pagination-item">
                              <a href="#" class="pagination-item__link " onclick="nextPage()">
                                >
                              </a></li>`);
  }
}

function deleteNovels() {
  let novelsId = document.getElementById("novelsId").value;
  //   ------------------------------------- API XOÁ TRUYỆN -------------------------------------
  $.ajax({
    url: baseUrlNovels + "/delete/" + novelsId,
    type: "DELETE",
    beforeSend: function (xhr) {
      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem("token"));
    },
    contentType: "application/json",
    error: function (err) {
      confirm(err.responseJSON.message)
    },
    success: function (data) {
      showAlrtSuccess()
      getlistNovels();
      $('#modalConfirmDelete').modal('hide')
    }
  });


  showAlrtSuccess();
  $('#modalConfirmDelete').modal('hide');
}
 function saveNovels() {
  // Lấy các thông tin cần thiết trên form
  const novelsId = document.getElementById('novelsId').value;
  const novelsTitle = document.getElementById('novelsTitle').value;
  const image = document.getElementById('image').value;
  const author = document.getElementById('author').value;
  const categoryName = document.getElementById('categoryName').value;
  const status = document.getElementById('status').value;


  // Tạo 1 request
  let request = new CreatNovelsRequest(novelsId, novelsTitle, image, author, categoryName,
    status);
  let url = (novelsId === "") ? (baseUrlNovels + "/create") : (baseUrlNovels + "/update");
  let type = (novelsId === "") ? "POST" : "PUT";
  //   ------------------------------------- API UPDATE, THÊM MỚI TRUYỆN -------------------------------------
  $.ajax({
    url: url,
    type: type,
    beforeSend: function (xhr) {
      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem("token"));
    },
    contentType: "application/json",
    data: JSON.stringify(request),
    error: function (err) {
      console.log(err)
      confirm(err.responseJSON.message)
    },
    success: function (data) {
      $('#modalAddAndUpateNovels').modal('hide')
      showAlrtSuccess();
      getlistNovels();
    }
  });

  $('#modalAddAndUpateNovels').modal('hide')
  showAlrtSuccess();
  
}

function chosePage(page) {
  event.preventDefault()
  pageNumber = page;
  getlistNovels();
}
function prePage() {
  event.preventDefault()
  pageNumber--;
  getlistNovels();
}

function nextPage() {
  event.preventDefault()
  pageNumber++;
  getlistNovels();
}

function showAlrtSuccess() {
  $("#success-alert").fadeTo(2000, 500).slideUp(500, function () {
      $("#success-alert").slideUp(3000);
  });
}

function resetFormNovels() {
  $('#novelsId').val('');
  $('#novelsTitle').val('');
  $('#image').val('');
  $('#categoryName').val('');
  $('#author').val('');
  $('#status').val('');
}

  function addNovels(){
$("#novels-title").empty().append(`<h5 class="modal-title">Add Novels</h5>`)
$("#novelsId-update").empty().append(`<input type="text" hidden id="novelsId" value="">`)
resetFormNovels()
  $('#modalAddAndUpateNovels').modal('show')
}

function updateNovels(id){
$("#novelsId-update").empty().append(`<input type="text" hidden id="novelsId" value="`+id+`">`)
  $.ajax({
    url: baseUrlNovels + "/"+ id,
    type: "GET",
    beforeSend: function (xhr) {
      xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem("token"));
    },
    contentType: "application/json",
    error: function (err) {
      confirm(err.responseJSON.message)
    },
    success: function (data) {
      $('#novelsId').val(data.id);
      $('#novelsTitle').val(data.novelsTitle);
      $('#image').val(data.image);
      $('#categoryName').val(data.categoryName);
      $('#author').val(data.author);
      $('#status').val(data.status);
      $("#novels-title").empty().append(`<h5 class="modal-title">Update Novels</h5>`)
      $('#modalAddAndUpateNovels').modal('show')
    }
  });

  
}