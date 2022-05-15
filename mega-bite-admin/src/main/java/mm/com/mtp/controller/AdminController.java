package mm.com.mtp.controller;

import lombok.extern.slf4j.Slf4j;
import mm.com.mtp.payload.StaffRequest;
import mm.com.mtp.security.CurrentUser;
import mm.com.mtp.security.UserPrincipal;
import mm.com.mtp.service.StaffService;
import mm.com.mtp.utils.PageUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    StaffService staffService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/create-account")
    public String CreateAccount(@ModelAttribute("staffRequest") StaffRequest staffRequest, Model model) {
        //model.addAttribute("staffStatus", Arrays.asList("Admin", "Staff", "Delivery Man"));
        //model.addAttribute("departments", departmentService.getDepartment());
        //model.addAttribute("successMessage", "Staff is created successfully.");
        return PageUrl.ADMIN;
    }

    @PostMapping("/save-account")
    public String saveAccount(@Valid @ModelAttribute("staffRequest") StaffRequest staffRequest,
                              BindingResult result,
                              Model model) {
        //System.out.print(staffRequest.get());
        //boolean isValid = true;
        validateFieldErrors(staffService.validateStaffRequest(staffRequest), result);

        if (result.hasErrors()) {
            model.addAttribute("staffStatus", Arrays.asList("Admin", "Staff", "Delivery Man"));
            //model.addAttribute("departments", departmentService.getDepartment());
            model.addAttribute("staffRequest", staffRequest);
            return PageUrl.ADMIN;
        }

        staffService.registerStaff(staffRequest);
        model.addAttribute("successStaff", true);
        //model.addAttribute("successMessage", "Staff is created successfully.");
        //return "redirect:/create-account";
        return PageUrl.ADMIN;
    }

    @GetMapping("/change-password")
    public String changePassword(@CurrentUser UserPrincipal userPrincipal,
                                 Model model) {
        if (userPrincipal.getRole().toString().equals("Staff")) {
            model.addAttribute("staff", staffService.getStaffDetail(userPrincipal.getId()));
            return PageUrl.STAFF_CHANGE_PASSWORD;
        }
        return PageUrl.CHANGE_PASSWORD;
    }

    @PostMapping("/reset-password")
    public String resetPassword(@CurrentUser UserPrincipal userPrincipal,
                                @RequestParam String currentPassword,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                Model model) {
        System.out.println("Name :" + userPrincipal.getName() + " " + userPrincipal.getAuthorities().toString());

        String password = userPrincipal.getPassword();
        Long id = userPrincipal.getId();

        boolean isValid = true;
        if (currentPassword != null && !currentPassword.isEmpty()) {
            if (!passwordEncoder.matches(currentPassword, password)) {
                isValid = false;
                model.addAttribute("checkCurrentPassword", "Current password doesn't not match.");
            }
        }

        if (newPassword != null && !newPassword.isEmpty() && confirmPassword != null && !confirmPassword.isEmpty()) {
            if (!newPassword.equals(confirmPassword)) {
                isValid = false;
                model.addAttribute("checkPassword", "Password doesn't not match");
            }
        }

        if (isValid) {
            staffService.changePassword(id, newPassword);
            model.addAttribute("successPassword", isValid);
        }

        model.addAttribute("currentPassword", currentPassword);
        model.addAttribute("newPassword", newPassword);
        model.addAttribute("confirmPassword", confirmPassword);

        if (userPrincipal.getRole().toString().equals("Staff")) {
            model.addAttribute("staff", staffService.getStaffDetail(userPrincipal.getId()));
            return PageUrl.STAFF_CHANGE_PASSWORD;
        }

        return PageUrl.CHANGE_PASSWORD;
    }


    @GetMapping("/edit-account")
    String EditAccount() {
        return PageUrl.EDIT_ACCOUNT;
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