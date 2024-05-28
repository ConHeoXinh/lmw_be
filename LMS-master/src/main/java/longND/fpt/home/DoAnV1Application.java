package longND.fpt.home;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import longND.fpt.home.data.modal.Role;
import longND.fpt.home.data.repository.RoleRepository;

@SpringBootApplication
@ComponentScan(basePackages = "longND.fpt")
@EnableScheduling
public class DoAnV1Application implements CommandLineRunner {
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(DoAnV1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Role> role = new ArrayList<>();
		role.add(new Role("ROLE_ADMIN"));
		role.add(new Role("ROLE_EMPLOYEE"));
		role.add(new Role("ROLE_USER"));
		role.add(new Role("ROLE_GUEST"));

		role.forEach(item -> {
			if (!roleRepository.existsByName(item.getName())) {
				roleRepository.save(item);
			}
		});
	}

}
