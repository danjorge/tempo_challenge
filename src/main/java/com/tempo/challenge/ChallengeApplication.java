package com.tempo.challenge;

import com.tempo.challenge.service.RoleService;
import com.tempo.challenge.model.Role;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class ChallengeApplication implements CommandLineRunner{

	private final RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);
	}

	@Override
	public void run(String... args) {
		List<Role> teamRoles = roleService.getAllTeamRoles();

		if (CollectionUtils.isEmpty(teamRoles)) {
			List<String> roles = List.of("Developer", "Product Owner", "Tester");

			roles.forEach(r -> {
				Role tr = new Role();
				tr.setDescription(r);
				teamRoles.add(tr);
			});

			roleService.save(teamRoles);
		}
	}
}
