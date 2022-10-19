package az.spring.rest.dmo.springrestdemo.rest.model.response;
import az.spring.rest.dmo.springrestdemo.rest.model.dto.EmployeeDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {
    private List<EmployeeDto> employees;
}
