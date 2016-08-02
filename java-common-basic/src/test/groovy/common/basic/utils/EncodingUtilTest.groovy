package common.basic.utils

import spock.lang.Specification

class EncodingUtilTest extends Specification {
    def "ctor"() {
        when: new EncodingUtil()
        then: thrown(InstantiationException)
    }

    def "SubstringWithEucKrByteIndex"() {
        setup:
        def s = "0123456789일이삼사오0123456789육칠팔구십";
        def array = s.getBytes("EUC-KR");

        expect:
        EncodingUtil.substringWithEucKrByteIndex(s, i, length) == c
        EncodingUtil.substringWithEucKrByteIndex(array, i, length) == c

        where:
        i  | length || c
        0  | 2      || "01"
        2  | 2      || "23"
        4  | 2      || "45"
        6  | 2      || "67"
        8  | 2      || "89"
        10 | 2      || "일"
        12 | 2      || "이"
        14 | 2      || "삼"
        16 | 2      || "사"
        18 | 2      || "오"
        20 | 2      || "01"
        22 | 2      || "23"
        24 | 2      || "45"
        26 | 2      || "67"
        28 | 2      || "89"
        30 | 2      || "육"
        32 | 2      || "칠"
        34 | 2      || "팔"
        36 | 2      || "구"
        38 | 2      || "십"
    }
}
