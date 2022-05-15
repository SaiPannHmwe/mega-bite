package mm.com.mtp.type;

import com.fasterxml.jackson.annotation.JsonValue;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.AllArgsConstructor;

/**
 * Created by Sai Pann Hmwe on 1/31/2021.
 */
@AllArgsConstructor
public enum OrderStatus {

    PENDING("Pending"),

    DELIVERED("Delivered"),

    COMPLETED("Completed"),

    ISSUED("Issued");

    private String status;

    @Getter
    @JsonValue
    public String getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.status;
    }

}
