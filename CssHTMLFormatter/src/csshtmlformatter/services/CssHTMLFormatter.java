package csshtmlformatter.services;

import java.util.regex.Pattern;

import csshtmlformatter.Activator;
import m2dl.osgi.editor.ICssHTMLFormatter;

public class CssHTMLFormatter implements ICssHTMLFormatter {

	Pattern keyword = Pattern.compile(":keyword\\{~(.*?)~\\}");
	Pattern comment = Pattern.compile(":tag\\{comment\\{~(.*?)~\\}", Pattern.DOTALL);
	String startHtml = "<html><head><style>.comment {color:#686868;} .keyword {color: #FE2E2E}</style>";
	String endHtml = "</head><body></body></html>";

	public CssHTMLFormatter() {
	}

	@Override
	public String getHTML(String code) {
		code = Activator.getCssServiceTracker().getService().getMarkedCode(code);
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
