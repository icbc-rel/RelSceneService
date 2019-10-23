<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <script src='https://cdn.opapp.cn/icbc/icbc_public_benefit/js/fitremtaobao.js' type="text/javascript"></script>
    <link rel="stylesheet" href="../css/fescard.css">
    <script src="../js/jquery-3.2.1.min.js"></script>
    <title>c端</title>

</head>

<body>
<!-- TODO::这里的信息由后端动态生成 -->
 <div class="mask">

    <div class="mycard-ui">
        <div class="container-box">
            <c:if test="${empty url}">
                <img alt="" src="../image/touxiang.jpg">
            </c:if>
            <c:if test="${not empty url}">
                <img alt="" src="${url}">
            </c:if>
            <div class="staff-name">Hi,${taskInfo.cusName}:</div>
            <div class="wish-text">
                ${card.wish}
            </div>
            <div class="byname-text">
                ${card.writeName}
            </div>
            <div class="send-time"></div>
        </div>
    </div>
</div>
<div class="save-pic">长按上图保存</div>
<img  id="pic" src="${card.bg}">

<script src='https://cdn.opapp.cn/icbc/icbc_public_benefit/js/html2canvas.js' type="text/javascript"></script>
<script>
    // imgUrl由后端传过来
    $('.mycard-ui').css('background-image', "url( ${card.bg} )")
    /*$('body').css('background-image', "url( ${card.bg})")*/
    // canvas生成图片
    html2canvas($('.mycard-ui')[0], {
        backgroundColor: 'transparent',
        useCORS: true
    }).then((canvas) => {
        // console.log(canvas)
        let dataURL = canvas.toDataURL("image/png")
        self.dataURL = dataURL
    document.getElementById('pic').src = dataURL

    })
    // $('.save-pic').click(e=>{
    //     $('.prompt').fadeIn().delay(1500).fadeOut();
    // })
    // 时间戳
    var year = new Date().getFullYear()
    var month = new Date().getMonth() + 1
    var date = new Date().getDate()
    var sendPre = year + "年" + month + "月" + date + "日";
    $('.send-time').html(year + "年" + month + "月" + date + "日")
    /*function formatDatetime(value) {
        return new Date(value.createTime).format('yyyy-MM-dd hh:mm:ss');
    }*/

</script>
</body>

</html>
