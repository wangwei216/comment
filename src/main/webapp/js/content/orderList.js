$(function() {
	common.showMessage($("#message").val());
});

function search() {
	$("#mainForm").submit();
}



function modifyInit(id) {
	$("#id").val(id);
	$("#mainForm").attr("action",$("#basePath").val() + "/orders/modifyInit");
	$("#mainForm").submit();
}