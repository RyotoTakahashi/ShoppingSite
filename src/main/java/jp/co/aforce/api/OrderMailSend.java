package jp.co.aforce.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderMailSend {
	public void sendOrderConfirmationMail(String to, String orderNumber, String orderToken,
            String paymentMethod, int totalAmount) throws Exception {

String subject = String.format("【ECサイト】ご注文ありがとうございます（注文番号: %s）", orderNumber);

String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年M月d日 HH:mm"));

String body = String.format(
"""
このたびは、当ECサイトにてご注文いただき誠にありがとうございます。
以下の内容でご注文を承りました。

―――――――――――――――――――――
■ご注文内容
注文番号　　：%s
ご注文日時　：%s
ご注文金額　：¥%,d（税込）
お支払方法　：%s
注文確認URL：https://shoppingsiteryototakahashi.f5.si/ShoppingSite/views/orderhistory?token=%s
―――――――――――――――――――――

本メールはご注文完了時に自動配信されたものです。

※お心当たりのない方は、お手数ですが当店までご連絡ください。
※ゲスト購入の場合は、本メールを紛失されますと注文確認ができなくなりますのでご注意ください。

─────────────────────
ECサイト
https://example.com
support@example.com
─────────────────────
""",
orderNumber,
formattedDateTime,
totalAmount,
paymentMethod,
orderToken
);

GmailApiUtil.sendEmail(to, subject, body);
}
}
