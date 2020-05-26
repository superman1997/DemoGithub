package com.dangth.bhxh.controller;

import com.dangth.bhxh.dto.DTOUtils;
import com.dangth.bhxh.dto.WorkerDTO;
import com.dangth.bhxh.model.Worker;
import com.dangth.bhxh.repository.WorkerRepository;
import com.dangth.bhxh.utils.Calculator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/worker")
public class ManagementController {

    private final WorkerRepository workerRepository;

    public ManagementController(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @GetMapping("/info/{number}")
    public WorkerDTO getInfo(@PathVariable String number) {
        Worker worker = workerRepository.findByIdentityCard_Number(number);
        if (worker == null) {
            System.out.println("info by msbh");
            worker = workerRepository.findByMsbh(number);
        }

        return worker != null ? DTOUtils.toWorkerDTO(worker, String.format("%.0f", Calculator.calculate(worker))) : null;
    }
    @GetMapping("/info")
    public Worker getInfoByMSBH(@RequestParam("msbh") String msbh) {
        return workerRepository.findByMsbh(msbh);
    }
    @GetMapping("/calculator")
    public Double calculatorFee(@RequestParam("ms") String ms) {
        Worker w = workerRepository.findByMsbh(ms);
        if (w == null) w = workerRepository.findByIdentityCard_Number(ms);
        return (w.getSalary()+w.getPc()) * 0.08;
    }
}
