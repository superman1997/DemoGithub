package com.dangth.bhxh.controller;

import com.dangth.bhxh.dto.DTOUtils;
import com.dangth.bhxh.dto.WorkerDTO;
import com.dangth.bhxh.model.Worker;
import com.dangth.bhxh.model.location.Commune;
import com.dangth.bhxh.model.location.Hamlet;
import com.dangth.bhxh.model.location.Province;
import com.dangth.bhxh.repository.CommuneRepository;
import com.dangth.bhxh.repository.HamletRepository;
import com.dangth.bhxh.repository.ProvinceRepository;
import com.dangth.bhxh.repository.WorkerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    private static Logger logger = LogManager.getLogger(HomeController.class);
    private final WorkerRepository workerRepository;
    private final ProvinceRepository provinceRepository;
    private final CommuneRepository communeRepository;
    private final HamletRepository hamletRepository;

    public HomeController(WorkerRepository workerRepository, ProvinceRepository provinceRepository, CommuneRepository communeRepository, HamletRepository hamletRepository) {
        this.workerRepository = workerRepository;
        this.provinceRepository = provinceRepository;
        this.communeRepository = communeRepository;
        this.hamletRepository = hamletRepository;
    }

    @GetMapping({"/"})
    public String index() {
        return "home";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping({"/qlinfo"})
    public String qlInfo(Model model) {
        model.addAttribute("worker", new WorkerDTO());
        return "qlinfo";
    }

    @GetMapping({"/calculatefee"})
    public String calculateFee() {
        return "bhxhfee";
    }

    @GetMapping({"/qlinfo/{id}"})
    public String qlinfoPage(@PathVariable String id, Model model) {
        Worker worker = workerRepository.findByMsbh(id);
        if (worker == null){
            worker = workerRepository.findByIdentityCard_Number(id);
            if (worker == null) {
                return "redirect:/qlinfo?notFound";
            }
        }
        List<Province> provinceList = provinceRepository.findAllByIdcity(worker.getAddress().getTinh());
        List<Commune> communeList = communeRepository.findAllByIdprovince(worker.getAddress().getHuyen());
        List<Hamlet> hamletList = hamletRepository.findAllByIdcommune(worker.getAddress().getPhuong());
        System.out.println(worker);
        model.addAttribute("worker", DTOUtils.toWorkerDTO(worker));
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("communeList", communeList);
        model.addAttribute("hamletList", hamletList);
        return "qlinfo";
    }

    @PostMapping("/qlinfo")
    public String updateQlinfo(@ModelAttribute("worker") WorkerDTO workerDto) {
        System.out.println(workerDto);
        Worker worker = workerRepository.findByIdentityCard_NumberAndIdentityCard_IdNot(workerDto.getIdentityNumber(), workerDto.getIdentityId());
        System.out.println(worker);
        if (worker == null) {
            workerRepository.save(DTOUtils.toWorker(workerDto, false));
            return "redirect:/qlinfo?success";
        }
        else {
            return "redirect:/qlinfo?identityExisted";
        }
    }

    @GetMapping({"/403"})
    public String accessDenied() {
        return "403";
    }

}
