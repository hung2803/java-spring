const iconBar = document.querySelector(".button-nav i");
const nav = document.querySelector(".nav");
const headerSearch = document.querySelector('.header-search')
const headerLeft = document.querySelector('.header-left')
const body = document.querySelector('body')
let starId = 0;

iconBar.addEventListener("click", () => {
  iconBar.classList.toggle("fa-times")
  nav.classList.toggle("open");
  headerSearch.classList.toggle('open')
  headerLeft.classList.toggle('open')
  body.classList.toggle('open')
});

$('#carousel-example').on('slide.bs.carousel', function (e) {
  /*
      CC 2.0 License Iatek LLC 2018 - Attribution required
  */
  var $e = $(e.relatedTarget);
  var idx = $e.index();
  var itemsPerSlide = 5;
  var totalItems = $('.carousel-item').length;

  if (idx >= totalItems-(itemsPerSlide-1)) {
      var it = itemsPerSlide - (totalItems - idx);
      for (var i=0; i<it; i++) {
          // append slides to end
          if (e.direction=="left") {
              $('.carousel-item').eq(i).appendTo('.carousel-inner');
          }
          else {
              $('.carousel-item').eq(0).appendTo('.carousel-inner');
          }
      }
  }
});

//  for để thêm sao hoặc đánh giá giảm sao
function changeStarReview(id){
  if (starId != 0){
    for (let i = 1; i < starId + 1; i++){
      $('#star-'+ i).removeClass('fa-star')
      $('#star-'+ i).addClass('fa-star-o')
      $('#star-'+ i).val("")
    }
    for (let i = 1; i < id + 1; i++){
      $('#star-'+ i).addClass('fa-star')
      $('#star-'+ i).removeClass('fa-star-o')
      $('#star-'+ i).val("")
    }
  }
  for (let i = 1; i < id + 1; i++){
    $('#star-'+ i).removeClass('fa-star-o')
    $('#star-'+ i).addClass('fa-star')
    $('#star-'+ i).val(i)
  }
  starId = id
}
function setStarReview(id){
  for (let i = 0; i < id; i++){
    $('#star-'+ (i +1)).removeClass('fa-star-o')
    $('#star-'+ (i +1)).addClass('fa-star')
    starId = id;
  }
}

function removeStarReview(){
  for (let i = 1; i < 6; i++){
    $('#star-'+ (i)).addClass('fa-star-o')
    $('#star-'+ (i)).removeClass('fa-star')
  }
}

