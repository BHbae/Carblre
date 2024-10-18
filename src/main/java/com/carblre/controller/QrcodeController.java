package com.carblre.controller;

import com.carblre.service.QrcodeService;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qr")
public class QrcodeController {

    private final QrcodeService qrcodeService;


    @GetMapping("/login")
    public ResponseEntity<byte[]> loginQr() throws IOException, WriterException {

        return  qrcodeService.CreateLoginQRcode();
    }
}
