function CreatReview(id, content, rating, accountId, novelsId, datePost) {
    this.id = id;
    this.content = content;
    this.rating = rating;
    this.accountId = accountId;
    this.novelsId = novelsId;
    this.datePost = datePost;
  }


let baseUrlReviewNovels = "http://192.168.1.175:8686/api/v1/review";

function addReview(){
    let novelsReviewId = $("#Novels-Review-Id").val()
    let reviewContent = $('#review-content').val()
    let stars = 0;
    let novelsId = $("#novels-id-update").val();
    let accountId = localStorage.getItem("id")
    let datePost = Date.now();
    for (let i = 1; i < 6; i++){
       let star = $('#star-'+ i).val();
       if (star != ''){
        stars = $('#star-'+ i).val();
        $('#star-'+ i).val('')
       }
    }
    let url = "/create"
    let type = "POST"
    if (novelsReviewId != ""){
      url = "/update"
      type = "PUT"
    }
    let request = new CreatReview(novelsReviewId, reviewContent, stars, accountId, novelsId, datePost);

    $.ajax({
        url: baseUrlReviewNovels + url,
        type: type,
        beforeSend: function (xhr) {
          xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem("token"));
        },
        contentType: "application/json",
        data: JSON.stringify(request),
        error: function (err) {
          confirm(err.responseJSON.message)
        },
        success: function(res){
          $('#modalReview').modal('hide')
          getlistNovels();
          removeStarReview();
        }
      });
    }