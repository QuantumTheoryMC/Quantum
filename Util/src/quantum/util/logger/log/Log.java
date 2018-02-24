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
 * Created file on 11/13/16 at 8:53 PM.
 *
 * This file is part of Quantum API
 */
package quantum.util.logger.log;

import quantum.util.logger.Logger;
import quantum.util.time.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * @author link
 */
public final class Log {

	private final List<Entry> entries = new ArrayList<>(5);

	public void add(Entry entry) {
		entries.add(entry);
	}

	public Entry get(int index) {
		return entries.get(index);
	}

	public Entry getFirst() {
		return get(0);
	}

	public Entry getLast() {
		return get(entries.size() - 1);
	}

	public void remove(int index) {
		entries.remove(index);
	}

	public int getEntryCount() {
		return entries.size();
	}

	public static class Entry {

		private final Time            timestamp;
		private final Logger.Severity severity;
		private final Throwable       t;
		private final String          entry;

		private final String formatted;

		public Entry(Time timestamp, Logger.Severity severity, String entry) {
			this(timestamp, severity, null, entry);
		}

		public Entry(Time timestamp, Logger.Severity severity, Throwable t, String entry) {
			this.timestamp = timestamp;
			this.severity = severity;
			this.t = t;
			this.entry = entry;
			formatted = "[" + timestamp.getHour() + ":" + timestamp.getMinute() + ":" + timestamp.getSecond() + "][" + severity.name() + "]" + (t != null ? ("[" + t.getClass().getName() + "]") : ": ") + entry;
		}


		public final Time getTimeStamp() {
			return timestamp;
		}

		public final Logger.Severity getSeverity() {
			return severity;
		}

		public final Throwable getError() {
			return t;
		}

		public final String getMessage() {
			return entry;
		}

		@Override
		public String toString() {
			return formatted;
		}

	}
}
