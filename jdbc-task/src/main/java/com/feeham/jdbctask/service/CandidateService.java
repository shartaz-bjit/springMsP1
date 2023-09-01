package com.feeham.jdbctask.service;

import com.feeham.jdbctask.model.Candidate;
import com.feeham.jdbctask.repository.CandidateRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    @Autowired
    CandidateRepositoryImpl candidatesRepo;

    public Optional<Candidate> getById(Integer id) throws EmptyResultDataAccessException {
        return candidatesRepo.getById(id);
    }
    public List<Candidate> getAll() {
        return candidatesRepo.getAll();
    }
    public boolean add(Candidate candidate) throws  RuntimeException{
        return candidatesRepo.add(candidate);
    }
    public boolean delete(Integer id) throws RuntimeException{
        return candidatesRepo.delete(id);
    }
    public boolean update(Integer id, Candidate candidate) throws RuntimeException {
        return candidatesRepo.update(id, candidate);
    }
}
