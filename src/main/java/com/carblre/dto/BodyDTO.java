package com.carblre.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

/**
 * 법규위반별 사고다발지역 API
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BodyDTO {

    @JsonProperty("pageNo")
    private int pageNo;
    @JsonProperty("totalCount")
    private int totalCount;
    private ItemsDTO items;

    @JsonProperty("numOfRows")
    private int numOfRows;

}
