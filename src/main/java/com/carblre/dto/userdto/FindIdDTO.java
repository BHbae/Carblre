package com.carblre.dto.userdto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FindIdDTO {
    private String email;
    private String nickName;
    private String userName;
}
