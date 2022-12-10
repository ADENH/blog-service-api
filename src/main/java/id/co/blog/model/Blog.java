package id.co.blog.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	@Column(columnDefinition = "text")
	private String content;
	private String tags;
	private String status;
	
	@Column(updatable = false)
	private Date createDate = new Date();
	private Date updateDate;
	private Long viewed;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "users", nullable = false)
	private Users users;
}
