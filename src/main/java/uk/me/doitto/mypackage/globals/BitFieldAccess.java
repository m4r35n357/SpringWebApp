package uk.me.doitto.mypackage.globals;

public class BitFieldAccess {
	
	public static boolean isFlag (BitFieldIf bitField, int flags) {
		return (flags & bitField.getValue()) != 0;
	}
	
	public static int setFlag (boolean set, BitFieldIf bitField, int flags) {
		if (set) {
			return flags | bitField.getValue();
		} else {
			return flags & ~bitField.getValue();
		}
	}
}
