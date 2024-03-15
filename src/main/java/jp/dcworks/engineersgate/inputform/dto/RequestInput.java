package jp.dcworks.engineersgate.inputform.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Input DTOクラス。
 * 入力画面で入力された値を保持するクラス。
 *
 * @author tomo-sato
 */
@Data
public class RequestInput implements Serializable {

	/** 名前 */
	@NotBlank(message = "名前は必須項目となります。")
	@Size(max = 32, message = "名前は最大32文字です。")
	private String name;

	/** 問い合わせ内容 */
	@NotBlank(message = "問い合わせ内容は必須項目となります。")
	@Size(max = 1000, message = "問い合わせ内容は最大1000文字です。")
	private String body;

}
