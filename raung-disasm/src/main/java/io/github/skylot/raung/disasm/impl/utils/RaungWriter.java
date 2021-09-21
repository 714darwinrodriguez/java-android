package io.github.skylot.raung.disasm.impl.utils;

import io.github.skylot.raung.common.DirectiveToken;

@SuppressWarnings("UnusedReturnValue")
public class RaungWriter {
	public static final String NL = System.getProperty("line.separator");
	public static final String INDENT_STR = "    ";

	private final StringBuilder sb = new StringBuilder();
	private String indentStr = "";
	private int indent = 0;

	public RaungWriter startLine() {
		sb.append(NL);
		sb.append(indentStr);
		return this;
	}

	public RaungWriter startLine(String line) {
		startLine();
		sb.append(line);
		return this;
	}

	public RaungWriter startLine(DirectiveToken directive) {
		startLine();
		add(directive);
		return this;
	}

	public RaungWriter add(DirectiveToken directive) {
		sb.append(directive.token());
		sb.append(' ');
		return this;
	}

	public RaungWriter add(String str) {
		sb.append(str);
		return this;
	}

	public RaungWriter add(int number) {
		sb.append(number);
		return this;
	}

	public RaungWriter add(char ch) {
		sb.append(ch);
		return this;
	}

	public RaungWriter space() {
		sb.append(' ');
		return this;
	}

	public RaungWriter increaseIndent() {
		indent++;
		indentStr = buildIndentStr();
		return this;
	}

	public RaungWriter decreaseIndent() {
		indent--;
		indentStr = buildIndentStr();
		return this;
	}

	private String buildIndentStr() {
		switch (indent) {
			case 0:
				return "";
			case 1:
				return INDENT_STR;
			case 2:
				return INDENT_STR + INDENT_STR;
			default:
				StringBuilder indSb = new StringBuilder(INDENT_STR.length() * indent);
				for (int i = 0; i < indent; i++) {
					indSb.append(INDENT_STR);
				}
				return indSb.toString();
		}
	}

	public String getCode() {
		return sb.toString();
	}
}
