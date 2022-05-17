package stringmatching;
public class KnuthMorrisPratt {
	synchronized public static String doKmp(String text, String pattern) {
		StringBuilder output = new StringBuilder();
		Integer[] longestPrefixSuffix = new Integer[pattern.length()];
		buildFailureFunction(pattern, longestPrefixSuffix);

		int textCurrentIndex = 0;
		int patternCurrentIndex = 0;
		while (textCurrentIndex < text.length()) {
			if (pattern.charAt(patternCurrentIndex) == text.charAt(textCurrentIndex)) {
				textCurrentIndex++;
				patternCurrentIndex++;
			}
			if (patternCurrentIndex == pattern.length()) {
				output.append(String.format("found pattern at index %d\n", (textCurrentIndex - patternCurrentIndex)));
				patternCurrentIndex = longestPrefixSuffix[patternCurrentIndex - 1];
			} else if (textCurrentIndex < text.length()
					&& pattern.charAt(patternCurrentIndex) != text.charAt(textCurrentIndex)) {
				if (patternCurrentIndex != 0) {
					patternCurrentIndex = longestPrefixSuffix[patternCurrentIndex-1];
				} else {
					textCurrentIndex++;
				}
			}
		}
		return output.toString();
	}

	private static void buildFailureFunction(String pattern, Integer[] lps) {
		lps[0] = 0;
		int i = 1, longestPrefixSuffix = 0;
		while (i < pattern.length()) {
			if (pattern.charAt(i) == pattern.charAt(longestPrefixSuffix)) {
				lps[i++] = ++longestPrefixSuffix;
			} else {
				if (longestPrefixSuffix != 0) {
					longestPrefixSuffix = lps[longestPrefixSuffix - 1];
				} else {
					lps[i++] = 0;
				}
			}
		}
	}
}
