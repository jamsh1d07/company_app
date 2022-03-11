package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.DepartmentDTO;
import uz.pdp.entity.Company;
import uz.pdp.entity.Department;
import uz.pdp.repository.CompanyRepository;
import uz.pdp.repository.DepartmentRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Department> departmentList = departmentRepository.findAll();
        return ResponseEntity.ok().body(departmentList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department department = optionalDepartment.get();
        return ResponseEntity.ok().body(department);
    }

    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody DepartmentDTO departmentDTO){
        Department department=new Department();
        department.setName(departmentDTO.getName());
        Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());
        Company company = optionalCompany.get();
        department.setCompany(company);
        Department save = departmentRepository.save(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id ,@Valid @RequestBody DepartmentDTO departmentDTO){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department department = optionalDepartment.get();
        department.setName(departmentDTO.getName());
        Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());
        Company company = optionalCompany.get();
        department.setCompany(company);
        Department save = departmentRepository.save(department);
        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        Department department = optionalDepartment.get();
        departmentRepository.delete(department);
        return ResponseEntity.ok().body("Delete"+department);
    }
}
