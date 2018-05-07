function remove(id) {
	$("#mainForm").attr("action",$("#basePath").val() + "/businesses/remove" + id);
	$("#mainForm").submit();
}

function search() {
	$("#mainForm").attr("method","GET");
	$("#mainForm").attr("action",$("#basePath").val() + "/businesses");
	$("#mainForm").submit();
}

function modifyInit(id) {
	location.href = $("#basePath").val() + "/businesses/modifyInit" + id;
}