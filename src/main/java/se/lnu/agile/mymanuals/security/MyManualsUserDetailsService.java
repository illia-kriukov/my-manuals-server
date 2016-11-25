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
import se.lnu.agile.mymanuals.dao.ConsumerDao;
import se.lnu.agile.mymanuals.dao.RepresentativeDao;
import se.lnu.agile.mymanuals.model.Consumer;
import se.lnu.agile.mymanuals.model.Representative;

@Service
public class MyManualsUserDetailsService implements UserDetailsService {

	private final ConsumerDao consumerDao;

	private final RepresentativeDao representativeDao;

	@Autowired
	public MyManualsUserDetailsService(ConsumerDao consumerDao, RepresentativeDao representativeDao) {
		this.consumerDao = consumerDao;
		this.representativeDao = representativeDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Consumer consumer = consumerDao.findByEmail(username);
		if (consumer != null) {
			return new ConsumerDetails(consumer);
		} else {
			Representative representative = representativeDao.findByEmail(username);
			if (representative != null) {
				return new RepresentativeDetails(representative);
			} else {
				throw new UsernameNotFoundException(String.format("User %s does not exist!", username));
			}
		}
	}

	private final static class ConsumerDetails extends Consumer implements UserDetails {

		private static final long serialVersionUID = 1L;

		private ConsumerDetails(Consumer consumer) {
			super(consumer);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
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