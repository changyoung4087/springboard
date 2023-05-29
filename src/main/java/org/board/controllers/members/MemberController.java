package org.board.controllers.members;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.board.models.member.MemberSaveService;
import org.hibernate.mapping.Join;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberSaveService saveService;
    private final JoinValidator joinValidator;

    @GetMapping("/join")
    public String join(@ModelAttribute JoinForm joinForm, Model model) {

//        JoinForm joinForm = new JoinForm();
//        model.addAttribute("JoinForm", joinForm);
        commonProcess(model);

        return "member/join";
    }
    @PostMapping("/join")
    public String joinPs(@Valid JoinForm joinForm, Errors errors, Model model) {
        commonProcess(model);

        // 검증을 실패해도 아래 if 조건에서 걸림.
        joinValidator.validate(joinForm, errors);

        if (errors.hasErrors()){
            return "member/join";
        }

        saveService.save(joinForm);
        return "redirect:/member/login";
    }

    @GetMapping("/login")
    public String login() {

        return "member/login";
    }

    private void commonProcess(Model model){
        model.addAttribute("pageTitle", "회원가입");
    }
}
