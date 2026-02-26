package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SpellCheckerTest {

	// A spellchecker should be able to tell us how many words it currently knows about
	@Test
	void testInitialWordCountIsZero() {

		SpellChecker checker = new SpellChecker();
		int words = checker.getNumberOfWords();

		assertEquals(0, words);
	}

	// The number of words should go up by one whenever a new word is added
	@Test
	void testAddWordIncreasesCount() {
		SpellChecker checker = new SpellChecker();
		checker.addWord("word");
		int words = checker.getNumberOfWords();

		assertEquals(1, words);
	}

	// If a word is added that is already contained in the spellchecker, then the number of words contained in the spellchecker should not change
	@Test
	void testAddingDuplicateWordDoesNotIncreaseCount() {
		SpellChecker checker = new SpellChecker();
		checker.addWord("apple");
		checker.addWord("apple");
		int words = checker.getNumberOfWords();

		assertEquals(1, words);
	}

	// A spellchecker should be able to accept a properly spelled word and return an indication that it is properly spelled
	@Test
	void testCorrectWordReturnsTrue() {
		SpellChecker checker = new SpellChecker();
		checker.addWord("correct");
		boolean correct = checker.isCorrect("correct");

		assertTrue(correct);
	}

	// A spellchecker should be able to accept an improperly spelled word and return an indication that it is improperly spelled
	@Test
	void testIncorrectWordReturnsFalse() {
		SpellChecker checker = new SpellChecker();
		checker.addWord("dog");
		boolean correct = checker.isCorrect("bog");

		assertFalse(correct);
	}

	// A spellchecker should ignore case when checking how words are spelled (i.e. "cat" and "CaT" are both properly spelled as long as "cat" (or "Cat" or "caT", etc.) appears in our spellchecker.
	@Test
	void testIgnoreCaseWhenCheckingSpelling() {
		SpellChecker checker = new SpellChecker();
		checker.addWord("cat");
		boolean correct = checker.isCorrect("CaT");

		assertTrue(correct);
	}

	// A spellchecker should be able to recommend a properly spelled word for any String that it is given. In other words, if I were to input the String "bamk", the spell checker could suggest that the word "bank" is properly spelled, meaning that the word "bank" is the alphabetically closest word in the spellchecker to the given String "bamk"
	void testSuggestWordForMisspelledWord() {
		SpellChecker checker = new SpellChecker();
		checker.addWord("bank");
		checker.addWord("cat");
		String suggestion = checker.suggestWord("bamk");

		assertEquals("bank", suggestion);
	}

	// If we ask the spellchecker to suggest a properly spelled word for a String that is already properly spelled, it should simply return the original word.
	@Test
	void testSuggestWordReturnsOriginalIfCorrect() {
		SpellChecker checker = new SpellChecker();
		checker.addWord("apple");
		String suggestion = checker.suggestWord("apple");

		assertEquals("apple", suggestion);
	}

	// Additional feature: removeWord should remove a word
	@Test
	void testRemoveWord() {
		SpellChecker checker = new SpellChecker();
		checker.addWord("cat");
		checker.removeWord("cat");
		boolean correct = checker.isCorrect("cat");

		assertFalse(correct);
	}

	// Additional feature: spellchecker should be able to clear all words
	@Test
	void testClearRemovesAllWords() {

		SpellChecker checker = new SpellChecker();
		checker.addWord("cat");
		checker.addWord("dog");

		checker.clear();

		int words = checker.getNumberOfWords();
		assertEquals(0, words);
	}
}