package uz.pdp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WorkerDTO {


    private String name;
    private String phoneNumber;

    private String city;
    private String street;
    private String home;

    private Integer departmentId;
}
