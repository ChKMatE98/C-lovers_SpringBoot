$(document).ready(function() {
	// 모든 문서 버튼 클릭했을 때
	$("#anchorApprovalForm").on("click", function(e) {
		e.preventDefault();
		// 드롭 메뉴바 보임
		$(".approvalForm__dropMenu").toggle();
	});
	
	// 전체 리스트 출력
	$.ajax({
		url: "/electronicsignature/progressTotalList"
	}).done(function(resp){
		for(let i = 0; i < resp.length; i++) {
			let listDiv = $("<div>");
			listDiv.addClass("document__list");
			
			let checkboxDiv = $("<div>");
			checkboxDiv.addClass("documentTable__checkbox");
			
			let labelTag = $("<label>");
			
			let checkboxInput = $("<input>");
			checkboxInput.attr("type", "checkbox");
			
			labelTag.append(checkboxInput);
			checkboxDiv.append(labelTag);
			
			let idDiv = $("<div>");
			idDiv.addClass("documentTable__documentId");
			idDiv.html(resp[i].document_id);
			
			let titleDiv = $("<div>");
			titleDiv.addClass("documentTable__title");
			titleDiv.html(resp[i].title);
			
			let drafterDiv = $("<div>");
			drafterDiv.addClass("documentTable__drafter");
			drafterDiv.html(resp[i].drafter_name);
			
			let draftDateDiv = $("<div>");
			draftDateDiv.addClass("documentTable__draftDate");
			draftDateDiv.html(resp[i].report_date);
			
			let divisionDiv = $("<div>");
			divisionDiv.addClass("documentTable__division");
			divisionDiv.html("");
			
			let statusDiv = $("<div>");
			statusDiv.addClass("documentTable__status");
			statusDiv.html(resp[i].status);
			
			listDiv.append(checkboxDiv).append(idDiv).append(titleDiv).append(drafterDiv).append(draftDateDiv).append(divisionDiv).append(statusDiv);
			$(".documentTable__body").append(listDiv);
		}
	})
})