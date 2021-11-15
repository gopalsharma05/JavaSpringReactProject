package com.example.SpringReactProjectTool.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.SpringReactProjectTool.domain.User;
import com.example.SpringReactProjectTool.payload.JWTLoginSuccessResponse;
import com.example.SpringReactProjectTool.payload.LoginRequest;
import com.example.SpringReactProjectTool.security.JwtTokenProvider;
import com.example.SpringReactProjectTool.services.MapValidationErrorService;
import com.example.SpringReactProjectTool.services.UserService;
import com.example.SpringReactProjectTool.validator.UserValidator;

import static com.example.SpringReactProjectTool.security.SecurityConstants.TOKEN_PREFIX;

@Controller
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	MapValidationErrorService mapValidationErrorService;

	@Autowired
	UserService userService;

	@Autowired
	UserValidator userValidator;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
	}

	@PostMapping("/register")
	public ResponseEntity<?> saveUser(@Valid @RequestBody User newUser, BindingResult result) {
		userValidator.validate(newUser, result);

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null) {
			return errorMap;
		}

		User user = userService.saveUser(newUser);
		return new ResponseEntity<User>(user, HttpStatus.CREATED);

	}

}
