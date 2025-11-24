package my.myPetClinic.controllers;

import my.myPetClinic.model.Owner;
import my.myPetClinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    //InitBinder Позволяет настроить процесс привязки данных, что очень полезно для форматирования и преобразования входных данных
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }


//    @RequestMapping({"","/","/index","/index.html"})
//    public String listOwners(Model model){
//        model.addAttribute("owners", ownerService.findAll());
//        return "owners/index";
//    }

   @RequestMapping("/find")
    public String findOwners(Model model){
       Owner owner = new Owner();
        model.addAttribute("owner", owner);
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if (owner.getLastName() == null){
            owner.setLastName("");
        }
//создаем список владельцев-однофамильцев
        List<Owner> listOwners = ownerService.findByLastNameLike(owner.getLastName());
//если совпадений нет
        if (listOwners.isEmpty()){
            result.rejectValue("lastName","notFound","notFound");
            return "owners/findOwners";
        } else if (listOwners.size() == 1) {
          //если одно совпадение
            owner = listOwners.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            //если совпадений несколько
            model.addAttribute("selections",listOwners);
            return "owners/ownersList";
        }
    }

}
