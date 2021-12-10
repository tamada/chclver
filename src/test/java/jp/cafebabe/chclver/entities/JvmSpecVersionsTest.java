package jp.cafebabe.chclver.entities;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JvmSpecVersionsTest {
    @Test
    public void testFind() {
        assertEquals(Optional.of(Version.of(55, 0)), JvmSpecVersions.find("11"));
    }

    @Test
    public void testValidVersionLabel() {
        assertTrue(JvmSpecVersions.isValidVersionLabel("1.0"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("1.1"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("1.2"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("1.3"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("1.4"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("5"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("6"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("7"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("8"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("9"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("10"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("11"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("12"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("13"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("14"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("15"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("16"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("17"));
        assertTrue(JvmSpecVersions.isValidVersionLabel("18"));
    }
}
