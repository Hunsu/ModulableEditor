package javahtmlformatter.services;

import java.util.regex.Pattern;

import javahtmlformatter.Activator;
import m2dl.osgi.editor.IJavaHTMLFormatter;

public class JavaHTMLFormatter implements IJavaHTMLFormatter {

	Pattern keyword = Pattern.compile(":keyword\\{~(.*?)~\\}");
	Pattern comment = Pattern.compile(":tag\\{comment\\{~(.*?)~\\}", Pattern.DOTALL);
	String startHtml = "<html><head><style>.comment {color:#686868;} .keyword {color: #FE2E2E}</style>";
	String endHtml = "</head><body></body></html>";

	public JavaHTMLFormatter() {
	}

	@Override
	public String getHTML(String code) {
		System.out.println(Activator.getJavaServiceTracker().getService());
			code = Activator.getJavaServiceTracker().getService().getMarkedCode(code);
		code = format(code);
		code = code.replaceAll("\\n", "<br />");
		code = startHtml + code + endHtml;
		return code;
	}

	private String format(String code) {
		code = keyword.matcher(code).replaceAll("<span class='keyword'>$1</span>");
		return comment.matcher(code).replaceAll("<span class='comment'>$1</span>");
	}

}
