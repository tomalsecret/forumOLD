import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class PasswordEncoderGenerator {

  public static String void Generate(String pass) {

	int i = 0;
	String hashedPassword = "";
	while (i < 10) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		hashedPassword = passwordEncoder.encode(pass);
		i++;
	}
	return hashedPassword;
  }
}