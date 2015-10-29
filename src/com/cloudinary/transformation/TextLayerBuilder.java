package com.cloudinary.transformation;

import java.util.ArrayList;

import com.cloudinary.SmartUrlEncoder;
import com.cloudinary.utils.StringUtils;
import com.cloudinary.codename1.util.CN1String;

public class TextLayerBuilder extends AbstractLayerBuilder<TextLayerBuilder> {
	protected String resourceType = "text";
	protected String fontFamily = null;
	protected Integer fontSize = null;
	protected String fontWeight = null;
	protected String fontStyle = null;
	protected String textDecoration = null;
	protected String textAlign = null;
	protected String stroke = null;
	protected String letterSpacing = null;
	protected String text = null;

	@Override
	TextLayerBuilder self() {
		return this;
	}

	public TextLayerBuilder resourceType(String resourceType) {
		throw new RuntimeException("Cannot modify resourceType for text layers");
	}

	public TextLayerBuilder type(String type) {
		throw new RuntimeException("Cannot modify type for text layers");
	}

	public TextLayerBuilder format(String format) {
		throw new RuntimeException("Cannot modify format for text layers");
	}

	public TextLayerBuilder fontFamily(String fontFamily) {
		this.fontFamily = fontFamily;
		return self();
	}

	public TextLayerBuilder fontSize(int fontSize) {
		this.fontSize = fontSize;
		return self();
	}

	public TextLayerBuilder fontWeight(String fontWeight) {
		this.fontWeight = fontWeight;
		return self();
	}

	public TextLayerBuilder fontStyle(String fontStyle) {
		this.fontStyle = fontStyle;
		return self();
	}

	public TextLayerBuilder textDecoration(String textDecoration) {
		this.textDecoration = textDecoration;
		return self();
	}

	public TextLayerBuilder textAlign(String textAlign) {
		this.textAlign = textAlign;
		return self();
	}

	public TextLayerBuilder stroke(String stroke) {
		this.stroke = stroke;
		return self();
	}

	public TextLayerBuilder letterSpacing(String letterSpacing) {
		this.letterSpacing = letterSpacing;
		return self();
	}

	public TextLayerBuilder text(String text) {
		this.text = new CN1String.Builder(SmartUrlEncoder.encode(text)).replace("%2C", "%E2%80%9A").replace("/", "%E2%81%84").toString();
		return self();
	}

	@Override
	public String toString() {
		if (this.publicId == null && this.text == null) {
			throw new IllegalArgumentException("Must supply either text or public_id.");
		}

		ArrayList<String> components = new ArrayList<String>();
		components.add(this.resourceType);

		String styleIdentifier = textStyleIdentifier();
		if (styleIdentifier != null) {
			components.add(styleIdentifier);
		}

		if (this.publicId != null) {
			components.add(this.formattedPublicId());
		}

		if (this.text != null) {
			components.add(this.text);
		}

		return StringUtils.join(components, ":");
	}

	protected String textStyleIdentifier() {
		ArrayList<String> components = new ArrayList<String>();

		if (StringUtils.isNotBlank(this.fontWeight) && !this.fontWeight.equals("normal"))
			components.add(this.fontWeight);
		if (StringUtils.isNotBlank(this.fontStyle) && !this.fontStyle.equals("normal"))
			components.add(this.fontStyle);
		if (StringUtils.isNotBlank(this.textDecoration) && !this.textDecoration.equals("none"))
			components.add(this.textDecoration);
		if (StringUtils.isNotBlank(this.textAlign))
			components.add(this.textAlign);
		if (StringUtils.isNotBlank(this.stroke) && !this.stroke.equals("none"))
			components.add(this.stroke);
		if (StringUtils.isNotBlank(this.letterSpacing))
			components.add("letter_spacing_" + this.letterSpacing);

		if (this.fontFamily == null && this.fontSize == null && components.isEmpty()) {
			return null;
		}

		if (this.fontFamily == null) {
			throw new IllegalArgumentException("Must supply fontFamily.");
		}

		if (this.fontSize == null) {
			throw new IllegalArgumentException("Must supply fontSize.");
		}

		components.add(0, Integer.toString(this.fontSize));
		components.add(0, this.fontFamily);

		return StringUtils.join(components, "_");

	}
}
