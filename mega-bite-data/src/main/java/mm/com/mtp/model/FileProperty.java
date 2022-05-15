package mm.com.mtp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.MappedSuperclass;

/**
 * Created by Sai Pann Hmwe on 1/4/2021.
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = false)
public class FileProperty extends DateAudit {

    private String name;

    private String type;

    private String path;

    private Long size;

}
