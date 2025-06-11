package us.ampre.rets.common.metadata.attrib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.ampre.rets.common.metadata.AttrType;
import us.ampre.rets.common.metadata.MetaParseException;


class AttrAlphanumTest extends AttrTypeTest {
    private AttrType mShort;
    private AttrType mLong;

    @BeforeEach
    void setUp() {
        this.mShort = new AttrAlphanum(1, 10);
        this.mLong = new AttrAlphanum(10, 100);
    }

    @Test
    void testAlphanum() throws MetaParseException {
        String test1 = "1234567890";
        String test2 = "abcdefghijklmnopqrstuvwxyz";
        String test3 = test2.toUpperCase();
        String test4 = "123-_ 456";
        mShort.parse(test1, true);
        mLong.parse(test2, true);
        mLong.parse(test3, true);
        mShort.parse(test4, true);
    }

    @Test
    void testFailures() throws Exception {
        String test1 = "abcdefg%";
        String test2 = "!abcdefg";
        String test3 = "___^___ ";

        assertParseException(mShort, test1);
        assertParseException(mShort, test2);
        assertParseException(mShort, test3);
    }

    @Test
    void testLength() throws Exception {
        String test1 = "abcdefghij12345";
        String test2 = "12345";
        assertParseException(mShort, test1);
        assertParseException(mLong, test2);
    }
}