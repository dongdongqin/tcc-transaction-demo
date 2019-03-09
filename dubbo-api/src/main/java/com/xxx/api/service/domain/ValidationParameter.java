package com.xxx.api.service.domain;

import com.xxx.api.service.ValidationService;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ValidationParameter
 * @Description Validation Parameter
 * @Author e079726
 * @Date 2019-02-14 14:04
 * @Version
 * @since JDK 1.8
 **/
@Data
public class ValidationParameter implements Serializable {
    private static final long serialVersionUID = 7158911668568000392L;

    @NotNull
    @Size(min = 1, max = 20)
    private String name;

    @NotNull(groups = ValidationService.Save.class) // It is not allowed to be blank when saving. When it is updated, it is allowed to be blank, indicating that the field is not updated
    @Pattern(regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")
    private String email;

    /** min value**/
    @Min(18)
    /**  max value**/
    @Max(100)
    private int age;

    /** Must be a past time **/
    @Past
    private Date loginDate;

    /** Must be a future time **/
    @Future
    private Date expiryDate;

}
