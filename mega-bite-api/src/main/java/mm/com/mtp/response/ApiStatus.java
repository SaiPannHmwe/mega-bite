package mm.com.mtp.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enumeration of API response status codes.
 * Created by Sai Pann Hmwe on 1/6/2021.
 */
@AllArgsConstructor
public enum ApiStatus {

    SUCCESS("success"),

    FAIL("fail"),

    ERROR("error");

    @Getter
    private String status;

    @Override
    public String toString() {
        return this.status;
    }

}
