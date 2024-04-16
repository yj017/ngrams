import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import browser.NgordnetQueryType;
import main.AutograderBuddy;
import main.Comp;
import main.NodeForKNot0;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.PriorityQueue;

import static com.google.common.truth.Truth.assertThat;

public class TestOneWordKNot0Hyponyms {
    public static final String WORDS_FILE = "data/ngrams/frequency-EECS.csv";
    public static final String TOTAL_COUNTS_FILE = "data/ngrams/total_counts.csv";
    public static final String SMALL_SYNSET_FILE = "data/wordnet/synsets-EECS.txt";
    public static final String SMALL_HYPONYM_FILE = "data/wordnet/hyponyms-EECS.txt";

    public static final String LARGE_SYNSET_FILE = "data/wordnet/synsets.txt";
    public static final String LARGE_HYPONYM_FILE = "data/wordnet/hyponyms.txt";
    public static final String top_14377_words = "data/ngrams/top_14377_words.csv";
    public static final String total_counts = "data/ngrams/total_counts.csv";

    @Test
    public void testActKNot0() {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                WORDS_FILE, TOTAL_COUNTS_FILE, SMALL_SYNSET_FILE, SMALL_HYPONYM_FILE);
        List<String> words = List.of("CS61A");

        NgordnetQuery nq = new NgordnetQuery(words, 2010, 2020, 4, NgordnetQueryType.HYPONYMS);
        String actual = studentHandler.handle(nq);
        String expected = "[CS170, CS61A, CS61B, CS61C]";
        assertThat(actual).isEqualTo(expected);
    }

    // TODO: Add more unit tests (including edge case tests) here.
    @Test
    public void test1 () {
        PriorityQueue<NodeForKNot0> pq = new PriorityQueue<>(new Comp());
        NodeForKNot0 node1 = new NodeForKNot0("a", 100.0);
        NodeForKNot0 node2 = new NodeForKNot0("b", 500.0);
        NodeForKNot0 node3 = new NodeForKNot0("c", 1.0);
        pq.add(node1);
        pq.add(node2);
        pq.add(node3);
        for (int i = 0; i < 2; i++) {
            System.out.println(pq.poll().word());
        }
    }
    @Test
    public void test2 () {
        NgordnetQueryHandler studentHandler = AutograderBuddy.getHyponymsHandler(
                top_14377_words, TOTAL_COUNTS_FILE, LARGE_SYNSET_FILE, LARGE_HYPONYM_FILE);
        List<String> words = List.of("food", "cake");

        NgordnetQuery nq = new NgordnetQuery(words, 1950, 1990, 7, NgordnetQueryType.HYPONYMS);
        String actual = studentHandler.handle(nq);
        String expected = "[cake, cookie, kiss, snap, wafer]";
        assertThat(actual).isEqualTo(expected);
    }
}
