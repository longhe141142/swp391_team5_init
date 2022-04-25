// function validate(val) {
//     v1 = document.getElementById("fname");
//     v2 = document.getElementById("lname");
//     v3 = document.getElementById("email");
//     v4 = document.getElementById("mob");
//     v5 = document.getElementById("job");
//     v6 = document.getElementById("ans");

//     flag1 = true;
//     flag2 = true;
//     flag3 = true;
//     flag4 = true;
//     flag5 = true;
//     flag6 = true;

//     if (val >= 1 || val == 0) {
//         if (v1.value == "") {
//             v1.style.borderColor = "red";
//             flag1 = false;
//         }
//         else {
//             v1.style.borderColor = "green";
//             flag1 = true;
//         }
//     }

//     if (val >= 2 || val == 0) {
//         if (v2.value == "") {
//             v2.style.borderColor = "red";
//             flag2 = false;
//         }
//         else {
//             v2.style.borderColor = "green";
//             flag2 = true;
//         }
//     }
//     if (val >= 3 || val == 0) {
//         if (v3.value == "") {
//             v3.style.borderColor = "red";
//             flag3 = false;
//         }
//         else {
//             v3.style.borderColor = "green";
//             flag3 = true;
//         }
//     }
//     if (val >= 4 || val == 0) {
//         if (v4.value == "") {
//             v4.style.borderColor = "red";
//             flag4 = false;
//         }
//         else {
//             v4.style.borderColor = "green";
//             flag4 = true;
//         }
//     }
//     if (val >= 5 || val == 0) {
//         if (v5.value == "") {
//             v5.style.borderColor = "red";
//             flag5 = false;
//         }
//         else {
//             v5.style.borderColor = "green";
//             flag5 = true;
//         }
//     }
//     if (val >= 6 || val == 0) {
//         if (v6.value == "") {
//             v6.style.borderColor = "red";
//             flag6 = false;
//         }
//         else {
//             v6.style.borderColor = "green";
//             flag6 = true;
//         }
//     }

//     flag = flag1 && flag2 && flag3 && flag4 && flag5 && flag6;

//     return flag;
// }

var btnUpload = $("#upload_file"),
    btnOuter = $(".button_outer");
btnUpload.on("change", function (e) {
    var ext = btnUpload.val().split('.').pop().toLowerCase();
    if ($.inArray(ext, ['gif', 'png', 'jpg', 'jpeg']) == -1) {
        $(".error_msg").text("Not an Image...");
    } else {
        $(".error_msg").text("");
        btnOuter.addClass("file_uploading");
        setTimeout(function () {
            btnOuter.addClass("file_uploaded");
        }, 3000);
        var uploadedFile = URL.createObjectURL(e.target.files[0]);
        setTimeout(function () {
            $("#uploaded_view").append('<img src="' + uploadedFile + '" />').addClass("show");
        }, 3500);
    }
});
$(".file_remove").on("click", function (e) {
    $("#uploaded_view").removeClass("show");
    $("#uploaded_view").find("img").remove();
    btnOuter.removeClass("file_uploading");
    btnOuter.removeClass("file_uploaded");
});


function getElmByClass(className) {
    return document.getElementsByClassName(className);
}
function validateEmail(email) {
    return email.match(
        /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    );
};
function getAllInput() {
    forms = getElmByClass('form-group');
    let requestBody = {
        error: false
    }
    let msgErr = '';
    let formElm = [];
    if (forms.length > 0)
        for (form of forms) {
            let obj = getChild(form).obj;
            if (getChild(form).obj.error) {
                requestBody.error = true;
                msgErr += getChild(form).errorMsg;
            }
            requestBody = Object.assign(requestBody, obj);
        }
    requestBody.msgErr = msgErr;
    return requestBody;
}

function checkPhoneRegex(phone) {
    const regex = /^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/;
    return regex.test(phone);
}

function isEmpty(str) {
    return (!str || str.length === 0 );
}

function getChild(element) {
    let obj = {}
    let flag = 0;
    let msg = '';
    if (element.hasChildNodes()) {
        flag = true;
        let field;
        let value;
        let inputTag = element.getElementsByTagName('input');
        let textAreaTag = element.getElementsByTagName('textarea');

        if (inputTag.length > 0) {
            field = inputTag[0].id;
            value =  inputTag[0].value;
            if (field === 'personnelSize') {
                value = Number(value);
                if (value instanceof Error) {
                    obj.error = true;
                }
            } else if (
                field === 'phone'
            ) {
                if (!checkPhoneRegex(value)) {
                    msg += `Phone number is incorrect format\n`
                    obj.error = true;
                }
            } else if (field === 'email') {
                if (!validateEmail(value)) {
                    msg += `Email is incorrect format\n`
                    obj.error = true;
                }
            }
            if (isEmpty(value)&& ! isEmpty(field)) {
                inputTag[0].style.borderColor = "red";
                obj.error = true;
                console.log(`${field} : ${value}`);
                msg += `${field} must not be null`
            }
        } else if (textAreaTag.length > 0) {
            field = textAreaTag[0].id;
            value =  textAreaTag[0].value;

            if (isEmpty(value)&& ! isEmpty(field)) {
                textAreaTag[0].style.borderColor = "red";
                obj.error = true;
                msg += `${field} must not be null`
            }
        }
        obj[field] = value;
        return {
            obj,
            errorMsg: msg
        };
    }
    return null;
}

