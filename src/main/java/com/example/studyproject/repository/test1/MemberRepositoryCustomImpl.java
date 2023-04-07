package com.example.studyproject.repository.test1;

import com.example.studyproject.entity.test1.Member;
import com.example.studyproject.entity.test1.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Autowired(required = false)
    public MemberRepositoryCustomImpl( @Qualifier("test1EntityManager") EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Member> getMembers() {
        QMember member

        return new ArrayList<>();
    }

}
