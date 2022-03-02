/**
 *  회원가입시 Ajax를 사용하는 이유
 *
 *  1. 요청에 대한 응답을 html이 아닌 Data(Json)로 받기 위해서이다.
 *  >> form 요청은 html로 응답을 받는데 이건 구형 방식임
 *  >> 서버에서 웹은 html로 받고 앱은 json으로 데이터를 받는데, 웹과 앱을 통합하기 위해 json을 보내는 방식으로 서버를 통합함
 *
 *  2. 비동기 통신을 하기 위해서이다.
 *  >> 일반 통신이 일어나면 앱이 잠깐 멈추는데, 비동기 통신이 이걸 막아준다.
 *
 */

let index = {
    init: function () {
        // jQuery 사용
        $("#btn-save").on("click", () => {
            this.save(); // save함수 이벤트로 호출
        });

        $("#btn-update").on("click", () => {
            this.update();
        });

    },

    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        // ajax 호출시 default가 비동기 호출이다.
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청을 한다.
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌.
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body데이터
            contentType: "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (MIME)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때, 기본적으로 모든 것이 문자열 (생긴게 json이라면 javascript 오브젝트로 변경해줌)
        }).done(function (res) {
            // 회원가입 오류 잡기 (아이디 중복일 경우) - GlobalExceptionHandler
            if(res.status === 500) {
                alert("회원가입에 실패하였습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href = "/";
            }

        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },



    update: function () {
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }

        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (res) {
            alert("회원수정이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });

    },

}


index.init();