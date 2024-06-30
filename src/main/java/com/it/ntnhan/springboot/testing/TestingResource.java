package com.it.ntnhan.springboot.testing;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestingResource {


    //    Only users with 'ROLE_ADMIN' role can access this end point'
    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Secured("ROLE_ADMIN")
//    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<?> AdminDashboard() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("accessed !");
    }


    @GetMapping("/user")
    @Secured("ROLE_USER")
    public ResponseEntity<?> userRole() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("accessed !");
    }

}
