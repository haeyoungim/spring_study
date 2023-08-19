package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
어노테이션을 쓰면 스프링이 configuration을 읽고 bean 등록을 하게 됨
*/

@Configuration
public class SpringConfig {
/*
    memberservice와 memberrepository를 @bean을 보고 스프링빈에 올려줌
    memberservice는 memberrepository를 사용하기 때문에 memberservice에서 repository 호출
    controller는 스프링이 관리하기 때문에 autowired로 등록
*/


    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
