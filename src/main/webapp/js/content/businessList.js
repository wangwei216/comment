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
    $("#id").val(id);
    $("#mainForm").attr("action",$("#basePath").val() + "/businesses/modifyInit");
    $("#mainForm").submit();
}

function add() {
    $("#mainForm").attr("method","PUT");
    $("#mainForm").attr("action",$("#basePath").val() + "/businesses/addPage");
    $("#mainForm").submit();
}