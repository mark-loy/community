/*通用的评论请求*/
function commonSubmit(parentId, type, content) {
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
                //刷新当前页面
                window.location.reload()
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

/*一级评论回复提交*/
function commentSubmit() {
    let content = $('#comment-content').val()
    let parentId = $('#comment-parentId').val()
    let type = 2 //2.问题回复
    if (content === "") {
        alert("评论内容不能为空");
        return
    }
    commonSubmit(parentId, type, content)
}

/*二级评论回复提交*/
function subCommentSubmit(id) {
    let content = $('#sub-comment-content-' + id).val()
    const type = 1 //1.评论回复
    if (content === "") {
        alert("评论内容不能为空")
        return
    }
    commonSubmit(id, type, content)
}

/*二级评论展开查询*/
function subComment(id) {
    //属性自动添加和移除
    let toggleClass = $('#collapse' + id).toggleClass('in');
    if (toggleClass[0].className.search('in') > 0) {
        //查询评论的回复列表
        $.get("/comment/" + id, function (data) {
                if (data.code === 200) {
                    let item = []
                    $.each(data.data, function (i, comment) {
                        item.push('<div class="media">' +
                            '<div class="media-left">' +
                            '<a href="#"><img class="media-object img-rounded" src=' + comment.user.avatarUrl + '>' +
                            '</a>' +
                            '</div>' +
                            '<div class="media-body">' +
                            '<h5 class="media-heading">' +
                            '<span>' + comment.user.loginName + '</span>' +
                            '</h5>' +
                            '<p class="comment-answer">' + comment.content + '</p>' +
                            '<p class="comment-menu">' +
                            '<span class="glyphicon glyphicon-hand-right icon "></span>' +
                            '<span class="comment-date">' + timestampToTime(comment.gmtCreate) + '</span>' +
                            '</div>' +
                            '</div>' +
                            '</div>')
                    })
                    $('#comment-list' + id).html(item)
                    //二级评论展开状态
                    $('#' + id).addClass('active')
                } else {
                    alert(data.result)
                }
            }
        );
    } else {
        $('#' + id).removeClass('active')
    }
}

/*显示标签层*/
function showTag(id) {
    const isActive = $("li[id^='tag-title-']").hasClass("active")
    if (!isActive) {
        $('#tag-title-'+id).addClass("active")
    }
    $('#label-tag').show()
}

/*切换一级标签*/
function tabParentTag(id) {
    const tagId = id.split("-")[2]
    $.get("/tabTag/"+tagId, function (data) {
        let tagContent = []
        if (data.code === 200) {
            //请求成功
            $.each(data.data.childTag, function (index, tag) {
                tagContent.push(
                    '<span class="publish-tag-span">' +
                    '<span onclick="selectedTag(this.id)" id='+tag.id+' ' +
                    'class="label label-info label-item">' +
                    '<span class="glyphicon glyphicon-tags"></span>' +
                    '<span id="tag-content-'+tag.id+'" class="label-content">'+tag.tagName+'</span>' +
                    '</span>' +
                    '</span>'
                )
            })
            $('#publish-tag-p').html(tagContent)
            /*移除之前选择的标签样式*/
            $("li[id^='tag-title-']").removeClass("active")
            /*给当前选择的标签添加样式*/
            $('#tag-title-'+tagId).addClass("active")
        }
    })

}

/*获取标签值
* 获取span标签值使用text()方法
* */
function selectedTag(id) {
    let tag = $('#tag-content-'+id).text()
    let inputTag = $('#tag').val();
    if (inputTag !== "") {
        tag = inputTag + "," + tag
    }
    $('#tag').val(tag)
}

/*
* 点击标签区域之外的元素，标签区域隐藏
* */
$(document).bind("click",function(e){
    var target  = $(e.target);
    if(target.closest("#label-tag,#tag").length === 0){
        $("#label-tag").hide();
    }
})





