function emptyCheck() {
	if( document.comFrm.memberId.value.length < 2 ) {
		alert('작성자를 두 글자 이상 입력해주세요.');
		return false;
	}
	
	if( document.comFrm.commentPwd.value.length < 2 ) {
		alert('비밀번호를 두 글자 이상 입력해주세요.');
		return false;
	}
	
	if( document.comFrm.commentContent.value.length == 0 ) {
		alert('내용을 입력해주세요.');
		return false;
	}
	
	return true;
}

function emptyCheck2() {
	if( document.comFrm.commentContent.value.length == 0 ) {
		alert('내용을 입력해주세요.');
		return false;
	}
	return true;
}

function setModalBoard(type) {
	document.deleteFrm.modalType.value = type;
}


function setModalComment(commentNum, type) {
	document.deleteFrm.commentNum.value = commentNum;
	document.deleteFrm.modalType.value = type;
}

function deleteBoard(memberId) {
	var boardNum = document.getElementById("boardNum").value;
	if(confirm('삭제하시겠습니까?')) {
		var data = {
			method: "POST",
			body: JSON.stringify({memberId, boardNum}),
			headers: {
				"Content-Type" : "application/json"
			}
		};
		fetch('/board/delete', data).then((response) => {
			return response.text();
		}).then((response) => {
			if(response == 'success') {
				alert('게시물을 삭제했습니다.');
				location.href='/board/';
			}else if(response == 'fail'){
				alert('본인의 게시물만 삭제할 수 있습니다.')
			}
		}).catch(() => {
			console.log('에러');
		})

	}else {
		
	}
}

function deleteComment(commentNum) {
	if(confirm('삭제하시겠습니까?')) {
		location.href="/board/deleteComment?commentNum="+commentNum
	}else {
		
	}
}

function modal() {
	var modalType = document.deleteFrm.modalType.value;
	var boardNum = document.deleteFrm.boardNum.value;
	var commentNum = document.deleteFrm.commentNum.value;
	var pwd = document.deleteFrm.pwd.value;

	if( pwd.length == 0 ) {
		alert('비밀번호를 입력해주세요');
		return;
	}

	var modalData = {
		method: "POST",
		body: JSON.stringify({modalType, boardNum, commentNum, pwd}),
		headers: {
			"Content-Type" : "application/json"
		}
	}

	fetch('/board/modal', modalData).then((response) => {
		return response.text();
	}).then((response) => {
		if( response == 'board' ) {
			alert('게시글을 삭제했습니다.')
			location.href="/board/";
		}else if( response == 'update' ) {
			location.href="/board/update?boardNum="+boardNum;
		}else if( response == 'comment' ) {
			alert('댓글을 삭제했습니다');
			location.reload();
		}else if( response == 'fail') {
			alert('비밀번호가 틀립니다.');
		}
	}).catch(() => {
		console.log("에러");
	})
}
