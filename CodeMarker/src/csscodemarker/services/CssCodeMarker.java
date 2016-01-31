package csscodemarker.services;

import java.util.regex.Pattern;

public class CssCodeMarker implements ICssCodeMarker {

	private Pattern keywords = Pattern.compile("(.*?:)");
	private Pattern oneLineComment = Pattern.compile("(//.*)");
	private Pattern multiLineComment = Pattern.compile("(/\\*.*?\\*/)", Pattern.DOTALL);
	private String commentTag = ":tag{comment{~";
	private String endCommentTag = "~}";

	@Override
	public String getMarkedCode(String code) {
		code = markKeywords(code);
		code = markComments(code);
		return code;
	}

	private String markKeywords(String code) {
		code = keywords.matcher(code).replaceAll(":keyword{~$0~}");
		return code;
	}

	private String markComments(String code) {
		code = oneLineComment.matcher(code).replaceAll(commentTag + "$1" + endCommentTag);
		return multiLineComment.matcher(code).replaceAll(commentTag + "$1" + endCommentTag);
	}

}
