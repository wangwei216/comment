$(function() {
    common.showMessage($("#message").val());
    $("#mainForm").validate({
        rules : {
            "title" : "required",
            "subtitle" : "required",
            "weight" : {
                required : true,
                digits : true,
                maxlength : 5
            }
        },
        messages : {
            "title" : "请输入标题！"
        }
    });
});

function modify() {
    $("#mainForm").submit();
}

function goback() {
    location.href = $('#basePath').val() + '/business';
}