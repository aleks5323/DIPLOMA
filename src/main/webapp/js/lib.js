$.getScript('/js/date.js');
$.getScript('/js/toastr/toastr.min.js');
$.getScript('/js/jquery.cookie.js');
$.getScript('/js/crypto-js-4.0.0/crypto-js.js');

function notify(params) {
    const toastrOptions = {
        "closeButton": true,
        "newestOnTop": true,
        "timeout": 5000,
        // "preventDuplicates": true
    };

    var type = params['type'];
    var msg = params['msg'];
    var header = params['header'];

    const types = ['error', 'success', 'warning', 'remove', 'clear'];
    if (types.includes(type)) {
        toastr[type](msg, header, toastrOptions);
        playNotificationSound(type);
    }
    else
        console.log("Toastr: no such type");
}

function playNotificationSound(type) {
    var obj = document.createElement("audio");
    obj.src = "/sounds/" + type + ".mp3";
    obj.volume = 0.3;
    obj.autoPlay = false;
    obj.preLoad = "auto";
    obj.controls = true;

    obj.play();
}

function resetForm(formName) {
    var form = $('form[name=' + formName + ']');

    $.each(form, function(i, item) {
        item.reset();
    });

    var inputs = form.find('input');

    inputs.each(function () {
        $(this).removeClass('is-invalid');
        $(this).removeClass('is-valid');
    });
}

function includeModalWindow(path) {
    $.get(path, function(data) {
        $("#modalsContainer").append(data);
    });
}

function includePage(path = null) {
    if (path == null)
        $("#pageContent").empty();
    else
        $.get(path, function(data) {
            $("#pageContent").html(data);
        });
}

function closeModalWindow(params, jqXHR) {
    let window = "#" + params['modalId'];
    $(window).modal('hide');
}

function showModalWindow(windowName) {
    let window = "#" + windowName;
    $(window).modal('show');
}

function closeLoginModal(params, jqXHR) {
    $('#loginModal').modal('hide');
    // $("#loginButton").hide();
    // var usernameLine = $.cookie("login");
    // $("#userString").text("Hello, " + usernameLine);
    // $("#userPanel").show();
}

function commonErrors(params, jqXHR, exception = null) {
    var msg = '';
    var type = '';
    if (jqXHR.status === 0) {
        msg = 'No connection to server.\n Please check your network connection or try again later.';
        type = 'error';
    } else if (jqXHR.status == 404) {
        msg = 'Requested page not found. [404]';
        type = 'error';
    } else if (jqXHR.status == 401) {
        msg = 'Your login/password is incorrect. [401]';
        type = 'error';
    } else if (jqXHR.status == 500) {
        msg = 'Internal Server Error [500].';
        type = 'error';
    } else if (exception === 'parsererror') {
        msg = 'Requested JSON parse failed.';
        type = 'error';
    } else if (exception === 'timeout') {
        msg = 'Time out error.';
        type = 'error';
    } else if (exception === 'abort') {
        msg = 'Ajax request aborted.';
        type = 'error';
    } else {
        msg = 'Uncaught Error.\n' + jqXHR.responseText;
        type = 'error';
    }
    notify({'type': type, 'msg': msg, 'header': "An error occurred!"});
}

function performMultiActions(params) {
    $.each(params, function(i, action) {
        action.fn(action.params);
    });
}

function performRequest(path, method = "GET", headers = null, data = null, onSuccess = null, successParams = null, onFail = null, failParams = null) {

    $.ajax(
        {
            "url": path,
            "method": method,
            "timeout": 0,
            "headers": headers,
            "data": data,
            // complete: function(jqXHR) {
            //     onSuccess(successParams, jqXHR);
            // },
            // error: function (jqXHR, exception) {
            //     onFail(failParams, jqXHR, exception);
            // }
            complete: function (jqXHR, status) {
                if (status == "success") {
                    onSuccess(successParams, jqXHR);
                }
                else if (status == "error") {
                    onFail(failParams, jqXHR, status);
                    commonErrors(failParams, jqXHR, status);
                }
            }
        }
    );
}

function performTable() {
    performRequest("/api/conversations/list",
        null,
        null,
        null,
        buildTable,["convos"]);
}

function buildTable(params, data) {
    mcount = 1;
    data = data.responseText;
    let tableRows = "";
    tableName = "#" + params[0] + " > tbody";

    $.each(JSON.parse(data), function(i, item){
        console.log(item);

        var reqDate = item.reqDate == null ? "-" : new Date(item.reqDate).toString('dd.MM.yyyy HH:mm');
        var resDate = item.resDate == null ? "-" : new Date(item.resDate).toString('dd.MM.yyyy HH:mm');
        var status = item.cstatus == null ? "-" : item.cstatus;

        tableRows += "<tr class=\"tt\" myId=" + item.cid + ">";
        tableRows += "<th scope=\"row\">" + mcount + "</th>";
        tableRows += "<td>" + reqDate + "</td>";
        tableRows += "<td>" + resDate + "</td>";
        tableRows += "<td>" + item.authorName + "</td>";
        tableRows += "<td>" + status + "</td>";
        tableRows += "</tr>";
        mcount++;
    });

    $(tableName).html(tableRows);
    $('#spinner').hide();
}

function fillModalConvo(id) {
    performRequest("/api/conversations/getConversation/" + id, null, null, null, buildModalConvo, ["conversationInfoModal"]);
}

function buildModalConvo(params, data) {
    var objName = "#" + params[0];
    var obj = $(objName);

    data = JSON.parse(data.responseText);

    var reqDate = data.reqDate == null ? "-" : new Date(data.reqDate).toString('dd.MM.yyyy HH:mm');
    var resDate = data.resDate == null ? "-" : new Date(data.resDate).toString('dd.MM.yyyy HH:mm');
    var status = data.cstatus == null ? "-" : data.cstatus;
    var request = JSON.parse(data.request);

    obj.find("#reqDate").val(reqDate);

    obj.find("#infoEntity").val(request.entity);
    obj.find("#infoEntityItn").val(request.entityItn);
    obj.find("#infoEntityRegNumber").val(request.entityRegNumber);
    obj.find("#infoOrgItn").val(request.orgItn);
    obj.find("#infoPhysItn").val(request.physItn);
    obj.find("#infoNewReqDate").val(request.newReqDate);

    obj.find("#respDate").val(resDate);
    obj.find("#resp").val(data.response);
    obj.find("#performedBy").text(data.authorName);
    obj.find("#status").val(status);
}

function deleteConversation(id) {
    performRequest("/api/conversations/delConversation/" + id,
        null,
        null,
        null,
        performMultiActions,
[
            {
                'fn': notify,
                'params': {'type': 'success', 'msg': 'Conversation successfully deleted!', 'header': 'Success'}
            },
            {
                'fn': performTable,
                'params': null
            }
            ],
        // notify, {'type': 'success', 'msg': 'Conversation successfully deleted!', 'header': 'Success'},
        notify, {'type': 'error', 'msg': 'Conversation cannot be deleted!', 'header': 'Error'});
}

function openLocation(path) {
    window.location = path;
}

function logout() {
    // $.removeCookie("login");
    // $.removeCookie("pass");

    openLocation('/');
}

function applyCookie(params) {
    $.cookie(params["name"], params["value"]);
}