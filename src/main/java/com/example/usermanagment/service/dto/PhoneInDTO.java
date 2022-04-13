package com.example.usermanagment.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data

public class PhoneInDTO {

    @ApiModelProperty(position = 6, required = true, example = "1234567")
    @NotNull(message = "NUmber can not be empty")
    private String number;
}
