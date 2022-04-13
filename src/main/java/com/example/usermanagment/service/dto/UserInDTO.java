package com.example.usermanagment.service.dto;

import com.example.usermanagment.service.dto.validation.ValidPassword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@ApiModel(description = "Class representing a user entrance")
@Value
public class UserInDTO {
    @ApiModelProperty(position = 1, required = true, example = "Juan Rodriguez")
    @Size(min = 1, max = 255) @NotNull @NotEmpty(message = "name can not be empty") String name;

    @ApiModelProperty(position = 2, required = true, example = "juan@rodriguez.com")
    @Email(message = "Not a valid email") @NotEmpty(message = "email can not be empty") String email;

    @ApiModelProperty(position = 3, required = true, example = "Hunter22")
    @NotNull(message = "Password can not be null")
    @NotEmpty(message = "Password can not be empty")
    @ValidPassword
    String password;

    @ApiModelProperty(position = 4, required = true, example = "1")
    @NotNull(message = "City Code can not be empty") @NotEmpty(message = "cityCode can not be empty") String cityCode;

    @ApiModelProperty(position = 5, required = true, example = "57")
    @NotNull(message = "City Code can not be empty") @Size(min = 2, max = 2, message = "Not  a valid countryCode") @NotEmpty(message = "countryCode can not be empty") String countryCode;

    @ApiModelProperty(position = 6)
    Set<PhoneInDTO> phones = new HashSet<>();
}
