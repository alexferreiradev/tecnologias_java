package dev.alexferreira;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class JavaRegexTest {

    private final JavaRegex javaRegex = new JavaRegex();


    @Nested
    class PlainTextRegexTests {

        @Test
        void matches() {
            Assertions.assertTrue(javaRegex.matches("alexTeste", "alexTeste"));
            Assertions.assertFalse(javaRegex.matches("alexTeste", "alex"));
            Assertions.assertFalse(javaRegex.matches("alexTeste123", "alex123"));
            Assertions.assertTrue(javaRegex.matches("alexTeste123", "alexTeste123"));
        }

        @Test
        void contains() {
            Assertions.assertTrue(javaRegex.contains("alexTeste", "alex"));
            Assertions.assertTrue(javaRegex.contains("alexTeste", "Teste"));
            Assertions.assertFalse(javaRegex.contains("alexTeste", "1"));
            Assertions.assertTrue(javaRegex.contains("alexTeste123", "123"));
            Assertions.assertFalse(javaRegex.contains("alexTeste123", "13"));
            Assertions.assertTrue(javaRegex.contains("alexTeste123", "T"));
            Assertions.assertFalse(javaRegex.contains("alexaTesTe123", "t"));
            Assertions.assertFalse(javaRegex.contains("alexateste123", "T"));
        }

        @Test
        void find() {
            Assertions.assertEquals("Teste", javaRegex.find("alexTeste", "Teste"));
            Assertions.assertEquals("a", javaRegex.find("alex", "a"));
            Assertions.assertEquals("e", javaRegex.find("alexTeste", "e"));

            Assertions.assertEquals("12", javaRegex.find("AlexTeste123", "12"));
            Assertions.assertNull(javaRegex.find("AlexTeste123", "13"));
        }

        @Test
        void findAll() {
            Assertions.assertEquals("e", javaRegex.findAll("alexTeste", "e").get(0));
            Assertions.assertEquals(3, javaRegex.findAll("alexTeste", "e").size());
        }
    }

    @Nested
    class NumberRegexTests {

        @Test
        void matches() {
            Assertions.assertTrue(javaRegex.matches("1", "\\d"));
            Assertions.assertFalse(javaRegex.matches("12", "\\d"));
            Assertions.assertFalse(javaRegex.matches("123", "\\d"));

            Assertions.assertTrue(javaRegex.matches("123", "\\d\\d\\d"));
            Assertions.assertTrue(javaRegex.matches("a1lex", "a\\dlex"));

            Assertions.assertFalse(javaRegex.matches("alex", "\\d"));
            Assertions.assertFalse(javaRegex.matches("alexALEX", "\\d"));
            Assertions.assertTrue(javaRegex.matches("alexALEX123", "alexALEX\\d23"));
        }

        @Test
        void contains() {
            Assertions.assertTrue(javaRegex.contains("1", "\\d"));
            Assertions.assertTrue(javaRegex.contains("12", "\\d"));
            Assertions.assertTrue(javaRegex.contains("123", "\\d"));

            Assertions.assertTrue(javaRegex.contains("123", "\\d\\d"));
            Assertions.assertTrue(javaRegex.contains("123", "\\d\\d\\d"));
            Assertions.assertFalse(javaRegex.contains("123", "\\d\\d\\d\\d"));
            Assertions.assertTrue(javaRegex.contains("a1lex", "\\d"));

            Assertions.assertFalse(javaRegex.contains("alex", "\\d"));
            Assertions.assertFalse(javaRegex.contains("alexALEX", "\\d"));
            Assertions.assertTrue(javaRegex.contains("alexALEX123", "alexALEX\\d"));
            Assertions.assertTrue(javaRegex.contains("alexALEX123", "\\d"));
        }

        @Test
        void find() {
            Assertions.assertEquals("1", javaRegex.find("1", "\\d"));
            Assertions.assertEquals("1", javaRegex.find("12", "\\d"));
            Assertions.assertEquals("1", javaRegex.find("123", "\\d"));

            Assertions.assertEquals("12", javaRegex.find("123", "\\d\\d"));
            Assertions.assertEquals("123", javaRegex.find("123", "\\d\\d\\d"));
            Assertions.assertNull(javaRegex.find("123", "\\d\\d\\d\\d"));
            Assertions.assertEquals("1", javaRegex.find("a1lex", "\\d"));

            Assertions.assertNull(javaRegex.find("alex", "\\d"));
            Assertions.assertNull(javaRegex.find("alexALEX", "\\d"));
            Assertions.assertEquals("alexALEX1", javaRegex.find("alexALEX123", "alexALEX\\d"));
            Assertions.assertEquals("1", javaRegex.find("alexALEX123", "\\d"));
        }

        @Test
        void findAllNumbers() {
            Assertions.assertEquals(3, javaRegex.findAll("alexALEX123", "\\d").size());
            Assertions.assertEquals(4, javaRegex.findAll("alex2ALEX123", "\\d").size());
        }
    }

    @Nested
    class WordsRegexTests {

        @Test
        void matches() {
            Assertions.assertTrue(javaRegex.matches("1", "1"));
            Assertions.assertTrue(javaRegex.matches("1", "\\d"));
            Assertions.assertTrue(javaRegex.matches("1", "\\w"));
            Assertions.assertTrue(javaRegex.matches("9", "\\w"));
            Assertions.assertTrue(javaRegex.matches("0", "\\w"));
            Assertions.assertTrue(javaRegex.matches("a", "\\w"));
            Assertions.assertTrue(javaRegex.matches("_", "\\w"));
            Assertions.assertTrue(javaRegex.matches("A", "\\w"));
            Assertions.assertTrue(javaRegex.matches("b", "\\w"));
            Assertions.assertTrue(javaRegex.matches("z", "\\w"));
            Assertions.assertTrue(javaRegex.matches("Z", "\\w"));
        }

        @Test
        void find() {
            Assertions.assertEquals("1", javaRegex.find("++++1a", "\\w"));
            Assertions.assertEquals("1a", javaRegex.find("+++1a", "1\\w"));
        }

        @Test
        void findAllNumbers() {
            Assertions.assertEquals(4, javaRegex.findAll("alex", "\\w").size());
            Assertions.assertEquals("alex123gmailcom", String.join("", javaRegex.findAll("alex.123@gmail.com", "\\w")));
        }
    }

    @Nested
    class QuantityRegexTests {

        @Test
        void matches() {
            Assertions.assertTrue(javaRegex.matches("1", "1"));
            Assertions.assertTrue(javaRegex.matches("1", "\\d"));
            Assertions.assertTrue(javaRegex.matches("111", "\\d{3}"));
            Assertions.assertFalse(javaRegex.matches("1111", "\\d{3}"));
            Assertions.assertFalse(javaRegex.matches("11", "\\d{3}"));

            Assertions.assertTrue(javaRegex.matches("11", "\\d{1,3}"));
            Assertions.assertTrue(javaRegex.matches("1", "\\d{1,3}"));
            Assertions.assertFalse(javaRegex.matches("a", "\\d{1,3}"));

            Assertions.assertTrue(javaRegex.matches("a", "a\\d{0,3}"));
            Assertions.assertTrue(javaRegex.matches("1", "\\d{0,1}"));
            Assertions.assertTrue(javaRegex.matches("a", "a\\d{0,1}"));

            Assertions.assertTrue(javaRegex.matches("1111", "\\d{0,}"));
            Assertions.assertTrue(javaRegex.matches("1111", "\\d*"));

            Assertions.assertTrue(javaRegex.matches("a1", "a\\d?"));
            Assertions.assertTrue(javaRegex.matches("a", "a\\d?"));
            Assertions.assertFalse(javaRegex.matches("a12", "a\\d?"));

            Assertions.assertTrue(javaRegex.matches("a12", "a\\d+"));
            Assertions.assertFalse(javaRegex.matches("a", "a\\d+"));

            Assertions.assertTrue(javaRegex.matches("a", "a\\d*"));
            Assertions.assertTrue(javaRegex.matches("a123", "a\\d*"));
            Assertions.assertTrue(javaRegex.matches("a123", "\\w+\\d*"));
            Assertions.assertTrue(javaRegex.matches("alex123", "\\w+\\d*"));
        }
    }

    @Nested
    class WildCardRegexTests {

        @Test
        void matches() {
            Assertions.assertTrue(javaRegex.matches("1", "1"));
            Assertions.assertTrue(javaRegex.matches("1", "\\d"));
            Assertions.assertTrue(javaRegex.matches("1", "."));
            Assertions.assertFalse(javaRegex.matches("1111", "..."));
            Assertions.assertFalse(javaRegex.matches("11", "..."));

            Assertions.assertTrue(javaRegex.matches("a", "."));
            Assertions.assertTrue(javaRegex.matches("1", "."));
            Assertions.assertTrue(javaRegex.matches("*", "."));
            Assertions.assertTrue(javaRegex.matches("+", "."));
            Assertions.assertTrue(javaRegex.matches("`", "."));
            Assertions.assertTrue(javaRegex.matches("a1`[]", "....."));

            Assertions.assertTrue(javaRegex.matches("11", ".+"));
            Assertions.assertTrue(javaRegex.matches("1", ".?"));
            Assertions.assertTrue(javaRegex.matches("a123*90-+[]'~", ".*"));
        }
    }

    @Nested
    class AdvancedRegexTests {

        @Test
        void find() {
            Assertions.assertEquals("12345", javaRegex.find("telefone:12345", "(?<=telefone\\:)\\d+"));
            Assertions.assertEquals("12345", javaRegex.find("telefone: 12345;", "(?<=telefone: )\\d+(?=;)"));
        }

        @Test
        void group() {
            Assertions.assertEquals(2, javaRegex.findAll("nome:alextelefone:456", "nome:(\\w+)telefone:(\\d+)").size());
            Assertions.assertEquals("alex,456", String.join(",",
                    javaRegex.findAll("nome:alextelefone:456", "nome:(\\w+)telefone:(\\d+)")
            ));
        }
    }
}
