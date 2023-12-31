package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
/*

@Controller , Service , Repository 등록해줘야 spring이 @AutoWired를 통해 자동으로 연결해줌
디펜던시 의존

스프링 빈을 등록하는 방법
1.컴포넌트 스캔과 자동 의존관계 설정
-@Component 등록하면 스프링빈으로 자동 등록된다 ( @Service,@Controller,@Repository에 등록되어있음)
2.자바코드로 직접 스프링 빈 등록하기
-

src/main/java/hello.hellospring에 있는 helloSpringApllication을 실행시키기 때문에
hello.hellospring이 안에 있는 하위 패키지들만 spring이 spring 빈으로 등록해준다

springd은 스프링 컨테이너에 스프링빈을 등록할때 기본으로 싱글톤으로 등록한다
유일하게 하나만 등록해서 공유한다. 따라서 같은 스프링 빈이면 모두 같은 인스턴스다.
설정으로 싱글톤 아니게 설정할 수 있지만 잘 사용 안함.
*/

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
