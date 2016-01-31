package Kotska;

import java.lang.reflect.Array;

public class SUtils {
	
	public static int globalcount = 0;

	public static <T> T[] array_concat(T[] a, T[] b)
	{
		int alen = a.length;
		int blen = b.length;
		
		@SuppressWarnings("unchecked")
		
		T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), alen + blen);
		System.arraycopy(a, 0, c, 0, alen);
		System.arraycopy(b, 0, c, alen, blen);
		
		return c;
	}
	
}
