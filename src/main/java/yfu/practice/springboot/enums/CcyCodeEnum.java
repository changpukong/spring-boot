package yfu.practice.springboot.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public enum CcyCodeEnum {
	
	USD("02"),
	HKD("03"),
	GBP("05"),
	CHF("06"),
	AUD("07"),
	SGD("08"),
	CAD("11"),
	SEK("13"),
	ZAR("15"),
	JPY("16"),
	DKK("20"),
	THB("25"),
	NZD("26"),
	CNY("29"),
	EUR("30"),
	TRY("38");
	
	private String ccyCode;
	
	private static final Map<String, String> MAP = new HashMap<>();
	
	private CcyCodeEnum(String ccyCode) {
		this.ccyCode = ccyCode;
	}

	public String getCcyCode() {
		return ccyCode;
	}
	
	public static Optional<String> getCcyName(String ccyCode) {
		if (MAP.isEmpty()) {
			Stream.of(CcyCodeEnum.values())
				.forEach(e -> MAP.put(e.getCcyCode(), e.name()));
		}
		
		return Optional.ofNullable(MAP.get(ccyCode));
	}

}