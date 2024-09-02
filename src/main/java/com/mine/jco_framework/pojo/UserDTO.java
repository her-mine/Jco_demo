package com.mine.jco_framework.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

/**
 * @author lynn
 * @date 2024/8/29/13:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Length(min = 3)
    @NotEmpty(message = "姓名不能为空")
    private String name;
    @Length(min = 10, message = "密码长度至少为10位")
    private String password;
    private Integer age;
    private String sex;
}
