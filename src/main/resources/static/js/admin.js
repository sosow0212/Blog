console.log("radio-value")



let index = {
    init: function () {

        $("#btn-update").on("click", () => {
            this.update();
        });

    },


    update: function () {
        let data = {
            id: $("#id").val(),
            role: $(".radio-value:checked").val(),
        }

        $.ajax({
            type: "PUT",
            url: "/#",
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