package uz.pdp.controller;

import org.hibernate.persister.entity.SingleTableEntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dto.WorkerDTO;
import uz.pdp.entity.Address;
import uz.pdp.entity.Department;
import uz.pdp.entity.Worker;
import uz.pdp.repository.DepartmentRepository;
import uz.pdp.repository.WorkerRepository;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Worker> workerList = workerRepository.findAll();
        return ResponseEntity.ok().body(workerList);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        Worker worker = optionalWorker.get();
        return ResponseEntity.ok().body(worker);
    }

    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody WorkerDTO workerDTO){
        Worker worker=new Worker();
        worker.setName(workerDTO.getName());
        Address address=new Address();
        address.setCity(workerDTO.getCity());
        address.setStreet(workerDTO.getStreet());
        address.setHome(workerDTO.getHome());
        worker.setAddress(address);
        worker.setPhoneNumber(workerDTO.getPhoneNumber());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDTO.getDepartmentId());
        Department department = optionalDepartment.get();
        worker.setDepartment(department);
        Worker save = workerRepository.save(worker);
        return ResponseEntity.status(HttpStatus.CREATED).body("Save"+save);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id,@Valid @RequestBody WorkerDTO workerDTO){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        Worker worker = optionalWorker.get();
        worker.setName(workerDTO.getName());
        Address address = worker.getAddress();
        address.setCity(workerDTO.getCity());
        address.setStreet(workerDTO.getStreet());
        address.setHome(workerDTO.getHome());
        worker.setAddress(address);
        worker.setPhoneNumber(workerDTO.getPhoneNumber());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDTO.getDepartmentId());
        Department department = optionalDepartment.get();
        worker.setDepartment(department);
        Worker save = workerRepository.save(worker);
        return ResponseEntity.status(HttpStatus.OK).body("Edit"+save);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        Worker worker = optionalWorker.get();
        workerRepository.delete(worker);
        return ResponseEntity.ok().body(worker);
    }
}
