package com.carblre.service;

import com.carblre.dto.PrincipalDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    /**
     * 이메일을 보내는 메소드
     *
     * @param to      = 수신자
     * @param subject = 제목
     * @param text    = 내용
     */
    public void sendMail(String to, String subject, String templateName, String text) {
        try {
            // Thymeleaf 문법
            // Controller 에서 받은 인증코드를,
            // Context 클래스를 이용해서
            // templates/sendValidateCode.html 로 보냅니다.
            Context context = new Context();
            context.setVariable("validationCode", text);

            String htmlFile = templateEngine.process(templateName, context);

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            // 메일 수신자
            mimeMessageHelper.setTo(to);
            // 메일 제목
            mimeMessageHelper.setSubject(subject);
            // 본문에 첨부할 html 파일, true 는 html 허용 유무를 나타냄
            mimeMessageHelper.setText(htmlFile, true);

            javaMailSender.send(mimeMessage);
            System.out.println("Sent email to " + to);
        } catch (MessagingException e) {
            throw new RuntimeException("Email을 보내는데에 오류가 생겼습니다.", e);
        }
    }

    /**
     * 아이디 찾기 이메일 발송
     * @param to
     * @param subject
     * @param dto
     */
    public void findIdByEmail(String to, String subject, PrincipalDTO dto) {
        try {
            // Thymeleaf 문법
            // Context 클래스를 이용하여 템플릿에 필요한 데이터를 설정합니다.
            Context context = new Context();

            // 템플릿 처리(이메일 본문)
            String htmlFile = templateEngine.process("findUserIdByEmail", context);
            // context: 템플릿에서 사용할 데이터 모델

            // MimeMessage 객체 생성
            // 이메일 메시지의 본문과 메타데이터를 포함하는 클래스
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            // MimeMessageHelper 객체 생성
            // MimeMessage 객체를 쉽게 다룰 수 있게 도와주는 클래스
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "UTF-8");
            // 파일 첨부 여부: false

            // 수신자의 메일 주소
            mimeMessageHelper.setTo(to);
            // 메일 제목
            mimeMessageHelper.setSubject(subject);
            // 본문에 첨부할 html 파일, true 는 html 허용 유무를 나타냄
            mimeMessageHelper.setText(htmlFile, true);

            // 이메일 전송
            javaMailSender.send(mimeMessage);
            System.out.println("Sent email to " + to);
        } catch (MessagingException e) {
            throw new RuntimeException("Email을 보내는데에 오류가 생겼습니다.", e);
        }

    }
}
