package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    
    private final MemberRepository memberRepository;

    //new로 생성하는게 아니라 외부에서 넣어주도록 ( memberServiceTest )
    //이것을 DI라 함
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /*
    회원가입
    
    ifPresent : 어떤 값이 있으면 로직이 동작
    Optional 때문에 가능
    과거에는 if null... 
    null일 가능성이 있으면 optional로 한번 감싸서 반환
    단축키 커맨드+옵션+v
         컨트롤+T
    
     */
    public Long join(Member member) {

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
        
    }

    //커맨드+옵션+M
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                //findByName 결과가 optinal member니까 앞에 생략해서 사용 가능
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    전체 회원 조회
     */

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
