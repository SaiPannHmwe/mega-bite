package mm.com.mtp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn({"springContext"})
public class I18NTranslator {

    private static MessageSource i18nMessageSource;

    public static String get(String key) {
        if (i18nMessageSource != null)
            return i18nMessageSource.getMessage(key, null, LocaleContextHolder.getLocale());
        return key;
    }

    @PostConstruct
    public void initialize() {
        i18nMessageSource = SpringContext.getBean("messageSource", MessageSource.class);
    }
}