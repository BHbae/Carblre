package com.carblre.service;

import com.carblre.repository.interfaces.QrcodeRepository;
import com.carblre.repository.model.Qrcode;
import com.carblre.repository.model.User;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class QrcodeService {

    private  final QrcodeRepository qrcodeRepository;

    public  boolean isValid(String token) {

        return qrcodeRepository.existsByToken(token);
    }

    public ResponseEntity<byte[]> CreateLoginQRcode() throws WriterException, IOException{
        int width = 300;
        int height = 300;
        String token = UUID.randomUUID().toString(); // 고유한 인증 토큰 생성
        String temptoken = "b8a09074-b644-4fc3-94da-f81cb8e255ff";

        String url = "http://192.168.0.13:8080/user/signIn/token=" + temptoken;
        // QR Code - BitMatrix: qr code 정보 생성
        BitMatrix encode = new MultiFormatWriter()
                .encode(url, BarcodeFormat.QR_CODE, width, height);

        // QR Code - Image 생성. : 1회성으로 생성해야 하기 때문에
        // stream으로 Generate(1회성이 아니면 File로 작성 가능.)
        try {
            //output Stream
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            //Bitmatrix, file.format, outputStream
            MatrixToImageWriter.writeToStream(encode, "PNG", out);

//            qrcodeRepository.saveQrcode(id,token);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(out.toByteArray());

        }catch (Exception e){log.warn("QR Code OutputStream 도중 Excpetion 발생, {}", e.getMessage());}

        return null;
    }



    public Qrcode generateTokenForUser(User user, String tokenNum) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusMinutes(3);

        return Qrcode.builder()
                .userId(user.getId())
                .token(tokenNum)
                .expirationTime(Timestamp.valueOf(expirationTime))
                .build();
    }

    public boolean isTokenValid(String token) {
        Qrcode storedToken = qrcodeRepository.findByToken(token);

        if (storedToken == null) {
            return false; // 토큰이 존재하지 않음
        }

        Timestamp expirationTime = storedToken.getExpirationTime();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        // 현재 시간이 만료 시간 이후라면 false 반환
        return currentTime.before(expirationTime);
      }
}
