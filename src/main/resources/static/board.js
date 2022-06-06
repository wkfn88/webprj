function recommend(memberId, boardNum) {
    if( memberId == null ) {
        alert('로그인이 필요합니다.');
        return;
    }

    var data = {
        method: "POST",
        body: JSON.stringify({memberId, boardNum}),
        headers: {
            "Content-Type" : "application/json"
        }
    }

    fetch('/board/recommend', data).then((response) => {
        return response.json()
    }).then((response) => {
        if( response.result == 'success') {
            var recoCount = document.getElementById("recoCount");
            recoCount.innerText = "";
            recoCount.innerText = " 추천 : " + response.recoCount;
        }else {
            alert('한 게시물에 한번만 추천할 수 있습니다.');
        }
    })
}