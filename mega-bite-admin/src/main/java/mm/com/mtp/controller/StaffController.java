package mm.com.mtp.controller;

import mm.com.mtp.payload.StaffRequest;
import mm.com.mtp.payload.StaffResponse;
import mm.com.mtp.service.StaffService;
import mm.com.mtp.type.StaffRole;
import mm.com.mtp.utils.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Sai Pann Hmwe on 6/3/2021.
 */
@Controller
public class StaffController {

    @Autowired
    StaffService staffService;

    @GetMapping("/office-staffs")
    public String getAllStaff(Model model) {
        List<StaffResponse> staffs = staffService.getOfficeStaff();
        model.addAttribute("staffs", staffs);
        return PageUrl.OFFICE_ACCOUNT;
    }

    @GetMapping("/office-staffs/{id}/detail")
    public String getStaffDetail(Model model, @PathVariable("id") Long id) {
        model.addAttribute("staff", staffService.getStaffDetail(id));
        return PageUrl.VIEW_OFFICE_ACCOUNT;
    }

    @GetMapping("/office-staffs/{id}/edit")
    public String editStaff(Model model, @PathVariable("id") Long id) {
        //model.addAttribute("departments", departmentService.getDepartment());
        model.addAttribute("staff", staffService.getStaffDetail(id));
        return PageUrl.EDIT_OFFICE_ACCOUNT;
    }

    @PostMapping("/office-staffs/update")
    public String updateStaff(@Valid @ModelAttribute("staffRequest") StaffRequest staffRequest,
                              Model model,
                              BindingResult result) {
        validateFieldErrors(staffService.validateStaffEditRequest(staffRequest), result);

        if (result.hasErrors()) {
            model.addAttribute("staffStatus", Arrays.asList("Admin", "Staff", "Delivery Man"));
            //model.addAttribute("departments", departmentService.getDepartment());
            model.addAttribute("staffRequest", staffRequest);
            return PageUrl.EDIT_OFFICE_ACCOUNT;
        }

        staffService.updateStaff(staffRequest.getId(), staffRequest);
        return "redirect:/office-staffs";
    }

    @GetMapping("/office-staffs/{staffId}/reset")
    public String resetStaffPassword(@PathVariable Long staffId) {
        staffService.resetPassword(staffId);
        return "redirect:/office-staffs";
    }

    @GetMapping("/office-staffs/{staffId}/ban")
    public String changeStaffStatus(@PathVariable Long staffId) {
        staffService.changeActiveStatus(staffId);
        return "redirect:/office-staffs";
    }

    @GetMapping("/office-staffs/{staffId}/activate")
    public String activateStaff(@PathVariable Long staffId) {
        staffService.changeInActiveStatus(staffId);
        return "redirect:/office-staffs";
    }

    /********************************** Delivery Men ********************************/

    @GetMapping("/delivery-men")
    public String getAllDeliveryMen(Model model) {
        List<StaffResponse> delivery = staffService.getStaffByRole(StaffRole.DELIVERY_MAN);
        model.addAttribute("delivery", delivery);
        return PageUrl.DELIVERY_ACCOUNT;
    }

    @GetMapping("/delivery-men/{staffId}/ban")
    public String changeDeliveryStatus(@PathVariable Long staffId) {
        staffService.changeActiveStatus(staffId);
        return "redirect:/delivery-men";
    }

    @GetMapping("/delivery-men/{id}/detail")
    public String getDeliveryManDetail(Model model, @PathVariable("id") Long id) {
        model.addAttribute("staff", staffService.getStaffDetail(id));
        return PageUrl.VIEW_DELIVERY_ACCOUNT;
    }

    @GetMapping("/delivery-men/{staffId}/reset")
    public String resetDeliveryManPassword(@PathVariable Long staffId) {
        staffService.resetPassword(staffId);
        return "redirect:/delivery-men";
    }

    @GetMapping("/delivery-men/{id}/edit")
    public String editDelivery(Model model, @PathVariable("id") Long id) {
        //model.addAttribute("departments", departmentService.getDepartment());
        model.addAttribute("staff", staffService.getStaffDetail(id));
        return PageUrl.EDIT_DELIVERY_ACCOUNT;
    }

    @PostMapping("/delivery-men/update")
    public String updateDelivery(@Valid @ModelAttribute("staffRequest") StaffRequest staffRequest,
                                 Model model,
                                 BindingResult result) {
        validateFieldErrors(staffService.validateStaffEditRequest(staffRequest), result);

        if (result.hasErrors()) {
            model.addAttribute("staffStatus", Arrays.asList("Admin", "Staff", "Delivery Man"));
            //model.addAttribute("departments", departmentService.getDepartment());
            model.addAttribute("staffRequest", staffRequest);
            return PageUrl.EDIT_DELIVERY_ACCOUNT;
        }

        staffService.updateStaff(staffRequest.getId(), staffRequest);
        return "redirect:/delivery-men";
    }

    @GetMapping("/delivery-men/{staffId}/activate")
    public String activateDeliveryMan(@PathVariable Long staffId) {
        staffService.changeInActiveStatus(staffId);
        return "redirect:/delivery-men";
    }

    private void validateFieldErrors(Map<String, String> fieldErrors, BindingResult result) {
        for (Map.Entry<String, String> entry : fieldErrors.entrySet()) {
            switch (entry.getKey()) {
                case "phoneNumber":
                    result.rejectValue("phoneNumber", "error.staffRequest", entry.getValue());
                    break;
                case "userName":
                    result.rejectValue("userName", "error.staffRequest", entry.getValue());
                    break;
                /*case "email":
                    result.rejectValue("email", "error.userForm", entry.getValue());
                    break;*/
            }
        }
    }


}
