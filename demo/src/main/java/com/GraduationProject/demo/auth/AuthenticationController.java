package com.GraduationProject.demo.auth;

import com.GraduationProject.demo.service.AuthenticationService;
import com.GraduationProject.demo.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;
    private final PasswordResetService passwordResetService;

    @PostMapping("register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request)) ;
    }
    @PostMapping("authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request)) ;
    }
    @PostMapping("forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        passwordResetService.generateResetCode(email);
        return ResponseEntity.ok("Password reset link sent to your email");
    }
    @PostMapping("reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {

        String newPassword = request.get("newPassword");
        String newPasswordConfirm = request.get("newPasswordConfirm");

        passwordResetService.resetPassword( newPasswordConfirm,newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }

    @PostMapping("Verify")
    public ResponseEntity<String> VerifyCode(@RequestBody Map<String, String> request) {
        String code = request.get("code");

        passwordResetService.verifyCode(code);
        return ResponseEntity.ok("verified successfully");
    }
    @PostMapping("Resend")
    public ResponseEntity<String> ResendCode() {

        passwordResetService.resendCode();
        return ResponseEntity.ok("Code was sent successfully");
    }
}
