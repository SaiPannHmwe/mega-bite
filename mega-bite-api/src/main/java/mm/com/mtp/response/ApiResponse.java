package mm.com.mtp.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {

	@Builder.Default
	private String status = ApiStatus.SUCCESS.toString();

	private String message;

	private T data;

}
