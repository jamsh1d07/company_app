package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.entity.Address;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyDTO {


    private String name;
    private String directorName;
    private String city;
    private String street;
    private String home;

}
