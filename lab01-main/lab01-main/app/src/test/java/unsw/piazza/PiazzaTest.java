// package unsw.piazza;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// import java.util.Arrays;
// import java.util.List;

// import org.junit.jupiter.api.Test;

// /**
//  * Tests for Pineapple on Piazza
//  *
//  * @author Nick Patrikeos
//  */
// public class PiazzaTest {
//     @Test
//     public void testExampleUsage() {
//         // Create a forum and make some posts!
//         PiazzaForum forum = new PiazzaForum("COMP2511");
//         assertEquals("COMP2511", forum.getName());

//         String title = "The Real Question - Pineapple on Piazza";
//         String content = "Who likes pineapple on piazza?";
//         Thread funThread = forum.publish(title, content);

//         funThread.setTags(new String[] {
//                 "pizza", "coding", "social", "hobbies"
//         });
//         assertTrue(Arrays.equals(new String[] {
//                 "coding", "hobbies", "pizza", "social"
//         }, funThread.getTags().toArray()));

//         funThread.publishPost("Yuck!");
//         funThread.publishPost("Yes, pineapple on pizza is the absolute best");
//         funThread.publishPost("I think you misspelled pizza btw");
//         funThread.publishPost("I'll just fix that lol");

//         assertEquals(5, funThread.getPosts().size());
//     }

//     @Test
//     public void testSearchByTag() {
//         PiazzaForum forum = new PiazzaForum("COMP2511");

//         Thread labThread = forum.publish("Lab 01", "How do I do the piazza exercise?");
//         Thread assignmentThread = forum.publish("Assignment", "Are we back in blackout?");
//         labThread.setTags(new String[] {
//                 "Java"
//         });
//         assignmentThread.setTags(new String[] {
//                 "Java"
//         });

//         List<Thread> searchResults = forum.searchByTag("Java");
//         assertEquals("Lab 01", searchResults.get(0).getTitle());
//         assertEquals("Assignment", searchResults.get(1).getTitle());
//     }
// }
