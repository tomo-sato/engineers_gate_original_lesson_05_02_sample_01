package jp.dcworks.engineersgate.inputform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.dcworks.engineersgate.inputform.dto.RequestInput;
import lombok.extern.log4j.Log4j2;

/**
 * 入力画面コントローラー。
 *
 * @author tomo-sato
 */
@Log4j2
@Controller
@RequestMapping("/")
public class InputController {

	/**
	 * [GET]初期表示処理。
	 * 　　・入力画面を表示する。
	 *
	 * @param model 入力フォームのオブジェクト
	 * @return テンプレートpath
	 */
	@GetMapping("input")
	public String index(Model model) {

		if (!model.containsAttribute("requestInput")) {
			model.addAttribute("requestInput", new RequestInput());
		}
		return "/input";
	}

	/**
	 * [POST]「確認画面へ」ボタン押下時のリクエスト処理。
	 * 　　・入力内容にエラーがあった場合、入力画面を表示する。
	 * 　　・エラーが無かった場合、確認画面を表示する。
	 *
	 * @param requestInput 入力フォームの内容
	 * @param result バリデーション結果
	 * @param redirectAttributes リダイレクト時に使用するオブジェクト
	 * @return テンプレートpath
	 */
	@PostMapping("input")
	public String index(@Validated @ModelAttribute RequestInput requestInput,
			BindingResult result,
			Model model,
			RedirectAttributes redirectAttributes) {

		log.info("アカウント作成処理のアクションが呼ばれました。：requestInput={}", requestInput);

		// バリデーション。
		if (result.hasErrors()) {
			log.warn("バリデーションエラーが発生しました。：requestInput={}, result={}", requestInput, result);

			// エラーメッセージを入力画面渡すFlashオブジェクトにセット。
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.requestInput", result);
			redirectAttributes.addFlashAttribute("requestInput", requestInput);

			// 入力画面へリダイレクト。
			return "redirect:/input";
		}

		// 完了画面へリダイレクト。
		return "/confirm";
	}
}
