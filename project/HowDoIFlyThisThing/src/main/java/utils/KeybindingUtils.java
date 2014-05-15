package utils;

import org.lwjgl.input.Keyboard;

/**
 *
 * @author Martin Nilsson & Francine Mäkelä
 */
public class KeybindingUtils {
	public static int getKeyboardKeyFormJava(int javaKey){
		switch(javaKey){
			//-----------------
			case(49):
			//1
				return Keyboard.KEY_1;
			//-----------------
			case(50):
			//2
				return Keyboard.KEY_2;
			//-----------------
			case(51):
			//3
				return Keyboard.KEY_3;
			//-----------------
			case(52):
			//4
				return Keyboard.KEY_4;
			//-----------------
			case(53):
			//5
				return Keyboard.KEY_5;
			//-----------------
			case(54):
			//6
				return Keyboard.KEY_6;
			//-----------------
			case(55):
			//7
				return Keyboard.KEY_7;
			//-----------------
			case(56):
			//8
				return Keyboard.KEY_8;
			//-----------------
			case(57):
			//9
				return Keyboard.KEY_9;
			//-----------------
			case(48):
			//0
				return Keyboard.KEY_0;
			//-----------------
			case(521):
			//+
				return Keyboard.KEY_ADD;
			//-----------------
			case(129):
			//´
				return Keyboard.KEY_GRAVE;
			//-----------------
			case(81):
			//q
				return Keyboard.KEY_Q;
			//-----------------
			case(87):
			//w
				return Keyboard.KEY_W;
			//-----------------
			case(69):
			//e
				return Keyboard.KEY_E;
			//-----------------
			case(82):
			//r
				return Keyboard.KEY_R;
			//-----------------
			case(84):
			//t
				return Keyboard.KEY_T;
			//-----------------
			case(89):
			//y
				return Keyboard.KEY_Y;
			//-----------------
			case(85):
			//u
				return Keyboard.KEY_U;
			//-----------------
			case(73):
			//i
				return Keyboard.KEY_I;
			//-----------------
			case(79):
			//o
				return Keyboard.KEY_O;
			//-----------------
			case(80):
			//p
				return Keyboard.KEY_P;
			//-----------------
			case(65):
			//a
				return Keyboard.KEY_A;
			//-----------------
			case(83):
			//s
				return Keyboard.KEY_S;
			//-----------------
			case(68):
			//d
				return Keyboard.KEY_D;
			//-----------------
			case(70):
			//f
				return Keyboard.KEY_F;
			//-----------------
			case(71):
			//g
				return Keyboard.KEY_G;
			//-----------------
			case(72):
			//h
				return Keyboard.KEY_H;
			//-----------------
			case(74):
			//j
				return Keyboard.KEY_J;
			//-----------------
			case(75):
			//k
				return Keyboard.KEY_K;
			//-----------------
			case(76):
			//l
				return Keyboard.KEY_L;
			//-----------------
			case(153):
			//<
				return Keyboard.KEY_EQUALS;
			//-----------------
			case(90):
			//z
				return Keyboard.KEY_Z;
			//-----------------
			case(88):
			//x
				return Keyboard.KEY_X;
			//-----------------
			case(67):
			//c
				return Keyboard.KEY_C;
			//-----------------
			case(86):
			//v
				return Keyboard.KEY_V;
			//-----------------
			case(66):
			//b
				return Keyboard.KEY_B;
			//-----------------
			case(78):
			//n
				return Keyboard.KEY_N;
			//-----------------
			case(77):
			//m
				return Keyboard.KEY_M;
			//-----------------
			case(44):
			//,
				return Keyboard.KEY_COMMA;
			//-----------------
			case(46):
			//.
				return Keyboard.KEY_PERIOD;
			//-----------------
			case(45):
			//-
				return Keyboard.KEY_MINUS;
			//-----------------
			case(32):
			//   [SPACE]
				return Keyboard.KEY_SPACE;
			//-----------------
			case(135):
			//   ^
				return Keyboard.KEY_CIRCUMFLEX;
			//-----------------
			case(222):
			//   '
				return Keyboard.KEY_APOSTROPHE;
			//-----------------
			case(16):
			//   shift
				return Keyboard.KEY_LSHIFT;
			//-----------------
			case(17):
			//   ctrl
				return Keyboard.KEY_LCONTROL;
			//-----------------
			case(18):
			//   alt
				return Keyboard.KEY_LMENU;
			//-----------------
			case(20):
			//   caps lock
				return Keyboard.KEY_CAPITAL;
			//-----------------
			case(37):
			//   left arrow
				return Keyboard.KEY_LEFT;
			//-----------------
			case(38):
			//   up arrow
				return Keyboard.KEY_UP;
			//-----------------
			case(39):
			//   right arrow
				return Keyboard.KEY_RIGHT;
			//-----------------
			case(40):
			//   down arrow
				return Keyboard.KEY_DOWN;
			//-----------------
			case(10):
			//   enter
				return Keyboard.KEY_RETURN;
		}
		return 0;
	}
}//end KeybindingUtils