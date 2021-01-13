package tamalecloud.api.entity;

import lombok.Data;

@Data
public class TSEntry {
	private String id;
	private String name;
	private TSEntity entity;
}
