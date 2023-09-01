package com.feeham.exception.controllers;
import com.feeham.exception.entity.Member;
import com.feeham.exception.exceptions.DuplicateEntityException;
import com.feeham.exception.exceptions.EntityNotFoundException;
import com.feeham.exception.services.MemberRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberRegistrationController {
    @Autowired
    MemberRegistrationService memberRegistrationService;
    @PostMapping("/add")
    public ResponseEntity<?> addMember(@RequestBody Member member) throws DuplicateEntityException {
        return new ResponseEntity<>(memberRegistrationService.add(member), HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity<?> getMember(@RequestParam long id) throws EntityNotFoundException {
        return new ResponseEntity<>(memberRegistrationService.find(id), HttpStatus.OK);
    }
}
