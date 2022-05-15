package mm.com.mtp.payload;

import lombok.Data;

/**
 * Created by Sai Pann Hmwe on 1/31/2021.
 */
@Data
public class ImageResponse {

    private Long id;

    private String name;

    private String type;

    private Long size;

    private String path;

}
