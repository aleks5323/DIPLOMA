$.getScript('/js/date.js');
$.getScript('/js/toastr/toastr.min.js');

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
    if (types.includes(type))
        toastr[type](msg, header, toastrOptions);
    else
        console.log("Toastr: no such type");
}

function includeModalWindow(path) {
    $.get(path, function(data) {
        $("#modalsContainer").append(data);
    });
}

function closeLoginModal(params, jqXHR) {
    if (jqXHR.status == 202)
    {
        $('#loginModal').modal('hide');
        $("#loginSection").load("html/userHello.html");
        var usernameLine = $("#userString").text();
        $("#userString").text("usernameLine");
    }
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

function performRequest(path, method = "GET", headers = null, data = null, onSuccess = null, successParams = null, onFail = commonErrors, failParams = null) {

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
                if (status == "success")
                    onSuccess(successParams, jqXHR);
                else if (status == "error")
                    onFail(failParams, jqXHR, status);
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
        tableRows += "<td>" + item.performedBy + "</td>";
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

    obj.find("#reqDate").val(reqDate);
    obj.find("#req").val(data.request);
    obj.find("#respDate").val(resDate);
    obj.find("#resp").val(data.response);
    obj.find("#performedBy").text(data.performedBy);
    obj.find("#status").val(status);
}

function deleteConversation(id) {
    performRequest("/api/conversations/delConversation/" + id,
        null,
        null,
        null,
        notify, {'type': 'success', 'msg': 'Conversation successfully deleted!', 'header': 'Success'},
        notify, {'type': 'error', 'msg': 'Conversation cannot be deleted!', 'header': 'Error'});
}