package org.robot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ElRobot {
	static Map<Integer, Integer> conversion = new HashMap<Integer, Integer>();;

	static {
		conversion.put(40, 519);
	}

	public static void main(String[] args) throws AWTException, IOException,
			InterruptedException {

		ElClipboard clp = new ElClipboard();
		String contenido = clp.getClipboardContents();

		System.out.println("cambia de ventana rapido!");
		Thread.sleep(2000);

		System.out.println("end delay!");

		pressKeys(contenido);

		// conKK(contenido);

	}

	private static void conKK(String contenido) throws AWTException,
			InterruptedException {
		KeyboardKeys kk = new KeyboardKeys();

		int total = contenido.length();
		for (int i = 0; i < total; i++) {
			char c = contenido.charAt(i);
			kk.keyPress(c);
			Thread.sleep(10);
		}
	}

	private static void pressKeys(String contenido)
			throws InterruptedException, AWTException {
		byte[] bytes = contenido.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			System.out.print(bytes[i] + ",");
		}

		// Runtime.getRuntime().exec("notepad");

		Robot robot = new Robot();
		KeyboardKeys kk = new KeyboardKeys();

		for (int i = 0; i < bytes.length; i++) {
			int cur = bytes[i];

			if (cur >= 65 && cur <= 90) {
				// Mayuscula: poner shift
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(cur);
				robot.keyRelease(cur);
				robot.keyRelease(KeyEvent.VK_SHIFT);
			} else if (cur >= 97 && cur <= 122) {
				// Minusculas: convertir en key mayuscula
				cur -= 32;
				robot.keyPress(cur);
				robot.keyRelease(cur);
			} else {
				// Cualquier otra cosa
				//
				// // Si tiene equivalencia, usarla
				// Integer converted = conversion.get(cur);
				// if (converted != null) {
				// System.out.println("converted = " + converted);
				// cur = converted;
				// }
				//
				// // Cualquier otra cosa
				// System.out.println("cur = " + cur);
				// // robot.keyPress(KeyEvent.VK_LEFT_PARENTHESIS);

				char c = contenido.charAt(i);
				kk.keyPress(c);
				Thread.sleep(10);

			}

			robot.delay(5);

		}
	}
}