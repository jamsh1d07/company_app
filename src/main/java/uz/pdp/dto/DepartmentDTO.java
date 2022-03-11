package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.entity.Company;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDTO {



    private String name;
    private Integer companyId;
}
