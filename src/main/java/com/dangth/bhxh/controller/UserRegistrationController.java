package com.dangth.bhxh.controller;

import com.dangth.bhxh.dto.DTOUtils;
import com.dangth.bhxh.dto.WorkerDTO;
import com.dangth.bhxh.model.Worker;
import com.dangth.bhxh.repository.CommuneRepository;
import com.dangth.bhxh.repository.HamletRepository;
import com.dangth.bhxh.repository.ProvinceRepository;
import com.dangth.bhxh.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private final WorkerRepository workerRepository;

    @Autowired
    public UserRegistrationController(WorkerRepository workerRepository, ProvinceRepository provinceRepository, HamletRepository hamletRepository, CommuneRepository communeRepository) {
        this.workerRepository = workerRepository;
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("worker", new WorkerDTO());
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("worker") WorkerDTO workerDto){

        Worker worker = workerRepository.findByIdentityCard_Number(workerDto.getIdentityNumber());
        if (worker != null) {
            return "redirect:/registration?alreadyRegistered";
        };
        System.out.println(workerDto);
        worker = DTOUtils.toWorker(workerDto, true);
        System.out.println(worker);
        if (worker != null) {
            workerRepository.save(worker);
            return "redirect:/registration?success";
        }
        return "redirect:/registration?fail";
    }
}
