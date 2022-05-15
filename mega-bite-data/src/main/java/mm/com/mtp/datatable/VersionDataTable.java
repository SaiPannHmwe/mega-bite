package mm.com.mtp.datatable;

import lombok.Data;
import mm.com.mtp.type.AppPlatform;

/**
 * Created by Sai Pann Hmwe on 10/24/2021.
 */
@Data
public class VersionDataTable {

    private Long id;

    private String code;

    private String name;

    private String description;

    private AppPlatform platform;

}
