function initPage(){
	updateHtml();
	$("#selTask").remove();
	onTaskChange();
	setControlValue();
}

function updateHtml(){
	var htmlString = "<select class='form-control input-sm' style='margin:8px 0; width: 180px;'>";
	htmlString = htmlString + $("#selTask").html() + "</select>";
	$(".panel-title").first().after(htmlString);
}

function onTaskChange(){
	$("select.form-control").first().change(function(){
		$('input[name="id.taskId"]').val($(this).val());
	});
}

function setControlValue(){
	$('textarea[name="body"]').attr("rows", "10");
	var taskid = $("select.form-control").val();
	$('input[name="id.taskId"]').val(taskid);
}