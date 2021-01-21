// js 파일이 여러 개일 때 함수 이름이 중복될 수 있음
// 따라서 index 라는 객체의 속성으로 main 을 만들고 function 을 선언함
// index 객체 안에서만 function 이 유효하므로 다른 js 파일과 겹칠 일이 없음

let main = {
    init : function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        })
        $('#btn-update').on('click', function () {
            _this.update();
        })
        $('#btn-delete').on('click', function () {
            _this.delete();
        })
    },
    save : function () {
        let data = {
            title: $('#title').val(),
            content: $('#content').val(),
            author: $('#author').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        let id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=UTF-8'
        }).done(function () {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();