package az.lcdinvite;

import java.util.HashMap;
import java.util.Map;

public class Encoder {
    private static Map<Character, Character> charmap = new HashMap<Character, Character>() {
        {
            put(' ', (char) 0x20);
            put('!', (char) 0x21);
            put('"', (char) 0x22);
            put('#', (char) 0x23);
            put('$', (char) 0x24);
            put('%', (char) 0x25);
            put('&', (char) 0x26);
            put('\'', (char) 0x27);
            put('(', (char) 0x28);
            put(')', (char) 0x29);
            put('*', (char) 0x2A);
            put('+', (char) 0x2B);
            put(',', (char) 0x2C);
            put('-', (char) 0x2D);
            put('.', (char) 0x2E);
            put('/', (char) 0x2F);
            put(':', (char) 0x3A);
            put(';', (char) 0x3B);
            put('<', (char) 0x3C);
            put('=', (char) 0x3D);
            put('>', (char) 0x3E);
            put('?', (char) 0x3F);
            put('@', (char) 0x40);
            put('[', (char) 0x5B);
            put(']', (char) 0x5D);
            put('^', (char) 0x5E);
            put('_', (char) 0x5F);
            put('`', (char) 0x60);
            put('А', (char) 0x41);
            put('Б', (char) 0xA0);
            put('В', (char) 0x42);
            put('Г', (char) 0xA1);
            put('Д', (char) 0xE0);
            put('Е', (char) 0x45);
            put('Ё', (char) 0xA2);
            put('Ж', (char) 0xA3);
            put('З', (char) 0xA4);
            put('И', (char) 0xA5);
            put('Й', (char) 0xA6);
            put('К', (char) 0x4B);
            put('Л', (char) 0xA7);
            put('М', (char) 0x4D);
            put('Н', (char) 0x48);
            put('О', (char) 0x4F);
            put('П', (char) 0xA8);
            put('Р', (char) 0x50);
            put('С', (char) 0x43);
            put('Т', (char) 0x54);
            put('У', (char) 0xA9);
            put('Ф', (char) 0xAA);
            put('Х', (char) 0x58);
            put('Ц', (char) 0xE1);
            put('Ч', (char) 0xAB);
            put('Ш', (char) 0xAC);
            put('Щ', (char) 0xE2);
            put('Ь', (char) 0x62);
            put('Ы', (char) 0xAE);
            put('Ъ', (char) 0xAD);
            put('Э', (char) 0xAF);
            put('Ю', (char) 0xB0);
            put('Я', (char) 0xB1);
            put('а', (char) 0x61);
            put('б', (char) 0xB2);
            put('в', (char) 0xB3);
            put('г', (char) 0xB4);
            put('д', (char) 0x67);
            put('е', (char) 0x65);
            put('ё', (char) 0xB5);
            put('ж', (char) 0xB6);
            put('з', (char) 0xB7);
            put('и', (char) 0xB8);
            put('й', (char) 0xB9);
            put('к', (char) 0xBA);
            put('л', (char) 0xBB);
            put('м', (char) 0xBC);
            put('н', (char) 0xBD);
            put('о', (char) 0x6F);
            put('п', (char) 0xBE);
            put('р', (char) 0x70);
            put('с', (char) 0x63);
            put('т', (char) 0xBF);
            put('у', (char) 0x79);
            put('ф', (char) 0xE4);
            put('х', (char) 0x78);
            put('ц', (char) 0xE5);
            put('ч', (char) 0xC0);
            put('ш', (char) 0xC1);
            put('щ', (char) 0xE6);
            put('ь', (char) 0xC4);
            put('ы', (char) 0xC3);
            put('ъ', (char) 0xC2);
            put('э', (char) 0xC5);
            put('ю', (char) 0xC6);
            put('я', (char) 0xC7);
            put('№', (char) 0xCC);
            put('~', (char) 0xE9);
        }
    };

    public static String encode(String text) {
        char[] chars = text.toCharArray();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i], c1 = 0;
            if (i > 0) out.append(",");
            if (c == '\\') {
                i++;
                c = chars[i];
                if (c == '\\') out.append("0xFF");
                else {
                    out.append("0x").append(Character.toUpperCase(c));
                    i++;
                    c = chars[i];
                    c1 = 0;
                    out.append(Character.toUpperCase(c));
                }
            } else if (c >= 'A' && c <= 'Z') {
                c1 = (char) (c - 'A' + 0x41);
            } else if (c >= 'a' && c <= 'z') {
                c1 = (char) (c - 'a' + 0x61);
            } else if (c >= '0' && c <= '9') {
                c1 = (char) (c - '0' + 0x30);
            } else if (c == '\n') {
                out.append('\n');
            } else {
                Character val = charmap.get(c);
                if (val == null) throw new RuntimeException("Unknown character " + c);
                c1 = val;
            }
            if (c1 != 0) {
                out.append("0x")
                        .append(Character.forDigit(c1 >>> 4, 16))
                        .append(Character.forDigit(c1 & 0xF, 16));
            }
        }
        return out.toString();
    }

    public static String decode(String text) {
        return text;
    }
}
