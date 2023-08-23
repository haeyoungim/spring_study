package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;



public class JpaMemberRepository implements MemberRepository{

    //jpa는 entityManager로 동작을 한다
    //jpa를 쓰려면 EntityManager를 주입 받아야한다.
    //PK기반이 아닌 다중건은 jpql을 작성 해야됨
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        //persist : 영속하다 , 영구 저장하다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        //조회 할 타입 & 식별자 PK 값

        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                //jpl쿼리 객체를 대상으로 쿼리를 날림 m
                .getResultList();
    }
}
