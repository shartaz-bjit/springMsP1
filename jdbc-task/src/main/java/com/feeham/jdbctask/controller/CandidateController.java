package com.feeham.jdbctask.controller;

import com.feeham.jdbctask.model.Candidate;
import com.feeham.jdbctask.repository.CandidateRepository;
import com.feeham.jdbctask.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/candidate/all")
    public ResponseEntity<?> getAllCandidate() {
        List<Candidate> candidateList = candidateService.getAll();
        if(candidateList.isEmpty()){
            return new ResponseEntity<>("No candidates recorded.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(candidateList, HttpStatus.OK);
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<?> getCandidateById(@PathVariable Integer id) {
        try{
            Optional<Candidate> candidateNullable = candidateService.getById(id);
            if(candidateNullable.isPresent()){
                Candidate candidate = candidateNullable.get();
                return new ResponseEntity<>(candidate, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("Could not find any item with id "+id, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            return new ResponseEntity<>("Could not find any item with id "+id, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/candidate")
    public ResponseEntity<String> addCandidate(@RequestBody Candidate candidate) {
        String response = "Could not add item. Possible reason: Duplicate entry.";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        try{
            boolean added = candidateService.add(candidate);
            if (added) {
                response = "Addition successful.";
                status = HttpStatus.OK;
            }
        }
        catch (Exception e){}
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("/candidate")
    public ResponseEntity<String> deleteCandidate(@RequestParam Integer id) {
        String response = "Could not delete item. Possible reason: Item not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        try{
            boolean deleted = candidateService.delete(id);
            if (deleted) {
                response = "Item deleted.";
                status = HttpStatus.OK;
            }
        }
        catch (Exception e){}
        return new ResponseEntity<>(response, status);
    }

    @PutMapping("/candidate/{id}")
    public ResponseEntity<String> updateCandidate(@PathVariable Integer id, @RequestBody Candidate candidate) {
        String response = "Could not update item. Possible reason: Item not found.";
        HttpStatus status = HttpStatus.NOT_FOUND;
        try{
            boolean updated = candidateService.update(id, candidate);
            if (updated) {
                response = "Item updated successfully.";
                status = HttpStatus.OK;
            }
        }
        catch (Exception e){}
        return new ResponseEntity<>(response, status);
    }
}
