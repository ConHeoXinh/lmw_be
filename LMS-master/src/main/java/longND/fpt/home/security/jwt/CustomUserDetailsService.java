package longND.fpt.home.security.jwt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import longND.fpt.home.data.modal.Role;
import longND.fpt.home.data.modal.User;
import longND.fpt.home.data.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User not found with username " + username));

		Set<Role> roles = new HashSet<>();
		roles.add(user.getRoles());
		List<GrantedAuthority> authorities = roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());

		return UserDetailsImpl.build(user);
	}

}
