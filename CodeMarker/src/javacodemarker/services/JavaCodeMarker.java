package javacodemarker.services;

import java.util.regex.Pattern;

public class JavaCodeMarker implements IJavaCodeMarker {

	private String[] keywords = new String[] { "abstract", "assert", "boolean", "break", "byte", "case", "catch",
			"char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final",
			"finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long",
			"native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
			"super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile",
			"while" };
	private Pattern oneLineComment = Pattern.compile("(//.*)");
	private Pattern multiLineComment = Pattern.compile("(/\\*.*?\\*/)", Pattern.DOTALL);
	private String commentTag = ":tag{comment{~";
	private String endCommentTag = "~}";

	@Override
	public String getMarkedCode(String code) {
		for (String keyword : keywords)
			code = code.replace(keyword, ":keyword{~" + keyword + "~}");
		code = markComments(code);
		return code;
	}

	private String markComments(String code) {
		code = oneLineComment.matcher(code).replaceAll(commentTag + "$1" + endCommentTag);
		return multiLineComment.matcher(code).replaceAll(commentTag + "$1" + endCommentTag);
	}

}
