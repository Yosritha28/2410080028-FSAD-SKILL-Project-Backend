/*package com.example.demo.controller;

public class AuthController {

}*/



/*package com.example.demo.controller;

import com.example.demo.dto.AdminLoginRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserLoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/user/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody UserLoginRequest request) {
        if (request.getUsername() == null || request.getUsername().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, "Username/password required", "user", null));
        }

        AuthResponse response = new AuthResponse(
                true,
                "User login successful",
                "user",
                request.getUsername()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<AuthResponse> loginAdmin(@RequestBody AdminLoginRequest request) {
        if (request.getAdminId() == null || request.getAdminId().isBlank()
                || request.getUsername() == null || request.getUsername().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()
                || request.getAdminCode() == null || request.getAdminCode().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, "All admin fields are required", "admin", null));
        }

        // simple example admin code check
        if (!"ADMIN123".equals(request.getAdminCode())) {
            return ResponseEntity.status(401)
                    .body(new AuthResponse(false, "Invalid admin code", "admin", null));
        }

        AuthResponse response = new AuthResponse(
                true,
                "Admin login successful",
                "admin",
                request.getUsername()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest request) {
        if (request.getFullName() == null || request.getFullName().isBlank()
                || request.getAge() == null
                || request.getGender() == null || request.getGender().isBlank()
                || request.getHeight() == null
                || request.getWeight() == null
                || request.getEmail() == null || request.getEmail().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, "Missing required fields", "user", null));
        }

        AuthResponse response = new AuthResponse(
                true,
                "User registered successfully",
                "user",
                request.getFullName()
        );
        return ResponseEntity.ok(response);
    }
}*/



/*package com.example.demo.controller;

import com.example.demo.dto.AdminLoginRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserLoginRequest;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/user/login")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody UserLoginRequest request) {
        if (request.getUsername() == null || request.getUsername().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, "Username/password required", "user", null));
        }

        AuthResponse response = authService.loginUser(request);
        if (!response.isSuccess()) {
            // 400 for now; you can change to 401 later
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/admin/login")
    public ResponseEntity<AuthResponse> loginAdmin(@RequestBody AdminLoginRequest request) {
        if (request.getAdminId() == null || request.getAdminId().isBlank()
                || request.getUsername() == null || request.getUsername().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()
                || request.getAdminCode() == null || request.getAdminCode().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, "All admin fields are required", "admin", null));
        }

        if (!"ADMIN123".equals(request.getAdminCode())) {
            return ResponseEntity.status(401)
                    .body(new AuthResponse(false, "Invalid admin code", "admin", null));
        }

        AuthResponse response = new AuthResponse(
                true,
                "Admin login successful",
                "admin",
                request.getUsername()
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody RegisterRequest request) {
        if (request.getFullName() == null || request.getFullName().isBlank()
                || request.getAge() == null
                || request.getGender() == null || request.getGender().isBlank()
                || request.getHeight() == null
                || request.getWeight() == null
                || request.getEmail() == null || request.getEmail().isBlank()
                || request.getPassword() == null || request.getPassword().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponse(false, "Missing required fields", "user", null));
        }

        AuthResponse response = authService.register(request);
        if (!response.isSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}*/


/*package com.example.nutridietbackend1.controller;

import com.example.nutridietbackend1.dto.AuthResponse;
import com.example.nutridietbackend1.dto.LoginRequest;
import com.example.nutridietbackend1.dto.RegisterRequest;
import com.example.nutridietbackend1.entity.User;
import com.example.nutridietbackend1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        AuthResponse response = new AuthResponse();

        if (request.getEmail() == null || request.getPassword() == null || request.getFullName() == null) {
            response.setSuccess(false);
            response.setMessage("Missing required fields");
            response.setRole("user");
            response.setDisplayName(null);
            return response;
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            response.setSuccess(false);
            response.setMessage("Email already registered");
            response.setRole("user");
            response.setDisplayName(null);
            return response;
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setAge(request.getAge());
        user.setGender(request.getGender());
        user.setHeight(request.getHeight());
        user.setWeight(request.getWeight());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setMobile(request.getMobile());

        userRepository.save(user);

        response.setSuccess(true);
        response.setMessage("User registered successfully");
        response.setRole("user");
        response.setDisplayName(user.getFullName());
        return response;
    }

    @PostMapping("/user/login")
    public AuthResponse userLogin(@RequestBody LoginRequest request) {
        AuthResponse response = new AuthResponse();

        if (request.getUsername() == null || request.getPassword() == null) {
            response.setSuccess(false);
            response.setMessage("Missing credentials");
            response.setRole("user");
            response.setDisplayName(null);
            return response;
        }

        User user = userRepository.findByEmail(request.getUsername()).orElse(null);
        if (user == null) {
            response.setSuccess(false);
            response.setMessage("User not found");
            response.setRole("user");
            response.setDisplayName(null);
            return response;
        }

        if (!user.getPassword().equals(request.getPassword())) {
            response.setSuccess(false);
            response.setMessage("Invalid credentials");
            response.setRole("user");
            response.setDisplayName(null);
            return response;
        }

        response.setSuccess(true);
        response.setMessage("User login successful");
        response.setRole("user");
        response.setDisplayName(user.getFullName());
        return response;
    }

    @PostMapping("/admin/login")
    public AuthResponse adminLogin(@RequestBody LoginRequest request) {
        AuthResponse response = new AuthResponse();

        if ("adminuser".equals(request.getUsername())
                && "adminpass".equals(request.getPassword())
                && "ADMIN123".equals(request.getAdminCode())) {

            response.setSuccess(true);
            response.setMessage("Admin login successful");
            response.setRole("admin");
            response.setDisplayName("adminuser");
        } else {
            response.setSuccess(false);
            response.setMessage("Invalid admin credentials or code");
            response.setRole("admin");
            response.setDisplayName(null);
        }
        return response;
    }
}*/



/*package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/user/login")
    public AuthResponse userLogin(@RequestBody UserLoginRequest request) {
        return authService.userLogin(request);
    }

    @PostMapping("/admin/login")
    public AuthResponse adminLogin(@RequestBody AdminLoginRequest request) {
        return authService.adminLogin(request);
    }
}*/



package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/user/login")
    public AuthResponse userLogin(@RequestBody UserLoginRequest request) {
        return authService.userLogin(request);
    }

    @PostMapping("/admin/login")
    public AuthResponse adminLogin(@RequestBody AdminLoginRequest request) {
        return authService.adminLogin(request);
    }
}