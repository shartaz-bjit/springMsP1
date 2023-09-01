package com.feeham.jdbctask.repository;


import com.feeham.jdbctask.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CandidateRepositoryImpl implements CandidateRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Candidate> getById(Integer id) throws EmptyResultDataAccessException {
        String query = "select * from candidate where id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, rowNum) ->
                Optional.of(
                        new Candidate(
                                resultSet.getInt("id"),
                                resultSet.getString("name")
                        )
                )
        );
    }

    @Override
    public List<Candidate> getAll() {
        String query = "select * from candidate";
        return jdbcTemplate
                .query(query, (resultSet, rowNum) ->
                        new Candidate(
                                resultSet.getInt("id"),
                                resultSet.getString("name")
                        )
                );
    }

    @Override
    public boolean add(Candidate candidate) throws  RuntimeException{
        String query = "insert into candidate (id, name) values (?, ?)";
        int rowsUpdated = jdbcTemplate.update(query, candidate.getId(), candidate.getName());
        if(rowsUpdated < 1) throw new RuntimeException();
        else return true;
    }

    @Override
    public boolean delete(Integer id) throws RuntimeException{
        String query = "delete from candidate where id = ?";
        int rowsUpdated = jdbcTemplate.update(query, id);
        if(rowsUpdated < 1) throw new RuntimeException();
        else return true;
    }

    @Override
    public boolean update(Integer id, Candidate candidate) throws RuntimeException {
        String query = "update candidate set name = ? where id = ?";
        int rowsUpdated = jdbcTemplate.update(query, candidate.getName(), id);
        if(rowsUpdated < 1) throw new RuntimeException();
        else return true;
    }

}
