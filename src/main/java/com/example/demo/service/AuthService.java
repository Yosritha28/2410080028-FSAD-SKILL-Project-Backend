/*package com.example.demo.service;

public class AuthService {

}*/



/*package com.example.demo.service;

public class AuthService {
    // later: methods like registerUser(), loginUser()
}*/



/*package com.example.demo.service;

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.dto.UserLoginRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(false, "Email already registered", "user", null);
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setAge(request.getAge());
        user.setGender(request.getGender());
        user.setHeight(request.getHeight());
        user.setWeight(request.getWeight());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // later: hash
        user.setMobile(request.getMobile());

        userRepository.save(user);

        return new AuthResponse(true, "User registered successfully", "user", user.getFullName());
    }

    public AuthResponse loginUser(UserLoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getUsername());

        if (userOpt.isEmpty()) {
            return new AuthResponse(false, "User not found", "user", null);
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(request.getPassword())) {
            return new AuthResponse(false, "Invalid credentials", "user", null);
        }

        return new AuthResponse(true, "User login successful", "user", user.getFullName());
    }
}*/



/*package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public AuthResponse register(RegisterRequest request) {
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

    public AuthResponse userLogin(UserLoginRequest request) {
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

    public AuthResponse adminLogin(AdminLoginRequest request) {
        AuthResponse response = new AuthResponse();

        if ("ADM001".equals(request.getAdminId())
                && "adminuser".equals(request.getUsername())
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



package com.example.demo.service;

import com.example.demo.dto.*;
import com.example.demo.model.User;
import com.example.demo.model.Admin;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    public AuthResponse register(RegisterRequest request) {
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

    public AuthResponse userLogin(UserLoginRequest request) {
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

    public AuthResponse adminLogin(AdminLoginRequest request) {
        AuthResponse response = new AuthResponse();

        if (request.getAdminId() == null || request.getUsername() == null
                || request.getPassword() == null || request.getAdminCode() == null) {
            response.setSuccess(false);
            response.setMessage("Missing admin credentials");
            response.setRole("admin");
            response.setDisplayName(null);
            return response;
        }

        Admin admin = adminRepository
                .findByAdminIdAndUsername(request.getAdminId(), request.getUsername())
                .orElse(null);

        if (admin == null) {
            response.setSuccess(false);
            response.setMessage("Admin not found");
            response.setRole("admin");
            response.setDisplayName(null);
            return response;
        }

        if (!admin.getPassword().equals(request.getPassword())
                || !admin.getAdminCode().equals(request.getAdminCode())) {
            response.setSuccess(false);
            response.setMessage("Invalid admin credentials or code");
            response.setRole("admin");
            response.setDisplayName(null);
            return response;
        }

        response.setSuccess(true);
        response.setMessage("Admin login successful");
        response.setRole("admin");
        response.setDisplayName(admin.getUsername());
        return response;
    }
}