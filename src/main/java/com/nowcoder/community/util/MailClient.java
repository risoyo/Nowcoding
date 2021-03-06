package com.nowcoder.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailClient {

    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);
    private final JavaMailSender mailSender;
    //    指定发送人，值为配置文件中的spring.mail.username项
    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    //    传入三个变量，收件人、标题、内容
    public void sendMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(helper.getMimeMessage());
          } catch (MessagingException e) {
            logger.error("发送邮件失败:" + e.getMessage());
            throw new BizException(NowcodingErrCode.VERIFY_CODE_SEND_FAIL.respCode(),NowcodingErrCode.VERIFY_CODE_SEND_FAIL.respMessage());
        }
    }

}
