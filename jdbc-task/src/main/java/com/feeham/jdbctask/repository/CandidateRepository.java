package com.feeham.jdbctask.repository;

import com.feeham.jdbctask.model.Candidate;

import java.util.List;
import java.util.Optional;

public interface CandidateRepository {

    Optional<Candidate> getById(Integer id);
    List<Candidate> getAll();
    boolean add(Candidate candidate);
    boolean delete(Integer id);
    boolean update(Integer id, Candidate candidate);
}
