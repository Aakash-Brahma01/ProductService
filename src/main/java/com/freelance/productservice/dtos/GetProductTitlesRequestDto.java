package com.freelance.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetProductTitlesRequestDto {
    List<String> uuid;
}
