package ks46team02.dry.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dry")
public class DryController {

    @GetMapping("/contract_info")
    public String getContractInfo(Model model){
        model.addAttribute("title","계약상세정보");

        return "contract/contract_info";
    }

    @GetMapping("/contract_list")
    public String getContractList(Model model){
        model.addAttribute("title","계약관리");

        return "contract/contract_list";
    }


}
