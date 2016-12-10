/*
 * The MIT License
 *
 * Copyright 2016 link.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * Created file on 11/13/16 at 8:54 PM.
 *
 * This file is part of Quantum API
 */
package quantum.util.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

import static java.lang.StrictMath.round;

/**
 * @author link
 */
public final class Time {

	private static byte DECADE;
	private static byte CENTURY;
	private static byte MILLENNIUM;
	private static int  YEAR;

	// initialize temporal, synchronized "constants".
	static {
		LocalDateTime time = LocalDateTime.now();
		String digits = String.valueOf(YEAR = time.getYear());
		int index = digits.length() - 1;
		// set the DECADE and CENTURY digits
		DECADE = toDigit(digits.charAt(index));
		CENTURY = toDigit(digits.charAt(index--));
		// get MILLENNIUM characters
		char[] chars = new char[index--];
		System.arraycopy(digits.toCharArray(), 0, chars, 0, index);
		//
		MILLENNIUM = Byte.valueOf(String.valueOf(chars).trim());

	}

	private static byte toDigit(char value) {
		switch (value) {
			case '0':
				return 0;
			case '1':
				return 1;
			case '2':
				return 2;
			case '3':
				return 3;
			case '4':
				return 4;
			case '5':
				return 5;
			case '6':
				return 6;
			case '7':
				return 7;
			case '8':
				return 8;
			case '9':
				return 9;
			default:
				throw new IllegalArgumentException("The given value was not a numerical digit: " + value);
		}
	}

	private final long   nanos;
	private final long   millis;
	private final int    micros;
	private final int    second;
	private final int    minute;
	private final int    hour;
	private final String day;
	private final String month;
	private final int    year;

	private Time(String zone, Locale locale) {
		LocalDateTime current = LocalDateTime.now(ZoneId.of(zone));
		nanos = current.getNano();
		millis = nanos * 1000;
		micros = (int) (nanos / 100);
		second = current.getSecond();
		minute = current.getMinute();
		hour = current.getHour();
		day = current.getDayOfWeek().getDisplayName(TextStyle.FULL, locale);
		month = current.getMonth().getDisplayName(TextStyle.FULL, locale);
		year = current.getYear();
	}

	public static Time get(String zone, Locale locale) {
		return new Time(zone, locale);
	}

	public static Time get(String zone) {
		return get(zone, Locale.getDefault(Locale.Category.DISPLAY));
	}

	/**
	 * Gets the current coordinated Universal Time (UTC) time stamp.
	 *
	 * @return this instant in UTC
	 */
	public static Time get() {
		return get("UTC");
	}


	public static long nanos() {
		return System.nanoTime();
	}

	public static strictfp int micros() {
		return round(nanos() / 100);
	}

	public static long millis() {
		return System.currentTimeMillis();
	}

	public static int second() {
		return LocalDateTime.now().getSecond();
	}

	public static int minute() {
		return LocalDateTime.now().getMinute();
	}

	public static int hour() {
		return LocalDateTime.now().getHour();
	}

	public static String day(Locale locale) {
		return LocalDateTime.now()
		                    .getDayOfWeek()
		                    .getDisplayName(TextStyle.FULL, locale);
	}

	public static String month(Locale locale) {
		return LocalDateTime.now()
		                    .getMonth()
		                    .getDisplayName(TextStyle.FULL, locale);
	}

	public static int year() {
		return YEAR;
	}

	public static byte getDecade() {
		return DECADE;
	}

	public static byte getCentury() {
		return CENTURY;
	}

	public static byte getMillennium() {
		return MILLENNIUM;
	}

	public long getNanos() {
		return nanos;
	}

	public int getMicros() {
		return micros;
	}

	public long getMillis() {
		return millis;
	}

	public int getSecond() {
		return second;
	}

	public int getMinute() {
		return minute;
	}

	public int getHour() {
		return hour;
	}

	public String getDay() {
		return day;
	}

	public String getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

}
