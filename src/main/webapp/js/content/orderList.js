$(function() {
	common.showMessage($("#message").val());
});

function search(id) {
    $("#id").val(id);
	$("#mainForm").submit();
}



function modifyInit(id) {
	$("#id").val(id);
	$("#mainForm").attr("action",$("#basePath").val() + "/orders/modifyInit");
	$("#mainForm").submit();
}