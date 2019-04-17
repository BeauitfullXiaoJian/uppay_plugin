function pay() {
    window['UPPayPlugin'] && window['UPPayPlugin'].call(
        // 调用方法
        'pay',
        // 支付参数
        ['787410383246521353801'],
        // 消息通知回调方法
        (res) => {
            alert(JSON.stringify(res));
            if (res.payResult === 'success') {
                alert('支付成功，验证参数' + res.resultData);
            } else {
                alert('支付失败');
            }
            // fail:交易失败 cancle:交易取消
        }
    );
}