package mm.com.mtp.type;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Sai Pann Hmwe on 10/10/2021.
 */
@AllArgsConstructor
public enum AppPlatform {

    ANDROID("Android"),

    IOS("iOS");

    @Getter
    @JsonValue
    private String platform;

    @Override
    public String toString() {
        return this.platform;
    }

}
