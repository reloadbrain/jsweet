package source.nativestructures;

import static jsweet.util.Globals.$export;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import def.js.Array;

/**
 * This test is executed without any Java runtime.
 */
public class Collections implements Cloneable, Serializable {

	static Array<String> trace = new Array<>();

	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();

		l.add("a");

		trace.push("" + l.size());
		trace.push(l.get(0));

		l.add("b");

		l.remove(0);

		trace.push("" + l.size());
		trace.push(l.get(0));

		l.add("a");
		l.add("b");

		trace.push("" + l.size());

		l.add(1, "d");

		trace.push("" + l.size());
		trace.push(l.get(1));
		trace.push(l.get(2));

		trace.push(l.subList(1, 2).get(0));

		l.clear();
		trace.push("" + l.size());

		// l.add("a");
		// l = java.util.Collections.EMPTY_LIST;

		trace.push("" + l.size());

		l.add("a");
		l = java.util.Collections.emptyList();

		trace.push("" + l.size());

		l.add("a");
		l = java.util.Collections.unmodifiableList(l);

		trace.push("" + l.get(0));

		List l2 = new ArrayList(l);

		trace.push("" + l2.get(0));

		l.addAll(l2);

		trace.push("" + l.size());
		trace.push("" + l.get(1));

		trace.push("" + l.contains("a"));
		trace.push("" + l.contains("b"));

		String[] array = { "a", "b", "c" };

		l = Arrays.asList(array);

		trace.push("" + l.size());
		trace.push("" + l.get(2));

		Vector v = new Vector(l);

		trace.push("" + v.elementAt(2));

		Enumeration<String> e = v.elements();
		while (e.hasMoreElements()) {
			String s = e.nextElement();
			trace.push("" + s);
		}

		Iterator it = v.iterator();
		while (it.hasNext()) {
			String s = (String) it.next();
			trace.push("" + s);
		}

		trace.push("" + v.toArray()[1]);

		String[] a = Arrays.copyOf(l.toArray(new String[0]), 1);

		trace.push("" + a.length);

		Comparator<String> reverse = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		};

		String[] newArray = (String[]) v.toArray();

		Arrays.sort(newArray, reverse);

		trace.push(newArray[0]);
		trace.push(newArray[1]);
		trace.push(newArray[2]);

		Arrays.sort(newArray, 0, 2);

		trace.push(newArray[0]);
		trace.push(newArray[1]);
		trace.push(newArray[2]);

		l = new Vector<>(5);

		trace.push("" + l.size());

		trace.push("" + l.isEmpty());

		trace.push("" + (l instanceof Vector));

		int i = Integer.MIN_VALUE;
		i = Integer.MAX_VALUE;

		l.add("it");

		for (String s : l) {
			trace.push(s);
		}

		Set<String> s = java.util.Collections.emptySet();
		trace.push("" + s.isEmpty());

		s.add("test");
		s.add("test");

		trace.push("" + s.size());

		$export("trace", trace.join(","));

	}

}

class TestClone {

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
