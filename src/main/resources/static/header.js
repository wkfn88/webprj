function login() {
    var memberId = document.getElementById("memberId").value;
    var memberPwd = document.getElementById("memberPwd").value;

    var loginData = {
        method : 'POST',
        body : JSON.stringify({memberId, memberPwd}),
        headers : {
            'Content-Type' : 'application/json'
        }
    };

    fetch('/member/login', loginData).then((response) => {
        return response.text();
    }).then((response) => {
        if( response == 'success' ) {
            location.reload();
        }else if( response == 'fail') {
            alert('일치하는 계정이 없습니다.');
        }
    }).catch(() => {
        console.log("에러");
    });
}