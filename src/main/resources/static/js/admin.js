let index = {
    init: function () {

        $("#btn-update").on("click", () => {
            this.update();
        });

    },


    update: function () {
        let data = {
            role: $(".radio-value:checked").val(),
        };
        let id = $("#id").val();


        console.log(data)
        console.log(id)

        $.ajax({
            type: "PUT",
            url: "/admin/manage/member/edit/" + id,
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