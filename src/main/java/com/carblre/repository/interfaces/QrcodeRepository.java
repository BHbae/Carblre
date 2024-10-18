package com.carblre.repository.interfaces;

import com.carblre.repository.model.Qrcode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface QrcodeRepository {

    Qrcode saveQrcode(@Param("userId")int userId,@Param("token") String token) ;

    Qrcode findByToken(String token);

    boolean existsByToken(String token);
}
