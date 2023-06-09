package com.aza.dto.admin.user;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.aza.domain.admin.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDto {
	
	@NotBlank(message = "아이디는 필수 입력값입니다.")
	private String userId;
	@NotBlank(message = "비밀헌호는 필수 입력값입니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
     message = "영문 숫자 특수기호 조합 8자리 이상입니다.")
	private String userPwd;
	@NotBlank(message = "이름은 필수 입력값입니다.")
	private String userName;
	private String userGender;
	@NotBlank(message = "전화번호는 필수 입력값입니다.")
	private String userPhoneNo;
	@NotNull(message = "생년월일은 필수 입력값입니다.")
	private Date userBirth;
	@NotBlank(message = "이메일은 필수 입력값입니다.")
	private String userEmail;
	
	public User toDomain(PasswordEncoder passwordEncoder) {
		String encryptedPassword = passwordEncoder.encode(userPwd);
		return User.builder()
				.userId(userId)
				.userPwd(encryptedPassword)
				.userName(userName)
				.userGender(userGender)
				.userPhoneNo(userPhoneNo)
				.userBirth(userBirth)
				.userEmail(userEmail)
				.build();
	}
	public static UserDto create(final User user) {
		return builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.userGender(user.getUserGender())
				.userPhoneNo(user.getUserPhoneNo())
				.userBirth(user.getUserBirth())
				.userEmail(user.getUserEmail())
				.build();

	}
	
}
