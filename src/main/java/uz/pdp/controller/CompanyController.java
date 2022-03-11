package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.CompanyAppApplication;
import uz.pdp.dto.CompanyDTO;
import uz.pdp.entity.Address;
import uz.pdp.entity.Company;
import uz.pdp.repository.CompanyRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyRepository companyRepository;

    @GetMapping
    public HttpEntity<?> getAll() {
        List<Company> companyList = companyRepository.findAll();
        return ResponseEntity.ok().body(companyList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        Company company = companyOptional.get();
        return ResponseEntity.ok().body(company);
    }

    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody CompanyDTO companyDTO){
        Company company=new Company();
        company.setName(companyDTO.getName());
        company.setDirectorName(companyDTO.getDirectorName());
        Address address=new Address();
        address.setCity(companyDTO.getCity());
        address.setStreet(companyDTO.getStreet());
        address.setHome(companyDTO.getHome());
        company.setAddress(address);
        Company save = companyRepository.save(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody CompanyDTO companyDTO){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        Company company = optionalCompany.get();
        company.setName(companyDTO.getName());
        company.setDirectorName(companyDTO.getDirectorName());
        Address address = company.getAddress();
        address.setCity(companyDTO.getCity());
        address.setStreet(companyDTO.getStreet());
        address.setHome(companyDTO.getHome());
        company.setAddress(address);
        Company save = companyRepository.save(company);
        return ResponseEntity.ok().body(save);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        Optional<Company> optionalCompany = companyRepository.findById(id);
        Company company = optionalCompany.get();
        companyRepository.delete(company);
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

}
