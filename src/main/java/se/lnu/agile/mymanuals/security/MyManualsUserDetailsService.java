package se.lnu.agile.mymanuals.security;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.model.Representative;

@Service
public class MyManualsUserDetailsService implements UserDetailsService {

	private final RepresentativeDao representativeDao;

	@Autowired
	public MyManualsUserDetailsService(RepresentativeDao userRepository) {
		this.representativeDao = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Representative representative = representativeDao.findByEmail(username);
		if (representative == null) {
			throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
		}
		return new RepresentativeDetails(representative);
	}

	private final static class RepresentativeDetails extends Representative implements UserDetails {

		private static final long serialVersionUID = 1L;

		private RepresentativeDetails(Representative representative) {
			super(representative);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}

		@Override
		public String getUsername() {
			return getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

	}

}