package az.spring.rest.dmo.springrestdemo.rest.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private long id;

    @NotBlank(message = "Name may not be empty")
    private String name;

    @NotBlank(message = "Name may not be empty")
    private String surname;

    private int age;
    private double salary;
}
