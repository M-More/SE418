package com.baowei.wordladder;

import com.baowei.wordladder.exception.Invalidinput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@RestController
public class WordladderController {

    @RequestMapping("/showladder")
    public Stack<String> showladder(@RequestParam(value = "word1") String w1, @RequestParam(value = "word2") String w2) throws Exception{
        Set<String> dic = new HashSet<String>();
        String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
                "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        try {
            FileReader fr = new FileReader("src/main/java/dictionary.txt");
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                dic.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Invalid input error handling
        if (w1.length() != w2.length()){
            throw new Invalidinput("The two words must be the same length.");
        }

        if (w1.equals(w2)){
            throw new Invalidinput("The two words must be different.");
        }

        if ((! dic.contains(w1)) || (! dic.contains(w2))){
            throw new Invalidinput("The two words must be found in the dictionary.");
        }

        Stack<String> ladder;

        Set<String> word_used = new HashSet<String>();      // a set which stores used words
        Queue<Stack<String>> ladder_queue = new LinkedList<Stack<String>>();  //Create an empty queue of stacks

        word_used.add(w1);

        Stack<String> stack_1 = new Stack<String>();
        stack_1.push(w1);
        ladder_queue.offer(stack_1);        //Create/add a stack containing first word to the queue

        while (!ladder_queue.isEmpty())    //While the queue is not empty
        {
            Stack<String> current_stack = ladder_queue.poll();   // Dequeue a partial-ladder from the front of the queue

            String current_word = current_stack.peek();

            for (int i = 0; i < current_word.length(); ++i)     // nested loop
            {
                for (int j = 0; j < 26; ++j) {
                    String new_word = current_stack.peek();

                    StringBuilder temp = new StringBuilder(new_word);

                    temp.replace(i, i + 1, alphabet[j]);

                    new_word = temp.toString();

                    if (!(word_used.contains(new_word)) && dic.contains(new_word))
                    //If that neighbor word has not already been used in a ladder before
                    {
                        word_used.add(new_word);
                        if (new_word.equals(w2))   //If the neighbor word is second word, Stop
                        {
                            current_stack.push(new_word);
                            ladder = current_stack;
                            return ladder;
                        } else {
                            Stack<String> new_stack = (Stack<String>) current_stack.clone();
                            // Create a copy of the current partial-ladder stack.
                            new_stack.push(new_word);       // Put the neighbor word on top of the copy stack.
                            ladder_queue.offer(new_stack);  // Add the copy stack to the end of the queue.
                        }
                    }
                }
            }
        }
        return null;
    }
}

