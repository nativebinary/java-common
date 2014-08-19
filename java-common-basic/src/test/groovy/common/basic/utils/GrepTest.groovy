package common.basic.utils

import spock.lang.Specification

class GrepTest extends Specification {

    def input = "I am a boy!\n" +
                "You are a girl?\n" +
                "He is my father.\n" +
                "She is her mother~\n" +
                "GrepTDDCode";

    def "ctor"() {
        when: new Grep()
        then: thrown(InstantiationException)
    }

    def "Execute"() {

        expect:
            Grep.execute(input, toFind) == i

        where:
            toFind      ||  i
            "girl"      ||  "You are a girl?"
            "is"        ||  "He is my father.\nShe is her mother~"
            "her"       ||  "He is my father.\nShe is her mother~"
            "~"         ||  "She is her mother~"
            " "         ||  "I am a boy!\nYou are a girl?\nHe is my father.\nShe is her mother~"
            "m a"       ||  "I am a boy!"
            "He is my"  ||  "He is my father."

    }

    def "ExecuteInvert"() {

        expect:
            Grep.executeInvert(input, toFind) == i

        where:
            toFind      ||  i
            "girl"      ||  "I am a boy!\nHe is my father.\nShe is her mother~\nGrepTDDCode"
            "is"        ||  "I am a boy!\nYou are a girl?\nGrepTDDCode"
            "her"       ||  "I am a boy!\nYou are a girl?\nGrepTDDCode"
            "~"         ||  "I am a boy!\nYou are a girl?\nHe is my father.\nGrepTDDCode"
            " "         ||  "GrepTDDCode"
            "m a"       ||  "You are a girl?\nHe is my father.\nShe is her mother~\nGrepTDDCode"
            "He is my"  ||  "I am a boy!\nYou are a girl?\nShe is her mother~\nGrepTDDCode"
    }
}
