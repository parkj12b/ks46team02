package ks46team02.common.email;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
 
@Service
@PropertySource("classpath:email.properties")
public class EmailServiceImpl implements EmailService{
 
    @Autowired
    JavaMailSender emailSender;
 
    private String ePw = createKey();
    @Value("${AdminMail.email}")
    String email;
    
    private MimeMessage createMessage(String to)throws Exception{
        ePw = createKey();
    	System.out.println("보내는 대상 : "+ to);
        System.out.println("인증 번호 : "+ePw);
        MimeMessage  message = emailSender.createMimeMessage();
 
        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("이메일 인증 테스트");//제목
 
        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 인증번호 </h1>";
        msgg+= "<h2>" + ePw + "</h2>";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        
        message.setFrom(new InternetAddress(email,"minseoPark"));//보내는 사람
 
        return message;
    }
 
    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();
 
        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤
 
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }
    @Override
    public String sendSimpleMessage(String to)throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}