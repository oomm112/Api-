package com.example.demo.data.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
	
	@JsonIgnore
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Column(name = "username", length = 50, unique = true)
	private String username;
	
	@JsonIgnore
	@Column(name = "password", length = 100)
	private String password;
	
	@Column(name = "nickname", length = 50)
	private String nickname;
	
	@JsonIgnore
	@Column(name = "activated")
	private boolean activated;
	
	//아래 두 어노테이션은 User객체와 권한객체의 테이블간의 다대다 관계를 일대다, 다대일 관계로 조인하였다는 뜻이다.
	@ManyToMany
	@JoinTable(
			name = "user_authority",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
	private Set<Authority> authorities;
}
