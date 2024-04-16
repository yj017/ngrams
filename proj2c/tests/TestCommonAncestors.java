import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import main.AutograderBuddy;
import main.Node;
import main.Reader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

public class TestCommonAncestors {
    public static final String WORDS_FILE = "data/ngrams/very_short.csv";
    public static final String LARGE_WORDS_FILE = "data/ngrams/top_14377_words.csv";

    public static final String big_WORDS_FILE = "data/ngrams/top_49887_words.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets16.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms16.txt";
    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";

    /** This is an example from the spec for a common-ancestors query on the word "adjustment".
     * You should add more tests for the other spec examples! */
    @Test
    public void testSpecAdjustment() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("adjustment");

        NgordnetQuery nq = new NgordnetQuery(words, 2000, 2020, 0, NgordnetQueryType.ANCESTORS);
        String actual = studentHandler.handle(nq);
        String expected = "[adjustment, alteration, event, happening, modification, natural_event, occurrence, occurrent]";
        assertThat(actual).isEqualTo(expected);
    }

    // TODO: Add more unit tests (including edge case tests) here.

    // TODO: Create similar unit test files for the k != 0 cases.
    @Test
    public void GraphTest1() {
        Reader test = new Reader(SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> list = new ArrayList<>();
        list.add("change");
        list.add("adjustment");
        System.out.println(test.getGraph().multiAncestorFinder(list));
    }
    @Test
    public void test() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                big_WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("adjustment", "change");

        NgordnetQuery nq = new NgordnetQuery(words, 2000, 2020, 7, NgordnetQueryType.ANCESTORS);
        String actual = studentHandler.handle(nq);
        System.out.println(actual);
    }
    @Test
    public void oneWord () {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("listener");

        NgordnetQuery nq = new NgordnetQuery(words, 2000, 2020, 0, NgordnetQueryType.ANCESTORS);
        String actual = studentHandler.handle(nq);
        String expected = "[animate_thing, attender, auditor, beholder, being, causal_agency, causal_agent, cause, entity, hearer, individual, listener, living_thing, mortal, object, observer, organism, perceiver, percipient, person, physical_entity, physical_object, somebody, someone, soul, unit, whole]";
        assertThat(actual).isEqualTo(expected);
    }
    @Test
    public void test2() {
        Reader test = new Reader(LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        System.out.println(test.getGraph().allAncestor(45463, new HashSet<>()));
    }
    @Test
    public void twoWord () {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                LARGE_WORDS_FILE, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("hang", "swap", "reduction");

        NgordnetQuery nq = new NgordnetQuery(words, 2000, 2020, 1, NgordnetQueryType.ANCESTORS);
        String actual = studentHandler.handle(nq);
        String expected = "[act]";
        assertThat(actual).isEqualTo(expected);
    }
}
