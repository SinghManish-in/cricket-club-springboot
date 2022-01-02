package com.chipura.cricket.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chipura.cricket.entity.Player;
import com.chipura.cricket.exception.ResourceNotFoundException;
import com.chipura.cricket.repository.PlayerRepository;


@RestController
@RequestMapping("/api/users")
public class PlayerController {

	@Autowired
	private PlayerRepository playerRepository;

	// get all users
	@GetMapping
	public List<Player> getAllUsers() {
		return this.playerRepository.findAll();
	}

	// get user by id
	@GetMapping("/{id}")
	public Player getUserById(@PathVariable (value = "id") long userId) {
		return this.playerRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
	}

	// create user
	@PostMapping
	public Player createUser(@RequestBody Player player) {
		return this.playerRepository.save(player);
	}
	
	// update user
	@PutMapping("/{id}")
	public Player updateUser(@RequestBody Player player, @PathVariable ("id") long playerId) {
		Player existingPlayer = this.playerRepository.findById(playerId)
			.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + playerId));
		 existingPlayer.setFirstName(player.getFirstName());
		 existingPlayer.setLastName(player.getLastName());
		 existingPlayer.setEmail(player.getEmail());
		 return this.playerRepository.save(existingPlayer);
	}
	
	// delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<Player> deleteUser(@PathVariable ("id") long userId){
		Player existingUser = this.playerRepository.findById(userId)
					.orElseThrow(() -> new ResourceNotFoundException("User not found with id :" + userId));
		 this.playerRepository.delete(existingUser);
		 return ResponseEntity.ok().build();
	}
}