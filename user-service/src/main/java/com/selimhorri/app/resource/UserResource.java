package com.marketplace.platform.user.web.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketplace.platform.user.core.dto.PlatformUserDto;
import com.marketplace.platform.user.core.dto.response.ApiCollectionResponse;
import com.marketplace.platform.user.core.service.PlatformUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = {"/api/v1/platform-users"})
@Slf4j
@RequiredArgsConstructor
public class PlatformUserController {
	
	private final PlatformUserService platformUserService;
	
	@GetMapping
	public ResponseEntity<ApiCollectionResponse<PlatformUserDto>> findAll() {
		log.info("*** PlatformUserDto List, controller; fetch all platform users *");
		return ResponseEntity.ok(ApiCollectionResponse.<PlatformUserDto>builder().collection(this.platformUserService.findAll()).build());
	}
	
	@GetMapping("/{platformUserId}")
	public ResponseEntity<PlatformUserDto> findById(
			@PathVariable("platformUserId") 
			@NotBlank(message = "Platform user ID cannot be blank") 
			@Valid final String platformUserId) {
		log.info("*** PlatformUserDto, controller; fetch platform user by ID *");
		return ResponseEntity.ok(this.platformUserService.findById(Integer.parseInt(platformUserId.strip())));
	}
	
	@PostMapping
	public ResponseEntity<PlatformUserDto> save(
			@RequestBody 
			@NotNull(message = "Platform user data cannot be null") 
			@Valid final PlatformUserDto platformUserDto) {
		log.info("*** PlatformUserDto, controller; create new platform user *");
		return ResponseEntity.ok(this.platformUserService.save(platformUserDto));
	}
	
	@PutMapping
	public ResponseEntity<PlatformUserDto> update(
			@RequestBody 
			@NotNull(message = "Platform user data cannot be null") 
			@Valid final PlatformUserDto platformUserDto) {
		log.info("*** PlatformUserDto, controller; update platform user *");
		return ResponseEntity.ok(this.platformUserService.update(platformUserDto));
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> update(
			@PathVariable("userId") 
			@NotBlank(message = "Input must not blank") final String userId, 
			@RequestBody 
			@NotNull(message = "Input must not NULL") 
			@Valid final UserDto userDto) {
		log.info("*** UserDto, resource; update user with userId *");
		return ResponseEntity.ok(this.userService.update(Integer.parseInt(userId.strip()), userDto));
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("userId") @NotBlank(message = "Input must not blank") @Valid final String userId) {
		log.info("*** Boolean, resource; delete user by id *");
		this.userService.deleteById(Integer.parseInt(userId));
		return ResponseEntity.ok(true);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<UserDto> findByUsername(
			@PathVariable("username") 
			@NotBlank(message = "Input must not blank") 
			@Valid final String username) {
		return ResponseEntity.ok(this.userService.findByUsername(username));
	}
	
	
	
}










