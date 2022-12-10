package id.co.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostCommentRequest {

	private String content;
	private String status;
	private String author;
	private String email;
	private String url;
	private Long blogId;
}
