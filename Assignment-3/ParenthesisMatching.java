//import java.util.Stack;
package com.gradescope.assignment1;

import com.gradescope.assignment1.AbstractParenthesisMatching;
import com.gradescope.assignment1.DemoStack;
public class ParenthesisMatching extends AbstractParenthesisMatching{
    public Boolean match_parenthesis(String s){
        DemoStack st = new DemoStack();     // defining a stack to work with from the imported DemoStack
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);     // pushing in case of an opening paranthesis
            }
            if(ch == ')' || ch == '}' || ch == ']') {
                if (st.is_empty() == false) {
                    if (ch == ')' && st.top() == '(' || ch == '}' && st.top() == '{' || ch == ']' && st.top() == '[') {
                        st.pop();
                    }
                    else {
                        return false;
                    }
                }
                else {
                    return false;
                }
            }
        }
        return st.is_empty();
    }
}
