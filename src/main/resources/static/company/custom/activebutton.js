function checkClassInClude(elm, className) {
  console.log(elm.classList);
  return elm.classList.contains(className);
}

const onclickChange = function(elm) {

  let idCompany = elm.id.split("-");
  console.log(`idCompany:${idCompany}`)

  $.ajax({
    url: `/admin/change-company-status?id=${idCompany[1]}`,
    processData: false,
    dataType: 'json',
    contentType: 'application/json',
    type: 'GET',
    success: function (data) {
    },
    error: function (xhr, status, error) {
      const response = JSON.parse(xhr.responseText);
      console.log(response);
      let errMsg = "";
      if (("responseBody" in response)) {
        errMsg = "message" in response.responseBody ? "Company not found" : "Internal Server error";
      } else {
        errMsg = "Internal server error";
      }
      handle_errors(errMsg);
    }
  });

  console.log(elm.id);
  console.log(elm);
  if (checkClassInClude(elm, 'btn-danger')) {
    elm.classList.remove("btn-danger");
    elm.classList.add('btn-success');
    elm.innerHTML = "ACTIVE";
  } else if (checkClassInClude(elm, 'btn-success')) {
    elm.classList.remove("btn-success");
    elm.classList.add('btn-danger');
    elm.innerHTML = "INACTIVE";
  }


}


// $(document).ready(function () {
//   $('.switch-status input').on('change', function () {
//     var dad = $(this).parent();
//
//     if ($(this).is(':checked')) {
//       dad.addClass('switch3-checked');
//
//     } else {
//       dad.removeClass('switch3-checked');
//     }
//
//   });
// });