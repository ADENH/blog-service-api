package id.co.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostBlogRequest {

	private String title;
	private String content;
	private String tags;
	private String status;
	private String authorId;
}