function reArrangeFields(objects) {
    let companyArrayFields = ["name", "phone", "email", "personnelSize", "description", "companyIntro", "address", "foundingAt"]
}

function reArrangeFields(objectName, objectArrayFields, allElement) {
    let parent = {
        [objectName]: {}
    }

    for (const [key, value] of Object.entries(allElement)) {
        console.log(`${key}: ${value}`);
        if (objectArrayFields.includes(key)) {
            parent[objectName][key] = value;
        }
    }
    return parent[objectName];
}

function handle_errors(msg) {
    let str = "You have errors with the following fields; \n";
    str+=msg;
    const errors_el = document.querySelector('.errors-custom');
    let error_el = document.createElement('div');
    error_el.classList.add('error2');
    error_el.innerText = str;

    error_el.addEventListener('click', function () {
        errors_el.removeChild(error_el);
    });
    errors_el.appendChild(error_el);
}

function isValidDate(dateString) {
    var regEx = /^\d{4}\/\d{2}\/\d{2}$/;
    if (!dateString.match(regEx)) return false;  // Invalid format
    return true;
}

function reCorrectDateString(dateString) {
    let val = dateString.split('/');
    let date = ''
    for (let i = val.length - 1; i >= 0; i--) {
        date += i === 0 ? val[i] : val[i] + '/';
    }
    return date;
}

// $.noConflict();
$(document).ready(function () {
    $('#submit-now').submit(function (e) {


        e.preventDefault();
        // $('[data-toggle="tooltip"]').tooltip();
        let getAll = getAllInput();
        console.log(getAll);
        let companyArrayFields = ["name", "phone", "email", "personnelSize", "description", "companyIntro", "address", "foundingAt"]
        let company = reArrangeFields("company", companyArrayFields, getAllInput());
        let user = reArrangeFields("user", ["email", "passwordEncoder"], getAllInput())
        let foundingAt = reCorrectDateString(document.getElementById('foundingAt').value);
        if (!isValidDate(foundingAt)) {
            getAll.error = true;
            getAll.msgErr+='Invalid date format of founding field\n'
            console.log("foundingAt"+foundingAt)
        }else{
            console.log("foundingAt"+foundingAt)

            company.foundingAt = foundingAt;
        }
        console.log(company)
        const dataPendingToSend = {
            user, company
        }


        // $('[data-toggle="tooltip"]').tooltip();
        if (!getAll.error) {
            $.ajax({
                url: '/admin/addCompany',
                data: JSON.stringify({...dataPendingToSend}),
                processData: false,
                dataType: 'json',
                contentType: 'application/json',
                type: 'POST',
                success: function (data) {
                    $('#uploadImage').modal({
                        backdrop: 'static',
                        keyboard: false
                    });
                    $("#uploadImage").modal('show');
                    $("#addCompanyModal").modal('hide');
                    document.getElementById("companyId").innerText = `${data.id}`
                },
                error: function (xhr, status, error) {
                    const response = JSON.parse(xhr.responseText);
                    console.log(response);
                    let errMsg = "";
                    if (("responseBody" in response)) {
                        errMsg = "user_existed" in response.responseBody ? "User already existed" : "Internal Server error";
                    } else {
                        errMsg = "Internal server error";
                    }
                    handle_errors(errMsg);
                }
            });

        }else{
            handle_errors(getAll.msgErr);
        }
    });

    //upload file
    $('#upload-file').submit(function (e) {    // $('[data-toggle="tooltip"]').tooltip();
        e.preventDefault();
        var formData = new FormData();
        var files = $('#upload_file')[0].files;
        if (files.length === 0) {
            handle_errors("YOU MUST INPUT IMAGE");
        } else {
            formData.append('file', $('#upload_file')[0].files[0])


            //call api upload
            $.ajax({
                url: `/admin/upload-company-image?id=${document.getElementById('companyId').innerHTML}`,
                type: 'POST',
                data: formData,
                processData: false,  // tell jQuery not to process the data
                contentType: false,  // tell jQuery not to set contentType
                success: function (data) {
                    $('#confirmModal').modal({
                        backdrop: 'static',
                        keyboard: false
                    });
                    $("#confirmModal").modal('toggle');

                    $("#uploadImage").modal('hide');
                }
            });


        }

    });



    //redirect to page

});




