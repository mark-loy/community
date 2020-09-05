function commentSubmit() {
    let content = $('#comment-content').val()
    let parentId = $('#comment-parentId').val()
    let type = 2 //2.问题回复 1.评论回复
    $.ajax({
        type: 'post',
        url: '/comment',
        contentType: 'application/json;charset=UTF-8',
        dataType: 'json',
        data: JSON.stringify({
            "parentId": parentId,
            "type": type,
            "content": content,
        }),
        success: function (result) {
            if (result.code === 200) {
                //回复成功
                //$('#comment-content').hide()
                $('#comment-content').val('')
            } else {
                //回复失败
                if (result.code === 2001) {
                    //用户未登录
                    let confirm = window.confirm(result.result);
                    if (confirm) {
                        window.open("https://github.com/login/oauth/authorize?client_id=Iv1.608c3ae1ace91189&scope=user&state=1")
                        window.localStorage.setItem("closable", "true");
                    }
                } else {
                    //其它异常
                    alert(result.result)
                }
            }
        },
    })
}