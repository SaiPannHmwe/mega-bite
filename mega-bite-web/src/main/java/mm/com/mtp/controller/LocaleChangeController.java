package mm.com.mtp.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/**
 * Created by Set Myat Phyoe on 12/6/2020.
 */
@Controller
public class LocaleChangeController {

    @GetMapping("/changeLocale")
    public String changeLocale(Model model, @RequestParam String localeCode, HttpServletRequest request) throws MalformedURLException {
        Locale locale = new Locale(localeCode);
        LocaleForm localeForm = new LocaleForm();
        localeForm.setLocaleCode(locale.toString());
        model.addAttribute("localeForm", localeForm);

        String previousUrl = request.getHeader("referer");
        URL url = new URL(previousUrl);
        String path = url.getPath();

        return "redirect:" + path;
    }

    @Data
    public static class LocaleForm {
        private String localeCode;
    }

}
