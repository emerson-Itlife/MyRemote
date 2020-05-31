package com.example.demo.app.inquiry;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {


	@GetMapping("/form")
	public String from(InquiryForm inquiryForm, Model model,
			@ModelAttribute("complete") String complete) {
		model.addAttribute("title","Inquiry Form");
		return "inquiry/form";
	}

//	@PostMapping("/form")
//	public String fromGoBack(InquiryForm inquiryForm, Model model) {
//		model.addAttribute("title","Inquiry Form");
//		return "inquiry/form";
//	}

	@PostMapping("/confirm")
	//@Validated タイムリーフ側で検査をかけるようにしたものがBindingResult resultに反映されるようになる
	public String confirm(@Validated InquiryForm inquiryForm, BindingResult result, Model model) {

		//入力制約に引っ掛かったら初期ページに戻してやる。
		if(result.hasErrors()) {
			model.addAttribute("title","Inquiry Form");
			return "inquiry/form";
		}

		model.addAttribute("title","Confirm page");
		return "inquiry/confirm";
	}

	@PostMapping("/complete")
	public String complete(@Validated InquiryForm inquiryForm, BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {

		//入力制約に引っ掛かったら初期ページに戻してやる。
		if(result.hasErrors()) {
			model.addAttribute("title","Inquiry Form");
			return "inquiry/form";
		}

//redirectAttributes　フラッシュスコープ；リダイレクト後に１度だけ値を保持して表示できる
//		リダイレクトはリクエストし直すからリクエストスコープに値を保存しても失われる
		redirectAttributes.addFlashAttribute("complete", "Registerd");
		return "redirect:/inquiry/form";
	}
}


