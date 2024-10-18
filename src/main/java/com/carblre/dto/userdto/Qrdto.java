package com.carblre.dto.userdto;

import lombok.*;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Qrdto {

   Integer id;
   Integer userId;
   Timestamp expirationTime;
}
