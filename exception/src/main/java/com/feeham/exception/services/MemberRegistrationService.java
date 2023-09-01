package com.feeham.exception.services;

import com.feeham.exception.entity.Member;
import com.feeham.exception.exceptions.DuplicateEntityException;
import com.feeham.exception.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberRegistrationService {
    private static List<Member> members = new ArrayList<>();
    public Member find(long id) throws EntityNotFoundException {
        for(Member member: members){
            if(member.getId() == id){
                return member;
            }
        }
        throw new EntityNotFoundException();
    }

    public Member add(Member newMember) throws DuplicateEntityException{
        for(Member member: members){
            if(member.getId() == newMember.getId()){
                throw new DuplicateEntityException();
            }
        }
        members.add(newMember);
        return newMember;
    }
}
